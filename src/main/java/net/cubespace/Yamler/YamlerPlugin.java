package net.cubespace.Yamler;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class YamlerPlugin extends Plugin {
    public void onEnable() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            getLogger().info("Could not start metrics");
        }
    }
}
