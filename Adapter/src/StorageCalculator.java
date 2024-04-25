import java.util.ArrayList;
import java.util.List;

public class StorageCalculator {
    public float totalVolume(List<Object3D> objects) {
        float total = 0;
        for(Object3D obj : objects) {
            total += obj.getVolume();
        }
        return total;
    }

    public static void main(String[] args) {
        StorageCalculator cal = new StorageCalculator();
        List<Object3D> objects = new ArrayList<>();
        objects.add(new Box(3,3,3));
        objects.add(new Box(1, 2, 1));
        for (int i = 0; i < 1000; i++) {
            objects.add(new Paper3DAdapter(new Paper(3, 4)));
        }
        System.out.println(cal.totalVolume(objects));
    }
}
