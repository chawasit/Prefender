package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class BackgroundSprite extends BaseSprite {

    private ParticleEffect starSwarm;
    private TextureAtlas star;

    public BackgroundSprite(AssetManager assetManager) {
        super(assetManager);
    }

    public void init() {
        initialized();
        starSwarm = new ParticleEffect();
        star = assetManager.get("packed/particles.atlas", TextureAtlas.class);
        starSwarm.load(Gdx.files.internal("resource/particles/star_swarm.p"), star);
        starSwarm.setPosition(Prefender.WIDTH/2, Prefender.HEIGHT/2);
        starSwarm.start();
    }

    public void loadAsset() {
        assetManager.load("packed/particles.atlas", TextureAtlas.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        starSwarm.update(deltaTime);
    }

    @Override
    public void render(SpriteBatch batch) {
        starSwarm.draw(batch);
    }

    @Override
    public void dispose() {
        starSwarm.dispose();
    }
}
