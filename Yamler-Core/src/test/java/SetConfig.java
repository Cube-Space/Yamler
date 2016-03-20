import net.cubespace.Yamler.Config.YamlConfig;

import java.util.*;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class SetConfig extends YamlConfig {
    public java.util.Set<String> StringSet = new HashSet<String>(){{
        add("Test");
    }};
}
