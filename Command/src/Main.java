public class Main {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        LightCommand OnCommand = new LightOnCommand(livingRoomLight);
        LightCommand OffCommand = new LightOffCommand(livingRoomLight);

        RemoteControl rc = new RemoteControl(OnCommand, OffCommand);
        rc.buttonOnClicked();
        rc.buttonOffClicked();
    }
}
