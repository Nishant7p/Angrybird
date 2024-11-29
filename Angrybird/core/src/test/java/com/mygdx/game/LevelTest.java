package com.mygdx.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {
    Level level;

    @BeforeEach
    void setup() {
        level = new Level(1);

    }

    @Test
    void testLevelinitialized() {
        assertEquals(1, level.levelgetter());
    }

    @Test
    void testnextlevelbut() {
        level.setnextlevel();
        assertEquals(2, level.levelgetter());

        level.setnextlevel();
        assertEquals(3, level.levelgetter());
    }

    @Test
    void testAlllevelcompleted() {
        level.setnextlevel();
        level.setnextlevel();
        level.setnextlevel();

        String message = level.setnextlevel();
        assertEquals("WinScreen all level completecd", message);

    }

    @Test
    void testaddingbird() {
        level.adBird(new Bird("Red Bird", 30, 130));
        assertEquals(1, level.birds_size);

        level.adBird(new Bird("Blue Bird", 30, 180));
        assertEquals(2, level.birds_size);
    }

    @Test
    void testlaunchingBird() {
        level.adBird(new Bird("Red Bird", 30, 130));
        level.adBird(new Bird("Blue Bird", 30, 180));
        assertEquals(2,level.birds_size);


        String message = level.launchBird();
        assertEquals("Blue Bird launched", message);
        assertEquals(1,level.birds_size);

        String message1 = level.launchBird();
        assertEquals(0,level.birds_size);
        assertEquals("Red Bird launched", message1);



    }

    @Test
    void testlaunchingallbird() {

        String message2 = level.launchBird();
        assertEquals("No Birds to launch", message2);



    }
    @Test
    void testaddingpig(){
        level.adPig(new Pig("small pig",20,50));
        assertEquals(1, level.pigs_size);

        level.adPig(new Pig("Medium Pig",1500,179));
        assertEquals(2, level.pigs_size);

    }
    @Test
    void testDestroyingPig(){
        Pig small_pig=new Pig("small pig",20,50);
        Pig Medium_pig=new Pig("Medium Pig",1500,179);


        level.adPig(small_pig);
        level.adPig(Medium_pig);
        assertEquals(2,level.pigs_size);

        String message = level.killpig(small_pig);
        assertEquals("small pig killed", message);
        assertEquals(1,level.pigs_size);




    }
    @Test
    void testallpigsDestroyed(){
        Pig small_pig=new Pig("small pig",20,50);
        String message = level.killpig(small_pig);
        assertEquals("Level Win ",message);


    }
    @Test
    void testretrylogic(){
        Pig small_pig=new Pig("small pig",20,50);
        Pig Medium_pig=new Pig("Medium Pig",1500,179);
        level.adBird(new Bird("Red Bird", 30, 130));
        level.adPig(small_pig);
        level.adPig(Medium_pig);
        level.launchBird();
        level.killpig(small_pig);

        String message=level.status();
        assertEquals("Retry as 1 Medium Pig is left",message);




    }
}
