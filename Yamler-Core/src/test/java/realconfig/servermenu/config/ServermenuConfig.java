package realconfig.servermenu.config;

import net.cubespace.Yamler.Config.YamlConfig;

import java.util.ArrayList;
import java.util.HashMap;

public class ServermenuConfig extends YamlConfig {
    public ServermenuConfig() {
        ArrayList<Servers> servers = new ArrayList<>();

        Servers servers1 = new Servers();

        servers1.setServer("TDM1");
        servers1.setDisplayMotd(false);
        servers1.setDisplayPlayers(true);
        servers1.setHostName("guerra.year4000.net");
        servers1.setPort(26602);

        servers.add(servers1);

        Menus menus1 = new Menus();

        menus1.setTitle("Game Servers");
        menus1.setServers(servers);

        menus.put("Game Servers", menus1);
    }

    private HashMap<String, Menus> menus = new HashMap<>();
}
