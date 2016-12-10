package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

import java.util.ArrayList;

/**
 * Created by zalzer on 12/10/2016 AD.
 */
public class Meteoroid extends BaseSprite {

    private static final String TAG = Meteoroid.class.getSimpleName();

    private static final int HEIGHT = 80;
    private static final int WIDTH = 235;
    private static final int HP_HEIGHT = 50;
    private static final int HP_WIDTH = 50;

    private int velocity_factor;
    private ArrayList<Integer> hp;
    private Sprite meteoroid;
    private Animation anim;
    private TextureRegion keyFrame[];
    private float stateTime;
    private float degree;
    private final int difficult;
    private TextureRegion hpDraw[];

    public Meteoroid(Skin skin, int velocity, int difficult) {
        super(skin);
        this.velocity_factor = velocity;
        this.difficult = difficult;
        randomPosition();
        randomHP();
        getVelocityToEarth();
    }

    private void getVelocityToEarth() {
        float radian = MathUtils.atan2((float) Prefender.HEIGHT / 2 - position.y, (float) Prefender.WIDTH / 2 - position.x);
        degree = radian * MathUtils.radiansToDegrees;
        velocity.x = velocity_factor * MathUtils.cos(radian);
        velocity.y = velocity_factor * MathUtils.sin(radian);
    }

    @Override
    public void init() {
        meteoroid = new Sprite();
        meteoroid.setPosition(Prefender.WIDTH / 2, Prefender.HEIGHT / 2);
        keyFrame = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            keyFrame[i] = resource.get("meteoroid/" + (i + 1), TextureRegion.class);
        }
        anim = new Animation(1f / 12, keyFrame);
        stateTime = 0;

        hp = new ArrayList<Integer>();
        hpDraw = new TextureRegion[]{
                resource.getRegion("gesture/horizontal"),
                resource.getRegion("gesture/verizontal"),
                resource.getRegion("gesture/caret"),
                resource.getRegion("gesture/rectangle"),
                resource.getRegion("gesture/triangle")
        };


    }

    private void randomHP() {
        for (int i = 0; i < difficult; i++) {
            hp.add(MathUtils.random(0, difficult));
        }
    }

    @Override
    public void update(float deltaTime) {
        stateTime += deltaTime;
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion textureRegion = anim.getKeyFrame(stateTime, true);
        batch.draw(textureRegion,
                position.x - getWidth() * getScale() / 2, position.y - getHeight() / 2, // position
                getHeight() / 2, getHeight() / 2, // origin relative to position
                getWidth(), getHeight(), // dimension
                1f, 1f, // scale
                180 + degree); // rotation

        for (int i = 0; i < hp.size(); i++) {
            batch.draw(hpDraw[hp.get(i)],
                    position.x - ((hp.size()-i) * HP_WIDTH), position.y + 40,
                    HP_WIDTH, HP_HEIGHT);
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < 5; i++) {
            keyFrame[i].getTexture().dispose();
        }
    }

    public void randomPosition() {
        int side = MathUtils.random(1, 4);
        float x = -100, y = -100;
        switch (side) {
            case 1: // Left
                y = MathUtils.random(0, Prefender.HEIGHT);
                x = -100;
                break;
            case 2: // right
                y = MathUtils.random(0, Prefender.HEIGHT);
                x = Prefender.WIDTH + 100;
                break;
            case 3: // top
                y = Prefender.HEIGHT + 100;
                x = MathUtils.random(0, Prefender.WIDTH);
                break;
            case 4: // bottom
                y = -100;
                x = MathUtils.random(0, Prefender.WIDTH);
                break;
        }

        position.x = x;
        position.y = y;

    }

    public boolean attacked(int id) {
        if(id == hp.get(0)) {
            hp.remove(0);
            return true;
        }
        return false;
    }

    public float getWidth() {
        return WIDTH * getScale();
    }

    public float getHeight() {
        return HEIGHT * getScale();
    }

    public float getScale() {
        if (getDistanceToEarth() > 1000) return 1f;
        if (getDistanceToEarth() < 200) return 0.8f;
        return (getDistanceToEarth() - 200) / 800f * 0.2f + 0.8f;
    }

    public float getDistanceToEarth() {
        return (float) Math.sqrt(
                Math.pow(position.x - Prefender.WIDTH / 2, 2) +
                        Math.pow(position.y - Prefender.HEIGHT / 2, 2)
        );
    }

    public boolean destroyed() {
        return hp.isEmpty();
    }
}
