package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Stone extends Block {
    Texture texture_broken=new Texture("brokenstone.png");

    public Stone(String texturePath, float x, float y, boolean vertical, float height, float width,Level level) {
        super(texturePath, x, y, vertical, height, width, 3,level);

    }
    @Override
    public int getType(){
        return 3;
    }
    @Override
    public void reduceHealth(int dealeddamage) {
        System.out.println("this is wood");
        health=health-dealeddamage;

        if (health<=0){
            destroyblock();
        }
        else {
            change_texture(texture_broken);
        }

    }
}
