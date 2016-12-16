package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

import java.util.ArrayList;

/**
 * Created by zalzer on 12/10/2016 AD.
 */
public class Meteoroid extends BaseSprite {

    public static final int SHIFT = 70;
    private static final String TAG = Meteoroid.class.getSimpleName();
    private static final int HEIGHT = 80;
    private static final int WIDTH = 235;
    private static final int HP_HEIGHT = 30;
    private static final int HP_WIDTH = 30;
    private final int difficult;
    private int velocity_factor;
    private ArrayList<Integer> hp;
    private Sprite meteoroid;
    private Animation anim;
    private TextureRegion keyFrame[];
    private float stateTime;
    private float degree;
    private TextureRegion hpDraw[];
    private Animation destroyAnim;
    private float deadStateTime;
    private TextureRegion[] deadKeyFrame;
    private int maxHP;
    private float lastScale;

    public Meteoroid(Skin skin, int velocity, int difficult, int maxHP) {
        super(skin);
        this.velocity_factor = velocity;
        this.difficult = difficult;
        this.maxHP = maxHP;
        randomPosition();
        randomHP();
        getVelocityToEarth();
    }

    public Meteoroid(Skin skin, int velocity, int difficult, Vector2 position) {
        super(skin);
        this.position = position;
        this.velocity_factor = velocity;
        this.difficult = difficult;
        randomHP();
        getVelocityToEarth();
    }

    public Meteoroid(Skin skin, int velocity, int[] hp, int positionID) {
        super(skin);
        setPosition(positionID);
        for (int id :
                hp) {
            this.hp.add(id);
        }
        this.velocity_factor = velocity;
        this.difficult = this.hp.size();
        getVelocityToEarth();
    }

    public Meteoroid(Skin skin, int velocity, int[] hp) {
        super(skin);
        randomPosition();
        for (int id :
                hp) {
            this.hp.add(id);
        }
        this.velocity_factor = velocity;
        this.difficult = this.hp.size();
        getVelocityToEarth();
    }

    public Meteoroid(Skin skin, int velocity, ArrayList<Integer> hp, Vector2 position) {
        super(skin);
        this.position = position;
        this.velocity_factor = velocity;
        this.difficult = hp.size();
        this.hp = hp;
        randomHP();
        getVelocityToEarth();
    }

    private void getVelocityToEarth() {
        float radian = MathUtils.atan2((float) Prefender.HEIGHT / 2 - position.y, (float) Prefender.WIDTH / 2 - position.x);
        degree = radian * MathUtils.radiansToDegrees;
        velocity.x = velocity_factor * MathUtils.cos(radian) / getScale();
        velocity.y = velocity_factor * MathUtils.sin(radian) / getScale();
    }

    @Override
    public void init() {
        meteoroid = new Sprite();
        meteoroid.setPosition(Prefender.WIDTH / 2, Prefender.HEIGHT / 2);
        keyFrame = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            keyFrame[i] = resource.getRegion("meteoroid/" + (i + 1));
        }
        anim = new Animation(1f / 12, keyFrame);
        stateTime = 0;

        hp = new ArrayList<Integer>();
        hpDraw = new TextureRegion[]{
                resource.getRegion("gesture/horizontal"),
                resource.getRegion("gesture/verizontal"),
                resource.getRegion("gesture/caret"),
                resource.getRegion("gesture/triangle"),
                resource.getRegion("gesture/circle"),
                resource.getRegion("gesture/thunder")
        };

        deadKeyFrame = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            deadKeyFrame[i] = resource.getRegion("meteoroid/b" + (i + 1));
        }
        destroyAnim = new Animation(1f / 12, deadKeyFrame);
        deadStateTime = 0;

    }

    private void randomHP() {
        for (int i = 0; i < maxHP; i++) {
            hp.add(MathUtils.random(0, Math.min(difficult, 3)));
        }
    }

    @Override
    public void update(float deltaTime) {
        if (hp.size() > 0)
            stateTime += deltaTime;
        else
            deadStateTime += deltaTime;

        if (!hp.isEmpty() && hp.get(0) == 4) {
            if (getDistanceToEarth() < 300)
                return;
        }
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion textureRegion;
        if (hp.size() > 0) {
            textureRegion = anim.getKeyFrame(stateTime, true);
            batch.draw(textureRegion,
                    position.x - getHeight() / 2, position.y - getHeight() / 2, // position
                    getHeight() / 2, getHeight() / 2, // origin relative to position
                    getWidth(), getHeight(), // dimension
                    1f, 1f, // scale
                    180 + degree); // rotation
        } else {
            textureRegion = destroyAnim.getKeyFrame(stateTime);
            batch.draw(textureRegion,
                    position.x - getHeight() / 2, position.y - getHeight() / 2, // position
                    getHeight() / 2, getHeight() / 2, // origin relative to position
                    getWidth(), getHeight(), // dimension
                    1f, 1f, // scale
                    180 + degree); // rotation
        }

    }

    public void renderHP(SpriteBatch batch) {
        int hpSize = Math.min(5, hp.size());
        for (int i = 0; i < hpSize; i++) {

            float posY = Math.min(Prefender.HEIGHT - 50, position.y + 40);
            posY = Math.max(0, posY);

            float posX = Math.min(Prefender.WIDTH, position.x);
            posX = Math.max((hpSize * (HP_WIDTH + 5)), posX);
            if (!hp.isEmpty())
                batch.draw(hpDraw[hp.get(i)],
                        posX - ((hpSize - i - 1) * (HP_WIDTH + 5)), posY,
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
        int side = MathUtils.random(1, 10);
        setPosition(side);
    }

    public boolean attacked(int id) {
        if (!hp.isEmpty() && (id == hp.get(0) || id == -2)) {
            hp.remove(0);
            if (hp.isEmpty()) {
                velocity_factor = 10;
                getVelocityToEarth();
            }
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
        return lastScale = isDestroying() ? lastScale : (Math.min(hp.size(), 10) / 4f) / 2 + 0.5f;
    }

    public float getDistanceToEarth() {
        return (float) Math.sqrt(
                Math.pow(position.x - Prefender.WIDTH / 2, 2) +
                        Math.pow(position.y - Prefender.HEIGHT / 2, 2)
        );
    }

    public boolean isDestroying() {
        return hp.isEmpty();
    }

    public boolean isDestroy() {
        return hp.isEmpty() && destroyAnim.isAnimationFinished(deadStateTime);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(int positionID) {
        switch (positionID) {
            case 1:
                position.x = Prefender.WIDTH / 4 + SHIFT;
                position.y = Prefender.HEIGHT + SHIFT;
                break;
            case 2:
                position.x = 0 - SHIFT;
                position.y = Prefender.HEIGHT / 3 - SHIFT;
                break;
            case 3:
                position.x = 0 - SHIFT;
                position.y = Prefender.HEIGHT * 2 / 3 - SHIFT;
                break;
            case 4:
                position.x = Prefender.WIDTH / 4 - SHIFT;
                position.y = 0 - SHIFT;
                break;
            case 5:
                position.x = Prefender.WIDTH * 3 / 4 + SHIFT;
                position.y = Prefender.HEIGHT + SHIFT;
                break;
            case 6:
                position.x = Prefender.WIDTH + SHIFT;
                position.y = Prefender.HEIGHT * 2 / 3 + SHIFT;
                break;
            case 7:
                position.x = Prefender.WIDTH + SHIFT;
                position.y = Prefender.HEIGHT / 3 + SHIFT;
                break;
            case 8:
                position.x = Prefender.WIDTH * 3 / 4 - SHIFT;
                position.y = 0 - SHIFT;
                break;
            case 9:
                position.x = Prefender.WIDTH / 2 + SHIFT;
                position.y = Prefender.HEIGHT + SHIFT;
                break;
            case 10:
                position.x = Prefender.WIDTH / 2 - SHIFT;
                position.y = 0 - SHIFT;
                break;
            default:
                position.x = 0;
                position.y = 0;
        }
    }

    public ArrayList<Integer> getHP() {
        return hp;
    }

}
