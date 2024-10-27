package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

public class GameScreen implements Screen {
    private GameMain game;
    private Texture gameBackground;
    public static ArrayList<Level> savedlevels=new ArrayList<>();
    private Texture foreground;
    private Texture nextbuttontexture=new Texture("nextlevel.png");
    private Texture pausebuttontexture=new Texture("pause.png");
    //private Texture dummywintexture=new Texture("winlevel");
   // private Texture dummylosstexture=new Texture("loss");
    private Level currentLevel;
    private Gulel catapult;
    private Stage stage;
    private Skin skin;
    private ImageButton nextButton;
    private int currentLevelNumber;
    private final int maxLevels = 3;
    private final int base_cordinate_y=150;

    public GameScreen(GameMain game,int levelNumber) {

        this.game = game;
        this.currentLevelNumber = levelNumber;
        setupLevel();

    }
    private void setupLevel(){
        gameBackground = new Texture("level_bg.png");
        //foreground=new Texture("ground.png");
        loadLevel(this.currentLevelNumber);
        catapult = new Gulel("gulel.png", 50,base_cordinate_y);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        nextButton = new ImageButton(new TextureRegionDrawable(nextbuttontexture));

        nextButton.setSize(200, 50);
        nextButton.setPosition(1000, 650);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentLevelNumber<maxLevels) {
                    game.setScreen(new GameScreen(game, currentLevelNumber + 1));
                } else if (currentLevelNumber==maxLevels) {
                    game.setScreen(new WinScreen(game) );

                }
            }
        });
        stage.addActor(nextButton);
        ImageButton pauseButton = new ImageButton(new TextureRegionDrawable(pausebuttontexture));
        pauseButton.setPosition(500,650);
        pauseButton.setSize(200, 50);
        pauseButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new PauseScreen(game,new GameScreen(game,currentLevelNumber),currentLevel));
            }
        });
        stage.addActor(pauseButton);
        Label.LabelStyle labelStyle=new Label.LabelStyle();
        labelStyle.fontColor= Color.GOLD;
        labelStyle.font=skin.getFont("default-font");

        Label level_shower=new Label("Level : "+currentLevelNumber+" / 3", labelStyle);
        level_shower.setSize(250,110);
        level_shower.setPosition(550,550);
        stage.addActor(level_shower);

        TextButton dummy_win=new TextButton("win ?",skin);
        TextButton dummy_loss =new TextButton("loss ?",skin);

        dummy_win.setPosition(100,650);
        dummy_loss.setPosition(200,650);

        stage.addActor(dummy_win);
        stage.addActor(dummy_loss);
        dummy_win.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new levelWinScreen(game,currentLevelNumber));


            }
        });
        dummy_loss.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                game.setScreen(new levelLossScreen(game,currentLevelNumber));
            }
        });




    }








    private void loadLevel(int levelNumber) {

        if (levelNumber==1) {
            currentLevel = new Level(1);
            currentLevel.addBird(new Bird("angry.png", 30,base_cordinate_y));
            currentLevel.addBird(new Bird("angry.png", 90,base_cordinate_y));
            currentLevel.addBird(new Bird("angry.png", 110, 210));

            currentLevel.addPig(new Pig("small_pig.png", 1020, 370));
            currentLevel.addPig(new Pig("small_pig.png", 870, 320));

            currentLevel.addBlock(new Block("wood_block.png", 1050,base_cordinate_y, true,200,20));
            currentLevel.addBlock(new Block("wood_block.png", 900, base_cordinate_y,true,150,20));

            currentLevel.addBlock(new Block("wood_block.png", 990, 350,false,20,100));
            currentLevel.addBlock(new Block("wood_block.png", 840, 300,false,20,100));
        } else if (levelNumber == 2) {
            currentLevel = new Level(2);
            currentLevel.addBird(new Bird("angry.png", 90, base_cordinate_y));
            currentLevel.addBird(new Bird("blue_bird.png", 30, base_cordinate_y));
            currentLevel.addBird(new Bird("blue_bird.png", 110, 210));

            currentLevel.addPig(new Pig("small_pig.png", 925,base_cordinate_y+167));
            currentLevel.addPig(new Pig("medium_pig.png", 925, base_cordinate_y));

            currentLevel.addBlock(new Block("wood_block.png", 900, base_cordinate_y, true, 150, 20));
            currentLevel.addBlock(new Block("wood_block.png", 1000, base_cordinate_y, true, 150, 20));
            currentLevel.addBlock(new Block("wood_block.png", 860, base_cordinate_y + 150, false, 20, 170));
            currentLevel.addBlock(new Block("wood_block.png", 1020, base_cordinate_y, true, 150, 20));
            currentLevel.addBlock(new Block("wood_block.png", 880, base_cordinate_y, true, 150, 20));
            currentLevel.addBlock(new Block("wood_block.png", 870, base_cordinate_y+270, false, 20, 145));
            currentLevel.addBlock(new Block("glass_block.png", 1010, base_cordinate_y + 167, true, 100, 20));
            currentLevel.addBlock(new Block("glass_block.png", 890, base_cordinate_y + 167, true, 100, 20));
        } else if (levelNumber == 3) {
            currentLevel = new Level(3);
            currentLevel.addBird(new Bird("yellow_bird.png", 30,base_cordinate_y));
            currentLevel.addBird(new Bird("blue_bird.png", 90,base_cordinate_y));
            currentLevel.addBird(new Bird("yellow_bird.png", 110, 210));

            currentLevel.addPig(new Pig("small_pig.png", 945,base_cordinate_y+220+130+3));
            currentLevel.addPig(new Pig("medium_pig.png", 1025, base_cordinate_y));
            currentLevel.addPig(new Pig("large_pig.png", 925,base_cordinate_y));
            currentLevel.addPig(new Pig("medium_pig.png", 825, base_cordinate_y));

            currentLevel.addBlock(new Block("wood_block.png", 900, base_cordinate_y, true, 150, 20));
            currentLevel.addBlock(new Block("wood_block.png", 1000, base_cordinate_y, true, 150, 20));
            currentLevel.addBlock(new Block("wood_block.png", 800, base_cordinate_y, true, 200, 20));
            currentLevel.addBlock(new Block("wood_block.png", 1100, base_cordinate_y, true, 200, 20));
            currentLevel.addBlock(new Block("glass_block.png", 880, base_cordinate_y+152, false, 20, 122));
            currentLevel.addBlock(new Block("glass_block.png", 780, base_cordinate_y+203, false, 20, 323));
            currentLevel.addBlock(new Block("glass_block.png", 980, base_cordinate_y+200+20, true, 130, 20));


            //currentLevel.addBlock(new Block("wood_block.png", 550, 180));
            //currentLevel.addBlock(new Block("wood_block.png", 570, 230, true));
            //currentLevel.addBlock(new Block("wood_block.png", 600, 200));
            //currentLevel.addBlock(new Block("wood_block.png", 650, 250, true));
        } /*else if (levelNumber == 4) {
            currentLevel.addBird(new Bird("angry.png", 130, 150));
            currentLevel.addBird(new Bird("blue_bird.png", 150, 150));
            currentLevel.addPig(new Pig("small_pig.png", 680, 120));
            currentLevel.addBlock(new Block("wood_block.png", 580, 190));
            currentLevel.addBlock(new Block("wood_block.png", 600, 240, true));
            currentLevel.addBlock(new Block("wood_block.png", 630, 210));
            currentLevel.addBlock(new Block("wood_block.png", 650, 260, true));
        } else if (levelNumber == 5) {
            currentLevel.addBird(new Bird("angry.png", 150, 150));
            currentLevel.addBird(new Bird("yellow_biel.addBlock(new Block("wood_block.png", 620, 230, true));
            currentLevel.addBlock(new Block("wood_block.png", 65rd.png", 170, 150));
            currentLevel.addPig(new Pig("small_pig.png", 700, 120));
            currentLevel.addBlock(new Block("wood_block.png", 600, 180));
            currentLev0, 200));
            currentLevel.addBlock(new Block("wood_block.png", 670, 250, true));
        }*/
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(gameBackground, 0, 0, 1300, 750);
        //game.batch.draw(foreground,-2,-4,1350,160);
        for (Bird bird : currentLevel.getBirds()) {
            game.batch.draw(bird.getTexture(), bird.getX(), bird.getY(), 40, 40);
        }
        for (Pig pig : currentLevel.getPigs()) {
            game.batch.draw(pig.getTexture(), pig.getX(), pig.getY(), 50, 50);
        }
        for (Block block : currentLevel.getBlocks()) {
                if (block.isVertical()) {
                    game.batch.draw(block.getTexture(),block.getX(),block.getY(),0,3, block.getHeight(), block.getWidth(), 1, 1,90);
                } else {
                    game.batch.draw(block.getTexture(), block.getX(), block.getY(),0,3, block.getWidth(),block.getHeight(),1,1,0);
                }


        }
        game.batch.draw(catapult.getTexture(), catapult.getX(), catapult.getY(), 150, 100);
        game.batch.end();
        stage.act();
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
        gameBackground.dispose();
        currentLevel.dispose();
        catapult.dispose();
        stage.dispose();
    }
}
