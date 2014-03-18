import base.Util;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Path {
    private PathConfig pathConfig;
    private File file;

    @BeforeSuite
    public void before() {
        pathConfig = new PathConfig();

        file = new File("temp", "pathConfig.yml");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    @org.testng.annotations.Test(priority = 1)
    public void initNull() throws InvalidConfigurationException, IOException {
        pathConfig.init(file);

        String fileContents = Util.readFile(file);
        System.out.println(fileContents);

        Assert.assertEquals(fileContents.replace("\r", ""), "# test\n" +
                "config-with-dash: true\n");
    }

    @org.testng.annotations.Test(priority = 2)
    public void changeBoolean() throws InvalidConfigurationException, IOException {
        pathConfig.Test = false;
        pathConfig.save();

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "# test\n" +
                "config-with-dash: false\n");
    }

    @org.testng.annotations.Test(priority = 3)
    public void loadConfig() throws InvalidConfigurationException {
        PathConfig pathConfig1 = new PathConfig();
        pathConfig1.load(file);

        Assert.assertTrue(!pathConfig1.Test);
    }
}
