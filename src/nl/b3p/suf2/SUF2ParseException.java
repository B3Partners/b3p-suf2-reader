/*
 * $Id: SUF2ParseException.java 8672 2008-07-17 16:37:57Z Matthijs $
 */

package nl.b3p.suf2;

import java.io.LineNumberReader;

/**
 * Exception thrown while parsing a SUF2 file, adds line number in front of
 * specified message.
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2ParseException extends Exception {

    private String message;

    public SUF2ParseException(LineNumberReader reader, String message) {
        super();
        this.message = "line " + reader.getLineNumber() + ": " + message;
    }
/*
    SUF2ParseException(SUF2Record record, String message) {
        super();
        this.message = "entry starting at line " + record.getStartingLineNumber() + ": " + message;
    }

    SUF2ParseException(SUF2Record record, String message, Exception cause) {
        super(cause);
        this.message = "entry starting at line " + record.getStartingLineNumber() + ": " + message;
    }
*/
    @Override
    public String getMessage() {
        return message;
    }
}
