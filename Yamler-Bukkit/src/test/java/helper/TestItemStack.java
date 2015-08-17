package helper;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

/**
 * An implementation of the default Bukkit {@link ItemStack} to provide
 * a similar ability to write/read the {@link ItemMeta} without CraftBukkit running.
 *
 * @author bibo38
 * @see ItemStack
 */
public class TestItemStack extends ItemStack {
    ItemMeta meta = null;

    /**
     * The common used constructor
     *
     * @param type The type of the ItemStack
     * @see ItemStack#ItemStack(Material)
     */
    public TestItemStack(Material type) {
        super(type);
    }

    @Override
    public ItemMeta getItemMeta() {
        if(meta == null)
            meta = new ItemMeta() {
                @Override
                public boolean hasDisplayName() {
                    return false;
                }

                @Override
                public String getDisplayName() {
                    return null;
                }

                @Override
                public void setDisplayName(String s) {
                }

                @Override
                public boolean hasLore() {
                    return false;
                }

                @Override
                public List<String> getLore() {
                    return null;
                }

                @Override
                public void setLore(List<String> list) {
                }

                @Override
                public boolean hasEnchants() {
                    return false;
                }

                @Override
                public boolean hasEnchant(Enchantment enchantment) {
                    return false;
                }

                @Override
                public int getEnchantLevel(Enchantment enchantment) {
                    return 0;
                }

                @Override
                public Map<Enchantment, Integer> getEnchants() {
                    return null;
                }

                @Override
                public boolean addEnchant(Enchantment enchantment, int i, boolean b) {
                    return false;
                }

                @Override
                public boolean removeEnchant(Enchantment enchantment) {
                    return false;
                }

                @Override
                public boolean hasConflictingEnchant(Enchantment enchantment) {
                    return false;
                }

                @Override
                public ItemMeta clone() {
                    return null;
                }

                @Override
                public Map<String, Object> serialize() {
                    return null;
                }
            };
        return meta;
    }

    @Override
    public boolean hasItemMeta() {
        return meta != null;
    }

    @Override
    public boolean setItemMeta(ItemMeta itemMeta) {
        meta = itemMeta;
        return true;
    }
}
