/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.suf2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gertjan
 */
public class SUF2Math {

    public static double calculateAngle(List<SUF2Coordinate> coordinates) throws Exception {

        if (coordinates.size() == 2) {
            SUF2Coordinate c1 = coordinates.get(0);
            SUF2Coordinate c2 = coordinates.get(1);

            double x = Math.abs(c1.x - c2.x);
            double y = Math.abs(c1.y - c2.y);

            return calculateQuadrant(c1, c2, x, y);

        } else {
            throw new Exception("Number of coordinates is not equal to 2 (size = " + coordinates.size() + "); unable to calculate angle between points");
        }
    }

    public static double calculateQuadrant(SUF2Coordinate c1, SUF2Coordinate c2, double x, double y) {

        if(y == 0){
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

    public static SUF2Coordinate getMiddle(SUF2Coordinate c1, SUF2Coordinate c2) {
        List<SUF2Coordinate> coordinates = new ArrayList<SUF2Coordinate>();
        coordinates.add(c1);
        coordinates.add(c2);

        return getMiddle(coordinates);
    }

    public static SUF2Coordinate getMiddle(List<SUF2Coordinate> coordinates) {
        double x = 0.0;
        double y = 0.0;

        for (int i = 0; i < coordinates.size(); i++) {
            x += coordinates.get(i).x;
            y += coordinates.get(i).y;
        }

        return new SUF2Coordinate(x / coordinates.size(), y / coordinates.size());
    }
}
