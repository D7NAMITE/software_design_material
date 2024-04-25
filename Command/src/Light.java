public class Light {
    private boolean on;

    public void turnOn() {
        on = true;
        System.out.println("Light is ON");
    }

    public void turnOff() {
        on = false;
        System.out.println("Light is OFF");
    }
}
