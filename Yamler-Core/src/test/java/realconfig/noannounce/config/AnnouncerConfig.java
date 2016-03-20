package realconfig.noannounce.config;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.YamlConfig;

import java.util.ArrayList;
import java.util.HashMap;

public class AnnouncerConfig extends YamlConfig {
    @Comment("Order, either sequential or random")
    public String order = "sequential";
    @Comment("The server that should be asked for permissions (Most likely the hub server)")
    public String permissionServer = "lobby";
    @Comment("How often the permissions cache is cleared in minutes. (0=never)")
    public int permissionCacheTime = 0;

    @Comment("A list of announcements (See spigot page for usage)")
    public HashMap<String, MessageMap> servers = new HashMap<String, MessageMap>();


    public static class MessageMap extends YamlConfig {
        public ArrayList<String> servers;
        public int offset;
        public int delay;
        public String permission;
        public ArrayList<Announcement> announcements = new ArrayList<Announcement>();
    }
    public static class Announcement extends YamlConfig {
        public String type;
        public String message;

        public Announcement clone(){
            Announcement clone = new Announcement();
            clone.type = type + "";
            clone.message = message +"";
            return clone;
        }
    }

    public HashMap<String, BroadcastMap> nonannouncements = new HashMap<String, BroadcastMap>();

    public static class BroadcastMap extends YamlConfig {
        public ArrayList<String> servers;
        public String permission;
        public Announcement announcement;
    }

}
