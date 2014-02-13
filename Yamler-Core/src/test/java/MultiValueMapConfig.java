import net.cubespace.Yamler.Config.Config;

import java.util.*;
import java.util.HashMap;
import java.util.List;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class MultiValueMapConfig extends Config {
    private java.util.HashMap<String, java.util.List<String>> items = new java.util.HashMap<String, java.util.List<String>>() {{
        //  Defaults
        java.util.List<String> example = new ArrayList<String>() {{
            add("BOOK");
            add("WRITTEN_BOOK");
        }};

        put("example", example);
    }};

    public HashMap<String, List<String>> getItems() {
        return items;
    }

    public void setItems(HashMap<String, List<String>> items) {
        this.items = items;
    }
}
