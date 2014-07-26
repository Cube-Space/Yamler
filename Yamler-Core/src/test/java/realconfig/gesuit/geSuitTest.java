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

        if (!((Announcements) config).Announcements.containsKey("test")) {
            AnnouncementEntry announcementEntry = new AnnouncementEntry();
            announcementEntry.Interval = 300;
            announcementEntry.Messages.add("&4Welcome to the server!");
            announcementEntry.Messages.add("&aDon't forget to check out our website");

            ((Announcements) config).Announcements.put("test", announcementEntry);
        }

        if (!((Announcements) config).Announcements.containsKey("test1")) {
            AnnouncementEntry announcementEntry = new AnnouncementEntry();
            announcementEntry.Interval = 300;
            announcementEntry.Messages.add("&4Welcome to the server!");
            announcementEntry.Messages.add("&aDon't forget to check out our website");

            ((Announcements) config).Announcements.put("test1", announcementEntry);
        }

        config.save();
    }

    @Test(priority = 3)
    public void checkYMLContents() throws Exception {
        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents, "Enabled: true\n" +
                "Announcements:\n" +
                "  test:\n" +
                "    Messages:\n" +
                "    - '&4Welcome to the server!'\n" +
                "    - '&aDon''t forget to check out our website'\n" +
                "    Interval: 300\n" +
                "  global:\n" +
                "    Messages:\n" +
                "    - '&4Welcome to the server!'\n" +
                "    - '&aDon''t forget to check out our website'\n" +
                "    Interval: 300\n" +
                "  test1:\n" +
                "    Messages:\n" +
                "    - '&4Welcome to the server!'\n" +
                "    - '&aDon''t forget to check out our website'\n" +
                "    Interval: 300\n");
    }

    @Test(priority = 4)
    public void checkConfig() throws Exception {
        Announcements announcements = new Announcements();
        announcements.init(file);
        announcements.save();

        Assert.assertTrue(announcements.Announcements.get("global") != null);
        Assert.assertTrue(announcements.Announcements.get("global") instanceof AnnouncementEntry);
        Assert.assertTrue(announcements.Announcements.get("global").Messages instanceof List);
        Assert.assertEquals(announcements.Announcements.get("global").Messages.size(), 2);
        Assert.assertEquals(announcements.Announcements.get("global").Interval, (Integer) 300);
    }
}
