import net.cubespace.Yamler.Config.YamlConfig;
import net.cubespace.Yamler.Config.ConfigSection;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class PrimitiveConfig extends YamlConfig {
    public boolean TestBoolean = false;
    public int TestInt = 0;
    public short TestShort = 0;
    public byte TestByte = 0;
    public double TestDouble = 0.0000001;
    public float TestFloat = 0.0001F;
    public long TestLong = 1684654679684L;
    public char TestChar = 'c';

    public void update(ConfigSection config) {
        config.set("TestInjectUpdate", "true");
        config.remove("TestInt");
    }
}
