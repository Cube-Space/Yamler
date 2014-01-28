import Config.ListConfig;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class List {
    private ListConfig listConfig;
    private File file;

    @BeforeSuite
    public void before() {
        listConfig = new ListConfig();

        file = new File("temp", "listConfig.yml");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    @Test(priority = 1)
    public void initNull() throws InvalidConfigurationException, IOException {
        listConfig.init(file);

        String fileContents = Util.readFile(file);

        System.out.println(fileContents);

        /*assertEquals(fileContents.replace("\r", ""), "TestBoolean: false\n" +
                "TestInt: 0\n" +
                "TestShort: 0\n" +
                "TestByte: 0\n" +
                "TestDouble: 1.0E-7\n" +
                "TestFloat: 1.0E-4\n" +
                "TestLong: 1684654679684\n" +
                "TestChar: c\n"); */
    }
}
