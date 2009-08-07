/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.suf2.records;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nl.b3p.suf2.SUF2Coordinate;
import nl.b3p.suf2.SUF2ParseException;
import nl.b3p.suf2.SUF2RecordFactory;
import nl.b3p.suf2.SUF2RecordLine;

/**
 *
 * @author Gertjan
 */
public abstract class SUF2Record {

    protected SUF2RecordLine line;
    protected boolean done;
    protected final int lineNumber;
    protected LineNumberReader lineNumberReader;
    protected Map properties;
    protected boolean hasGeometry = false;
    // finals
    public static final String RECORDTYPE = "recordtype";
    public static final String COORDINATELIST = "coordinates";
    public static final String LKI_CLASSIFICATIECODE = "LKI classificatiecode";

    public SUF2Record(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        this(lineNumberReader, line, new HashMap());
    }

    public SUF2Record(LineNumberReader lineNumberReader, String line, Map properties) throws SUF2ParseException, IOException {
        if (line.length() != 64) {
            throw new SUF2ParseException(lineNumberReader, "Line length incorrect (!= 64); length: " + line.length());
        }

        this.line = new SUF2RecordLine(line);
        this.lineNumberReader = lineNumberReader;
        this.lineNumber = lineNumberReader.getLineNumber();
        this.properties = properties;
        
        String recordType = getRecordType();
        if (!line.substring(0, 2).equals(recordType)) {
            throw new SUF2ParseException(lineNumberReader, "Incorrect Record class selected for line; Recordclass=" + recordType + " linetype=" + line.substring(0, 2));
        }

        parseRecord();
    }

    public Map getProperties() throws SUF2ParseException, IOException {
        return properties;
    }

    private void parseRecord() throws SUF2ParseException, IOException {
        if (properties.containsKey(RECORDTYPE)) {
            properties.put(RECORDTYPE, properties.get(RECORDTYPE).toString() + "|" + line.part(1, 2));
        } else {
            properties.put(RECORDTYPE, line.part(1, 2));
        }

        parseProperties();

        if (line.isMultiLine()) {
            // Multi-line key
            SUF2Record record = SUF2RecordFactory.getNextRecord(lineNumberReader, properties);

            // Clone the hasGeometry
            hasGeometry = hasGeometry || record.hasGeometry();
        }
    }

    protected abstract void parseProperties() throws SUF2ParseException;

    public boolean hasGeometry() {
        return hasGeometry;
    }

    public List<SUF2Coordinate> getCoordinates() throws Exception {
        return (List) properties.get(COORDINATELIST);
    }

    public String getRecordType() {
        String name = this.getClass().getSimpleName();
        return name.substring(name.length() - 2);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public SUF2RecordLine getLine() {
        return line;
    }
}
