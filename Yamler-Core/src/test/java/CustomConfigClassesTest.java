import base.Util;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Fabian Faßbender on 26.07.2014.
 * Created for IndieLemon
 * Product GommeHD
 */
public class CustomConfigClassesTest {
    private CustomConfigClasses customConfigClasses;
    private File file;

    @BeforeSuite
    public void before() {
        customConfigClasses = new CustomConfigClasses();

        file = new File("temp", "customConfigClasses.yml");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    @Test(priority = 1)
    public void initNull() throws InvalidConfigurationException, IOException {
        customConfigClasses.init(file);

        String fileContents = Util.readFile( file );

        Assert.assertEquals( fileContents.replace( "\r", "" ), "# This is a example Config showing you how to use Custom Classes inside the Config\n" +
                "\n" +
                "Spawn:\n" +
                "  # To configure the default Spawn Location please use '/plugin setspawn' ingame.\n" +
                "  # If you modify it here it can not work.\n" +
                "  Location:\n" +
                "    server: null\n" +
                "    world: null\n" +
                "    x: null\n" +
                "    y: null\n" +
                "    z: null\n" +
                "    pitch: null\n" +
                "    yaw: null\n" );
    }
}
