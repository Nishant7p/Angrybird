package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class HomeScreen implements Screen{
    private GameMain game;
    private Texture homeBackground;

    public HomeScreen(GameMain game) {
        this.game = game;
        homeBackground = new Texture("home_background.png");
    }

    @Override
    public void show(){}

    @Override
    public void render(float delta) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(homeBackground,0,0,1240,800);
        game.batch.end();

        if(Gdx.input.justTouched()){
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
