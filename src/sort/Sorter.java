package sort;

import interfaces.AdtArray;

public class Sorter {

    private static int noMagicNumber = 12;
    private static long[] timeA = new long[2];
    private static long[] stepsA = new long[2];
    private static long steps = 0;

    private Sorter(){}
    public static void insertionsort(AdtArray array, int begin, int end) {
        /**
         * @TODO Christof fragen wie der Kack mit den Indizes funktioniert,
         * damit man seinen Sort beschränken kann. Deine Mudda ist beschränkt!
         */

        if (begin < 1) {
            begin = 1;
        }

        if (end > array.length()) {
            end = array.length();
        }

        for (int i = begin; i <= end; i++) {
            int j = i;
            int temp = array.get(i);

            while (j > 0 && array.get(j - 1) > temp) {
                array.set(j, array.get(j - 1));
                j--;
            }
            array.set(j, temp);
        }
    }
    
    public static void quicksort(AdtArray array, PivotMethod pivot){
        quicksort_(array, pivot, 0, array.length());
    }

    public static void quicksort_(AdtArray array, PivotMethod pivot, int left, int right) {
        int pivotIndex = pivot.getPivotIndex(left, right);
        if( left < right){
            int i = quickswap(array, left, right, pivotIndex);
            
            quicksort_(array, pivot, left, i-1);
            quicksort_(array, pivot, i+1, right);
        }
    }
    private static int quickswap(AdtArray array, int ilinks, int irechts, int pivotIndex){
        int i = ilinks;
        int j = irechts-1;
        int pivot = pivotIndex;
        
        while(i<=j){
            while((array.get(i) <= pivot) && (i < irechts)){
                i++;
            }
            while((ilinks <= j) && (array.get(j) > pivot)){
                j--;
            }
            if(i < j){
                int temp = i;
                i = j;
                j = temp;
            }
        }
        return irechts;
    }

    public static long insertionsortTime(AdtArray array, int begin, int end) {
        long time = System.currentTimeMillis();
        insertionsort(array, begin, end);
        return time - System.currentTimeMillis();
    }

    public static long[] quicksortTime(AdtArray array, PivotMethod pivot) {
        timeA[0] = 0;
        timeA[1] = 0;
        quicksort(array, pivot);
        return timeA;
    }

    public static long insertionsortSteps(AdtArray array, int begin, int end) {
        steps = 0;
        insertionsort(array, begin, end);
        return steps;
    }

    public static long[] quicksortSteps(AdtArray array, PivotMethod pivot) {
        stepsA[0] = 0;
        stepsA[1] = 0;
        quicksort(array, pivot);
        return stepsA;
    }

    @FunctionalInterface
    public interface PivotMethod {
        int getPivotIndex(int start, int end);
    }
}
