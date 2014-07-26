package realconfig.servermenu;

import base.BaseTest;
import base.Util;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import realconfig.servermenu.config.ServermenuConfig;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ServermenuTest extends BaseTest {
    @BeforeSuite
    public void setup() throws Exception {
        config = new ServermenuConfig();
        filename = "serverMenuConfig.yml";

        before();
    }

    @Test(priority = 1)
    public void onInit() throws Exception {
        config.init(file);

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents, "menus:\n" +
                "  Game Servers:\n" +
                "    servers:\n" +
                "    - displayPlayers: true\n" +
                "      server: TDM1\n" +
                "      hostName: guerra.year4000.net\n" +
                "      port: 26602\n" +
                "      timeout: null\n" +
                "      displayMotd: false\n" +
                "    title: Game Servers\n");
    }
}
