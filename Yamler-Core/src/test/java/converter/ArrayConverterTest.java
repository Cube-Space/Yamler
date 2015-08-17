package converter;

import base.BaseTest;
import converter.config.ArrayTestConfig;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * This test uses the {@link ArrayTestConfig} to check that
 * the {@link net.cubespace.Yamler.Config.Converter.Array} converter
 * works in the correct way.
 *
 * @author bibo38
 * @see ArrayTestConfig The used config
 */
public class ArrayConverterTest extends BaseTest {
    /**
     * Sets the Test up, to provide the used configuration
     * and the filename for the test configuration.
     *
     * @throws Exception
     */
    @Override
    @BeforeSuite
    public void setup() throws Exception {
        config = new ArrayTestConfig();
        filename = "arrayConverterTest.yml";

        before();
    }

    /**
     * A basic test to confirm, that the used Config can be saved and loaded.
     *
     * @throws InvalidConfigurationException If the configuration cannot be loaded
     */
    @Test
    public void testInitLoad() throws InvalidConfigurationException {
        config.init(file);
        config.load();
    }
}
