package th.ac.cmu.eng.cpe.cpe200.bases;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public abstract class BaseSprite {

    private AssetManager assetManager;

    public BaseSprite(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

}