import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.Path;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 *
 * This Config example shows how you can use the Path annotation to tell Yamler how the YAML stored Key should look like.
 * *
 * This example will use the public Access Pattern. You can use Getter/Setters if you like
 */
public class PathAnnotation extends Config {
    @Path("config-with-dash")
    public Boolean Test = true;
}

/**
 * This Config generates this YML:
 *
 * config-with-dash: false
 */
