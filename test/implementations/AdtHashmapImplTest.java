/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementations;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static implementations.AdtHashmapImpl.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *
 * @author Rene
 */
public class AdtHashmapImplTest {
    
    public AdtHashmapImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void magnificentAllInOneTestMethod() throws Exception {
        String[] texts = new String[2];
        texts[0] = "src/io/texta.txt";
        texts[1] = "src/io/textb.txt";
        
        int uniqueWordsInTextA = AdtHashmapImpl.getNumberOfUniqueWords(texts[0]);
        int uniqueWordsInTextB = AdtHashmapImpl.getNumberOfUniqueWords(texts[1]);
        
        String[] probingMethods = new String[3];
        probingMethods[0] = AdtHashmapImpl.LINEAR;
        probingMethods[1] = AdtHashmapImpl.QUADRATIC;
        probingMethods[2] = AdtHashmapImpl.DOUBLEHASH;
        
        ArrayList<String> headerCSV = new ArrayList<>();
        headerCSV.add("Textfile");
        headerCSV.add("Probing Method");
        headerCSV.add("Runtime in ms");        
        headerCSV.add("Hashmap Size");        
        headerCSV.add("Collisions");
        
//      outputToCSV("src/io/header.csv", headerCSV);
        
        for (String text : texts) {
            for (String probingMethod : probingMethods) {
                ArrayList<String> resultToExport = new ArrayList<>();
                
                resultToExport.add(text);
                resultToExport.add(probingMethod);
                
                long hashRT;
                
                if(text.equals(texts[0])){
                    // Text A
                    hashRT = AdtHashmapImpl.hashRT(probingMethod, text);
                } else {
                    // Text B
                    hashRT = AdtHashmapImpl.hashRT(probingMethod, text);                    
                }
                
                resultToExport.add(hashRT + "");
                resultToExport.add(AdtHashmapImpl.getHashSize() + "");
                resultToExport.add(AdtHashmapImpl.getCollisions() + "");
                
                outputToCSV(text + probingMethod, resultToExport);
            }
        }
    }
    
    public static void outputToCSV(String filename, ArrayList<String> args) {

        String nl = System.lineSeparator();        
        File f = new File(filename);
        
        

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename + ".csv", true), "utf-8"))) {

            // print line
            for (int i = 0; i < args.size(); i++) {
                if (i != args.size() - 1) {
                    writer.write(args.get(i) + ",");
                } else {
                    writer.write(args.get(i));
                }

            }
            writer.write(nl);

        } catch (IOException e) {
        }
    }
}