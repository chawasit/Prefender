package th.ac.cmu.eng.cpe.cpe200.desktop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by zalzer on 12/14/2016 AD.
 */
public class PackResource {
    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 4096;
        settings.maxHeight = 4096;
        settings.filterMag = Texture.TextureFilter.MipMapLinearLinear;
        settings.filterMin = Texture.TextureFilter.MipMapLinearLinear;
        TexturePacker.process(settings, "resource", "packed", "resource");
//        TexturePacker.process(settings, "resource/particles/img", "packed", "particles");
//        TexturePacker.process(settings, "resource/button", "packed", "button");
//        TexturePacker.process(settings, "resource/meteoroid", "packed", "meteoroid");
    }
}
