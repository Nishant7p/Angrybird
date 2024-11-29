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

public class levelLossScreen implements Screen {
    GameMain game;
    Stage stage=new Stage();
    int screen_sttus;
    Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clicker.mp3"));
    Texture retrytexture=new Texture("retry.png");
    Texture mainmenutexture=new Texture("mainmenu.png");
    Sound lossSound = Gdx.audio.newSound(Gdx.files.internal("sounds/losssound.mp3"));

    //Texture loss_bg=new Texture("loss_bg.png");
    Texture loss_bg=new Texture("q.png");
    int currentlevel;
    public levelLossScreen(GameMain game,int currentlevel) {
        this.game=game;
        this.screen_sttus=7;
        this.currentlevel=currentlevel;

        lossSound.play();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
       ImageButton retry_but=new ImageButton(new TextureRegionDrawable(retrytexture));
       screen_sttus--;
       retry_but.setPosition(650,350);
       retry_but.setSize(200,190);
        ImageButton main_but=new ImageButton(new TextureRegionDrawable(mainmenutexture));
        screen_sttus--;
        main_but.setPosition(650,250);
        main_but.setSize(200,190);
       stage.addActor(retry_but);
       stage.addActor(main_but);
       main_but.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent changeEvent, Actor actor) {
               clickSound.play();
               lossSound.pause();
               game.setScreen(new MenuScreen(game));
           }
       });
       retry_but.addListener(new ChangeListener() {
           @Override
           public void changed(ChangeEvent changeEvent, Actor actor) {
               clickSound.play();
               lossSound.pause();
               game.setScreen(new GameScreen(game,currentlevel,0));
           }
       });

    }

    @Override
    public void render(float v) {
        game.batch.begin();

        game.batch.draw(loss_bg, 0, 0, 1350, 840);

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
