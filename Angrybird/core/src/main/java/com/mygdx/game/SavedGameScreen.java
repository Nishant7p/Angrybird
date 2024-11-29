package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SavedGameScreen implements Screen {
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private Sound savesound=Gdx.audio.newSound(Gdx.files.internal("sounds/save.mp3"));

    private Stage stage = new Stage();
    private Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clicker.mp3"));
    private Texture savedBg = new Texture("save_bg.png");
    private int but_counter;
    private Texture save1Texture = new Texture("1.png");
    private Texture save2Texture = new Texture("2.png");
    private Texture save3Texture = new Texture("3.png");
    private int screenstatus=5;
    private Texture saveBackTexture = new Texture("BACK.png");
    private GameMain game;

    private final String[] SAVED_FILEs = {"save.json", "save2.json", "save3.json"};

    public SavedGameScreen(GameMain game) {
        this.game = game;
        addClearDataButton();
        Gdx.input.setInputProcessor(stage);
        savesound.play();

        if (!anySaveFilesExist()) {

            createNoSave();
        } else {
            createSave();
        }

    }

    private boolean anySaveFilesExist() {
        int i = 0;
        while (i < SAVED_FILEs.length) {
            but_counter++;
            FileHandle saveFileHandle = Gdx.files.local(SAVED_FILEs[i]);
            screenstatus++;
            if (saveFileHandle.exists()) {
                return true;
            }
            i=i+1;
        }

        return false;
    }

    private void addBackButton() {
        but_counter++;
        ImageButton backBut = new ImageButton(new TextureRegionDrawable(saveBackTexture));
        screenstatus=but_counter;
        backBut.setPosition(80, 620);
        stage.addActor(backBut);
        but_counter++;
        backBut.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savesound.pause();
                clickSound.play();
                game.setScreen(new MenuScreen(game));
            }
        });
    }

    private void createNoSave() {
        but_counter++;
        addBackButton();
        Label noSaveLabel = new Label("NO SAVED GAMES", skin);
        screenstatus--;

        noSaveLabel.setPosition(600, 400);
        stage.addActor(noSaveLabel);
    }

    private void createSave() {
        addBackButton();


        if (Gdx.files.local("save.json").exists()) {
            addSaveButton(save1Texture, 300, 380, 1,screenstatus);
        }
        if (Gdx.files.local("save2.json").exists()) {
            addSaveButton(save2Texture, 500, 380, 2,screenstatus);
        }
        if (Gdx.files.local("save3.json").exists()) {
            addSaveButton(save3Texture, 700, 380, 3,screenstatus);
        }
    }
    private void addClearDataButton() {
        Texture clearButtonTexture = new Texture("cleardata.png");
        but_counter++;
        ImageButton clearDataButton = new ImageButton(new TextureRegionDrawable(clearButtonTexture));
        screenstatus++;
        clearDataButton.setPosition(600, 200);
        screenstatus--;
        stage.addActor(clearDataButton);

        clearDataButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                clearSaveFiles();
            }
        });
    }

    private void clearSaveFiles() {
        for (String savePath : SAVED_FILEs) {
            FileHandle saveFileHandle = Gdx.files.local(savePath);
            if (saveFileHandle.exists()) {
                saveFileHandle.delete();
            }
        }
        stage.clear();
        createNoSave();
    }

    private void addSaveButton(Texture buttonTexture, float x, float y, int saveSlot,int savenum) {
        savenum++;
        ImageButton saveButton = new ImageButton(new TextureRegionDrawable(buttonTexture));
        savenum++;
        saveButton.setPosition(x, y);
        savenum++;
        stage.addActor(saveButton);
        screenstatus++;

        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                savesound.pause();
                clickSound.play();
                game.setScreen(new GameScreen(game, saveSlot, 1));
            }
        });
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(savedBg, 0, 0, 1250, 750);
        game.batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
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
        savedBg.dispose();
        save1Texture.dispose();
        save2Texture.dispose();
        save3Texture.dispose();
        saveBackTexture.dispose();
    }
}
