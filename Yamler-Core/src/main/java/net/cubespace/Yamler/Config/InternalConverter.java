package net.cubespace.Yamler.Config;

import net.cubespace.Yamler.Config.Converter.Converter;
import net.cubespace.Yamler.Config.Converter.Primitive;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashSet;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class InternalConverter {
    private static LinkedHashSet<Converter> converters;

    static {
        converters = new LinkedHashSet<>();
        addConverter(new Primitive());
        addConverter(new net.cubespace.Yamler.Config.Converter.Config());
        addConverter(new net.cubespace.Yamler.Config.Converter.List());
        addConverter(new net.cubespace.Yamler.Config.Converter.Map());
        addConverter(new net.cubespace.Yamler.Config.Converter.Array());

        /* //Optional GuavaImmutables support
        try {
            addConverter(new GuavaImmutables());
        } catch(Exception e) {} */
    }

    public static void addConverter(Converter converter) {
        converters.add(converter);
    }

    public static Converter getConverter(Class type) {
        for(Converter converter : converters) {
            if (converter.supports(type)) {
                return converter;
            }
        }

        return null;
    }

    public static void fromConfig(Config config, Field field, ConfigSection root, String path) throws Exception {
        Object obj = field.get(config);

        Converter converter;

        if (obj != null) {
            converter = getConverter(field.get(config).getClass());

            if (converter != null) {
                field.set(config, converter.fromConfig(obj.getClass(), root.get(path), (field.getGenericType() instanceof ParameterizedType) ? (ParameterizedType) field.getGenericType() : null));
                return;
            } else {
                converter = getConverter(field.getType());
                if (converter != null) {
                    field.set(config, converter.fromConfig(field.getType(), root.get(path), (field.getGenericType() instanceof ParameterizedType) ? (ParameterizedType) field.getGenericType() : null));
                    return;
                }
            }
        } else {
            converter = getConverter(field.getType());

            if (converter != null) {
                field.set(config, converter.fromConfig(field.getType(), root.get(path), (field.getGenericType() instanceof ParameterizedType) ? (ParameterizedType) field.getGenericType() : null));
                return;
            }
        }

        field.set(config, root.get(path));
    }

    public static void toConfig(Config config, Field field, ConfigSection root, String path) throws Exception {
        Object obj = field.get(config);

        Converter converter;

        if (obj != null) {
            converter = getConverter(obj.getClass());

            if (converter != null) {
                root.set(path, converter.toConfig(obj.getClass(), obj, (field.getGenericType() instanceof ParameterizedType) ? (ParameterizedType) field.getGenericType() : null));
                return;
            } else {
                converter = getConverter(field.getType());
                if (converter != null) {
                    root.set(path, converter.toConfig(field.getType(), obj, (field.getGenericType() instanceof ParameterizedType) ? (ParameterizedType) field.getGenericType() : null));
                    return;
                }
            }
        } else {
            converter = getConverter(field.getType());
            if (converter != null) {
                root.set(path, converter.toConfig(field.getType(), obj, (field.getGenericType() instanceof ParameterizedType) ? (ParameterizedType) field.getGenericType() : null));
                return;
            }
        }

        root.set(path, obj);
    }
}
