package realconfig.servermenu.config;

import net.cubespace.Yamler.Config.YamlConfig;

import java.util.ArrayList;
import java.util.List;

public class Menus extends YamlConfig {
    private String title;
    private List<Servers> servers = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setServers(List<Servers> servers) {
        this.servers = servers;
    }
}
