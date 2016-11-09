package th.ac.cmu.eng.cpe.cpe200.bases;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by zalzer on 11/9/2016 AD.
 */
public abstract class BaseMeteoroid extends BaseSprite {

    private Stack<Integer> hp;

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


}
