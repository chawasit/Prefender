package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

/**
 * Created by zalzer on 12/10/2016 AD.
 */
public class MeteoroidManager {

    private static final int LANDED_DIAMETER = 150;
    private EarthSprite earthSprite;
    private ArrayList<Meteoroid> meteoroids;
    private Skin resource;

    private int mMaxSize;
    private int mVelocity;
    private int mBatchSize;
    private int mDifficulty;

    private Sound explodeSound;
    private Sound attackSound;


    public MeteoroidManager(EarthSprite earthSprite, Skin resource) {
        this.earthSprite = earthSprite;
        this.resource = resource;

        mMaxSize = 3;
        mVelocity = 100;
        mBatchSize = 2;
        mDifficulty = 4;

        explodeSound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/explode_2.wav"));
        attackSound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/explode_1.wav"));

        meteoroids = new ArrayList<Meteoroid>();
    }

    public int attacked(int id) {
        int count = 0;
        for (Meteoroid m :
                meteoroids) {
            if(m.attacked(id))
                count++;
        }
        attackSound.play();
        return count;
    }

    public void update(float deltaTime) {
        if(mMaxSize - meteoroids.size() >= mBatchSize) {
            for (int i = 0; i < mBatchSize; i++) {
                meteoroids.add(new Meteoroid(resource, mVelocity, mDifficulty));
            }
        }
        ArrayList<Meteoroid> landed = new ArrayList<Meteoroid>();
        for (Meteoroid m :
                meteoroids) {
            m.update(deltaTime);
            if(m.destroyed()) {
                landed.add(m);
            }else if (m.getDistanceToEarth() < LANDED_DIAMETER) {
                explodeSound.play();
                earthSprite.attacked();
                landed.add(m);
            }
        }

        meteoroids.removeAll(landed);
    }

    public void render(SpriteBatch batch) {
        for (Meteoroid m :
                meteoroids) {
            m.render(batch);
        }
    }

    public void dispose() {
        for (Meteoroid m :
                meteoroids) {
            m.dispose();
        }
    }
}
