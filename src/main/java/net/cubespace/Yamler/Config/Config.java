package net.cubespace.Yamler.Config;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Config extends YamlConfigMapper implements IConfig {
    @Override
    public void save() throws InvalidConfigurationException {
        if(CONFIG_FILE == null) {
            throw new IllegalArgumentException("Saving a config without given File");
        }

        for (Field field : getClass().getDeclaredFields()) {
            String path = field.getName().replaceAll("_", ".");

            if (doSkip(field)) continue;

            try {
                root.set(path, field.get(this));
            } catch (IllegalAccessException e) {
                throw new InvalidConfigurationException("Could not save the Field", e);
            }
        }

        saveToYaml();
    }

    @Override
    public void save(File file) throws InvalidConfigurationException {
        if(file == null) {
            throw new IllegalArgumentException("File argument can not be null");
        }

        CONFIG_FILE = file;
        save();
    }

    @Override
    public void init() throws InvalidConfigurationException {
        if(!CONFIG_FILE.exists()) {
            CONFIG_FILE.getParentFile().mkdirs();
            try {
                CONFIG_FILE.createNewFile();
                save();
            } catch (IOException e) {
                throw new InvalidConfigurationException("Could not create new empty Config", e);
            }
        } else {
            load();
        }
    }

    @Override
    public void init(File file) throws InvalidConfigurationException {
        if(file == null) {
            throw new IllegalArgumentException("File argument can not be null");
        }

        CONFIG_FILE = file;
        init();
    }

    @Override
    public void reload() throws InvalidConfigurationException {
        reloadFromYaml();
    }

    @Override
    public void load() throws InvalidConfigurationException {
        if(CONFIG_FILE == null) {
            throw  new IllegalArgumentException("Loading a config without given File");
        }

        loadFromYaml();

        boolean save = false;
        for (Field field : getClass().getDeclaredFields()) {
            String path = field.getName().replaceAll("_", ".");

            if (doSkip(field)) continue;

            if(root.has(path)) {
                try {
                    field.set(this, root.get(path));
                } catch (IllegalAccessException e) {
                    throw new InvalidConfigurationException("Could not set field", e);
                }
            } else {
                try {
                    root.set(path, field.get(this));
                    save = true;
                } catch (IllegalAccessException e) {
                    throw new InvalidConfigurationException("Could not get field", e);
                }
            }
        }

        if(save) {
            save();
        }
    }

    @Override
    public void load(File file) throws InvalidConfigurationException {
        if(file == null) {
            throw new IllegalArgumentException("File argument can not be null");
        }

        CONFIG_FILE = file;
        load();
    }

    protected boolean doSkip(Field field) {
        return Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())
                || Modifier.isFinal(field.getModifiers()) || !Modifier.isPublic(field.getModifiers());
    }
}
