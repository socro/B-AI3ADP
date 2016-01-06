package implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class AdtHashmapImpl{
    private static final String LINEAR = "L";
    private static final String QUADRATIC = "Q";
    private static final String BRENT = "B";
    
    private static String strategy;
    private static String[] words;
    private static int[] count;
    
    private static ArrayList<String> importedWords;
    
    public static void hash(String strategy, String filename){
        AdtHashmapImpl.strategy = strategy;
        
        importedWords = importFile(filename);
    }
    private static void create(int size, String strategy){
        words = new String[size];
        count = new int[size];          
    }
    public static void insert (AdtHashmapImpl hashmap, String word){
        
    }
    public static int find(AdtHashmapImpl hashmap, String word){
        int returnCount = 0;
        
        return returnCount;
    }
    private static int divisionRemainderMethod(String word){
        int index = 0;
        
        return index;        
    }
    private static int doLinearProbing(String word){
        int index = 0;
        
        return index;        
    }
    private static int doQuadraticProbing(String word){
        int index = 0;
        
        return index;        
    }
    private static int doDoubleHashing(String word){
        int index = 0;
        
        return index;
        
    }
    
    public static ArrayList<String> importFile(String filename) {
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
}
