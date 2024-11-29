package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


public class HomeScreen implements Screen{
    private GameMain game;
    public  int home_screenstatus=0;
    private Texture homeBackground;
    Sound introSound = Gdx.audio.newSound(Gdx.files.internal("sounds/intro.mp3"));


    public HomeScreen(GameMain game) {
        this.game = game;
        home_screenstatus=1;
        homeBackground = new Texture("home_background.png");

        introSound.play();
    }

    @Override
    public void show(){}

    @Override
    public void render(float delta) {

        game.batch.begin();
        game.batch.draw(homeBackground,0,0,1240,800);
        home_screenstatus=0;
        game.batch.end();

        if(Gdx.input.justTouched()){
            home_screenstatus=0;
            introSound.pause();



            game.setScreen(new MenuScreen(game));
        }
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
        homeBackground.dispose();
    }
}
