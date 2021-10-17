package com.paces.game.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.paces.game.Main;

public class Luna extends Lunas{

    float dir = 0;

    public Luna(float escala, float x, float y, float z, float luzDirX, float luzDirY, float luzDirZ){

        textura = Main.assets.manager.get("TexturasLunas/moon.jpg");
        ambiente = new Environment();
        dirLuz = new DirectionalLight().set(1.8f, 1.8f, 1.8f, luzDirX, luzDirY, luzDirZ);

        ModelBuilder modelBuilder = new ModelBuilder();
        modelo = modelBuilder.createSphere(escala, escala, escala, 32, 32, new Material(TextureAttribute.createDiffuse(textura)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        instancia = new ModelInstance(modelo, x, y, z);

        ambiente.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.05f, 0.05f, 0.05f, 0.5f));
        ambiente.add(dirLuz);
        instancia.transform.rotate(0f,1f, 0f, 180f);

    }


    public void movLuna(float rotateX, float rotateY, float rotateZ, float degRotate, float translateX, float translateY, float tranlateZ){


        instancia.transform.rotate(rotateX,rotateY,rotateZ,degRotate);
        instancia.transform.translate(translateX, translateY,tranlateZ);

    }

    @Override
    public void animacionL() {

        Vector3 escalaFinal = new Vector3(1f ,1f, 1f);

        if(xA <= escalaFinal.x){
            xA = xA + 0.01f;
            yA = yA + 0.01f;
            zA = zA + 0.01f;
            instancia.transform.setToScaling(xA, yA, zA);
            instancia.transform.translate(-8,0,0);
            instancia.transform.rotate(0f,1f, 0f, 180f);
        }
    }

}
