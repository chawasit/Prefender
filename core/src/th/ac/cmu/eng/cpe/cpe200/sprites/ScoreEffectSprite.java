package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

/**
 * Created by zalzer on 11/15/2016 AD.
 */
public class ScoreEffectSprite extends BaseSprite {
    public static final int SCORING_TIME = 2*1000;
    private int score;
    private boolean finish;
    private long start_time;
    private BitmapFont font;
    private Vector2 refPosition;

    public ScoreEffectSprite(Skin skin, int score, Vector2 refPosition) {
        super(skin);
        this.score = score;
        this.refPosition = refPosition;
        position = refPosition;
        position.y -= 100;
    }

    @Override
    public void init() {
        start_time = TimeUtils.millis()+SCORING_TIME;
        font = new BitmapFont();

    }

    @Override
    public void update(float deltaTime) {
        position.y += velocity.y*deltaTime;
    }

    @Override
    public void render(SpriteBatch batch) {
        font.draw(batch, "+100", position.x, position.y);
    }

    @Override
    public void dispose() {

    }

    public boolean isFinish() {
        return TimeUtils.millis()>start_time;
    }

    public int getScore() {
        return score;
    }
}
