package net.cubespace.Yamler.Config;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ConfigSection {
    private String key;
    private String fullPath;
    private ConfigFastLookupCache configFastLookupCache;
    protected final Map<Object, Object> map = new LinkedHashMap<>();

    public ConfigSection() {
        this.key = "";
        this.fullPath = "";

        configFastLookupCache = new ConfigFastLookupCache();
    }

    public ConfigSection(ConfigSection root, String key) {
        this.key = key;
        this.fullPath = (!root.fullPath.equals("")) ? root.fullPath + "." + key : key;
        this.configFastLookupCache = root.configFastLookupCache;
        configFastLookupCache.set(fullPath, this);
    }

    public ConfigSection create(String path) {
        if(path == null) {
            throw new IllegalArgumentException("Cannot create section at empty path");
        }

        //Be sure to have all ConfigSections down the Path
        int i1 = -1, i2;
        ConfigSection section = this;
        while ((i1 = path.indexOf('.', i2 = i1 + 1)) != -1) {
            String node = path.substring(i2, i1);
            ConfigSection subSection = section.getConfigSection(node);

            //This subsection does not exists create one
            if (subSection == null) {
                section = section.create(node);
            } else {
                section = subSection;
            }
        }

        String key = path.substring(i2);
        if (section == this) {
            ConfigSection result = new ConfigSection(this, key);
            map.put(key, result);
            return result;
        }

        return section.create(key);
    }

    private ConfigSection getConfigSection(String node) {
        return (map.containsKey(node) && map.get(node) instanceof ConfigSection) ? (ConfigSection) map.get(node) : null;
    }

    public void set(String path, Object value) {
        if(path == null) {
            throw new IllegalArgumentException("Cannot set a value at empty path");
        }

        //Be sure to have all ConfigSections down the Path
        int i1 = -1, i2;
        ConfigSection section = this;
        while ((i1 = path.indexOf('.', i2 = i1 + 1)) != -1) {
            String node = path.substring(i2, i1);
            ConfigSection subSection = section.getConfigSection(node);

            if (subSection == null) {
                section = section.create(node);
            } else {
                section = subSection;
            }
        }

        String key = path.substring(i2);
        if (section == this) {
            if (value == null) {
                map.remove(key);
            } else {
                map.put(key, value);
                configFastLookupCache.set((!fullPath.equals("")) ? fullPath + "." + key : key, value);
            }
        } else {
            section.set(key, value);
        }
    }

    protected void mapChildrenValues(Map<Object, Object> output, ConfigSection section, boolean deep) {
        if (section != null) {
             for (Map.Entry<Object, Object> entry : section.map.entrySet()) {
                if (entry.getValue() instanceof ConfigSection) {
                    Map<Object, Object> result = new LinkedHashMap<>();
                    output.put(entry.getKey(), result);
                    if (deep) {
                        mapChildrenValues(result, (ConfigSection) entry.getValue(), true);
                    }
                } else {
                    output.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            Map<Object, Object> values = section.getValues(deep);

            for (Map.Entry<Object, Object> entry : values.entrySet()) {
                output.put(section.fullPath + "." + entry.getKey(), entry.getValue());
            }
        }
    }

    public Map getMap(String path) {
        return ((ConfigSection) configFastLookupCache.get(path)).getRawMap();
    }

    public Map<Object, Object> getValues(boolean deep) {
        Map<Object, Object> result = new LinkedHashMap<>();
        mapChildrenValues(result, this, deep);
        return result;
    }

    public void niceOutput() {
        configFastLookupCache.niceOutput();
    }

    public boolean has(String path) {
        return configFastLookupCache.has(path);
    }

    public <T> T get(String path) {
        return configFastLookupCache.get(path);
    }

    public Map getRawMap() {
        return map;
    }
}
