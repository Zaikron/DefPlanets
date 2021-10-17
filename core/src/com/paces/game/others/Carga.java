package com.paces.game.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paces.game.Main;
import com.paces.game.estados.Estado;
import com.paces.game.estados.GameStateManager;
import com.paces.game.planets.Escenario;

import java.awt.Font;

public class Carga extends Estado {

    private static final float SCALEFONT = (Gdx.app.getGraphics().getWidth() / 4) / 80;

    int progress = 0;

    SpriteBatch batch;
    Texture carga;
    Presentacion presentacion;

    Texture c0;
    Texture c25;
    Texture c50;
    Texture c75;
    Texture c100;

    BitmapFont font;

    public Carga(GameStateManager gsm){
        super(gsm);

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(1);

        //Texturas de la barra de carga
        c0 = new Texture(Gdx.files.internal("MPrincipal/barra/0.png"));
        c25 = new Texture(Gdx.files.internal("MPrincipal/barra/25.png"));
        c50 = new Texture(Gdx.files.internal("MPrincipal/barra/50.png"));
        c75 = new Texture(Gdx.files.internal("MPrincipal/barra/75.png"));
        c100 = new Texture(Gdx.files.internal("MPrincipal/barra/100.png"));

    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20. glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();

        //Se comprueba el estado de carga del assetManager, la clase que carga todos los recursos
        if(Main.getManager().update()){
            Main.getManager().finishLoading();
            presentacion = new Presentacion(gsm);
            gsm.establecer(presentacion);
            //gsm.establecer(new Escenario(gsm));

        }else{
            progress = (int)(Main.getManager().getProgress() * 100) + 3;//Progreso en entero
            font.draw(batch, String.valueOf(progress) + "%",//Dibujo de los numeros del progreso
                    (Gdx.app.getGraphics().getWidth() / 2),  (Gdx.app.getGraphics().getHeight() / 12));

            //Cambio de imagenes de la barra de carga conforme al progreso de carga de los assets
            if(progress <= 25){
                batch.draw(c0, 0, 0, (Gdx.app.getGraphics().getWidth() / 2),  (Gdx.app.getGraphics().getHeight() / 8));
            }else if(progress > 25 && progress <= 50){
                batch.draw(c25, 0,0, (Gdx.app.getGraphics().getWidth() / 2),  (Gdx.app.getGraphics().getHeight() / 8));
            }else if(progress > 50 && progress <= 75){
                batch.draw(c50, 0, 0, (Gdx.app.getGraphics().getWidth() / 2),  (Gdx.app.getGraphics().getHeight() / 8));
            }else if(progress > 75 && progress < 100){
                batch.draw(c75, 0,  0, (Gdx.app.getGraphics().getWidth() / 2),  (Gdx.app.getGraphics().getHeight() / 8));
            }else if (progress == 100){
                batch.draw(c100, 0, 0, (Gdx.app.getGraphics().getWidth() / 2),  (Gdx.app.getGraphics().getHeight() / 8));
            }

        }

        batch.end();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void desaparecer() {
    }
}
