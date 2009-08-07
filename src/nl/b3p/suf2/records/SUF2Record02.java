package nl.b3p.suf2.records;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Map;
import nl.b3p.suf2.SUF2ParseException;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2Record02 extends SUF2Record {

    public static final String RD = "R.D.-coordinatenstelsel";
    public static final String LKI = "aanduiding keuze voor LKI-classificatie";
    public static final String COORD_MILLIMETERS = "registratie coordinaten in millimeters";
    public static final String RICHTINGEN_MICROGON = "registratie richtingen in microgen";
    public static final String LKI_SYMBOOL = "LKI-symbool-codes";
    public static final String NAP = "hoogte in NAP";
    public static final String HEEFT_OPTEL_X = "geen optelconstante in X-coordinaat";
    public static final String HEEFT_OPTEL_Y = "geen optelconstante in Y-coordinaat";
    public static final String HEEFT_OPTEL_Z = "geen optelconstante in Z-coordinaat";
    public static final String VERMENIGVULDIGINGSCONSTANTE_XY = "vermeningsvuldigingsconstante x, y";
    public static final String VERMENIGVULDIGINGSCONSTANTE_Z = "vermeningsvuldigingsconstante z";

    public SUF2Record02(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public SUF2Record02(LineNumberReader lineNumberReader, String line, Map properties) throws SUF2ParseException, IOException {
        super(lineNumberReader, line, properties);
    }

    public void parseProperties() {
        properties.put(RD, line.part(3, 4));
        properties.put(LKI, line.part(5, 6));
        properties.put(COORD_MILLIMETERS, line.part(7));
        properties.put(RICHTINGEN_MICROGON, line.part(8));
        properties.put(LKI_SYMBOOL, line.part(9, 10));
        properties.put(NAP, line.part(11, 12));
        properties.put(HEEFT_OPTEL_X, line.part(22));
        properties.put(HEEFT_OPTEL_Y, line.part(32));
        properties.put(VERMENIGVULDIGINGSCONSTANTE_XY, line.part(42));
        properties.put(HEEFT_OPTEL_Z, line.part(52));
        properties.put(VERMENIGVULDIGINGSCONSTANTE_Z, line.part(62));
    }
}
