package ivk.danilo.movies2watch.helpers;

public class Comparator {
    // String
    public static int string(String first, String second, boolean desc) {
        int result = first.compareTo(second);
        return desc ? result : -result;
    }

    public static int stringAsc(String first, String second) {
        return string(first, second, false);
    }

    public static int stringDesc(String first, String second) {
        return string(first, second, true);
    }

    // Int
    public static int intAsc(int first, int second) {
        return first - second;
    }

    public static int intDesc(int first, int second) {
        return second - first;
    }

    // Double
    public static int doubleAsc(double first, double second) {
        return first != second
                ? first > second ? -1 : 1
                : 0;
    }

    public static int doubleDesc(double first, double second) {
        return first != second
                ? first > second ? 1 : -1
                : 0;
    }
}
