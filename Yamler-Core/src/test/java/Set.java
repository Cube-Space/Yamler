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
public class Set {
    private SetConfig setConfig;
    private File file;

    @BeforeSuite
    public void before() {
        setConfig = new SetConfig();

        file = new File("temp", "setConfig.yml");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    @Test(priority = 1)
    public void initNull() throws InvalidConfigurationException, IOException {
        setConfig.init(file);

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "StringSet:\n" +
                "- Test\n");
    }

    @org.testng.annotations.Test(priority = 2)
    public void addToSet() throws InvalidConfigurationException, IOException {
        setConfig.StringSet.add("Test1");
        setConfig.save();

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "StringSet:\n" +
                "- Test1\n" +
                "- Test\n");
    }

    @org.testng.annotations.Test(priority = 3)
    public void loadConfig() throws InvalidConfigurationException {
        SetConfig setConfig1 = new SetConfig();
        setConfig1.load(file);

        Assert.assertEquals(setConfig1.StringSet.size(), 2);
    }
}
