package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.List;

public class Buttons {
private final List<ImageButton> button_list_menu;
private GameMain game;
public Sound menuSound = Gdx.audio.newSound(Gdx.files.internal("sounds/menu.mp3"));
 Sound clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/clicker.mp3"));


    public Buttons(List<ImageButton> listOfMenuButtons, GameMain game) {
        this.button_list_menu=listOfMenuButtons;
        menubuttonSetter();
        this.game=game;
        menuSound.play();

    }
    private void menubuttonSetter(){
        ImageButton Startb=button_list_menu.get(0);
        Startb.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("Start Button clicked");
                clickSound.play();
                //HomeScreen.menuSound.pause();
                menuSound.pause();
                //game.setScreen(new testGameScreen(game,1) );
                game.setScreen(new GameScreen(game,1,0));


            }
        });
       ImageButton Selectlevelb=button_list_menu.get(1);
        Selectlevelb.addListener(new ChangeListener() {
          @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
             System.out.println("Select Button clicked");
             clickSound.play();
              //HomeScreen.menuSound.pause();
              menuSound.pause();
              game.setScreen(new SelectLevelScreen(game) );
            }
       });
       ImageButton savedlevelb=button_list_menu.get(2);
       savedlevelb.addListener(new ChangeListener() {
           @Override
          public void changed(ChangeEvent changeEvent, Actor actor) {
               System.out.println("Save  Button clicked");
               clickSound.play();
              // HomeScreen.menuSound.pause();
               menuSound.pause();
               game.setScreen(new SavedGameScreen(game));
           }
      });
       ImageButton exitb=button_list_menu.get(3);
       // HomeScreen.menuSound.pause();
        clickSound.play();
        menuSound.pause();
     exitb.addListener(new ChangeListener() {
        @Override
          public void changed(ChangeEvent changeEvent, Actor actor) {
          Gdx.app.exit();
          }
  });


    }
}
