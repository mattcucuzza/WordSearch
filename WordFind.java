// Word Find
//
// Author Matthew Cucuzza
// 10/7/14
//
// Program reads a file containing scrambled letters and uses the words provided in
// file to solve a Word Search
//

// Imports
import java.util.Scanner;
import java.io.*;

public class WordFind {
    
    // Variables
    private int rows;
    private int columns;
    private int wordAmount;
    private String input;
    private boolean found = false;
    char [][] letters;
    String [] answers;
    
    // Reads letters to solve and solved words from a file
    public void readLetters() {
        File inFile = new File("WordFind.txt");
        
        try {
            Scanner file = new Scanner (inFile);
            
            // Assigns rows & columns
            rows = file.nextInt();
            columns = file.nextInt();
            
            // Size of scrambled letters array based on first two ints
            letters = new char[rows][columns];
            
            input = file.nextLine();
            
            // Reads scrambled letters into an array
            for (int i = 0; i < rows; i++) {
                input = file.nextLine();
                for(int j = 0; j < columns; j++) {
                    letters[i][j] = input.charAt(j);
                }
            }
            // Defines amount of words needed to be solved
            wordAmount = file.nextInt();
            
            input = file.nextLine();
            
            // Size of unscrambled words array
            answers = new String[wordAmount];
            
            // Reads unscrambled words into an array
                for (int i = 0; i < wordAmount; i++) {
                    answers[i] = file.next();
                }
            file.close();
        }
        
        catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    
    // Method to print the array of scrambled letters
    public void printLetters() {
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++) {
                System.out.print(letters[i][j]);
            }
            System.out.println();
        }
    }
    
    // Method to print the array of unscrambled words
    public void printWords() {
        for (int i = 0; i < wordAmount; i++) {
            System.out.print(answers[i]);
            System.out.println();
        }
    }
    
    // Formula to wrap around x-axis
    public int addX (int x, int dx) {
        x = x + dx;
        if (x < 0) x = rows-1;
        if (x >= rows) x = 0;
        return x;
    }
    
    // Formula to wrap around y-axis
    public int addY (int y, int dy) {
        y = y + dy;
        if (y < 0) y = columns - 1;
        if (y >= columns) y = 0;
        return y;
    }
    
    // General formula to compare both arrays and try various directions
    public boolean tryDirection (int x, int y, int k, String word, int dx, int dy) {
        if (k == word.length()) return true;
        if (letters[x][y] == word.charAt(k)) {
            return tryDirection(addX(x,dx), addY(y,dy), k+1, word, dx, dy);
        }
        else return false;
    }
    
    // Method to solve the Word Search Program.
    // Compares the unscrambled char array to scrambled string array and checks
    // the location of the first char if the word is found
    public void search () {
        // Searches the entire program for the answers
        // For loop uses the amount of words needed to be solved as boundary
        for (int m = 0; m < wordAmount; m++) {
            String word = answers[m];

            found = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    // If the char's are the same at both locations enter if state.
                    if (letters[i][j] == word.charAt(0)) {
                        // Directions to search are located here
                        
                        // Search down
                        if (tryDirection(i, j, 0, word, 1, 0) == true) {
                            System.out.println(i + " " + j);
                            found = true;
                        }
                        
                        // Search up
                        else if (tryDirection(i, j, 0, word, -1, 0) == true) {
                            System.out.println(i + " " + j);
                            found = true;
                        }
                        
                        // Search right
                        else if (tryDirection(i, j, 0, word, 0, 1) == true) {
                            System.out.println(i + " " + j);
                            found = true;
                        }
                        
                        // Search left
                        else if (tryDirection(i, j, 0, word, 0, -1) == true) {
                            System.out.println(i + " " + j);
                            found = true;
                        }
                        
                        // Search down to the right
                        else if (tryDirection(i, j, 0, word, 1, 1) == true) {
                            System.out.println(i + " " + j);
                            found = true;
                        }
                        
                        // Search down to the left
                        else if (tryDirection(i, j, 0, word, 1, -1) == true) {
                            System.out.println(i + " " + j);
                            found = true;
                        }
                        
                        // Search up to the right
                        else if (tryDirection(i, j, 0, word, -1, 1) == true) {
                            System.out.println(i + " " + j);
                            found = true;
                        }
                        
                        // Search up to the left
                        else if (tryDirection(i, j, 0, word, -1, -1) == true) {
                            System.out.println(i + " " + j);
                            found = true;
                        }
                    }
                }
            }
            // If the word wasn't found in recursive loop, print NOT FOUND
            if (found == false) {
                System.out.println("WORD NOT FOUND");
            }
        }
        
    }
    
    /****************************************************************
     *********************** Main class to run Program **************
     ****************************************************************/
    public static void main (String [] args) {
        WordFind search = new WordFind();
        System.out.println(" ");
        search.readLetters();
        System.out.println("---------------");
        System.out.println("Scrambled Words");
        System.out.println("---------------");
        // Prints scrambled letters
        search.printLetters();
        System.out.println(" ");
        System.out.println("---------------");
        System.out.println("Unscrambled Words");
        System.out.println("---------------");
        // Prints unscrambled words to search for
        search.printWords();
        System.out.println(" ");
        System.out.println("---------------");
        System.out.println("Location of Words");
        System.out.println("---------------");
        // Solves the word search
        search.search();
        System.out.println(" ");
        System.out.println("---------------");
        System.out.println("End Program");
        System.out.println("---------------");
        System.out.println(" ");
    }
}
