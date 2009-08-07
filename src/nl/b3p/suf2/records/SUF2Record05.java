package nl.b3p.suf2.records;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.b3p.suf2.SUF2Coordinate;
import nl.b3p.suf2.SUF2ParseException;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2Record05 extends SUF2Record {

    public static final String TEXT_ALIGN = "text align";
    public static final String STATUS_PERCEEL = "status perceel";
    public static final String TEKST_OF_SYMBOOL = "is tekst";
    public static final String SYMBOOLTYPE = "symbooltype";

    public SUF2Record05(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public SUF2Record05(LineNumberReader lineNumberReader, String line, Map properties) throws SUF2ParseException, IOException {
        super(lineNumberReader, line, properties);
    }

    public void parseProperties() throws SUF2ParseException {
        double x, y;
        line.setShift(2);

        properties.put(TEXT_ALIGN, line.part(2));
        properties.put(STATUS_PERCEEL, line.part(3));
        properties.put(TEKST_OF_SYMBOOL, line.part(4));
        properties.put(SYMBOOLTYPE, line.part(5, 10));

        line.setShift(12);
        List<SUF2Coordinate> coordinates = new ArrayList();

        x = Integer.parseInt(line.part(2, 10));

        line.setShift(22);
        y = Integer.parseInt(line.part(2, 10));

        coordinates.add(new SUF2Coordinate(x / 1000, y / 1000));

        line.setShift(32);
        x = Integer.parseInt(line.part(2, 10));

        line.setShift(42);
        y = Integer.parseInt(line.part(2, 10));

        coordinates.add(new SUF2Coordinate(x / 1000, y / 1000));
        properties.put(COORDINATELIST, coordinates);
        
        hasGeometry = true;
        
        setType(Type.SYMBOL);

        line.setShift(52);
        properties.put(LKI_CLASSIFICATIECODE, line.part(2, 4));
    }
}
