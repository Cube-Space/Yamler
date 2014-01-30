package net.cubespace.Yamler.Config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ConfigBasic {
    protected boolean doSkip(Field field) {
        return Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())
                || Modifier.isFinal(field.getModifiers());
    }
}
