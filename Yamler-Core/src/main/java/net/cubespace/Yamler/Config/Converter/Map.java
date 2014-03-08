package net.cubespace.Yamler.Config.Converter;

import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.InternalConverter;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Map implements Converter {
    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) throws Exception {
        return obj;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        if (genericType != null) {

            java.util.Map map = ((java.util.Map) ((Class) genericType.getRawType()).newInstance());

            if(genericType.getActualTypeArguments().length == 2 && genericType.getActualTypeArguments()[1] instanceof ParameterizedTypeImpl) {
                Class keyClass = ((Class) genericType.getActualTypeArguments()[0]);

                java.util.Map<?, ?> map1 = (section instanceof java.util.Map) ? (java.util.Map) section : ((ConfigSection) section).getRawMap();
                for(java.util.Map.Entry<?, ?> entry : map1.entrySet()) {
                    Object key;

                    if (keyClass.equals(Integer.class)) {
                        key = Integer.valueOf((String) entry.getKey());
                    } else if (keyClass.equals(Short.class)) {
                        key = Short.valueOf((String) entry.getKey());
                    } else if (keyClass.equals(Byte.class)) {
                        key = Byte.valueOf((String) entry.getKey());
                    } else if (keyClass.equals(Float.class)) {
                        key = Float.valueOf((String) entry.getKey());
                    } else if (keyClass.equals(Double.class)) {
                        key = Double.valueOf((String) entry.getKey());
                    } else {
                        key = entry.getKey();
                    }

                    ParameterizedType parameterizedType = (ParameterizedType) genericType.getActualTypeArguments()[1];
                    Class clazz = (Class) parameterizedType.getRawType();

                    Converter converter = InternalConverter.getConverter(clazz);
                    map.put(key, ( converter != null ) ? converter.fromConfig(clazz, entry.getValue(), (ParameterizedType) genericType.getActualTypeArguments()[1]) : entry.getValue());
                }
            } else {
                Converter converter = InternalConverter.getConverter((Class) genericType.getRawType());

                if (converter != null) {
                    return converter.fromConfig((Class) genericType.getRawType(), section, null);
                }

                return (section instanceof java.util.Map) ? (java.util.Map) section : ((ConfigSection) section).getRawMap();
            }

            return map;
        } else {
            return section;
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return java.util.Map.class.isAssignableFrom(type);
    }
}
