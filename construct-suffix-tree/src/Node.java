import java.util.List;
import java.io.*;
import java.util.*;
import java.text.*;
import java.lang.*;

public class Node {
    private String label;
    private ArrayList<Node> children;
    
    public Node(String label){
        this.label = label;
        this.children = new ArrayList<>();
    }

    // Getters and setters
    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void addChild(Node child){
        children.add(child);
    }

    // Find child where the first character of the label is the same as the given char
    public int firstCharMatch(char first){
        for(int i = 0; i < children.size(); i++){
            Node currChild = children.get(i);
            char currChar = (currChild.getLabel()).charAt(0);
            if(currChar == first){
                return i;
            }
        }

        return -1;
    }

    // 
    public void replaceChild(Node child, Node replacementChild){
        for(int i = 0; i < children.size(); i++){
            Node currChild = children.get(i);
            if(child == currChild){
                children.remove(currChild);
                children.add(replacementChild);
            }
        }
    }

    // Print this node with the lable and children 
    public String printNode(){
        String output = "";
        output += "Label: " + this.label;
        output += ", Children: ";
        for(Node child : children){
            output += child.label + " ";
        }
        return output;
    }
}
