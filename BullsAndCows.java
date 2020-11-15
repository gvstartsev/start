import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BullsAndCows {
    private static void showResult(int bulls, int cows, int numbers) {
        if (bulls == numbers) {
            System.out.println("Grade: " + numbers + " bulls\n" +
                    "Congratulations! You guessed the secret code.");
        } else if (bulls == 0 && cows == 0) {
            System.out.println("Grade: None.");
        } else if (bulls == 0) {
            System.out.println("Grade: " + cows + " cow(s).");
        } else if (cows == 0) {
            System.out.println("Grade: " + bulls + " bull(s).");
        } else
            System.out.println("Grade: " + bulls + " bull(s)" + " and " + cows + " cow(s).");
    }

    private static int[] bullsAndCows(char[] inputArray, char[] topSecret) {

        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == topSecret[i]) {
                bulls++;
            }
        }
        Arrays.sort(topSecret);
        for (int i = 0; i < topSecret.length; i++) {
            if (Arrays.binarySearch(topSecret, inputArray[i]) > 0) {
                cows++;
            }
        }
        int[] bc = new int[2];
        bc[0] = bulls;
        bc[1] = cows;
        return bc;
    }

    private static char[] generateCode(int lengthSecret, int quantityOfSymbols) {
        char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        ArrayList<Character> symbols = new ArrayList<>();
        for (int i = 0; i < quantityOfSymbols; i++) {
            symbols.add(chars[i]);
        }

        Collections.shuffle(symbols);
        char[] secret = new char[lengthSecret];

        for (int i = 0; i < lengthSecret; i++) {
            secret[i] = symbols.get(i);
        }

        return secret;
    }

    private static String charsConsists(int quantityOfLetters) {
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        return String.valueOf(chars[0]) +
                '-' +
                chars[quantityOfLetters - 1];
    }

    private static String starsForCount(int count) {
        String stars = "************************************";
        return stars.substring(0, count);
    }

    private static int[] input() {
        int[] input = new int[2];
        System.out.println("Input the length of the secret code:");
        Scanner sc = new Scanner(System.in);
        String inputCode = sc.next();
        int length = 0;
        try {
            length = Integer.parseInt(inputCode);
        } catch (Exception e) {
            System.out.println("Error: \"" + inputCode + "\" isn't a valid number.");
        }
        System.out.println("Input the number of possible symbols in the code:");
        String inputSymbols = sc.next();
        int quantityOfSymbols = 0;
        try {
            quantityOfSymbols = Integer.parseInt(inputSymbols);
        } catch (Exception e) {
            System.out.println("Error: \"" + inputSymbols + "\" isn't a valid number.");
        }
        input[0] = length;
        input[1] = quantityOfSymbols;
        return input;
    }

    public static void main(String[] args) {
        int[] inputInt = input();
        int length = inputInt[0];
        int quantityOfSymbols = inputInt[1];
        char[] secret;
        if (length > quantityOfSymbols || length == 0) {
            System.out.println("Error: it's not possible to generate a code with a length of " + length + " with " + quantityOfSymbols + " unique symbols.");
        } else {
            if (length > 36 || quantityOfSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            } else {
                secret = generateCode(length, quantityOfSymbols);
                if (quantityOfSymbols > 10) {
                    System.out.println("The secret is prepared: " + starsForCount(length) + " (0-9, " + charsConsists(quantityOfSymbols - 10) + ")");
                } else {
                    System.out.println("The secret is prepared: " + starsForCount(length) + " (0-9)");
                }


                System.out.println("Okay, let's start a game!");
                int[] bc;
                int count = 0;
                do {
                    count++;
                    System.out.println("Turn " + count + ":");
                    Scanner sc = new Scanner(System.in);
                    String input = sc.next();
                    bc = bullsAndCows(input.toCharArray(), secret);
                    showResult(bc[0], bc[1], length);
                }
                while (bc[0] != length);
            }
        }
    }
}
