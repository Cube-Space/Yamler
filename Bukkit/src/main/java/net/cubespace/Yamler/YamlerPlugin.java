package net.cubespace.Yamler;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class YamlerPlugin extends JavaPlugin {
    public void onEnable() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            e.printStackTrace();
            getLogger().info("Could not start metrics");
        }
    }
}
