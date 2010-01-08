/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.suf2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nl.b3p.suf2.records.SUF2Record;
import nl.b3p.suf2.records.SUF2Record.Type;

/**
 *
 * @author Gertjan
 */
public class SUF2Map extends HashMap {

    String[] ALLOWED_OVERWRITE = {SUF2Record.RECORDTYPE}; //, SUF2Record.COORDINATELIST

    public Map getProperties() {
        return (Map) this;
    }

    @Override
    public Object put(Object key, Object value) {
        if (key.equals(SUF2Record.COORDINATELIST)) {
            List<SUF2Coordinate> coordinates = (List) value;

            if (!containsKey(SUF2Record.COORDINATELIST)) {
                List<SUF2Coordinate> coordinateList = new ArrayList<SUF2Coordinate>();

                for (int i = 0; i < coordinates.size(); i++) {
                    if (coordinateList.size() == 0) {
                        coordinateList.add(coordinates.get(i));
                    } else if (!coordinates.get(i).equals(coordinateList.get(coordinateList.size() - 1))) {
                        coordinateList.add(coordinates.get(i));
                    }else{
                        int z=0;
                    }
                }

                return super.put(SUF2Record.COORDINATELIST, coordinateList);

            } else {
                // Add coordinates to current list
                List<SUF2Coordinate> coordinateList = (List) get(SUF2Record.COORDINATELIST);

                // If coordinatelist contains two equal coordinates, it's a polygon
                // Note that this will only be set to polygon if GeomType ranking allows this
                /*for (int i = 0; i < coordinates.size(); i++) {
                if (coordinateList.contains(coordinates.get(i))) {
                put(SUF2Record.GEOM_TYPE, Type.POLYGON);
                }
                }
                 */
                if (coordinateList.size() > 2) {
                    if (coordinateList.get(0).equals(coordinateList.get(coordinateList.size() - 1))) {
                        put(SUF2Record.GEOM_TYPE, Type.POLYGON);
                    }
                }
/*
                for (int i = 0; i < coordinates.size(); i++) {
                    if (coordinateList.size() == 0) {
                        coordinateList.add(coordinates.get(i));
                    } else if (!coordinates.get(i).equals(coordinateList.get(coordinateList.size() - 1))) {
                        coordinateList.add(coordinates.get(i));
                    }else{
                        int cry = 0;
                    }
                }*/
                coordinateList.addAll(coordinates);
                return super.put(SUF2Record.COORDINATELIST, coordinateList);
            }

        } else if (key.equals(SUF2Record.RECORDTYPE)) {
            // Make a recordType string, so no recordnumbers will be lost
            // Example: "01|04|04"
            if (containsKey(SUF2Record.RECORDTYPE)) {
                return super.put(SUF2Record.RECORDTYPE, get(SUF2Record.RECORDTYPE).toString() + "|" + value);
            } else {
                return super.put(SUF2Record.RECORDTYPE, value);
            }

        } else if (doWrite(key, value)) {
            return super.put(key, value);

        } else {
            return key;
        }
    }

    /**
     * Check if (over)writing this K/V is allowed
     * Current value will be checked and decided if any existing value may be overwritten
     * @param keyObject Key to check for this map
     * @param value Value to be assigned
     * @return (Over)write of this K/V allowed
     */
    public boolean doWrite(Object keyObject, Object value) {
        if (keyObject instanceof String) {
            String key = (String) keyObject;

            if (!containsKey(key)) {
                return true;
            } else if (key.equals(SUF2Record.GEOM_TYPE)) {
                Type currentType = (Type) get(key);
                Type newType = (Type) value;

                return newType.getRank() > currentType.getRank();
            } else {
                for (int i = 0; i < ALLOWED_OVERWRITE.length; i++) {
                    if (ALLOWED_OVERWRITE[i].toLowerCase().equals(key.toLowerCase())) {
                        return true;
                    }
                }
                return false;
            }
        } else {
            return true;
        }
    }
}
