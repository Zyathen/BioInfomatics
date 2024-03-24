import java.io.*;
import java.util.*;

import org.w3c.dom.Node;

import java.text.*;
import java.lang.*;

public class BurrowsWheeler {
    public static void main(String[] args) throws Exception {
        // Read the dnaSequence from the input file
        String input = readInput();

        // Find the string that has been scrambled
        String output = burrowsWheelerTransform(input);

        // Create the output file
        createOutput(output);
    }

    // Reads the Burrows Wheeler Transform
    public static String readInput(){
        String input = "";
        try {
            File inputFile = new File("input");
            Scanner fileReader = new Scanner(inputFile);

            // All other lines are part of the text
            while (fileReader.hasNextLine()){
                input += fileReader.nextLine();
            }

            fileReader.close(); 

        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        return input;
    }

    // Creates the output file of the 
    public static void createOutput(String edges){
        try {
            File outputFile = new File("output");
            FileWriter fileWriter = new FileWriter(outputFile);

            fileWriter.write(edges);
            
            
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Output file not created");
        }
    }

    public static String burrowsWheelerTransform(String input){
        String output = "";

        return output;
    } 
}
