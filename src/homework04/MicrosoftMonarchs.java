package homework04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MicrosoftMonarchs {

    private static final String filePath = "MicrosoftStockData.txt";

    public static void main(String[] args){

        Scanner fileIn = null;
        String line;

        System.out.println("Welcome to Microsoft Monarchs!");
        System.out.println("------------------------------");

        try
        {
            fileIn = new Scanner(new FileInputStream(filePath));
            while (fileIn.hasNextLine()){
                line = fileIn.nextLine();
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unable to find or open the file.");
        }

    }

}
