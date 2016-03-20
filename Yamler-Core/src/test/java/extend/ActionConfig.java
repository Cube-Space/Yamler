package extend;

import net.cubespace.Yamler.Config.YamlConfig;

public class ActionConfig extends YamlConfig
{
    public ActionConfig()
    {
    }

    public ActionConfig(String action)
    {
        this.action = action;
    }

    public String action = "default action";
}