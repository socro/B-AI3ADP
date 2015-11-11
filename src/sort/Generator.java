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
import java.util.concurrent.ThreadLocalRandom;

public class Generator {

    private final static int MAXRND = 200;
    private final static int MINRND = 1;

    private final static String FILENAME = "zahlen.dat";

    private static AdtArray RNDNUMBERS = AdtContainerFactory.adtArray();

    public static void sortnum(int amount) {
        clearRNDNUMBERS();
        generateRNDNUMBERS(amount);
        writeToDatFile();
    }

    public static void sortnumLeft(int amount) {
        clearRNDNUMBERS();
        generateRNDNUMBERS(amount);
        Sorter.insertionsort(RNDNUMBERS, 0, RNDNUMBERS.length());
        writeToDatFile();
    }

    public static void sortnumRight(int amount) {
        clearRNDNUMBERS();
        generateRNDNUMBERS(amount);
        Sorter.insertionsort(RNDNUMBERS, 0, RNDNUMBERS.length());
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

    private static void generateRNDNUMBERS(int amount) {
        for (int i = 0; i < amount; i++) {
            RNDNUMBERS.set(i, ThreadLocalRandom.current().nextInt(MINRND, MAXRND + 1));
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
    }
}
