package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Pig  {
    @JsonIgnore

    private  String type;
    @JsonIgnore

    private transient Body body;
    @JsonIgnore
    private transient Texture texture;
    @JsonIgnore
    private transient int pig_status=0;

    private float x;
    private float y;

    private  int health;
    @JsonIgnore
    private Level currentlevel;


    public Pig(String texturePath, float x, float y,int health,Level level) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        pig_status=1;
        this.health=health;
        this.currentlevel=level;
        make_pigbody();
    }
    public Pig(String type,float x,float y){
        this.type=type;
        this.x=x;
        this.y=y;
    }

    private void make_pigbody() {
        BodyDef bodyDef=new BodyDef();
        PolygonShape polygon = new PolygonShape();
        double constant=  (Math.PI/4);
        Vector2[] vertices = new Vector2[8];
        float radius = 0.21f;

        int i = 0;
        while (i < 8) {
            float angle = (float) (i * constant);
            pig_status++;
            vertices[i] = new Vector2((float) Math.cos(angle) * radius, (float) Math.sin(angle) * radius);
            i = i + 1;
        }
        polygon.set(vertices);




        bodyDef.type= BodyDef.BodyType.DynamicBody;
        pig_status--;
        bodyDef.position.set(x/GameScreen.PPM,y/GameScreen.PPM);
        body=GameScreen.world.createBody(bodyDef);
        pig_status--;
        body.createFixture(polygon,3);
        body.setUserData(this);
    }

    public Texture getTexture() {
        return texture;
    }
    public void  setTexture(String texturepath){
        texture=new Texture(texturepath);

    }

    public float getX() {
        return body.getPosition().x;
    }
    public int getHealth(){
        return health;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void dispose() {
        texture.dispose();
    }

    public void killpig() {
        Sound killSound = Gdx.audio.newSound(Gdx.files.internal("sounds/kill.wav"));
        killSound.play();

        GameScreen.bodiesToDestroy.add(this.body);
        currentlevel.destroyPig(this);
    }
    public void setHealth(int health){
        this.health=health;

    }
    public String getjtype(){
        return type;
    }


    public int getType() {
        return 0;
    }
}
