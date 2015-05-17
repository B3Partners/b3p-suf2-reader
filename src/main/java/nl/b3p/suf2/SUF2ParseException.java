package nl.b3p.suf2;

import java.io.LineNumberReader;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2ParseException extends Exception {

    private String message;

    public SUF2ParseException(LineNumberReader reader, String message) {
        super();
        this.message = "line " + reader.getLineNumber() + ": " + message;
    }

    public SUF2ParseException(LineNumberReader reader, String message, Exception cause) {
        super(cause);
        this.message = "entry starting at line " + reader.getLineNumber() + ": " + message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
