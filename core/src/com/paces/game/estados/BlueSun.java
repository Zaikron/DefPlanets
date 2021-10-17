package com.paces.game.estados;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.paces.game.Main;
import com.paces.game.planets.Planetas;

public class BlueSun extends Planetas {

    public BlueSun(float escala, float x, float y, float z, float luzDirX, float luzDirY, float luzDirZ){

        eje = 0f;
        batch = new SpriteBatch();
        font = new BitmapFont();
        setSrings();

        textura = Main.assets.manager.get("MPrincipal/texturaSolAzul.png");
        ambiente = new Environment();
        dirLuz = new DirectionalLight().set(1.8f, 1.8f, 1.8f, luzDirX, luzDirY, luzDirZ);

        ModelBuilder modelBuilder = new ModelBuilder();
        modelo = modelBuilder.createSphere(escala, escala, escala, 32, 32,new Material(TextureAttribute.createDiffuse(textura)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        instancia = new ModelInstance(modelo, x, y, z);
        instancia.transform.rotate(1f, 0f, 0f, eje);

        ambiente.set(new ColorAttribute(ColorAttribute.AmbientLight, .1f, .1f, .1f, .3f));
        ambiente.add(dirLuz);

    }

    @Override
    public void rotacion(float rotateX, float rotateY, float rotateZ, float degRotate) {
        instancia.transform.rotate(rotateX, rotateY, rotateZ, degRotate);
    }

    @Override
    public void animacion() {
        Vector3 escalaFinal = new Vector3(1f ,1f, 1f);

        if(x <= escalaFinal.x){
            x = x + 0.01f;
            y = y + 0.01f;
            z = z + 0.01f;
            instancia.transform.setToScaling(x, y, z);
            instancia.transform.rotate(1f, 0f, 0f, eje);
        }
    }

    @Override
    public void mostrarInfo() {

    }

    @Override
    public void setSrings() {

    }
}
