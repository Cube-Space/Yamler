package net.cubespace.Yamler.Config;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Config extends MapConfigMapper implements IConfig {
    private boolean save = false;

    public Config() {

    }

    public Config(String filename, String ... header) {
        CONFIG_FILE = new File(filename + (filename.endsWith(".yml") ? "" : ".yml"));
        CONFIG_HEADER = header;
    }

    @Override
    public void save() throws InvalidConfigurationException {
        if (CONFIG_FILE == null) {
            throw new IllegalArgumentException("Saving a config without given File");
        }

        if (root == null) {
            root = new ConfigSection();
        }

        clearComments();

        internalSave(getClass());
        saveToYaml();
        internalLoad(getClass());
    }

    private void internalSave(Class clazz) throws InvalidConfigurationException {
        if (!clazz.getSuperclass().equals(Config.class)) {
            internalSave(clazz.getSuperclass());
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (doSkip(field)) continue;

            String path = (CONFIG_MODE.equals(ConfigMode.DEFAULT)) ? field.getName().replaceAll("_", ".") : field.getName();

            ArrayList<String> comments = new ArrayList<>();
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof Comment) {
                    Comment comment = (Comment) annotation;
                    comments.add(comment.value());

                }

                if (annotation instanceof Comments) {
                    Comments comment = (Comments) annotation;
                    comments.addAll(Arrays.asList(comment.value()));
                }
            }

            if (field.isAnnotationPresent(Path.class)) {
                Path path1 = field.getAnnotation(Path.class);
                path = path1.value();
            }

            if (comments.size() > 0) {
                for (String comment : comments) {
                    addComment(path, comment);
                }
            }

            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }

            try {
                converter.toConfig(this, field, root, path);
            } catch (Exception e) {
                if (!skipFailedObjects) {
                    throw new InvalidConfigurationException("Could not save the Field", e);
                }
            }
        }
    }

    @Override
    public void save(File file) throws InvalidConfigurationException {
        if (file == null) {
            throw new IllegalArgumentException("File argument can not be null");
        }

        CONFIG_FILE = file;
        save();
    }

    @Override
    public void init() throws InvalidConfigurationException {
        if (!CONFIG_FILE.exists()) {
            if (CONFIG_FILE.getParentFile() != null)
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
        if (file == null) {
            throw new IllegalArgumentException("File argument can not be null");
        }

        CONFIG_FILE = file;
        init();
    }

    @Override
    public void reload() throws InvalidConfigurationException {
        loadFromYaml();
        internalLoad(getClass());

        if (save) {
            save();
            save = false;
        }
    }

    @Override
    public void load() throws InvalidConfigurationException {
        if (CONFIG_FILE == null) {
            throw new IllegalArgumentException("Loading a config without given File");
        }

        loadFromYaml();
        update(root);
        internalLoad(getClass());

        if (save) {
            save();
            save = false;
        }
    }

    private void internalLoad(Class clazz) throws InvalidConfigurationException {
        if (!clazz.getSuperclass().equals(Config.class)) {
            internalLoad(clazz.getSuperclass());
        }

        for (Field field : clazz.getDeclaredFields()) {
            if (doSkip(field)) continue;

            String path = (CONFIG_MODE.equals(ConfigMode.DEFAULT)) ? field.getName().replaceAll("_", ".") : field.getName();

            if (field.isAnnotationPresent(Path.class)) {
                Path path1 = field.getAnnotation(Path.class);
                path = path1.value();
            }

            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }

            if (root.has(path)) {
                try {
                    converter.fromConfig(this, field, root, path);
                } catch (Exception e) {
                    throw new InvalidConfigurationException("Could not set field", e);
                }
            } else {
                try {
                    converter.toConfig(this, field, root, path);
                    save = true;
                } catch (Exception e) {
                    if (!skipFailedObjects) {
                        throw new InvalidConfigurationException("Could not get field", e);
                    }
                }
            }
        }
    }

    @Override
    public void load(File file) throws InvalidConfigurationException {
        if (file == null) {
            throw new IllegalArgumentException("File argument can not be null");
        }

        CONFIG_FILE = file;
        load();
    }
}
