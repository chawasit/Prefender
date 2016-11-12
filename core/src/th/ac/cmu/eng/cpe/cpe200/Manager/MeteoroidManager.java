package th.ac.cmu.eng.cpe.cpe200.Manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseMeteoroid;
import th.ac.cmu.eng.cpe.cpe200.sprites.Meteoroid;

import java.util.ArrayList;


/**
 * Created by kawewut on 11/11/2016 AD.
 */
public class MeteoroidManager extends BaseMeteoroid {
    public static final String TAG = MeteoroidManager.class.getSimpleName();
    private static final int TUBE_COUNT = 4;

    private ArrayList<Meteoroid> meteoroids;

    public MeteoroidManager(AssetManager assetManager) {
        super(assetManager);

        meteoroids = new ArrayList<Meteoroid>();

        for (int i = 1; i <= TUBE_COUNT; i++) {
            meteoroids.add(new Meteoroid(assetManager));
        }
    }

    @Override
    public void loadAsset(AssetManager assetManager) {

    }


    @Override
    public void update(float deltaTime) {
        for (Meteoroid meteoroid : meteoroids) {
            if (!meteoroid.isHit()) {
                meteoroid.update(deltaTime);
            } else {
                meteoroid.setHit(false);
                meteoroid.randomPosition();
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (Meteoroid meteoroid : meteoroids) {
            meteoroid.render(batch);
        }
    }

    @Override
    public void dispose() {

    }
}
