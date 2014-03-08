package net.cubespace.Yamler.Config.Converter;

import net.cubespace.Yamler.Config.ConfigSection;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Config implements Converter {
    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) {
        return ((net.cubespace.Yamler.Config.Config) obj).saveToMap();
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        net.cubespace.Yamler.Config.Config obj = (net.cubespace.Yamler.Config.Config) type.cast(type.newInstance());
        obj.loadFromMap((section instanceof Map) ? (Map) section : ((ConfigSection) section).getRawMap());
        return obj;
    }

    @Override
    public boolean supports(Class<?> type) {
        return net.cubespace.Yamler.Config.Config.class.isAssignableFrom(type);
    }
}
