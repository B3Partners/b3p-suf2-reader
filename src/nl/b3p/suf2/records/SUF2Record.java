/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.suf2.records;

import java.awt.Point;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nl.b3p.suf2.SUF2ParseException;
import nl.b3p.suf2.SUF2RecordCollector;
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
    private Map properties = new HashMap();
    protected boolean hasGeometry = false;
    // finals
    public static final String RECORDTYPE = "recordtype";
    public static final String COORDINATELIST = "coordinates";
    public static final String LKI_CLASSIFICATIECODE = "LKI classificatiecode";

    public SUF2Record(LineNumberReader lineNumberReader, String line) throws SUF2ParseException, IOException {
        if (line.length() != 64) {
            throw new SUF2ParseException(lineNumberReader, "Line length incorrect (!= 64); length: " + line.length());
        }

        this.line = new SUF2RecordLine(line);
        this.lineNumberReader = lineNumberReader;
        this.lineNumber = lineNumberReader.getLineNumber();

        String recordType = getRecordType();
        if (!line.substring(0, 2).equals(recordType)) {
            throw new SUF2ParseException(lineNumberReader, "Incorrect Record class selected for line; Recordclass=" + recordType + " linetype=" + line.substring(0, 2));
        }

        parseProperties();
    }

    public Map getProperties() throws SUF2ParseException, IOException {
        return properties;
    }

    private void parseProperties() throws SUF2ParseException, IOException {
       try{
        properties.put(RECORDTYPE, Integer.parseInt(line.part(1, 2)));
        properties.putAll(getCurrentProperties());

        if (line.isMultiLine()) {
            // Multi-line key
            SUF2Record record = SUF2RecordCollector.getNextRecord(lineNumberReader);

            // Clone the hasGeometry
            hasGeometry = hasGeometry || record.hasGeometry();

            Iterator it = record.getProperties().keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                Object value = record.getProperties().get(key);
                if (properties.containsKey(key)) {
                    // Property already exists
                    if (properties.get(key) instanceof List) {
                        // Add new properties to current key/value<List>
                        List list = (List) properties.get(key);
                        if (value instanceof List) {
                            // current List + new List
                            list.addAll((List)value);
                        } else {
                            // current List + new Object
                            list.add(value);
                        }
                    }
                }
            }
            properties.putAll(record.getProperties());
        }
       }catch(Exception ex){
           int z=0;
       }
    }

    protected abstract Map getCurrentProperties() throws SUF2ParseException;

    public boolean hasGeometry() {
        return hasGeometry;
    }

    public List<Point> getCoordinates() throws Exception {
        return (List)properties.get(COORDINATELIST);
    }

    public String getRecordType() {
        String name = this.getClass().getSimpleName();
        return name.substring(name.length() - 2);
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
