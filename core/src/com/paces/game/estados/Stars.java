package com.paces.game.estados;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.paces.game.Main;

public class Stars {
    private static final int WD = Gdx.app.getGraphics().getWidth();
    private static final int HG = Gdx.app.getGraphics().getHeight();

    public SpriteBatch batch;
    private ParticleEffect estrellas;
    private ParticleEffect estrellas1;
    private ParticleEffect estrellas2;
    private ParticleEffect estrellas3;
    private ParticleEffect miniEstrellas1;
    private ParticleEffect miniEstrellas2;
    private ParticleEffect miniEstrellas3;
    private ParticleEffect miniEstrellas4;



    int x  = (int)(Math.random() * WD) + 1;
    int y = (int)(Math.random() * HG) + 1;


    public Stars(){

        batch = new SpriteBatch();
        estrellas = new ParticleEffect();
        estrellas1 = new ParticleEffect();
        estrellas2 = new ParticleEffect();
        estrellas3 = new ParticleEffect();
        miniEstrellas1 = new ParticleEffect();
        miniEstrellas2 = new ParticleEffect();
        miniEstrellas3 = new ParticleEffect();
        miniEstrellas4 = new ParticleEffect();



        //Colocacion de las particulas de estrellas en posiciones iniciales
        setStars(estrellas, WD / 4, HG / 4);
        setStars(estrellas1, WD / 4, HG - (HG / 4));
        setStars(estrellas2, WD - (WD / 4) , HG / 4);
        setStars(estrellas3, WD - (WD / 4), HG - (HG / 4));
        setMiniStars(miniEstrellas1, WD / 2, HG / 6);
        setMiniStars(miniEstrellas2, WD / 6, HG / 2);
        setMiniStars(miniEstrellas3, WD - (WD / 6), HG / 2);
        setMiniStars(miniEstrellas4, WD / 2, HG / 2);



    }

    public void renderStars(float dt){
        //Actualizacion de la particulas
        updateStars(estrellas, dt);
        updateStars(estrellas1, dt);
        updateStars(estrellas2, dt);
        updateStars(estrellas3, dt);
        updateStars(miniEstrellas1, dt);
        updateStars(miniEstrellas2, dt);
        updateStars(miniEstrellas3, dt);
        updateStars(miniEstrellas4, dt);


        batch.begin();
        //Dibujando las particulas
        //En esa funcion tambien se comprueba si la animacion de particulas a terminado
            drawStars(estrellas);
            drawStars(estrellas1);
            drawStars(estrellas2);
            drawStars(estrellas3);
            drawStars(miniEstrellas1);
            drawStars(miniEstrellas2);
            drawStars(miniEstrellas3);
            drawStars(miniEstrellas4);
        batch.end();
    }



    private void setStars(ParticleEffect estrellas, int x, int y){
        //Establecer particulas, imagen de particula y iniciacion de las particulas
        estrellas.load(Gdx.files.internal("Particulas/stars.p"), Gdx.files.internal("Particulas"));
        estrellas.getEmitters().first().setPosition(x, y);
        estrellas.start();
    }

    private void setMiniStars(ParticleEffect estrellas, int x, int y){
        estrellas.load(Gdx.files.internal("Particulas/miniStars.p"), Gdx.files.internal("Particulas"));
        estrellas.getEmitters().first().setPosition(x, y);
        estrellas.start();
    }

    private void updateStars(ParticleEffect estrellas, float dt){
        estrellas.update(dt);
    }

    private void drawStars(ParticleEffect estrellas){
        estrellas.draw(batch);
        if(estrellas.isComplete()){
            estrellas.reset();//Si ya se completo, comenzar de nuevo la animacion
            resetPosition(estrellas);//Y reestablecer posicion de las particulas
        }
    }

    //Se reestablecen las posiciones aleatoriamente en la pantalla
    private void resetPosition(ParticleEffect estrellas){

        x = (int)(Math.random() * WD) + 1;
        y = (int)(Math.random() * HG) + 1;

        estrellas.getEmitters().first().setPosition(x, y);
    }
}
