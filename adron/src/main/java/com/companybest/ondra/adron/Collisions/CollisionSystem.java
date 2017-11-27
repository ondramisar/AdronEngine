package com.companybest.ondra.adron.Collisions;


import com.companybest.ondra.adron.Entity.Entity;
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
     *
     * @param e an Entity with CollisionBox components.
     */
    public void addEntity(CollisionBox e) {
        if (e instanceof CollisionBox) {
            addCollidable((CollisionBox) e);
        }

    }

    /**
     * Add a new CollisionBox to this collision system.
     *
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
         *
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

            x = (int) t.getX();
            y = (int) t.getY();
            w = t.getWidth() - 20;
            h = t.getHeight() - 20;
        }
    }
}
