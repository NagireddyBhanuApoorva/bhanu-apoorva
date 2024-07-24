import java.util.Scanner;
import java.util.Random;
public class NumberGuessingGame
{
public static void main(String[] args) 
{
Scanner scanner = new Scanner(System.in);
Random random = new Random();
boolean playAgain = true;
int totalAttempts = 0;
int totalRounds = 0;
System.out.println("Welcome to the Number Guessing Game!");
while (playAgain) 
{
totalRounds++;
int secretNumber = random.nextInt(100) + 1; // Random number between 1 and 100
int attempts = 0;
boolean guessedCorrectly = false;
System.out.println("\nI'm thinking of a number between 1 and 100. You have 10 attempts.");
while (!guessedCorrectly && attempts < 10) 
{
System.out.print("Enter your guess: ");
int guess = scanner.nextInt();
attempts++;
if (guess < secretNumber) {
System.out.println("Too low! Try guessing higher.");
} 
else if (guess > secretNumber) 
{
System.out.println("Too high! Try guessing lower.");
} 
else 
{
System.out.println("Congratulations! You guessed it right in " + attempts + " attempts.");
guessedCorrectly = true;
}
}
totalAttempts += attempts;
if (!guessedCorrectly) 
{
System.out.println("Sorry, you did not guess the number. It was: " + secretNumber);
}
System.out.print("\nDo you want to play again? (yes/no): ");
String playAgainResponse = scanner.next().toLowerCase();
if (!playAgainResponse.equals("yes")) 
{
playAgain = false;
}
}
scanner.close();
System.out.println("\nGame Status:");
System.out.println("Total rounds played: " + totalRounds);
System.out.println("Total attempts made: " + totalAttempts);
System.out.println("Average attempts per round: " + (double) totalAttempts / totalRounds);        System.out.println("\nThanks for playing!");
}
}
