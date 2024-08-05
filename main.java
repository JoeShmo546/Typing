import java.util.*;
import java.io.*;

/*
 *  get a random word from a file
 *  add it to a buffer
 *  collect user input 
 *  compare user input to the first word in the buffer
 *
 */

public class main {

    public static String file = "words.txt";

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
        
        for (int i = 0; i < 10; i++){
            System.out.print(getWord() + " ");
        }
        System.out.println();


    }
}
