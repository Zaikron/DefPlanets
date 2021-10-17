package com.paces.game.planets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import com.paces.game.others.AnimacionInfo;

public abstract class Planetas {

    public static final int wInfo = (Gdx.app.getGraphics().getWidth() / 4) + (Gdx.app.getGraphics().getWidth() / 12) + 50;
    public static final int hInfo = (Gdx.app.getGraphics().getHeight() / 2) + (Gdx.app.getGraphics().getHeight() / 5);
    public static final int xInfo = (-Gdx.app.getGraphics().getWidth() / 2);
    public static final int yInfo = (Gdx.app.getGraphics().getHeight() / 2) - (Gdx.app.getGraphics().getHeight() / 4) + 30;

    public Model modelo;
    public ModelInstance instancia;
    public Environment ambiente;
    public DirectionalLight dirLuz;

    public SpriteBatch batch;
    public BitmapFont font;
    public BitmapFont font2;
    public String nomP, pOribital, pRotacion, masa, vol, grv, tempMax, tempMin, distSol;
    public Color cl = new Color(0, 0, 1, 0.8f);
    public Color cl2 = new Color(0, 1, 1, 0.9f);

    public Texture textura;
    public Texture info;
    public float eje;
    public float x=0.4f, y=0.4f, z=0.4f;

    AnimacionInfo animacionInfo;

    public Planetas(){

        font = new BitmapFont();
        font2 = new BitmapFont();
        font.getData().setScale(1f);
        font2.getData().setScale(1f);
        font.setColor(cl);
        font2.setColor(cl2);
    }


    public abstract void rotacion(float rotateX, float rotateY, float rotateZ, float degRotate);

    public abstract void animacion();

    public abstract void mostrarInfo();

    public abstract void setSrings();

    public AnimacionInfo getAnimacionInfo(){
        return animacionInfo;
    }


}



