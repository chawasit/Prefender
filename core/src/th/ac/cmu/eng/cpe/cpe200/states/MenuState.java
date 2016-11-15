package th.ac.cmu.eng.cpe.cpe200.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.StateManager;
import th.ac.cmu.eng.cpe.cpe200.bases.State;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class MenuState extends State {

    public MenuState(StateManager stateManager, AssetManager assetManager) {
        super(stateManager, assetManager);
    }

    @Override
    public void update(float deltaTime) {
        if(Gdx.input.isTouched())
            getStateManager().popPush(new PlayState(getStateManager(), getAssetManager()));
    }

    @Override
    public void render(SpriteBatch batch) {
        BitmapFont b = new BitmapFont();
        b.draw(batch, "Menu State", 400, 400);
    }

    @Override
    public void dispose() {

    }
}
