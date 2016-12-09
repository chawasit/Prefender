package th.ac.cmu.eng.cpe.cpe200.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import th.ac.cmu.eng.cpe.cpe200.Prefender;

public class DesktopLauncher {
    public static void main(String[] arg) {

        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = 4096;
        settings.maxHeight = 4096;
        settings.filterMag = Texture.TextureFilter.MipMapLinearLinear;
        settings.filterMin = Texture.TextureFilter.MipMapLinearLinear;
//        TexturePacker.process(settings, "resource", "packed", "resource");
//        TexturePacker.process(settings, "resource/particles/img", "packed", "particles");
//        TexturePacker.process(settings, "resource/button", "packed", "button");
//        TexturePacker.process(settings, "resource/meteoroid", "packed", "meteoroid");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = Prefender.WIDTH;
        config.height = Prefender.HEIGHT;
        config.resizable = false;
        config.samples = 3;
        config.title = Prefender.TITLE;
        config.fullscreen = false;

        new LwjglApplication(new Prefender((arg.length == 1 && arg[0].toUpperCase().compareTo("DEBUG") == 0)), config);
    }
}
