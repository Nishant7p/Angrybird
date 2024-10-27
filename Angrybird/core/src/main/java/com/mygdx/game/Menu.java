package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.List;
import java.util.ArrayList;

public class Menu {
    private Texture bg_menu;
    private List<ImageButton> list_of_menu_buttons;
    private boolean menuState;
    private GameMain game;
    private Texture playbuttontexture=new Texture(Gdx.files.internal("Start.png"));
    private  Texture selectbuttontexture=new Texture(Gdx.files.internal("SelectLevel.png"));
    private  Texture savebuttontexture=new Texture(Gdx.files.internal("SavedLevel.png"));
    private  Texture exitbuttontexture=new Texture(Gdx.files.internal("Exit.png"));


    public Menu(GameMain game) {
        bg_menu = new Texture("menu_background.png");
        list_of_menu_buttons=new ArrayList<>();
        menuState = true;
        this.game=game;

    }


    public void showMenu(Stage stage, Skin skin) {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.bottom();





        ImageButton playButton= new ImageButton(new TextureRegionDrawable(playbuttontexture));
        playButton.getImageCell().width(250).height(70);





        ImageButton SelectLevelButton= new ImageButton(new TextureRegionDrawable(selectbuttontexture));
        SelectLevelButton.getImageCell().width(200).height(50);




        ImageButton RestoreButton= new ImageButton(new TextureRegionDrawable(savebuttontexture));
        RestoreButton.getImageCell().width(200).height(50);



        ImageButton exitButton= new ImageButton(new TextureRegionDrawable(exitbuttontexture));
        exitButton.getImageCell().width(200).height(50);




        list_of_menu_buttons.add(playButton);
     list_of_menu_buttons.add(SelectLevelButton);
     list_of_menu_buttons.add(RestoreButton);
     list_of_menu_buttons.add(exitButton);
        float buttonWidth = 300f;
        float buttonHeight = 60f;
        float buttonPadding = 20f;
        Buttons buttons=new Buttons(list_of_menu_buttons,game);





        table.add(playButton).width(250f).height(70f).pad(buttonPadding).colspan(3);
        table.row();
        table.add(SelectLevelButton).width(buttonWidth).height(buttonHeight).pad(buttonPadding);
        table.add();

        table.add(RestoreButton).width(buttonWidth).height(buttonHeight).pad(buttonPadding);
        table.add();
        table.row();
        table.add(exitButton).width(buttonWidth).height(buttonHeight).pad(buttonPadding).colspan(3);
    }



    public void render_menu(SpriteBatch batch) {
        batch.begin();
        batch.draw(bg_menu, 0, 0,1250,760);
        batch.end();
    }

    public void hideMenu() {
        menuState = false;
    }
}
