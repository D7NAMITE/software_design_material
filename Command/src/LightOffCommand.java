public class LightOffCommand extends LightCommand {
    public LightOffCommand(Light light) {
        super(light);
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}
