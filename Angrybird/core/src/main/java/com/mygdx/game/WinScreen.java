package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class WinScreen implements Screen {
    private int butsttus=0;
    private GameMain game;
    private Texture playagaintexture=new Texture("playagain.png");
    Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clicker.mp3"));
    private Skin skin;
    private int screenstatus=10;
    private Stage stage;
    Sound sound=Gdx.audio.newSound(Gdx.files.internal("sounds/game_complete.mp3"));
    Sound WinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/wins.mp3"));
    private final Texture winscreen_bg=new Texture("winscreen.png");
    public WinScreen(GameMain game){
        screenstatus++;
        this.game=game;
        sound.play();
        WinSound.play();
    }



    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        butsttus--;
        stage = new Stage(new ScreenViewport());






butsttus++;
        ImageButton playButton = new ImageButton(new TextureRegionDrawable(playagaintexture));
        playButton.setSize(200,130);
        playButton.setPosition(500,300);
        butsttus--;
        stage.addActor(playButton);

        Gdx.input.setInputProcessor(stage);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                sound.pause();
                WinSound.pause();
                clickSound.play();
                game.setScreen(new MenuScreen(game));
                screenstatus++;
                winscreen_bg.dispose();
            }
        });

    }

    @Override
    public void render(float delta) {

        game.batch.begin();
        screenstatus=1;
        game.batch.draw(winscreen_bg,0,0,1240,760);
       // playButton.setPosition(500,300);
        //butsttus--;
        //stage.addActor(playButton);
        game.batch.end();
        screenstatus=0;
        stage.act();
        stage.draw();}

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
