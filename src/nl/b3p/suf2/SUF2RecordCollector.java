package nl.b3p.suf2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.Iterator;

import nl.b3p.suf2.records.SUF2Record;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
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

    public int getLineNumber() {
        return lineNumberReader.getLineNumber();
    }
}
