public class Box implements Object3D {
    private float width;
    private float height;
    private float depth;

    public Box(float width, float height, float depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }


    @Override
    public float getVolume() {
        return width * height * depth;
    }
}
