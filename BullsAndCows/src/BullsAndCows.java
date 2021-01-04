import java.util.Random;
import java.util.Scanner;

public class BullsAndCows{
    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        String guessCode;
        char[] guess;
        int bulls, cows;
        int turn = 1;

        String secretCode = generateSecretCode();

        if (!secretCode.equals("-1")) {
            char[] code = secretCode.toCharArray();
            System.out.println("Okay, let's start a game!");

            do {
                System.out.printf("Turn %d:\n", turn);
                guessCode = scanner.next();
                guess = guessCode.toCharArray();

                bulls = bullsCheck(code, guess);
                cows = cowsCheck(code, guess) - bulls;

                print(bulls, cows);
                turn++;
            } while (bulls != secretCode.length());
            System.out.println("Congratulations! You guessed the secret code.");
        }

    }

    static String generateSecretCode(){
        System.out.println("Please, enter the secret code's length:");
        int number = scanner.nextInt();
        int out;
        String temp;
        String num = "";
        if (number <= 10) {
            while (num.length() < number) {
                out = new Random().nextInt(9);
                temp = Integer.toString(out);
                if (!num.contains(temp)) {
                    num += temp;
                }
            }
        }
        else {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            num = "-1";
        }
        return num;
    }

    static int bullsCheck(char[] code, char[] guess){
        int bulls = 0;
        for (int i = 0; i < code.length; i++){
            if (code[i] == guess[i]){
                bulls++;
            }
        }
        return bulls;
    }

    static int cowsCheck(char[] code, char[] guess){
        int cows = 0;
        for(char guessLetter: guess) {
            for (char codeLetter : code) {
                if (guessLetter == codeLetter) {
                    cows++;
                }
            }
        }
        return cows;
    }

    static void print(int bulls, int cows) {
        if ((bulls > 0) && (cows > 0)) {
            System.out.printf("Grade: %d bull(s) and %d cow(s).\n", bulls, cows);
        }
        else if ((bulls == 0) && (cows > 0)) {
            System.out.printf("Grade: %d cow(s).\n", cows);
        }
        else if ((bulls > 0) && (cows == 0)) {
            System.out.printf("Grade: %d bull(s).\n", bulls);
        }
        else {
            System.out.println("None.\n");
        }
    }
}