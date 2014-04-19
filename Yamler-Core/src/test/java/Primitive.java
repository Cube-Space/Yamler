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
public class Primitive {
    private PrimitiveConfig primitiveConfig;
    private File file;

    @BeforeSuite
    public void before() {
        primitiveConfig = new PrimitiveConfig();

        file = new File("temp", "primitiveConfig.yml");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    @org.testng.annotations.Test(priority = 1)
    public void initNull() throws InvalidConfigurationException, IOException {
        primitiveConfig.init(file);

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

    @org.testng.annotations.Test(priority = 2)
    public void changeBoolean() throws InvalidConfigurationException, IOException {
        primitiveConfig.TestBoolean = true;

        primitiveConfig.save();

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

    @org.testng.annotations.Test(priority = 3)
    public void loadConfig() throws Exception {
        PrimitiveConfig primitiveConfig1 = new PrimitiveConfig();

        primitiveConfig1.load(file);

        Assert.assertEquals(primitiveConfig1.TestBoolean, true);

        primitiveConfig1.save();

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "TestBoolean: true\n" +
                "TestShort: 0\n" +
                "TestByte: 0\n" +
                "TestDouble: 1.0E-7\n" +
                "TestFloat: 1.0E-4\n" +
                "TestLong: 1684654679684\n" +
                "TestChar: c\n" +
                "TestInjectUpdate: 'true'\n" +
                "TestInt: 0\n");
    }
}
