import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
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

    static String generateSecretCode() {
        int out;
        String validSymbols = "0123456789abcdefghijklmnopqrstuvwxyz";
        String temp;
        String num = "";

        try {
            System.out.println("Input the length of the secret code:");
            int number = scanner.nextInt();
            System.out.println("Input the number of possible symbols in the code:");
            int symbols = scanner.nextInt();

            if (!(symbols < number) && !(number == 0)){
                validSymbols = validSymbols.substring(0, symbols);
                while (num.length() < number) {
                    out = new Random().nextInt(symbols);
                    temp = validSymbols.substring(out, out + 1);
                    if (!num.contains(temp)) {
                        num += temp;
                    }
                }
                System.out.print("The secret is prepared: ");
                for (int i = 0; i < number; i++) {
                    System.out.print("*");
                }
                if (symbols > 10){
                    System.out.printf(" (0-9, a-%c)\n", validSymbols.charAt(validSymbols.length()-1));
                }
                else if (symbols <= 10) {
                    System.out.printf(" (0-%c)\n", validSymbols.charAt(validSymbols.length() - 1));
                }
            } else {
                System.out.printf("Error: it's not possible to generate a code of length %d with %d unique symbols\n", number, symbols);
                num = "-1";
            }
        } catch (InputMismatchException e){
            System.out.println("Error: Not a valid number");
            num = "-1";
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            num = "-1";
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getClass().getName());
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
