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
public class Private {
    private PrivateConfig privateConfig;
    private File file;

    @BeforeSuite
    public void before() {
        privateConfig = new PrivateConfig();

        file = new File("temp", "privateConfig.yml");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    @Test(priority = 1)
    public void initNull() throws InvalidConfigurationException, IOException {
        privateConfig.init(file);

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "TestBoolean: false\n" +
                "TestInt: 0\n" +
                "TestShort: 0\n" +
                "TestByte: 0\n" +
                "TestDouble: 1.0E-7\n" +
                "TestFloat: 1.0E-4\n" +
                "TestLong: 1684654679684\n" +
                "TestChar: c\n");
    }

    @Test(priority = 2)
    public void changeBoolean() throws InvalidConfigurationException, IOException {
        privateConfig.setTestBoolean(true);
        privateConfig.save();

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "TestBoolean: true\n" +
                "TestInt: 0\n" +
                "TestShort: 0\n" +
                "TestByte: 0\n" +
                "TestDouble: 1.0E-7\n" +
                "TestFloat: 1.0E-4\n" +
                "TestLong: 1684654679684\n" +
                "TestChar: c\n");
    }

    @Test(priority = 3)
    public void loadConfig() throws Exception {
        PrivateConfig privateConfig1 = new PrivateConfig();
        privateConfig1.load(file);

        Assert.assertEquals(privateConfig1.isTestBoolean(), true);
    }
}
