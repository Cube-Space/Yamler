import base.Util;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Real {
    private RealConfig realConfig;
    private File file;

    @BeforeSuite
    public void before() {
        realConfig = new RealConfig();

        file = new File("temp", "realConfig.yml");
        if(!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if(file.exists()) {
            file.delete();
        }
    }

    @Test(priority = 3)
    private void changePosition() throws InvalidConfigurationException {
        realConfig.getSetup_lobbyPosition().setX(123);
        realConfig.getSetup_lobbyPosition().setY(135);
        realConfig.getSetup_lobbyPosition().setZ(456);
        realConfig.save();

        RealConfig config = new RealConfig();
        config.load(file);

        Assert.assertEquals(config.getSetup_lobbyPosition().getX(), 123);
        Assert.assertEquals(config.getSetup_lobbyPosition().getY(), 135);
        Assert.assertEquals(config.getSetup_lobbyPosition().getZ(), 456);
        Assert.assertTrue(config.getSetup_spawnPosition().get(0) instanceof Position);
    }

    @Test(priority = 2)
    public void doubleCommentFix() throws IOException, InvalidConfigurationException {
        realConfig.save();

        String fileContents = Util.readFile(file);
        Assert.assertEquals(fileContents.replace("\r", ""), "# Database configuration.\n" +
                "database:\n" +
                "  # The database JDBC address. Should replace dbname with the database name.\n" +
                "  address: jdbc:mysql://localhost/dbname\n" +
                "  # The table to use within given database\n" +
                "  table: RushPlugin\n" +
                "  # Database username, if applicable. Leave empty if unneeded\n" +
                "  username: ''\n" +
                "  # Database password, if applicable. Leave empty if unneeded\n" +
                "  password: ''\n" +
                "# Gameplay settings\n" +
                "gameplay:\n" +
                "  # Delay in minutes during which players cannot break beds.\n" +
                "  bedBreakDelay: 60\n" +
                "  # Amount of players each team needs before starting the game.\n" +
                "  playersPerTeam: 4\n" +
                "  # Amount of teams in the game.\n" +
                "  # Can be 2, 3, 4 or 6\n" +
                "  teams: 2\n" +
                "  # Delay in seconds before starting the game\n" +
                "  gameStartDelay: 10\n" +
                "  # Delay in seconds for forced respawn after death.\n" +
                "  respawnDelay: 10\n" +
                "# Score system settings\n" +
                "scoring:\n" +
                "  # Points earned when winning the game\n" +
                "  win: 4\n" +
                "  # Points lost when losing the game\n" +
                "  lose: 2\n" +
                "  # Points earned when killing an enemy\n" +
                "  kill: 2\n" +
                "  # Points lost when getting killed\n" +
                "  death: 1\n" +
                "# Economy settings\n" +
                "economy:\n" +
                "  # Money earned when winning the game\n" +
                "  win: 4\n" +
                "  # Money lost when losing the game\n" +
                "  lose: 2\n" +
                "  # Points earned when killing an enemy\n" +
                "  kill: 2\n" +
                "  # Points lost when getting killed\n" +
                "  death: 1\n" +
                "# Game Setup\n" +
                "setup:\n" +
                "  # Game world\n" +
                "  world: world\n" +
                "  # Server to move players to after game over\n" +
                "  hubServer: hub\n" +
                "  # Position of the lobby\n" +
                "  lobbyPosition:\n" +
                "    x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  # Position of each spawns\n" +
                "  spawnPosition:\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n");

    }

    @Test(priority = 1)
    public void initNull() throws InvalidConfigurationException, IOException {
        realConfig.init(file);

        String fileContents = Util.readFile(file);

        Assert.assertEquals(fileContents.replace("\r", ""), "# Database configuration.\n" +
                "database:\n" +
                "  # The database JDBC address. Should replace dbname with the database name.\n" +
                "  address: jdbc:mysql://localhost/dbname\n" +
                "  # The table to use within given database\n" +
                "  table: RushPlugin\n" +
                "  # Database username, if applicable. Leave empty if unneeded\n" +
                "  username: ''\n" +
                "  # Database password, if applicable. Leave empty if unneeded\n" +
                "  password: ''\n" +
                "# Gameplay settings\n" +
                "gameplay:\n" +
                "  # Delay in minutes during which players cannot break beds.\n" +
                "  bedBreakDelay: 60\n" +
                "  # Amount of players each team needs before starting the game.\n" +
                "  playersPerTeam: 4\n" +
                "  # Amount of teams in the game.\n" +
                "  # Can be 2, 3, 4 or 6\n" +
                "  teams: 2\n" +
                "  # Delay in seconds before starting the game\n" +
                "  gameStartDelay: 10\n" +
                "  # Delay in seconds for forced respawn after death.\n" +
                "  respawnDelay: 10\n" +
                "# Score system settings\n" +
                "scoring:\n" +
                "  # Points earned when winning the game\n" +
                "  win: 4\n" +
                "  # Points lost when losing the game\n" +
                "  lose: 2\n" +
                "  # Points earned when killing an enemy\n" +
                "  kill: 2\n" +
                "  # Points lost when getting killed\n" +
                "  death: 1\n" +
                "# Economy settings\n" +
                "economy:\n" +
                "  # Money earned when winning the game\n" +
                "  win: 4\n" +
                "  # Money lost when losing the game\n" +
                "  lose: 2\n" +
                "  # Points earned when killing an enemy\n" +
                "  kill: 2\n" +
                "  # Points lost when getting killed\n" +
                "  death: 1\n" +
                "# Game Setup\n" +
                "setup:\n" +
                "  # Game world\n" +
                "  world: world\n" +
                "  # Server to move players to after game over\n" +
                "  hubServer: hub\n" +
                "  # Position of the lobby\n" +
                "  lobbyPosition:\n" +
                "    x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  # Position of each spawns\n" +
                "  spawnPosition:\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n" +
                "  - x: 0\n" +
                "    y: 50\n" +
                "    z: 0\n");
    }
}
