package net.cubespace.Yamler;

import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class YamlerPlugin extends Plugin {
    public void onEnable() {
        TestConfig testConfig = new TestConfig(this);
        try {
            testConfig.init();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            getLogger().info("Could not start metrics");
        }
    }
}
