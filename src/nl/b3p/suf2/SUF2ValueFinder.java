package nl.b3p.suf2;

import java.util.Map;

/**
 *
 * @author Gertjan Al, B3Partners
 */
public class SUF2ValueFinder {

    public static void addValue(String number, String property, Map properties, String... values) {
        String value = "";
        try {
            int integer = Integer.parseInt(number);
            value = values[integer];

        } catch (NumberFormatException ex) {
        } catch (ArrayIndexOutOfBoundsException ex) {
        } finally {
            properties.put(property, number + "|" + (value == null ? "" : value));
        }
    }

    public static void addValue(String number, String property, Map properties, Map<Integer, String> values) {
        String value = "";
        try {
            int integer = Integer.parseInt(number);
            value = values.get(integer);

        } catch (NumberFormatException ex) {
        } catch (ArrayIndexOutOfBoundsException ex) {
        } finally {
            properties.put(property, number + "|" + (value == null ? "" : value));
        }
    }
}
