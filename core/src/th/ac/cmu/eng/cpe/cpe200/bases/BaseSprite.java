package th.ac.cmu.eng.cpe.cpe200.bases;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public abstract class BaseSprite {

    protected Skin resource;
    protected Vector2 position;
    protected Vector2 velocity;

    public BaseSprite(Skin skin) {
        resource = skin;
        position = new Vector2();
        velocity = new Vector2();
        init();
    }

    public abstract void init();

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public abstract void dispose();

}