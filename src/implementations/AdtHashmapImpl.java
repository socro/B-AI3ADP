package implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AdtHashmapImpl{
    public static final String LINEAR = "L";
    public static final String QUADRATIC = "Q";
    public static final String DOUBLEHASH = "B";
    
    private static String strategy;
    private static String[] words;
    private static int[] count;
    
    private static int wiselyChoosenPrime = 0;
    
    private static ArrayList<String> importedWords;
    
    public static void hash(String strategy, String filename) throws Exception{
        importedWords = importFile(filename);
                
        create(calculateHashmapSize(importedWords), strategy);
        
        for(String stringToStore : importedWords){
            int calculatedIndex = divisionRemainderMethod(stringToStore);
            if(words[calculatedIndex] == null){
                words[calculatedIndex] = stringToStore;
                count[calculatedIndex]++;
            } else if(words[calculatedIndex].equals(stringToStore)){
                count[calculatedIndex]++;
            } else {
                int carefullyCalculatedIndex = pleaseHashAgain(stringToStore,calculatedIndex);
                if(words[carefullyCalculatedIndex] == null){
                    words[carefullyCalculatedIndex] = stringToStore;
                }                
                count[carefullyCalculatedIndex]++;
            }
        }
        
        System.out.println("");
    }
    private static void create(int size, String strategy){
        AdtHashmapImpl.strategy = strategy;
        words = new String[size];
        count = new int[size];
        for(int i = 0; i < count.length; i++){
            count[i] = 0;
        }
    }
    public static void insert (String stringToStore) throws Exception{
        int calculatedIndex = divisionRemainderMethod(stringToStore);
            if(words[calculatedIndex] == null){
                words[calculatedIndex] = stringToStore;
                count[calculatedIndex]++;
            } else if(words[calculatedIndex].equals(stringToStore)){
                count[calculatedIndex]++;
            } else {
                int carefullyCalculatedIndex = pleaseHashAgain(stringToStore,calculatedIndex);
                if(words[carefullyCalculatedIndex] == null){
                    words[carefullyCalculatedIndex] = stringToStore;
                }                
                count[carefullyCalculatedIndex]++;
            }
    }
    public static int find(String stringToFind) throws Exception{
        int returnCount = -1;
        
        int calculatedIndex = divisionRemainderMethod(stringToFind);
            if(words[calculatedIndex] == null){} 
            else if(words[calculatedIndex].equals(stringToFind)){
                return count[calculatedIndex];
            } else {
                return count[pleaseHashAgain(stringToFind,calculatedIndex)];
            }
        
        return returnCount;
    }
    private static int divisionRemainderMethod(String word){
        int index = 0;
        
        for(int i = 0; i < word.length(); i++){
            index = (index * 128 + (int) word.charAt(i)) % wiselyChoosenPrime;
        }
        System.out.println("Hashwert zu: " + word + " -> " + index);
        return index;        
    }
    
    private static int doLinearProbing(String stringToStore,int previouslyCalculatedIndex){
        boolean weNeedAPlaceForThisString = true;
        
        int thisIsThePlace = previouslyCalculatedIndex + 1;
        while(weNeedAPlaceForThisString){
            if(words[thisIsThePlace] == null || words[thisIsThePlace].equals(stringToStore)){
                return thisIsThePlace;
            }             
            thisIsThePlace = (thisIsThePlace + 1) % words.length;
        }
        return -1;
    }
    
    private static int doQuadraticProbing(String stringToStore, int previouslyCalculatedIndex){
        boolean weNeedAPlaceForThisString = true;
        
        int thisIsThePlace = previouslyCalculatedIndex + 1;
        while(weNeedAPlaceForThisString){
            if(words[thisIsThePlace] == null || words[thisIsThePlace].equals(stringToStore)){
                return thisIsThePlace;
            }             
            thisIsThePlace = (((thisIsThePlace/2)^2) * (-1)^thisIsThePlace )% words.length;
        }
        return -1;
    }
    
    // TODO bitte weiter bearbeiten, aber dalli
    private static int doDoubleHashing(String stringToStore, int previouslyCalculatedIndex){
        boolean weNeedAPlaceForThisString = true;
        
        int thisIsThePlace = previouslyCalculatedIndex + 1;
        while(weNeedAPlaceForThisString){
            if(words[thisIsThePlace] == null || words[thisIsThePlace].equals(stringToStore)){
                return thisIsThePlace;
            }             
            
            //#############################################################################
            thisIsThePlace = (((thisIsThePlace/2)^2) * (-1)^thisIsThePlace )% words.length;
            //#############################################################################
        }
        return -1;
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
        while(!foundPrime) {
          if(isPrime(minimum)) {
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
        for(long i = 1 ; i <= l / 2 ; i++) {
            if(l % i == 0) {
                list.add(i);
            }
        }
        list.add(l);
        long[] result = new long[list.size()];
        int len = list.size();
        for(int i = 0; i < len; i++) {
            result[i] = (long) list.get(i);
        }
        return result;
    }

    private static int calculateHashmapSize(ArrayList<String> importedWords) {
        int hashmapSize = (int) calcPrime((long)importedWords.size());
        wiselyChoosenPrime = hashmapSize;
        
        return hashmapSize;
    }

    private static int pleaseHashAgain(String s, int previouslyCalculatedIndex) throws Exception {
        switch(strategy){
            case(LINEAR):{
                return doLinearProbing(s,previouslyCalculatedIndex);
            }
            case(QUADRATIC):{
                return doQuadraticProbing(s,previouslyCalculatedIndex);
            }
            case(DOUBLEHASH):{
                return doDoubleHashing(s,previouslyCalculatedIndex);
            }
            default:{
                throw new Exception("Missing Hashing-Strategy");
            }
        }                
    }
}
