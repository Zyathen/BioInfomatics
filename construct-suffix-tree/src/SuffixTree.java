import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class SuffixTree {
    public static void main(String[] args) throws Exception {
        // Read the dnaSequence from the input file
        String input = readInput();

        // Create suffix tree
        Node root = createSuffixTree(input);

        // Traverse the suffix tree for the edges
        String output = traverseSuffixTreeRec(root);

        // Create the output file
        createOutput(output);
    }

    // Reads the dnaSequence that ends in $ from the input file
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

    // Creates the output file of the edges in the suffix tree
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

    // Creates a list of the string edges for the output
    public static String traverseSuffixTreeRec(Node node){
        // Check if the node is null or it has no children
        if(node == null || (node.getChildren()).isEmpty()){
            return "";
        }

        ArrayList<Node> children = node.getChildren();

        String output = "";
        // Iterate through each of the children, calling this method
        for(Node child: children){
            output += traverseSuffixTreeRec(child);
            output += child.getLabel() + "\n";
        }

        return output;
    }

    public static Node createSuffixTree(String input){
        // Create the root of the suffixTree
        Node root = new Node("");

        // Add the first child (the whole input string)
        root.addChild(new Node(input));
        
        int inputLen = input.length();

        // Iterate through each of the suffixes
        for(int i = 1; i < inputLen; i++){
            // Set the current node to be the root
            Node currNode = root;

            int j = i;

            while(j < inputLen){
                int childIndex = currNode.firstCharMatch(input.charAt(j));
                // If one of the children of the current Node, begins with the first character of curr suffix
                if(childIndex != -1){
                    Node matchedChild = (currNode.getChildren()).get(childIndex);
                    String edge = matchedChild.getLabel();

                    // Suffix index
                    int k = j + 1; 

                    int edgeLen = edge.length();

                    // Two conditions will stop matching, 1. Exhausted the edge 2. Mismatch
                    while(k - j < edgeLen && input.charAt(k) == edge.charAt(k - j)){
                        k++;
                    }
                    // Condition 1. Exhausted the edge
                    if(k - j == edgeLen){
                        currNode = matchedChild;
                        j = k;
                    // Condition 2. Mismatch
                    }else {
                        // Create the two edges for the new middle node
                        String existingEdge = edge.substring(k-j);
                        String newEdge = input.substring(k);

                        // Create the middle node with the two edges
                        Node middleNode = new Node(edge.substring(0, k-j));

                        // Add the new node
                        middleNode.addChild(new Node(newEdge));

                        // Add the existing node to the middle child
                        middleNode.addChild(matchedChild);
                        // Child label is curtailed to the string
                        matchedChild.setLabel(edge.substring(k-j));

                        // Middle node becomes new child of the original parent 
                        currNode.replaceChild(matchedChild, middleNode);
                    }

                // Create a new branch at the currNode (make a new edge)
                } else {
                    // Create new node with the rest of the suffix
                    Node newNode = new Node(input.substring(j));
                    currNode.addChild(newNode);
                }
            }
        }
        return root;
    }

}
