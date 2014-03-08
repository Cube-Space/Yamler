package net.cubespace.Yamler.Config.Converter;

import java.lang.reflect.ParameterizedType;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public interface Converter {
    public Object toConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) throws Exception;
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception;
    public boolean supports(Class<?> type);
}
