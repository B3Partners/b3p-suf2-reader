package nl.b3p.suf2.records;

import java.io.IOException;
import java.io.LineNumberReader;
import nl.b3p.suf2.SUF2Map;
import nl.b3p.suf2.SUF2ParseException;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2Record06 extends SUF2Record {

    public static final String VELDLENGTE = "tekst veldlengte";
    public static final String TEKST = "tekst";
    //Perceelnummer
    public static final String GEMEENTECODE = "gemeentecode";
    public static final String SECTIE = "sectie";
    public static final String PERCEELNUMMER = "perceelnummer";
    public static final String INDEXLETTER = "indexletter";
    public static final String INDEXNUMMER = "indexnummer";

    public SUF2Record06(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public SUF2Record06(LineNumberReader lineNumberReader, String line, SUF2Map properties) throws SUF2ParseException, IOException {
        super(lineNumberReader, line, properties);
    }

    public void parseProperties() throws SUF2ParseException {
        if (line.charAt(6) == 'T') {
            if (!properties.containsKey(LKI_CLASSIFICATIECODE)) {
                throw new SUF2ParseException(lineNumberReader, "LKI classificatiecode niet gevonden voor dit object");
            }

            if (line.part(19, 23).equals("G0000")) {
                properties.put(GEMEENTECODE, line.part(7, 11));
                properties.put(SECTIE, line.part(12, 13));
                properties.put(PERCEELNUMMER, line.part(14, 18));
                properties.put(INDEXLETTER, line.part(19));
                properties.put(INDEXNUMMER, line.part(20, 23));

                setType(Type.PERCEEL);

            } else if (line.part(19, 23).equals("D0000")) {
                //TODO
                properties.put(GEMEENTECODE, line.part(7, 11));
                properties.put(SECTIE, line.part(12, 13));
                properties.put(PERCEELNUMMER, line.part(14, 18));
                properties.put(INDEXLETTER, line.part(19));
                properties.put(INDEXNUMMER, line.part(20, 23));

                setType(Type.PERCEEL);
            } else {
                if (properties.get(SUF2Record05.TEKST_OF_SYMBOOL).equals("1")) {
                    properties.put(TEKST, line.part(7, 46));
                    properties.put(VELDLENGTE, line.part(4, 5));
                    setType(Type.TEXT);

                } else {
                    // non-text TODO
                    setType(Type.SYMBOL);
                }
            }
            hasGeometry = true;

        } else {
            throw new SUF2ParseException(lineNumberReader, "Line is a record06, but character 6 is not a 'T'");
        }
    }
}
