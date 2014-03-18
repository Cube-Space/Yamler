import base.Util;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ExtendedConfig {
    private ExtendedConfigConfig extendedConfig;
    private File file;

    @BeforeSuite
    public void before() {
        extendedConfig = new ExtendedConfigConfig();

        file = new File("temp", "extendedConfig.yml");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    @Test(priority = 1)
    public void initNull() throws InvalidConfigurationException, IOException {
        extendedConfig.init(file);

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "test: test\n" +
                "test1: test\n");
    }

    @Test(priority = 2)
    public void changeBoolean() throws InvalidConfigurationException, IOException {
        extendedConfig.test = "test1";
        extendedConfig.save();

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "test: test1\n" +
                "test1: test\n");
    }

    @Test(priority = 2)
    public void loadConfig() throws InvalidConfigurationException {
        ExtendedConfigConfig extendedConfig1 = new ExtendedConfigConfig();
        extendedConfig1.load(file);

        Assert.assertEquals(extendedConfig1.test1, "test");
        Assert.assertEquals(extendedConfig1.test, "test1");
    }
}
