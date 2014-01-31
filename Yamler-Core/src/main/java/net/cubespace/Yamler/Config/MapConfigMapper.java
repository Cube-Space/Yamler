package net.cubespace.Yamler.Config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class MapConfigMapper extends YamlConfigMapper {
    public Map saveToMap() {
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

    public void loadFromMap(Map section) throws NoSuchFieldException, IllegalAccessException {
        for(Map.Entry<String, Object> entry : ((Map<String, Object>) section).entrySet()) {
            String path = entry.getKey().replace(".", "_");

            Field field = this.getClass().getDeclaredField(path);

            if(Modifier.isPrivate(field.getModifiers()))
                field.setAccessible(true);

            field.set(this, entry.getValue());
        }
    }
}
