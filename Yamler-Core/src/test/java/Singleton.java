import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Singleton {
    @Test
    public void init() {
        String message = Messages.get("general");
        Assert.assertEquals(message, "Did I do that!");
    }

    @Test(priority = 1)
    public void reinit() {
        Messages.instance = null;
        init();
        init();
    }
}
