package net.cubespace.Yamler.Config;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ConfigBasic {
    protected transient File CONFIG_FILE = null;
    protected transient String[] CONFIG_HEADER = null;
    protected transient ConfigMode CONFIG_MODE = ConfigMode.DEFAULT;

    protected boolean doSkip(Field field) {
        return Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())
                || Modifier.isFinal(field.getModifiers());
    }
}
