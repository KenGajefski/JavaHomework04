package homework04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MicrosoftMonarchs {

    // Global variables
    private static final String FILE_PATH = "MicrosoftStockData.txt";

    // -----------------------------------------------------------------------------------------------------------------
    // readTetFile method
    private static void readTextFile(String[] dates, double[] close, int[] volume,
                             double[] open, double[] high, double[] low){
        try
        {
            Scanner fileIn = null;
            String headerLine;
            int counter = 0;

            fileIn = new Scanner(new FileInputStream(FILE_PATH));
            fileIn.useDelimiter("[,|\\s]+");

            // Taking the first line out of the buffer for the reader
            headerLine = fileIn.nextLine();

            while (fileIn.hasNext()){

                dates[counter] = fileIn.next();
                close[counter] = fileIn.nextDouble();
                volume[counter] = fileIn.nextInt();
                open[counter] = fileIn.nextDouble();
                high[counter] = fileIn.nextDouble();
                // This is where it breaks
                //low[counter] = fileIn.nextDouble();
                fileIn.nextLine();

                System.out.println(dates[counter]);
                System.out.println(close[counter]);
                System.out.println(volume[counter]);
                System.out.println(open[counter]);
                System.out.println(high[counter]);
                //System.out.println(low[counter]);

                counter++;
            }
            // End of while loop
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unable to find or open the file.");
        }
    }
    // End of readTextFile method
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // Main
    public static void main(String[] args){

        String[] dates = new String[2518];
        double[] close = new double[2518];
        int[] volume = new int[2518];
        double[] open = new double[2518];
        double[] high = new double[2518];
        double[] low = new double[2518];

        System.out.println("Welcome to Microsoft Monarchs!");
        System.out.println("------------------------------");

        readTextFile(dates, close, volume, open, high, low);

    }
    // End of Main
    // -----------------------------------------------------------------------------------------------------------------

}
