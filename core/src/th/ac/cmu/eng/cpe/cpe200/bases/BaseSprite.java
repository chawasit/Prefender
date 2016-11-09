package th.ac.cmu.eng.cpe.cpe200.bases;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public abstract class BaseSprite {

    private AssetManager assetManager;
    private Vector2 position;
    private Vector2 velocity;

    public BaseSprite(AssetManager assetManager) {
        this.assetManager = assetManager;
        position = new Vector2();
        velocity = new Vector2();
        loadAsset(assetManager);
    }

    public abstract void loadAsset(AssetManager assetManager);

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }
}