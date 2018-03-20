package homework04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MicrosoftMonarchs {

    // Global variables
    private static final String FILE_PATH = "MicrosoftStockData.txt";
    private static final String COL_DATE = "%11s";
    private static final String COL_COST_HEAD = "%8s";
    private static final String COL_COST = "%8.2f";
    private static final String COL_VOLUME_HEAD = "%14s";
    private static final String COL_VOLUME = "%,14d";

    // -----------------------------------------------------------------------------------------------------------------
    // readTetFile method
    private static void readTextFile(String[] dates, double[] close, int[] volume,
                             double[] open, double[] high, double[] low){
        try
        {
            Scanner fileIn = null;
            String headerLine;
            String temp;
            int counter = 0;

            fileIn = new Scanner(new FileInputStream(FILE_PATH)).useDelimiter("[,|\\n]+");

            // Taking the first line out of the buffer for the reader
            headerLine = fileIn.nextLine();

            while (fileIn.hasNext()){

                dates[counter] = fileIn.next();
                close[counter] = fileIn.nextDouble();
                volume[counter] = fileIn.nextInt();
                open[counter] = fileIn.nextDouble();
                high[counter] = fileIn.nextDouble();
                // Low value is captured in a string then converted into a double - This is not the best way to do this
                temp = fileIn.next();
                low[counter] = Double.parseDouble(temp);

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
    // printData method
    private static void printData(String[] dates, double[] close, int[] volume,
                                  double[] open, double[] high, double[] low) {
        // Printing Column headers first
        System.out.printf(COL_DATE + COL_COST_HEAD + COL_VOLUME_HEAD + COL_COST_HEAD + COL_COST_HEAD + COL_COST_HEAD,
                "Date", "Close", "Volume", "Open", "High", "Low");
        System.out.println();

        // Printing first 12 entries of data
        for (int i = 0; i < 12; i++){
            System.out.printf(COL_DATE + COL_COST + COL_VOLUME + COL_COST + COL_COST + COL_COST,
                    dates[i], close[i], volume[i], open[i], high[i], low[i]);
            System.out.println();
        }

    }
    // End of printData method
    // -----------------------------------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------------------------------
    // analyzeData method
    private static void analyzeData(String[] dates, double[] close, int[] volume,
                                    double[] open, double[] high, double[] low) {
        double highest = close[0];
        double lowest = close[0];
        double diff = 0;
        int spot = 1;
        int lowSpot;
        int highSpot;
        int bigDiff;
        String[] yearly = new String[11];
        // Sets first spot in the year end price array to the first data entry, March 5th 2018,
        // as there is no value where the year changes afterward
        yearly[0] = dates[0];
        // The '/' character is required only for the first value of year as without it, the substring function
        // will throw an error or count 2018 values twice
        String year = "/2018";

        for (int j = 0; j < close.length; j++){
            // Finding lowest close
            if (lowest > close[j]) {
                lowest = close[j];
                lowSpot = j;
            }

            // Finding highest close
            if (highest < close[j]) {
                highest = close[j];
                highSpot = j;
            }

            // Finding the biggest difference in daily high and low
            if ((high[j] - low[j]) > diff) {
                diff = high[j] - low[j];
                bigDiff = j;
            }

            // Only runs until all of the yearly closing prices are found
            if(j > 0 && spot < 12) {
                if (!(dates[j].substring(dates[j].lastIndexOf("/")).matches(year))) {
                    yearly[spot] = dates[j];
                    spot++;
                    year = dates[j].substring(dates[j].lastIndexOf("/"));
                }
            }

        }
        // End of for loop
    }
    // End of analyzeData method
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
        printData(dates, close, volume, open, high, low);
        analyzeData(dates, close, volume, open, high, low);

    }
    // End of Main
    // -----------------------------------------------------------------------------------------------------------------

}
