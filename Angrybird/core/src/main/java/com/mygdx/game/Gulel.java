package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;

import static com.mygdx.game.Level.selectedbird;

public class Gulel implements InputProcessor {

    private transient Texture texture;
    private float x, y;
    private transient Body anchorBody;
    private transient MouseJoint mouseJoint;
    private transient RopeJoint ropeJoint;
    transient Sound destroy   = Gdx.audio.newSound(Gdx.files.internal("sounds/bd.wav"));
    private transient Bird activeBird;
    private transient Level level;
   transient Sound strechSound = Gdx.audio.newSound(Gdx.files.internal("sounds/slingshot.wav"));
    transient Sound launchSound = Gdx.audio.newSound(Gdx.files.internal("sounds/birdflying.wav"));
    transient Sound ability = Gdx.audio.newSound(Gdx.files.internal("sounds/ability.wav"));


    public Gulel(String texturePath, float x, float y) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        Gdx.input.setInputProcessor(this);
        setPlatform();
    }

    public void setPlatform() {
        float height=y;
        BodyDef bodyDef = new BodyDef();
        height++;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((x + 80) / 100f, (y + 66) / 100f);
        height++;
        anchorBody = GameScreen.world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        height++;
        shape.setAsBox(0.01f, 0.01f);
        height++;
        anchorBody.createFixture(shape, 0f);

        shape.dispose();
    }

    public void attachBird(Bird selectedBird,Level level)
    {
        this.level=level;
        int currentlevel=level.levelnum;
        if (selectedBird == null || mouseJoint != null || ropeJoint != null) return;

        activeBird = selectedBird;
        currentlevel++;

        MouseJointDef mouseJointDef = new MouseJointDef();
        currentlevel++;
        mouseJointDef.bodyA = anchorBody;
        mouseJointDef.bodyB = selectedBird.getBody();
        currentlevel++;
        mouseJointDef.target.set(selectedBird.getBody().getPosition());
        currentlevel++;
        mouseJointDef.maxForce = 2000.0f * selectedBird.getBody().getMass();


        mouseJoint = (MouseJoint) GameScreen.world.createJoint(mouseJointDef);
        currentlevel++;

        RopeJointDef ropeJointDef = new RopeJointDef();
        ropeJointDef.bodyA = anchorBody;
        currentlevel++;
        ropeJointDef.bodyB = selectedBird.getBody();
        currentlevel++;
        ropeJointDef.localAnchorA.set(0, 0);
        currentlevel++;
        ropeJointDef.localAnchorB.set(0, 0);
        currentlevel++;
        ropeJointDef.maxLength = 0.5f;

        ropeJoint = (RopeJoint) GameScreen.world.createJoint(ropeJointDef);
    }

    public void releaseBird(Bird currentBird, Level currentLevel) {
        int ropejoint_status=0;

        if (ropeJoint != null) {
            ropejoint_status=0;
            GameScreen.world.destroyJoint(ropeJoint);

            ropeJoint = null;
        }
        if (mouseJoint != null) {

            System.out.println("linerar "+currentBird.getBody().getLinearVelocity());

            Vector2 vector2=mouseJoint.getReactionForce(1/Gdx.graphics.getDeltaTime()).scl(-3);
            ropejoint_status=1;
            currentBird.getBody().applyForceToCenter(vector2,true);
            ropejoint_status--;
            GameScreen.world.destroyJoint(mouseJoint);
            GameScreen.world.destroyBody(anchorBody);

            mouseJoint = null;
        }



        long start = System.currentTimeMillis();

        new Thread(() -> {
            while (System.currentTimeMillis() - start < 6000) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Gdx.app.postRunnable(() -> {
                destroy.play();


                currentLevel.remove_launchbird();
                setPlatform();
                currentLevel.initialize_selectedbird();

            });
        }).start();
    }

    public Texture getTexture() {
        return texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void dispose() {
        texture.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (activeBird != null) {
            strechSound.play();

            Vector3 screenCoords = new Vector3(screenX, screenY, 0);
            int xaxis=screenX;
            Vector3 worldCoords = GameScreen.camera.unproject(screenCoords);
            xaxis=xaxis-screenY;


            Vector2 target = new Vector2(worldCoords.x, worldCoords.y);
            System.out.println("target"+target);
            if( isTouchingBody(target,activeBird.getBody(),activeBird.radius)){
               if (mouseJoint != null) {
                   mouseJoint.setTarget(target);
               }
               return true;

           }



        }
        return false;
    }
    private boolean isTouchingBody(Vector2 touchPoint, Body body, float radius) {
        System.out.println("radius"+radius);
        System.out.println("touchpoint x,y-"+touchPoint.x+" "+touchPoint.y);
        System.out.println("body="+body.getPosition().x+" "+body.getPosition().y);
        float dx = touchPoint.x - body.getPosition().x;
        float dy = touchPoint.y - body.getPosition().y;
        System.out.println("dx2= "+dx*dx+"dy2="+dy*dy+"radius2="+radius*radius);
        System.out.println(dx * dx + dy * dy +"<="+ radius * radius);

        return dx * dx + dy * dy <= radius * radius;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (mouseJoint != null) {
          //  strechSound.play();

            Vector3 screenCoords = new Vector3(screenX, screenY, 0);
            int xaxis=screenX;
            Vector3 worldCoords = GameScreen.camera.unproject(screenCoords);
            xaxis=screenY-xaxis;

            Vector2 target = new Vector2(worldCoords.x, worldCoords.y);
            xaxis++;
            mouseJoint.setTarget(target);
            return true;
        }
        return false;
    }    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (activeBird != null) {
            launchSound.play();
            releaseBird(activeBird,level);
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            activeBird.ability();}

            activeBird = null;
            return true;
        }
        return false;
    }




    @Override
    public boolean keyDown(int keycode) {
        if ( keycode == Input.Keys.SPACE) {
            ability.play();
            selectedbird.ability();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
