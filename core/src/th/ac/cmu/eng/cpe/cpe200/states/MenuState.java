package th.ac.cmu.eng.cpe.cpe200.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.StateManager;
import th.ac.cmu.eng.cpe.cpe200.bases.State;
import th.ac.cmu.eng.cpe.cpe200.sprites.Button;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class MenuState extends State {

    private static final String TAG = MenuState.class.getSimpleName();
    private Button playBtn;
    private Button soundOnBtn;
    private Button soundOffBtn;
    private Sprite logo;
    private int god_counter;

    public MenuState(StateManager stateManager, Skin skin) {
        super(stateManager, skin);
    }

    @Override
    public void init() {
        playBtn = new Button(resource);
        playBtn.setBtnUp(resource.getRegion("button/play"));
        playBtn.setBtnDown(resource.getRegion("button/playpress"));
        playBtn.setBtnOver(resource.getRegion("button/playpress"));
        playBtn.setCustomHeight(76);
        playBtn.setCustomWidth(259);
        playBtn.setPosition(Prefender.WIDTH / 2, Prefender.HEIGHT / 2);

        playBtn.setButtonClickListener(new Button.ButtonClickListener() {
            @Override
            public void clickEvent() {
                stateManager.popPush(new PlayState(stateManager, resource));
                Gdx.app.debug("playBtn", "launch Play State");
            }
        });

        soundOnBtn = new Button(resource);
        soundOnBtn.setBtnUp(resource.getRegion("button/speaker"));
        soundOnBtn.setBtnDown(resource.getRegion("button/speakerpress"));
        soundOnBtn.setBtnOver(resource.getRegion("button/speakerpress"));
        soundOnBtn.setCustomHeight(76);
        soundOnBtn.setCustomWidth(259);
        soundOnBtn.setPosition(Prefender.WIDTH / 2, Prefender.HEIGHT / 2 - 100);
        soundOnBtn.setButtonClickListener(new Button.ButtonClickListener() {
            @Override
            public void clickEvent() {
                Prefender.enableSound = false;
                Gdx.app.debug("soundOnBtn", "sound on click");
            }
        });

        soundOffBtn = new Button(resource);
        soundOffBtn.setBtnUp(resource.getRegion("button/offspeaker"));
        soundOffBtn.setBtnDown(resource.getRegion("button/offspeakerpress"));
        soundOffBtn.setBtnOver(resource.getRegion("button/offspeakerpress"));
        soundOffBtn.setCustomHeight(76);
        soundOffBtn.setCustomWidth(259);
        soundOffBtn.setPosition(Prefender.WIDTH / 2, Prefender.HEIGHT / 2 - 100);
        soundOffBtn.setButtonClickListener(new Button.ButtonClickListener() {
            @Override
            public void clickEvent() {
                Prefender.enableSound = true;
                Gdx.app.debug("soundOffBtn", "sound off click");
            }
        });

        logo = resource.getSprite("logo/logo");
        logo.setScale((float) 0.3);
        logo.setOriginCenter();
        logo.setPosition(Prefender.WIDTH / 2 - logo.getWidth() / 2, Prefender.HEIGHT / 2);

        god_counter = 0;
    }

    @Override
    public void update(float deltaTime) {
        playBtn.update(deltaTime);
        if (Prefender.enableSound)
            soundOnBtn.update(deltaTime);
        else
            soundOffBtn.update(deltaTime);
        if(Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if(x<10 && y < 10) {
                god_counter++;
            }
            if(god_counter > 10) {
                Prefender.GOD_MODE = true;
                Gdx.app.log(TAG, "GOD MODE ! ");
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        playBtn.render(batch);
        if (Prefender.enableSound)
            soundOnBtn.render(batch);
        else
            soundOffBtn.render(batch);
        logo.draw(batch);
    }

    @Override
    public void dispose() {

    }
}
