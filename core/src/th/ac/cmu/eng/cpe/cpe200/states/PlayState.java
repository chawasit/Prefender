package th.ac.cmu.eng.cpe.cpe200.states;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.Manager.MeteoroidManager;
import th.ac.cmu.eng.cpe.cpe200.StateManager;
import th.ac.cmu.eng.cpe.cpe200.bases.State;
import th.ac.cmu.eng.cpe.cpe200.sprites.Meteoroid;

/**
 * Created by kawewut on 11/10/2016 AD.
 */
public class PlayState extends State {

    private MeteoroidManager meteoroids;

    public PlayState(StateManager stateManager, AssetManager assetManager) {
        super(stateManager, assetManager);
    }


    @Override
    protected void handleInput() {

    }


    @Override
    public void update(float deltaTime) {
        if (meteoroids==null)
            meteoroids = new MeteoroidManager(getAssetManager());
        meteoroids.update(deltaTime);

    }

    @Override
    public void render(SpriteBatch batch) {
        meteoroids.render(batch);

    }

    @Override
    public void dispose() {

    }
}
