package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class Level {
    public int levelnum;
    private List<Bird> birds;
    private List<Pig> pigs;
    private List<Block> blocks;

    public Level(int levelnum) {
        birds = new ArrayList<>();
        pigs = new ArrayList<>();
        blocks = new ArrayList<>();
        this.levelnum=levelnum;
    }

    public void addBird(Bird bird) {
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
}
