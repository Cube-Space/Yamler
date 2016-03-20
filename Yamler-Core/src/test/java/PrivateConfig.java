import net.cubespace.Yamler.Config.YamlConfig;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class PrivateConfig extends YamlConfig {
    private boolean TestBoolean = false;
    private int TestInt = 0;
    private short TestShort = 0;
    private byte TestByte = 0;
    private double TestDouble = 0.0000001;
    private float TestFloat = 0.0001F;
    private long TestLong = 1684654679684L;
    private char TestChar = 'c';

    public boolean isTestBoolean() {
        return TestBoolean;
    }

    public void setTestBoolean(boolean testBoolean) {
        TestBoolean = testBoolean;
    }

    public int getTestInt() {
        return TestInt;
    }

    public void setTestInt(int testInt) {
        TestInt = testInt;
    }

    public short getTestShort() {
        return TestShort;
    }

    public void setTestShort(short testShort) {
        TestShort = testShort;
    }

    public byte getTestByte() {
        return TestByte;
    }

    public void setTestByte(byte testByte) {
        TestByte = testByte;
    }

    public double getTestDouble() {
        return TestDouble;
    }

    public void setTestDouble(double testDouble) {
        TestDouble = testDouble;
    }

    public float getTestFloat() {
        return TestFloat;
    }

    public void setTestFloat(float testFloat) {
        TestFloat = testFloat;
    }

    public long getTestLong() {
        return TestLong;
    }

    public void setTestLong(long testLong) {
        TestLong = testLong;
    }

    public char getTestChar() {
        return TestChar;
    }

    public void setTestChar(char testChar) {
        TestChar = testChar;
    }
}
