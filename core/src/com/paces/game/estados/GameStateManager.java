package com.paces.game.estados;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<Estado> estadosPila;

    public GameStateManager(){
        estadosPila = new Stack<Estado>();
    }

    public void insertar(Estado estado){//Insertar un elemento en la pila de estados
        estadosPila.push(estado);
    }

    public void eliminarUltimo(){//eliminar ultimo estado de la pila
        estadosPila.pop().desaparecer();
    }

    public void establecer(Estado estado){//Ingresar pero reemplazando
        estadosPila.pop().desaparecer();
        estadosPila.push(estado);
    }

    public void actualizar(float dt){
        //peek: selecciona u obten el elemento actual de la pila
        //update: metodo abstracto de la clase abstracta Estado
        estadosPila.peek().update(dt);
    }

    public void renderizar(SpriteBatch sb){//Utilizacion de recursos mostrados

        estadosPila.peek().render(sb);
    }

}
