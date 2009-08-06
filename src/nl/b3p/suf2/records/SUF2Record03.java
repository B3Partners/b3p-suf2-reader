/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.suf2.records;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import nl.b3p.suf2.SUF2ParseException;

/**
 *
 * @author Gertjan
 */
public class SUF2Record03 extends SUF2Record {

    public static final String GEMEENTECODE_LINKS = "gemeentecode links";
    public static final String SECTIELETTER_LINKS = "sectieletter links";
    public static final String PERCEELNUMMER_LINKS = "perceelnummer links";
    public static final String INDEXLETTER_PERCEELNUMMER = "indexletter van perceelnummer";
    public static final String INDEXNUMMER_PERCEELNUMMER = "indexnummer van perceelnummer";

    public SUF2Record03(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public Map getCurrentProperties() throws SUF2ParseException {
        Map properties = new HashMap();
        line.setShift(2);

        if (line.charAt(1) == 'M') {
            properties.put(LKI_CLASSIFICATIECODE, line.part(2, 4));

        } else if (line.charAt(1) == 'L') {
            properties.put(GEMEENTECODE_LINKS, line.part(2, 6));
            properties.put(SECTIELETTER_LINKS, line.part(7, 8));
            properties.put(INDEXLETTER_PERCEELNUMMER, line.part(10));

        } else if (line.charAt(1) == 'V') {
            properties.put(PERCEELNUMMER_LINKS, line.part(2, 6));
            properties.put(INDEXNUMMER_PERCEELNUMMER, line.part(7, 10));

        } else {
            throw new SUF2ParseException(lineNumberReader, "Unknown subrecord character in " + this.getClass().getSimpleName() + "; " + line.charAt(1) + " not supported");
        }

        return properties;
    }
}
