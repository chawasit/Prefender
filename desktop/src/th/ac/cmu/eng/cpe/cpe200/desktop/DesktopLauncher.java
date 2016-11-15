package th.ac.cmu.eng.cpe.cpe200.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import th.ac.cmu.eng.cpe.cpe200.Prefender;

public class DesktopLauncher {
	public static void main (String[] arg) {

		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.maxWidth = 4096;
		settings.maxHeight = 4096;
		settings.filterMag = Texture.TextureFilter.MipMapLinearLinear;
		settings.filterMin = Texture.TextureFilter.MipMapLinearLinear;
		TexturePacker.process(settings, "resource/earth", "packed", "earth");
		TexturePacker.process(settings, "resource/particles/img", "packed", "particles");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = Prefender.WIDTH;
		config.height = Prefender.HEIGHT;
		config.resizable = false;
		config.samples = 8;
		config.title = Prefender.TITLE;
		config.fullscreen = false;


		new LwjglApplication(new Prefender(), config);
	}
}
