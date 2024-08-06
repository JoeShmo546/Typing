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
        
        int points = 0;
        
        // assign words to buffer and display them
        for (int i = 0; i < 100; i++){
            buffer[i] = getWord();
            System.out.print(buffer[i] + " ");
        }

        System.out.println("");

        // collect user input
        Scanner scanner = new Scanner(System.in); 
        String input = scanner.nextLine();
        
        String[] inputArray = input.split(" ");
        

        for (int i = 0; i < inputArray.length; i++){

            if (buffer[i].equals(inputArray[i])){
                ++points;
            } 

        }
        
        System.out.println("you got " + points + " words correct");

    }
}
