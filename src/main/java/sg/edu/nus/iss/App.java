package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
       // First argument passed in is the directory to create
        String dirPath = args[0];

        // Use File class to check if directory exists
        // if directory doesn't exitst, create the directory using variable dirPath
        File newDirectory = new File(dirPath);
        if (newDirectory.exists()) {
            System.out.println(" Directory" + newDirectory + " already exists!");
        
        } else {
            newDirectory.mkdir();
        }
       
       
        System.out.println( "Welcome to your Shopping Cart!");

        // List collection to store cart items of login user
        ArrayList<String> cart = new ArrayList<>();


        // use Console class to read from keyboard
        Console c = System.console();
        String input = "";

        // used to keep track of current login user
        // this is also used as the filename for cart items
        String loginUser = "";

        // Exit while loop if input equals "quit"
        while (!input.equals("quit")) {
            input = c.readLine("What do you want to do?");

            if (input.startsWith("login")) {
                Scanner scan = new Scanner(input.substring(6));

                while (scan.hasNext()) {
                    loginUser = scan.next();

                }

                // check if the file <loginuser> exists
                // if not exist, create a new file that you can write to the file
                // else (maybe) override
                File loginFile = new File(dirPath + File.separator + loginUser);

                if (loginFile.exists()) {
                    System.out.println("File " + loginUser + " already exists!");
                } else {
                    loginFile.createNewFile();
                }
            }

            if (input.equals("users")) {
                File directoryPath = new File(dirPath);

                String [] directoryListing = directoryPath.list();
                System.out.println("List of files and directories in the specific folder: " + dirPath);
                for (String dirList : directoryListing) {
                    System.out.println(dirList);
                }

            }

            if(input.startsWith("add")) {
            input = input.replace(",", " ");

            // 1. use FileWriter & PrintWriter to write a loginuser file
            FileWriter fw = new FileWriter(dirPath + File.separator + loginUser, false);
            PrintWriter pw = new PrintWriter(fw);

            String currentScanString = "";
            Scanner inputScanner = new Scanner (input.substring(4));
            while (inputScanner.hasNext()) {
                currentScanString = inputScanner.next();
                cart.add(currentScanString);

                // 2. write to file using printwriter
                pw.write("\n" + currentScanString);
            }

            // 3. flush and close the filewriter and printwriter objects
            pw.flush();
            pw.close();
            fw.close();

            }
        }
    }
}
