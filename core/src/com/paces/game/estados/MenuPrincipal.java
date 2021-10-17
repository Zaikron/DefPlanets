package com.paces.game.estados;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.paces.game.Main;
import com.paces.game.others.FadeIn;
import com.paces.game.others.FadeOut;
import com.paces.game.others.Musica;
import com.paces.game.planets.Aster;
import com.paces.game.planets.Escenario;


public class MenuPrincipal extends Estado{

    private static final int WDBTN = Gdx.app.getGraphics().getWidth()/10;
    private static final int HGBTN = Gdx.app.getGraphics().getWidth()/10;

    public static final float SCALESUN = Gdx.app.getGraphics().getHeight() / 192;

    public Stage stage;
    public Skin skin;
    public TextButton btn;

    private Texture bg;

    private Texture tTierra;
    private ImageButton btnTierra;

    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;

    private BitmapFont font;
    private SpriteBatch batch;

    FadeOut fadeOut;
    FadeIn fadeIn;
    Escenario escenario;
    boolean indicadorFinPantalla = false;
    boolean inputP = false;

    ModelBatch modelBatch;
    BlueSun bSun;
    Stars stars;
    Aster aster;

    private Music musicaFondo;
    private Musica musica;
    private boolean indicadorFinM = false;

    public MenuPrincipal(GameStateManager gsm) {
        super(gsm);

        musicaFondo = Main.assets.manager.get("Musica/M1.mp3");
        musica = new Musica(musicaFondo);

        modelBatch = new ModelBatch();
        bSun = new BlueSun(2, 0,0,0, 0,0,5f);
        escenario = new Escenario(gsm);
        inputP = true;
        bg = Main.assets.manager.get("MPrincipal/Back.png");
        stars = new Stars();
        aster = new Aster();

        fadeOut = new FadeOut(0.01f);
        fadeIn = new FadeIn(0.01f);

        font = new BitmapFont();
        batch = new SpriteBatch();

        botonesPl();


        camera = new PerspectiveCamera(47, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(-8f, 0f, 0f);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();

        //indicadorFinPantalla = true;
    }

    @Override
    protected void handleInput() {

        btnTierra.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //gsm.establecer(new Escenario(gsm));
                indicadorFinPantalla = true;//Para indicar que se cambiara de pantalla, se activara el efecto en el metodo update
                indicadorFinM = true;
                //gsm.insertar(new Escenario(gsm));

            }
        });

    }

    @Override
    public void update(float dt) {

        setInputPross();
        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20. glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        dibujarFondo();
        stars.renderStars(dt);
        aster.renderAsters(dt);

        bSun.rotacion(0f,1f,0f,0.5f);

        modelBatch.begin(camera);
        modelBatch.render(bSun.instancia, bSun.ambiente);
        modelBatch.end();

        stage.act();
        stage.draw();

        musica.fadeInMusic(indicadorFinM);
        musica.fadeOutMusic(indicadorFinM);

        fadeOut.setFadeOut(indicadorFinPantalla, gsm, escenario);
        //Esta funcion de la clase FadeOut actuara similar a un escuchador que estara esperando hasta que cumplan ciertas
        //condiciones en la funcion, el indicador dara partida a que la funcion comience a trabajar, tambien se envia la pantalla a la que se dirigira

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        fadeIn.setFadeIn();
    }

    @Override
    public void desaparecer() {

        //System.out.println("prin");
        /*tTierra.dispose();
        fadeIn.fade.dispose();
        fadeIn.batchFade.dispose();
        fadeOut.fade.dispose();
        fadeOut.fade.dispose();
        bg.dispose();
        batch.dispose();*/

    }


    private void botonesPl(){

        int altoMenu = HGBTN / 2;
        int baseMarte = (Gdx.app.getGraphics().getWidth() / 2) - 75;//Por alguna razon no queda completamente en medio, le reste 30 mas
        int baseJupiter = (Gdx.app.getGraphics().getWidth() / 2) + 15;//y a esta variable le quite lo que le aumente a la otra

        stage = new Stage(new ScreenViewport());

        establecerTexturasBotones();
        establecerBotones();
        handleInput();
        posicionarBoton(btnTierra, (Gdx.app.getGraphics().getWidth() / 2) - WDBTN / 2, (Gdx.app.getGraphics().getHeight() / 2) - (HGBTN / 2));

        addToStage(stage, btnTierra);

        Gdx.input.setInputProcessor(stage);
    }


    private void establecerTexturasBotones(){

        tTierra = Main.assets.manager.get("MPrincipal/triangle.png");

    }

    private void posicionarBoton(ImageButton imageButton, int x, int y){

        imageButton.setSize(WDBTN, HGBTN);
        imageButton.setPosition(x, y);
    }

    private void addToStage(Stage stage, ImageButton imageButton){
        stage.addActor(imageButton);
    }

    private void establecerBotones(){

        myTextureRegion = new TextureRegion(tTierra);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnTierra = new ImageButton(myTexRegionDrawable);

    }

    private void dibujarFondo(){

        batch.begin();
        batch.draw(bg, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        batch.end();
    }

    public void setInputPross(){

        if(inputP == true){
            musica.playMusic();
            Gdx.input.setInputProcessor(stage);
            inputP = false;
        }
    }

}
