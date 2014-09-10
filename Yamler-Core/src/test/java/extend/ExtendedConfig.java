package extend;

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
    private ExtendTestConfig extendedConfig;
    private File file;

    @BeforeSuite
    public void before() {
        extendedConfig = new ExtendTestConfig();

        file = new File("temp", "extendedTestConfig.yml");
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

        Assert.assertEquals(fileContents.replace("\r", ""), "action:\n" +
                "  action: default action\n" +
                "trigAct:\n" +
                "  action: default action\n" +
                "  trigger: default trigger\n" +
                "actionCtor:\n" +
                "  action: another action (2)\n" +
                "trigActCtor:\n" +
                "  action: yet another action (3)\n" +
                "  trigger: another trigger (3)\n");
    }

    @Test(priority = 2)
    public void changeBoolean() throws InvalidConfigurationException, IOException {
        extendedConfig.action.action = "test1";
        extendedConfig.save();

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "action:\n" +
                "  action: test1\n" +
                "trigAct:\n" +
                "  action: default action\n" +
                "  trigger: default trigger\n" +
                "actionCtor:\n" +
                "  action: another action (2)\n" +
                "trigActCtor:\n" +
                "  action: yet another action (3)\n" +
                "  trigger: another trigger (3)\n");
    }

    @Test(priority = 2)
    public void loadConfig() throws InvalidConfigurationException {
        ExtendTestConfig extendedConfig1 = new ExtendTestConfig();
        extendedConfig1.load(file);

        Assert.assertEquals(extendedConfig1.trigActCtor.trigger, "another trigger (3)");
        Assert.assertEquals(extendedConfig1.trigActCtor.action, "yet another action (3)");
    }
}
