package com.mygdx.game;

public class MediumPig extends Pig{
    public MediumPig(String texturePath, float x, float y,Level level) {
        super(texturePath, x, y, 3,level);
        System.out.println("x"+x+"y"+y);

    }
    @Override
    public int getType(){
        return 2;
    }
    public void damagepig() {
        int Health=this.getHealth()-1;
        this.setHealth(Health);
        if (Health==1){
            this.setTexture("damagemedium.png");
        }
        if (Health==0){
            this.killpig();
        }
    }
}
