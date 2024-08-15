import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.awt.Robot;
import java.awt.event.KeyEvent;

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
    public static final String RED = "\u001B[31m";


// gets random word from a word.txt file
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

// print text, reset cursor to beginning, injects \u200B to detect end of line :: works
    public static void printText(){
        int lines = 0;
        int width = 80; 
        int currentLine = 0;
        int[] lineLengths = new int[100];
        

        // assign words to buffer(list of words user needs to type) and display them
        for (int i = 0; i < 100; i++){

            buffer[i] = getWord();

            if ((currentLine + (buffer[i].length() + 1)) > width){
                buffer[i] += "\u200B";
                System.out.println(buffer[i]);
                lineLengths[lines] = currentLine;
                lines++;
                currentLine = 0;
            } else {
                currentLine += buffer[i].length() + 1;
                System.out.print(buffer[i] + " ");
            }

        }

        System.out.print("\033[" + lines + "A\r" + YELLOW);

    }
// need a method which collectsa char of user input and checks it against list of values
// 1. collect user input as char
// 2. press "enter"
// 3. check if char is equal to the correct character or "new line"
// 4. repeat to step 1

    public static void charCheck(int inputChar){

// 1. collect user input as char
        System.out.flush();

        try{
            inputChar = System.in.read();
        } catch (IOException e) {
            System.out.println("Stoopid IOException occured " + e.getMessage());    
        }
        
// 2. press "enter"

        try{
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            System.out.print("\033[1A");

        } catch (AWTException e){
            e.printStackTrace();
        }

// 3. check if char is equal to the correct character or "new line"
        switch ((char) inputChar) { 
            case '\n': 
                System.out.println("Enter");
                break;
            case '\u200B': 
                System.out.println("End of line");
                break;
            case 'a': 
                System.out.println("a");
                break;
        }

    }

// main method
    public static void main(String[] args){
        
        int points = 0;
        int charsTyped = 0;
        Scanner scanner = new Scanner(System.in); 
        int inputChar = 0;

        printText();

        //for (int i = 0; i < 100; i++){
        //    inputChar = 0;
        //    charCheck(inputChar);
        //}
        try{
            int test = System.in.read();
            System.out.println(test);
        }catch(IOException e){
            System.out.println(e);
        }

       // Scanner scanner = new Scanner(System.in); 
       // 
       // System.out.flush();

       // // check user if usere input is correct on a per charectar basis
       // for (String word : buffer){
       //     for (int i = 0; i < word.length(); i++){

       //         // collect a character of user input
       //         int int_character = System.in.read();
       //         try {
       //             while ((character = System.in.read()) != -1 && (character = System.in.read()) != KeyEvent.VK_ENTER){

       //             if (int_character == -1) {
       //                 break;
       //             }
       //             char char_character = (char) int_character;

       //             if (character == '\n'){
       //                 System.exit(0);
       //             }

       //                 try{
       //                     Robot robot = new Robot();
       //                     robot.keyPress(KeyEvent.VK_ENTER);
       //                     robot.keyRelease(KeyEvent.VK_ENTER);
       //                     System.out.print("\033[1A");
       //                     System.out.print("yes");

       //                 } catch (AWTException e){
       //                     e.printStackTrace();
       //                 }
       //                 System.out.print((char) character);

       //                 if (character == '\u200B'){ 
       //                     System.out.println("\r"); 
       //                 } /*else if (character == 10 || character == 13){
       //                     System.out.println("quiting ....");
       //                     System.exit(0);
       //                 }*/else if (character == word.charAt(i)){
       //                     System.out.print(YELLOW);
       //                 } else {
       //                     System.out.print("\b" + RED + (char) character);
       //                 }
       //                 System.out.print(YELLOW);
       //             }
       //         } catch (IOException e) {
       //             System.out.println("Stoopid IOException occured " + e.getMessage());    
       //         }

       //     }
       // }

        System.out.print(RESET);

        
        System.out.println("you got " + points + " words correct");

    }
}
