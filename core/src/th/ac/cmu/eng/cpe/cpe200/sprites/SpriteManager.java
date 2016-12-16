package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.utils.GameLevelManager;

import java.util.ArrayList;

/**
 * Created by zalzer on 12/10/2016 AD.
 */
public class SpriteManager {

    private static final int LANDED_DIAMETER = 50;
    private static final String TAG = SpriteManager.class.getSimpleName();
    private final BitmapFont font;
    private final Sound healSound;

    private EarthSprite earthSprite;
    private ArrayList<Meteoroid> meteoroids;
    private ArrayList<ShieldSprite> shieldSprites;
    private Skin resource;

    private int mMaxSize;
    private int mVelocity;
    private int mBatchSize;
    private int mDifficulty;

    private Sound explodeSound;
    private Sound attackSound;
    private Sound destroySound;

    private int level;
    private int wave;
    private float waveTime;
    private float levelTime;
    private int meteoroidID;

    private GameLevelManager gameLevelManager;
    private int mMaxHP;


    public SpriteManager(EarthSprite earthSprite, Skin resource) {
        this.earthSprite = earthSprite;
        this.resource = resource;

        // Default Value
        mMaxSize = 3;
        mVelocity = 40;
        mBatchSize = 2;
        mDifficulty = 2;
        mMaxHP = 3;

        explodeSound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/explode_2.wav"));
        attackSound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/explode_1.wav"));
        destroySound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/attack.mp3"));

        meteoroids = new ArrayList<Meteoroid>();
        shieldSprites = new ArrayList<ShieldSprite>();

        level = 0;
        wave = 0;
        waveTime = 0;
        meteoroidID = 0;
        levelTime = 0;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("resource/font/zorque.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1.5f;
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        healSound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/item_pickup_1.wav"));
    }

    public int attacked(int id) {
        int count = 0;
        if (id == 4) {
            count = shieldSprites.size();
            shieldSprites.clear();
            if (Prefender.enableSound)
                healSound.play();
        } else {
            for (Meteoroid m :
                    meteoroids) {
                if (m.attacked(id)) {
                    count++;
                    if (Prefender.enableSound)
                        attackSound.play();
                }
            }
        }
        return count;
    }

    public void update(float deltaTime) {
        levelTime += deltaTime;
        if (levelTime < 3)
            return;
        waveTime += deltaTime;

        if (level < gameLevelManager.level.length) {
            if (meteoroidID < gameLevelManager.level[level][wave].length) {
                GameLevelManager.MeteoroidSpec spec = gameLevelManager.level[level][wave][meteoroidID];
                if ((float) spec.delay / 1000f <= waveTime) {
                    Gdx.app.log(TAG, "New Meteoroid (v:" + mVelocity + ", hp:" + spec.hp.toString() +
                            ", positon:" + spec.position + ", delay:" + spec.delay);
                    meteoroids.add(new Meteoroid(resource, mVelocity, spec.hp, spec.position));
                    meteoroidID++;
                }
            }

            if (meteoroids.isEmpty() && meteoroidID == gameLevelManager.level[level][wave].length) {
                if (wave == gameLevelManager.level[level].length - 1) {
                    level++;
                    levelTime = 0;
                    waveTime = 0;
                    wave = 0;
                    meteoroidID = 0;
                    if (earthSprite.getState() > 0) {
                        for (int i = 0; i < earthSprite.getState(); i++) {
                            shieldSprites.add(new ShieldSprite(resource));
                        }
                    }
                } else {
                    wave++;
                    waveTime = 0;
                    meteoroidID = 0;
                }
            }
        } else {
            if (mMaxSize - meteoroids.size() >= mBatchSize) {
                for (int i = 0; i < mBatchSize; i++) {
                    meteoroids.add(new Meteoroid(resource, mVelocity, mDifficulty, mMaxHP));
                }
            }
            mDifficulty = Math.max(mDifficulty, (int) (waveTime / 9));
            mMaxSize = Math.max(mMaxSize, (int) (waveTime / 7));
            mBatchSize = Math.max(mBatchSize, (int) (waveTime / 10));
            mVelocity = Math.max(mVelocity, 40 + (int) (waveTime / 20));
        }

        ArrayList<Meteoroid> removeList = new ArrayList<Meteoroid>();
        for (Meteoroid m :
                meteoroids) {
            m.update(deltaTime);
            if (m.isDestroy()) {
                removeList.add(m);
                if (Prefender.enableSound)
                    destroySound.play(2f);
            } else if (m.getDistanceToEarth() < LANDED_DIAMETER && !m.isDestroying()) {
                if (Prefender.enableSound)
                    explodeSound.play(2f);
                earthSprite.attacked();
                m.getHP().clear();
            }
        }
        meteoroids.removeAll(removeList);

        for (ShieldSprite s :
                shieldSprites) {
            s.update(deltaTime);
        }
    }

    public void render(SpriteBatch batch) {
        for (Meteoroid m :
                meteoroids) {
            m.render(batch);
        }
        for (ShieldSprite s :
                shieldSprites) {
            s.render(batch);
        }
        for (Meteoroid m :
                meteoroids) {
            m.renderHP(batch);
        }

        if (levelTime < 1.700) {
            GlyphLayout layout = new GlyphLayout();
            if (level < gameLevelManager.level.length) {
                if (level + 1 == gameLevelManager.level.length)
                    layout.setText(font, "FINAL LEVEL");
                else
                    layout.setText(font, "LEVEL: " + (level + 1));
            } else {
                layout.setText(font, "Just Survive!");
            }
            font.draw(batch, layout, Prefender.WIDTH / 2 - layout.width / 2, Prefender.HEIGHT / 2 - layout.height / 2 + 100);
        }

    }

    public void dispose() {
        for (Meteoroid m :
                meteoroids) {
            m.dispose();
        }
    }
}
