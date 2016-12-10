package th.ac.cmu.eng.cpe.cpe200.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.StateManager;
import th.ac.cmu.eng.cpe.cpe200.bases.State;
import th.ac.cmu.eng.cpe.cpe200.sprites.*;
import th.ac.cmu.eng.cpe.cpe200.utils.GestureDetection;

/**
 * Created by zalzer on 11/14/2016 AD.
 */
public class PlayState extends State {

    private static final String TAG = PlayState.class.getSimpleName();

    private GestureDetection gestureDetection;
    private ParticleEffect mouseEffect;
    private HudSprite hudSprite;
    private boolean isTouch;
    private EarthSprite earth;
    private MeteoroidManager meteoroidManager;
    private Button playBtn;
    private static final int BASE_SCORE = 10;


    public PlayState(StateManager stateManager, Skin skin) {
        super(stateManager, skin);
    }

    @Override
    public void init() {
        Gdx.app.debug(TAG, "init ...");
        hudSprite = new HudSprite(resource);
        gestureDetection = new GestureDetection();
        isTouch = false;
//        Gdx.input.setInputProcessor(inputProcessor);

        // Touch Effect
        mouseEffect = new ParticleEffect();
        mouseEffect.load(Gdx.files.internal("resource/particles/click_effect.p"),
                Gdx.files.internal("resource/particles/img"));
        mouseEffect.setPosition(-500, -500);

        // EarthSprite
        earth = new EarthSprite(resource);

        meteoroidManager = new MeteoroidManager(earth, resource);

        playBtn = new Button(resource);
        playBtn.setBtnUp(resource.getRegion("button/play"));
        playBtn.setBtnDown(resource.getRegion("button/playpress"));
        playBtn.setBtnOver(resource.getRegion("button/playpress"));
        playBtn.setCustomHeight(76);
        playBtn.setCustomWidth(259);
        playBtn.setPosition(Prefender.WIDTH / 2, Prefender.HEIGHT / 2 - 240);

        playBtn.setButtonClickListener(new Button.ButtonClickListener() {
            @Override
            public void clickEvent() {
                stateManager.popPush(new PlayState(stateManager, resource));
                Gdx.app.debug("playBtn", "launch Play State");
            }
        });

    }

    @Override
    public void update(float deltaTime) {
        mouseEffect.update(deltaTime);

        if(!earth.isDead()) {
            // Touch Event
            if (Gdx.input.justTouched()) {
                isTouch = true;
                gestureDetection.init(new GridPoint2(Gdx.input.getX(), Gdx.input.getY()));
                mouseEffect.setPosition(Gdx.input.getX(), Prefender.HEIGHT - Gdx.input.getY());
                mouseEffect.start();
            } else if (Gdx.input.isTouched()) {
                gestureDetection.addPoint(new GridPoint2(Gdx.input.getX(), Gdx.input.getY()));
                mouseEffect.setPosition(Gdx.input.getX(), Prefender.HEIGHT - Gdx.input.getY());
            } else if(isTouch) {
                isTouch = false;
                mouseEffect.setPosition(-500, -500);
                int attack = gestureDetection.finish();
                int count = meteoroidManager.attacked(attack);
                int score = 0;
                if(count>1)
                    score = BASE_SCORE*count*3/2;
                else
                    score = BASE_SCORE;
                hudSprite.addScore(score);
                Gdx.app.debug(TAG, "Attack: " + attack);
            }

            meteoroidManager.update(deltaTime);
        }else{
            // Freeze Everything
            hudSprite.gameOver();
            playBtn.update(deltaTime);
        }

        hudSprite.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        earth.render(batch);
        meteoroidManager.render(batch);
        hudSprite.render(batch);
        if(!earth.isDead()) {
            mouseEffect.draw(batch);
            gestureDetection.render(batch);
        }else{
            playBtn.render(batch);
        }
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        gestureDetection = null;
    }
}
