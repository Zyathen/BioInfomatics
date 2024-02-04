import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.*;

public class dnaCounter {

    public static void main(String[] args) {
        HashMap<Character, Integer> counter = new HashMap<>();
        //Start the counter at 0 for each of the following bases
        counter.put('A', 0);
        counter.put('T', 0);
        counter.put('C', 0);
        counter.put('G', 0);

        //Read the input file for the sequence
        try {
            File myObj = new File ("input.*");
            Scanner fileReader = new Scanner(myObj);

            while (fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                Pattern pattern = Pattern.compile("/^[ATGC]$/");
                if "/^[ATGC]+$/"
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    
    }

    public static array[] countBases(Hashmap <>){

    }
}
