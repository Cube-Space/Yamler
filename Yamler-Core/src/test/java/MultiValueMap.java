import base.Util;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class MultiValueMap {
    private MultiValueMapConfig multiValueMapConfig;
    private File file;

    @BeforeSuite
    public void before() {
        multiValueMapConfig = new MultiValueMapConfig();

        file = new File("temp", "multiValueMapConfig.yml");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    @org.testng.annotations.Test(priority = 1)
    public void initNull() throws InvalidConfigurationException, IOException {
        multiValueMapConfig.init(file);

        String fileContents = Util.readFile(file);
        System.out.println(fileContents);

        Assert.assertEquals(fileContents.replace("\r", ""), "items:\n" +
                "  example:\n" +
                "  - BOOK\n" +
                "  - WRITTEN_BOOK\n" +
                "servers:\n" +
                "  default:\n" +
                "    item:\n" +
                "      name: '&6Server Name'\n" +
                "      type: ENCHANTED_BOOK\n" +
                "    servers:\n" +
                "    - server_one\n" +
                "    - server_two\n" +
                "intMap:\n" +
                "  1: []\n");
    }

    @org.testng.annotations.Test(priority = 2)
    public void addEntryToMap() throws InvalidConfigurationException, IOException {
        multiValueMapConfig.getItems().put("test", new ArrayList<String>());
        multiValueMapConfig.getItems().get("example").add("Test");
        multiValueMapConfig.save();

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "items:\n" +
                "  test: []\n" +
                "  example:\n" +
                "  - BOOK\n" +
                "  - WRITTEN_BOOK\n" +
                "  - Test\n" +
                "servers:\n" +
                "  default:\n" +
                "    item:\n" +
                "      name: '&6Server Name'\n" +
                "      type: ENCHANTED_BOOK\n" +
                "    servers:\n" +
                "    - server_one\n" +
                "    - server_two\n" +
                "intMap:\n" +
                "  1: []\n");
    }

    @org.testng.annotations.Test(priority = 3)
    public void loadConfig() throws InvalidConfigurationException {
        MultiValueMapConfig multiValueMapConfig1 = new MultiValueMapConfig();
        multiValueMapConfig1.load(file);

        Assert.assertEquals(multiValueMapConfig1.getItems().size(), 2);
        Assert.assertTrue(multiValueMapConfig1.getItems().containsKey("example"));
        Assert.assertEquals(multiValueMapConfig1.getItems().get("example").size(), 3);
        Assert.assertTrue(multiValueMapConfig1.getIntMap().keySet().contains(1));
    }
}
