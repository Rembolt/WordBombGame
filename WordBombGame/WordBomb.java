/**
 * WordBomb
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class WordBomb {
    
    public static Scanner input = new Scanner(System.in);
    public static int words = 0;
    public static boolean gameisRunning = true;
    public static BombTimer bombTimer = new BombTimer();


    public static void main(String[] args) {

        System.out.println("This is the Word Bomb Game!");
        System.out.println("A game that uses a 466k word bank.");
		System.out.println("A Game that will help you: ");
        System.out.println("type faster, increase the amount of words you know, and remember spelling.");
        System.out.println("Your objective is to type a word that contains a given letter or syllable");
        System.out.println("But you must type before the timer in the bomb reachs zero!");
        System.out.println("Are you ready? wich difficulty would you like to play on?(easy/medium/hard):");
		String difficulty = input.nextLine().toLowerCase();

        bombTimer.timerinnit();

        if (difficulty.equals("easy"))
            Initialize(15, 2);
        else if(difficulty.equals("medium"))
            Initialize(10, 3);
        else if(difficulty.equals("hard"))
            Initialize(5, 3);
        else
            System.out.println("input error");

    }


    public static void Initialize(int timer, int letters){

        while(gameisRunning){

            bombTimer.resetTimer(timer);
            System.out.println("You have "+ timer +" seconds!");
            String syllable = getSyllabel(letters);
            System.out.println("Input a word with [ "+ syllable +" ] in it.");

            boolean correctWord = false;
            while(correctWord == false){

                String answer = userInput(syllable);
                correctWord = findinwordBank(answer);

            }
            words++;
            System.out.println("You have succefully gessed!");
            System.out.println("\n\n\n\n");
        }

    }

    public static String getSyllabel(int letters){
        
        try{

            int n = (int) (Math.random()*194000 + 3); 
            String line = Files.readAllLines(Paths.get("WordBank333.txt")).get(n);
            return line.substring(0, letters).toLowerCase();

        }catch(IOException e){
            System.out.println(e);
            return "Error: please restart code!";
        }

    }

    public static String userInput(String syllable){

        boolean notAnswered = true;
        String answer = "";
        while(notAnswered){

            System.out.println("...");
		    answer = input.nextLine().toLowerCase();
            if(answer.equals(syllable)){}
            else{
                if(answer.contains(syllable))
                notAnswered = false;
            }
            

        }
        return answer;
    }

    public static boolean findinwordBank(String answer){

        try{
            Scanner scan = new Scanner(new File("WordBank333.txt"));
            while(scan.hasNext()){
                String line = scan.nextLine().toLowerCase().toString();
                if(line.contains(answer)){
                    return true;
                }
            }
            return false;
        }catch(IOException e){
            System.out.println(e);
            return false;
        }

    }

    public static void endGame(){

        gameisRunning = false;
        System.out.println("GAMME OVER! The bomb has exploded!");
        System.out.println("You have correctly gessed "+words+" words");
        System.out.println("Thank you for playing!");

    }

}