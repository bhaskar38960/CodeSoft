/*NUMBER GAME*/

import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        final int MIN = 1;
        final int MAX = 100;
        final int MAX_ATTEMPTS = 7;

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        int round = 0;
        boolean playAgain = true;

        System.out.println("🎯 Welcome to the Number Guessing Game!");
        System.out.println("Guess the number between " + MIN + " and " + MAX);

        while (playAgain) {
            int numberToGuess = random.nextInt(MAX - MIN + 1) + MIN;
            int attemptsLeft = MAX_ATTEMPTS;
            round++;

            System.out.println("\n🔁 Starting Round " + round + "...");
            boolean guessedCorrectly = false;

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (" + attemptsLeft + " attempts left): ");
                int guess;

                // Check for valid input
                try {
                    guess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Please enter a valid integer.");
                    continue;
                }

                if (guess < numberToGuess) {
                    System.out.println("📉 Too low!");
                } else if (guess > numberToGuess) {
                    System.out.println("📈 Too high!");
                } else {
                    System.out.println("🎉 Correct! You guessed the number in " + (MAX_ATTEMPTS - attemptsLeft + 1) + " attempts.");
                    guessedCorrectly = true;
                    score++;
                    break;
                }

                attemptsLeft--;
            }

            if (!guessedCorrectly) {
                System.out.println("❌ Out of attempts! The correct number was: " + numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();

            if (!response.equals("yes")) {
                playAgain = false;
                System.out.println("\n🏁 Game Over! You played " + round + " rounds and guessed correctly " + score + " times.");
            }
        }

        scanner.close();
    }
}
