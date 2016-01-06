package implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdtHashmapImpl {

    public static final String LINEAR = "L";
    public static final String QUADRATIC = "Q";
    public static final String DOUBLEHASH = "B";

    private static String strategy;
    private static String[] words;
    private static int[] count;

    private static int wiselyChoosenPrime = 0;

    private static ArrayList<String> importedWords;

    public static void hash(String strategy, String filename) throws Exception {
        importedWords = importFile(filename);

        create(calculateHashmapSize(importedWords), strategy);

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

        System.out.println("");
    }

    private static void create(int size, String strategy) {
        AdtHashmapImpl.strategy = strategy;
        words = new String[size];
        count = new int[size];
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
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
        System.out.println("Hashwert zu: " + word + " -> " + index);
        return index;
    }

    private static int doDoubleHashProbing(String word, int previouslyCalculatedIndex) {
        int index = 0;

        index = 1 + (previouslyCalculatedIndex % wiselyChoosenPrime - 2);

        System.out.println("Double Hashwert zu: " + word + " -> " + index);

        return index;
    }

    private static int doLinearProbing(String stringToStore, int previouslyCalculatedIndex) {
        boolean weNeedAPlaceForThisString = true;

        int thisIsThePlace = previouslyCalculatedIndex + 1;
        while (weNeedAPlaceForThisString) {
            if (words[thisIsThePlace] == null || words[thisIsThePlace].equals(stringToStore)) {
                return thisIsThePlace;
            }
            thisIsThePlace = (thisIsThePlace + 1) % words.length;
        }
        return -1;
    }

    private static int doQuadraticProbing(String stringToStore, int previouslyCalculatedIndex) {
        boolean weNeedAPlaceForThisString = true;

        while (weNeedAPlaceForThisString) {
            if (words[previouslyCalculatedIndex] == null || words[previouslyCalculatedIndex].equals(stringToStore)) {
                return previouslyCalculatedIndex;
            }
            previouslyCalculatedIndex = Math.abs(((previouslyCalculatedIndex / 2) ^ 2) * (-1) ^ previouslyCalculatedIndex) % wiselyChoosenPrime;
        }
        return -1;
    }

    // TODO Double Hashing nach Brent
    private static void doBrentWithDoubleHash(String newString, int previouslyCalculatedIndex) {
        String oldString = words[previouslyCalculatedIndex];
        
        boolean weNeedAPlaceForThisString = true;
        boolean weNeedAPlaceForThatString = true;

        int timesMovedNewString = 0;
        int timesMovedOldString = 0;

        int indexNewString = doDoubleHashProbing(newString, previouslyCalculatedIndex);
        int indexOldString = doDoubleHashProbing(oldString, previouslyCalculatedIndex);

        while (weNeedAPlaceForThisString) {
            if (words[indexNewString] == null || words[indexNewString].equals(newString)) {
                weNeedAPlaceForThisString = false;
                timesMovedNewString++;
                indexNewString = doDoubleHashProbing(newString, indexNewString);
            }
        }
        while (weNeedAPlaceForThatString) {
            if (words[indexOldString] == null || words[indexOldString].equals(oldString)) {
                weNeedAPlaceForThatString = false;
                timesMovedOldString++;
                indexOldString = doDoubleHashProbing(oldString, indexOldString);
            }
        }
        if (timesMovedNewString < timesMovedOldString) {
            if(words[indexNewString] == null){
                words[indexNewString] = newString;                
            }
            count[indexNewString]++;
        }else if(timesMovedOldString < timesMovedNewString){
            if(words[indexOldString] == null){
                words[indexOldString] = words[previouslyCalculatedIndex];
                count[indexOldString] = count[previouslyCalculatedIndex];
            } else if(words[indexOldString].equals(oldString)){
                count[indexOldString]++;
            }
            words[previouslyCalculatedIndex] = newString;
            count[previouslyCalculatedIndex] = 1;            
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
                for (String s : splittedLine) {
                    returnValue.add(s);
                }
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
                int newIndex = doLinearProbing(stringToStore, previouslyCalculatedIndex);
                if (words[newIndex] == null) {
                    words[newIndex] = stringToStore;
                }
                count[newIndex]++;
                break;
            }
            case (QUADRATIC): {
                int newIndex = doQuadraticProbing(stringToStore, previouslyCalculatedIndex);
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
                return doBrentProbingSearch(stringToFind, calculatedIndex);
            }
            default: {
                throw new Exception("Missing Hashing-Strategy");
            }
        }
    }

    private static int doBrentProbingSearch(String stringToFind, int calculatedIndex) {
        boolean weNeedAPlaceForThisString = true;

        int thisIsThePlace = calculatedIndex + 1;
        while (weNeedAPlaceForThisString) {
            if (words[thisIsThePlace] == null || words[thisIsThePlace].equals(stringToFind)) {
                return thisIsThePlace;
            }
            thisIsThePlace = 1 + (thisIsThePlace % wiselyChoosenPrime - 2);
        }
        return -1;
    }
}
