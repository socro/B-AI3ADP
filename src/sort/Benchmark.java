package sort;

import interfaces.AdtArray;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Benchmark {
    
    private static boolean firstrun = true;
    private static String nl = System.lineSeparator();
    
    private static int howmanynumbersdoyouwant = 3000;
    
    public static void main(String args[]) {
        System.out.println("Beginn des Benchmark");
        AdtArray klauckarray = Generator.importNums("klauck.dat");
        final int medianindexquicksortArray = medianof3(0, klauckarray.length(), klauckarray);
        outputToCSV("Quicksort,Time + IO,rndsorted,pivotmedian,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.quicksortSteps(klauckarray, (int start, int end) -> (medianindexquicksortArray)),Sorter.quicksortTime(klauckarray, (int start, int end) -> (medianindexquicksortArray)));        
        outputToCSV("Insertionsort_Klauck,Time + IO,rndsorted,pivotmedian,"+howmanynumbersdoyouwant+","+Sorter.getInsertionThreshold(),Sorter.insertionsortSteps(klauckarray,0,klauckarray.length()),new long[]{0,Sorter.insertionsortTime(klauckarray,0,klauckarray.length())});
        
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
//        //----------------------------------------
//        System.out.println("Ende vom Benchmark");
    }

    public static void outputToCSV(String filename,long[] ioTest,long[] runtimeTest) {
        if(firstrun){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename, true), "utf-8"))) {
                writer.write("Testname,Testarten,RNDSortierung,PivotPos,#Nr,Insertionthreshold,AlgRQ,AlgWQ,AdtRQ,AdtWQ,AlgRI,AlgWI,AdtRI,AdtWI,RtQ,RtI");
                writer.write(nl);

        } catch (IOException e) {
            e.printStackTrace();
        }
            firstrun= false;
        }

        long[] args = new long[runtimeTest.length+ioTest.length];
        System.arraycopy(ioTest, 0, args, 0, ioTest.length);
        System.arraycopy(runtimeTest, 0, args, ioTest.length, runtimeTest.length);
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename, true), "utf-8"))) {
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
        
        int valuestart  = array1.get(start);
        int valuemiddle = array1.get(middleindex);
        int valueend    = array1.get(end);
        int medianvalue = Math.min(Math.min(valuestart, valuemiddle), valueend);
        
        int medianindex = -1;
        
        for(int i = 0; i < array1.length(); i++){
            if (array1.get(i) == medianvalue){
                medianindex = i;
                break;
            }
        }
        
        final int returnvalue = medianindex;
        
        return returnvalue;                
    }
}
