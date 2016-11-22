package th.ac.cmu.eng.cpe.cpe200.bases;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public abstract class BaseSpriteGDX extends Sprite{

    protected Vector2 velocity;

    public BaseSpriteGDX() {
    }

    public BaseSpriteGDX(Texture texture) {
        super(texture);
    }

    public BaseSpriteGDX(Texture texture, int srcWidth, int srcHeight) {
        super(texture, srcWidth, srcHeight);
    }

    public BaseSpriteGDX(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
    }

    public BaseSpriteGDX(TextureRegion region) {
        super(region);
    }

    public BaseSpriteGDX(TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(region, srcX, srcY, srcWidth, srcHeight);
    }

    public BaseSpriteGDX(Sprite sprite) {
        super(sprite);
    }

    public abstract void update(float deltaTime);
}