import java.io.*;
import java.util.*;
import java.text.*;

public class KMPAlgorithm {
    public static void main(String[] args) throws Exception {
        // Read the query and text from the input file
        String input = readInput();
        System.out.println(input);

        int spVals[] = generateSpVals(input);

        // Write the output file of the indices in the string of matches
        createOutput(spVals);
    }

    // Reads the input file for the query and the text
    public static String readInput(){
        String text = "";
        try {
            File inputFile = new File("input");
            Scanner fileReader = new Scanner(inputFile);

            // All other lines are part of the text
            while (fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                if(line.charAt(0) != '>'){
                    text += line;
                }
            }

            fileReader.close(); 

        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        
        return text;
    }

    // Creates the output file of the indexes of the matches
    public static void createOutput(int[] spVals){
        try {
            File outputFile = new File("output");
            FileWriter fileWriter = new FileWriter(outputFile);

            for(int index : spVals){
                fileWriter.write(Integer.toString(index) + " ");
            }
            
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Output file not created");
        }
    }

    // Searches through the text to find matches and returns the indices of these matches
    public static int[] generateSpVals(String text){
        int spVals[] = new int[text.length()];
        spVals[0] = 0;

        while(i < )



        return spVals;
    }
}
