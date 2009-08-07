package nl.b3p.suf2;

/*
 * $Id: SUF2RecordParser.java 9066 2008-09-30 15:01:19Z Richard $
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.Iterator;

import nl.b3p.suf2.records.SUF2Record;
import nl.b3p.suf2.records.SUF2Record01;
import nl.b3p.suf2.records.SUF2Record02;
import nl.b3p.suf2.records.SUF2Record03;
import nl.b3p.suf2.records.SUF2Record04;
import nl.b3p.suf2.records.SUF2Record05;
import nl.b3p.suf2.records.SUF2Record06;
import nl.b3p.suf2.records.SUF2Record07;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Represents a single feature from a SDL file, which can be a point, polyline(s)
 * or polygon(s). A SDL feature has name, key and urlLink attributes.
 * 
 * On construction, this class will parse the next feature from a LineNumberReader
 * and create a JTS Geometry.
 * 
 * @author Gertjan Al, B3Partners
 */
public class SUF2RecordCollector implements Iterator<SUF2Record> {

    private static final Log log = LogFactory.getLog(SUF2RecordCollector.class);
    private SUF2Record record;
    private LineNumberReader lineNumberReader;
    private CountingInputStream cis;

    public SUF2RecordCollector(URL url) throws IOException, SUF2ParseException {
        this.cis = new CountingInputStream(url.openStream());
        this.lineNumberReader = new LineNumberReader(new InputStreamReader(cis));
    }

    public SUF2Record next() {
        return record;
    }

    public boolean hasNext() {
        try {
            record = SUF2RecordFactory.getNextRecord(lineNumberReader);

            if (record == null) {
                return false;
            }
        } catch (EOFException ex) {
            return false;
        } catch (Exception ex) {
            int z = 0; // TODO
        }
        return true;
    }

  

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void close() throws IOException {
        lineNumberReader.close();
    }

    public long getByteCount() {
        return cis.getByteCount();
    }

    public int getLineNumber(){
        return lineNumberReader.getLineNumber();
    }
}
