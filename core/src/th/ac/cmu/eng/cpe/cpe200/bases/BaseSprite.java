package th.ac.cmu.eng.cpe.cpe200.bases;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public abstract class BaseSprite {

    protected AssetManager assetManager;
    protected Vector2 position;
    protected Vector2 velocity;
    private boolean initialized;

    public BaseSprite(AssetManager assetManager) {
        this.assetManager = assetManager;
        position = new Vector2();
        velocity = new Vector2();
        initialized = false;
        loadAsset();
    }

    public abstract void loadAsset();

    public abstract void init();

    public void update(float deltaTime) {
        if(!isInitialized())
            init();

    }

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

    public boolean isInitialized() {
        return initialized;
    }

    public void initialized() {
        initialized = true;
    }

}