package nl.b3p.suf2;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2Math {

    /**
     * Calculate the angle of a line (two coordinates)
     * @param coordinates List with two coordinates
     * @return Angle in degrees
     */
    /*
    public static double angle(List<SUF2Coordinate> coordinates) throws Exception {
        if (coordinates.size() == 2) {
            SUF2Coordinate c1 = coordinates.get(0);
            SUF2Coordinate c2 = coordinates.get(1);

            return angle(c1, c2);
        } else {
            throw new Exception("Number of coordinates is not equal to 2 (size = " + coordinates.size() + "); unable to calculate angle between points");
        }
    }
     * */

    /**
     * Calculate the angle of a line (two coordinates)
     * @param c1 First coordinate
     * @param c2 Second coordinate
     * @return Angle in degrees
     */
    public static double angle(SUF2Coordinate c1, SUF2Coordinate c2) {

        double dx = c2.x - c1.x;
        double dy = c2.y - c1.y;
        double angle;

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

    /**
     * Calculate the middle point between two coordinates
     * @param c1 First Coordinate
     * @param c2 Second Coordinate
     * @return Middle coordinate
     */
    public static SUF2Coordinate middle(SUF2Coordinate c1, SUF2Coordinate c2) {
        List<SUF2Coordinate> coordinates = new ArrayList<SUF2Coordinate>();
        coordinates.add(c1);
        coordinates.add(c2);

        return middle(coordinates);
    }

    /**
     * Calculate the middle point between multiple coordinates
     * @param coordinates A list of coordinates
     * @return Middle coordinate
     */
    public static SUF2Coordinate middle(List<SUF2Coordinate> coordinates) {
        double x = 0.0;
        double y = 0.0;

        for (int i = 0; i < coordinates.size(); i++) {
            x += coordinates.get(i).x;
            y += coordinates.get(i).y;
        }

        return new SUF2Coordinate(x / coordinates.size(), y / coordinates.size());
    }

    /**
     * Calculate the distance between two coordinates
     * @param c1 First coordinate
     * @param c2 Second coordinate
     * @return
     */
    public static double distance(SUF2Coordinate c1, SUF2Coordinate c2) {
        return Point2D.distance(c1.x, c1.y, c2.x, c2.y);
    }
}
