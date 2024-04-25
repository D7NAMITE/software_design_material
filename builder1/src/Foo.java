public class Foo {
    private int x;
    private float y;
    private String z;

    public Foo(int x, float y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static class Builder {
        private int x;
        private float y;
        private String z;

        public Builder setX(int x) {
            this.x = x;
            return this;
        }

        public Builder setY(float y) {
            this.y = y;
            return this;
        }

        public Builder setZ(String z) {
            this.z = z;
            return this;
        }

        public Foo build() {
            return new Foo(x, y, z);
        }
    }

    public void print() {
        System.out.println(x + ", " + y + ", " + z);
    }

    public static void main(String[] args) {
        Foo foo = new Foo.Builder()
                .setX(3)
                .setY(1.2f)
                .setZ("hello")
                .build();
        foo.print();
    }
}
