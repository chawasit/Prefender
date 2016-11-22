package th.ac.cmu.eng.cpe.cpe200.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.StateManager;
import th.ac.cmu.eng.cpe.cpe200.bases.State;
import th.ac.cmu.eng.cpe.cpe200.utils.GestureDetection;

/**
 * Created by zalzer on 11/14/2016 AD.
 */
public class PlayState extends State {

    private GestureDetection gestureDetection;
    private boolean isStarted;
    private ParticleEffect mouseEffect;
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
            mouseEffect.setPosition(screenX, Prefender.HEIGHT - screenY);
            mouseEffect.start();
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            mouseEffect.setPosition(-500, -500);
            int attackId = gestureDetection.finish();
            System.out.println("Attack: " + attackId);
            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            gestureDetection.addPoint(new GridPoint2(screenX, screenY));
            mouseEffect.setPosition(screenX, Prefender.HEIGHT - screenY);
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


    public PlayState(StateManager stateManager, AssetManager assetManager) {
        super(stateManager, assetManager);

        gestureDetection = new GestureDetection();

        Gdx.input.setInputProcessor(inputProcessor);

        isStarted = true;

        mouseEffect = new ParticleEffect();
        mouseEffect.load(Gdx.files.internal("resource/particles/click_effect.p"),
                Gdx.files.internal("resource/particles/img"));
        mouseEffect.setPosition(-500, -500);
    }

    @Override
    public void update(float deltaTime) {
        mouseEffect.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        mouseEffect.draw(batch);
        gestureDetection.render(batch);
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        gestureDetection = null;
    }
}
