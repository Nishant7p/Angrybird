package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PauseScreen implements Screen {
    private Level levelcont;
    private GameMain game;
    private Screen previousScreen;
    private Texture pauseBackground;
    private Stage stage=new Stage();
    private  Texture savebuttontexture=new Texture(Gdx.files.internal("savepause.png"));
    private  Texture pausebuttontexture=new Texture(Gdx.files.internal("pausedtext.png"));
    private  Texture resumebuttontexture=new Texture(Gdx.files.internal("resume.png"));
    private  Texture returnbuttontexture=new Texture(Gdx.files.internal("returnmenu.png"));


    public PauseScreen(GameMain game, Screen previousScreen,Level level) {
        this.game = game;
        this.previousScreen = previousScreen;
        pauseBackground = new Texture("pause_bg.png");
        this.levelcont=level;
        pausemenushow();
    }
    private void pausemenushow(){

        Gdx.input.setInputProcessor(stage);
        ImageButton pausedButton= new ImageButton(new TextureRegionDrawable(pausebuttontexture));

        pausedButton.setSize(250,150);
        pausedButton.setPosition(600,690);
        stage.addActor(pausedButton);



        ImageButton Save_exitButton= new ImageButton(new TextureRegionDrawable(savebuttontexture));

        Save_exitButton.setSize(150,100);
        Save_exitButton.setPosition(670,500);
        stage.addActor(Save_exitButton);
        Save_exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                GameScreen.savedlevels.add( levelcont );
                game.setScreen(new MenuScreen(game));
            }
        });

        ImageButton resumeButton= new ImageButton(new TextureRegionDrawable(resumebuttontexture));

        resumeButton.setSize(100,100);
        resumeButton.setPosition(670,560);
        stage.addActor(resumeButton);

        ImageButton returnmenu= new ImageButton(new TextureRegionDrawable(returnbuttontexture));

        returnmenu.setSize(180,100);
        returnmenu.setPosition(670,450);
        stage.addActor(returnmenu);
        returnmenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
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
        game.batch.draw(pauseBackground, 400, 159,570,550);
        game.batch.end();
        stage.act(delta);
        stage.draw();





       // if (Gdx.input.justTouched()) {
           // game.setScreen(previousScreen);
       // }
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
