package update;

import base.BaseTest;
import base.Util;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class UpdateConfig extends BaseTest {
    @BeforeSuite
    public void setup() throws Exception {
        filename = "updateConfig.yml";

        file = new File(filename);
        if(file.exists()) {
            file.delete();
        }

        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("IsEnabled: true\n");
        } catch(Exception e) {
            throw e;
        }

        config = new UpdateConfigConfig();
    }

    @Test(priority = 1)
    public void init() throws Exception {
        config.init(file);
    }

    @Test(priority = 2)
    public void save() throws Exception {
        String fileContents = Util.readFile(file);

        Assert.assertEquals( fileContents.replace( "\r", "" ), "IsEnabled: true\n" +
                "Enabled: false\n" +
                "restriction:\n" +
                "  bedwars.restriction.player: 1\n" +
                "  bedwars.restriction.premium: 3\n" );
    }

    @Test(priority = 3)
    public void load() throws Exception {
        UpdateConfigConfig config1 = new UpdateConfigConfig();
        config1.load(file);

        Assert.assertEquals( config1.restriction, new HashMap<String, Integer>(){{
            put( "bedwars.restriction.player", 1 );
            put( "bedwars.restriction.premium", 3 );
        }} );
    }
}
