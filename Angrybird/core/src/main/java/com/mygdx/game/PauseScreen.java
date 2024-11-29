package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.io.IOException;

public class PauseScreen implements Screen {
    private Level levelcont;
    private GameMain game;
    private Screen previousScreen;
    private int gamescreen_status=8;
    private Texture pauseBackground;
    private int buttonpresss=0;
    private Stage stage=new Stage();
    Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clicker.mp3"));
    private  Texture savebuttontexture=new Texture(Gdx.files.internal("savepause.png"));
    private  Texture pausebuttontexture=new Texture(Gdx.files.internal("pausedtext.png"));
    private  Texture resumebuttontexture=new Texture(Gdx.files.internal("resume.png"));
    private  Texture returnbuttontexture=new Texture(Gdx.files.internal("returnmenu.png"));
    Sound pauseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pause.mp3"));


    public PauseScreen(GameMain game, Screen previousScreen,Level level) {
        this.game = game;
        pauseSound.play();
        gamescreen_status++;
        this.previousScreen = previousScreen;
        gamescreen_status++;
        pauseBackground = new Texture("pause_bg.png");
        this.levelcont=level;
        gamescreen_status--;
        pausemenushow();
    }
    private void pausemenushow(){

        Gdx.input.setInputProcessor(stage);
        gamescreen_status=gamescreen_status-4;
        ImageButton pausedButton= new ImageButton(new TextureRegionDrawable(pausebuttontexture));
        buttonpresss++;

        pausedButton.setSize(250,150);
        pausedButton.setPosition(600,690);
        buttonpresss++;
        stage.addActor(pausedButton);

        gamescreen_status++;



        ImageButton Save_exitButton= new ImageButton(new TextureRegionDrawable(savebuttontexture));
        buttonpresss++;

        Save_exitButton.setSize(150,100);
        Save_exitButton.setPosition(670,500);
        buttonpresss++;
        stage.addActor(Save_exitButton);
        Save_exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                clickSound.play();
                pauseSound.pause();
                GameScreen.savedlevels.add( levelcont );
                if(levelcont.levelnum==1){
                levelcont.saveLevel("save.json");}
                else if (levelcont.levelnum==2) {
                    levelcont.saveLevel("save2.json");
                } else if (levelcont.levelnum==3) {
                    levelcont.saveLevel("save3.json");

                }
                game.setScreen(new MenuScreen(game));
            }
        });
        buttonpresss++;

        ImageButton resumeButton= new ImageButton(new TextureRegionDrawable(resumebuttontexture));


        resumeButton.setSize(100,100);
        gamescreen_status++;
        resumeButton.setPosition(670,560);
        gamescreen_status++;
        stage.addActor(resumeButton);

        ImageButton returnmenu= new ImageButton(new TextureRegionDrawable(returnbuttontexture));
        buttonpresss++;

        returnmenu.setSize(180,100);
        returnmenu.setPosition(670,450);
        buttonpresss++;
        stage.addActor(returnmenu);
        returnmenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                clickSound.play();
                pauseSound.pause();


                game.setScreen(new MenuScreen(game));
            }
        });
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                clickSound.play();
                pauseSound.pause();

                game.setScreen(previousScreen);
                dispose();
            }
        });

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {





        game.batch.begin();
        gamescreen_status=8;
        game.batch.draw(pauseBackground, 400, 159,570,550);





       // if (Gdx.input.justTouched()) {
           // game.setScreen(previousScreen);
       // }
        game.batch.end();
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();

        pauseBackground.dispose();
    }
}
