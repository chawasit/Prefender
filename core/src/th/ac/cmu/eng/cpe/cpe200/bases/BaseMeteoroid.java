package th.ac.cmu.eng.cpe.cpe200.bases;

import com.badlogic.gdx.assets.AssetManager;
import th.ac.cmu.eng.cpe.cpe200.Prefender;

import java.util.Stack;

/**
 * Created by zalzer on 11/9/2016 AD.
 */
public abstract class BaseMeteoroid extends BaseSprite {

    private Stack<Integer> hp;
    private double radian;
    public BaseMeteoroid(AssetManager assetManager) {
        super(assetManager);
    }

    public boolean isDead() {
        return hp.empty();
    }

    public boolean damage(int i) {
        if(isDead()) return false;
        if(hp.peek() == i) {
            hp.pop();
            return true;
        }else{
            return false;
        }
    }

    public void drawSymbol() {
        System.out.println(" ");
    }

    public void getVelocityToCenter() {
        radian = Math.atan2( getPosition().y - Prefender.HEIGHT/2, getPosition().x - Prefender.WIDTH/2);
        getVelocity().x = (float) -(velocity_scale*Math.cos(radian));
        getVelocity().y = (float) -(velocity_scale*Math.sin(radian));
    }

    public float getRadian() {
        return (float) (radian);
    }
}
