package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

/**
 * Created by zalzer on 12/10/2016 AD.
 */
public class EarthSprite extends BaseSprite {

    private static final String TAG = EarthSprite.class.getSimpleName();
    private Sprite earth[];
    private int state;

    public EarthSprite(Skin skin) {
        super(skin);
    }

    @Override
    public void init() {
        state = 0;
        earth = new Sprite[5];
        for (int i = 0; i < 5; i++) {
            earth[i] = resource.getSprite("earth/" + (i + 1));
            earth[i].setOriginCenter();
            earth[i].setScale(0.35f);
            earth[i].setPosition(Prefender.WIDTH / 2 - earth[i].getWidth() / 2, Prefender.HEIGHT / 2 - earth[i].getHeight() / 2);
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        if (state >= 4)
            earth[4].draw(batch);
        else
            earth[state].draw(batch);
    }

    @Override
    public void dispose() {
        for (int i = 0; i < 5; i++) {
            earth[i].getTexture().dispose();
        }
    }

    public boolean attacked() {
        state++;
        return state > 4;
    }

    public boolean isDead() {
        return state > 4;
    }
}
