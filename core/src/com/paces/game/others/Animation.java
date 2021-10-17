package com.paces.game.others;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.paces.game.Main;
import com.paces.game.planets.Planetas;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Animation {

    public static final float RAPIDEZ = 1f;

    DecimalFormat dF;
    DecimalFormat dF2;

    public float espacioMejorAngulo = 9f;
    float distancia;//Distancia global para poder realizar la funcion de animacion de transicion
    //Distancia total entre el punto actual y destdino
    float posAc = 0f;//Guarda la posicion actual de la camara la manejarla al realizar una animacion
    int indicadorRA = 0;//Indicador para "indicarme" si estara avanzado o retrocediendo entre los planetas
    int indicePlaneta = 3;//3 pertenece a la tierra, sera el primer planeta en mostrarse
    float saveDistancia = 0f;

    float rapidez = RAPIDEZ;//Entre mas bajo mas lenta sera la animacion

    float divicionDistancia = 0f;//Variable utilizada para dividir la distanica entre 10, asi lograre una disminucion en la animacion...
                                //... de llegada de todos los planetas, dependiendo de su distancia siempre empezara a dismiuir a la misma distancia de la camara y el planeta

    float rapidezDisminucion = 0f;
    boolean indicadorSound = true;

    private Sound transition;

    public Animation(){
        dF = new DecimalFormat("#.##");//Lo utilizo como una forma de "encerrar un resultado decimal Ejem: 3.9 = 4"
        dF2 = new DecimalFormat("#.####");//Para resultados que requieren sumas o restas de decimales con mayor exactitud

        transition = Main.assets.manager.get("Sonidos/transition.wav");
    }


    public void calculoDistancia(float posActual, float posDestino){

        posAc = posActual;
        if(posActual < posDestino){//Si se debe avanzar para llegar al destino
            //La distancia se obtiene restando la localizacion de z del destino a z de la posicion actual
            distancia = (posDestino - posActual) + espacioMejorAngulo;//espacioMejorAngulo es una pequeña distancia para que se logre apreciar el sol
            //posZ = posActual + distancia;//Se le suma la distancia a la posicion actual (avanza)
            //lookZ = posActual + distancia - espacioMejorAngulo;//Se le quita el espacio para que la camara mire exactamente al planeta

            indicadorRA = 1;

        }else if(posActual > posDestino){//Si se retorcede para llegar al destino
            //La distancia se obtiene restando a la posicion actual de la camara, la de destino
            distancia = (posActual - posDestino) - espacioMejorAngulo;
            //posZ = posActual - distancia;//Se le resta la distancia a la posicion actual (retrocede)
            //lookZ = posActual - distancia - espacioMejorAngulo;

            indicadorRA = 2;
        }else{

        }
        saveDistancia = distancia;
        divicionDistancia = saveDistancia / 10;//Se obtiene el numero entre el que se dividira la distancia
    }

    public boolean animationTraslationListener(ArrayList <Planetas>planetas, int indice, PerspectiveCamera camera){

        //Esto ocurre si se tiene que avanzar entre planetas
        if (Float.parseFloat(dF.format(distancia)) > 0f && indicadorRA == 1) {//Cuando llegue la distancia a 0 sabre que ya he consumido toda la distancia entre los planetas
            if(indicadorSound == true){
                transition.play(0.3f);
                indicadorSound = false;
            }

            //Cuando se requiere una disminucion del avance de la camara(efecto)
            if(Float.parseFloat(dF.format(distancia)) < Float.parseFloat(dF.format(saveDistancia)) / divicionDistancia){
                if(Float.parseFloat(dF2.format(rapidez)) > 0f){//Para que la rapidez nunca sea negativa
                    /*Cada vez que se pasa por aqui se divide la rapidez entre el numero de unidades que hacen falta para
                    para completar el recorrido lento, asi el valor de rapidez siempre disminuira en proporcion a la distanca de movimiento lento*/
                    /*Ejem: rapidezDis... = 1f / (100 / 10) = 0.1 (En caso de ir de la tierra a marte). 0.1 se le resta a la rapidez, para que el
                    movimiento sea cada vez mas lento, cada pasada*/
                    rapidezDisminucion =  rapidez / (Float.parseFloat(dF2.format(saveDistancia))  / divicionDistancia);
                    //Se le resta lo obtenido anteriormente, es decir, movimiento cada vez mas lento
                    rapidez = rapidez - rapidezDisminucion;
                    //System.out.println(rapidez);
                }
            }

            distancia = Float.parseFloat(dF2.format(distancia)) - Float.parseFloat(dF2.format(rapidez));//Consumo de la distancia
            posAc = posAc + Float.parseFloat(dF2.format(rapidez));//Aumento de la variable de posicion actual. Se aumentara de 1 en 1, pues la distancia se disminuye en la misma...
            //cantidad. Ejemplo: si la distancia entre planetas es 100, entonces le aumetare 100 a la posicion actual

            //System.out.println("posA: "+ Float.parseFloat(dF2.format(posAc)));//Se utiliza el DecimalFormat que es para encerrar valores decimales
            //System.out.println("dist: "+ Float.parseFloat(dF.format(distancia)));
            camera.position.set(-8f, 1f, Float.parseFloat(dF.format(posAc)));//Se estara actualizando la camara, pues esta funcion de ejecuta en update
            camera.update();

            return true;
        }else  if(Float.parseFloat(dF.format(distancia)) > 0f && indicadorRA == 2){//Si se tiene que retroceder entre los planetas
            if(indicadorSound == true){
                transition.play(0.3f);
                indicadorSound = false;
            }

            //Cuando se requiere una disminucion del avance de la camara(efecto)
            if(Float.parseFloat(dF.format(distancia)) < Float.parseFloat(dF.format(saveDistancia)) / divicionDistancia){
                if(Float.parseFloat(dF2.format(rapidez)) > 0f){
                    rapidezDisminucion =  rapidez / (Float.parseFloat(dF2.format(saveDistancia)) / divicionDistancia);
                    rapidez = rapidez - rapidezDisminucion;
                    //System.out.println(Float.parseFloat(dF2.format(rapidez)));
                }
            }

            distancia = Float.parseFloat(dF2.format(distancia)) - Float.parseFloat(dF2.format(rapidez));
            posAc = posAc -  Float.parseFloat(dF2.format(rapidez));//Entonces se le estara restando a la posicion actual

            //System.out.println("posA: "+ Float.parseFloat(dF2.format(posAc)));
            //System.out.println("dist: "+ Float.parseFloat(dF.format(distancia)));
            camera.position.set(-8f, 1f, Float.parseFloat(dF.format(posAc)));
            camera.update();

            return true;
        }else{
            rapidez = RAPIDEZ;
            //System.out.println(rapidez);
            planetas.get(indice).mostrarInfo();
            indicadorSound = true;
            return false;
        }
    }


}
