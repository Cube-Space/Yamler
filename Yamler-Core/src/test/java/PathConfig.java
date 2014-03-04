import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.Path;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class PathConfig extends Config {
    @Comment("test")
    @Path("config-with-dash")
    public Boolean Test = true;
}
