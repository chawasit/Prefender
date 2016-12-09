package th.ac.cmu.eng.cpe.cpe200.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import th.ac.cmu.eng.cpe.cpe200.Prefender;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Prefender.WIDTH;
		config.height = Prefender.HEIGHT;
		config.resizable = false;
		config.samples = 3;
		new LwjglApplication(new Prefender(), config);
	}
}
