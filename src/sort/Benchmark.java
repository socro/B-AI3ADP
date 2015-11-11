package sort;

import interfaces.AdtArray;
import static org.junit.Assert.*;

public class Benchmark {

    public static void main(String args[]){
        Generator.sortnum(15);
        AdtArray array1 = Generator.importNums("zahlen.dat");
        AdtArray array2 = Generator.importNums("zahlen.dat");
        Sorter.quicksort(array1, (int start, int end) -> (start+end)/2);
        Sorter.insertionsort(array2, 0, array2.length());
        //----------------------------------------
        System.out.println("Ende vom Benchmark");
    }
}