package net.cubespace.Yamler.Config;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class YamlConfigMapper {
    protected transient File CONFIG_FILE = null;
    protected transient String[] CONFIG_HEADER = null;

    private final DumperOptions yamlOptions = new DumperOptions();
    private final Representer yamlRepresenter = new Representer();
    private final Yaml yaml = new Yaml(new Constructor(), yamlRepresenter, yamlOptions);
    protected ConfigSection root = new ConfigSection();

    protected YamlConfigMapper() {
        yamlOptions.setIndent(2);
        yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yamlRepresenter.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    }

    protected void loadFromYaml() throws InvalidConfigurationException {
        try {
            convertMapsToSections((Map<?, ?>) yaml.load(new FileReader(CONFIG_FILE)), root);
        } catch (IOException|ClassCastException|YAMLException e) {
            throw new InvalidConfigurationException("Could not load YML", e);
        }
    }

    protected void reloadFromYaml() throws InvalidConfigurationException {
        root = new ConfigSection();

        loadFromYaml();
    }

    private void convertMapsToSections(Map<?, ?> input, ConfigSection section) {
        for (Map.Entry<?, ?> entry : input.entrySet()) {
            String key = entry.getKey().toString();
            Object value = entry.getValue();

            if (value instanceof Map) {
                convertMapsToSections((Map<?, ?>) value, section.create(key));
            } else {
                section.set(key, value);
            }
        }
    }

    protected void saveToYaml() throws InvalidConfigurationException {
        try(FileWriter fileWriter = new FileWriter(CONFIG_FILE)) {
            if(CONFIG_HEADER != null) {
                for(String line : CONFIG_HEADER) {
                    fileWriter.write(line + "\n");
                }

                fileWriter.write("\n");
            }

            String yamlString = yaml.dump(root.getValues(true));
            fileWriter.write(yamlString);
        } catch (IOException e) {
            throw new InvalidConfigurationException("Could not save YML", e);
        }
    }
}
