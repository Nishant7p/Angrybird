package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class YellowBird extends Bird{
    public YellowBird(String texturePath, float x, float y,Level level) {
        super(texturePath, x, y, 1,level);
        System.out.println("x"+x+"y"+y);


    }
    @Override
    public int getType(){
        return 3;
    }
    @Override
    public void ability(){
        int stat =0;
        int z=0;
        setTexture("yellowability.png");
        this.setDamage();
        stat++;
        Vector2 pos=this.getBody().getLinearVelocity();
        z--;
        this.getBody().setLinearVelocity(pos.x+10, pos.y);

    }
}
