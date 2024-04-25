public class Paper3DAdapter implements Object3D {
    private Paper paper;

    public Paper3DAdapter(Paper paper) {
        this.paper = paper;
    }

    @Override
    public float getVolume() {
        return paper.getArea() *  0.001f;
    }
}
