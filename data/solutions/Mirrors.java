
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;
import static java.util.Arrays.deepToString;

/**
 * @author merrillm
 */
public class Mirrors {

    private static int assertCount = 0;
    private static void assertEquals(Object a, Object b) {
        assertCount++;

        if (a == null ^ b == null || (a != null && !a.equals(b))) {
            System.out.printf("%d.\tExpected: %s; Got: %s\n", assertCount,
                    (a instanceof Object[])?deepToString((Object[])a):a,
                    (b instanceof Object[])?deepToString((Object[])b):b);
        }
    }

    public static void main(String[] args) {
        //System.out.println(allThingsConsidered(new int[]{3,2}, 4, new int[]{1,2}, new int[]{1, 1}, 1, 0));
        Scanner scn = new Scanner(System.in);

        int[] dim = { scn.nextInt(), scn.nextInt() };
        int[] tur = { scn.nextInt(), scn.nextInt() };
        int[] som = { scn.nextInt(), scn.nextInt()};
        float dist = scn.nextFloat();

        System.out.println(answer(dim, tur, som, dist));
/*
        assertEquals(7, answer(new int[]{3,3}, new int[]{1,1}, new int[]{2,2}, 5));
        assertEquals(7, answer(new int[]{3,2}, new int[]{1,1}, new int[]{2,1}, 4));
        assertEquals(9, answer(new int[]{300,275}, new int[]{150,150}, new int[]{185,100}, 500));*/
    }

    public static int answer(int[] dim, int[] cptPos, int[] badPos, float dist) {

        int[] cx = new int[] { cptPos[0], dim[0] - cptPos[0] };
        int[] cy = new int[] { cptPos[1], dim[1] - cptPos[1] };

        int bx = badPos[0]-cptPos[0];
        int by = badPos[1]-cptPos[1];

        Set<Thing> thingsInRange = allThingsConsidered(dim, dist, cx, cy, bx, by);
        Map<Point, Thing> closestAtVector = new HashMap<>();

        for (Thing thing : thingsInRange) {

            Point vector = reduceVector(thing);
            Thing otherAtVector = closestAtVector.get(vector);

            if (otherAtVector == null || thing.distanceSq()<otherAtVector.distanceSq())
                closestAtVector.put(vector, thing);
        }

        int retcount = 0;
        for (Thing thing : closestAtVector.values()) {
            if (thing.what == Thing.What.CAPTN)
                continue;

            if (thing.distanceSq() <= dist * dist)
                retcount++;
        }
        return retcount;
    }

    private static Set<Thing> allThingsConsidered(int[] dim, float dist, int[] cx, int[] cy, int bx, int by) {

        Set<Thing> things = new HashSet<>();

        int xtiles = (int) ceil(dist*1.0 / dim[0]) + 4;
        int ytiles = (int) ceil(dist*1.0 / dim[1]) + 4;
        for (int tilex = -xtiles; tilex <= xtiles; tilex++) {
            for (int tiley = -ytiles; tiley <= ytiles; tiley++) {

                int xpos = tilex * dim[0];
                int ypos = tiley * dim[1];

                if (tilex % 2 != 0)
                    xpos -= cx[0] - cx[1];

                if (tiley % 2 != 0)
                    ypos -= cy[0] - cy[1];

                Thing captain = new Thing(xpos, ypos, Thing.What.CAPTN);

                if (captain.distanceSq() <= dist*dist)
                    things.add(captain);

                xpos += bx * Math.pow(-1, tilex&1);
                ypos += by * Math.pow(-1, tiley&1);

                Thing baddy = new Thing(xpos, ypos, Thing.What.BADDY);

                if (baddy.distanceSq() <= dist*dist)
                    things.add(baddy);
            }
        }

        return things;
    }


    // Please allow use of java.awt.Point.
    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x<<16+y;
        }

        @Override
        public boolean equals(Object obj) {
            try {
                Point other = (Point) obj;
                return (this.x == other.x) && (this.y == other.y);
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", x, y);
        }

        public int distanceSq() {
            return x*x + y*y;
        }
    }

    private static class Thing extends Point {
//        Enums might be breaking FooBar?
//        enum What { CAPTN, BADDY }

        public static class What {
            public static int CAPTN = 1;
            public static int BADDY = -1;
        }

        //        What what;
        int what;
        Thing(int x, int y, int what) {
            super(x,y);
            this.what = what;
        }
        public String toString() {
            return String.format("%s:(%d,%d)",what,x,y);
        }
    }

    private static Point reduceVector(Point p) {
        return reduceVector(p.x, p.y);
    }

    private static Point reduceVector(int x, int y) {
        int gcd = abs(gcd(x,y));

        if (x == 0)
            return new Point(0, y==0?0:(y/abs(y)));
        if (y == 0)
            return new Point(x/abs(x), 0);

        return new Point(x/gcd, y/gcd);
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, ((a % b) + b) % b);
    }
}
