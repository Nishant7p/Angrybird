package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;



import java.util.ArrayList;
import java.util.List;


public class GameScreen implements Screen  {
    private Screen screen=this;
    public static final float PPM = 100f;
    public Vector3 worldCoords;
    public  int screen_status;

    public static List<Body> bodiesToDestroy = new ArrayList<>();
    private Vector2 draggingPosition = null;
    public static World world;
    private Bird selectedBird = null;
    private boolean isDraggingBird = false;

    public static GameMain game;
    static OrthographicCamera camera = new OrthographicCamera();
    Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clicker.mp3"));
    private Texture gameBackground;
    public static ArrayList<Level> savedlevels = new ArrayList<>();
    private Texture foreground;
    private Texture nextbuttontexture = new Texture("nextlevel.png");
    private Texture pausebuttontexture = new Texture("pause.png");
    //private Texture dummywintexture=new Texture("winlevel");
    //private Texture dummylosstexture=new Texture("loss");
    private Level currentLevel;
    public static Gulel catapult;
    private int gulel_status;
    private Stage stage;
    private Skin skin;
    private ImageButton nextButton;
    private int currentLevelNumber;
    private final int maxLevels = 3;
    private final int base_cordinate_y = 150;
    private Box2DDebugRenderer debugRenderer;
    private Body testbody;
    private Body groundbody;
    private Body currentbody;
   public static Sound sound= Gdx.audio.newSound(Gdx.files.internal("sounds/intro.mp3"));

    public GameScreen(GameMain game, int levelNumber,int save_status) {
        screen_status=1;
        sound.play();




        this.game = game;
        screen_status=1;
        this.currentLevelNumber = levelNumber;
        screen_status++;
        // world = new World(new Vector2(0, 0.0f), false);
        world=new World(new Vector2(0,-7.0f),false);
        float height=Gdx.graphics.getHeight();
        screen_status++;
        world.setContactListener(new CollisionListener());
        float width=Gdx.graphics.getWidth();
        screen_status++;

        debugRenderer = new Box2DDebugRenderer();
        screen_status++;


        camera.setToOrtho(false, width / PPM, height / PPM);

        System.out.println("Camera Position: " + camera.position);
        System.out.println("Viewport: " + camera.viewportWidth + "x" + camera.viewportHeight);

if(save_status==0){
        setupLevel();}else {
    setup_savelevel(levelNumber);
        }


    }

    private void setup_savelevel(int levelNumber) {
        setupground();
        gameBackground = new Texture("level_bg.png");
        catapult = new Gulel("gulel.png", 70, base_cordinate_y);
        currentLevel =new Level(levelNumber,catapult);
        if(levelNumber==1){
            currentLevel.loadLevel("save.json");

        } else if (levelNumber==2) {
            currentLevel.loadLevel("save2.json");

        } else if (levelNumber==3) {
            currentLevel.loadLevel("save3.json");

        }


        currentLevel.initialize_selectedbird();


        stage = new Stage();
        screen_status++;
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        gulel_status=0;
        nextButton = new ImageButton(new TextureRegionDrawable(nextbuttontexture));

        nextButton.setSize(200, 50);
        screen_status=gulel_status;
        nextButton.setPosition(1000, 650);
        gulel_status++;
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();

                if (levelNumber < maxLevels) {
                    screen_status--;
                    game.setScreen(new GameScreen(game, levelNumber + 1,0));
                } else if (levelNumber == maxLevels) {
                    screen_status--;
                    game.setScreen(new WinScreen(game));


                }
            }
        });
        stage.addActor(nextButton);
        gulel_status++;
        ImageButton pauseButton = new ImageButton(new TextureRegionDrawable(pausebuttontexture));
        pauseButton.setPosition(500, 650);
        screen_status++;
        pauseButton.setSize(200, 50);
        pauseButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                sound.pause();
                clickSound.play();
                screen_status--;
                game.setScreen(new PauseScreen(game, screen, currentLevel));
            }
        });
        stage.addActor(pauseButton);
        screen_status=gulel_status;
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        screen_status--;
        labelStyle.fontColor = Color.GOLD;
        gulel_status--;
        labelStyle.font = skin.getFont("default-font");
        screen_status++;

        Label level_shower = new Label("Level : " + currentLevelNumber + " / 3", labelStyle);

        level_shower.setSize(250, 110);
        Label label=new Label("next:"+currentLevelNumber+1,labelStyle);
        level_shower.setPosition(550, 550);
        stage.addActor(level_shower);





    }


    private void setupLevel() {
        setupground();
        gameBackground = new Texture("level_bg.png");



        catapult = new Gulel("gulel.png", 70, base_cordinate_y);
        loadLevel(this.currentLevelNumber,catapult);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        nextButton = new ImageButton(new TextureRegionDrawable(nextbuttontexture));

        nextButton.setSize(200, 50);
        nextButton.setPosition(1000, 650);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();

                if (currentLevelNumber < maxLevels) {
                    game.setScreen(new GameScreen(game, currentLevelNumber + 1,0));
                } else if (currentLevelNumber == maxLevels) {
                    game.setScreen(new WinScreen(game));


                }
            }
        });
        stage.addActor(nextButton);
        gulel_status++;
        ImageButton pauseButton = new ImageButton(new TextureRegionDrawable(pausebuttontexture));
        pauseButton.setPosition(500, 650);
        screen_status++;
        pauseButton.setSize(200, 50);
        pauseButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                sound.pause();
                clickSound.play();
                screen_status--;
                game.setScreen(new PauseScreen(game, screen, currentLevel));
            }
        });
        stage.addActor(pauseButton);
        screen_status=gulel_status;
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        screen_status--;
        labelStyle.fontColor = Color.GOLD;
        gulel_status--;
        labelStyle.font = skin.getFont("default-font");
        screen_status++;

        Label level_shower = new Label("Level : " + currentLevelNumber + " / 3", labelStyle);

        level_shower.setSize(250, 110);
        Label label=new Label("next:"+currentLevelNumber+1,labelStyle);
        level_shower.setPosition(550, 550);
        stage.addActor(level_shower);





    }

    private void setupground() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        screen_status++;
        bodyDef.position.set(650 / PPM, 75 / PPM);
        screen_status++;
        bodyDef.fixedRotation = true;
        PolygonShape polygonShape = new PolygonShape();
        int set_box=screen_status;
        polygonShape.setAsBox(650 / PPM, 75 / PPM);
        set_box++;
        groundbody = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        set_box--;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.9f;
        groundbody.createFixture(fixtureDef);


    }


    private void loadLevel(int levelNumber,Gulel gulel) {
        int add_count=0;



        if (levelNumber == 1) {
            currentLevel = new Level(1,gulel);


            currentLevel.addBird(new RedBird("angry.png", 15, base_cordinate_y + 25,currentLevel));
            add_count++;

            currentLevel.addBird(new RedBird("angry.png", 57, base_cordinate_y + 25,currentLevel));
            add_count++;
            currentLevel.addBird(new RedBird("angry.png", 110, base_cordinate_y + 20,currentLevel));
            add_count++;

            currentLevel.addPig(new SmallPig("small_pig.png", 1050, base_cordinate_y + 244,currentLevel));
            add_count++;
            currentLevel.addPig(new SmallPig("small_pig.png", 900, base_cordinate_y + 170,currentLevel));
            add_count++;

            currentLevel.addBlock(new Wood("wood_block.png", 1050, base_cordinate_y + 20, true, 200, 20,currentLevel)); // Horizontal block
            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 900, base_cordinate_y + 20, true, 150, 20,currentLevel));  // Horizontal block
            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 1050, base_cordinate_y + 213, false, 20, 120,currentLevel)); // Vertical block

            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 900, base_cordinate_y + 160, false, 20, 100,currentLevel)); // Vertical block
            add_count++;
            currentLevel.birds_size=3;
            currentLevel.pigs_size=3;
        } else if (levelNumber == 2) {
            currentLevel = new Level(2,gulel);

            currentLevel.addBird(new BlueBird("blue_bird.png", 18, base_cordinate_y+23,currentLevel));
            add_count++;
            currentLevel.addBird(new YellowBird("yellow_bird.png", 61, 172,currentLevel));
            add_count++;
            currentLevel.addBird(new RedBird("angry.png", 110, base_cordinate_y,currentLevel));
            add_count++;
            currentLevel.addPig(new SmallPig("small_pig.png", 925, base_cordinate_y + 167,currentLevel));
            add_count++;
            currentLevel.addPig(new MediumPig("medium_pig.png", 925, base_cordinate_y,currentLevel));
            add_count++;

            currentLevel.addBlock(new Wood("wood_block.png", 900, base_cordinate_y, true, 150, 20,currentLevel));
            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 1000, base_cordinate_y, true, 150, 20,currentLevel));
            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 950, base_cordinate_y + 150, false, 20, 170,currentLevel));
            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 1020, base_cordinate_y, true, 150, 20,currentLevel));
            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 880, base_cordinate_y, true, 150, 20,currentLevel));
            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 950, base_cordinate_y + 270, false, 20, 145,currentLevel));
            add_count++;
            currentLevel.addBlock(new Glass("glass_block.png", 1010, base_cordinate_y + 177, true, 100, 20,currentLevel));
            add_count++;
            currentLevel.addBlock(new Glass("glass_block.png", 890, base_cordinate_y + 177, true, 100, 20,currentLevel));
            add_count++;
            currentLevel.birds_size=3;
            currentLevel.pigs_size=3;
        } else if (levelNumber == 3) {
            currentLevel = new Level(3,gulel);


            currentLevel.addBird(new BlueBird("blue_bird.png", 19, base_cordinate_y+25,currentLevel));
            add_count++;
            currentLevel.addBird(new YellowBird("yellow_bird.png", 59, base_cordinate_y+25,currentLevel));
            add_count++;
            currentLevel.addBird(new YellowBird("yellow_bird.png", 110, 210,currentLevel));
            add_count++;
            currentLevel.addPig(new SmallPig("small_pig.png", 979, base_cordinate_y + 220 + 130 + 27+20,currentLevel));
            add_count++;
            currentLevel.addPig(new MediumPig("medium_pig.png", 1025, base_cordinate_y,currentLevel));
            add_count++;
            currentLevel.addPig(new BigPig("large_pig.png", 935, base_cordinate_y,currentLevel));
            add_count++;
            currentLevel.addPig(new MediumPig("medium_pig.png", 825, base_cordinate_y,currentLevel));
            add_count++;
            currentLevel.addBlock(new Stone("stone.png", 900, base_cordinate_y, true, 150, 20,currentLevel));
            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 1000, base_cordinate_y, true, 150, 20,currentLevel));
            add_count++;
            currentLevel.addBlock(new Stone("stone.png", 800, base_cordinate_y, true, 200, 20,currentLevel));
            add_count++;
            currentLevel.addBlock(new Wood("wood_block.png", 1100, base_cordinate_y, true, 200, 20,currentLevel));
            add_count++;
            currentLevel.addBlock(new Glass("glass_block.png", 950, base_cordinate_y + 152, false, 20, 129,currentLevel));
            add_count++;
            currentLevel.addBlock(new Glass("glass_block.png", 945, base_cordinate_y + 203, false, 20, 335,currentLevel));
            add_count++;

            currentLevel.addBlock(new Glass("glass_block.png", 980, base_cordinate_y + 200 + 20, true, 130, 29,currentLevel));

            currentLevel.addBlock(new Stone("stone.png",969,base_cordinate_y + 220 + 130 + 24,false,20,60,currentLevel));

            currentLevel.birds_size=3;
            currentLevel.pigs_size=3;
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
        currentLevel.initialize_selectedbird();
    }

    @Override
    public void show() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(catapult);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Circle boundary = catapult.getBoundary();


        game.batch.begin();
        game.batch.draw(gameBackground, 0, 0, 1300, 750);
        int width=150;

        game.batch.draw(catapult.getTexture(), catapult.getX(), catapult.getY(), width, 100);



        float radius = 0.22f * PPM;

        if (width==150){
            int start=0;
        }


        for (Bird bird : currentLevel.getBirds()) {

            if (bird instanceof YellowBird&&bird.getBody().getLinearVelocity().x<0.1){
                bird.setTexture("yellow_bird.png");


            }

            game.batch.draw(bird.getTexture(), bird.getX() * PPM - bird.radius * PPM, bird.getY() * PPM - bird.radius * PPM, bird.radius * 2 * PPM, bird.radius * 2 * PPM);

        }
        for (Pig pig : currentLevel.getPigs()) {
            game.batch.draw(pig.getTexture(), pig.getX() * PPM - radius, pig.getY() * PPM - radius, radius * 2, radius * 2);
        }
        for (Block block : currentLevel.getBlocks()) {
            if (block.isVertical()) {
                block.update();
                game.batch.draw(block.getTexture(), block.getX() * PPM - block.getHeight() / 2, block.getY() * PPM - block.getWidth() / 2, block.getHeight() / 2, block.getWidth() / 2, block.getHeight(), block.getWidth(), 1, 1, 90 + (float) Math.toDegrees(block.body.getAngle()));
            } else {
                block.update();
                game.batch.draw(block.getTexture(), block.getX() * PPM - block.getWidth() / 2, block.getY() * PPM - block.getHeight() / 2, block.getWidth() / 2, block.getHeight() / 2, block.getWidth(), block.getHeight(), 1, 1, (float) Math.toDegrees(block.body.getAngle()));
            }


        }




        game.batch.draw(catapult.getTexture(), catapult.getX(), catapult.getY(), 150, 100);
        for (Body body : bodiesToDestroy) {
            world.destroyBody(body);
        }
        bodiesToDestroy.clear();


        game.batch.end();
        debugRenderer.setDrawBodies(false);

        debugRenderer.render(world, camera.combined);
        stage.act();

        stage.draw();
        camera.update();

    }

    private void update(float deltaTime) {
        world.step(1 / 60f, 6, 2);
        camera.update();
    }

    @Override
    public void dispose() {
        gameBackground.dispose();
        currentLevel.dispose();
        catapult.dispose();
        stage.dispose();
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
}
