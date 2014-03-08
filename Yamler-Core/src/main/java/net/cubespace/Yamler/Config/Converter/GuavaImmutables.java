package net.cubespace.Yamler.Config.Converter;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableClassToInstanceMap;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;

import java.lang.reflect.ParameterizedType;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class GuavaImmutables implements Converter {
    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType parameterizedType) throws Exception {
        return null;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        return null;
    }

    @Override
    public boolean supports(Class<?> type) {
        return  ImmutableSet.class.isAssignableFrom(type) ||
                ImmutableMultimap.class.isAssignableFrom(type) ||
                ImmutableMap.class.isAssignableFrom(type) ||
                ImmutableBiMap.class.isAssignableFrom(type) ||
                ImmutableClassToInstanceMap.class.isAssignableFrom(type) ||
                ImmutableCollection.class.isAssignableFrom(type) ||
                ImmutableList.class.isAssignableFrom(type) ||
                ImmutableListMultimap.class.isAssignableFrom(type) ||
                ImmutableMultiset.class.isAssignableFrom(type);
    }
}
