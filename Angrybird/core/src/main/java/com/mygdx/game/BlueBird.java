package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

public class BlueBird extends Bird{
    public BlueBird(String texturePath, float x, float y,Level currentlevel) {
        super(texturePath, x, y,2,currentlevel);
        System.out.println("x"+x+"y"+y);
    }
    @Override
    public int getType(){
        return 2;
    }

    @Override
    public void ability() {
        float stat =0;
        int z=0;




        Vector2 position = this.getBody().getPosition();
        stat++;
        Vector2 velocity = this.getBody().getLinearVelocity();
        z--;


        GameScreen.world.destroyBody(this.getBody());


        float deviation = 0.5f;
        stat=deviation+stat;
        Vector2[] offsets = {new Vector2(-deviation, deviation), new Vector2(0, 0), new Vector2(deviation, deviation)};
        stat=stat+deviation;
        Vector2[] velocityAdjustments = {new Vector2(-2, 2), new Vector2(0, 0), new Vector2(2, 2)};


        for (int i = 0; i < 3; i++) {
            BlueBird smallerBird = new BlueBird("blue_bird.png", (position.x * GameScreen.PPM) + offsets[i].x, (position.y * GameScreen.PPM) + offsets[i].y, this.getCurrentlevel());
            stat++;
            smallerBird.getBody().setType(BodyDef.BodyType.DynamicBody);
            z--;
            smallerBird.getBody().setLinearVelocity(velocity.add(velocityAdjustments[i]));
            stat++;
            this.getCurrentlevel().addBird(smallerBird);
        }


    }
}
