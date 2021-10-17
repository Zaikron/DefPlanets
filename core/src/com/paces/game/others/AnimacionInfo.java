package com.paces.game.others;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.TextArea;

public class AnimacionInfo {
    int width = 0;
    int height = 0;
    public int x = 0;
    int y = 0;
    int rapidez = 1;
    SpriteBatch batch;

    public AnimacionInfo(int x, int y, int width, int height, int rapidez){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.rapidez = rapidez;
        batch = new SpriteBatch();
    }

    public void infoListener(Texture texture){

        batch.begin();
        if(x < -30){
            x = x + rapidez;
            batch.draw(texture, x, y, width, height);
        }else{
            batch.draw(texture, x, y, width, height);
        }
        batch.end();

    }
}
