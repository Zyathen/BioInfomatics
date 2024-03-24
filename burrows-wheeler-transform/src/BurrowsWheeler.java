import java.io.*;
import java.util.*;
import java.text.*;

public class BurrowsWheeler {
    public static void main(String[] args) throws Exception {
        // Read the dnaSequence from the input file
        String input = readInput();

        // Find the string that has been scrambled
        String outputNaive = burrowsWheelerTransformNaive(input);

        //String output = burrowsWheelerTransform(input);

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

    public static String burrowsWheelerTransformNaive(String input){
        // Input is the last column of the BWT
    
        // Create BWT matrix
        String[] bwt = new String[input.length()];
        Arrays.fill(bwt, "");

        // Sort the array by the first letter, with $ being the highest
        for(int i = 0; i < input.length(); i++){
            // Preappend the input to each string in the bwt array
            for(int j = 0; j < bwt.length; j++){
                bwt[j] = input.charAt(j) + bwt[j];
            }

            // Sort the bwt array
            Arrays.sort(bwt);
        }

        int i = 0;
        while(bwt[i].endsWith("$") != true){
            i++;
        }
    
        return bwt[i];
    } 

    // public static String burrowsWheelerTransform(String input){
    //     return output;
    // }
}
