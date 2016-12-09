package th.ac.cmu.eng.cpe.cpe200.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.StateManager;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSpriteGDX;
import th.ac.cmu.eng.cpe.cpe200.bases.State;
import th.ac.cmu.eng.cpe.cpe200.sprites.Button;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class MenuState extends State {

    private Button playBtn;
    private Button soundOnBtn;
    private Button soundOffBtn;
    private Sprite logo;

    public MenuState(StateManager stateManager, Skin skin) {
        super(stateManager, skin);
        init();
    }

    private void init() {
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
            }
        });

        soundOnBtn = new Button(resource);
        soundOnBtn.setBtnUp(resource.getRegion("button/speaker"));
        soundOnBtn.setBtnDown(resource.getRegion("button/speakerpress"));
        soundOnBtn.setBtnOver(resource.getRegion("button/speakerpress"));
        soundOnBtn.setCustomHeight(76);
        soundOnBtn.setCustomWidth(259);
        soundOnBtn.setPosition(Prefender.WIDTH / 2, Prefender.HEIGHT / 2 - 200);
        soundOnBtn.setButtonClickListener(new Button.ButtonClickListener() {
            @Override
            public void clickEvent() {
                Prefender.playSound = false;
                System.out.println("sound on click");
            }
        });

        soundOffBtn = new Button(resource);
        soundOffBtn.setBtnUp(resource.getRegion("button/offspeaker"));
        soundOffBtn.setBtnDown(resource.getRegion("button/offspeakerpress"));
        soundOffBtn.setBtnOver(resource.getRegion("button/offspeakerpress"));
        soundOffBtn.setCustomHeight(76);
        soundOffBtn.setCustomWidth(259);
        soundOffBtn.setPosition(Prefender.WIDTH / 2, Prefender.HEIGHT / 2 - 200);
        soundOffBtn.setButtonClickListener(new Button.ButtonClickListener() {
            @Override
            public void clickEvent() {
                Prefender.playSound = true;
                System.out.println("sound off click");
            }
        });

        logo = resource.getSprite("logo/logo");
        logo.setScale((float) 0.5);
        logo.setOriginCenter();
        logo.setPosition(Prefender.WIDTH/2 - logo.getWidth()/2, Prefender.HEIGHT/2);
    }

    @Override
    public void update(float deltaTime) {
        playBtn.update(deltaTime);
        if(Prefender.playSound)
            soundOnBtn.update(deltaTime);
        else
            soundOffBtn.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        playBtn.render(batch);
        if(Prefender.playSound)
            soundOnBtn.render(batch);
        else
            soundOffBtn.render(batch);

        logo.draw(batch);
    }

    @Override
    public void dispose() {
        playBtn.dispose();
        soundOnBtn.dispose();
    }
}
