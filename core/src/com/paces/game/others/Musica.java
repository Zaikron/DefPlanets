package com.paces.game.others;

import com.badlogic.gdx.audio.Music;
import com.paces.game.Main;

import java.text.DecimalFormat;
import java.util.zip.DataFormatException;

public class Musica {

    private Music musica;
    private float volMusica;
    private DecimalFormat df;

    //Se recibe la musica
    public Musica(Music musica){
        this.musica = musica;
        musica.setVolume(0.0f);
        volMusica = 0f;

        df = new DecimalFormat("#.##");
    }

    //Para iniciar la musica
    public void playMusic(){
        musica.play();
        musica.setLooping(true);//repetir
    }

    //Cuando se inicia una pantalla, la musica va aumentado su volumen poco a poco
    public void fadeInMusic(boolean indicadorFin){
        if(volMusica < 0.600f && indicadorFin == false){//Indicador para accionarse
            if(volMusica == 0f){//A veces no se reproduce, por eso coloco este if para que la reproduzca en esas ocaciones
                musica.play();
            }
            volMusica = volMusica + 0.001f;
            musica.setVolume(volMusica);
        }
    }

    //Cuando se termina una pantalla, la musica va disminuyendo su volumen poco a poco
    public  void fadeOutMusic(boolean indicadorFin){
        if(Float.parseFloat(df.format(volMusica)) >= 0.00f && indicadorFin == true){//Indicador para accionarse
            volMusica = volMusica - 0.01f;
            musica.setVolume(volMusica);
            if(Float.parseFloat(df.format(volMusica))== 0.00f){
                musica.stop();
            }
        }
    }
}
