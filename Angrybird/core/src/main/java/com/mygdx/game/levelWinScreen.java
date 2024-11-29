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

public class levelWinScreen  implements Screen {
    GameMain game;
    Stage stage=new Stage();
    int screen_status;
    int currentlevel;
    Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clicker.mp3"));
    Texture win_bg=new Texture("win.png");
    private Texture nextbuttontexture=new Texture("nextlevel.png");
    int buttoncaller=0;
    Texture mainmenutexture=new Texture("mainmenu.png");
    Sound WinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/wins.mp3"));




    public levelWinScreen(GameMain game,int currentlevel) {
        WinSound.play();
        this.game=game;
        screen_status=8;
        this.currentlevel=currentlevel;

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        ImageButton main_but=new ImageButton(new TextureRegionDrawable(mainmenutexture));
        screen_status--;
        ImageButton next_but=new ImageButton(new TextureRegionDrawable(nextbuttontexture));
        screen_status--;
        next_but.setPosition(300,400);
        screen_status++;
        main_but.setPosition(300,300);

        screen_status++;
        next_but.setSize(150,130);

        main_but.setSize(150,130);
        stage.addActor(next_but);
        next_but.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                clickSound.play();
                WinSound.pause();
                if (currentlevel==3){
                    buttoncaller++;

                    game.setScreen(new WinScreen(game));
                }
                else {
                    buttoncaller++;
                game.setScreen(new GameScreen(game,currentlevel+1,0));}
            }
        });
        buttoncaller--;
        stage.addActor(main_but);
        main_but.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                clickSound.play();
                WinSound.pause();
                buttoncaller++;
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
