import java.io.*;
import java.util.*;
import java.text.*;

public class ZAlgorithm {
    public static void main(String[] args) throws Exception {
        // Read the query and text from the input file
        String inputs[] = readInput();
        String query = inputs[0];
        String text = inputs[1];

        String concatString = (query + "$" + text);
        
        // Calculate runtime (starting timer when getting matches)
        long startTime = System.nanoTime();

        // Employes the Z Algorithm to determine matches
        ArrayList<Integer> matches = getZValues(concatString, query.length());

        // Calculating total runtime, printing out the total
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);

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
    public static ArrayList<Integer> getZValues(String conStr, int queryLen){
        ArrayList<Integer> indices = new ArrayList<Integer>();

        char concatString[] = conStr.toCharArray();

        int CONCAT_LENGTH = concatString.length;

        Integer zArray[] = new Integer[CONCAT_LENGTH];
        // The first index of the zArray is blank, nothing to compare it to
        zArray[0] = 0;

        for(int i = 1; i < CONCAT_LENGTH; ++i){
            int R = 0, L = 0;
            // When the index is R index is greater than the curr index there has been matches
            if(i > R){
                //If there are no matches, then we start with the naive way
                L = R = i;

                // While still inside the concatted string and the query is matching
                while(R < CONCAT_LENGTH && concatString[R-L] == concatString[R]){
                    R++;
                }

                // Recording the number of matches
                zArray[i] = R - L;
                R--;

            // There have been matches
            } else {
                int k = i - L;
                /* Case 1: If the number of matches at z[k] are less than the remaining interval
                then we will fill that interval with the values we have already determined in 
                from the prefix*/ 
                if(zArray[k] < (R - i + 1)){
                    zArray[i] = zArray[k];

                // Case 2: Start matching past the interval, zArray[k] > (R-i+1)
                } else {
                    L = i;
                    //Case 3: When t
                    while(R < CONCAT_LENGTH && concatString[R-L] == concatString[R]){
                        R++;
                    }
                    // Recording the number of matches
                    zArray[i] = R - L;
                    R--;
                }
            }
        }

        // Adding the index if the match is the correct length
        for (int i = 0; i < zArray.length; i++){
            if(zArray[i] == queryLen){
                indices.add(i - queryLen - 1);
            }        
        }

        return indices;
    }

}
