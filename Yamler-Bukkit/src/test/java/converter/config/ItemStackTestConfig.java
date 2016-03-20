package converter.config;

import helper.TestItemStack;
import net.cubespace.Yamler.Config.YamlConfig;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The test configuration to check for a correct working ItemStack.
 *
 * @author bibo38
 * @see converter.ItemStackConverterTest
 */
public class ItemStackTestConfig extends YamlConfig {

    public TestItemStack cookie = new TestItemStack(Material.COOKIE);
    public TestItemStack specialWool = new TestItemStack(Material.WOOL);

    /**
     * The default constructor is used to add the {@link net.cubespace.Yamler.Converter.ItemStack} converter
     * and add a special ItemMeta class to the {@link this#specialWool} variable to test the converter.
     *
     * @throws Exception When the converter cannot be added
     */
    public ItemStackTestConfig() throws Exception {
        addConverter(net.cubespace.Yamler.Converter.ItemStack.class);

        specialWool.setItemMeta(new ItemMeta() {
            @Override
            public boolean hasDisplayName() {
                return true;
            }

            @Override
            public String getDisplayName() {
                return "Test Wool";
            }

            @Override
            public void setDisplayName(String s) {
            }

            @Override
            public boolean hasLore() {
                return true;
            }

            @Override
            public List<String> getLore() {
                return Arrays.asList("This is Wool", "Good Wool");
            }

            @Override
            public void setLore(List<String> list) {
            }

            @Override
            public boolean hasEnchants() {
                return true;
            }

            @Override
            public boolean hasEnchant(Enchantment enchantment) {
                return Enchantment.LUCK.equals(enchantment);
            }

            @Override
            public int getEnchantLevel(Enchantment enchantment) {
                return hasEnchant(enchantment) ? 1 : 0;
            }

            @Override
            public Map<Enchantment, Integer> getEnchants() {
                HashMap<Enchantment, Integer> enchantments = new HashMap<>();
                enchantments.put(Enchantment.LUCK, 1);
                return enchantments;
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
        });
    }
}
