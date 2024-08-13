import java.util.*;
import java.io.*;
import java.text.*;

/*
 *  get a random word from a file /
 *  add it to a buffer / 
 *  display it / 
 *  collect user input /
 *  reset cursor
 *  compare user input to the first word in the buffer
 *  if right add a point
 *
 */

public class main {

    public static String file = "words.txt";
    public static String[] buffer = new String[1000];
    // colors
    public static final String RESET = "\033[0m";
    public static final String YELLOW = "\033[0;33m";


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

    public static void printText(){
        int lines = 0;
        int width = 80; 
        int currentLine = 0;

        // assign words to buffer and display them
        for (int i = 0; i < 100; i++){
            buffer[i] = getWord();

            if ((currentLine + (buffer[i].length() + 1)) > width){
           // if (1 == 2){
                System.out.println(buffer[i]);
                currentLine = 0;
                lines++;
            } else {
                currentLine += buffer[i].length() + 1;
                System.out.print(buffer[i] + " ");
            }

        }

        System.out.print("\033[" + lines + "A\r" + YELLOW);

    }

    public static void main(String[] args){
        
        int points = 0;
        
        printText();

        // collect user input
        Scanner scanner = new Scanner(System.in); 
        String input = scanner.nextLine();
        
        String[] inputArray = input.split(" ");
        
        System.out.println(RESET);
        
        for (int i = 0; i < inputArray.length; i++){
            if ((buffer[i].equals(inputArray[i])) && (i <= buffer.length)){
                ++points;
            }

        }
        
        System.out.println("you got " + points + " words correct");

    }
}
