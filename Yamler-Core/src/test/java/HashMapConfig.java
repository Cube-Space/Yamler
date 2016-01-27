import net.cubespace.Yamler.Config.YamlConfig;

import java.util.HashMap;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class HashMapConfig extends YamlConfig {
    public HashMap<String, HashMap<String, HashMap<String, String>>> TestHashMap = new HashMap<String, HashMap<String, HashMap<String, String>>>(){{
        put("test", new HashMap<String, HashMap<String, String>>(){{
            put("test", new HashMap<String, String>(){{
                put("test1", "tesw");
            }});
        }});
    }};

    public HashMap<String, Position> TestMap1 = new HashMap<String, Position>(){{
        put("test", new Position());
        put("test1", new Position());
    }};
}
