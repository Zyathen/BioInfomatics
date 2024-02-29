import java.io.*;
import java.util.*;
import java.text.*;

public class stringComparator {
    public static void main(String[] args) throws Exception {
        // Read the query and text from the input file
        String inputs[] = readInput();
        String query = inputs[0];
        String text = inputs[1];

        // Employes the Z Algorithm to determine matches
        ArrayList<Integer> matches = compareString(text, query);

        // Write the output file of the indices in the string of matches
        createOutput(matches);
    
    }

    // Reads the input file for the query and the text
    public static String[] readInput(){
        String inputs[] = new String[2];

        try {
            File inputFile = new File("input");
            Scanner fileReader = new Scanner(inputFile);

            // First line of input is the query
            String query = fileReader.nextLine();
            inputs[0] = query;
            String text = "";

            // All other lines are part of the text
            while (fileReader.hasNextLine()){
                text += fileReader.nextLine();
            }

            fileReader.close(); 
            // Returns the query and the text inputs
            inputs[1] = text;

        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        
        return inputs;
    }

    // Creates the output file of the indexes of the matches
    public static void createOutput(ArrayList<Integer> matches){
        try {
            File outputFile = new File("output");
            FileWriter fileWriter = new FileWriter(outputFile);

            for(int index : matches){
                fileWriter.write(Integer.toString(index) + " ");
            }
            
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Output file not created");
        }
    }

    // Searches through the text to find matches and returns the indices of these matches
    public static ArrayList<Integer> compareString(String text, String query){
        ArrayList<Integer> indices = new ArrayList<Integer>();

        char textChars[] = text.toCharArray();
        char queryChars[] = query.toCharArray();

        int textLength = text.length();
        int queryLength = query.length();

        // Goes through the text up until there cannot not be the query due to not enough chars
        for (int i = 0; i < (textLength + 1) - queryLength; i++){
            int queryCounter = 0;
            int textCounter = i;
            // Until the end of the query and while the two strands are matching
            while(queryCounter < queryLength && queryChars[queryCounter] == textChars[textCounter]){
                textCounter++;
                queryCounter++;
            }
            // If there is complete match add to the indices
            if (queryCounter == queryLength){
                indices.add(i);
            }
        }
        return indices;
    }
}
