package realconfig.cloudchat;

import base.BaseTest;
import base.Util;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import realconfig.cloudchat.config.CCBMessagesConfig;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class CCBMessageTest extends BaseTest {
    @BeforeSuite
    public void setup() throws Exception {
        config = new CCBMessagesConfig();
        filename = "ccbMessages.yml";

        before();
    }

    @Test(priority = 1)
    public void onInit() throws Exception {
        config.init(file);

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents,
                "\nCommand:\n" +
                "  NotPlayer: You can do this only as a Player\n" +
                "Announce:\n" +
                "  FactionMode: 'You currently Chat to: %mode'\n" +
                "Switch:\n" +
                "  FactionChat:\n" +
                "    Allies: You now talk to your Allies\n" +
                "    Faction: You now talk to your Faction\n" +
                "    AlliesAndTruces: You now talk to your Allies and Truces\n" +
                "    Truces: You now talk to your Truces\n" +
                "    Enemies: You now talk to your Enemies\n" +
                "    Global: You now talk to the Global Chat\n" +
                "Towny:\n" +
                "  NotInTown: You are not in a Town\n" +
                "  TownNotInNation: This Town is not in a Nation\n" +
                "  CouldNotGetTown: Could not get Town. Please report this to a Admin.\n");
    }
}
