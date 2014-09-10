package extend;

public class TriggerableActionConfig extends ActionConfig
{
    public TriggerableActionConfig()
    {
    }

    public TriggerableActionConfig(String trigger, String action)
    {
        super(action);
        this.trigger = trigger;
    }

    public String trigger = "default trigger";
}
