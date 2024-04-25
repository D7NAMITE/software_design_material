public class LightOnCommand extends LightCommand {
    public LightOnCommand(Light light) {
        super(light);
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}
