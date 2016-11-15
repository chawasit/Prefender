package th.ac.cmu.eng.cpe.cpe200.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Bresenham2;
import com.badlogic.gdx.math.GridPoint2;
import th.ac.cmu.eng.cpe.cpe200.Prefender;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class GestureDetection {
    private static final String TAG = GestureDetection.class.getSimpleName();
    private static final int GRID_DIAMETER = 10;
    private static final double MAX_ERROR = 0.20d;
    private static final int CIRCLE_RADIUS = 3;

    int[] histogram;

    // Draw Touch Line
    private Pixmap pixmap;
    private Texture touchLine;

    // Store Vector and Last Interesting Point
    private ArrayList<GridPoint2> pointStore;
    private GridPoint2 lastInterestingPoint, lastTouchPoint;

    // Memorize
    private int lastID;

    public GestureDetection() {
        histogram = new int[9];
        pointStore = new ArrayList<GridPoint2>();
    }


    private void createPixmap() {
        if (pixmap != null)
            pixmap.dispose();
        pixmap = new Pixmap(Prefender.WIDTH, Prefender.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.LIGHT_GRAY);
    }

    public void render(SpriteBatch batch) {
        if (pixmap != null) {
            if (touchLine != null)
                touchLine.dispose();
            touchLine = new Texture(pixmap, true);
            touchLine.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.MipMapLinearLinear);
            batch.draw(touchLine, 0, 0);
        }
    }

    /**
     * เรียกใช้ตอนเริ่มมีการสัมผัส
     *
     * @param firstPoint
     */
    public void init(GridPoint2 firstPoint) {
        createPixmap();
        histogram = new int[9];
        lastTouchPoint = firstPoint;
        lastInterestingPoint = firstPoint;
        pointStore.add(firstPoint);
        lastID = -1;
    }

    /**
     * จบการทำงาน เคลียร์ค่าต่าง ๆ
     */
    public int finish() {
        if (pixmap != null)
            pixmap.dispose();
        pixmap = null;
        histogram = null;
        pointStore.clear();
        lastInterestingPoint = null;
        return lastID;
    }

    /**
     * เพิ่มจุดที่สัมผัส
     *
     * @param newPoint
     */
    public void addPoint(GridPoint2 newPoint) {
        // Draw Line between point
        for (GridPoint2 p :
                new Bresenham2().line(lastTouchPoint, newPoint)) {
            pixmap.fillCircle(p.x, p.y, CIRCLE_RADIUS);
            pointStore.add(p);
        }
        pointStore.add(newPoint);
        lastTouchPoint = newPoint;

        // Extract Vector Info
        double deltaX = newPoint.x - lastInterestingPoint.x,
                deltaY = newPoint.y - lastInterestingPoint.y;
        double diameter = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // not interesting
        if (Math.abs(deltaX) > GRID_DIAMETER || Math.abs(deltaY) > GRID_DIAMETER) {
            // Calculate Vector Degree
            double degree = Math.toDegrees(Math.atan2(deltaY, deltaX));
            addHistogram(degree);

            lastInterestingPoint = newPoint;

            // try to detect shape
            gestureColor(detectShape());
        }
    }

    private void addHistogram(double degree) {
        histogram[8]++;
        if (degree <= 22.5 && degree >= -22.5) {
            histogram[0]++; // RIGHT
        } else if (degree >= -180 && degree <= -157.5 || degree <= 180 && degree >= 157.5) {
            histogram[1]++; // LEFT
        } else if (degree <= -67.5 && degree >= -112.5) {
            histogram[2]++; // UP
        } else if (degree <= 112.5 && degree >= 67.5) {
            histogram[3]++; // DOWN
        } else if (degree <= -22.5 && degree >= -67.5) {
            histogram[4]++; // UP-RIGHT
        } else if (degree <= 67.5 && degree >= 22.5) {
            histogram[5]++; // DOWN-RIGHT
        } else if (degree <= -112.5 && degree >= -157.5) {
            histogram[6]++; // UP-LEFT
        } else if (degree <= 157.5 && degree >= 112.5) {
            histogram[7]++; // DOWN-LEFT
        } else {
            histogram[8]--;
            Gdx.app.log(TAG, "ERROR degree " + degree);
        }
    }

    /**
     * Compare current histogram with all sample datas
     *
     * @return id of Most Matched Sample
     */
    private int detectShape() {
        if (histogram[8] == 0)
            return -1;
        int ret = -1;
        double min_error = 100000000000d;

        int[][][] samples = GestureSample.samples;

        for (int dataset_id = 0; dataset_id < samples.length; dataset_id++) {
            for (int sample_id = 0; sample_id < samples[dataset_id].length; sample_id++) {
                double error = compareHistogram(histogram, samples[dataset_id][sample_id]);
                if (error < min_error) {
                    min_error = error;
                    ret = dataset_id;
                }
            }
        }

        return (min_error < MAX_ERROR) ? ret : -1;
    }


    private void gestureColor(int id) {
        if (id != lastID) {
            lastID = id;
            createPixmap();
            setLineColor(id);
            for (GridPoint2 p :
                    pointStore) {
                pixmap.fillCircle(p.x, p.y, CIRCLE_RADIUS);
            }
        }
    }

    /**
     * set color
     *
     * @param id
     */
    private void setLineColor(int id) {
        if (pixmap == null) return;
        switch (id) {
            case 0:
                pixmap.setColor(Color.RED);
                break;
            case 1:
                pixmap.setColor(Color.BLUE);
                break;
            case 2:
                pixmap.setColor(Color.FOREST);
                break;
            case 3:
                pixmap.setColor(Color.BROWN);
                break;
            case 4:
                pixmap.setColor(Color.CYAN);
                break;
            case 5:
                pixmap.setColor(Color.PINK);
                break;
            case 6:
                pixmap.setColor(Color.YELLOW);
                break;
            default:
                pixmap.setColor(Color.LIGHT_GRAY);
                break;
        }
    }

    // Calculate Distant between Histogram using Euclidean Distant
    private double compareHistogram(int[] his1, int[] his2) {
        double error = 0d, d1, d2;

        for (int i = 0; i < 8; i++) {
            // Normalize
            d1 = his1[i] / (double) his1[8];
            d2 = his2[i] / (double) his2[8];
            // sum error
            error += Math.pow(d1 - d2, 2);
        }

        error = Math.sqrt(error);

        return error;
    }

    public int[] getHistogram() {
        return histogram;
    }
}
