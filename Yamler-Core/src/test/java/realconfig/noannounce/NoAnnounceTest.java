package realconfig.noannounce;

import base.BaseTest;
import base.Util;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import realconfig.noannounce.config.AnnouncerConfig;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class NoAnnounceTest extends BaseTest {
    @BeforeSuite
    public void setup() throws Exception {
        config = new AnnouncerConfig();
        filename = "noAnnounceAnnouncerConfig.yml";

        before();
    }

    @Test(priority = 1)
    public void onInit() throws Exception {
        config.init(file);

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents, "# Order, either sequential or random\n" +
                "order: sequential\n" +
                "# The server that should be asked for permissions (Most likely the hub server)\n" +
                "permissionServer: lobby\n" +
                "# How often the permissions cache is cleared in minutes. (0=never)\n" +
                "permissionCacheTime: 0\n" +
                "# A list of announcements (See spigot page for usage)\n" +
                "servers: {}\n" +
                "nonannouncements: {}\n");
    }
}
