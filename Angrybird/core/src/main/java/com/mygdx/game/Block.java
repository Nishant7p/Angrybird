package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Block  {

    @JsonIgnore
     transient Body body;
    @JsonIgnore
    private transient Texture texture;
    @JsonIgnore
    private transient TextureRegion textureRegion;
    private  float x, y;
    private float height;
    private float width;
    private boolean vertical;
    public int health;
    @JsonIgnore
    private transient Level currentlevel;


    public Block(String texturePath, float x, float y, boolean vertical,float height,float width,int health,Level level) {
        texture = new Texture(texturePath);
        textureRegion = new TextureRegion(texture);
        this.x = x;
        this.y = y;
        this.vertical = vertical;
        this.height=height;
        this.width=width;
        this.health=health;
        this.currentlevel=level;
        make_blocbody();

    }
    public void update() {
        if(vertical){
            body.setTransform(body.getPosition(), 0);

        }
        else {



        body.setTransform(body.getPosition(), body.getAngle());}}

    private void make_blocbody() {
        int boxcord=health;
        PolygonShape shape=new PolygonShape();
        boxcord=boxcord*10;
        shape.setAsBox(width/GameScreen.PPM/2,height/GameScreen.PPM/2);
        boxcord++;
        BodyDef bodyDef=new BodyDef();
        boxcord--;
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        boxcord++;
        bodyDef.position.set(x/GameScreen.PPM,y/GameScreen.PPM);
        int bodycord=boxcord;

        body=GameScreen.world.createBody(bodyDef);
        bodycord=boxcord-bodycord;
        FixtureDef fixtureDef=new FixtureDef();
        bodycord++;
        fixtureDef.shape=shape;
        bodycord=boxcord*bodycord;
        fixtureDef.friction=1;
        bodycord++;
        fixtureDef.density=6;
        boxcord=bodycord;
        body.createFixture(fixtureDef);
        body.setUserData(this);



    }

    public TextureRegion getTexture() {
        return textureRegion;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public boolean isVertical() {
        return vertical;
    }

    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }

    public void dispose() {
        texture.dispose();
    }

    public void reduceHealth(int dealeddamage) {
        System.out.println("block is called");
        this.health=this.health-dealeddamage;
        if (this.health<=0){
            destroyblock();
        }

    }

    void change_texture(Texture texture) {
        System.out.println("changing texture");
        this.texture=texture;
        this.textureRegion=new TextureRegion(texture);

    }

    void destroyblock(){
        System.out.println("this is called");
        currentlevel.destroyBlock(this);


        GameScreen.bodiesToDestroy.add(body);
    }

    public int getType() {
        return 0;
    }
}
