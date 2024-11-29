package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;

public class CollisionListener implements ContactListener {
    Sound crack = Gdx.audio.newSound(Gdx.files.internal("sounds/rock.wav"));
    Sound damage=Gdx.audio.newSound(Gdx.files.internal("sounds/pigdamage.wav"));

    @Override
    public void beginContact(Contact contact) {
        int hit=1;
        int cintact_status=1;
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        System.out.println("FixtureA  " + fixtureA.getBody().getUserData());




        if((fixtureA.getBody().getUserData() instanceof Block && fixtureB.getBody().getUserData() instanceof Bird)) {
            cintact_status=0;
            hit--;
          //  System.out.println("collision of bird and block");
            handleCollision_BB((Block) fixtureA.getBody().getUserData(), (Bird) fixtureB.getBody().getUserData());
        } else if(fixtureB.getBody().getUserData() instanceof Block && fixtureA.getBody().getUserData() instanceof Bird) {
            //System.out.println("collision of bird and block");
            cintact_status=0;
           ;
            handleCollision_BB((Block) fixtureB.getBody().getUserData(), (Bird) fixtureA.getBody().getUserData());
            hit--;}


        else if(fixtureA.getBody().getUserData() instanceof Pig && fixtureB.getBody().getUserData() instanceof Bird){
            //System.out.println("collision pig and bird");
            cintact_status=0;
            hit--;
            handleCollision_PB((Pig) fixtureA.getBody().getUserData(),(Bird) fixtureB.getBody().getUserData());

            hit--;} else if (fixtureA.getBody().getUserData() instanceof Bird && fixtureB.getBody().getUserData() instanceof Pig) {
            //System.out.println("collision pig and bird");
            cintact_status=0;
            hit--;

            handleCollision_PB((Pig) fixtureB.getBody().getUserData(),(Bird) fixtureA.getBody().getUserData());

            hit--; } else if (fixtureA.getBody().getUserData() instanceof SmallPig&& fixtureB.getBody().getUserData() ==null) {
            handleCollision_PG((SmallPig) fixtureA.getBody().getUserData());
            cintact_status=0;

            hit--;} else if (fixtureB.getBody().getUserData()instanceof SmallPig&&fixtureA.getBody().getUserData()==null){
            cintact_status=0;
            handleCollision_PG((SmallPig) fixtureB.getBody().getUserData());



            hit--; } else if (fixtureA.getBody().getUserData()instanceof MediumPig&&fixtureB.getBody().getUserData()instanceof Block) {
            cintact_status=0;
            handleCollision_BP((MediumPig) fixtureA.getBody().getUserData());


        } else if (fixtureB.getBody().getUserData()instanceof MediumPig && fixtureA.getBody().getUserData()instanceof Block) {
            cintact_status=0;
            handleCollision_BP((MediumPig) fixtureB.getBody().getUserData());
            hit--;
        }
        else if (fixtureA.getBody().getUserData()instanceof BigPig&&fixtureB.getBody().getUserData()instanceof Block) {
            cintact_status=0;
            handleCollision_BPb((BigPig) fixtureA.getBody().getUserData());
            hit--;

        } else if (fixtureB.getBody().getUserData()instanceof BigPig && fixtureA.getBody().getUserData()instanceof Block) {
            cintact_status=0;
            handleCollision_BPb((BigPig) fixtureB.getBody().getUserData());
            hit--;
    }}

    private void handleCollision_BPb(BigPig userData) {
        damage.play();
        userData.damagepig();
    }


    private void handleCollision_BP(MediumPig pig) {
        damage.play();
        pig.damagepig();



    }

    private void handleCollision_PG(SmallPig pig) {
        pig.damagepig();
    }

    private void handleCollision_PB(Pig pig,Bird bird) {
        pig.killpig();

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void handleCollision_BB(Block block, Bird bird) {
        crack.play();


        block.reduceHealth(bird.getDamage());
    }

}
