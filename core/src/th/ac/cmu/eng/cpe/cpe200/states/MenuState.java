package th.ac.cmu.eng.cpe.cpe200.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.StateManager;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSpriteGDX;
import th.ac.cmu.eng.cpe.cpe200.bases.State;
import th.ac.cmu.eng.cpe.cpe200.sprites.Button;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class MenuState extends State {

    private Button test;
    private BaseSpriteGDX sp;

    public MenuState(StateManager stateManager, AssetManager assetManager) {
        super(stateManager, assetManager);
        assetManager.load("packed/earth.atlas", TextureAtlas.class);

        assetManager.finishLoading();


        TextureAtlas atlas = assetManager.get("packed/earth.atlas", TextureAtlas.class);
        Skin sk = new Skin();
        sk.addRegions(atlas);

        test = new Button(assetManager);
        test.setBtnUp(sk.getRegion("1"));
        test.setBtnDown(sk.getRegion("2"));
        test.setBtnOver(sk.getRegion("3"));

        test.setButtonClickListener(new Button.ButtonClickListener() {
            @Override
            public void clickEvent() {
                getStateManager().popPush(new PlayState(getStateManager(), getAssetManager()));
            }
        });

        test.setCustomHeight(200);
        test.setCustomWidth(200);
        test.setPosition(500, 500);
    }

    @Override
    public void update(float deltaTime) {
        test.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        BitmapFont b = new BitmapFont();
        b.draw(batch, "Menu State", 400, 400);
        test.render(batch);
    }

    @Override
    public void dispose() {

    }
}
