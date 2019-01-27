package com.paces.game.planets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.paces.game.estados.Estado;
import com.paces.game.estados.GameStateManager;

public class tierra extends Estado {

    public PerspectiveCamera cam;
    public Model model;
    private Model lunaM;
    private ModelInstance lunaI;
    public ModelInstance instance;
    public ModelBatch modelBatch;
    public Environment ambiente;
    public CameraInputController controlador;

    public Texture eart;
    private float ejeTierra = 23.5f;
    private float ejeLuna = 88.3f;
    private DirectionalLight dl;


    public tierra(GameStateManager gsm) {
        super(gsm);

        modelBatch = new ModelBatch();
        eart = new Texture(Gdx.files.internal("tierra.jpg"));


        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(-15f, 2f, -8f);
        //cam.position.rotate(180f, 0f, 0f, 0f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();


        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createSphere(7f, 7f, 7f, 32, 32, new Material(TextureAttribute.createDiffuse(eart)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        instance = new ModelInstance(model, 0, 0, 0);
        instance.transform.rotate(1f, 0f, 0f, ejeTierra);

        dl = new DirectionalLight().set(1.8f, 1.8f, 1.8f, -0.5f, 0f, 2f);

        ambiente = new Environment();
        ambiente.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        ambiente.add(dl);

        crearLuna();

        controlador = new CameraInputController(cam);
        Gdx.input.setInputProcessor(controlador);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

        controlador.update();

        Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20. glClearColor(0, 0, 0, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        instance.transform.rotate(0f, 1f, 0f, 0.1f);
        lunaI.transform.rotate(0f,1f,0f,0.1f);

        cam.update();

        modelBatch.begin(cam);
        modelBatch.render(instance, ambiente);
        modelBatch.render(lunaI, ambiente);
        modelBatch.end();

        instance.calculateTransforms();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void desaparecer() {

        model.dispose();
        modelBatch.dispose();
        //eart.dispose();

        System.out.println("liberado");
    }

    public void crearLuna(){

        ModelBuilder modelBuilder = new ModelBuilder();
        lunaM = modelBuilder.createSphere(1f, 1f, 1f, 32, 32, 1,new Material(TextureAttribute.createDiffuse(eart)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        lunaI = new ModelInstance(lunaM, -7f, 0f, 0f);
        lunaI.transform.rotate(1f, 0f, 0f, ejeLuna);
        lunaI.transform.rotate(0f, 0f, 1f, 90f);

    }
}
