public abstract class LightCommand {
    protected Light light;

    public LightCommand(Light light) {
        this.light = light;
    }

    public abstract void execute();
}
