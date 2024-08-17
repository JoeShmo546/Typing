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
    public static ArrayList<ArrayList<String>>  buffer = new ArrayList<>();

    // colors
    public static final String RESET = "\033[0m";
    public static final String YELLOW = "\033[0;33m";
    public static final String RED = "\u001B[31m";
    public static int points = 0;


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
//
// simpy: adds a word to each element of buffer 2d array
//      End of line is determined by length of 
//
//
    public static void printText(){
        int lines = 0;
        int width = 80; 
        int currentLineLength = 0;
        String word;

        // assign words to buffer(list of words user needs to type) and display them
        for (int i = 0; i < 100; i++){
            
            word = getWord();

            //buffer.add(line);
            // buffer[lines][i] = getWord();

            if ((currentLineLength + (word.length() + 1)) > width){ // determines where to start a new "dimension" in 2d array
                ArrayList<String> line = new ArrayList<>();
                System.out.println(word + " ");
                lines++;
                currentLineLength = 0;
                buffer.add(line);
            } else {
                buffer.get(lines).add(word);
                System.out.print(word + " ");
                currentLineLength += word.length();
            }

        }

        System.out.print("\033[" + lines + "A\r" + YELLOW);

        //for (int i = 0; i < lines; i++){
        //    for (int j = 0; j < buffer.get(i).size(); j++){
        //        System.out.println(buffer.get(i).get(j));
        //    }
        //}

    }
// need a method which collectsa char of user input and checks it against list of values
// the point of checking every character is to search for the new line char
// 1. collect user input as String
// 2. press "enter"
// 3. check if char is equal to the correct character or "new line"
// 4. repeat to step 1

// 1. "buffer" stored in a 2d array, lines and words
// 2. check every line
//

    public static void charCheck(String inputInt){

// 1. collect user input as char
        //System.out.flush();

        Scanner scanner = new Scanner(System.in);
        inputInt = scanner.next();
        while(inputInt.length()!=1){
            inputInt = scanner.next();
        }
        //try{
        //    inputInt = System.in.read();
        //} catch (IOException e) {
        //    System.out.println("Stoopid IOException occured " + e.getMessage());    
        //}
        System.out.println(inputInt + inputInt.length()); 
// 2. press "enter"

        try{
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            System.out.print("\033[1A\b");

        } catch (AWTException e){
            e.printStackTrace();
        }

        char inputChar = inputInt.charAt(0);

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
                points++;
                break;
        }

    }

// main method
    public static void main(String[] args){
        
        int charsTyped = 0;
        Scanner scanner = new Scanner(System.in); 
        int inputInt = 0;

        printText();

        for (int i = 0; i < 100; i++){

            try{
                int test = System.in.read();
                System.out.println((char)test);
            }catch(IOException e){
                System.out.println(e);
            }

            inputInt = 0;
            charCheck("a");
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
