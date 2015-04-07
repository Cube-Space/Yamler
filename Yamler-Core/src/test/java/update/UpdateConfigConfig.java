package update;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */

import net.cubespace.Yamler.Config.Config;

import java.util.HashMap;
import java.util.Map;

public class UpdateConfigConfig extends Config {
    public Boolean Enabled = false;
    public Map<String, Integer> restriction = new HashMap<String, Integer>(){{
        put( "bedwars.restriction.player", 1 );
        put( "bedwars.restriction.premium", 3 );
    }};
}
