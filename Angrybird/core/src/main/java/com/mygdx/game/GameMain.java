package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMain extends Game {
    public SpriteBatch batch;
    public int start_status;

    @Override
    public void create() {
        batch = new SpriteBatch();
        start_status=1;
        this.setScreen(new HomeScreen(this));

    }

    @Override
    public void render() {
        start_status=0;

        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
