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
public class SUF2Record06 extends SUF2Record {

    public static final String VELDLENGTE = "tekst veldlengte";
    public static final String TEKST = "tekst";

    public SUF2Record06(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public Map getCurrentProperties() throws SUF2ParseException {
        Map properties = new HashMap();

        if (line.charAt(6) == 'T') {
            properties.put(TEKST, line.part(7, 46));
            properties.put(VELDLENGTE, line.part(4,5));

            System.out.println("hier");

        } else {
            throw new SUF2ParseException(lineNumberReader, "Line is a record06, but character 6 is not a 'T'");
        }

        return properties;
    }
}
