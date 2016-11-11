package th.ac.cmu.eng.cpe.cpe200.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.StateManager;
import th.ac.cmu.eng.cpe.cpe200.bases.State;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class MenuState extends State {

    Texture playBtn;


    public MenuState(StateManager stateManager, AssetManager assetManager) {
        super(stateManager, assetManager);
        getAssetManager().load("playbtn.png", Texture.class);
    }

    @Override
    public void handleInput() {

        if(Gdx.input.justTouched()){
            getStateManager().popPush(new PlayState(getStateManager(), getAssetManager()));
        }
    }

    @Override
    public void update(float deltaTime) {
        if(playBtn == null)
            playBtn = getAssetManager().get("playbtn.png");
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(playBtn, Prefender.WIDTH / 2, Prefender.HEIGHT / 2);
    }

    @Override
    public void dispose() {
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
