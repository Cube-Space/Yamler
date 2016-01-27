package base;

import net.cubespace.Yamler.Config.YamlConfig;

import java.io.File;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public abstract class BaseTest {
    protected String filename;
    protected YamlConfig config;
    protected File file;

    public void before() throws Exception {
        file = new File("temp", filename);
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    public abstract void setup() throws Exception;
}
