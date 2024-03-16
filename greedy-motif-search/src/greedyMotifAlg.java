import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class greedyMotifAlg {
    public static void main(String[] args) throws Exception {
        // Read the input file
        ArrayList<String> inputs = readInput();
        // First value is the k-mer size
        int kmerSize = Integer.parseInt(inputs.get(0));
        inputs.remove(0);

        // Second value is the number of strings to look for the motif
        int numStrings = Integer.parseInt(inputs.get(0));
        inputs.remove(0);

        // The t DNA strings to look for the motif in
        ArrayList<String> dnaSequences = inputs;

        // Employes the greedy motif algorithm and returns the sequences
        String[] output = greedyMotif(kmerSize, numStrings, dnaSequences);

        // Write the output file of the sequences
        createOutput(output);
    }


    // Reads the input file for the inputs (kmer size, # of strings, dna sequences)
    public static ArrayList<String> readInput(){
        
        ArrayList<String> inputs = new ArrayList<>();

        try {
            File inputFile = new File("input");
            Scanner fileReader = new Scanner(inputFile);

            // First line has the two ints, kmer size and number of sequences
            String kmer = Integer.toString(fileReader.nextInt());
            inputs.add(kmer);
            int numStrings = fileReader.nextInt();
            inputs.add(Integer.toString(numStrings));

            fileReader.nextLine();
            // Each line is a dna sequence
            while (fileReader.hasNextLine()){
                inputs.add(fileReader.nextLine());
            }

            fileReader.close(); 

        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        
        return inputs;
    }

     // Creates the output file of the most probable kmers
    public static void createOutput(String[] sequences){
        try {
            File outputFile = new File("output");
            FileWriter fileWriter = new FileWriter(outputFile);

            for(String sequence : sequences){
                fileWriter.write(sequence + "\n");
            }
            
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Output file not created");
        }
    }

    // Implementation of the greedy motif search algorithm
    public static String[] greedyMotif(int kmerSize, int numStrings, ArrayList<String> sequences){
        String[] bestMotifs = new String[numStrings];

        // Create the best motifs, using the first chars from every DNA sequence
        for (int i = 0; i < numStrings; i++){
            bestMotifs[i] = (sequences.get(i)).substring(0, kmerSize);
        }

        // Calculate the score and the profile of the bestMotifs
        float[][] bestProfile = calcProfile(bestMotifs);
        int bestScore = calcScore(bestProfile, bestMotifs);

        //Iterate through every kmer of the first DNA sequence
        String firstSequence = sequences.get(0);
        for(int i = 0; i < firstSequence.length() - kmerSize + 1; i++){
            String firstKmer = firstSequence.substring(i, i + kmerSize);

            // Holds the most probable kmer for kmer at this index in 1st sequence
            String[] currMotifs = new String[numStrings];
            currMotifs[0] = firstKmer;
        
            //Iterate through each of the sequences with the kmer from the first sequence
            for(int j = 1; j < numStrings; j++){
                String currSequence = sequences.get(j);

                float maxProb = 0;
                String maxKmer = "";

                // Calculate the new Profile for the given motifs
                float[][] currProfile = calcProfile(currMotifs);

                // Iterate through kmers of the given sequence
                for(int k = 0; k <= currSequence.length() - kmerSize; k++){
                    String currKmer = currSequence.substring(k, k + kmerSize);
                    // For each sequence find the most probable kmer using the profile of the motifs collected
                    float currProb = calcProb(currProfile, currKmer);

                    // If the current probabilty is bigger, replace for max
                    if(currProb > maxProb){
                        maxProb = currProb;
                        maxKmer = currKmer;
                    }
                }
                currMotifs[j] = maxKmer;
                
            }

            // Create a profile for the motifs collected
            float[][] currProfile = calcProfile(currMotifs);
            int currScore = calcScore(currProfile, currMotifs);
            
            // Compare the score with the best score and replace if smaller than best
            if(currScore < bestScore){
                bestScore = currScore;
                bestMotifs = currMotifs; 
            }
        }

        return bestMotifs;
    }

    // Creates the profile for a set of motifs
    public static float[][] calcProfile(String[] motifs){
        int lenKmer = motifs[0].length();

        // Holds the frequency of each nucleotide (A,C,G,T) at each index
        float[][] frequency = new float[4][lenKmer];

        // Holds the probability of each nucleotide (A,C,G,T) at each index
        float[][] probs = new float[4][lenKmer];

        // Iterate through each character of all the motifs
        for(int i = 0; i < motifs.length; i++){
            //Current kmer
            String motif = motifs[i];
            if (motif == null){
                break;
            }

            for(int j = 0; j < motif.length(); j++){
                char nucleotide = motif.charAt(j);

                // Match the nucleotide with the correct index
                int nucleoIndex = matchNucleotide(nucleotide);

                // Adding to the current count of the frequency
  
                frequency[nucleoIndex][j] = frequency[nucleoIndex][j] + 1;
            }
        }

        float total = 4 + motifs.length;

        // Calculate the percentage for each element in the array
        for (int rows = 0; rows < frequency.length; rows++){
            for(int cols = 0; cols < frequency[rows].length; cols++){
                // Fill with ones for Laplacian
                probs[rows][cols] = (frequency[rows][cols] + 1) / total; 
            }
        }
        return probs;
    }

    // Calculates the score of a set of motifs
    public static int calcScore(float[][] profile, String[] motifs){
        int score = 0;
        String probKmer = mostProbKmer(profile);
        // Calculates all the mismatches of the given motifs
        for(int row = 0; row < motifs.length; row++){
            String currMotif = motifs[row];
            for(int col = 0; col < currMotif.length(); col++){
                if (currMotif.charAt(col) != probKmer.charAt(col)){
                    score++;
                }
            }
        }

        return score;
    }

    // Creates the most probable kmer based on the profile of a set of motifs
    public static String mostProbKmer(float[][] profile){
        String probKmer = "";
        // Iterate through each column of the profile array
        for(int cols = 0; cols < profile[0].length; cols++){
            float maxProb = 0;
            int maxCharIndex = -1;

            // Iterate through each row, representing a nucleotide, find the one with the highest prob
            for(int row = 0; row < profile.length; row++){
                float currProb = profile[row][cols];
                if(maxProb < currProb){
                    maxProb = currProb;
                    maxCharIndex = row;
                }
            }
            char nucleotide = matchNucleotideIndex(maxCharIndex);
            probKmer = probKmer + nucleotide;
        }

        return probKmer;
    }
   
    // Calculates the probability for a particular kmer based on the profile given
    public static float calcProb(float[][] profile, String kmer){
        float prob = 1;

        // Iterating through every character of the nucleotide
        for(int i = 0; i < kmer.length(); i++){
            char nucleotide = kmer.charAt(i);
            
            int row = matchNucleotide(nucleotide);
            // Multiply the probability for each character of the kmer
            prob = profile[row][i] * prob;
            
        }
        return prob;
    }

    public static int matchNucleotide(char nucleotide){
        int index = -1;
        // Match the nucleotide with the correct index
        switch(nucleotide){
            case 'A': index = 0;
                break;
            case 'C': index = 1;
                break;
            case 'G': index = 2;
                 break;
            case 'T': index = 3;
                break;
            default: index = -1;
                break;
        }
        return index;
    }

    public static char matchNucleotideIndex(int nucelotideIndex){
        char nucleotide = 'H';
        // Match the nucleotide with the correct index
        switch(nucelotideIndex){
            case 0: nucleotide = 'A';
                break;
            case 1: nucleotide = 'C';
                break;
            case 2: nucleotide = 'G';
                 break;
            case 3: nucleotide = 'T';
                break;
            default: nucleotide = 'H';
                break;
        }
        return nucleotide;
    }
}
