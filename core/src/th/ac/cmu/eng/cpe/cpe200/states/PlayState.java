package th.ac.cmu.eng.cpe.cpe200.states;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.Manager.MeteoroidManager;
import th.ac.cmu.eng.cpe.cpe200.StateManager;
import th.ac.cmu.eng.cpe.cpe200.bases.State;

/**
 * Created by kawewut on 11/10/2016 AD.
 */
public class PlayState extends State {
    private MeteoroidManager meteoroids;
    private int METEOROID_COUNT = 2;
    private int checkscore = 0;
    public PlayState(StateManager stateManager, AssetManager assetManager) {
        super(stateManager, assetManager);
    }


    @Override
    protected void handleInput() {

    }


    @Override
    public void update(float deltaTime) {
        if (meteoroids==null)
            meteoroids = new MeteoroidManager(getAssetManager(),3);

        if( meteoroids.getScore() > 0 && (meteoroids.getScore() )%10 == 0 && checkscore != meteoroids.getScore() ){
            checkscore = meteoroids.getScore();
            meteoroids.addMeteoroid(getAssetManager());
        }

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
