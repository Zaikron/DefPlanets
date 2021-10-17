package com.paces.game.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.Model;
import java.awt.TextArea;

public class Assets {

    public AssetManager manager;
    public ParticleEffectLoader managerParticle;
    FileHandleResolver resolver;

    public Assets(){

        manager = new AssetManager();
    }

    public void loadAssets(){
        manager.load("TexturasPlanetas/mercurio.jpg", Texture.class);
        manager.load("TexturasPlanetas/venus.jpg", Texture.class);
        manager.load("TexturasPlanetas/tierra.jpg", Texture.class);
        manager.load("TexturasPlanetas/marte.jpg", Texture.class);
        manager.load("TexturasPlanetas/jupiter.jpg", Texture.class);
        manager.load("TexturasPlanetas/saturno.png", Texture.class);
        manager.load("TexturasPlanetas/urano.jpg", Texture.class);
        manager.load("TexturasPlanetas/neptuno.jpg", Texture.class);
        manager.load("TexturasPlanetas/sol.jpg", Texture.class);

        manager.load("TexturasLunas/bg.jpg", Texture.class);
        manager.load("TexturasLunas/fade.jpg", Texture.class);
        manager.load("TexturasLunas/moon.jpg", Texture.class);

        manager.load("Iconos/mini_Mercurio.png", Texture.class);
        manager.load("Iconos/mini_Venus.png", Texture.class);
        manager.load("Iconos/mini_Tierra.png", Texture.class);
        manager.load("Iconos/mini_Marte.png", Texture.class);
        manager.load("Iconos/mini_Jupiter.png", Texture.class);
        manager.load("Iconos/mini_Saturno.png", Texture.class);
        manager.load("Iconos/mini_Urano.png", Texture.class);
        manager.load("Iconos/mini_Neptuno.png", Texture.class);

        manager.load("MPrincipal/Back.png", Texture.class);
        manager.load("MPrincipal/cargando.png", Texture.class);
        manager.load("MPrincipal/regresar.png", Texture.class);
        manager.load("MPrincipal/triangle.png", Texture.class);
        manager.load("MPrincipal/texturaSolAzul.png", Texture.class);
        manager.load("MPrincipal/nomNormal.png", Texture.class);
        manager.load("MPrincipal/nomBug.png", Texture.class);
        manager.load("MPrincipal/logodef.png", Texture.class);
        manager.load("MPrincipal/logodef2.png", Texture.class);
        manager.load("MPrincipal/logodef3.png", Texture.class);
        manager.load("MPrincipal/space.jpg", Texture.class);

        manager.load("Sonidos/bug.wav", Sound.class);
        manager.load("Sonidos/transition.wav", Sound.class);

        manager.load("Musica/M1.mp3", Music.class);
        manager.load("Musica/M2.mp3", Music.class);

        manager.load("Planetas_Info/Tierra.png", Texture.class);
        manager.load("Planetas_Info/Mercurio.png", Texture.class);
        manager.load("Planetas_Info/Venus.png", Texture.class);
        manager.load("Planetas_Info/Marte.png", Texture.class);
        manager.load("Planetas_Info/Jupiter.png", Texture.class);
        manager.load("Planetas_Info/Saturno.png", Texture.class);
        manager.load("Planetas_Info/Urano.png", Texture.class);
        manager.load("Planetas_Info/Neptuno.png", Texture.class);


        //manager.finishLoading();

    }
}
