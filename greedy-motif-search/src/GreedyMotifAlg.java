import java.io.*;
import java.util.*;
import java.text.*;

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
    public static void createOutput(Strings[] sequences){
        try {
            File outputFile = new File("output");
            FileWriter fileWriter = new FileWriter(outputFile);

            for(String sequence : sequences){
                fileWriter.write(sequence + " ");
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
        for (int i = 0; i < numStrings - 1; i++){
            bestMotifs[i] = (seqeunces.get(i)).substring(0, kmerSize - 1);
        }
        
        // Calculate the score and the profile of the bestMotifs
        double[][] bestProfile = calcProfile(bestMotifs);
        int bestScore = calcScore(bestProfile, bestMotifs);

        //Iterate through every kmer of the first DNA sequence
        String firstSequence = seqeunces.get(0);
        for(int i = 0; i < firstSequence.length() - kmerSize + 1; i++){
            String firstKmer = firstSequence.substring(i, i + kmerSize - 1);

            // Holds the most probable kmer for kmer at this index in 1st sequence
            String[] currMotifs = new String[numStrings];
            currMotifs[0] = firstKmer;
        
            //Iterate through each of the sequences with the kmer from the first sequence
            for(int j = 1; j < numStrings; j++){
                String currSequence = sequences.get(j);

                double maxProb = 0;
                String maxKmer = "";

                // Calculate the new Profile for the given motifs
                double[][] currProfile = calcProfile(currMotifs);
                
                int kmerIndex = 0;

                // Iterate through kmers of the given sequence
                for(k = 0; k <= currSequence.length - kmerSize; i++){
                    String currKmer = currSequence.substring(k, k + kmerSize - 1);
                    // For each sequence find the most probable kmer using the profile of the motifs collected
                    double currProb = calcProb(currProfile, currKmer);

                    // If the current probabilty is bigger, replace for max
                    if(currProb > maxProb){
                        maxProb = currProb;
                        maxKmer = currKmer;
                    }
                }
                currMotifs[j] = maxKmer;
            }
            
            // Create a profile for the motifs collected
            int[][] currProfile = calcProfile(currMotifs);
            int currScore = calcScore(currProfile, currMotifs);
            
            // Compare the score with the best score and replace if smaller than best
            if(currScore > bestScore){
                bestScore = currScore;
                bestMotifs = currMotifs; 
            }
        }

        return bestMotifs;
    }

    // Creates the profile for a set of motifs
    public static double[][] calcProfile(String[] motifs){
        int lenKmer = motifs[0].length();

        // Holds the frequency of each nucleotide (A,C,G,T) at each index
        int[][] frequency = new int[4][lenKmer];
        Arrays.fill(frequency, 1);

        // Holds the probability of each nucleotide (A,C,G,T) at each index
        double[][] probs = new double[4][lenKmer];

        // Iterate through each columns of all the motifs
        for(int i = 0; i < motifs.length - 1; i++){
            //Current kmer
            String motif = motifs[i];
            for(int j = 0; j < motif.length() - 1; j++){
                char nucleotide = motif.charAt(j);
                int nucleoIndex = -1;

                // Match the nucleotide with the correct index
                switch(nucleotide){
                    case 'A': nucleoIndex = 0;
                        break;
                    case 'C': nucleoIndex = 1;
                        break;
                    case 'G': nucleoIndex = 2;
                        break;
                    case 'T': nucleoIndex = 2;
                        break;
                    default: nucleoIndex = -1;
                        break;
                }

                // Adding to the current count of the frequency
                frequency[nucleoIndex][j] = frequency[nucleoIndex][j] + 1;
            }
        }

        int total = 3 + motifs.length;

        for (int rows = 0; rows < frequency.length - 1; rows++){
            for(int cols = 0; cols < frequency[rows].length - 1; cols++){
                probs[rows][cols] = (frequency[rows][cols]) / total; 
            }
        }

        return probs;
    }

    // Calculates the score of a set of motifs
    public static int calcScore(int[][] profile, Strings[] motifs){
        int score = 0;
        String probKmer = mostProbKmer();

        return score;
    }

    // Creates the most probable kmer based on the profile of a set of motifs
    public static String mostProbKmer(int[][] profile){
        String probKmer = "";
        return probKmer;
    }
   
    // Calculates the probability for a particular kmer based on the profile given
    public static double calcProb(int[][] profile, String kmer){
        double prob = 1;

        return prob;
    }
    
}
