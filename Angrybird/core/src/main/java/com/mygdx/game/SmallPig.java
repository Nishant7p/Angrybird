package com.mygdx.game;

public class SmallPig extends Pig{
    public SmallPig(String texturePath, float x, float y,Level level) {
        super(texturePath, x, y, 1,level);
        System.out.println("big pig build");
        System.out.println("x"+x+"y"+y);
    }
    @Override
    public int getType(){
        return 1;
    }


    public void damagepig(){
        killpig();

    }
}
