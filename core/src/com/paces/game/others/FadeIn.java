package com.paces.game.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FadeIn {

    /*Importante: Colocar este efecto en el metodo: render(sp), para que funcione correctamente*/

    float fullView = 1.0f;
    float timeFade = 0f;

    public Texture fade;
    public Texture charge;
    public SpriteBatch batchFade;
    public SpriteBatch batchC;

    public FadeIn(float time){

        this.timeFade = time;
        batchFade = new SpriteBatch();
        batchC = new SpriteBatch();
        fade = new Texture(Gdx.files.internal("TexturasLunas/fade.jpg"));
        charge = new Texture(Gdx.files.internal("MPrincipal/cargando.png"));
    }

    public void setFadeIn(){

        if(fullView >= 0){
        //Se va disminullendo la opacidad de la textuta negra
            batchFade.begin();
                batchFade.setColor(1,1,1, fullView);
                fullView = fullView - timeFade;
                batchFade.draw(fade, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
            batchFade.end();
            //mostrarCarga();
        }
    }

    public void mostrarCarga(){

        batchC.begin();
        batchC.draw(charge, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        batchC.end();
    }
}
