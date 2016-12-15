package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseMeteoroid;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

/**
 * Created by zalzer on 12/15/2016 AD.
 */
public class ShieldSprite extends BaseMeteoroid {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private TextureRegion shield;
    private float animateY;
    private float originalY;
    public ShieldSprite(Skin skin) {
        super(skin);
    }

    @Override
    public void init() {
        position.x = -100;
        position.y = MathUtils.random(100, Prefender.HEIGHT - 100);
        velocity.x = 20;
        animateY = 25;
        originalY = position.y;
        shield = resource.getRegion("meteoroid/shield");
    }

    @Override
    public void update(float deltaTime) {
        if (position.x < 100)
            position.x += velocity.x * deltaTime;
        if(position.y > originalY + animateY) {
            velocity.y = -5;
        }else if(position.y < originalY - animateY) {
            velocity.y += 5;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(shield, position.x - WIDTH / 2, position.y - HEIGHT / 2, WIDTH, HEIGHT);
    }

    @Override
    public void dispose() {

    }
}
