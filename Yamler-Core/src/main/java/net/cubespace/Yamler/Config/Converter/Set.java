package net.cubespace.Yamler.Config.Converter;

import net.cubespace.Yamler.Config.*;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashSet;

public class Set implements Converter {
    private InternalConverter internalConverter;

    public Set(InternalConverter internalConverter) {
        this.internalConverter = internalConverter;
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType genericType) throws Exception {
        java.util.Set<Object> values = (java.util.Set<Object>) obj;
        java.util.List<Object> newList = new ArrayList<>();

        if (genericType.getActualTypeArguments()[0] instanceof Class && net.cubespace.Yamler.Config.Config.class.isAssignableFrom((Class<?>)genericType.getActualTypeArguments()[0])) {
            Converter converter = internalConverter.getConverter(net.cubespace.Yamler.Config.Config.class);

            for (Object valObj : values)
                newList.add(converter.toConfig((Class<?>)genericType.getActualTypeArguments()[0], valObj, null));
        } else
            newList.addAll(values);

        return newList;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        java.util.List<Object> values = (java.util.List<Object>)section;
        java.util.Set<Object> newList = new HashSet<>();

        try {
            newList = (java.util.Set<Object>)type.newInstance();
        } catch (Exception e) { }

        if (genericType.getActualTypeArguments()[0] instanceof Class && net.cubespace.Yamler.Config.Config.class.isAssignableFrom((Class<?>)genericType.getActualTypeArguments()[0])) {
            Converter converter = internalConverter.getConverter(net.cubespace.Yamler.Config.Config.class);

            for (Object valObj : values)
                newList.add(converter.fromConfig((Class<?>)genericType.getActualTypeArguments()[0], valObj, null));
        } else
            newList.addAll(values);

        return newList;
    }

    @Override
    public boolean supports(Class<?> type) {
        return java.util.Set.class.isAssignableFrom(type);
    }

}
