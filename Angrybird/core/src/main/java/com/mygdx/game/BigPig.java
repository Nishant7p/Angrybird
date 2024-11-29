package com.mygdx.game;

public class BigPig extends Pig{
    public BigPig(String texturePath, float x, float y,Level level) {

        super(texturePath, x, y,4,level);
        System.out.println("big pig build");
        System.out.println("x"+x+"y"+y);
    }
    @Override
    public int getType(){
        return 3;
    }
    public void damagepig() {
        int Health=this.getHealth()-1;
        this.setHealth(Health);
        if (Health==1){
            this.setTexture("damageking.png");
        }
        if (Health==0){
            this.killpig();
        }
    }
}
