package th.ac.cmu.eng.cpe.cpe200.containers;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class Histogram {
    // Index
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int UP_LEFT = 4;
    public static final int UP_RIGHT = 5;
    public static final int DOWN_LEFT = 6;
    public static final int DOWN_RIGHT = 7;

    int[] histogram;
    int count;

    public Histogram() {
        histogram = new int[8];
        count = 0;
    }

    public Histogram(int[] his) {
        histogram = his;
        count = 0;
        for (int c :
                histogram) {
            count += c;
        }
    }

    public Histogram(int up, int down, int left, int right, int upLeft, int upRight, int downLeft, int downRight) {
        histogram = new int[]{up, down, left, right, upLeft, upRight, downLeft, downRight};
        count = up + down + left + right + upLeft + upRight + downLeft + downRight;
    }

    public void add(int direction) {
        histogram[direction]++;
        count++;
    }

    public void add(int direction, int amount) {
        histogram[direction] += amount;
        count++;
    }

    public double getHk() {
        return (double) count / 8d;
    }

    public double correlationTo(Histogram his2) {
        return 0; // TODO: ว่างค่อยทำ
    }

    public double compareTo(Histogram his2) {
        double error = 0d, d1, d2;
        int[] histogram2 = his2.getArray();
        int count2 = his2.getCount();

        for (int i = 0; i < 8; i++) {
            // Normalize
            d1 = histogram[i] / (double) count;
            d2 = histogram2[i] / (double) count2;
            error += Math.pow(d1 - d2, 2);
        }

        error = Math.sqrt(error);

        return error;

    }

    public int[] getArray() {
        return histogram;
    }

    public int getCount() {
        return count;
    }

}
