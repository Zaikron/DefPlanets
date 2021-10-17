package com.paces.game.planets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Aster {

    private static final int WD = Gdx.app.getGraphics().getWidth();
    private static final int HG = Gdx.app.getGraphics().getHeight();

    public SpriteBatch batch;
    private ParticleEffect cometa;
    private ParticleEffect meteoro;

    private ParticleEffect star1;

    int x = (int)(Math.random() * WD) + 1;
    int y = (int)(Math.random() * HG) + 1;

    public Aster(){

        batch =new SpriteBatch();
        cometa = new ParticleEffect();
        meteoro = new ParticleEffect();
        star1 = new ParticleEffect();
        setAster(cometa, x, y);
        setMeteoro(meteoro, x, y);
        setStar(star1, x, y);

    }

    private void setAster(ParticleEffect aster, int x, int y) {
        //Establecer particulas, imagen de particula y iniciacion de las particulas
        aster.load(Gdx.files.internal("Particulas/cometa.p"), Gdx.files.internal("Particulas"));
        aster.getEmitters().first().setPosition(x, y);
        aster.start();
    }

    private void setMeteoro(ParticleEffect aster, int x, int y) {
        //Establecer particulas, imagen de particula y iniciacion de las particulas
        aster.load(Gdx.files.internal("Particulas/meteoro.p"), Gdx.files.internal("Particulas"));
        aster.getEmitters().first().setPosition(x, y);
        aster.start();
    }

    private void setStar(ParticleEffect estrellas, int x, int y){
        //Establecer particulas, imagen de particula y iniciacion de las particulas
        estrellas.load(Gdx.files.internal("Particulas/star.p"), Gdx.files.internal("Particulas"));
        estrellas.getEmitters().first().setPosition(x, y);
        estrellas.start();
    }

    public void renderAsters(float dt){

        updateAsters(cometa, dt);
        batch.begin();
            drawAsters(cometa);
        batch.end();
    }

    public void renderStar(float dt){
        updateAsters(star1, dt);

        batch.begin();
        //Dibujando las particulas
        //En esa funcion tambien se comprueba si la animacion de particulas a terminado
        drawAsters(star1);
        batch.end();
    }

    public void renderMeteoro(float dt){
        updateAsters(meteoro, dt);
        batch.begin();
        drawAsters(meteoro);
        batch.end();
    }

    private void updateAsters(ParticleEffect aster, float dt){
        aster.update(dt);
    }


    private void drawAsters(ParticleEffect aster){
        aster.draw(batch);
        if(aster.isComplete()){
            aster.reset();//Si ya se completo, comenzar de nuevo la animacion
            resetPosition(aster);//Y reestablecer posicion de las particulas
        }
    }

    //Se reestablecen las posiciones aleatoriamente en la pantalla
    private void resetPosition(ParticleEffect aster){

        x = (int)(Math.random() * WD) + 1;
        y = (int)(Math.random() * HG) + 1;

        aster.getEmitters().first().setPosition(x, y);
    }
}
