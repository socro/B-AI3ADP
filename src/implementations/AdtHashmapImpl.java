package implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AdtHashmapImpl {

    public static final String LINEAR = "L";
    public static final String QUADRATIC = "Q";
    public static final String DOUBLEHASH = "B";

    private static String strategy;
    private static String[] words;
    private static int[] count;
    private static int[] brentted;
    
    private static int collisions = 0;

    private static int wiselyChoosenPrime = 0;

    private static ArrayList<String> importedWords;

    public static void hash(String strategy, String filename, int hashmapSize) throws Exception {
        importedWords = importFile(filename);

        create(hashmapSize, strategy);

        for (String stringToStore : importedWords) {
            int calculatedIndex = divisionRemainderMethod(stringToStore);
            if (words[calculatedIndex] == null) {
                words[calculatedIndex] = stringToStore;
                count[calculatedIndex]++;
            } else if (words[calculatedIndex].equals(stringToStore)) {
                count[calculatedIndex]++;
            } else {
                pleaseHashAgain(stringToStore, calculatedIndex);
            }
        }
    }
    
    public static void hash(String strategy, String filename) throws Exception {
        importedWords = importFile(filename);
        hash(strategy, filename, calculateHashmapSize(importedWords));
    }
    
    public static long hashRT(String strategy, String filename, int size) throws Exception {
        if(importedWords == null){
            importedWords = importFile(filename);
        }
        long runtime = System.currentTimeMillis();
        hash(strategy, filename, size);
        return System.currentTimeMillis()-runtime;
    }
    
    public static long hashRT(String strategy, String filename) throws Exception {
        importedWords = importFile(filename);
        return hashRT(strategy, filename, calculateHashmapSize(importedWords));
    }

    public static void create(int size, String strategy) {
        collisions = 0;
        AdtHashmapImpl.strategy = strategy;
        words = new String[size];
        count = new int[size];
        brentted = new int[size];
        wiselyChoosenPrime = size;
        
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }
        for (int i = 0; i < brentted.length; i++) {
            brentted[i] = 0;
        }
        for (int i = 0; i < words.length; i++) {
            words[i] = null;
        }
    }

    public static void insert(String stringToStore) throws Exception {
        int calculatedIndex = divisionRemainderMethod(stringToStore);
        if (words[calculatedIndex] == null) {
            words[calculatedIndex] = stringToStore;
            count[calculatedIndex]++;
        } else if (words[calculatedIndex].equals(stringToStore)) {
            count[calculatedIndex]++;
            
        } else {
            pleaseHashAgain(stringToStore, calculatedIndex);
        }
    }

    public static int find(String stringToFind) throws Exception {
        int returnCount = -1;

        int calculatedIndex = divisionRemainderMethod(stringToFind);
        if (words[calculatedIndex] == null) {
        } else if (words[calculatedIndex].equals(stringToFind)) {
            return count[calculatedIndex];
        } else {
            return count[pleaseSearchAgain(stringToFind, calculatedIndex)];
        }

        return returnCount;
    }

    private static int divisionRemainderMethod(String word) {
        int index = 0;

        for (int i = 0; i < word.length(); i++) {
            index = (index * 128 + (int) word.charAt(i)) % wiselyChoosenPrime;
        }
        return index;
    }

    private static int doDoubleHashProbing(String word) {
        int index = 0;
        
        for (int i = 0; i < word.length(); i++) {
            index = (index * 128 + (int) word.charAt(i)) % (wiselyChoosenPrime - 2);
        }
        
        index++;
        return index;
    }

    private static int doLinearProbing(String stringToStore, int previouslyCalculatedIndex) {
        boolean weNeedAPlaceForThisString = true;

        int thisIsThePlace = (previouslyCalculatedIndex + 1) % words.length;
        while (weNeedAPlaceForThisString) {
            collisions++;
            if (words[thisIsThePlace] == null || words[thisIsThePlace].equals(stringToStore)) {
                return thisIsThePlace;
            }
            thisIsThePlace = (thisIsThePlace + 1) % words.length;
        }
        return -1;
    }

    private static int doQuadraticProbing(String stringToStore, int previouslyCalculatedIndex) {
        boolean weNeedAPlaceForThisString = true;
        int howManyTimesFailed = 0;

        while (weNeedAPlaceForThisString) {
            collisions++;
            if (words[previouslyCalculatedIndex] == null || words[previouslyCalculatedIndex].equals(stringToStore)) {
                return previouslyCalculatedIndex;
            }
            previouslyCalculatedIndex = Math.abs((previouslyCalculatedIndex - howManyTimesFailed * (previouslyCalculatedIndex / 2) ^ 2) * (-1) ^ previouslyCalculatedIndex) % wiselyChoosenPrime;
            howManyTimesFailed++;
        }
        return -1;
    }

    // TODO Double Hashing nach Brent
    private static void doBrentWithDoubleHash(String newString, int previouslyCalculatedIndex) {
        int indexNewString = previouslyCalculatedIndex;
        int indexNewOldString = 0;
        
        boolean weNeedAPlaceForThisString = true;

        int timesMovedNewString = 0;

        boolean thisRuns = true;
        
        while (weNeedAPlaceForThisString) {
            collisions++;
            int indexOldString = indexNewString;
            indexNewString = Math.abs(indexNewString - timesMovedNewString * doDoubleHashProbing(newString)) % wiselyChoosenPrime;
            indexNewOldString = Math.abs((indexOldString - brentted[indexNewOldString] * doDoubleHashProbing(words[indexOldString]))) % wiselyChoosenPrime;
            
            if(thisRuns){
                thisRuns = false;
            }
            if((words[indexNewOldString] == null)&& 
                brentted[indexOldString] <= timesMovedNewString){
                
                words[indexNewOldString] = words[indexOldString];
                count[indexNewOldString] = count[indexOldString];
                brentted[indexNewOldString] = brentted[indexOldString];                
                
                words[indexOldString] = newString;
                count[indexOldString] = 1;
                brentted[indexOldString] = timesMovedNewString;
                weNeedAPlaceForThisString = false;
            }else if(words[indexNewString] == null || words[indexNewString].equals(newString)){
                if(words[indexNewString] == null) {
                    words[indexNewString] = newString;
                }
                count[indexNewString]++;
                weNeedAPlaceForThisString = false;
            }
            else{
                timesMovedNewString++;
            }
        }
    }

    private static ArrayList<String> importFile(String filename) {
        String splitBy = " ";
        String characters;
        String[] splittedLine;
        ArrayList<String> returnValue = new ArrayList<>();

        try (
                BufferedReader br = new BufferedReader(new FileReader(filename))) {

            while ((characters = br.readLine()) != null) {
                splittedLine = characters.split(splitBy);
                returnValue.addAll(Arrays.asList(splittedLine));
            }

        } catch (Exception e) {
            System.out.println("Explosionen!");
        }
        return returnValue;
    }

    public static long calcPrime(long minimum) {
        boolean foundPrime = false;
        while (!foundPrime) {
            if (isPrime(minimum)) {
                foundPrime = true;
            } else {
                minimum++;
            }
        }
        return minimum;
    }

    private static boolean isPrime(long l) {
        return l != 1 && getFactors(l).length == 2;
    }

    private static long[] getFactors(long l) {
        List list = new LinkedList<>();
        for (long i = 1; i <= l / 2; i++) {
            if (l % i == 0) {
                list.add(i);
            }
        }
        list.add(l);
        long[] result = new long[list.size()];
        int len = list.size();
        for (int i = 0; i < len; i++) {
            result[i] = (long) list.get(i);
        }
        return result;
    }

    private static int calculateHashmapSize(ArrayList<String> importedWords) {
        int hashmapSize = (int) calcPrime((long) importedWords.size());
        wiselyChoosenPrime = hashmapSize;

        return hashmapSize;
    }

    private static void pleaseHashAgain(String stringToStore, int previouslyCalculatedIndex) throws Exception {
        switch (strategy) {
            case (LINEAR): {
                int newIndex = Math.abs(previouslyCalculatedIndex - doLinearProbing(stringToStore, previouslyCalculatedIndex)) % wiselyChoosenPrime;
                if (words[newIndex] == null) {
                    words[newIndex] = stringToStore;
                }
                count[newIndex]++;
                break;
            }
            case (QUADRATIC): {
                int newIndex = Math.abs(previouslyCalculatedIndex - doQuadraticProbing(stringToStore, previouslyCalculatedIndex)) % wiselyChoosenPrime;
                if (words[newIndex] == null) {
                    words[newIndex] = stringToStore;
                }
                count[newIndex]++;
                break;
            }
            case (DOUBLEHASH): {
                doBrentWithDoubleHash(stringToStore, previouslyCalculatedIndex);
                break;
            }
            default: {
                throw new Exception("Missing Hashing-Strategy");
            }
        }
    }

    private static int pleaseSearchAgain(String stringToFind, int calculatedIndex) throws Exception {
        switch (strategy) {
            case (LINEAR): {
                return doLinearProbing(stringToFind, calculatedIndex);
                        }
            case (QUADRATIC): {
                return doQuadraticProbing(stringToFind, calculatedIndex);
            }
            case (DOUBLEHASH): {
                return doBrentProbingSearch(stringToFind);
            }
            default: {
                throw new Exception("Missing Hashing-Strategy");
            }
        }
    }

    private static int doBrentProbingSearch(String stringToFind) {
        boolean weNeedAPlaceForThisString = true;
        int timesMovedString = 0;
        
        int thisIsThePlace = Math.abs(0 - timesMovedString * doDoubleHashProbing(stringToFind)) % wiselyChoosenPrime;
        while (weNeedAPlaceForThisString) {
            if (words[thisIsThePlace] == null || words[thisIsThePlace].equals(stringToFind)) {
                return thisIsThePlace;
            }
            thisIsThePlace = Math.abs(thisIsThePlace - timesMovedString * doDoubleHashProbing(stringToFind)) % wiselyChoosenPrime;
            timesMovedString++;
        }
        return -1;
    }

    public static int getCollisions() {
        return collisions;
    }
    
    public static int getHashSize() {
        return words.length;
    }
    
    public static int getWiselyChoosenPrime() {
        return wiselyChoosenPrime;
    }
    
    public static int getNumberOfUniqueWords(String filename) throws Exception {
        Set<String> tempSet = new HashSet<>();
        
        hash(DOUBLEHASH, filename);
        
        for(String s : words){
            if(s != null){
                tempSet.add(s);
            }
        }
        
        return tempSet.size();
    }
}
