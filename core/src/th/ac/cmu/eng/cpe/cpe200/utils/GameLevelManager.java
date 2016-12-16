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
                            new MeteoroidSpec(1, new int[]{0}),
                            new MeteoroidSpec(2, new int[]{1}),
                            new MeteoroidSpec(5, new int[]{0}),
                            new MeteoroidSpec(2, new int[]{0}, 1000),
                            new MeteoroidSpec(3, new int[]{2}, 1000),
                            new MeteoroidSpec(6, new int[]{3}, 1000),
                            new MeteoroidSpec(7, new int[]{1}, 1000),
                    },
                    // wave 2
                    {
                            new MeteoroidSpec(1, new int[]{1, 2}),
                            new MeteoroidSpec(3, new int[]{2, 3}),
                            new MeteoroidSpec(6, new int[]{1, 3}),
                            new MeteoroidSpec(1, new int[]{1, 2}, 1000),
                            new MeteoroidSpec(3, new int[]{2, 3}, 1000),
                            new MeteoroidSpec(6, new int[]{1, 3}, 1300),
                    },
                    // wave 3
                    {
                            new MeteoroidSpec(1, new int[]{0}),
                            new MeteoroidSpec(2, new int[]{1}),
                            new MeteoroidSpec(3, new int[]{2}),
                            new MeteoroidSpec(4, new int[]{3}),
                            new MeteoroidSpec(5, new int[]{0}, 2000),
                            new MeteoroidSpec(6, new int[]{1}, 2300),
                            new MeteoroidSpec(7, new int[]{2}, 2600),
                            new MeteoroidSpec(8, new int[]{3}, 2900),
                    },
                    // Boss
                    {
                            new MeteoroidSpec(7, new int[]{1, 2, 3, 3, 2, 1, 2, 1, 2}), //slow

                    }
            },
            {
                    // wave 1
                    {
                            new MeteoroidSpec(2, new int[]{3, 1}),
                            new MeteoroidSpec(6, new int[]{5}),
                            new MeteoroidSpec(7, new int[]{0, 1}),
                            new MeteoroidSpec(9, new int[]{1, 1}),
                            new MeteoroidSpec(10, new int[]{2, 1}),
                    },
                    // wave 2
                    {
                            new MeteoroidSpec(2, new int[]{3, 0}),
                            new MeteoroidSpec(7, new int[]{0, 1}),
                            new MeteoroidSpec(9, new int[]{1, 2}),
                            new MeteoroidSpec(10, new int[]{2, 3}),
                            new MeteoroidSpec(1, new int[]{3, 0}, 1500),
                            new MeteoroidSpec(4, new int[]{2, 3}, 1500),
                            new MeteoroidSpec(8, new int[]{0, 1}, 2000),
                            new MeteoroidSpec(6, new int[]{1, 2}, 2000),
                            new MeteoroidSpec(4, new int[]{5}, 2200),
                    },
                    // wave 3
                    {
                            new MeteoroidSpec(1, new int[]{1}),
                            new MeteoroidSpec(3, new int[]{1}),
                            new MeteoroidSpec(5, new int[]{0}),
                            new MeteoroidSpec(7, new int[]{1}),
                            new MeteoroidSpec(10, new int[]{0}),
                            new MeteoroidSpec(2, new int[]{2, 0, 1}, 1500),
                            new MeteoroidSpec(4, new int[]{2, 0, 1}, 1500),
                            new MeteoroidSpec(6, new int[]{2, 0, 0}, 1500),
                            new MeteoroidSpec(8, new int[]{3, 0, 0}, 1500),
                            new MeteoroidSpec(9, new int[]{3, 0, 1}, 1500),
                    },
                    // wave 4
                    {
                            new MeteoroidSpec(6, new int[]{1}, 0),
                            new MeteoroidSpec(6, new int[]{5}, 300),
                            new MeteoroidSpec(7, new int[]{2}, 600),
                            new MeteoroidSpec(8, new int[]{3}, 900),
                            new MeteoroidSpec(8, new int[]{0}, 1200),
                            new MeteoroidSpec(10, new int[]{3, 1}, 1500),
                            new MeteoroidSpec(10, new int[]{1}, 1800),
                            new MeteoroidSpec(4, new int[]{2}, 2100),
                            new MeteoroidSpec(3, new int[]{1, 3}, 2400),
                            new MeteoroidSpec(3, new int[]{2, 0}, 2700),
                            new MeteoroidSpec(2, new int[]{3, 1}, 3000),
                    },
                    // Boss
                    {
                            new MeteoroidSpec(6, new int[]{1, 0, 1}),
                            new MeteoroidSpec(7, new int[]{1, 1, 1, 0, 0, 2, 0, 0, 1, 1, 3, 1, 1, 1, 0, 2, 0, 1}, 500), //slow
                            new MeteoroidSpec(8, new int[]{0, 1, 0}, 1000),
                            new MeteoroidSpec(6, new int[]{2, 3}, 2000),
                            new MeteoroidSpec(8, new int[]{3, 0}, 2000),
                    },
            },
            {
                    // wave 1
                    {
                            new MeteoroidSpec(4, new int[]{3, 0, 2}),
                            new MeteoroidSpec(7, new int[]{1, 0, 2}),
                            new MeteoroidSpec(9, new int[]{1, 3}, 3500),
                    },
                    // wave 2
                    {
                            new MeteoroidSpec(5, new int[]{0, 2, 0, 3, 0, 2, 0, 3}),
                            new MeteoroidSpec(8, new int[]{1, 2, 1, 3, 1, 2, 1, 3}),
                            new MeteoroidSpec(5, new int[]{0, 2, 0, 3, 0, 2, 0, 3}),

                    },
                    // wave 3
                    {
                            new MeteoroidSpec(2, new int[]{1, 3, 1, 0}),
                            new MeteoroidSpec(4, new int[]{1, 0, 1, 2}),
                            new MeteoroidSpec(2, new int[]{0, 3, 2, 0}),
                            new MeteoroidSpec(3, new int[]{5}, 2500),
                            new MeteoroidSpec(4, new int[]{3, 3, 0}, 2800),
                            new MeteoroidSpec(6, new int[]{2, 2, 0}, 3100),
                            new MeteoroidSpec(7, new int[]{0}, 3400), //speed increase
                            new MeteoroidSpec(2, new int[]{1}, 4000), //speed increase
                            new MeteoroidSpec(1, new int[]{0, 2, 0, 3, 0, 2, 0, 3}, 5500),
                            new MeteoroidSpec(5, new int[]{1, 2, 1, 3, 1, 2, 1, 3}, 6000),
                    },
                    // wave 4
                    {
                            new MeteoroidSpec(3, new int[]{0, 1, 0, 1, 0, 2, 2, 3}),
                            new MeteoroidSpec(8, new int[]{1, 0, 1, 0, 1, 2, 3, 2}),
                            new MeteoroidSpec(5, new int[]{0, 2, 0, 3, 0, 2, 0, 3}),
                            new MeteoroidSpec(1, new int[]{1, 2, 3, 0}, 5000), //speed increase
                            new MeteoroidSpec(4, new int[]{0, 0, 3, 1}, 6000), //speed increase
                            new MeteoroidSpec(6, new int[]{5}, 7000), //speed increase
                            new MeteoroidSpec(10, new int[]{1, 1, 2, 0}, 8500),
                            new MeteoroidSpec(9, new int[]{0, 3, 3, 0}, 9000), //speed increase
                            new MeteoroidSpec(2, new int[]{1, 2, 3, 0}, 10000),
                            new MeteoroidSpec(10, new int[]{3, 1, 3, 0}, 10500), //speed increase
                    },
                    // Boss
                    {
                            new MeteoroidSpec(5, new int[]{3, 0, 3, 1}),
                            new MeteoroidSpec(8, new int[]{3, 0, 3, 1}),
                            new MeteoroidSpec(7, new int[]{1, 1, 1, 0, 0, 0, 2, 2, 2, 3, 3, 3, 0, 1, 2, 3, 0, 1, 2, 3, 3, 2, 2, 1, 0, 1, 0}, 500), //slow
                            new MeteoroidSpec(2, new int[]{0, 1, 0}, 3000),
                            new MeteoroidSpec(3, new int[]{2, 3}, 3500),
                            new MeteoroidSpec(2, new int[]{3, 3}, 4000),
                            new MeteoroidSpec(3, new int[]{2, 3}, 4500),
                            new MeteoroidSpec(9, new int[]{1, 0}, 5500), //speed increase
                            new MeteoroidSpec(10, new int[]{0, 1}, 6000), //speed increase
                    }
            }
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
