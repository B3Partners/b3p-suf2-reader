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
public class SUF2Record01 extends SUF2Record {

    public static final String BESTANDSIDENTIFICATIE = "bestandsidentificatie";
    public static final String VOLLEDIG_OF_MUTATIE = "volledig/mutatie";
    public static final String DATUM_HERZIENING = "datum laatste herziening";
    public static final String DATUM_ACTUALITEIT = "actualiteitsdatum";
    public static final String UITWISSELING_DEELBESTANDEN = "aantal deelbestanden uitwisseling";
    public static final String UITWISSELING_DEELBESTANDEN_HUIDIG = "huidig deelbestandsnummer";
    public static final String BESTANDSNAAM = "bestandsnaam";

    public SUF2Record01(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public Map getCurrentProperties() {
        Map properties = new HashMap();

        properties.put(BESTANDSIDENTIFICATIE, line.part(3, 14));
        properties.put(VOLLEDIG_OF_MUTATIE, line.part(15));
        properties.put(DATUM_HERZIENING, line.part(16, 21));
        properties.put(DATUM_ACTUALITEIT, line.part(22, 27));

        return properties;
    }
}
