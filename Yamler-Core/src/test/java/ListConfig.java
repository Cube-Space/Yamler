import net.cubespace.Yamler.Config.YamlConfig;

import java.util.ArrayList;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ListConfig extends YamlConfig {
    public ArrayList<ListSubConfig> TestList = new ArrayList<ListSubConfig>() {{
        add(new ListSubConfig());
    }};
}
