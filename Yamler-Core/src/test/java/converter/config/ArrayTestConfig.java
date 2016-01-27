package converter.config;

import net.cubespace.Yamler.Config.YamlConfig;

/**
 * This test config is based on the Issues #16 and #17
 * It provides tests for (multidimensional) arrays of primitive types,
 * Strings and convertable types.
 *
 * @author bibo38
 * @see converter.ArrayConverterTest
 */
public class ArrayTestConfig extends YamlConfig {

    public class InnerConfig extends YamlConfig {
        public int x = 7;
    }

    public int[] data = { 1, 2, 3 };
    public double[][] multiArray = { { 1 }, { 2, 3 }, { 4 } };
    public InnerConfig[] configs = { new InnerConfig(),
            new InnerConfig(),
            new InnerConfig()
    };
    public String[] strings = { "Hello", "World", "!" };
}
