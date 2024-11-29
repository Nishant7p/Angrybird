package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {

    private GameMain game;
    private Menu menu;
    private int screen_status=7;
    private Stage stage;
    private Skin skin;


    public MenuScreen(GameMain game) {
        this.game=game;
        screen_status++;
        menu=new Menu(game);
      //  menuSound.play();
    }

    @Override
    public void show() {

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        screen_status--;

        menu.showMenu(stage, skin);
    }

    @Override
    public void render(float delta) {


        menu.render_menu(game.batch);
        screen_status++;
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
