package performance.config;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Comments;
import net.cubespace.Yamler.Config.YamlConfig;
import net.cubespace.Yamler.Config.ConfigSection;

import java.util.ArrayList;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class PerformanceConfig extends YamlConfig {
    @Comment("Database configuration.")
    private ConfigSection database;
    @Comments({"The database JDBC address. Should replace dbname with the database name."})
    private String database_address = "jdbc:mysql://localhost/dbname";
    @Comments({"The table to use within given database"})
    private String database_table = "RushPlugin";
    @Comment("Database username, if applicable. Leave empty if unneeded")
    private String database_username = "";
    @Comment("Database password, if applicable. Leave empty if unneeded")
    private String database_password = "";

    @Comment("Gameplay settings")
    private ConfigSection gameplay;
    @Comment("Delay in minutes during which players cannot break beds.")
    private int gameplay_bedBreakDelay = 60;
    @Comment("Amount of players each team needs before starting the game.")
    private int gameplay_playersPerTeam = 4;
    @Comments({"Amount of teams in the game.",
            "Can be 2, 3, 4 or 6"})
    private int gameplay_teams = 2;
    @Comment("Delay in seconds before starting the game")
    private int gameplay_gameStartDelay = 10;
    @Comment("Delay in seconds for forced respawn after death.")
    private int gameplay_respawnDelay = 10;

    @Comment("Score system settings")
    private ConfigSection scoring;
    @Comment("Points earned when winning the game")
    private int scoring_win = 4;
    @Comment("Points lost when losing the game")
    private int scoring_lose = 2;
    @Comment("Points earned when killing an enemy")
    private int scoring_kill = 2;
    @Comment("Points lost when getting killed")
    private int scoring_death = 1;

    @Comment("Economy settings")
    private ConfigSection economy;
    @Comment("Money earned when winning the game")
    private int economy_win = 4;
    @Comment("Money lost when losing the game")
    private int economy_lose = 2;
    @Comment("Points earned when killing an enemy")
    private int economy_kill = 2;
    @Comment("Points lost when getting killed")
    private int economy_death = 1;

    @Comment("Game Setup")
    private ConfigSection setup;
    @Comment("Game world")
    private String setup_world = "world";
    @Comment("Server to move players to after game over")
    private String setup_hubServer = "hub";
    @Comment("Position of the lobby")
    private Position setup_lobbyPosition = new Position(0,50,0);
    @Comment("Position of each spawns")
    private ArrayList<Position> setup_spawnPosition = new ArrayList<Position>() {{
        for (int i = 0;i < 8;i++) { add(new Position(0,50,0)); }
    }};
}
