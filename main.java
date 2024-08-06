import java.util.*;
import java.io.*;

/*
 *  get a random word from a file /
 *  add it to a buffer / 
 *  display it / 
 *  collect user input 
 *  reset cursor
 *  compare user input to the first word in the buffer
 *  if right add a point
 *
 */

public class main {

    public static String file = "words.txt";
    public static String[] buffer = new String[100];
    public static int points = 0;

    public static String getWord(){

        Random random = new Random();
        int lineNumber = random.nextInt(5459);

        try(BufferedReader br = new BufferedReader(new FileReader (file))) {

            String line;
            int currentLine = 1;

            while((line = br.readLine()) != null){
                if(currentLine == lineNumber) {
                    return line;
                }
                currentLine++;
            }
        } catch (IOException e){
            System.err.println("An error ocurred while reading the file.");
            //e.printStackTrack();
        }
        return "nothing";

    }
    public static void main(String[] args){
        
        // assign words to buffer and display them
        for (int i = 0; i < 100; i++){
            buffer[i] = getWord();
            System.out.print(getWord() + " ");
        }

        // collect user input
        Scanner scanner = new Scanner(System.in); 
        String input = scanner.nextLine();
        
        String[] inputArray = input.split(" ");
        
        // compare user input to correct input
        for (String word : buffer){
            for (String typedWord : inputArray){

                if (typedWord.equals(word)){
                    points++;
                }
            }
        }
        
        System.out.println("you got " + points + " words correct");

    }
}
