package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
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
    private boolean gameOver;
    private Button playBtn;
    private Button soundOnBtn;

    public HudSprite(Skin skin) {
        super(skin);
    }

    @Override
    public void init() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("resource/font/zorque.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 1.5f;
        font = generator.generateFont(parameter); // font size 12 pixels
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        scoreAnimates = new ArrayList<ScoreEffectSprite>();
        score = 0;
        gameOver = false;
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

        GlyphLayout layout = new GlyphLayout();

        if(gameOver) {
            layout.setText(font, "Your Score: "+score);
            font.draw(batch, layout, Prefender.WIDTH/2 - layout.width/2, Prefender.HEIGHT/2 - layout.height/2+80);

            layout.setText(font, "High Score: "+Prefender.HIGH_SCORE);
            font.draw(batch, layout, Prefender.WIDTH/2 - layout.width/2, Prefender.HEIGHT/2 - layout.height/2-50);

            layout.setText(font, "GAME OVER");
            font.draw(batch, layout, Prefender.WIDTH/2 - layout.width/2, Prefender.HEIGHT/2 - layout.height/2+200);
        }else {
            layout.setText(font, "High Score");
            font.draw(batch, layout, HUD_GAP, Prefender.HEIGHT - HUD_GAP);

            layout.setText(font, Prefender.HIGH_SCORE + "");
            font.draw(batch, layout, HUD_GAP, Prefender.HEIGHT - HUD_GAP * 2);

            layout.setText(font, "Score");
            font.draw(batch, layout, Prefender.WIDTH - HUD_GAP - layout.width,
                    Prefender.HEIGHT - HUD_GAP);

            layout.setText(font, score + "");
            font.draw(batch, layout, Prefender.WIDTH - HUD_GAP - layout.width,
                    Prefender.HEIGHT - HUD_GAP * 2);
        }
    }

    @Override
    public void dispose() {
        font.dispose();
        scoreAnimates.clear();
    }

    public void addScore(int score) {
        this.score += score;
        if(this.score > Prefender.HIGH_SCORE) {
            Prefender.HIGH_SCORE = this.score;
        }
//        scoreAnimates.add(
//                new ScoreEffectSprite(
//                        resource,
//                        score,
//                        new Vector2(Prefender.WIDTH - HUD_GAP * 5, Prefender.HEIGHT - HUD_GAP * 2))
//        );
    }


    public void gameOver() {
//        if(!gameOver) {
//            Preferences scorePref = Gdx.app.getPreferences("score");
//            scorePref.putInteger("high_score", Prefender.HIGH_SCORE);
//            scorePref.flush();
//        }
        gameOver = true;

    }
}
