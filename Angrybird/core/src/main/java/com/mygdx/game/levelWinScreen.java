package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class levelWinScreen  implements Screen {
    GameMain game;
    Stage stage=new Stage();
    int currentlevel;
    Texture win_bg=new Texture("win.png");
    private Texture nextbuttontexture=new Texture("nextlevel.png");
    Texture mainmenutexture=new Texture("mainmenu.png");




    public levelWinScreen(GameMain game,int currentlevel) {
        this.game=game;
        this.currentlevel=currentlevel;

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        ImageButton main_but=new ImageButton(new TextureRegionDrawable(mainmenutexture));
        ImageButton next_but=new ImageButton(new TextureRegionDrawable(nextbuttontexture));
        next_but.setPosition(300,400);
        main_but.setPosition(300,300);
        next_but.setSize(150,130);
        main_but.setSize(150,130);
        stage.addActor(next_but);
        next_but.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                if (currentlevel==3){
                    game.setScreen(new WinScreen(game));
                }
                else {
                game.setScreen(new GameScreen(game,currentlevel+1));}
            }
        });
        stage.addActor(main_but);
        main_but.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });

    }

    @Override
    public void render(float v) {
        game.batch.begin();
        game.batch.draw(win_bg, 0, 0, 1250, 750);
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
