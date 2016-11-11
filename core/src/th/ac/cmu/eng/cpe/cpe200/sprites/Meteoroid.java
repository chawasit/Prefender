package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseMeteoroid;
import java.util.Random;
/**
 * Created by kawewut on 11/10/2016 AD.
 */
public class Meteoroid extends BaseMeteoroid {

    private AssetManager assetManager;

    private Texture meteoroidTexture;
    Random rand = new Random();
    public Meteoroid(AssetManager assetManager) {
        super(assetManager);
        meteoroidTexture = new Texture("badlogic.jpg");
        int x = rand.nextInt(Prefender.WIDTH);
        int y = rand.nextInt(Prefender.HEIGHT);
        getPosition().x = x;
        getPosition().y = y;
        velocity_scale = 50;

    }

    //
    @Override
    public void loadAsset(AssetManager assetManager) {

    }

    @Override
    public void update(float deltaTime) {

        getVelocityToCenter();
        getPosition().x += deltaTime*getVelocity().x;
        getPosition().y += deltaTime*getVelocity().y;

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(meteoroidTexture, getPosition().x, getPosition().y);

    }

    @Override
    public void dispose() {
        meteoroidTexture.dispose();
    }
}
