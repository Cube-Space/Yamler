import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.YamlConfig;
import net.cubespace.Yamler.Config.Path;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class PathConfig extends YamlConfig {
    @Comment("test")
    @Path("config-with-dash")
    public Boolean Test = true;
}
