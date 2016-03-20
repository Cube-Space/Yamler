import net.cubespace.Yamler.Config.Comment;
import net.cubespace.Yamler.Config.Comments;
import net.cubespace.Yamler.Config.YamlConfig;
import net.cubespace.Yamler.Config.ConfigSection;

import java.util.ArrayList;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class RealConfig extends YamlConfig {
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

    public ConfigSection getDatabase() {
        return database;
    }

    public void setDatabase(ConfigSection database) {
        this.database = database;
    }

    public String getDatabase_address() {
        return database_address;
    }

    public void setDatabase_address(String database_address) {
        this.database_address = database_address;
    }

    public String getDatabase_table() {
        return database_table;
    }

    public void setDatabase_table(String database_table) {
        this.database_table = database_table;
    }

    public String getDatabase_username() {
        return database_username;
    }

    public void setDatabase_username(String database_username) {
        this.database_username = database_username;
    }

    public String getDatabase_password() {
        return database_password;
    }

    public void setDatabase_password(String database_password) {
        this.database_password = database_password;
    }

    public ConfigSection getGameplay() {
        return gameplay;
    }

    public void setGameplay(ConfigSection gameplay) {
        this.gameplay = gameplay;
    }

    public int getGameplay_bedBreakDelay() {
        return gameplay_bedBreakDelay;
    }

    public void setGameplay_bedBreakDelay(int gameplay_bedBreakDelay) {
        this.gameplay_bedBreakDelay = gameplay_bedBreakDelay;
    }

    public int getGameplay_playersPerTeam() {
        return gameplay_playersPerTeam;
    }

    public void setGameplay_playersPerTeam(int gameplay_playersPerTeam) {
        this.gameplay_playersPerTeam = gameplay_playersPerTeam;
    }

    public int getGameplay_teams() {
        return gameplay_teams;
    }

    public void setGameplay_teams(int gameplay_teams) {
        this.gameplay_teams = gameplay_teams;
    }

    public int getGameplay_gameStartDelay() {
        return gameplay_gameStartDelay;
    }

    public void setGameplay_gameStartDelay(int gameplay_gameStartDelay) {
        this.gameplay_gameStartDelay = gameplay_gameStartDelay;
    }

    public int getGameplay_respawnDelay() {
        return gameplay_respawnDelay;
    }

    public void setGameplay_respawnDelay(int gameplay_respawnDelay) {
        this.gameplay_respawnDelay = gameplay_respawnDelay;
    }

    public ConfigSection getScoring() {
        return scoring;
    }

    public void setScoring(ConfigSection scoring) {
        this.scoring = scoring;
    }

    public int getScoring_win() {
        return scoring_win;
    }

    public void setScoring_win(int scoring_win) {
        this.scoring_win = scoring_win;
    }

    public int getScoring_lose() {
        return scoring_lose;
    }

    public void setScoring_lose(int scoring_lose) {
        this.scoring_lose = scoring_lose;
    }

    public int getScoring_kill() {
        return scoring_kill;
    }

    public void setScoring_kill(int scoring_kill) {
        this.scoring_kill = scoring_kill;
    }

    public int getScoring_death() {
        return scoring_death;
    }

    public void setScoring_death(int scoring_death) {
        this.scoring_death = scoring_death;
    }

    public ConfigSection getEconomy() {
        return economy;
    }

    public void setEconomy(ConfigSection economy) {
        this.economy = economy;
    }

    public int getEconomy_win() {
        return economy_win;
    }

    public void setEconomy_win(int economy_win) {
        this.economy_win = economy_win;
    }

    public int getEconomy_lose() {
        return economy_lose;
    }

    public void setEconomy_lose(int economy_lose) {
        this.economy_lose = economy_lose;
    }

    public int getEconomy_kill() {
        return economy_kill;
    }

    public void setEconomy_kill(int economy_kill) {
        this.economy_kill = economy_kill;
    }

    public int getEconomy_death() {
        return economy_death;
    }

    public void setEconomy_death(int economy_death) {
        this.economy_death = economy_death;
    }

    public ConfigSection getSetup() {
        return setup;
    }

    public void setSetup(ConfigSection setup) {
        this.setup = setup;
    }

    public String getSetup_world() {
        return setup_world;
    }

    public void setSetup_world(String setup_world) {
        this.setup_world = setup_world;
    }

    public String getSetup_hubServer() {
        return setup_hubServer;
    }

    public void setSetup_hubServer(String setup_hubServer) {
        this.setup_hubServer = setup_hubServer;
    }

    public Position getSetup_lobbyPosition() {
        return setup_lobbyPosition;
    }

    public void setSetup_lobbyPosition(Position setup_lobbyPosition) {
        this.setup_lobbyPosition = setup_lobbyPosition;
    }

    public ArrayList<Position> getSetup_spawnPosition() {
        return setup_spawnPosition;
    }

    public void setSetup_spawnPosition(ArrayList<Position> setup_spawnPosition) {
        this.setup_spawnPosition = setup_spawnPosition;
    }
}
