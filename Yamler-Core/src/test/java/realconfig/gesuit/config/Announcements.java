package realconfig.gesuit.config;


import net.cubespace.Yamler.Config.Config;
import realconfig.gesuit.config.sub.AnnouncementEntry;

import java.util.HashMap;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Announcements extends Config {
    public Boolean Enabled = true;
    public HashMap<String, AnnouncementEntry> Announcements = new HashMap<>();
}
