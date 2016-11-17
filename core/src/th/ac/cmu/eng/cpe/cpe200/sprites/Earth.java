package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

import java.util.ArrayList;

/**
 * Created by kawewut on 11/15/2016 AD.
 */
public class Earth extends BaseSprite {
    //Texture earthTexture1 ;
    ArrayList<Texture> earth;
    private int STATE = 1;
    public Earth(AssetManager assetManager ,int state) {
        super(assetManager);
        STATE = state;
        earth = new ArrayList<Texture>();
        earth.add(new Texture("Earth1.png"));
        earth.add(new Texture("Earth2.png"));
        earth.add(new Texture("Earth3.png"));
        earth.add(new Texture("Earth4.png"));
        earth.add(new Texture("Earth5.png"));
    }

    @Override
    public void loadAsset(AssetManager assetManager) {
        if (earth != null) {
            earth.add(new Texture("Earth1.png"));
            earth.add(new Texture("Earth2.png"));
            earth.add(new Texture("Earth3.png"));
            earth.add(new Texture("Earth4.png"));
            earth.add(new Texture("Earth5.png"));
        }

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        if(STATE == 0)
            batch.draw(earth.get(0), earth.get(0).getWidth()-Prefender.HEIGHT/2,
                    earth.get(0).getHeight()-Prefender.WIDTH/2,877/4,877/4);
        else if(STATE == 1) {
            batch.draw(earth.get(1), earth.get(1).getWidth() - Prefender.HEIGHT / 2,
                    earth.get(1).getHeight() - Prefender.WIDTH / 2, 877 / 4, 877 / 4);
            earth.get(0).dispose();
        }else if(STATE == 2) {
            batch.draw(earth.get(2), earth.get(2).getWidth() - Prefender.HEIGHT / 2,
                    earth.get(2).getHeight() - Prefender.WIDTH / 2, 877 / 4, 877 / 4);
            earth.get(1).dispose();
        }else if(STATE == 3) {
            batch.draw(earth.get(4), earth.get(4).getWidth() - Prefender.HEIGHT / 2,
                    earth.get(4).getHeight() - Prefender.WIDTH / 2, 877 / 4, 877 / 4);
            earth.get(3).dispose();
        }
        else if(STATE == 4) {
            batch.draw(earth.get(4), earth.get(4).getWidth() - Prefender.HEIGHT / 2,
                    earth.get(4).getHeight() - Prefender.WIDTH / 2, 877 / 4, 877 / 4);
            earth.get(3).dispose();
        }
    }

    @Override
    public void dispose() {

    }

    public void setSTATE(int STATE) {
        this.STATE = STATE;
    }
}
