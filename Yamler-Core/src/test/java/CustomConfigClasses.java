import net.cubespace.Yamler.Config.Comments;
import net.cubespace.Yamler.Config.ConfigMode;
import net.cubespace.Yamler.Config.SerializeOptions;
import net.cubespace.Yamler.Config.YamlConfig;

import java.io.File;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 *         <p/>
 *         This Config example shows how you can use other Objects (from Config Classes) to build up more complex
 *         Configurations. In this example the Goal is to have a Location saved into a Config.
 *         <p/>
 *         For that we use a public inner class. This is not the best thing todo. Normally its the best to make
 *         a seperate Package containing Config Sub classes.
 *         <p/>
 *         This example will use the Getter/Setter pattern for accessing the values, you can ofc use public modified
 *         fields if you want to.
 */
@SerializeOptions(configMode = ConfigMode.PATH_BY_UNDERSCORE)
public class CustomConfigClasses extends YamlConfig {
	/**
	 * This class represents a Location (with server, world, x, y, z, yaw and pitch)
	 * <p/>
	 * Server and World are Strings
	 * x, y, z are Integer (since we want to save full block positions)
	 * yaw and pitch are Floats
	 * <p/>
	 * Hint: Since this is a embedded Config Class it does not need to setup a File
	 */
	public class Location extends YamlConfig {
		private String server;
		private String world;
		private Integer x;
		private Integer y;
		private Integer z;
		private Float yaw;
		private Float pitch;

		public String getServer() {
			return server;
		}

		public void setServer(String server) {
			this.server = server;
		}

		public String getWorld() {
			return world;
		}

		public void setWorld(String world) {
			this.world = world;
		}

		public Integer getX() {
			return x;
		}

		public void setX(Integer x) {
			this.x = x;
		}

		public Integer getY() {
			return y;
		}

		public void setY(Integer y) {
			this.y = y;
		}

		public Integer getZ() {
			return z;
		}

		public void setZ(Integer z) {
			this.z = z;
		}

		public Float getYaw() {
			return yaw;
		}

		public void setYaw(Float yaw) {
			this.yaw = yaw;
		}

		public Float getPitch() {
			return pitch;
		}

		public void setPitch(Float pitch) {
			this.pitch = pitch;
		}
	}

	/**
	 * You can build a Constructor which sets a header and the file. The header can only be set
	 * from inside the Config Class. The File can be given by load(File), init(File) or save(File)
	 * <p/>
	 * In this Case i set the File inside the Constructor and add a Little header
	 */
	public CustomConfigClasses() {
		CONFIG_FILE = new File("customClassConfig.yml");
		CONFIG_HEADER = new String[]{
				"This is a example Config showing you how to use Custom Classes inside the Config"
		};
	}

	/**
	 * What we need now is a Field which holds a Location type. The fields Name will build the Key in the YAML
	 * Config File. In the default Mode the Fields name gets converted from "_" to a "." so you can get a nice
	 * YAML hierachy.
	 * <p/>
	 * In addition i want the YAML to contain a Comment on how to use this Config Option above it. This can be
	 * done with the @Comment(s) annotations. @Comment is one line, @Comments is multilined.
	 */
	@Comments({
			"To configure the default Spawn Location please use '/plugin setspawn' ingame.",
			"If you modify it here it can not work."
	})
	private Location Spawn_Location = new Location();

	public Location getSpawn_Location() {
		return Spawn_Location;
	}

	public void setSpawn_Location(Location spawn_Location) {
		Spawn_Location = spawn_Location;
	}
}

/**
 * This example will produce this config YML:
 *
 * # This is a example Config showing you how to use Custom Classes inside the Config
 *
 * Spawn:
 *   # To configure the default Spawn Location please use '/plugin setspawn' ingame.
 *   # If you modify it here it can not work.
 *   Location:
 *     yaw: null
 *     server: null
 *     pitch: null
 *     z: null
 *     y: null
 *     world: null
 *     x: null
 */
