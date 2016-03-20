package staticconfig;

import net.cubespace.Yamler.Config.*;

@SerializeOptions(configMode = ConfigMode.FIELD_IS_KEY)
public class StaticConfig extends YamlConfig {
	@Path(value = "Nest.Eggs.1")
	@PreserveStatic
	public static String blueEgg = "Blue";

	@Path(value = "Nest.Eggs.2")
	@PreserveStatic
	public static String redEgg = "Red";

	@Path(value = "Nest.Eggs.3")
	@PreserveStatic
	public static String dynamiteEgg = "DYNOMITE-KABOOM";
}
