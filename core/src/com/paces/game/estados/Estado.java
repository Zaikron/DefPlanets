package com.paces.game.estados;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class Estado {

    //Señalizar que es lo que queremos ver en cierto momento
    protected OrthographicCamera camara;
    protected PerspectiveCamera camera;
    //Para saber donde se dio click o donde esta posicionado el raton
    protected Vector3 mouse;
    //
    protected GameStateManager gsm;

    protected Estado(GameStateManager gameStateManager){

        this.gsm = gameStateManager;
        camara = new OrthographicCamera();
        mouse = new Vector3();

    }

    protected abstract void handleInput();//Deteccion de entradas

    //Ciclo del juego, determinar estados, cargarlos y actualizarlos
    public abstract void update(float dt);//Actualizar juego en tiempo real. Todas sus subclases contienen el metodo update, cuando se utiliza
    // alguna se sobrescribe el metodo. La actualizacion constante se lleva a cabo en la clase PruebaJuego

    public abstract void render(SpriteBatch spriteBatch);//Cargar todos los elementos necesarios

    public abstract void desaparecer();


}
