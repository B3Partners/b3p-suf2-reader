package nl.b3p.suf2.records;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import nl.b3p.suf2.SUF2Map;
import nl.b3p.suf2.SUF2ParseException;
import nl.b3p.suf2.SUF2ValueFinder;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2Record03 extends SUF2Record {

   /* public static final String ORIENTATIEGEMEENTECODE = "orientatie gemeentecode";
    public static final String ORIENTATIESECTIELETTER = "orientatie sectieletter";
    public static final String ORIENTATIEPERCEELNUMMER = "orientatie perceelnummer";*/
    public static final String ORIENTATIETOVPERCEEL = "orientatie tov perceel";
    public static final String INDEXLETTER_PERCEELNUMMER = "indexletter van perceelnummer";
    public static final String INDEXNUMMER_PERCEELNUMMER = "indexnummer van perceelnummer";
    // G
    public static final String G_STRINGSOORT = "stringsoort van het grafisch element";
    public static final String G_ZICHTBAARHEID = "zichtbaarheid van object ivm tekeninstructies";
    public static final String G_INWINNING = "wijze van inwinning";
    public static final String G_STATUS_VAN_OBJECT = "status van het object";
    // D
    public static final String D_OPNAMEDATUM = "opnamedatum van het grafisch element";
    // B
    public static final String B_BRONVERMELDING = "bronvermelding bij het object";
    public static final String B_WIJZE_VERZEKERING = "wijze van verzekering bij grondslag";

    public SUF2Record03(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        super(lineNumberReader, line);
    }

    public SUF2Record03(LineNumberReader lineNumberReader, String line, SUF2Map properties) throws SUF2ParseException, IOException {
        super(lineNumberReader, line, properties);
    }

    public void parseProperties() throws SUF2ParseException {
        line.setShift(2);
        while (line.getShift() < 62) {
            if (line.charAt(1) == 'M') {
                properties.put(LKI_CLASSIFICATIECODE, line.part(2, 4));
            }else if (line.charAt(1) == 'L' || line.charAt(1) == 'R') {
                properties.put(ORIENTATIETOVPERCEEL, line.part(1));
                properties.put(SUF2Record06.GEMEENTECODE, line.part(2, 6));
                properties.put(SUF2Record06.SECTIE, line.part(7, 8));
                properties.put(INDEXLETTER_PERCEELNUMMER, line.part(10));

            } else if (line.charAt(1) == 'V') {
                properties.put(SUF2Record06.PERCEELNUMMER, line.part(2, 6));
                properties.put(INDEXNUMMER_PERCEELNUMMER, line.part(7, 10));

            }else if(line.charAt(1) == 'G') {
                Map<Integer, String> values_stringsoort = new HashMap();
                values_stringsoort.put(1, "(knik)punt");
                values_stringsoort.put(12, "string (2 punten of meer)");
                values_stringsoort.put(13, "cirkelboog voor drie punten");
                SUF2ValueFinder.addValue(line.part(2, 3), G_STRINGSOORT, properties, values_stringsoort);

                String[] values_zichtbaarheid = {"normaal / niet bekend", "boven en onder maaiveld (Z-niveau)", "onzichtbaar vanuit de lucht", "vaag of slecht interpreteerbaar"};
                SUF2ValueFinder.addValue(line.part(4), G_ZICHTBAARHEID, properties, values_zichtbaarheid);

                String[] values_inwinning = {"niet bekend", "terrestrische meting (T)", "fotogrammetrische meting (F)", "digistalisering kaart (D)", "scanning kaart (S)", "kaartverbetering (K)"};
                SUF2ValueFinder.addValue(line.part(6), G_INWINNING, properties, values_inwinning);

                String[] values_status = {null, "nieuw object", null, null, "te verwijderen object"};
                SUF2ValueFinder.addValue(line.part(7), G_STATUS_VAN_OBJECT, properties, values_status);


            } else if (line.charAt(1) == 'D') {
                properties.put(D_OPNAMEDATUM, line.part(2, 10));

            } else if (line.charAt(1) == 'B') {
                properties.put(B_BRONVERMELDING, line.part(2, 6));

                String[] values = {"niet bekend", "kad. steen", "ijzeren buis", "draineerbuis", "bout", "spijker", "piket"};
                SUF2ValueFinder.addValue(line.part(8), B_WIJZE_VERZEKERING, properties, values);
            }
            line.shift(10);
        }
    }

    private void parseSub() {
        
    }
}

