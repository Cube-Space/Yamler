import net.cubespace.Yamler.Config.InvalidConfigurationException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class RunAll {
    public static void main(String[] args) {
        CustomConfigClasses customConfigClasses = new CustomConfigClasses();
        try {
            customConfigClasses.init();
        } catch (InvalidConfigurationException e) {

        }
    }
}
