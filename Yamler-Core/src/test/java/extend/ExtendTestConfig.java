package extend;

import net.cubespace.Yamler.Config.Config;

import java.io.File;

public class ExtendTestConfig extends Config
{
    public ActionConfig action = new ActionConfig();
    public TriggerableActionConfig trigAct = new TriggerableActionConfig();
    public ActionConfig actionCtor = new ActionConfig("another action (2)");
    public TriggerableActionConfig trigActCtor = new TriggerableActionConfig("another trigger (3)", "yet another action (3)");
}
