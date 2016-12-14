package th.ac.cmu.eng.cpe.cpe200.utils;

import th.ac.cmu.eng.cpe.cpe200.sprites.Meteoroid;

import java.util.ArrayList;

/**
 * Created by zalzer on 12/14/2016 AD.
 */
public class GameLevelManager {

    public static MeteoroidSpec level[][][] = new MeteoroidSpec[][][]{
            {
                    // wave 1
                    {
                            new MeteoroidSpec(1, new int[]{1, 2}),
                            new MeteoroidSpec(3, new int[]{2, 3}),
                            new MeteoroidSpec(5, new int[]{1, 4}),
                    },
                    // wave 2
                    {
                            new MeteoroidSpec(1, new int[]{1, 2, 1}),
                            new MeteoroidSpec(10, new int[]{2, 3}),
                            new MeteoroidSpec(5, new int[]{1, 2, 2}),
                            new MeteoroidSpec(1, new int[]{3, 2, 3}, 1000),
                            new MeteoroidSpec(7, new int[]{2, 3}, 1000),
                            new MeteoroidSpec(4, new int[]{1, 3}, 2000),
                    },
                    // Boss
                    {
                            new MeteoroidSpec(1, new int[]{1, 2, 3, 3, 2, 1, 2, 1, 2}),
                    }
            },
    };

    // Position 8 axis
    // 1 TOP
    // 2 TOP RIGHT
    // 3 RIGHT
    // 4 BOTTOM RIGHT
    // 5 BOTTOM
    // 6 BOTTOM LEFT
    // 7 LEFT
    // 8 Top LEFT

    // HP int
    // 0 = Horizontal
    // 1 = Vertical
    // 2 = Caret
    // 3 = Triangle
    // 4 = Circle (heal)
    // 5 = Lighting (attack all at once)

    public static class MeteoroidSpec {
        public int position; // create position
        public int[] hp; // hp list
        public int delay; // milisecond

        public MeteoroidSpec(int position, int[] hp) {
            this.position = position;
            this.hp = hp;
            this.delay = 0;
        }

        public MeteoroidSpec(int position, int[] hp, int delay) {
            this.position = position;
            this.hp = hp;
            this.delay = delay;
        }
    }
}
