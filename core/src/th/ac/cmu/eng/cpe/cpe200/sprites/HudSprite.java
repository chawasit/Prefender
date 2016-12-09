package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

import java.util.ArrayList;

/**
 * Created by zalzer on 11/15/2016 AD.
 */
public class HudSprite extends BaseSprite {

    private static final int HUD_GAP = 30;
    private int score;
    private ArrayList<ScoreEffectSprite> scoreAnimates;
    private BitmapFont font;

    public HudSprite(Skin skin) {
        super(skin);
    }

    @Override
    public void init() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("resource/font/zorque.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        scoreAnimates = new ArrayList<ScoreEffectSprite>();
        score = 0;
    }

    @Override
    public void update(float deltaTime) {

        // Update Scoring Effect
        for (ScoreEffectSprite scoreAnim :
                scoreAnimates) {
            if (scoreAnim.isFinish()) {
                score += scoreAnim.getScore();
                scoreAnimates.remove(scoreAnim);
                scoreAnim.dispose();
            } else {
                scoreAnim.update(deltaTime);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        // Scoring Effect
        for (ScoreEffectSprite scoreAnim :
                scoreAnimates) {
            scoreAnim.render(batch);
        }
        String hight_score = "HIGH SCORE: 9999";
        String score = "SCORE: 400";
        font.draw(batch, "High Score", HUD_GAP, Prefender.HEIGHT - HUD_GAP);
        font.draw(batch, "999", HUD_GAP, Prefender.HEIGHT - HUD_GAP*2);
        font.draw(batch, "Score", Prefender.WIDTH - HUD_GAP*5,
                Prefender.HEIGHT - HUD_GAP);
        font.draw(batch, "222", Prefender.WIDTH - HUD_GAP*5,
                Prefender.HEIGHT - HUD_GAP*2);
    }

    @Override
    public void dispose() {

    }

    public void addScore(int score) {
//        scoreAnimates.add(new ScoreEffectSprite(assetManager, score));
    }


}
