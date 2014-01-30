import net.cubespace.Yamler.Config.Config;

import java.util.ArrayList;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ListConfig extends Config {
    public ArrayList<ListSubConfig> TestList = new ArrayList<ListSubConfig>() {{
        add(new ListSubConfig());
    }};
}
