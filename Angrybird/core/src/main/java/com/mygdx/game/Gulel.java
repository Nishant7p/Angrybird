package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

public class Gulel {
    private Texture texture;
    private float x, y;

    public Gulel(String texturePath, float x, float y) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
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
}
