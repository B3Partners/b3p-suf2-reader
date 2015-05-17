/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.suf2;

import java.io.File;
import java.net.URL;
import nl.b3p.suf2.records.SUF2Record;

/**
 *
 * @author Gertjan
 */
public class Client {

    public static void main(String[] args) throws Exception {
        URL url = new File("C:/Documents and Settings/Gertjan/Mijn documenten/NetBeansProjects/ETL/WEESP_N__7001.NEN").toURL();

        SUF2RecordCollector iter = new SUF2RecordCollector(url);
        int count = 0;
        while (iter.hasNext()) {
            SUF2Record record = iter.next();
            System.out.println(record.getProperties().toString());
            count++;
        }
        System.out.println(count + " complete records, in " + iter.getLineNumber() + " lines");
    }
}
