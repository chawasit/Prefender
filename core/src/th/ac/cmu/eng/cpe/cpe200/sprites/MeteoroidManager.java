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
public class MeteoroidManager {

    private static final int LANDED_DIAMETER = 50;
    private static final String TAG = MeteoroidManager.class.getSimpleName();
    private final BitmapFont font;

    private EarthSprite earthSprite;
    private ArrayList<Meteoroid> meteoroids;
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


    public MeteoroidManager(EarthSprite earthSprite, Skin resource) {
        this.earthSprite = earthSprite;
        this.resource = resource;

        mMaxSize = 3;
        mVelocity = 50;
        mBatchSize = 2;
        mDifficulty = 2;
        mMaxHP = 3;

        explodeSound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/explode_2.wav"));
        attackSound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/explode_1.wav"));
        destroySound = Gdx.audio.newSound(Gdx.files.internal("resource/sounds/attack.mp3"));

        meteoroids = new ArrayList<Meteoroid>();

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
    }

    public int attacked(int id) {
        int count = 0;
        for (Meteoroid m :
                meteoroids) {
            if (m.attacked(id)) {
                count++;
                if (Prefender.enableSound)
                    attackSound.play();
            }
        }
        return count;
    }

    public void update(float deltaTime) {
        levelTime += deltaTime;
        if(levelTime<3)
            return;
        waveTime += deltaTime;

        if (level < gameLevelManager.level.length) {
            if (meteoroidID < gameLevelManager.level[level][wave].length) {
                GameLevelManager.MeteoroidSpec spec = gameLevelManager.level[level][wave][meteoroidID];
                if ((float) spec.delay/1000f <= waveTime) {
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
                    wave = 0;
                    meteoroidID = 0;
                } else {
                    wave++;
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
                    destroySound.play(3f);
            } else if (m.getDistanceToEarth() < LANDED_DIAMETER && !m.isDestroying()) {
                if (Prefender.enableSound)
                    explodeSound.play(3f);
                earthSprite.attacked();
                m.getHP().clear();
            }
        }
        meteoroids.removeAll(removeList);
    }

    public void render(SpriteBatch batch) {
        for (Meteoroid m :
                meteoroids) {
            m.render(batch);
        }
        for (Meteoroid m :
                meteoroids) {
            m.renderHP(batch);
        }

        if (levelTime < 1.700) {
            GlyphLayout layout = new GlyphLayout();
            if(level<gameLevelManager.level.length) {
                layout.setText(font, "LEVEL: " + (level + 1));
            }else {
                layout.setText(font, "BONUS Wave!");
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
