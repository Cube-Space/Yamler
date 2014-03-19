package net.cubespace.Yamler.Config;

import net.cubespace.Yamler.Config.Converter.Converter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class MapConfigMapper extends YamlConfigMapper {
    public Map saveToMap() throws Exception {
        Map<String, Object> returnMap = new HashMap<>();

        for (Field field : getClass().getDeclaredFields()) {
            if (doSkip(field)) continue;

            String path = (CONFIG_MODE.equals(ConfigMode.DEFAULT)) ? field.getName().replaceAll("_", ".") : field.getName();

            if (field.isAnnotationPresent(Path.class)) {
                Path path1 = field.getAnnotation(Path.class);
                path = path1.value();
            }

            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }

            try {
                returnMap.put(path, field.get(this));
            } catch (IllegalAccessException e) { }
        }

        Converter mapConverter = converter.getConverter(Map.class);
        return (Map) mapConverter.toConfig(HashMap.class, returnMap, null);
    }

    public void loadFromMap(Map section) throws NoSuchFieldException, IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            if (doSkip(field)) continue;

            String path = (CONFIG_MODE.equals(ConfigMode.DEFAULT)) ? field.getName().replaceAll("_", ".") : field.getName();

            if (field.isAnnotationPresent(Path.class)) {
                Path path1 = field.getAnnotation(Path.class);
                path = path1.value();
            }

            if(Modifier.isPrivate(field.getModifiers()))
                field.setAccessible(true);

            field.set(this, section.get(path));
        }
    }
}
