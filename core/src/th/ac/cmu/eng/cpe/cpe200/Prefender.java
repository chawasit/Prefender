package th.ac.cmu.eng.cpe.cpe200;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import th.ac.cmu.eng.cpe.cpe200.bases.State;
import th.ac.cmu.eng.cpe.cpe200.sprites.Background;
import th.ac.cmu.eng.cpe.cpe200.states.MenuState;
import th.ac.cmu.eng.cpe.cpe200.utils.GestureDetection;

import java.util.EmptyStackException;

public class Prefender extends ApplicationAdapter {

    public static final String TAG = Prefender.class.getSimpleName();
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;
    GestureDetection gestureDetection;
    InputProcessor inputProcessor = new InputProcessor() {
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            gestureDetection.init(new GridPoint2(screenX, screenY));
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            gestureDetection.finish();
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            gestureDetection.addPoint(new GridPoint2(screenX, screenY));
            return true;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    };
    private StateManager stateManager;
    private SpriteBatch batch;
    private AssetManager assetManager;
    private Texture loadingImage;
    private Background background;

    @Override
    public void create() {
        batch = new SpriteBatch();
        stateManager = new StateManager();
        assetManager = new AssetManager();
        background = new Background(assetManager);

        // Begin with Menu
        stateManager.push(new MenuState(stateManager, assetManager));

        gestureDetection = new GestureDetection();

        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render() {
        // Clear Screen with Blood!
        Gdx.gl.glClearColor(0, 0, 0, 0.7f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw New Frame
        float deltaTime = Gdx.graphics.getDeltaTime();
        batch.begin();
        if (assetManager.update()) {
            // Background Space wewwww~
            background.update(deltaTime);
            gestureDetection.render(batch);
            // Update and Render Top State
            try {
                State state = stateManager.peek();
                state.update(deltaTime);
                state.render(batch);
            } catch (EmptyStackException e) {
                stateManager.push(new MenuState(stateManager, assetManager));
            }

        } else {
            // Render Loading Screen
            // TODO: show loading
            Gdx.app.log(TAG, "Loading Screen");
        }
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        stateManager.dispose();
        batch.dispose();
    }
}
