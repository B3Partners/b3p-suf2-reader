package nl.b3p.suf2.records;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nl.b3p.suf2.SUF2Coordinate;
import nl.b3p.suf2.SUF2ParseException;
import nl.b3p.suf2.SUF2ValueFinder;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2Record04 extends SUF2Record {

    // I
    public static final String I_COORD_FUNCTIE = "functie van het coordinaatpunt";
    // Q
    public static final String Q_PRECISIEKLASSE = "precisieklasse";
    public static final String Q_IDEALISATIEKLASSE = "idealisatieklasse";
    public static final String Q_BETROUWBAARHEID = "betrouwbaarheid";

    public SUF2Record04(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public SUF2Record04(LineNumberReader lineNumberReader, String line, Map properties) throws SUF2ParseException, IOException {
        super(lineNumberReader, line, properties);
    }

    public void parseProperties() throws SUF2ParseException {
        line.setShift(2);

        List<SUF2Coordinate> coordinates;
        if (properties.containsKey(COORDINATELIST)) {
            coordinates = (List<SUF2Coordinate>) properties.get(COORDINATELIST);
        } else {
            coordinates = new ArrayList();
        }


        SUF2Coordinate.Tag tag = null;


        while (line.getShift() < 52) {

            if (line.charAt(1) == 'I') {

                int tagNum = Integer.parseInt(line.part(2));
                switch (tagNum) {
                    case 1:
                        tag = SUF2Coordinate.Tag.I1;
                        break;
                    case 2:
                        tag = SUF2Coordinate.Tag.I2;
                        break;
                    case 4:
                        tag = SUF2Coordinate.Tag.I4;
                        setType(Type.ARC);
                        break;
                    default:
                        throw new SUF2ParseException(lineNumberReader, "Unknown Coordinate tag " + tagNum);
                }

            } else if (line.charAt(1) == 'X') {
                String x = line.part(2, 10);
                line.shift(10);

                if (line.charAt(1) == 'Y') {
                    String y = line.part(2, 10);
                    SUF2Coordinate coordinate = new SUF2Coordinate(
                            ((double) Integer.parseInt(x)) / 1000,
                            ((double) Integer.parseInt(y)) / 1000);

                    if (tag != null) {
                        coordinate.setTag(tag);
                    }

                    tag = null; // reset tag for next coordinate
                    coordinates.add(coordinate);
                }

            } else if (line.charAt(1) == 'Q') {
                String[] values_precisie = {"1 cm", "5 cm", "12 cm", "23 cm", "23 cm", "46 cm", "100 cm", "250 cm"};
                SUF2ValueFinder.addValue(line.part(4), Q_PRECISIEKLASSE, properties, values_precisie);

                String[] values_idealisatie = {"onbekend", "0 - 2 cm", "2 - 5 cm", "5 - 10 cm", "> 10 cm"};
                SUF2ValueFinder.addValue(line.part(7), Q_IDEALISATIEKLASSE, properties, values_idealisatie);

                properties.put(Q_BETROUWBAARHEID, line.part(10));
            }

            line.shift(10);
        }

        if (coordinates.size() != 0) {
            properties.put(COORDINATELIST, coordinates);
            if(getType() == Type.UNDEFINED){
                setType(Type.LINE);
            }
            hasGeometry = true;
        }
    }
}
