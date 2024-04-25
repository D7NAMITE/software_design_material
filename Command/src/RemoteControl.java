public class RemoteControl {
    private LightCommand buttonOnCommand;
    private LightCommand buttonOffCommand;

    public RemoteControl(LightCommand buttonOnCommand, LightCommand buttonOffCommand) {
        this.buttonOnCommand = buttonOnCommand;
        this.buttonOffCommand = buttonOffCommand;
    }

    public void buttonOnClicked() {
        buttonOnCommand.execute();
    }

    public void buttonOffClicked() {
        buttonOffCommand.execute();
    }
}