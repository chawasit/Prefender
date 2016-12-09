package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseSprite;

/**
 * Created by zalzer on 11/22/2016 AD.
 */
public class Button extends BaseSprite {

    private static final int UP = 1;
    private static final int OVER = 2;
    private static final int DOWN = 3;
    private TextureRegion btnUp;
    private TextureRegion btnOver;
    private TextureRegion btnDown;
    private int customHeight = -1;
    private int customWidth = -1;
    private ButtonClickListener buttonClickListener;
    private int current_state;

    public Button(Skin skin) {
        super(skin);
    }

    @Override
    public void init() {

    }

    private int getHeight() {
        if (btnUp == null) {
            return 0;
        } else {
            return customHeight == -1 ? btnUp.getRegionHeight() : customHeight;
        }
    }

    private int getWidth() {
        if (btnUp == null) {
            return 0;
        } else {
            return customWidth == -1 ? btnUp.getRegionWidth() : customWidth;
        }
    }

    private int getHalfWidth() {
        return getWidth() / 2;
    }

    private int getHalfHeight() {
        return getHeight() / 2;
    }

    @Override
    public void update(float deltaTime) {
        current_state = getState();
    }

    private int getState() {
        if (btnUp != null) {
            int x = Gdx.input.getX();
            int y = Prefender.HEIGHT - Gdx.input.getY();
            int hw = getHalfWidth();
            int hh = getHalfHeight();

            if (position.x - hw <= x && x <= position.x + hw && position.y - hh <= y && y <= position.y + hh) {
                if (Gdx.input.justTouched()) {
                    if (buttonClickListener != null)
                        buttonClickListener.clickEvent();
                    return DOWN;
                } else {
                    return OVER;
                }
            }
        }
        return UP;
    }

    @Override
    public void render(SpriteBatch batch) {
        switch (current_state) {
            case OVER:
                if (btnOver != null) {
                    batch.draw(btnOver, position.x - getHalfWidth(), position.y - getHalfHeight(), getWidth(), getHeight());
                    break;
                }
            case DOWN:
                if (btnDown != null) {
                    batch.draw(btnDown, position.x - getHalfWidth(), position.y - getHalfHeight(), getWidth(), getHeight());
                    break;
                }
            default:
                if (btnUp != null)
                    batch.draw(btnUp, position.x - getHalfWidth(), position.y - getHalfHeight(), getWidth(), getHeight());
        }
    }

    @Override
    public void dispose() {
        if (btnUp != null)
            btnUp.getTexture().dispose();
        if (btnOver != null)
            btnOver.getTexture().dispose();
        if (btnDown != null)
            btnDown.getTexture().dispose();
    }

    public void setBtnUp(TextureRegion btnUp) {
        this.btnUp = btnUp;
    }

    public void setBtnOver(TextureRegion btnOver) {
        this.btnOver = btnOver;
    }

    public void setBtnDown(TextureRegion btnDown) {
        this.btnDown = btnDown;
    }

    public void setCustomHeight(int customHeight) {
        this.customHeight = customHeight;
    }

    public void setCustomWidth(int customWidth) {
        this.customWidth = customWidth;
    }

    public void setButtonClickListener(ButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    public void setPosition(int x, int y) {
        position.set(x,y);
    }

    public interface ButtonClickListener {
        void clickEvent();
    }
}



