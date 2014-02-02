package net.cubespace.Yamler.Config;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Converter {
    public static void fromConfig(Config config, Field field, ConfigSection root, String path) throws Exception {
        if (Map.class.isAssignableFrom(field.getType())) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            field.set(config, convertToMap(field, parameterizedType, root, path));
        } else if (field.getType().isArray()) {
            setList(config, field, field.getType().getComponentType(), (List) root.get(path));
        } else if (Config.class.isAssignableFrom(field.getType())) {
            Map section = root.getMap(path);
            Config obj = (Config) field.getType().cast(field.getType().newInstance());
            obj.loadFromMap(section);
            field.set(config, obj);
        } else if(List.class.isAssignableFrom(field.getType())) {
            List newList = new ArrayList();
            try {
                newList = ((List) ((Class) ((ParameterizedType) field.getGenericType()).getRawType()).newInstance());
            } catch (Exception e) {}

            List values = root.get(path);

            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            if(Config.class.isAssignableFrom((Class) parameterizedType.getActualTypeArguments()[0])) {
                for(int i = 0; i < values.size(); i++) {
                    Config config1 = (Config) ((Class) parameterizedType.getActualTypeArguments()[0]).newInstance();
                    config1.loadFromMap((Map)values.get(i));
                    newList.add(config1);
                }
            } else {
                newList = values;
            }

            field.set(config, newList);
        } else {
            switch(field.getType().getSimpleName()) {
                case "short":
                    field.set(config, (new Integer((int)root.get(path)).shortValue()));
                    break;
                case "byte":
                    field.set(config, (new Integer((int)root.get(path)).byteValue()));
                    break;
                case "float":
                    field.set(config, (new Double((double)root.get(path)).floatValue()));
                    break;
                case "char":
                    field.set(config, ((String)root.get(path)).charAt(0));
                    break;
                default:
                    field.set(config, root.get(path));
            }
        }
    }

    public static Object convertToMap(Field field, ParameterizedType parameterizedType, ConfigSection root, String path) throws Exception {
        Map map = ((Map) ((Class) ((ParameterizedType) field.getGenericType()).getRawType()).newInstance());

        if(parameterizedType.getActualTypeArguments()[1] instanceof ParameterizedTypeImpl) {
            for(Map.Entry<?, ?> entry : ((Map<?, ?>) root.getMap(path)).entrySet()) {
                map.put(entry.getKey(), convertToMap(field, (ParameterizedType) parameterizedType.getActualTypeArguments()[1], root, path + "." + entry.getKey()));
            }
        } else {
            return root.getMap(path);
        }

        return map;
    }

    public static void toConfig(Config config, Field field, ConfigSection root, String path) throws Exception {
        Object obj = field.get(config);

        if(obj instanceof Config) {
            root.set(path, ((Config) obj).saveToMap());
        } else if(obj instanceof List) {
            List list = (List) obj;
            List newList = new ArrayList();
            try {
                newList = ((List) ((Class) ((ParameterizedType) field.getGenericType()).getRawType()).newInstance());
            } catch (Exception e) {}

            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            if(Config.class.isAssignableFrom((Class) parameterizedType.getActualTypeArguments()[0])) {
                for(int i = 0; i < list.size(); i++) {
                    if(list.get(i) instanceof Config)
                        newList.add(((Config) list.get(i)).saveToMap());
                    else
                        newList.add(list.get(i));
                }
            } else {
                newList = list;
            }

            root.set(path, newList);
        } else {
            root.set(path, obj);
        }
    }

    private static <T> void setList(Config config, Field field, Class<T> type, List list) {
        try {
            T[] array = (T[]) Array.newInstance(type, list.size());
            field.set(config, list.toArray(array));
        } catch (IllegalAccessException e) { }
    }
}
