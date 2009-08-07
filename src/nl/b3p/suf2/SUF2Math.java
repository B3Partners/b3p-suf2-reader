package nl.b3p.suf2;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2Math {

    public static double calculateAngle(List<SUF2Coordinate> coordinates) throws Exception {

        if (coordinates.size() == 2) {
            SUF2Coordinate c1 = coordinates.get(0);
            SUF2Coordinate c2 = coordinates.get(1);
            /*
            double x = Math.abs(c1.x - c2.x);
            double y = Math.abs(c1.y - c2.y);

            return calculateQuadrant(c1, c2, x, y);
             */


            return angle(c1, c2);
        } else {
            throw new Exception("Number of coordinates is not equal to 2 (size = " + coordinates.size() + "); unable to calculate angle between points");
        }
    }

    public static double calculateQuadrant(SUF2Coordinate c1, SUF2Coordinate c2, double x, double y) {

        if (y == 0) {
            return 0;
        }


        if (c1.x > c2.x && c1.y > c2.y) {
            return Math.toDegrees(Math.tanh(y / x)) + 180; // 24

        } else if (c1.x <= c2.x && c1.y > c2.y) {
            // return 0;
            return 360 - Math.toDegrees(Math.tanh(y / x)); // Nijverheidslaan

            //return Math.toDegrees(Math.tanh(x / y)) + 270; // Nijverheidslaan

        } else if (c1.x > c2.x && c1.y <= c2.y) {
            return Math.toDegrees(Math.tanh(y / x)) + 90; // done 25

            //} else if (c1.x <= c2.x && c1.y <= c2.y) {
        } else {
            // done 23
            return 90 - Math.toDegrees(Math.tanh(x / y));
        }
    }

    public static SUF2Coordinate middle(SUF2Coordinate c1, SUF2Coordinate c2) {
        List<SUF2Coordinate> coordinates = new ArrayList<SUF2Coordinate>();
        coordinates.add(c1);
        coordinates.add(c2);

        return middle(coordinates);
    }

    public static SUF2Coordinate middle(List<SUF2Coordinate> coordinates) {
        double x = 0.0;
        double y = 0.0;

        for (int i = 0; i < coordinates.size(); i++) {
            x += coordinates.get(i).x;
            y += coordinates.get(i).y;
        }

        return new SUF2Coordinate(x / coordinates.size(), y / coordinates.size());
    }

    public static double distance(SUF2Coordinate c1, SUF2Coordinate c2) {
        return Point2D.distance(c1.x, c1.y, c2.x, c2.y);
    }

    public static double angle(SUF2Coordinate a, SUF2Coordinate b) {

        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        double angle = 0.0;

        if (dx == 0.0) {
            if (dy == 0.0) {
                angle = 0.0;
            } else if (dy > 0.0) {
                angle = Math.PI / 2.0;
            } else {
                angle = (Math.PI * 3.0) / 2.0;
            }
        } else if (dy == 0.0) {
            if (dx > 0.0) {
                angle = 0.0;
            } else {
                angle = Math.PI;
            }
        } else {
            if (dx < 0.0) {
                angle = Math.atan(dy / dx) + Math.PI;
            } else if (dy < 0.0) {
                angle = Math.atan(dy / dx) + (2 * Math.PI);
            } else {
                angle = Math.atan(dy / dx);
            }
        }
        return (angle * 180) / Math.PI;
    }
}
