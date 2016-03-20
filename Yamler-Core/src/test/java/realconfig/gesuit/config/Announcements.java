package realconfig.gesuit.config;


import net.cubespace.Yamler.Config.YamlConfig;
import realconfig.gesuit.config.sub.AnnouncementEntry;

import java.util.HashMap;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Announcements extends YamlConfig {
    public Boolean Enabled = true;
    public HashMap<String, AnnouncementEntry> Announcements = new HashMap<>();
}
