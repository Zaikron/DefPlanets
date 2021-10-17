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

public class Jupiter extends Planetas {

    private float zPlaneta = 0f;
    public Jupiter(float escala, float x, float y, float z, float luzDirX, float luzDirY, float luzDirZ){

        animacionInfo = new AnimacionInfo(xInfo, yInfo, wInfo, hInfo, 10);
        info = Main.assets.manager.get("Planetas_Info/Jupiter.png");
        this.zPlaneta = z;
        eje = 3f;
        batch = new SpriteBatch();
        setSrings();

        textura = Main.assets.manager.get("TexturasPlanetas/jupiter.jpg");
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
        nomP = "Jupiter";
        pOribital = "12 años";
        pRotacion = "9 h 55 m 30 s";
        masa = "1.898 × 10^27 kg, 318 tierras";
        vol = "1,4313 × 10^15 km^3, 1317 tierras";
        grv = "24.79 m/s^2";
        tempMax = "-75,15 °C";
        tempMin = "-163,15 C";
        distSol = "778.5 millones km";

    }

    public float getZPlaneta() {
        return zPlaneta;
    }
}
