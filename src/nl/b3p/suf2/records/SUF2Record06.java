package nl.b3p.suf2.records;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Map;
import nl.b3p.suf2.SUF2ParseException;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2Record06 extends SUF2Record {

    public static final String VELDLENGTE = "tekst veldlengte";
    public static final String TEKST = "tekst";
    

    public SUF2Record06(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public SUF2Record06(LineNumberReader lineNumberReader, String line, Map properties) throws SUF2ParseException, IOException {
        super(lineNumberReader, line, properties);
    }

    public void parseProperties() throws SUF2ParseException {
        if (line.charAt(6) == 'T') {
            if (!properties.containsKey(LKI_CLASSIFICATIECODE)) {
                throw new SUF2ParseException(lineNumberReader, "LKI classificatiecode niet gevonden voor dit object");
            }

            if (properties.get(SUF2Record05.TEKST_OF_SYMBOOL).equals("1")) {
                properties.put(TEKST, line.part(7, 46));
                properties.put(VELDLENGTE, line.part(4, 5));

                setType(Type.TEXT);

            } else {
                // non-text TODO
                setType(Type.SYMBOL);
            }

            hasGeometry = true;

           

        } else {
            throw new SUF2ParseException(lineNumberReader, "Line is a record06, but character 6 is not a 'T'");
        }
    }
}
