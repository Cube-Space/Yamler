import net.cubespace.Yamler.Config.Config;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Position extends Config {
    private int x;
    private int y;
    private int z;

    public Position() {}

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
