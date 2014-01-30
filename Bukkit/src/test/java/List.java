import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;

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

        assertEquals(fileContents.replace("\r", ""), "TestList:\n" +
                "- Test: Test\n");
    }

    @Test(priority = 2)
    public void changeBoolean() throws InvalidConfigurationException, IOException {
        listConfig.TestList.add(new ListSubConfig());
        listConfig.save();

        String fileContents = Util.readFile(file);

        assertEquals(fileContents.replace("\r", ""), "TestList:\n" +
                "- Test: Test\n" +
                "- Test: Test\n");
    }

    @Test(priority = 3)
    public void loadConfig() throws InvalidConfigurationException {
        ListConfig listConfig1 = new ListConfig();
        listConfig1.load(file);

        assertEquals(listConfig1.TestList.size(), 2);
    }
}
