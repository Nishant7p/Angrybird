package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SelectLevelScreen implements Screen {
    GameMain game;
    Texture level1texture=new Texture("level1.png");
    Texture level2texture=new Texture("level2.png");
    Texture level3texture=new Texture("level3.png");
    Texture Selectscreen_bg=new Texture("Select_bg.png");
    Stage stage=new Stage();
    public SelectLevelScreen(GameMain game) {


        this.game=game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        ImageButton level1=new ImageButton(new TextureRegionDrawable(level1texture));
        ImageButton level2=new ImageButton(new TextureRegionDrawable(level2texture));
        ImageButton level3=new ImageButton(new TextureRegionDrawable(level3texture));

        level1.setPosition(1100,510);
        level1.setSize(170,150);
        stage.addActor(level1);
        level2.setPosition(1069,260);
        level2.setSize(170,160);
        stage.addActor(level2);
        level3.setPosition(1069,40);
        level3.setSize(170,160);
        stage.addActor(level3);
        level1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new GameScreen(game,1));
            }
        });
        level2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new GameScreen(game,2));
            }
        });
        level3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new GameScreen(game,3));
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
