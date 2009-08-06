/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.suf2.records;

import java.awt.Point;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.b3p.suf2.SUF2ParseException;

/**
 *
 * @author Gertjan
 */
public class SUF2Record04 extends SUF2Record {
    public static final String SUBRECORDTYPE = "subrecordtype";

    public SUF2Record04(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public Map getCurrentProperties() {
        Map properties = new HashMap();

        line.setShift(2);

        List<Point> coordinates = new ArrayList();


        while (line.getShift() < 54) {
            if (line.charAt(1) == 'X') {
                String x = line.part(2, 10);

                line.shift(10);

                if (line.charAt(1) == 'Y') {
                    String y = line.part(2, 10);
                    Point coordinate = new Point(
                            Integer.parseInt(x),
                            Integer.parseInt(y));

                    coordinates.add(coordinate);

                    hasGeometry = true;
                }
            } else if (line.charAt(1) == 'I') {
                properties.put(SUBRECORDTYPE, line.part(2));
            }

            line.shift(10);
        }

        if (hasGeometry) {
            properties.put(COORDINATELIST, coordinates);
        }



//        properties.put(RD, line.part(3, 4));
//        properties.put(LKI, line.part(5, 6));
//        properties.put(COORD_MILLIMETERS, line.part(7));
//        properties.put(RICHTINGEN_MICROGON, line.part(8));
//        properties.put(LKI_SYMBOOL, line.part(9, 10));
//        properties.put(NAP, line.part(11, 12));
//        properties.put(HEEFT_OPTEL_X, line.part(22));
//        properties.put(HEEFT_OPTEL_Y, line.part(32));
//        properties.put(VERMENIGVULDIGINGSCONSTANTE_XY, line.part(42));
//        properties.put(HEEFT_OPTEL_Z, line.part(52));
//        properties.put(VERMENIGVULDIGINGSCONSTANTE_Z, line.part(62));

        return properties;
    }
}
