package com.paces.game.planets;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.paces.game.Main;
import com.paces.game.estados.Estado;
import com.paces.game.estados.GameStateManager;
import com.paces.game.estados.MenuPrincipal;
import com.paces.game.others.Animation;
import com.paces.game.others.BG;
import com.paces.game.others.FadeIn;
import com.paces.game.others.FadeOut;
import com.paces.game.others.Luna;
import com.paces.game.others.Lunas;
import com.paces.game.others.Musica;

public class Escenario extends Estado {

    FadeIn fadeIn;//Efecto fadein
    FadeOut fadeOut;//Efecto fadeout
    Animation animation;//Animacion de traslado entre los planetas
    MenuPrincipal menuPrincipal;
    boolean inputP = true;
    Aster aster;//Generacion de particulas

    private ModelBatch modelBatch;
    private CameraInputController controlador;
    private Luna luna;
    private BG background;
    private Earth tierra;//Objetos de cada uno de los planetas para introducir en arreglo
    private Mercury mercurio;
    private Venus venus;
    private Mars marte;
    private Jupiter jupiter;
    private  Saturn saturno;
    private Neptune neptuno;
    private Uranus urano;
    private Sun sol;
    private ArrayList<Planetas> planetas = new ArrayList<Planetas>();
    private ArrayList<Lunas> lunas = new ArrayList<Lunas>();

    //Constantes para los botones de los planetas
    private static final int WDBTN = Gdx.app.getGraphics().getHeight()/13;
    private static final int HGBTN = Gdx.app.getGraphics().getHeight()/13;

    //Contantes para el boton de salir
    private static final int WSALIR= Gdx.app.getGraphics().getHeight()/6;
    private static final int HSALIR = Gdx.app.getGraphics().getHeight()/6;

    public Stage stageBtn;//Para los botones con imagen

    //Texturas de los planetas
    private Texture tMercurio;
    private Texture tVenus;
    private Texture tTierra;
    private Texture tMarte;
    private Texture tJupiter;
    private Texture tSaturno;
    private Texture tUrano;
    private Texture tNeptuno;
    private Texture tSalir;

    //Botones con imagen para los planetas
    private ImageButton btnMercurio;
    private ImageButton btnVenus;
    private ImageButton btnTierra;
    private ImageButton btnMarte;
    private ImageButton btnJupiter;
    private ImageButton btnSaturno;
    private ImageButton btnUrano;
    private ImageButton btnNeptuno;
    private ImageButton btnSalir;

    //Objetos necesarios para la creacion y funcionamiento de los botones con imagenes
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;

    private  Texture back;//Imagen de fondo
    private SpriteBatch batchSpace;

    int indicePlaneta = 3;//3 pertenece a la tierra, sera el primer planeta en mostrarse
    boolean indicadorFinPantalla = false;//Cambiara cuando se le de fin a una pantalla, contribuira al fadeout

    //Creacion de particulas de estrella
    private Aster star1;
    private Aster star2;
    private Aster star3;
    private Aster star4;

    //Objeto Musica creado por mi y objeto music para elegir la cancion
    private Musica musicaClass;
    private Music music;
    private boolean indicadorFinMusica = false;//Cambiara cuando se cambie de pantalla, ayudara a dar fin a la musica

    private ArrayList<Aster> estrellas;


    public Escenario(GameStateManager gsm) {
        super(gsm);

        //Inicializacion de objetos, la mayoria toman el recurso de la clase de assets, ya que ya fueron cargados
        music = Main.assets.manager.get("Musica/M2.mp3");
        musicaClass = new Musica(music);
        musicaClass.playMusic();

        aster = new Aster();
        batchSpace = new SpriteBatch();
        back = Main.assets.manager.get("TexturasLunas/bg.jpg");
        botonesP();

        fadeIn = new FadeIn(0.01f);//Tiempos a los fade
        fadeOut = new FadeOut(0.01f);
        animation = new Animation();

        modelBatch = new ModelBatch();
        luna = new Luna(0.95f,-8f,0f,300f, 2f, 0f, 5f);
        background = new BG(1f,900f,900f, 300f);

        //Inicializacion de los planetas
        sol = new Sun(150f, 0,0,0, 0,0,5f);
        mercurio = new Mercury(3.5f, 0,0,100, 4,0,5f);
        venus = new Venus(3.5f, 0,0,200, 3,0,5f);
        tierra = new Earth(3.5f, 0,0,300, 2,0,5f);
        marte = new Mars(3.5f, 0,0,400, 2,0,5f);
        jupiter = new Jupiter(3.5f, 0,0,500, 2,0,5f);
        saturno = new Saturn(3.5f, 0,0,600, 2,0,5f);
        urano = new Uranus(3.5f, 0,0,700, 2,0,5f);
        neptuno = new Neptune(3.5f, 0,0,800, 2,0,5f);

        //Introduccion de los planetas al arreglo
        planetas.add(sol);
        planetas.add(mercurio);
        planetas.add(venus);
        planetas.add(tierra);//Introduccion de planetas al arreglo
        planetas.add(marte);
        planetas.add(jupiter);
        planetas.add(saturno);
        planetas.add(urano);
        planetas.add(neptuno);

        lunas.add(luna);//Introduccion de lunas al arreglo

        //Inicializacion de arreglo de estrellas (Particulas)
        estrellas = new ArrayList<Aster>();
        estrellas.add(star1 = new Aster());
        estrellas.add(star2 = new Aster());
        estrellas.add(star3 = new Aster());
        estrellas.add(star4 = new Aster());

        camera = new PerspectiveCamera(47, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(-8f, 1f, 300f + animation.espacioMejorAngulo);
        camera.lookAt(0,0,300);
        camera.near = 1f;
        camera.far = 900f;
        camera.update();
        //btnJupiter.setDisabled(true);
        //controlador = new CameraInputController(camera);
        //controlador = new CameraInputController(camera);
        //Gdx.input.setInputProcessor(controlador);
    }


    @Override
    protected void handleInput() {
        btnMercurio.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                //Se manda por parametros la posicion en z de la camara y la posicion en z del planeta

                if(indicePlaneta != 1){
                    planetas.get(indicePlaneta).getAnimacionInfo().x = Planetas.xInfo;
                    indicePlaneta=1;
                    animation.calculoDistancia(camera.position.z, mercurio.getZPlaneta());
                }
            }
        });

        btnVenus.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(indicePlaneta != 2){
                    planetas.get(indicePlaneta).getAnimacionInfo().x = Planetas.xInfo;
                    indicePlaneta=2;
                    animation.calculoDistancia(camera.position.z, venus.getZPlaneta());
                }
            }
        });
        btnTierra.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(indicePlaneta != 3){
                    planetas.get(indicePlaneta).getAnimacionInfo().x = Planetas.xInfo;
                    indicePlaneta=3;
                    animation.calculoDistancia(camera.position.z, tierra.getZPlaneta());
                }
            }
        });
        btnMarte.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(indicePlaneta != 4){
                    planetas.get(indicePlaneta).getAnimacionInfo().x = Planetas.xInfo;
                    indicePlaneta=4;
                    animation.calculoDistancia(camera.position.z, marte.getZPlaneta());
                }
            }
        });
        btnJupiter.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(indicePlaneta != 5){
                    planetas.get(indicePlaneta).getAnimacionInfo().x = Planetas.xInfo;
                    indicePlaneta=5;
                    animation.calculoDistancia(camera.position.z, jupiter.getZPlaneta());
                }
            }
        });
        btnSaturno.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(indicePlaneta != 6){
                    planetas.get(indicePlaneta).getAnimacionInfo().x = Planetas.xInfo;
                    indicePlaneta=6;
                    animation.calculoDistancia(camera.position.z, saturno.getZPlaneta());
                }
            }
        });
        btnUrano.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(indicePlaneta != 7){
                    planetas.get(indicePlaneta).getAnimacionInfo().x = Planetas.xInfo;
                    indicePlaneta=7;
                    animation.calculoDistancia(camera.position.z, urano.getZPlaneta());
                }
            }
        });
        btnNeptuno.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(indicePlaneta != 8){
                    planetas.get(indicePlaneta).getAnimacionInfo().x = Planetas.xInfo;
                    indicePlaneta=8;
                    animation.calculoDistancia(camera.position.z, neptuno.getZPlaneta());
                }
            }
        });
        btnSalir.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                indicadorFinPantalla = true;
                indicadorFinMusica = true;
            }
        });
    }

    @Override
    public void update(float dt) {

        //Funcion ejecutada en bucle
        setInputPross();
        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20. glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        setSpace();//Establesco el fondo de espacio
        aster.renderAsters(dt);//Renderizo la partiula de un cometa

        //Renderizo las particulas del arreglo de estrellas
        for(int i = 0; i < estrellas.size(); i++){
            estrellas.get(i).renderStar(dt);
        }

        //Rotacion de todos los planetas
        planetas.get(1).rotacion(0f,1f,0f,0.1f);
        planetas.get(2).rotacion(0f,1f,0f,0.1f);
        planetas.get(3).rotacion(0f,1f,0f,0.1f);
        planetas.get(4).rotacion(0f,1f,0f,0.1f);
        planetas.get(5).rotacion(0f,1f,0f,0.1f);
        planetas.get(6).rotacion(0f,1f,0f,0.1f);
        planetas.get(7).rotacion(0f,1f,0f,0.1f);
        planetas.get(8).rotacion(0f,1f,0f,0.1f);
        planetas.get(0).rotacion(0f,1f,0f,0.03f);

        luna.movLuna(0f,1f, 0f, 0.14f,0f,0f,-0.02f);//

        //Renderizado de todos los planetas y su ambiente visual
        modelBatch.begin(camera);
        //modelBatch.render(background.bgI);
        modelBatch.render(planetas.get(0).instancia, planetas.get(0).ambiente);
        modelBatch.render(planetas.get(1).instancia, planetas.get(1).ambiente);
        modelBatch.render(planetas.get(2).instancia, planetas.get(2).ambiente);
        modelBatch.render(planetas.get(3).instancia, planetas.get(3).ambiente);
        modelBatch.render(lunas.get(0).instancia, lunas.get(0).ambiente);//
        modelBatch.render(planetas.get(4).instancia, planetas.get(4).ambiente);
        modelBatch.render(planetas.get(5).instancia, planetas.get(5).ambiente);
        modelBatch.render(planetas.get(6).instancia, planetas.get(6).ambiente);
        modelBatch.render(planetas.get(7).instancia, planetas.get(7).ambiente);
        modelBatch.render(planetas.get(8).instancia, planetas.get(8).ambiente);
        modelBatch.end();

        //importante para mostrar los botones en pantalla
        stageBtn.act();
        stageBtn.draw();

        //Funcion que se ejecutara todo el tiempo, similar a un escuchador
        // pero se ejecutara cuando las condiciones dentro de ella se cumplan. (Clase Animation creada por mi)
        if(animation.animationTraslationListener(planetas, indicePlaneta, camera)){
            desactivarBotones();
        }else{
            activarBotones();
        }

        camera.update();

        //Parecidos a listener, se accionaran cuando cambie la variable pasada por parametros
        //Si es true la musica se ira opacando, si es false la musica se ira escuchando mas fuerte
        musicaClass.fadeInMusic(indicadorFinMusica);
        musicaClass.fadeOutMusic(indicadorFinMusica);

        //Cuando se establesca la var boolean en true se accionara el efecto
        fadeOut.setFadeOut(indicadorFinPantalla, gsm, menuPrincipal);

    }

    //Desactivar y avtivar botones para evitar el bug
    public void desactivarBotones(){
        btnMercurio.setDisabled(true);
        btnVenus.setDisabled(true);
        btnTierra.setDisabled(true);
        btnMarte.setDisabled(true);
        btnJupiter.setDisabled(true);
        btnSaturno.setDisabled(true);
        btnUrano.setDisabled(true);
        btnNeptuno.setDisabled(true);
    }

    public void activarBotones(){
        btnMercurio.setDisabled(false);
        btnVenus.setDisabled(false);
        btnTierra.setDisabled(false);
        btnMarte.setDisabled(false);
        btnJupiter.setDisabled(false);
        btnSaturno.setDisabled(false);
        btnUrano.setDisabled(false);
        btnNeptuno.setDisabled(false);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        //Tamien se ejecuta todo el tiempo
        //Esta funcion funciona de mejor forma aqui
        fadeIn.setFadeIn();
    }

    @Override
    public void desaparecer() {

    }


    //Funciones para crear el menu de planetas

    public void botonesP(){

        //HGBTN es la medida del ancho de los botones
        int margenError = HGBTN / 2;//Se utiliza ya que cuando realizo una funcion para colocar imagenes exactamente en medio de...
                                    //... pantalla se colocan en un sitio un poco apartado del medio, con esto lo soluciono
        int altoMenu = Gdx.app.getGraphics().getWidth() - (WDBTN + (WDBTN / 2));//Para colocar la barra de botones a lo ancho
        //Tomo como base el boton de marte y el de jupiter, ya que estos seran los que representen la parte media de la pantalla
        int baseMarte = (Gdx.app.getGraphics().getHeight() / 2) - (HGBTN - (HGBTN / 4)) - margenError;
        int baseJupiter = (Gdx.app.getGraphics().getHeight() / 2) + (HGBTN - (HGBTN / 4) - margenError);
        int espacionEntre = (HGBTN - (HGBTN / 4)) * 2;//Espacio que habra entre cada boton de planeta

        stageBtn = new Stage(new ScreenViewport());

        establecerTexturasBotones();//Definicion de las texturas de los planetas
        establecerBotones();//Definicion de los botones con imagenes
        handleInput();//Ejecucion de los listeners para cada boton

        //Pocicionamiento de los botones
        posicionarBoton(btnMercurio, altoMenu, baseMarte - (espacionEntre * 3), WDBTN, HGBTN);//Se manda a la funcion el boton y las posiciones
        posicionarBoton(btnVenus, altoMenu, baseMarte - (espacionEntre * 2), WDBTN, HGBTN);
        posicionarBoton(btnTierra, altoMenu, baseMarte - (espacionEntre), WDBTN, HGBTN);
        posicionarBoton(btnMarte, altoMenu, baseMarte, WDBTN, HGBTN);
        posicionarBoton(btnJupiter, altoMenu, baseJupiter, WDBTN, HGBTN);
        posicionarBoton(btnSaturno, altoMenu, baseJupiter + (espacionEntre), WDBTN, HGBTN);
        posicionarBoton(btnUrano, altoMenu, baseJupiter + (espacionEntre * 2), WDBTN, HGBTN);
        posicionarBoton(btnNeptuno, altoMenu, baseJupiter + (espacionEntre * 3), WDBTN, HGBTN);

        posicionarBoton(btnSalir, 10, 0, WSALIR, HSALIR);


        //Se añaden todos los botones al stage
        addToStage(stageBtn, btnMercurio);
        addToStage(stageBtn, btnVenus);
        addToStage(stageBtn, btnTierra);
        addToStage(stageBtn, btnMarte);
        addToStage(stageBtn, btnJupiter);
        addToStage(stageBtn, btnSaturno);
        addToStage(stageBtn, btnUrano);
        addToStage(stageBtn, btnNeptuno);
        addToStage(stageBtn, btnSalir);

        //Se establece  un procesamiento al stage
        Gdx.input.setInputProcessor(stageBtn);

    }

    //Definicion de texturas
    private void establecerTexturasBotones(){

        tMercurio = Main.assets.manager.get("Iconos/mini_Mercurio.png");
        tVenus = Main.assets.manager.get("Iconos/mini_Venus.png");
        tTierra = Main.assets.manager.get("Iconos/mini_Tierra.png");
        tMarte = Main.assets.manager.get("Iconos/mini_Marte.png");
        tJupiter = Main.assets.manager.get("Iconos/mini_Jupiter.png");
        tSaturno = Main.assets.manager.get("Iconos/mini_Saturno.png");
        tUrano = Main.assets.manager.get("Iconos/mini_Urano.png");
        tNeptuno = Main.assets.manager.get("Iconos/mini_Neptuno.png");

        tSalir = Main.assets.manager.get("MPrincipal/regresar.png");
    }

    //Posicionamiento de los botones en pantalla
    private void posicionarBoton(ImageButton imageButton, int x, int y, int tamW, int tamH){

        imageButton.setSize(tamW, tamH);
        imageButton.setPosition(x, y);
    }

    //Añadir al stage los botones
    private void addToStage(Stage stage, ImageButton imageButton){
        stage.addActor(imageButton);
    }

    //Definicion de todos los botones
    private void establecerBotones(){

        myTextureRegion = new TextureRegion(tMercurio);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnMercurio = new ImageButton(myTexRegionDrawable);

        myTextureRegion = new TextureRegion(tVenus);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnVenus = new ImageButton(myTexRegionDrawable);

        myTextureRegion = new TextureRegion(tTierra);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnTierra = new ImageButton(myTexRegionDrawable);

        myTextureRegion = new TextureRegion(tMarte);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnMarte = new ImageButton(myTexRegionDrawable);

        myTextureRegion = new TextureRegion(tJupiter);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnJupiter = new ImageButton(myTexRegionDrawable);

        myTextureRegion = new TextureRegion(tSaturno);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnSaturno = new ImageButton(myTexRegionDrawable);

        myTextureRegion = new TextureRegion(tUrano);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnUrano = new ImageButton(myTexRegionDrawable);

        myTextureRegion = new TextureRegion(tNeptuno);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnNeptuno = new ImageButton(myTexRegionDrawable);

        myTextureRegion = new TextureRegion(tSalir);
        myTexRegionDrawable =new TextureRegionDrawable(myTextureRegion);
        btnSalir = new ImageButton(myTexRegionDrawable);

    }

    //Establecer el fondo de espacio
    private void setSpace(){

        batchSpace.begin();
            batchSpace.draw(back, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        batchSpace.end();
    }


    /*Esta funcion la llamo en update, pues en la pantalla principal tuve la necesidad de inicializar al objeto de la pantalla Escenario en..
    el contructor, ya que si lo definia en update se relentizaba mucho el programa. Si inicializo en el contructor significa que cuando acceda...
    a esta pantalla no se ejecutara el metodo Gdx...setInpu...(stageBtn); pues ya se ejecuto en constructor de la pantalla anterior, y...
    en esta pantalla tambien tengo un inputProcesor, entonces se llegaria a esta pantalla con el unputProcesor de la clase anterior.
    Esta funcion solo se ejecuta una sola vez, para reeestablecer el inputProcesor.*/
    public void setInputPross(){

        if(inputP == true){
            //musicaClass.playMusic();
            inputP = false;
            menuPrincipal = new MenuPrincipal(gsm);
            Gdx.input.setInputProcessor(stageBtn);
        }
    }

}
