import java.io.*;
import java.util.Scanner;
import java.lang.*;

public class toolsDNARep {
    public static void main(String[] args) throws Exception {
        
        // Read the input file for the query and text to search against
        Pair<String, String> inputs = readInputFile();
        char[] query = str.toCharArray(inputs.getKey());
        char[] text = str.toCharArary(inputs.getValue());

        // Stores the index when there is a query match
        ArrayList<Integer> queryMatches = new ArrayList<String>();

        // Create the z value array

        // Search through the z array for any values that are equa

        // Creates an outfile with the indexes of the matches
        createOutputFile(queryMatches);
        return;
    }

    // Reads the input file, returns the query and text to search against
    public static Pair<String, String> readInputFile(){
         try {
            File inputFile = new File("input");
            Scanner fileReader = new Scanner(inputFile);

            // Query is the text trying to match
            String query = fileReader.nextLine();
            StringBuilder stringBuilder = new StringBuilder();

            // Create the text from all the lines of the input file
            while (fileReader.hasNextLine()){
                stringBuilder.append(fileReader.nextLine());
            }
            String text = stringBulder.toString();
            fileReader.close(); 
        } catch (FileNotException e){
            System.out.println(e);
        }

        return new Pair<String, String>(query, text);
    }

    // Creates an output file
    public static void createOutputFile(Array[] matches){
        try {
            File outputFile = new File("output");
            FileWriter fileWriter = new FileWriter(outputFile);

            for(Int index : matches){
                fileWrite.write(index.toString() + " ");
            }
            
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Output file not created");
        }
    }

    // Searches through the text and finds matches
    public static Array[] zValues(String text){
        return zvalues;
    }
}
