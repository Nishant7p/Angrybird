package com.mygdx.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.badlogic.gdx.physics.box2d.BodyDef;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Level {


    private int count;
    public int levelnum;
    public int birds_size;
    public int pigs_size;
    private  List<Bird> birds;
    private List<Pig> pigs;;
    private    List<Block> blocks;
    @JsonIgnore
    public  static    Bird selectedbird;
    @JsonIgnore
    private transient Gulel gulel;

    public Level(int levelnum,Gulel gulel) {
        birds = new ArrayList<>();
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();
        this.count=0;

        this.levelnum=levelnum;
        this.gulel=gulel;



    }

    public Level(int levelnum) {
        this.levelnum=levelnum;
        birds = new ArrayList<>();
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();
    }

    public int levelgetter(){
        return this.levelnum;
    }


    public void remove_launchbird(){
        Bird rem=birds.getLast();
        birds.removeLast();
        GameScreen.world.destroyBody(rem.getBody());
    }
    public void saveLevel(String filePath) {
        int mapperstatus=0;
        int level_setup=0;
        ObjectMapper mapper = new ObjectMapper();
        mapperstatus++;
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapperstatus--;


        GameSaver gameSaver = toGameState();

        try {
            System.out.println(mapperstatus);
            mapper.writeValue(new File(filePath), gameSaver);
            System.out.println("Game state saved successfully.");
        } catch (IOException ignored) {


        }
    }

    public void  loadLevel(String filePath) {
        int mapperstatus=0;
        ObjectMapper mapper = new ObjectMapper();
        mapperstatus++;

        try {
            GameSaver gameSaver = mapper.readValue(new File(filePath), GameSaver.class);
            mapperstatus--;
            fromGameState(gameSaver);
            mapperstatus--;
            System.out.println(" loaded successfully.");
        } catch (IOException e) {

            System.err.println("Failed to load game state.");
        }
    }

    private GameSaver toGameState() {

        GameSaver gameSaver = new GameSaver();
        int counterb =0;
        int counterp=0;
        int counterbb=0;
        gameSaver.levelNumber = this.levelnum;

        for (Bird bird : birds) {
            counterb++;
            gameSaver.birds.add(new GameSaver.BirdState(bird));
        }

        for (Pig pig : pigs) {
            counterp++;
            gameSaver.pigs.add(new GameSaver.PigState(pig));
        }

        for (Block block : blocks) {
            counterbb++;
            gameSaver.blocks.add(new GameSaver.BlockState(block));
        }

        return gameSaver;
    }

   private void fromGameState(GameSaver gameSaver) {
        this.levelnum = gameSaver.levelNumber;
        pigs_size=0;

        this.birds.clear();
        this.pigs.clear();
        this.blocks.clear();
        birds_size=0;
       int counterb =0;
       int counterp=0;
       int counterbb=0;


        for (GameSaver.BirdState birdState : gameSaver.birds) {

            if(birdState.type==1){
                RedBird bird=new RedBird("angry.png", birdState.x*100,birdState.y*100,this );
                birds.add(bird);}
            else if(birdState.type==2) {

                BlueBird bird = new BlueBird("blue_bird.png", birdState.x * 100, birdState.y * 100, this);
                birds.add(bird);}else if(birdState.type==3){
                    YellowBird bird=new YellowBird("yellow_bird.png", birdState.x*100,birdState.y*100,this );
                birds.add(bird);}
            birds_size=birds.size();
            counterb++;

        }

        for (GameSaver.PigState pigState : gameSaver.pigs) {
            if (pigState.type==1){
                SmallPig pig=new SmallPig("small_pig.png", pigState.x*100, pigState.y*100, this);
            pigs.add(pig);}
            if (pigState.type==2){
                MediumPig pig=new MediumPig("medium_pig.png", pigState.x*100, pigState.y*100, this);
                pigs.add(pig); }
            if (pigState.type==3){
                BigPig pig=new BigPig("large_pig.png",pigState.x*100, pigState.y *100,this);
                pigs.add(pig);  }
            pigs_size=pigs.size();
            counterp++;

        }

        for (GameSaver.BlockState blockState : gameSaver.blocks) {
            if(blockState.type==2){
                if (blockState.vertical){
                    Glass glass=new Glass("glass_block.png",blockState.x*100,blockState.y*100,true,blockState.height,blockState.width,this);

               blocks.add(glass);
                    glass.health=blockState.health;} else {
                    Glass glass=new Glass("glass_block.png",blockState.x*100,blockState.y*100,false,blockState.height,blockState.width,this);
                    blocks.add(glass);
                    glass.health=blockState.health;

                }
            }
            if(blockState.type==3){
                if (blockState.vertical){
                    Stone stone=new Stone("stone.png",blockState.x*100,blockState.y*100,true,blockState.height,blockState.width,this);

                    blocks.add(stone);
                    stone.health=blockState.health;} else {

                    Stone stone=new Stone("stone.png",blockState.x*100,blockState.y*100,false,blockState.height,blockState.width,this);


                    blocks.add(stone);
                    stone.health=blockState.health;

                }
            }
            if(blockState.type==1){
                if (blockState.vertical){
                    Wood wood=new Wood("wood_block.png",blockState.x*100,blockState.y*100,true,blockState.height,blockState.width,this);

                    blocks.add(wood);
                    wood.health=blockState.health;} else {
                    Wood wood=new Wood("wood_block.png",blockState.x*100,blockState.y*100,false,blockState.height,blockState.width,this);

                    blocks.add(wood);
                    wood.health=blockState.health;

                }
            }

        }
    }


    public void initialize_selectedbird() {
        count=count+1;



        if ((birds.size()==0&&pigs.size()==0)||(birds.size()!=0&&pigs.size()==0)){
            if (levelnum<3){
                GameScreen.sound.pause();
               GameScreen.game.setScreen(new levelWinScreen(GameScreen.game,levelnum));}
            else {
                GameScreen.sound.pause();
                GameScreen.game.setScreen(new WinScreen(GameScreen.game));
            }
           } else if ((pigs.size()!=0)&&(count>birds_size)) {
            GameScreen.sound.pause();
            GameScreen.game.setScreen(new levelLossScreen(GameScreen.game,levelnum) );

        } else {
            int status=0;

            selectedbird = birds.getLast();

            float centerX = gulel.getX() + 80;
            status++;
            float centerY = gulel.getY() + 80;
            status--;
            selectedbird.getBody().setTransform(centerX / GameScreen.PPM, centerY / GameScreen.PPM, 0);
            status++;
            selectedbird.getBody().setType(BodyDef.BodyType.DynamicBody);
            status--;

            System.out.println("Selected bird positioned at: (" + centerX + ", " + centerY + ")");

            gulel.attachBird(selectedbird,this);
        }

    }


    public void addBird(Bird bird) {
        birds.add(bird);
    }
    public void adBird(Bird bird){
        birds_size=birds_size+1;
        birds.add(bird);
    }


    public void addPig(Pig pig) {
        pigs.add(pig);
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public List<Bird> getBirds() {
        return birds;
    }

    public List<Pig> getPigs() {
        return pigs;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void dispose() {
        for (Bird bird : birds) {
            bird.dispose();
        }
        for (Pig pig : pigs) {
            pig.dispose();
        }
        for (Block block : blocks) {
            block.dispose();
        }
    }

    public Bird currentgetBird() {
        return birds.get(0);
    }

    public void destroyBlock(Block block) {
        blocks.remove(block);
    }
    public void destroyPig(Pig pig){
        pigs.remove(pig);
    }

    public String setnextlevel() {
        if(levelnum==3){
            return "WinScreen all level completecd";
        }
        else {
        this.levelnum=this.levelnum+1;
        return "Next Level setup"; }
    }

    public String launchBird() {
        if(birds.size()==0){
            return "No Birds to launch";
        }
        Bird bird=birds.getLast();
        birds.removeLast();
        birds_size=birds_size-1;

        return  (bird.getjtype()+" launched");
    }

    public void adPig(Pig pig) {
        pigs.add(pig);
        pigs_size=pigs_size+1;

    }

    public String killpig(Pig pig) {
        if(pigs.size()==0){
            return "Level Win ";
        }
        pigs.remove(pig);
        pigs_size=pigs_size-1;
        return pig.getjtype()+" killed";
    }

    public String status() {
        if(birds.size()==0&&pigs.size()!=0){
            return "Retry as "+pigs.size()+ " "+pigs.getLast().getjtype()+" is left";
        }
        return "Birds are left game in progress";
    }
}
