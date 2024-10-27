package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class SavedGameScreen implements Screen {
   // ArrayList<Level> savedlevel;
    Skin skin=new Skin(Gdx.files.internal("uiskin.json"));
    Stage stage=new Stage();
    Texture saved_bg=new Texture("save_bg.png");
    Texture save1texture=new Texture("1.png");
    Texture savebacktexture=new Texture("BACK.png");
    Texture save2texture=new Texture("2.png");
    Texture save3texture=new Texture("3.png");
    private boolean noSaveGame;
    GameMain game;

    public SavedGameScreen(GameMain game) {
        this.game = game;
       // this.savedlevel = savedLevels;
        noSaveGame = GameScreen.savedlevels.isEmpty();

        Gdx.input.setInputProcessor(stage);

        if (noSaveGame){
            createNoSave();
        }
        else {
            createSave();
        }


    }
    private void addbackbut(){
        ImageButton back_but=new ImageButton(new TextureRegionDrawable(savebacktexture));
        back_but.setPosition(80,620);
        stage.addActor(back_but);
        back_but.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });

    }
    private void add1level(){
        ImageButton but_1=new ImageButton(new TextureRegionDrawable(save1texture));
        but_1.setPosition(300,380);
        int levelnum1=GameScreen.savedlevels.get(0).levelnum;
        stage.addActor(but_1);
        but_1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new GameScreen(game,levelnum1));
            }
        });
    }
    private void add2level(){
        ImageButton but_2 =new ImageButton(new TextureRegionDrawable(save2texture));
        but_2.setPosition(550,380);
        int levelnum2=GameScreen.savedlevels.get(1).levelnum;
        stage.addActor(but_2);
        but_2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new GameScreen(game,levelnum2));
            }
        });
    }
    private void add3level(){
        ImageButton but_3 =new ImageButton(new TextureRegionDrawable(save3texture));
        int levelnum3=GameScreen.savedlevels.get(2).levelnum;
        but_3.setPosition(800,380);
        stage.addActor(but_3);
        but_3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new GameScreen(game,levelnum3));
            }
        });
    }



    private void createNoSave(){
        addbackbut();
        Label nosavelabel=new Label("NO SAVED GAMES",skin);
        nosavelabel.setPosition(600,400);
        stage.addActor(nosavelabel);


    }
    private void createSave(){
        stage.clear();

        addbackbut();
        if (GameScreen.savedlevels.size()==1){
            add1level();

        } else if (GameScreen.savedlevels.size()==2) {
            add1level();
            add2level();

        } else if (GameScreen.savedlevels.size()==3) {
            add1level();
            add2level();
            add3level();

        }
    }





    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        game.batch.begin();
        game.batch.draw(saved_bg,0,0,1250,750);
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
        stage.dispose();
        skin.dispose();
        saved_bg.dispose();
        save1texture.dispose();
        savebacktexture.dispose();
        save2texture.dispose();
        save3texture.dispose();


    }
}



