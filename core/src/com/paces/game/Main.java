package com.paces.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paces.game.estados.GameStateManager;
import com.paces.game.estados.MenuPrincipal;
import com.paces.game.others.Assets;
import com.paces.game.others.Carga;
import com.paces.game.others.Presentacion;
import com.paces.game.planets.Escenario;

public class Main extends ApplicationAdapter {

	private GameStateManager gsm;
	private SpriteBatch batch;

	public static final int alto = 414;
	public static final int ancho = 736;

	public static MenuPrincipal menuPrincipal;
	public static  Escenario escenario;


	public static Assets assets;


	@Override
	public void create () {
		assets = new Assets();
		assets.loadAssets();
		gsm = new GameStateManager();

		gsm.insertar(new Carga(gsm));
}

	@Override
	public void render () {
		//Gdx.gl.glClearColor(1, 0, 0, 1);

		gsm.actualizar(Gdx.graphics.getDeltaTime());//Obtencion del tiempo de ejecucion para el renderizado
		gsm.renderizar(batch);

	}
	
	@Override
	public void dispose () {

	}

	public static AssetManager getManager(){
		return assets.manager;
	}
}
