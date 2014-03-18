package realconfig.gesuit;

import base.BaseTest;
import base.Util;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import realconfig.gesuit.config.Announcements;
import realconfig.gesuit.config.sub.AnnouncementEntry;

import java.util.List;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class geSuitTest extends BaseTest {
    @BeforeSuite
    public void setup() throws Exception {
        config = new Announcements();
        filename = "geSuitAnnouncements.yml";

        before();
    }

    @Test(priority = 1)
    public void onInit() throws Exception {
        config.init(file);

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents,
                "Enabled: true\n" +
                "Announcements: {}\n");
    }

    @Test(priority = 2)
    public void addAnnouncement() throws Exception {
        if (!((Announcements) config).Announcements.containsKey("global")) {
            AnnouncementEntry announcementEntry = new AnnouncementEntry();
            announcementEntry.Interval = 300;
            announcementEntry.Messages.add("&4Welcome to the server!");
            announcementEntry.Messages.add("&aDon't forget to check out our website");

            ((Announcements) config).Announcements.put("global", announcementEntry);
        }

        config.save();
    }

    @Test(priority = 3)
    public void checkYMLContents() throws Exception {
        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents,
                "Enabled: true\n" +
                "Announcements:\n" +
                "  global:\n" +
                "    Messages:\n" +
                "    - '&4Welcome to the server!'\n" +
                "    - '&aDon''t forget to check out our website'\n" +
                "    Interval: 300\n");
    }

    @Test(priority = 4)
    public void checkConfig() throws Exception {
        config.reload();

        Assert.assertTrue(((Announcements) config).Announcements.get("global") != null);
        Assert.assertTrue(((Announcements) config).Announcements.get("global") instanceof AnnouncementEntry);
        Assert.assertTrue(((Announcements) config).Announcements.get("global").Messages instanceof List);
        Assert.assertEquals(((Announcements) config).Announcements.get("global").Messages.size(), 2);
        Assert.assertEquals(((Announcements) config).Announcements.get("global").Interval, (Integer) 300);
    }
}
