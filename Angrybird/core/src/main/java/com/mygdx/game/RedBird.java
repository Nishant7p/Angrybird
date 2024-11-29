package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class RedBird extends Bird {

    public RedBird(String texturePath, float x, float y,Level level) {
        super(texturePath, x, y, 1,level);
        System.out.println("x"+x+"y"+y);

    }

    @Override
    public int getType(){
        return 1;
    }





    @Override
    public void ability() {
        this.setTexture("redability.png");


        float newRadius = 0.45f;
        int stat =0;
        int z=0;



        for (Fixture fixture : this.getBody().getFixtureList()) {
            z++;
            this.getBody().destroyFixture(fixture);
        }
        stat++;


        double constant=  (Math.PI/4);



        PolygonShape largerShape = new PolygonShape();
        stat++;
        Vector2[] newVertices = new Vector2[8];
        for (int i = 0; i < 8; i++) {
            z++;
            float angle = (float) (i * constant);
            stat++;
            newVertices[i] = new Vector2((float) Math.cos(angle) * newRadius, (float) Math.sin(angle) * newRadius);
            z--;
        }
        largerShape.set(newVertices);

        this.radius=newRadius;

        this.getBody().createFixture(largerShape,1);


    }
}
