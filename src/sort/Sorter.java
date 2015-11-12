package sort;

import interfaces.AdtArray;

public class Sorter {

    private static final int AlgRQ = 0;
    private static final int AlgWQ = 1;
    private static final int AdtRQ = 2;
    private static final int AdtWQ = 3;
    private static final int AlgRI = 4;
    private static final int AlgWI = 5;
    private static final int AdtRI = 6;
    private static final int AdtWI = 7;

    private static int insertionThreshold = 2;
    private static long[] timeA = new long[2];
    private static long[] stepsA = new long[8];
    private static long insertionTime;
    private static long steps = 0;

    private static boolean subroutine = false;

    public static int getInsertionThreshold() {
        return insertionThreshold;
    }

    private Sorter() {
    }

    public static void insertionsort(AdtArray array, int begin, int end) {
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

    public static void quicksort(AdtArray array, PivotMethod pivot) {
        quicksort_(array, pivot, 0, array.length());
    }

    public static void quicksort_(AdtArray array, PivotMethod pivot, int left, int right) {
        if (right - left < getInsertionThreshold()) {
            insertionsort(array, left, right);
        } else {
            int pivotIndex = pivot.getPivotIndex(left, right);
            if (left < right) {
                int i = quickswap(array, left, right, pivotIndex);

                quicksort_(array, pivot, left, i - 1);
                quicksort_(array, pivot, i + 1, right);
            }
        }
    }

    private static int quickswap(AdtArray array, int ilinks, int irechts, int pivotIndex) {
        int i = ilinks;
        int j = irechts - 1;
        int pivot = array.get(pivotIndex);

        while (i <= j) {
            while ((array.get(i) <= pivot) && (i < irechts)) {
                i++;
            }
            while ((ilinks <= j) && (array.get(j) > pivot)) {
                j--;
            }
            if (i < j) {
                int temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
            }
        }
        int temp = array.get(i);
        array.set(i, array.get(irechts));
        array.set(irechts, temp);

        return i;
    }

    public static long insertionsortTime(AdtArray array, int begin, int end) {
        insertionTime = System.currentTimeMillis();
        insertionsort(array, begin, end);
        insertionTime = System.currentTimeMillis() - insertionTime;
        return insertionTime;
    }

    /**
     *
     * @param array
     * @param pivot
     * @return long[0] = quicksorttime | long[1] = insertiontime
     */
    public static long[] quicksortTime(AdtArray array, PivotMethod pivot) {
        timeA[1] = 0;
        timeA[0] = System.currentTimeMillis();

        quicksortTime_(array, pivot, 0, array.length());        

        timeA[0] = System.currentTimeMillis() - timeA[0] - timeA[1];
        return timeA;
    }

    private static void quicksortTime_(AdtArray array, PivotMethod pivot, int left, int right) {
        if (right - left < getInsertionThreshold()) {
            insertionsortTime(array, left, right);
            timeA[1] =+ insertionTime;
        } else {
            int pivotIndex = pivot.getPivotIndex(left, right);
            if (left < right) {
                int i = quickswap(array, left, right, pivotIndex);

                quicksortTime_(array, pivot, left, i - 1);
                quicksortTime_(array, pivot, i + 1, right);
            }
        }
    }

    /**
     *
     * @param array
     * @param begin
     * @param end
     * @return stepsA[AlgRI] = readaccess Algo | stepsA[AlgWI] = writeaccess
     * Algo | stepsA[AdtRI] = readaccess Adt | stepsA[AdtWI] = writeaccess Adt
     */
    public static long[] insertionsortSteps(AdtArray array, int begin, int end) {
        if (!subroutine) {
            clearStepsArray();
        }

        if (begin < 1) {
            begin = 1;
            stepsA[AlgWI]++;
        }

        if (end > array.length()) {
            end = array.length();
            stepsA[AlgWI]++;
            stepsA[AdtRI]++;
        }

        stepsA[AlgWI]++;

        for (int i = begin; i <= end; i++) {
            int j = i;
            int temp = array.get(i);
            stepsA[AlgWI]++;
            stepsA[AdtRI]++;

            while (j > 0 && array.get(j - 1) > temp) {
                stepsA[AdtRI]++;
                array.set(j, array.get(j - 1));
                stepsA[AdtWI]++;
                stepsA[AdtRI]++;
                j--;
            }
            array.set(j, temp);
            stepsA[AdtWI]++;
        }
        long[] returnValue = new long[8];        
        System.arraycopy( stepsA, 0, returnValue, 0, stepsA.length );
        if (!subroutine) {
            clearStepsArray();
        }
        return returnValue;
    }

    /**
     *
     * @param array
     * @param pivot
     * @return stepsA[AlgRQ] = readaccess Adt Quick | stepsA[AlgWQ] =
     * writeaccess Adt Quick | stepsA[AdtRQ] = readaccess Adt Quick |
     * stepsA[AdtWQ] = writeaccess Adt Quick
     */
    public static long[] quicksortSteps(AdtArray array, PivotMethod pivot) {
        clearStepsArray();

        quicksortSteps_(array, pivot, 0, array.length());
        
        long[] returnValue = new long[8];
        System.arraycopy( stepsA, 0, returnValue, 0, stepsA.length );
        clearStepsArray();
        return returnValue;
    }

    public static void clearStepsArray() {
        for (int i = 0; i < stepsA.length; i++) {
            stepsA[i] = 0;
        }
    }

    private static void quicksortSteps_(AdtArray array, PivotMethod pivot, int left, int right) {
        if (right - left < getInsertionThreshold()) {
            subroutine = true;
            insertionsortSteps(array, left, right);
            subroutine = false;
        } else {
            int pivotIndex = pivot.getPivotIndex(left, right);
            stepsA[AlgWQ]++;
            if (left < right) {
                int i = quickswapSteps(array, left, right, pivotIndex);
                stepsA[AlgWQ]++;
                quicksortSteps_(array, pivot, left, i - 1);
                quicksortSteps_(array, pivot, i + 1, right);
            }
        }
    }

    private static int quickswapSteps(AdtArray array, int ilinks, int irechts, int pivotIndex) {
        int i = ilinks;
        stepsA[AlgWQ]++;
        int j = irechts - 1;
        stepsA[AlgWQ]++;
        int pivot = array.get(pivotIndex);
        stepsA[AdtRQ]++;
        stepsA[AlgWQ]++;

        while (i <= j) {
            while ((array.get(i) <= pivot) && (i < irechts)) {
                i++;
                stepsA[AdtRQ]++;
            }
            while ((ilinks <= j) && (array.get(j) > pivot)) {
                j--;
                stepsA[AdtRQ]++;
            }
            if (i < j) {
                int temp = array.get(i);
                stepsA[AdtRQ]++;
                stepsA[AlgWQ]++;
                array.set(i, array.get(j));
                stepsA[AdtRQ]++;
                stepsA[AdtWQ]++;
                array.set(j, temp);
                stepsA[AdtWQ]++;
            }
        }
        int temp = array.get(i);
        stepsA[AdtRQ]++;
        stepsA[AlgWQ]++;
        array.set(i, array.get(irechts));
        stepsA[AdtRQ]++;
        stepsA[AdtWQ]++;
        array.set(irechts, temp);
        stepsA[AdtWQ]++;

        return i;
    }

    @FunctionalInterface
    public interface PivotMethod {

        int getPivotIndex(int start, int end);
    }
}
