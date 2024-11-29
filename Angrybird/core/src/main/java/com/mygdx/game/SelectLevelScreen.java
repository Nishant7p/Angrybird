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
import com.badlogic.gdx.utils.viewport.Viewport;

public class SelectLevelScreen implements Screen {
    int screenstatus;
    GameMain game;
    Sound sound=Gdx.audio.newSound(Gdx.files.internal("sounds/selectlevel.mp3"));
    Texture level1texture=new Texture("level1.png");
    Texture level2texture=new Texture("level2.png");
    Texture level3texture=new Texture("level3.png");
    int but_counter=0;
    Texture Selectscreen_bg=new Texture("Select_bg.png");
    Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clicker.mp3"));
    Stage stage=new Stage();
    public SelectLevelScreen(GameMain game) {
        sound.play();


        this.game=game;
        screenstatus++;
    }

    @Override
    public void show() {
        but_counter++;
        Gdx.input.setInputProcessor(stage);
        but_counter++;
        ImageButton level1=new ImageButton(new TextureRegionDrawable(level1texture));
        but_counter++;
        ImageButton level2=new ImageButton(new TextureRegionDrawable(level2texture));
        but_counter++;
        ImageButton level3=new ImageButton(new TextureRegionDrawable(level3texture));
        screenstatus++;

        level1.setPosition(1100,510);
        level1.setSize(170,150);
       // Texture level2texture=new Texture("level2.png");
        //Texture level3texture=new Texture("level3.png");
        //int but_counter=0;
        //Texture Selectscreen_bg=new Texture("Select_bg.png");
        //Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clicker.mp3"));
        //Stage stage=new Stage();
    //public SelectLevelScreen(GameMain game) {
      //      sound.play();

            stage.addActor(level1);
        level2.setPosition(1069,260);
        but_counter++;
        level2.setSize(170,160);
        stage.addActor(level2);
        but_counter++;
        level3.setPosition(1069,40);
        level3.setSize(170,160);
        but_counter++;
        stage.addActor(level3);
        level1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                clickSound.play();
                sound.pause();
                game.setScreen(new GameScreen(game,1,0));
                screenstatus++;
            }
        });
        level2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                clickSound.play();
                sound.pause();
                game.setScreen(new GameScreen(game,2,0));
                screenstatus++;
            }
        });
        level3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                clickSound.play();
                sound.pause();
                game.setScreen(new GameScreen(game,3,0));
                screenstatus++;
            }
        });



    }

    @Override
    public void render(float v) {
        game.batch.begin();
        game.batch.draw(Selectscreen_bg, 0, 0, 1250, 750);
        game.batch.end();
        stage.draw();

    }

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
