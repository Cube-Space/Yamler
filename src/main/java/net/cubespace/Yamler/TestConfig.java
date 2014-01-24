package net.cubespace.Yamler;

import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Comments;
import net.cubespace.Yamler.Config.Config;

import java.io.File;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class TestConfig extends Config {
    public TestConfig(YamlerPlugin p) {
        CONFIG_FILE = new File(p.getDataFolder(), "config.yml");
    }

    @Comment("Database configuration.")
    private Map database;
    @Comments({"The database JDBC address. Should replace dbname with the database name."})
    private String database_address = "jdbc:mysql://localhost/dbname";
    @Comment("Database username, if applicable. Leave empty if unneeded")
    private String database_username = "";
    @Comment("Database password, if applicable. Leave empty if unneeded")
    private String database_password = "";

    @Comment("Gameplay settings")
    private Map gameplay;
    @Comment("Delay in minutes during which players cannot break beds.")
    private int gameplay_bedBreakDelay = 60;
    @Comment("Amount of players each team needs before starting the game.")
    private int gameplay_playersPerTeam = 4;
    @Comments({"Amount of teams in the game.",
            "Can be 2, 3, 4 or 6"})
    private int gameplay_teams = 2;
    @Comment("Delay in seconds before starting the game")
    private int gameplay_gameStartDelay = 10;

    @Comment("Score system settings")
    private Map scoring;
    @Comment("Points earned when winning the game")
    private int scoring_win = 4;
    @Comment("Points lost when losing the game")
    private int scoring_lose = 2;
    @Comment("Points earned when killing an enemy")
    private int scoring_kill = 2;
    @Comment("Points lost when getting killed")
    private int scoring_death = 1;

    @Comment("Economy settings")
    private Map economy;
    @Comment("Money earned when winning the game")
    private int economy_win = 4;
    @Comment("Money lost when losing the game")
    private int economy_lose = 2;
    @Comment("Points earned when killing an enemy")
    private int economy_kill = 2;
    @Comment("Points lost when getting killed")
    private int economy_death = 1;

    @Comment("Game Setup")
    private Map setup;
    @Comment("Game world")
    private String setup_world = "world";
    @Comment("Position of the lobby")
    private Position setup_lobbyPosition = new Position(0,0,0);
    @Comment("Position of each spawns")
    private Position[] setup_spawnPosition = new Position[8];
}
