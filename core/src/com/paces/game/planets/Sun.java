package com.paces.game.planets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.paces.game.Main;


public class Sun extends Planetas {


    public Sun(float escala, float x, float y, float z, float luzDirX, float luzDirY, float luzDirZ){

        eje = 0f;
        batch = new SpriteBatch();
        font = new BitmapFont();
        setSrings();

        textura = Main.assets.manager.get("TexturasPlanetas/sol.jpg");
        ambiente = new Environment();
        dirLuz = new DirectionalLight().set(1.8f, 1.8f, 1.8f, luzDirX, luzDirY, luzDirZ);

        ModelBuilder modelBuilder = new ModelBuilder();
        modelo = modelBuilder.createSphere(escala, escala, escala, 32, 32,new Material(TextureAttribute.createDiffuse(textura)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        instancia = new ModelInstance(modelo, x, y, z);
        instancia.transform.rotate(1f, 0f, 0f, eje);

        ambiente.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
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
        font.draw(batch, "El "+nomP, Gdx.app.getGraphics().getWidth() / 2 - ((nomP.length()+3) * 3), 50);
        font.draw(batch, "Periodo Oribital: "+pOribital, 20, Main.alto - 20);
        font.draw(batch, "Periodo de Rotacion: "+pRotacion, 20, Main.alto - 55);
        font.draw(batch, "Masa: "+masa, 20, Main.alto - 90);
        font.draw(batch, "Volumen: "+vol, 20, Main.alto - 125);
        font.draw(batch, "Gravedad: "+grv, 20, Main.alto - 160);
        font.draw(batch, "Temperatura Maxima: "+tempMax, 20, Main.alto - 195);
        font.draw(batch, "Temperatura Minima: "+tempMin, 20, Main.alto - 230);
        font.draw(batch, "Distacia del Sol: "+distSol, 20, Main.alto - 265);

        batch.end();
    }

    @Override
    public void setSrings() {
        nomP = "Sol";
        pOribital = "xxxxx";
        pRotacion = "xxxxxx";
        masa = "xxxxxx";
        vol = "xxxxxx";
        grv = "xxxx";
        tempMax = "xxxxxx";
        tempMin = "xxxxxx";
        distSol = "xxxxxxxx";

        font.setColor(cl);
    }
}
