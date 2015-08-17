package converter;

import base.BaseTest;
import converter.config.ItemStackTestConfig;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.cubespace.Yamler.Converter.ItemStack;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * A basic ItemStack converter test to ensure the proper saving and
 * loading of a ItemStack.
 *
 * @author bibo38
 * @see ItemStackTestConfig
 * @see ItemStack
 */
public class ItemStackConverterTest extends BaseTest {
    /**
     * The setup method, which creates the configuration file
     * and the Configuration used for the following tests.
     *
     * @throws Exception
     */
    @Override
    @BeforeSuite
    public void setup() throws Exception {
        config = new ItemStackTestConfig();
        filename = "itemstackConverterTest.yml";

        before();
    }

    /**
     * A basic test to ensure the proper loading and saving of the Config.
     *
     * @throws InvalidConfigurationException If an configuration error occurs
     */
    @Test
    public void testInitLoad() throws InvalidConfigurationException {
        config.init(file);
        config.load();
    }
}
