package th.ac.cmu.eng.cpe.cpe200.utils;

/**
 * Created by zalzer on 11/8/2016 AD.
 */
public class GestureSample {
    public static final int[][][] samples = {
            // Horizontal Line
            {
                    {15, 0, 0, 0, 0, 0, 0, 0, 15},
                    {0, 15, 0, 0, 0, 0, 0, 0, 15},
            },
            // Vertical Line
            {
                    {0, 0, 15, 0, 0, 0, 0, 0, 15},
                    {0, 0, 0, 15, 0, 0, 0, 0, 15},
            },
            // Caret
            {
                    {0, 0, 0, 0, 15, 15, 0, 0, 30},
                    {0, 0, 0, 0, 0, 0, 15, 15, 30},
            },
            // Rectangle
            {
                    {5, 5, 5, 5, 0, 0, 0, 0, 20},
            },
            // Triangle
            {
//                    {},
//                    {},
//                    {},
            },
            // Circle
            {
                    {5, 5, 5, 5, 5, 5, 5, 5, 40},
            },
    };
}
