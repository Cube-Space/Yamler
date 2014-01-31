import net.cubespace.Yamler.Config.Config;

import java.util.HashMap;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class HashMapConfig extends Config {
    public HashMap<String, HashMap<String, HashMap<String, String>>> TestHashMap = new HashMap<String, HashMap<String, HashMap<String, String>>>(){{
        put("test", new HashMap<String, HashMap<String, String>>(){{
            put("test", new HashMap<String, String>(){{
                put("test1", "tesw");
            }});
        }});
    }};
}
