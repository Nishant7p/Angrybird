package com.mygdx.game;

public class Glass extends Block {
    public Glass(String texturePath, float x, float y, boolean vertical, float height, float width,Level level) {
        super(texturePath, x, y, vertical, height, width,1,level);
    }
    @Override
    public int getType(){
        return 2;
    }
}
