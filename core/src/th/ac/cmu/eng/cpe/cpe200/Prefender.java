package th.ac.cmu.eng.cpe.cpe200;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import th.ac.cmu.eng.cpe.cpe200.bases.State;
import th.ac.cmu.eng.cpe.cpe200.sprites.BackgroundSprite;
import th.ac.cmu.eng.cpe.cpe200.states.MenuState;

import java.util.EmptyStackException;

public class Prefender extends ApplicationAdapter {

    public static final String TAG = Prefender.class.getSimpleName();

    public static final int WIDTH = 1200 * 10 / 12;
    public static final int HEIGHT = 720 * 10 / 12;
    public static final String TITLE = "Prefender CPE#24";
    public static int HIGH_SCORE = 0;
    public static boolean enableSound = true;
    private static OrthographicCamera camera;
    private StateManager stateManager;
    private SpriteBatch batch;
    private AssetManager assetManager;
    private Texture loadingImage;
    private BackgroundSprite backgroundSprite;
    private long splash_time;
    private boolean debug;
    private Skin resource;
    private boolean isLoaded;
    private Sound startUpSound;
    private Music themeSong;

    public Prefender(boolean debug) {
        this.debug = debug;
        this.isLoaded = false;
        enableSound = true;
    }

    @Override
    public void create() {
        if (debug)
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
        else
            Gdx.app.setLogLevel(Application.LOG_NONE);

        // Init Base System
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        camera.setToOrtho(false);
        batch.setProjectionMatrix(camera.combined);

        stateManager = new StateManager();
        assetManager = new AssetManager();
        assetManager.load("packed/resource.atlas", TextureAtlas.class);
        loadingImage = new Texture(Gdx.files.internal("resource/loading1000x600.png"));

        // Get Save High Score
        Preferences scorePref = Gdx.app.getPreferences("score");
        Prefender.HIGH_SCORE = scorePref.getInteger("high_score", 0);

        // Load Sound and Theme Song
        startUpSound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/startup.wav"));
        startUpSound.play();
        themeSong = Gdx.audio.newMusic(Gdx.files.internal("resource/sounds/theme_song_2.mp3"));
        themeSong.setLooping(true);
        themeSong.setVolume(0.5f);

        splash_time = TimeUtils.millis() + 1000 * 3;
    }

    private void init() {
        isLoaded = true;
        resource = new Skin();
        resource.addRegions(assetManager.get("packed/resource.atlas", TextureAtlas.class));

        backgroundSprite = new BackgroundSprite(resource);

        // Begin with Menu
        stateManager.push(new MenuState(stateManager, resource));
    }

    @Override
    public void render() {
        // Clear Screen with Blood!
        float gray = (float) 35 / 255;
        Gdx.gl.glClearColor(gray, gray, gray, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Theme Song Control
        if (Prefender.enableSound && TimeUtils.millis() > splash_time) {
            themeSong.play();
        } else {
            themeSong.stop();
        }

        // Draw New Frame
        float deltaTime = Gdx.graphics.getDeltaTime();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if (assetManager.update() && TimeUtils.millis() > splash_time) {
            if (!isLoaded)
                init();
            // BackgroundSprite Space wew~
            backgroundSprite.update(deltaTime);
            backgroundSprite.render(batch);
            // Update and Render Top State
            try {
                State state = stateManager.peek();
                state.update(deltaTime);
                state.render(batch);
            } catch (EmptyStackException e) {
                Gdx.app.error("MainRenderer", "StateManager is Empty!");
                stateManager.push(new MenuState(stateManager, resource));
            }
        } else {
            batch.draw(loadingImage,
                    0, 0,
                    Prefender.WIDTH, Prefender.HEIGHT);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        Preferences scorePref = Gdx.app.getPreferences("score");
        scorePref.putInteger("high_score", Prefender.HIGH_SCORE);
        scorePref.flush();
        themeSong.stop();
        themeSong.dispose();
        if (backgroundSprite != null)
            backgroundSprite.dispose();
        if (stateManager != null)
            stateManager.dispose();
        if (batch != null)
            batch.dispose();
        startUpSound.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
//        camera.viewportWidth = 30f;
//        camera.viewportHeight = 30f * height/width;
//        camera.update();
    }
}
