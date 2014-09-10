package extend;

import net.cubespace.Yamler.Config.Config;

public class ActionConfig extends Config
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