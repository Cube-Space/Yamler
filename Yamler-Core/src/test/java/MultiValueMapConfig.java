import net.cubespace.Yamler.Config.YamlConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class MultiValueMapConfig extends YamlConfig {
    private java.util.HashMap<String, java.util.List<String>> items = new java.util.HashMap<String, java.util.List<String>>() {{
        //  Defaults
        java.util.List<String> example = new ArrayList<String>() {{
            add("BOOK");
            add("WRITTEN_BOOK");
        }};

        put("example", example);
    }};

    private HashMap<String, HashMap<String, Object>> servers = new HashMap<String, HashMap<String, Object>>() {{
        // Default layout
        put("default", new HashMap<String, Object>() {{
            // Item layout
            put("item", new HashMap<String, Object>() {{
                put("name", "&6Server Name");
                put("type", "ENCHANTED_BOOK");
            }});

            // Server layout
            put("servers", new ArrayList<String>() {{
                add("server_one");
                add("server_two");
            }});
        }});
    }};

    private HashMap<Integer, ArrayList<String>> intMap = new HashMap<Integer, ArrayList<String>>() {{
        put(1, new ArrayList<String>());
    }};

    public HashMap<String, List<String>> getItems() {
        return items;
    }

    public void setItems(HashMap<String, List<String>> items) {
        this.items = items;
    }

    public HashMap<String, HashMap<String, Object>> getServers() {
        return servers;
    }

    public void setServers(HashMap<String, HashMap<String, Object>> servers) {
        this.servers = servers;
    }

    public HashMap<Integer, ArrayList<String>> getIntMap() {
        return intMap;
    }

    public void setIntMap(HashMap<Integer, ArrayList<String>> intMap) {
        this.intMap = intMap;
    }
}
