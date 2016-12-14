package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class BackgroundSprite extends BaseSprite {

    private ParticleEffect starSwarm;
    private TextureAtlas star;

    public BackgroundSprite(Skin skin) {
        super(skin);
    }

    @Override
    public void init() {
        starSwarm = new ParticleEffect();
        starSwarm.load(Gdx.files.internal("resource/particles/star_swarm.p"),
                Gdx.files.internal("resource/particles/img"));
        starSwarm.setPosition(Prefender.WIDTH/2, Prefender.HEIGHT/2);
        starSwarm.start();
    }

    @Override
    public void update(float deltaTime) {
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
