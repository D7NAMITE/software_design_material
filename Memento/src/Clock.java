public class Clock {
    private int counter;

    public void tick() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public Memento save() {
        return new Memento(counter);
    }

    public void load(Memento memento) {
        counter = memento.savedCounter;
    }

    public static class Memento {
        private int savedCounter;

        private Memento(int counter) {
            this.savedCounter = counter;
        }
    }
}