package com.paces.game.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paces.game.Main;
import com.paces.game.estados.Estado;
import com.paces.game.estados.GameStateManager;
import com.paces.game.estados.MenuPrincipal;
import com.paces.game.estados.Stars;
import com.paces.game.planets.Aster;

import java.util.ArrayList;

public class Presentacion extends Estado {

    private static final float WDLOG = Gdx.app.getGraphics().getWidth() / 2;
    private static final float HGLOG = Gdx.app.getGraphics().getHeight() / 6;
    private static final float WDLOGDEF = (Gdx.app.getGraphics().getWidth() / 2);


    FadeIn fadeIn;
    FadeOut fadeOut;

    Aster estrella1;
    Aster estrella2;
    Aster estrella3;
    Aster estrella4;
    Aster estrella5;
    Aster estrella6;
    Aster estrella7;
    Aster estrella8;
    Aster estrella9;

    private MenuPrincipal menuPrincipal;
    private SpriteBatch batch;
    private SpriteBatch batchBG;
    private Texture nNormal;
    private Texture nBug;
    int contadorTiempo = 0;

    private Texture l1;
    private Texture l2;
    private Texture l3;
    private Texture space;

    private boolean indicadorFin = false;
    private Sound bug;

    ArrayList<Aster> estrellas;

    public  Presentacion(GameStateManager gsm){
        super(gsm);

        batch = new SpriteBatch();
        batchBG = new SpriteBatch();
        nNormal = Main.assets.manager.get("MPrincipal/nomNormal.png");
        nBug = Main.assets.manager.get("MPrincipal/nomBug.png");

        l1 = Main.assets.manager.get("MPrincipal/logodef.png");
        l2 = Main.assets.manager.get("MPrincipal/logodef2.png");
        l3 = Main.assets.manager.get("MPrincipal/logodef3.png");
        space = Main.assets.manager.get("MPrincipal/space.jpg");

        menuPrincipal = new MenuPrincipal(gsm);
        fadeIn = new FadeIn(0.01f);
        fadeOut = new FadeOut(0.01f);

        estrellas = new ArrayList<Aster>();
        estrellas.add(estrella1 = new Aster());
        estrellas.add(estrella2 = new Aster());
        estrellas.add(estrella3 = new Aster());
        estrellas.add(estrella4 = new Aster());
        estrellas.add(estrella5 = new Aster());
        estrellas.add(estrella6 = new Aster());
        estrellas.add(estrella7 = new Aster());
        estrellas.add(estrella8 = new Aster());
        estrellas.add(estrella9 = new Aster());

        bug = Main.assets.manager.get("Sonidos/bug.wav");

    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20. glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //Animacion de inicio, presentacion
        batch.begin();
        if(contadorTiempo <= 100){
            batch.draw(nNormal, (Gdx.app.getGraphics().getWidth()/2) - (WDLOG / 2), (Gdx.app.getGraphics().getHeight()/2) - (HGLOG / 2), WDLOG, HGLOG);
            contadorTiempo++;
        }else if(contadorTiempo >= 100 && contadorTiempo <= 140){

            if (contadorTiempo >= 100 && contadorTiempo <= 105){
                batch.draw(nBug, (Gdx.app.getGraphics().getWidth()/2) - (WDLOG / 2), (Gdx.app.getGraphics().getHeight()/2) - (HGLOG / 2),  WDLOG, HGLOG);
            }else if(contadorTiempo >= 105 && contadorTiempo <= 115){
                batch.draw(l1, (Gdx.app.getGraphics().getWidth()/2) - (WDLOGDEF / 2), (Gdx.app.getGraphics().getHeight()/2) - (HGLOG / 2), WDLOGDEF, HGLOG);
                if(contadorTiempo == 110){
                    bug.play(0.2f);
                }
            }else if(contadorTiempo >= 115 && contadorTiempo <= 125){
                batch.draw(l2, (Gdx.app.getGraphics().getWidth()/2) - (WDLOGDEF / 2), (Gdx.app.getGraphics().getHeight()/2) - (HGLOG / 2),  WDLOGDEF, HGLOG);
            }else if(contadorTiempo >= 125 && contadorTiempo <= 135){
                batch.draw(l3, (Gdx.app.getGraphics().getWidth()/2) - (WDLOGDEF / 2), (Gdx.app.getGraphics().getHeight()/2) - (HGLOG / 2),  WDLOGDEF, HGLOG);
            }else{
                batch.draw(nBug, (Gdx.app.getGraphics().getWidth()/2) - (WDLOG / 2), (Gdx.app.getGraphics().getHeight()/2) - (HGLOG / 2),  WDLOG, HGLOG);
            }
            contadorTiempo++;
        }else if(contadorTiempo >= 140 && contadorTiempo != 211){
            batch.draw(nNormal, (Gdx.app.getGraphics().getWidth()/2) - (WDLOG / 2), (Gdx.app.getGraphics().getHeight()/2) - (HGLOG / 2),  WDLOG, HGLOG);
            contadorTiempo++;
        }else if(contadorTiempo == 211){
            indicadorFin = true;
            contadorTiempo++;
        }
        batch.end();

        for (int i = 0; i < estrellas.size(); i++){
            estrellas.get(i).renderStar(dt);
        }

        fadeOut.setFadeOut(indicadorFin, gsm, menuPrincipal);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        fadeIn.setFadeIn();
    }

    @Override
    public void desaparecer() {

    }
}
