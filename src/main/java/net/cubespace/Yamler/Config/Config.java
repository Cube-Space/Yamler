package net.cubespace.Yamler.Config;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Config extends YamlConfigMapper implements IConfig {
    @Override
    public void save() throws InvalidConfigurationException {
        if (CONFIG_FILE == null) {
            throw new IllegalArgumentException("Saving a config without given File");
        }

        for (Field field : getClass().getDeclaredFields()) {
            String path = field.getName().replaceAll("_", ".");

            if (doSkip(field)) continue;

            for (Annotation annotation : field.getAnnotations()) {
                if (annotation instanceof Comment) {
                    Comment comment = (Comment) annotation;
                    addComment(path, comment.value());
                }

                if (annotation instanceof Comments) {
                    Comments comment = (Comments) annotation;

                    for (String comment1 : comment.value()) {
                        addComment(path, comment1);
                    }
                }
            }

            if(Modifier.isPrivate(field.getModifiers()))
                field.setAccessible(true);

            try {
                Object obj = field.get(this);

                if(obj instanceof Config) {
                    root.set(path, ((Config) obj).getAsMap());
                } else {
                    root.set(path, field.get(this));
                }
            } catch (IllegalAccessException e) {
                throw new InvalidConfigurationException("Could not save the Field", e);
            }
        }

        saveToYaml();
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
        reloadFromYaml();
    }

    @Override
    public void load() throws InvalidConfigurationException {
        if (CONFIG_FILE == null) {
            throw new IllegalArgumentException("Loading a config without given File");
        }

        loadFromYaml();

        boolean save = false;
        for (Field field : getClass().getDeclaredFields()) {
            String path = field.getName().replaceAll("_", ".");

            if (doSkip(field)) continue;

            if(Modifier.isPrivate(field.getModifiers()))
                field.setAccessible(true);

            if (root.has(path)) {
                try {
                    if (Map.class.isAssignableFrom(field.getType())) {
                        field.set(this, root.getMap(path));
                    } else if (field.getType().isArray()) {
                        setList(field, field.getType().getComponentType(), (List) root.get(path));
                    } else if (Config.class.isAssignableFrom(field.getType())) {
                        Map section = root.getMap(path);
                        Config obj = (Config) field.getType().cast(field.getType().newInstance());
                        obj.loadFromMap(section);
                    } else {
                        field.set(this, root.get(path));
                    }
                } catch (Exception e) {
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

        if (save) {
            save();
        }
    }

    private void loadFromMap(Map section) throws NoSuchFieldException, IllegalAccessException {
        for(Map.Entry<String, Object> entry : ((Map<String, Object>) section).entrySet()) {
            String path = entry.getKey().replace(".", "_");

            Field field = this.getClass().getDeclaredField(path);

            if(Modifier.isPrivate(field.getModifiers()))
                field.setAccessible(true);

            field.set(this, entry.getValue());
        }
    }

    private <T> void setList(Field field, Class<T> type, List list) {
        T[] array;
        try {
            array = (T[]) Array.newInstance(type, list.size());
            field.set(this, list.toArray(array));
        } catch (IllegalAccessException e) {

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

    protected boolean doSkip(Field field) {
        return Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())
                || Modifier.isFinal(field.getModifiers());
    }

    public Map getAsMap() {
        Map<String, Object> returnMap = new HashMap<>();

        for (Field field : getClass().getDeclaredFields()) {
            String path = field.getName().replaceAll("_", ".");

            if (doSkip(field)) continue;

            if(Modifier.isPrivate(field.getModifiers()))
                field.setAccessible(true);

            try {
                returnMap.put(path, field.get(this));
            } catch (IllegalAccessException e) {

            }
        }

        return returnMap;
    }
}
