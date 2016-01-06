package sort;

import implementations.AdtContainerFactory;
import interfaces.AdtArray;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

    private final static int MAXRND = 2000;
    private final static int MINRND = 1;

    private final static String FILENAME = "zahlen.dat";

    private static AdtArray RNDNUMBERS = AdtContainerFactory.adtArray();

    /**
     * 
     * @param amount
     * @param allowduplicates
     * @param caseSwitcher 1 = unsorted, 2 = left-sorted, 3 = right-sorted
     */
    public static void sortnum(int amount, boolean allowduplicates, int caseSwitcher){
        switch(caseSwitcher){
            case 1:sortnum(amount, allowduplicates);break;
            case 2:sortnumLeft(amount, allowduplicates);break;
            case 3:sortnumRight(amount, allowduplicates);break;
            default:System.out.println("Please try again!");
        }
    }
    
    public static void sortnum(int amount, boolean allowduplicates) {
        clearRNDNUMBERS();
        generateRNDNUMBERS(amount, allowduplicates);
        writeToDatFile();
    }

    public static void sortnumLeft(int amount, boolean allowduplicates) {
        clearRNDNUMBERS();
        generateRNDNUMBERS(amount, allowduplicates);
        Sorter.quicksort(RNDNUMBERS, (start, end) -> (end / 2));
        writeToDatFile();
    }

    public static void sortnumRight(int amount, boolean allowduplicates) {
        clearRNDNUMBERS();
        generateRNDNUMBERS(amount, allowduplicates);
        Sorter.quicksort(RNDNUMBERS, (start, end) -> (end / 2));
        flipRNDNUMBERS();
        writeToDatFile();
    }

    public static AdtArray importNums(String filename) {
        String splitBy = " ";
        String characters;
        String[] splittedLine;
        AdtArray returnValue = AdtContainerFactory.adtArray();

        try (
                BufferedReader br = new BufferedReader(new FileReader(filename))) {

            while ((characters = br.readLine()) != null) {
                splittedLine = characters.split(splitBy);
                for (int i = 0; i < splittedLine.length; i++) {
                    returnValue.set(i, Integer.valueOf(splittedLine[i]));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Explosionen!");
        }
        return returnValue;
    }

    private static void generateRNDNUMBERS(int amount, boolean allowduplicates) {
        if (allowduplicates) {
            for (int i = 0; i < amount; i++) {
                RNDNUMBERS.set(i, ThreadLocalRandom.current().nextInt(MINRND, MAXRND + 1));
            }
        } else {

            ArrayList<Integer> templist = new ArrayList<>();
            int upperBound = MAXRND-MINRND > amount ? MAXRND : MINRND + amount;
            for (int i = MINRND; i < upperBound; i++) {
                templist.add(i);
            }
            Collections.shuffle(templist);
            for(int i = templist.size(); i > amount; i--){
                templist.remove(i-1);
            }
            for(int i = 0; i < templist.size(); i++){
                RNDNUMBERS.set(i, templist.get(i));
            }
        }

    }

    private static void writeToDatFile() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(FILENAME, false), "US-ASCII"))) {
            for (int i = 0; i <= RNDNUMBERS.length(); i++) {
                writer.write(RNDNUMBERS.get(i) + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearRNDNUMBERS() {
        RNDNUMBERS = AdtContainerFactory.adtArray();
    }

    private static void flipRNDNUMBERS() {
        AdtArray temp = AdtContainerFactory.adtArray();
        for (int i = 0; i < RNDNUMBERS.length(); i++) {
            temp.set(RNDNUMBERS.length() - i, RNDNUMBERS.get(i));
        }
        RNDNUMBERS = temp;
    }
}
