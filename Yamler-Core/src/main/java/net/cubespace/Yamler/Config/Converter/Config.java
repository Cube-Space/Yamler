package net.cubespace.Yamler.Config.Converter;

import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.InternalConverter;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Config implements Converter {
    private InternalConverter internalConverter;

    public Config(InternalConverter internalConverter) {
        this.internalConverter = internalConverter;
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) {
        return (obj instanceof Map) ? obj : ((net.cubespace.Yamler.Config.Config) obj).saveToMap();
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        if (section instanceof Config) {
            return section;
        }

        net.cubespace.Yamler.Config.Config obj = (net.cubespace.Yamler.Config.Config) type.newInstance();
        obj.loadFromMap((section instanceof Map) ? (Map) section : ((ConfigSection) section).getRawMap());
        return obj;
    }

    @Override
    public boolean supports(Class<?> type) {
        return net.cubespace.Yamler.Config.Config.class.isAssignableFrom(type);
    }
}
