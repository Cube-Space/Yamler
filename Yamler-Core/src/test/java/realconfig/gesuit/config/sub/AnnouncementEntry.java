package realconfig.gesuit.config.sub;

import net.cubespace.Yamler.Config.Config;

import java.util.ArrayList;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class AnnouncementEntry extends Config {
    public Integer Interval = 150;
    public ArrayList<String> Messages = new ArrayList<>();
}
