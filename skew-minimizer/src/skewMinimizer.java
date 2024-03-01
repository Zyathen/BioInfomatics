import java.io.*;
import java.util.*;
import java.text.*;
public class skewMinimizer {
    public static void main(String[] args) throws Exception {
        // Read the sequence from the input file
        String sequence = readInput();

        // Calculates the skew min
        ArrayList<Integer> indices = findMins(sequence);

       // Write the output file of the indices in the string of matches
        createOutput(indices); 
    }

    // Reads the input file for the query and the text
    public static String readInput(){
        String sequence = "";

        try {
            File inputFile = new File("input");
            Scanner fileReader = new Scanner(inputFile);

            // All other lines are part of the text
            while (fileReader.hasNextLine()){
                sequence += fileReader.nextLine();
            }

            fileReader.close(); 

        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        
        return sequence;
    }

    // Creates the output file of the indexes of the matches
    public static void createOutput(ArrayList<Integer> indices){
        try {
            File outputFile = new File("output");
            FileWriter fileWriter = new FileWriter(outputFile);

            for(int index : indices){
                fileWriter.write(Integer.toString(index) + " ");
            }
            
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Output file not created");
        }
    }

    public static ArrayList<Integer> findMins(String sequence){
        ArrayList<Integer> indices = new ArrayList<Integer>();

        char sequenceChars[] = sequence.toCharArray();

        int counter = 0;
        int minValue = 0;

        for (int i = 0; i < sequenceChars.length; i++){
            // If there is a C, minus, if there is a G add, else do nothing
            if(sequenceChars[i] == 'C'){
                counter--;
            } else if(sequenceChars[i] == 'G'){
                counter++;
            }

            // If the skew is less than curr min value, replace the min with the counter
            if(counter < minValue){
                minValue = counter;
                indices.removeAll(indices);
                indices.add(i + 1);
            // If the minimum value is still the counter, add to the indices
            } else if (counter == minValue){
                indices.add(i + 1);
            } 
        }

        return indices;
    }
}
