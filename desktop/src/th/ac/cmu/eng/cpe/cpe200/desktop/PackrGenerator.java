package th.ac.cmu.eng.cpe.cpe200.desktop;

import com.badlogicgames.packr.Packr;
import com.badlogicgames.packr.PackrConfig;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by zalzer on 12/14/2016 AD.
 */
public class PackrGenerator {
    public static void main(String[] args) {

        PackrConfig config = new PackrConfig();
        config.platform = PackrConfig.Platform.Windows32;
//        config.jdk = "/Users/zalzer/Workspace/CPE200/Prefender/lib/openjdk-1.7.0-u80-unofficial-macosx-x86_64-image.zip";
        config.jdk = "/Users/zalzer/Workspace/CPE200/Prefender/lib/openjdk-1.7.0-u80-unofficial-windows-i586-image.zip";
        config.executable = "Prefender";
        config.classpath = Arrays.asList("/Users/zalzer/Workspace/CPE200/Prefender/lib/prefender-1.0-demo2.jar");
        config.mainClass = "th.ac.cmu.eng.cpe.cpe200.desktop.DesktopLauncher";
        config.vmArgs = Arrays.asList("Xmx1G");
        config.minimizeJre = "soft";
        config.outDir = new java.io.File("out-win");

        try {
            new Packr().pack(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
