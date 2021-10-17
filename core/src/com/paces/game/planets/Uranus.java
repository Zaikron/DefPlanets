package com.paces.game.planets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
import com.paces.game.others.AnimacionInfo;

public class Uranus extends Planetas {

    private float zPlaneta = 0f;
    public Uranus(float escala, float x, float y, float z, float luzDirX, float luzDirY, float luzDirZ){

        animacionInfo = new AnimacionInfo(xInfo, yInfo, wInfo, hInfo, 10);
        info = Main.assets.manager.get("Planetas_Info/Urano.png");
        this.zPlaneta = z;
        eje = 98f;
        batch = new SpriteBatch();
        setSrings();

        textura = Main.assets.manager.get("TexturasPlanetas/urano.jpg");
        ambiente = new Environment();
        dirLuz = new DirectionalLight().set(1.8f, 1.8f, 1.8f, luzDirX, luzDirY, luzDirZ);

        ModelBuilder modelBuilder = new ModelBuilder();
        modelo = modelBuilder.createSphere(escala, escala, escala, 32, 32,new Material(TextureAttribute.createDiffuse(textura)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        instancia = new ModelInstance(modelo, x, y, z);
        instancia.transform.rotate(1f, 0f, 0f, eje);

        ambiente.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.1f, 0.1f, 0.1f, 0.5f));
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
        batch.begin();
        animacionInfo.infoListener(info);

        batch.end();
    }

    @Override
    public void setSrings() {
        nomP = "Urano";
        pOribital = "84 años";
        pRotacion = "17h 14m";
        masa = "8.681 × 10^25 kg, 14.54 tierras";
        vol = "6,833×10^13 km^3, 63 tierras";
        grv = "8.87 m/s^2";
        tempMax = "-218,2 ℃";
        tempMin = "-214,2 ℃";
        distSol = "2.871 miles de millones km";
    }

    public float getZPlaneta() {
        return zPlaneta;
    }
}
