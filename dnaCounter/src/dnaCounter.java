import java.io.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.*;

public class dnaCounter {

    public static void main(String[] args) {
        HashMap<Character, Integer> counter = new HashMap<>();

        //Start the counter at 0 for each of the following bases
        counter.put('A', 0);
        counter.put('C', 0);
        counter.put('G', 0);
        counter.put('T', 0);

        //Read the input file for the sequence
        try {
            File inputFile = new File ("input");
            Scanner fileReader = new Scanner(inputFile);

            // Scanning the file for each line of the sequence
            while (fileReader.hasNextLine()){
                String sequence = fileReader.nextLine();

                //Throws an error if it includes anything but nucelotides
                Boolean matchFound = Pattern.matches("/^[ATGC]+$/", sequence);
                if (matchFound == false){
                    System.out.println("Sequence includes other characters");
                }

                // Iterating through the sequence string and counting instances in counter
                CharacterIterator it = new StringCharacterIterator(sequence);
                while (it.current() != CharacterIterator.DONE){
                    int count = counter.get(it.current());
                    counter.put(it.current(), count + 1);

                    it.next();
                }
            }

            // Close the reader once done
            fileReader.close();    
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        // Creating and writing to the output file
        try {
            File outputFile = new File("output");
            FileWriter fileWriter = new FileWriter(outputFile);

            //Writing to the file
            for (Integer count : counter.values()){
                fileWriter.write(count.toString() + " ");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Output file not created");
        }
    }
}
