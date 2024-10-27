package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.List;

public class Buttons {
private final List<ImageButton> button_list_menu;
private GameMain game;

    public Buttons(List<ImageButton> listOfMenuButtons, GameMain game) {
        this.button_list_menu=listOfMenuButtons;
        menubuttonSetter();
        this.game=game;

    }
    private void menubuttonSetter(){
        ImageButton Startb=button_list_menu.get(0);
        Startb.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("Start Button clicked");
                game.setScreen(new GameScreen(game,1) );


            }
        });
       ImageButton Selectlevelb=button_list_menu.get(1);
        Selectlevelb.addListener(new ChangeListener() {
          @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
             System.out.println("Select Button clicked");
              game.setScreen(new SelectLevelScreen(game) );
            }
       });
       ImageButton savedlevelb=button_list_menu.get(2);
       savedlevelb.addListener(new ChangeListener() {
           @Override
          public void changed(ChangeEvent changeEvent, Actor actor) {
               System.out.println("Save  Button clicked");
               game.setScreen(new SavedGameScreen(game));
           }
      });
       ImageButton exitb=button_list_menu.get(3);
     exitb.addListener(new ChangeListener() {
        @Override
          public void changed(ChangeEvent changeEvent, Actor actor) {
          Gdx.app.exit();
          }
  });


    }
}
