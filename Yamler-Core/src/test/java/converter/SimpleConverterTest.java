package converter;

import base.BaseTest;
import base.Util;
import converter.config.SimpleObjectConfig;
import converter.customconverter.ObjectConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class SimpleConverterTest extends BaseTest {
    @BeforeSuite
    public void setup() throws Exception {
        config = new SimpleObjectConfig();
        config.addConverter(ObjectConverter.class);
        filename = "simpleConverterTest.yml";

        before();
    }

    @Test(priority = 1)
    public void onInit() throws Exception {
        config.init(file);

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents,
                "TestMap:\n" +
                "  test: test\n" +
                "TestSet:\n" +
                "- test\n");
    }
}
