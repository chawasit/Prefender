package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

/**
 * Created by zalzer on 12/10/2016 AD.
 */
public class EarthSprite extends BaseSprite {

    private static final String TAG = EarthSprite.class.getSimpleName();
    private Sprite earth[];
    private Sprite earthDestroy[];
    private int state;
    private TextureRegion[] keyFrame;
    private float stateTime;
    private Animation anim;

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
            earth[i].setScale(0.1f);
            earth[i].setPosition(Prefender.WIDTH / 2 - earth[i].getWidth() / 2, Prefender.HEIGHT / 2 - earth[i].getHeight() / 2);
        }

        earthDestroy = new Sprite[4];
        for (int i = 0; i < 4; i++) {
            earthDestroy[i] = resource.getSprite("earth/b" + (i + 1));
            earthDestroy[i].setOriginCenter();
            earthDestroy[i].setScale(0.1f);
            earthDestroy[i].setPosition(Prefender.WIDTH / 2 - earth[i].getWidth() / 2, Prefender.HEIGHT / 2 - earth[i].getHeight() / 2);
        }
        keyFrame = new TextureRegion[4];
        stateTime = 0;

        anim = new Animation(1/12f, keyFrame);
    }

    @Override
    public void update(float deltaTime) {
        if (state > 4)
            stateTime += deltaTime;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (state > 4)
            earthDestroy[anim.getKeyFrameIndex(stateTime)].draw(batch);
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
        return state > 4 && anim.isAnimationFinished(stateTime);
    }

    public void heal(int count) {
        state -= count;
        if(state<0)
            state = 0;
    }

    public int getState() {
        return state;
    }
}
