package th.ac.cmu.eng.cpe.cpe200.bases;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.StateManager;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public abstract class State {

    protected StateManager stateManager;
    protected Skin resource;

    public State(StateManager stateManager, Skin skin) {
        this.stateManager = stateManager;
        this.resource = skin;
    }

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

}
