import java.util.Comparator;

public class CustomComparator implements Comparator<LabelledPoint> {

    public int compare(LabelledPoint p1, LabelledPoint p2) {
        Double key1 = p1.getKey();
        Double key2 = p2.getKey();
        return key2.compareTo(key1);
    }
}

