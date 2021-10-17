package com.paces.game.planets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.paces.game.Main;
import com.paces.game.others.AnimacionInfo;
import com.paces.game.others.Assets;
import com.paces.game.others.Luna;

public class Earth extends Planetas{

    Luna luna;

    private float zPlaneta = 0f;


    public Earth(float escala, float x, float y, float z, float luzDirX, float luzDirY, float luzDirZ){

        this.zPlaneta = z;//Utilizada para el funcionamiento de la clase Animatios7
        animacionInfo = new AnimacionInfo(xInfo, yInfo, wInfo, hInfo, 10);

        info = Main.assets.manager.get("Planetas_Info/Tierra.png");

        //Objeto de creacion de la luna
        luna = new Luna(1.9f,-8f,0f,0f, 0f, 0f, 20f);

        eje = 23.5f;
        batch = new SpriteBatch();

        setSrings();

        //Creacion de texturas y el ambiente (Luz y su direccion)
        textura = Main.assets.manager.get("TexturasPlanetas/tierra.jpg");
        ambiente = new Environment();
        dirLuz = new DirectionalLight().set(1.8f, 1.8f, 1.8f, luzDirX, luzDirY, luzDirZ);

        //Modelo de la tierra con su textura
        ModelBuilder modelBuilder = new ModelBuilder();
        modelo = modelBuilder.createSphere(escala, escala, escala, 32, 32,new Material(TextureAttribute.createDiffuse(textura)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        //Inicializacion de la instancia del modelo
        instancia = new ModelInstance(modelo, x, y, z);
        instancia.transform.rotate(1f, 0f, 0f, eje);

        ambiente.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.1f, 0.1f, 0.1f, 0.5f));
        ambiente.add(dirLuz);

    }


    public void rotacion(float rotateX, float rotateY, float rotateZ, float degRotate){

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
            /*font.draw(batch, "Planeta: ", (Gdx.app.getGraphics().getWidth() / 2) - ((nomP.length()+9) * 3), 50);
            font2.draw(batch, nomP, (Gdx.app.getGraphics().getWidth() / 2) - ((nomP.length()+9) * 3) + 54, 50);

            font.draw(batch, "Periodo Oribital: ", 20, Gdx.app.getGraphics().getHeight() - 20);
            font2.draw(batch, pOribital, 20 + 108, Gdx.app.getGraphics().getHeight() - 20);

            font.draw(batch, "Periodo de Rotacion: ", 20, Gdx.app.getGraphics().getHeight() - 55);
            font2.draw(batch, pRotacion, 20 + 147, Gdx.app.getGraphics().getHeight() - 55);

            font.draw(batch, "Masa: ", 20, Gdx.app.getGraphics().getHeight()- 90);
            font2.draw(batch, masa, 20 + 40, Gdx.app.getGraphics().getHeight()- 90);

            font.draw(batch, "Volumen: ", 20, Gdx.app.getGraphics().getHeight()- 125);
            font2.draw(batch, vol, 20 + 60, Gdx.app.getGraphics().getHeight()- 125);

            font.draw(batch, "Gravedad: ", 20, Gdx.app.getGraphics().getHeight() - 160);
            font2.draw(batch, grv, 20 + 70, Gdx.app.getGraphics().getHeight() - 160);

            font.draw(batch, "Temperatura Maxima: ", 20, Gdx.app.getGraphics().getHeight() - 195);
            font2.draw(batch, tempMax, 20 + 145, Gdx.app.getGraphics().getHeight() - 195);

            font.draw(batch, "Temperatura Minima: ", 20, Gdx.app.getGraphics().getHeight() - 230);
            font2.draw(batch, tempMin, 20 + 140, Gdx.app.getGraphics().getHeight() - 230);

            font.draw(batch, "Distacia del Sol: ", 20, Gdx.app.getGraphics().getHeight() - 265);
            font2.draw(batch, distSol, 20 + 108, Gdx.app.getGraphics().getHeight() - 265);*/

            //batch.draw(info, xInfo, yInfo, wInfo, hInfo);
            animacionInfo.infoListener(info);

        batch.end();

    }

    @Override
    public void setSrings() {
        nomP = "Tierra";
        pOribital = "365 días";
        pRotacion = "23 horas, 56 minutos";
        masa = "5.972 × 10^24 kg";
        vol = "1,08321×10^12 km^3";
        grv = "9.807 m/s^2";
        tempMax = "56,7 °C";
        tempMin = "-89,15 °C";
        distSol = "149.6 millones km";

    }


    public float getZPlaneta() {
        return zPlaneta;
    }

    public AnimacionInfo getAnimacionInfo() {
        return animacionInfo;
    }
}
