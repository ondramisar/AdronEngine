package com.companybest.ondra.adron.Collisions;


import com.companybest.ondra.adron.Entity.Entity;
import com.companybest.ondra.adron.OpenGl.CompobebtsHelp;
import com.companybest.ondra.adron.OpenGl.EntityComponents;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

public class CollisionSystem extends Entity {

    private static final int DEF_CELL_W = 1; // Default cellW
    private static final int DEF_CELL_H = 1; // Default cellH

    private int cellW;                       // Width of a cell on the cell grid.
    private int cellH;                       // Height of a cell on the cell grid.

    private ArrayList<HitBox> hitBoxes;      // All of the hit boxes in this system.
    private Entity parent;

    /**
     * Create a new collision system.
     */
    public CollisionSystem() {
        init();
    }

    /**
     * Create a new collision system.
     */
    public CollisionSystem(Entity parent) {

        this.parent = parent;
        init();
    }

    private void init() {
        hitBoxes = new ArrayList<HitBox>();
        cellW = DEF_CELL_W;
        cellH = DEF_CELL_H;
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public void showBoxes(boolean show) {
        if (parent != null) {
           /* QuadRenderSystem renderSystem = parent.getRoom().getQuadRenderSystem(new Graphic());
            renderSystem.setLayerColor(2, 1, 0, 0, 0.5f);

            if (show) {
                for (final HitBox h : hitBoxes) {
                    final GraphicAreaTransformation graphic = new AnimatedGraphicAreaTransform();

                    Quad q = new Quad() {
                        @Override
                        public Transformation getTransformation() {
                            return h.c.getBoxTransformation();
                        }

                        @Override
                        public GraphicAreaTransformation getGraphicAreaTransformation() {
                            return graphic;
                        }
                    };

                    renderSystem.addQuad(q);
                }
            } else {
                renderSystem.removeAllQuads();
            }*/
        }
    }

    /**
     * Set the dimensions of a single cell on the cell grid. Larger cells means
     * larger hit boxes. The smallest value is 1 which will cause cells to be
     * one pixel. Default value is 1.
     *
     * @param cellW Width of a cell.
     * @param cellH Height of a cell.
     */
    public void setCellDim(int cellW, int cellH) {
        if (cellW < 1) cellW = 1;
        if (cellH < 1) cellH = 1;
        this.cellW = cellW;
        this.cellH = cellH;
    }

    /**
     * Add each CollisionBox component of Entity e to this system.
     * @param e an Entity with CollisionBox components.
     */
    public void addEntity(CollisionBox e) {
        if (e instanceof CollisionBox) {
            addCollidable((CollisionBox) e);
        }

    }

    /**
     * Add a new CollisionBox to this collision system.
     * @param c The CollisionBox to add to this collision system.
     */
    public void addCollidable(CollisionBox c) {
        hitBoxes.add(new HitBox(c));
    }



    @Override
    public void draw(GL10 gl10) {
        for (int i = 0; i < hitBoxes.size(); i++) {           // Update all the hit boxes.
            hitBoxes.get(i).update();
        }

        for (int i = 0; i < hitBoxes.size(); i++) {           // Find all the CollisionHandlers.
            HitBox h1 = hitBoxes.get(i);

            if (h1.c.getCollisionHandler() != null) {         // Is this hit box for a CollisionHandler?
                for (int j = 0; j < hitBoxes.size(); j++) {   // Compare hit box h1 to all other hit boxes.
                    HitBox h2 = hitBoxes.get(j);

                    if (h1.c != h2.c) {                       // Make sure it isn't the same CollisionBox!
                        //boolean collided = (((h2.y <= h1.y + h2.h) && (h2.y >= h1.y - h1.h)) && ((h2.x >= h1.x - h2.w) && (h2.x <= h1.x + h1.w)));
                        boolean collided = (h1.x < h2.x + h2.w) && (h1.x + h1.w > h2.x) && (h1.y < h2.y + h2.h) && (h1.y + h1.h > h2.y);
                        if (collided) {
                            h1.c.getCollisionHandler().onCollision(h2.c);
                        }
                    }
                }
            }
        }
    }

    /**
     * Generates a CollisionBox object (collision box, hit box) using a parent Transformation and a box defined by two points within the bounds
     * of the parent Transformation. The left edge of the parent Transformation will 0 on the x axis, while the right edge will be 1. The
     * bottom of the parent Transformation will be 0 on the y axis and the top will be 1. Therefor, a box defined by points (0,0) and (1,1)
     * will be the full bounds of the parent Transformation.
     *
     * @param x1 The left edge of the collision box
     * @param y1 The bottom edge of the collision box
     * @param x2 The right edge of the collision box
     * @param y2 The top edge of the collision box
     * @param owner The Entity that this collision box belongs to.
     * @param parent The parent Transformation that this box is based on.
     * @param collisionHandler An optional CollisionHandler to handle collision events with this box. Can be null.
     *
     * @return A new CollisionBox.
     */
    public static CollisionBox generateCollisionBox(final double x1, final double y1, final double x2, final double y2, final Entity owner, final EntityComponents parent, final CollisionHandler collisionHandler) {
        return new CollisionBox() {
            CompobebtsHelp t;

            {
                t = new CompobebtsHelp();
                t.x = (float) ((((x1 + x2) / 2) - .5) * parent.getWidth());
                t.y = (float) ((((1-y1 + 1-y2) / 2) - .5) * parent.getHeight());
                t.width = (float) ((x2 - x1) * parent.getWidth());
                t.height = (float) ((y2 - y1) * parent.getHeight());
            }

            @Override public CompobebtsHelp getBoxTransformation() {
                double pw = parent.getWidth();
                double ph = parent.getHeight();

                t.x = (float) ((((x1 + x2) / 2) - .5) * pw);
                t.y = (float) ((((1-y1 + 1-y2) / 2) - .5) * ph);
                t.width = (float) ((x2 - x1) * pw);
                t.height = (float) ((y2 - y1) * ph);
                return t;
            }

            @Override public CollisionHandler getCollisionHandler() {
                return collisionHandler;
            }

            @Override public Entity getEntity() {
                return owner;
            }
        };
    }

    /**
     * Generates a new CollisionBox using the provided Transformation for the bounds.
     *
     * @param transformation A Transformation defining the bounds of the CollisionBox
     * @param collisionHandler A CollisionHandler, optional.
     * @param owner The Entity that this box belongs to.
     *
     * @return A new CollisionBox
     */
    public static CollisionBox generateCollisionBox(final CompobebtsHelp transformation, final CollisionHandler collisionHandler, final Entity owner) {
        return new CollisionBox() {
            @Override public CompobebtsHelp getBoxTransformation() {
                return transformation;
            }

            @Override public CollisionHandler getCollisionHandler() {
                return collisionHandler;
            }

            @Override public Entity getEntity() {
                return owner;
            }
        };
    }

 /*   public static boolean checkPosition(CollisionBox c, double x, double y) {
        Sprite t = c.getBoxTransformation();

        int x1 = (int) (Transform.getRealX(t) - Math.abs(t.getWidth()) * Transform.getRealScale(t) / 2);
        int y1 = (int) (Transform.getRealY(t) + Math.abs(t.getHeight()) * Transform.getRealScale(t) / 2);

        int x2 = (int) (Transform.getRealX(t) + Math.abs(t.getWidth()) * Transform.getRealScale(t) / 2);
        int y2 = (int) (Transform.getRealY(t) - Math.abs(t.getHeight()) * Transform.getRealScale(t) / 2);

        return x >= x1 && x <= x2 && y <= y1 && y >= y2;
    }*/

    /**
     * An instance of this class is generated for each CollisionBox in this system each frame.
     * The information held in the class is used to determine if two Collidables have collided
     * with each other.
     */
    private class HitBox {
        CollisionBox c;    // The collidable from which this hit box was generated.

        int x; // The x position on the cell grid of the upper left corner of this box.
        int y; // The y position on the cell grid of the upper left corner of this box.
        float w; // The width in cells on the cell grid of this box.
        float h; // The width in cells on the cell grid of this box.

        /**
         * Generates a new hit box.
         * @param c
         */
        public HitBox(CollisionBox c) {
            this.c = c;
        }

        /**
         * Refreshes this hit box to reflect changes in c's position, dimensions, and scale.
         */
        public void update() {
            EntityComponents t = c.getBoxTransformation();

 /*           int x1 = (int) (CompobebtsHelp.getRealX(t) - Math.abs(t.getWidth()) * CompobebtsHelp.getRealScale(t) / 2) / cellW;
            int y1 = (int) (CompobebtsHelp.getRealY(t) + Math.abs(t.getHeight()) * CompobebtsHelp.getRealScale(t) / 2) / cellH;

            int x2 = (int) (CompobebtsHelp.getRealX(t) + Math.abs(t.getWidth()) * CompobebtsHelp.getRealScale(t) / 2) / cellW;
            int y2 = (int) (CompobebtsHelp.getRealY(t) - Math.abs(t.getHeight()) * CompobebtsHelp.getRealScale(t) / 2) / cellH;


            int x1 = (int) (t.getX() - Math.abs(t.getWidth()) * t.getScale() / 2) / cellW;
            int y1 = (int) (t.getY() + Math.abs(t.getHeight()) * t.getScale() / 2) / cellH;

            int x2 = (int) (t.getX() + Math.abs(t.getWidth()) * t.getScale() / 2) / cellW;
            int y2 = (int) (t.getY() - Math.abs(t.getHeight()) *t.getScale() / 2) / cellH;


            x = x1;
            y = y1;
            w = x2 - x1;
            h = y1 - y2;
*/
            x = (int) t.getX();
            y = (int) t.getY();
            w = t.getWidth();
            h = t.getHeight();
        }
    }
}
