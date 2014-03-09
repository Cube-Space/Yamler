package net.cubespace.Yamler.Config.Converter;

import net.cubespace.Yamler.Config.*;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class List implements Converter {
    private InternalConverter internalConverter;

    public List(InternalConverter internalConverter) {
        this.internalConverter = internalConverter;
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType genericType) throws Exception {
        java.util.List values = (java.util.List) obj;
        java.util.List newList = new ArrayList();

        try {
            newList = ((java.util.List) type.newInstance());
        } catch (Exception e) {}


        if(genericType.getActualTypeArguments()[0] instanceof Class && net.cubespace.Yamler.Config.Config.class.isAssignableFrom((Class) genericType.getActualTypeArguments()[0])) {
            Converter converter = internalConverter.getConverter(net.cubespace.Yamler.Config.Config.class);

            for(int i = 0; i < values.size(); i++) {
                newList.add(converter.toConfig((Class) genericType.getActualTypeArguments()[0], values.get(i), null));
            }
        } else {
            newList = values;
        }

        return newList;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        java.util.List newList = new ArrayList();
        try {
            newList = ((java.util.List) type.newInstance());
        } catch (Exception e) {}

        java.util.List values = (java.util.List) section;

        if(genericType.getActualTypeArguments()[0] instanceof Class && net.cubespace.Yamler.Config.Config.class.isAssignableFrom((Class) genericType.getActualTypeArguments()[0])) {
            Converter converter = internalConverter.getConverter(net.cubespace.Yamler.Config.Config.class);

            for(int i = 0; i < values.size(); i++) {
                newList.add(converter.fromConfig((Class) genericType.getActualTypeArguments()[0], values.get(i), null));
            }
        } else {
            newList = values;
        }

        return newList;
    }

    @Override
    public boolean supports(Class<?> type) {
        return java.util.List.class.isAssignableFrom(type);
    }
}
