package th.ac.cmu.eng.cpe.cpe200.bases;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.StateManager;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public abstract class State {

    private StateManager stateManager;
    private AssetManager assetManager;

    public State(StateManager stateManager, AssetManager assetManager) {
        this.stateManager = stateManager;
        this.assetManager = assetManager;

        // Load Resource Here
    }

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

    public StateManager getStateManager() {
        return stateManager;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
