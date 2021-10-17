package com.paces.game.others;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;

public abstract class Lunas {

    public ModelInstance instancia;
    public Model modelo;
    public Texture textura;
    public Environment ambiente;
    public DirectionalLight dirLuz;
    float xA=0.4f, yA=0.4f, zA=0.4f;

    public boolean indi = false;

    public abstract void movLuna(float rotateX, float rotateY, float rotateZ, float degRotate, float translateX, float translateY, float tranlateZ);

    public abstract void animacionL();
}
