package nl.b3p.suf2;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2RecordLine {

    private String line;
    private int shift = 0;

    public SUF2RecordLine(String line) {
        this.line = line;
    }

    public String part(int start, int end) {
        return line.substring(start - 1 + shift, end + shift).trim();
    }

    public String part(int start) {
        return line.substring(start - 1 + shift, start + shift);
    }

    public boolean isMultiLine() {
        return line.endsWith("00");
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Character charAt(int pos) {
        return line.charAt(pos - 1 + shift);
    }

    public int length() {
        return line.length();
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public void shift(int shift) {
        this.shift += shift;
    }

    public void resetShift() {
        shift = 0;
    }

    @Override
    public String toString() {
        return line;
    }
}
