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

    public static void main(String args[]) {
        Generator.sortnum(1500);
        AdtArray array1 = Generator.importNums("zahlen.dat");

        outputToCSV("QuicksortTime + IO1",Sorter.quicksortSteps(array1, (int start, int end) -> (end)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO2",Sorter.quicksortSteps(array1, (int start, int end) -> (end-1)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO3",Sorter.quicksortSteps(array1, (int start, int end) -> (end-2)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO4",Sorter.quicksortSteps(array1, (int start, int end) -> (end-3)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO5",Sorter.quicksortSteps(array1, (int start, int end) -> (end-4)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO6",Sorter.quicksortSteps(array1, (int start, int end) -> (end-5)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO7",Sorter.quicksortSteps(array1, (int start, int end) -> (end-6)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO8",Sorter.quicksortSteps(array1, (int start, int end) -> (end-7)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO9",Sorter.quicksortSteps(array1, (int start, int end) -> (end-8)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO10",Sorter.quicksortSteps(array1, (int start, int end) -> (end-9)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        outputToCSV("QuicksortTime + IO11",Sorter.quicksortSteps(array1, (int start, int end) -> (end-10)),Sorter.quicksortTime(array1, (int start, int end) -> (end)));
        //----------------------------------------
        System.out.println("Ende vom Benchmark");
    }

    public static void outputToCSV(String filename,long[] ioTest,long[] runtimeTest) {
        if(firstrun){
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename, true), "utf-8"))) {
                writer.write("Testname,AlgRQ,AlgWQ,AdtRQ,AdtWQ,AlgRI,AlgWI,AdtRI,AdtWI,RtQ,RtI");
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
}
