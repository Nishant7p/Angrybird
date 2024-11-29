package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Bird {


    @JsonIgnore
    public float radius;

    @JsonIgnore

    private transient Level currentlevel;
    @JsonIgnore
    private transient Body body;
    @JsonIgnore
    private transient Texture texture;
    @JsonIgnore
    private String texturepath;
    @JsonIgnore
    private String type;


    private  float x, y;
    private int damage;


    public Bird(String texturePath, float x, float y,int damage,Level currentlevel) {
        this.texturepath=texturePath;
        this.texture = new Texture(texturePath);

        this.x = x;
        this.y = y;
        this.damage=damage;
        this.radius=0.22f;
        this.currentlevel=currentlevel;
        making_body();
    }
    public Bird(String type,float x,float y){
        this.type=type;
        this.x=x;
        this.y=y;

    }
   public String getjtype(){
        return type;
    }


     void making_body() {


         PolygonShape polygon = new PolygonShape();


        Vector2[] vertices = new Vector2[8];
        int stat= (int) x;
        float radius = 0.21f;
        float z=radius;

         double constant=  (Math.PI/4);




         stat++;

         for (int i = 0; i < 8; i++) {
             z++;
             float angle = (float) (i * constant);
             stat++;
             vertices[i] = new Vector2((float) Math.cos(angle) * radius, (float) Math.sin(angle) * radius);
             z--;
         }

        polygon.set(vertices);
         stat=0;
        BodyDef def_bird=new BodyDef();
        stat++;
        def_bird.type=BodyDef.BodyType.StaticBody;
        z--;
        def_bird.position.set(x/ GameScreen.PPM,y/GameScreen.PPM);



        body=GameScreen.world.createBody(def_bird);
        FixtureDef def=new FixtureDef();
        def.density=1.0f;
        def.shape=polygon;


        def.friction=0.5f;
        body.createFixture(def);
        body.setUserData(this);
        body.setAngularDamping(1.0f);
        body.setBullet(true);





    }

    public Texture getTexture() {
        return texture;
    }


    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void dispose() {
        texture.dispose();
    }

    public Body getBody() {
      return   this.body;}

    public int getDamage() {
        return  this.damage;
    }


    public void ability() {
        System.out.println("ability is calledlllllllllllllllllllllllllllllllllllllllllllllll" );


    }
    public Level getCurrentlevel(){
        return currentlevel;
    }

    protected void setDamage() {
        this.damage=3;
    }

    protected void setTexture(String path) {
        this.texture=new Texture(path);
    }

    public int getType() {
        return 0;
    }
}
