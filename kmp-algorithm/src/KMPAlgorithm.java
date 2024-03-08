import java.io.*;
import java.util.*;
import java.text.*;

public class KMPAlgorithm {
    public static void main(String[] args) throws Exception {
        // Read the query and text from the input file
        String input = readInput();

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
        int TEXT_LEN = text.length();
        int spVals[] = new int[TEXT_LEN];

        // The first index of the spVals is blank, nothing to compare it to
        spVals[0] = 0;
        // Keeps track of the beginning
        int pat = 0;
        //Goes through the whole text comparing to the beginning of text
        int i = 1;

        // Loops through the text
        while(i < TEXT_LEN){
            // If there is a match add to the spVals
            if(text.charAt(i) == text.charAt(pat)){
                pat++;
                spVals[i] = pat;
                i++;
            } else {
                // No match copy the beginning/ subtract down
                if(pat != 0){
                    pat = spVals[pat - 1];
                } else {
                    spVals[i] = 0;
                    i++;
                }
            }
        }

        return spVals;
    }
}
