public class Main {
    public static void main(String[] args) {
        Clock clock = new Clock();
        clock.tick();
        clock.tick();
        Clock.Memento m1 = clock.save();
        System.out.println(clock.getCounter());
        clock.tick();
        clock.tick();
        clock.load(m1);
        System.out.println(clock.getCounter());
    }
}
