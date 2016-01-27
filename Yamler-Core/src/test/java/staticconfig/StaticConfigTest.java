package staticconfig;

import base.BaseTest;
import base.Util;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class StaticConfigTest extends BaseTest {

	@BeforeSuite
	public void setup() throws Exception {
		config = new StaticConfig();
		filename = "staticConfig.yml";

		before();
	}

	@Test(priority = 1)
	public void onInit() throws Exception {
		config.init(file);

		String contents = Util.readFile(file);

		Assert.assertEquals(contents,
				"\nNest:\n"
						+ "  Eggs:\n"
						+ "    '1': Blue\n"
						+ "    '2': Red\n"
						+ "    '3': DYNOMITE-KABOOM\n"
		);
	}

	@Test(priority = 2)
	public void onTestFileChange() throws Exception {
		config.init(file);
		String oldContents = Util.readFile(file);

		StaticConfig.blueEgg = "Red";
		config.save();

		String newContents = Util.readFile(file);

		Assert.assertEquals(oldContents,
				"\nNest:\n"
						+ "  Eggs:\n"
						+ "    '1': Blue\n"
						+ "    '2': Red\n"
						+ "    '3': DYNOMITE-KABOOM\n"
		);

		Assert.assertEquals(newContents,
				"\nNest:\n"
						+ "  Eggs:\n"
						+ "    '1': Red\n"
						+ "    '2': Red\n"
						+ "    '3': DYNOMITE-KABOOM\n"
		);
	}
}
