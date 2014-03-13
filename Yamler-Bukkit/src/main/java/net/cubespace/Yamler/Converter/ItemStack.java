package net.cubespace.Yamler.Converter;

import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.Converter.Converter;
import net.cubespace.Yamler.Config.InternalConverter;
import org.bukkit.Material;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class ItemStack implements Converter {
    private InternalConverter converter;

    public ItemStack(InternalConverter converter) {
        this.converter = converter;
    }

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType genericType) throws Exception {
        org.bukkit.inventory.ItemStack itemStack = (org.bukkit.inventory.ItemStack) obj;

        Map<String, Object> saveMap = new HashMap<>();
        saveMap.put("id", itemStack.getType() + ((itemStack.getDurability() > 0) ? ":" + itemStack.getDurability() : ""));
        saveMap.put("amount", itemStack.getAmount());

        Converter listConverter = converter.getConverter(List.class);

        Map<String, Object> meta = new HashMap<>();
        meta.put("name", itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : null);
        meta.put("lore", itemStack.getItemMeta().hasLore() ? listConverter.toConfig(List.class, itemStack.getItemMeta().getLore(), null) : null);

        saveMap.put("meta", meta);

        return saveMap;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        Map<String, Object> itemstackMap = (Map<String, Object>) ((ConfigSection) section).getRawMap();
        Map<String, Object> metaMap = (Map<String, Object>) ((ConfigSection) itemstackMap.get("meta")).getRawMap();

        String[] temp = ((String) itemstackMap.get("id")).split(":");
        org.bukkit.inventory.ItemStack itemStack = new org.bukkit.inventory.ItemStack(Material.valueOf(temp[0]));
        itemStack.setAmount((int) itemstackMap.get("amount"));

        if (temp.length == 2) {
            itemStack.setDurability(Short.valueOf(temp[1]));
        }

        if (metaMap.get("name") != null) {
            itemStack.getItemMeta().setDisplayName((String) metaMap.get("name"));
        }

        if (metaMap.get("lore") != null) {
            Converter listConverter = converter.getConverter(List.class);
            itemStack.getItemMeta().setLore((List<String>) listConverter.fromConfig(List.class, metaMap.get("lore"), null));
        }

        return itemStack;
    }

    @Override
    public boolean supports(Class<?> type) {
        return org.bukkit.inventory.ItemStack.class.isAssignableFrom(type);
    }

}
