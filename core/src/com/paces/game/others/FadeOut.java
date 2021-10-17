package com.paces.game.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paces.game.estados.Estado;
import com.paces.game.estados.GameStateManager;
import com.paces.game.planets.Escenario;

public class FadeOut {

    /*Importante: Colocar este efecto en el metodo: update(dt), para que funcione correctamente*/

    float opacityView = 0.0f;
    float timeFade = 0f;//Rapides en la que se ejecutara el efecto, entra mas alto mas rapido

    public Texture fade;//Textura color negra
    public Texture charge;
    public SpriteBatch batchFade;
    public SpriteBatch batchC;


    public FadeOut(float time){

        this.timeFade = time;
        batchFade = new SpriteBatch();
        batchC = new SpriteBatch();

        fade = new Texture(Gdx.files.internal("TexturasLunas/fade.jpg"));
        charge = new Texture(Gdx.files.internal("MPrincipal/cargando.png"));
    }

    public void setFadeOut(boolean indicadorFinPantalla, GameStateManager gsm, Estado estado){

        //La opacidad de la textura negra se estara incrementando
        //El indicador sera mandado para permitir activar el efecto

        if(opacityView <= 1.0 && indicadorFinPantalla == true){

            batchFade.begin();
                batchFade.setColor(1,1,1, opacityView);
                opacityView = opacityView + timeFade;
                batchFade.draw(fade, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
            batchFade.end();
            mostrarCarga();

        }else if(opacityView >= 1.0 && indicadorFinPantalla == true){
            //Cuando se complete el efecto y el indicador este igual, pues asi se save que se dio un click al boton de cambiar de pantalla,
            //se accedera a la pantalla establecida en los parametros de la funcion
            mostrarCarga();
            gsm.establecer(estado);
        }
    }

    public void mostrarCarga(){

        batchC.begin();
        batchC.draw(charge, 0, 0, Gdx.app.getGraphics().getWidth() / 4, Gdx.app.getGraphics().getHeight() / 4);
        batchC.end();
    }


}
