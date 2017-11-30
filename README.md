# AdronEngine
AdronEngine is android 2d game engine. 
Right now in a activ development.

### Installing

Right now you can add Adron to your project by only downloading this project, but gradle compile version is comming soon.


## Use

### Start
To basicly start using AdronEngine your class needs to extend BasicAdrClass. BasicAdrClass is subclass of Activity so you can use all normal things in android activity

```
public class YourActivity extends BasicAdrClass{
...
}
```

### Override methods from BasicAdrClass
Here all the override methods that you can now use and explanation
```
public class YourActivity extends BasicAdrClass{

    @Override
    public void onSurfaceChanged(GL10 gl10, int pWidth, int pHeight) {
        super.onSurfaceChanged(gl10, pWidth, pHeight); // Called when OpenGl surface is changed
                                                       // Here you will most likely set up your grid height and width
    }

    @Override
    public void onSurfaceCreated(GL10 gl10) {
        super.onSurfaceCreated(gl10); // Called when OpenGl is created 
    }

    @Override
    public Scene setUpScene() {
                                      // Set ups the scene that will be rendered
                                      // Here you will create your scene from variety of components and objects
        return null;
    }

    @Override
    public void update(float dt) {  
                                      // Here you can update your components from scene
 
    }

    // Click and Touch methods
    @Override
    public void onPress(float x, float y) {
    }

    @Override
    public void onRelease(float x, float y) {

    }

    @Override
    public void onMove(float x, float y) {

    }

    @Override
    public void onPress(Entity entity) {

    }

    @Override
    public void onRelease(Entity entity) {

    }

    @Override
    public void onMove(Entity entity) {

    }

    @Override
    public void onReleaseOutside(Entity entity) {

    }
}
```

## Authors

* **Ondřej Misař ** - *Initial work* - [AdronEngine](https://github.com/ondramisar/AdronEngine)

## License

This project is licensed under the Apache 2

## Acknowledgments

