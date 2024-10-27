package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Block {
    private Texture texture;
    private TextureRegion textureRegion;
    private float x, y;
    private float height;
    private float width;
    private boolean vertical;


    public Block(String texturePath, float x, float y, boolean vertical,float height,float width) {
        texture = new Texture(texturePath);
        textureRegion = new TextureRegion(texture);
        this.x = x;
        this.y = y;
        this.vertical = vertical;
        this.height=height;
        this.width=width;
    }

    public TextureRegion getTexture() {
        return textureRegion;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isVertical() {
        return vertical;
    }

    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }

    public void dispose() {
        texture.dispose();
    }
}
