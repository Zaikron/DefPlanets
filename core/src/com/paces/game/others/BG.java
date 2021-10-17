package com.paces.game.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class BG {

    public Model bg;
    public ModelInstance bgI;
    public Texture fondo;

    public BG(float prof, float alto, float ancho, float posX){

        fondo = new Texture(Gdx.files.internal("TexturasLunas/bg.jpg"));

        ModelBuilder modelBuilder = new ModelBuilder();
        bg = modelBuilder.createBox(prof, alto, ancho, new Material(TextureAttribute.createDiffuse(fondo)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        bgI = new ModelInstance(bg, posX, 0f, 0f);
    }
}
