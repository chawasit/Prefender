package th.ac.cmu.eng.cpe.cpe200.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseMeteoroid;
import th.ac.cmu.eng.cpe.cpe200.sprites.Meteoroid;

import java.util.ArrayList;


/**
 * Created by kawewut on 11/11/2016 AD.
 */
public class MeteoroidManager extends BaseMeteoroid {
    public static final String TAG = MeteoroidManager.class.getSimpleName();
    private int METEOROID_COUNT;
    private int MAX = 10;
    private int score = 0;
    private ArrayList<Meteoroid> meteoroids;


    public MeteoroidManager(AssetManager assetManager, int METEOROID_MAX) {
        super(assetManager);
        meteoroids = new ArrayList<Meteoroid>();
        for (int i = 0; i < METEOROID_MAX; i++) {
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
                score++;
                meteoroid.setHit(false);
                meteoroid.randomPosition();
            }
        }
        //Gdx.app.log(TAG, String.valueOf(score));
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

    public int getMETEOROID_COUNT() {
        return METEOROID_COUNT;
    }

    public void setMETEOROID_COUNT(int METEOROID_COUNT) {
        this.METEOROID_COUNT = METEOROID_COUNT;
    }

    public void addMeteoroid(AssetManager assetManager){
        if(MAX >=  meteoroids.size())
            meteoroids.add(new Meteoroid(assetManager));
    }

    public int getScore() {
        return score;
    }
}
