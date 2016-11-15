package th.ac.cmu.eng.cpe.cpe200;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import th.ac.cmu.eng.cpe.cpe200.bases.State;
import th.ac.cmu.eng.cpe.cpe200.sprites.BackgroundSprite;
import th.ac.cmu.eng.cpe.cpe200.states.MenuState;

import java.util.EmptyStackException;

public class Prefender extends ApplicationAdapter {

    public static final String TAG = Prefender.class.getSimpleName();

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 720;
    public static final String TITLE = "Prefender CPE#24";

    private StateManager stateManager;
    private SpriteBatch batch;
    private AssetManager assetManager;
    private Texture loadingImage;
    private BackgroundSprite backgroundSprite;
    private long splash_time;
    private boolean debug;

    public Prefender(boolean debug) {
        this.debug = debug;
    }

    @Override
    public void create() {
        if(debug)
            Gdx.app.setLogLevel(Application.LOG_DEBUG);
        else
            Gdx.app.setLogLevel(Application.LOG_NONE);

        batch = new SpriteBatch();
        stateManager = new StateManager();
        assetManager = new AssetManager();
        preLoadAssets();

        backgroundSprite = new BackgroundSprite(assetManager);

        // Begin with Menu
        stateManager.push(new MenuState(stateManager, assetManager));

        splash_time = TimeUtils.millis() + 1000 * 3;
    }

    private void preLoadAssets() {
        loadingImage = new Texture(Gdx.files.internal("resource/loading.png"), true);
        loadingImage.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);
    }

    @Override
    public void render() {
        // Clear Screen with Blood!
        float gray = (float) 35 / 255;
        Gdx.gl.glClearColor(gray, gray, gray, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw New Frame
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch.begin();

        if (assetManager.update() && TimeUtils.millis() > splash_time) {
            // BackgroundSprite Space wew~
            backgroundSprite.update(deltaTime);
            backgroundSprite.render(batch);
            // Update and Render Top State
            try {
                State state = stateManager.peek();
                state.update(deltaTime);
                state.render(batch);
            } catch (EmptyStackException e) {
                stateManager.push(new MenuState(stateManager, assetManager));
            }
        } else {
            batch.draw(loadingImage,
                    0, 0,
                    Prefender.WIDTH, Prefender.HEIGHT);
        }
        batch.end();


        Gdx.app.log("FPS", ""+Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void dispose() {
        if (backgroundSprite != null)
            backgroundSprite.dispose();
        if (stateManager != null)
            stateManager.dispose();
        if (batch != null)
            batch.dispose();
    }
}
