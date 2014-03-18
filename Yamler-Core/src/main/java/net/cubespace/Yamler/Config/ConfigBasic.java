package net.cubespace.Yamler.Config;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ConfigBasic {
    protected transient File CONFIG_FILE = null;
    protected transient String[] CONFIG_HEADER = null;
    protected transient ConfigMode CONFIG_MODE = ConfigMode.DEFAULT;
    protected transient boolean skipFailedObjects = false;

    protected transient InternalConverter converter = new InternalConverter();

    /**
     * This function gets called after the File has been loaded and before the Converter gets it.
     * This is used to manually edit the configSection when you updated the config or something
     * @param configSection The root ConfigSection with all Subnodes loaded into
     */
    public void update(ConfigSection configSection) {

    }

    /**
     * Add a Custom Converter. A Converter can take Objects and return a pretty Object which gets saved/loaded from
     * the Converter. How a Converter must be build can be looked up in the Converter Interface.
     *
     * @param addConverter Converter to be added
     * @throws InvalidConverterException If the Converter has any errors this Exception tells you what
     */
    public void addConverter(Class addConverter) throws InvalidConverterException {
        converter.addConverter(addConverter);
    }

    protected boolean doSkip(Field field) {
        return Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())
                || Modifier.isFinal(field.getModifiers());
    }
}
