package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class GameSaver {
    public int levelNumber;
    public List<BirdState> birds;
    public List<PigState> pigs;
    public List<BlockState> blocks;


    public GameSaver() {
        birds = new ArrayList<>();
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();
    }

    public static class BirdState {
        public int type;
        public float x;
        public float y;




        public BirdState() {}
        public BirdState(Bird bird) {
            this.type = bird.getType();
            this.x = bird.getX();
            this.y = bird.getY();

        }
    }

    public static class PigState {
        public int type;
        public float x;
        public float y;


        public PigState(){

        }

        public PigState(Pig pig) {
            this.type = pig.getType();
            this.x = pig.getX();
            this.y = pig.getY();

        }
    }

    public static class BlockState {
        public int type;
        public float x;
        public float width;
        public float height;
        public float angle;
        public float y;
        public int health;
        public boolean vertical;


        public BlockState(){}
        public BlockState(Block block) {
            this.health=block.health;
            this.angle=block.body.getAngle();
            this.type = block.getType();
            this.height=block.getHeight();
            this.x = block.getX();
            this.width=block.getWidth();
            this.y = block.getY();

            this.vertical= block.isVertical();

        }
    }
}
