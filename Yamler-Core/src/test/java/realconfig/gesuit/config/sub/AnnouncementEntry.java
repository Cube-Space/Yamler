package realconfig.gesuit.config.sub;

import net.cubespace.Yamler.Config.YamlConfig;

import java.util.ArrayList;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class AnnouncementEntry extends YamlConfig {
    public Integer Interval = 150;
    public ArrayList<String> Messages = new ArrayList<>();
}
