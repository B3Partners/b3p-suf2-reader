package nl.b3p.suf2;

import java.io.EOFException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import nl.b3p.suf2.records.SUF2Record;
import nl.b3p.suf2.records.SUF2Record01;
import nl.b3p.suf2.records.SUF2Record02;
import nl.b3p.suf2.records.SUF2Record03;
import nl.b3p.suf2.records.SUF2Record04;
import nl.b3p.suf2.records.SUF2Record05;
import nl.b3p.suf2.records.SUF2Record06;
import nl.b3p.suf2.records.SUF2Record07;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2RecordFactory {

    public static SUF2Record getNextRecord(LineNumberReader lineNumberReader, Map properties) throws SUF2ParseException, IOException {
        String line = lineNumberReader.readLine();
        int recordType = Integer.parseInt(line.substring(0, 2));

        switch (recordType) {
            case 1:
                return new SUF2Record01(lineNumberReader, line, properties);
            case 2:
                return new SUF2Record02(lineNumberReader, line, properties);
            case 3:
                return new SUF2Record03(lineNumberReader, line, properties);
            case 4:
                return new SUF2Record04(lineNumberReader, line, properties);
            case 5:
                return new SUF2Record05(lineNumberReader, line, properties);
            case 6:
                return new SUF2Record06(lineNumberReader, line, properties);
            case 7:
                return new SUF2Record07(lineNumberReader, line, properties);
            case 99:
                throw new EOFException();
            default:
                throw new IOException("Unknown recordnumber " + recordType);
        }
    }

    public static SUF2Record getNextRecord(LineNumberReader lineNumberReader) throws SUF2ParseException, IOException {
        return getNextRecord(lineNumberReader, new HashMap());
    }
}
