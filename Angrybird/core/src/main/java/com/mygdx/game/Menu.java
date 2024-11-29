package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
    int button_caller;
    private GameMain game;
    private Texture playbuttontexture=new Texture(Gdx.files.internal("Start.png"));
    private  Texture selectbuttontexture=new Texture(Gdx.files.internal("SelectLevel.png"));
    private  Texture savebuttontexture=new Texture(Gdx.files.internal("SavedLevel.png"));
    private  Texture exitbuttontexture=new Texture(Gdx.files.internal("Exit.png"));
    //public static Sound WinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu.mp3"));


    public Menu(GameMain game) {
        bg_menu = new Texture("menu_background.png");

        list_of_menu_buttons=new ArrayList<>();
        button_caller=0;
        menuState = true;
        this.game=game;
       // WinSound.play();

    }


    public void showMenu(Stage stage, Skin skin) {
        int row_num=0;
        Table table = new Table();
        int coulmn_num=0;
        table.setFillParent(true);
        button_caller++;
        stage.addActor(table);
        table.bottom();




coulmn_num++;
        ImageButton playButton= new ImageButton(new TextureRegionDrawable(playbuttontexture));
        row_num++;
        playButton.getImageCell().width(250).height(70);
        button_caller++;





        ImageButton SelectLevelButton= new ImageButton(new TextureRegionDrawable(selectbuttontexture));
        coulmn_num++;
        SelectLevelButton.getImageCell().width(200).height(50);
        row_num++;




        ImageButton RestoreButton= new ImageButton(new TextureRegionDrawable(savebuttontexture));
        coulmn_num++;
        RestoreButton.getImageCell().width(200).height(50);
        row_num++;



        ImageButton exitButton= new ImageButton(new TextureRegionDrawable(exitbuttontexture));
        coulmn_num++;
        exitButton.getImageCell().width(200).height(50);
        row_num++;




        list_of_menu_buttons.add(playButton);
        button_caller++;
     list_of_menu_buttons.add(SelectLevelButton);
     button_caller++;
     list_of_menu_buttons.add(RestoreButton);
     button_caller++;
     list_of_menu_buttons.add(exitButton);
     button_caller++;
        float buttonWidth = 300f;
        row_num= (int) buttonWidth;
        float buttonHeight = 60f;
        coulmn_num=(int) buttonHeight;
        float buttonPadding = 20f;
        Buttons buttons=new Buttons(list_of_menu_buttons,game);





        table.add(playButton).width(250f).height(70f).pad(buttonPadding).colspan(3);
        row_num++;
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


}
