package sort;

import implementations.AdtAVLTree;
import implementations.AdtAVLTreeIO;
import interfaces.AdtArray;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;

public class Benchmark {

    private static final Random rnd = new Random();
    private static boolean firstrun = true;
    private static final String nl = System.lineSeparator();
    private static final int UNSORTED = 1;
    private static final int LEFTSORTED = 2;
    private static final int RIGHTSORTED = 3;
    private static int howManyNumbers;

    public static void main(String args[]) throws InterruptedException {
        // Initialisierung fuer die Tests
        System.out.println("Beginn des Benchmark");
        System.out.println("--------------------");
        System.out.println("Insert-Tests");
        howManyNumbers = 20;
        doAVLRuntimeIOTests(howManyNumbers, UNSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, UNSORTED, true);
        doAVLRuntimeIOTests(howManyNumbers, LEFTSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, LEFTSORTED, true);
        doAVLRuntimeIOTests(howManyNumbers, RIGHTSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, RIGHTSORTED, true);
        
        howManyNumbers = 200;
        doAVLRuntimeIOTests(howManyNumbers, UNSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, UNSORTED, true);
        doAVLRuntimeIOTests(howManyNumbers, LEFTSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, LEFTSORTED, true);
        doAVLRuntimeIOTests(howManyNumbers, RIGHTSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, RIGHTSORTED, true);
        
        howManyNumbers = 2000;
        doAVLRuntimeIOTests(howManyNumbers, UNSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, UNSORTED, true);
        doAVLRuntimeIOTests(howManyNumbers, LEFTSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, LEFTSORTED, true);
        doAVLRuntimeIOTests(howManyNumbers, RIGHTSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, RIGHTSORTED, true);
        
        howManyNumbers = 20000;
        doAVLRuntimeIOTests(howManyNumbers, UNSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, UNSORTED, true);
        doAVLRuntimeIOTests(howManyNumbers, LEFTSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, LEFTSORTED, true);
        doAVLRuntimeIOTests(howManyNumbers, RIGHTSORTED, false);
        doAVLRuntimeIOTests(howManyNumbers, RIGHTSORTED, true);
        
        System.out.println("--------------------");
        System.out.println("Delete-Tests");
        
        AdtAVLTree deleteTree = new AdtAVLTree();
        Generator.sortnum(50, false, UNSORTED);
        AdtArray zahlenDat = Generator.importNums("zahlen.dat");
        for(int i = 0; i < zahlenDat.length(); i++){
            deleteTree.insert(zahlenDat.get(i));
        }
        for(int i = 0; i < zahlenDat.length();i++){
            deleteTree.remove(zahlenDat.get(i));
            deleteTree.print("deleteFromTree" + i);
        }
        System.out.println("--------------------");


        // Test mit Klaucks Zahlen
//        Generator.importNums("zahlen.dat");
//        for (int i = 0; i < testzahlen.length(); i++) {
//            testtree.insert(testzahlen.get(i));      
//        }
//        testtree.print("graph_klauck");
        // Test mit 20 Zahlen
        // Runtime Test mit 20 Zahlen
        // IO Test mit 20 Zahlen
        // Test mit 50 Zahlen
//        Generator.sortnum(20, false);
//        testzahlen = Generator.importNums("zahlen.dat");
        // Test mit 100 Zahlen
//        Generator.sortnum(20, false);
//        testzahlen = Generator.importNums("zahlen.dat");      
//        AdtArray klauckarray = Generator.importNums("klauck.dat");
//        System.out.println("read finished");
//        final int medianindexquicksortArray = medianof3(0, klauckarray.length(), klauckarray);        
//        outputToCSV("Quicksort,Time + IO,klauck,pivotmedian,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(klauckarray, (int start, int end) -> (medianindexquicksortArray)),Sorter.quicksortTime(klauckarray, (int start, int end) -> (medianindexquicksortArray)));
//        System.out.println("quicksort finsihed");
//        
//        klauckarray = Generator.importNums("klauck.dat");
//        System.out.println("read finished");    
//        outputToCSV("Quicksort,Time + IO,klauck,pivotend,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(klauckarray, (int start, int end) -> (end)),Sorter.quicksortTime(klauckarray, (int start, int end) -> (end)));
//        System.out.println("quicksort finsihed");
//        
//        klauckarray = Generator.importNums("klauck.dat");
//        System.out.println("read finished");    
//        outputToCSV("Quicksort,Time + IO,klauck,pivotstart,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(klauckarray, (int start, int end) -> (start)),Sorter.quicksortTime(klauckarray, (int start, int end) -> (start)));
//        System.out.println("quicksort finsihed");
//        
//        
//        final int rndindex = rnd.nextInt(klauckarray.length()+1);
//        klauckarray = Generator.importNums("klauck.dat");
//        System.out.println("read finished");    
//        outputToCSV("Quicksort,Time + IO,klauck,pivotrnd,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(klauckarray, (int start, int end) -> (rndindex)),Sorter.quicksortTime(klauckarray, (int start, int end) -> (rndindex)));
//        System.out.println("quicksort finsihed");
//        
//        Generator.sortnum(howmanynumbersdoyouwant);
//        AdtArray quicksortArray = Generator.importNums("zahlen.dat");
//        AdtArray insertionsortArray = Generator.importNums("zahlen.dat");
//        Generator.sortnumLeft(howmanynumbersdoyouwant);
//        AdtArray quicksortArray_leftsorted = Generator.importNums("zahlen.dat");
//        AdtArray insertionsortArray_leftsorted = Generator.importNums("zahlen.dat");
//        Generator.sortnumRight(howmanynumbersdoyouwant);
//        AdtArray quicksortArray_rightsorted = Generator.importNums("zahlen.dat");
//        AdtArray insertionsortArray_rightsorted = Generator.importNums("zahlen.dat");
//
//        outputToCSV("Quicksort,Time + IO,rndsorted,pivotend,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(quicksortArray, (int start, int end) -> (end)),Sorter.quicksortTime(quicksortArray, (int start, int end) -> (end)));        
//        outputToCSV("Insertionsort,Time + IO,rndsorted,pivotend,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(insertionsortArray,0,insertionsortArray.length()),new long[]{0,Sorter.insertionsortTime(insertionsortArray,0,insertionsortArray.length())});
//        
//        outputToCSV("Quicksort,Time + IO,leftsorted,pivotend,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(quicksortArray_leftsorted, (int start, int end) -> (end)),Sorter.quicksortTime(quicksortArray_leftsorted, (int start, int end) -> (end)));
//        outputToCSV("Insertionsort,Time + IO,leftsorted,pivotend,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(insertionsortArray_leftsorted,0,insertionsortArray_leftsorted.length()),new long[]{0,0,Sorter.insertionsortTime(insertionsortArray_leftsorted,0,insertionsortArray_leftsorted.length())});
//                
//        outputToCSV("Quicksort,Time + IO,rightsorted,pivotend,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(quicksortArray_rightsorted, (int start, int end) -> (end)),Sorter.quicksortTime(quicksortArray_rightsorted, (int start, int end) -> (end)));
//        outputToCSV("Insertionsort,Time + IO,rightsorted,pivotend,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(insertionsortArray_rightsorted,0,insertionsortArray_rightsorted.length()),new long[]{0,Sorter.insertionsortTime(insertionsortArray_rightsorted,0,insertionsortArray_rightsorted.length())});
//        
//        
//        Generator.sortnum(howmanynumbersdoyouwant);
//        quicksortArray = Generator.importNums("zahlen.dat");
//        insertionsortArray = Generator.importNums("zahlen.dat");
//        Generator.sortnumLeft(howmanynumbersdoyouwant);
//        quicksortArray_leftsorted = Generator.importNums("zahlen.dat");
//        insertionsortArray_leftsorted = Generator.importNums("zahlen.dat");
//        Generator.sortnumRight(howmanynumbersdoyouwant);
//        quicksortArray_rightsorted = Generator.importNums("zahlen.dat");
//        insertionsortArray_rightsorted = Generator.importNums("zahlen.dat");
//        
//        outputToCSV("Quicksort,Time + IO,rndsorted,pivotstart,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(quicksortArray, (int start, int end) -> (start)),Sorter.quicksortTime(quicksortArray, (int start, int end) -> (start)));        
//        outputToCSV("Insertionsort,Time + IO,rndsorted,pivotstart,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(insertionsortArray,0,insertionsortArray.length()),new long[]{0,Sorter.insertionsortTime(insertionsortArray,0,insertionsortArray.length())});
//        
//        outputToCSV("Quicksort,Time + IO,leftsorted,pivotstart,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(quicksortArray_leftsorted, (int start, int end) -> (start)),Sorter.quicksortTime(quicksortArray_leftsorted, (int start, int end) -> (start)));
//        outputToCSV("Insertionsort,Time + IO,leftsorted,pivotstart,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(insertionsortArray_leftsorted,0,insertionsortArray_leftsorted.length()),new long[]{0,0,Sorter.insertionsortTime(insertionsortArray_leftsorted,0,insertionsortArray_leftsorted.length())});
//                
//        outputToCSV("Quicksort,Time + IO,rightsorted,pivotstart,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(quicksortArray_rightsorted, (int start, int end) -> (start)),Sorter.quicksortTime(quicksortArray_rightsorted, (int start, int end) -> (start)));
//        outputToCSV("Insertionsort,Time + IO,rightsorted,pivotstart,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(insertionsortArray_rightsorted,0,insertionsortArray_rightsorted.length()),new long[]{0,Sorter.insertionsortTime(insertionsortArray_rightsorted,0,insertionsortArray_rightsorted.length())});
//        
//        
//        Generator.sortnum(howmanynumbersdoyouwant);
//        quicksortArray = Generator.importNums("zahlen.dat");
//        insertionsortArray = Generator.importNums("zahlen.dat");
//        Generator.sortnumLeft(howmanynumbersdoyouwant);
//        quicksortArray_leftsorted = Generator.importNums("zahlen.dat");
//        insertionsortArray_leftsorted = Generator.importNums("zahlen.dat");
//        Generator.sortnumRight(howmanynumbersdoyouwant);
//        quicksortArray_rightsorted = Generator.importNums("zahlen.dat");
//        insertionsortArray_rightsorted = Generator.importNums("zahlen.dat");
//        
//        final int medianindexquicksortArray = medianof3(0, quicksortArray.length(), quicksortArray);
//        final int medianindexquicksortArray_leftsorted = medianof3(0, quicksortArray.length(), quicksortArray);
//        final int medianindexquicksortArray_rightsorted = medianof3(0, quicksortArray.length(), quicksortArray);
//        
//        outputToCSV("Quicksort,Time + IO,rndsorted,pivotmedian,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(quicksortArray, (int start, int end) -> (medianindexquicksortArray)),Sorter.quicksortTime(quicksortArray, (int start, int end) -> (medianindexquicksortArray)));        
//        outputToCSV("Insertionsort,Time + IO,rndsorted,pivotmedian,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(insertionsortArray,0,insertionsortArray.length()),new long[]{0,Sorter.insertionsortTime(insertionsortArray,0,insertionsortArray.length())});
//        
//        outputToCSV("Quicksort,Time + IO,leftsorted,pivotmedian,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(quicksortArray_leftsorted, (int start, int end) -> (medianindexquicksortArray_leftsorted)),Sorter.quicksortTime(quicksortArray_leftsorted, (int start, int end) -> (medianindexquicksortArray_leftsorted)));
//        outputToCSV("Insertionsort,Time + IO,leftsorted,pivotmedian,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(insertionsortArray_leftsorted,0,insertionsortArray_leftsorted.length()),new long[]{0,0,Sorter.insertionsortTime(insertionsortArray_leftsorted,0,insertionsortArray_leftsorted.length())});
//                
//        outputToCSV("Quicksort,Time + IO,rightsorted,pivotmedian,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(quicksortArray_rightsorted, (int start, int end) -> (medianindexquicksortArray_rightsorted)),Sorter.quicksortTime(quicksortArray_rightsorted, (int start, int end) -> (medianindexquicksortArray_rightsorted)));
//        outputToCSV("Insertionsort,Time + IO,rightsorted,pivotmedian,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(insertionsortArray_rightsorted,0,insertionsortArray_rightsorted.length()),new long[]{0,Sorter.insertionsortTime(insertionsortArray_rightsorted,0,insertionsortArray_rightsorted.length())});
//        
//        
        //----------------------------------------
        System.out.println("Ende vom Benchmark");
    }

    public static void doAVLTests(int howManyNumbers, boolean doYouWantDuplicates) throws InterruptedException {
        AdtAVLTree testtree = AdtAVLTree.create();
        Generator.sortnum(howManyNumbers, doYouWantDuplicates);
        AdtArray testzahlen = Generator.importNums("zahlen.dat");
        for (int i = 0; i < testzahlen.length(); i++) {
            testtree.insert(testzahlen.get(i));
        }
        testtree.print("AVL" + howManyNumbers + doYouWantDuplicates);
    }

    public static void doAVLRuntimeIOTests(int howManyNumbers,int sortOrder, boolean doYouWantDuplicates) throws InterruptedException {       
        AdtAVLTree testtree = AdtAVLTree.create();
        AdtAVLTreeIO testtreeIO = AdtAVLTreeIO.create();
        long[] timebefore = new long[1];
        long[] timeafter = new long[1];
        Generator.sortnum(howManyNumbers, doYouWantDuplicates, sortOrder);
        AdtArray testzahlen = Generator.importNums("zahlen.dat");
        timebefore[0] = System.currentTimeMillis();
        for (int i = 0; i < testzahlen.length(); i++) {
            testtree.insert(testzahlen.get(i));
        }
        timeafter[0] = System.currentTimeMillis() - timebefore[0];
        for (int i = 0; i < testzahlen.length(); i++) {
            testtreeIO.insert(testzahlen.get(i));
        }
        testtree.print("AVLRuntimeIO" + howManyNumbers + doYouWantDuplicates + sortOrder);
        outputToCSV("AVLRuntimeIO," + howManyNumbers + "," + doYouWantDuplicates + "," + sortOrder, testtreeIO.stepsA, timeafter);
    }

    public static void outputToCSV(String filename, long[] ioTest, long[] runtimeTest) {
        if (firstrun) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename+".csv", true), "utf-8"))) {
                //writer.write("Testname,Testarten,RNDSortierung,PivotPos,#Nr,Insertionthreshold,AlgRQ,AlgWQ,AdtRQ,AdtWQ,AlgRI,AlgWI,AdtRI,AdtWI,RtQ,RtI");
                writer.write("Testname,Testarten,RNDSortierung,#Nr,AlgR,AlgW,AdtR,AdtW");
                writer.write(nl);

            } catch (IOException e) {
                e.printStackTrace();
            }
            firstrun = false;
        }

        long[] args = new long[runtimeTest.length + ioTest.length];
        System.arraycopy(ioTest, 0, args, 0, ioTest.length);
        System.arraycopy(runtimeTest, 0, args, ioTest.length, runtimeTest.length);

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename+".csv", true), "utf-8"))) {
            writer.write(filename + ",");
            // print line
            for (int i = 0; i < args.length; i++) {
                if (i != args.length - 1) {
                    writer.write(args[i] + ",");
                } else {
                    writer.write("" + args[i]);
                }

            }
            writer.write(nl);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int medianof3(int start, int end, AdtArray array1) {
        int middleindex = (end + start) / 2;

        int valuestart = array1.get(start);
        int valuemiddle = array1.get(middleindex);
        int valueend = array1.get(end);
        int medianvalue = Math.min(Math.min(valuestart, valuemiddle), valueend);

        int medianindex = -1;

        for (int i = 0; i < array1.length(); i++) {
            if (array1.get(i) == medianvalue) {
                medianindex = i;
                break;
            }
        }

        final int returnvalue = medianindex;

        return returnvalue;
    }
}
