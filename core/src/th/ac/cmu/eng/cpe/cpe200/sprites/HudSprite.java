package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

import java.util.ArrayList;

/**
 * Created by zalzer on 11/15/2016 AD.
 */
public class HudSprite extends BaseSprite {

    private int score;
    private ArrayList<ScoreEffectSprite> scoreAnimates;

    public HudSprite(AssetManager assetManager) {
        super(assetManager);
    }

    @Override
    public void loadAsset() {
//        assetManager.load();
    }

    @Override
    public void init() {

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
    }

    @Override
    public void dispose() {

    }

    public void addScore(int score) {
//        scoreAnimates.add(new ScoreEffectSprite(assetManager, score));
    }


}
