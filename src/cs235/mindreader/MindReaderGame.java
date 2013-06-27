

package cs235.mindreader;

import java.util.Scanner;


public class MindReaderGame {

    private static Scanner in = null;

    public static void main(String[] args) {
	playGame();
    }

    static void playGame() {
	MindReader game = Factory.createMindReader();
	game.loadPlayerProfile("profile.txt");
	in = new Scanner(System.in);
	printWelcome();
	for (;;) {
	    String prediction = game.getPrediction();
	    String choice = readChoice();
	    if (choice.equals("quit"))
		break;
	    game.makeChoice(choice);
	    printResult(prediction, choice);
	    printScores(game);
	}
	game.savePlayerProfile("profile.txt");
	in.close();
    }

    static void printWelcome() {
	System.out.println();
	System.out.println("Welcome to Mind Reader.");
	System.out.println();
    }

    static String readChoice() {
	String choice = null;
	for (;;) {

	    System.out.println("Please choose heads or tails.");
	    System.out.print("What is your choice [h/t or quit] ? ");
	    String s = in.next();
	    System.out.println();

	    s = s.toLowerCase();
	    if (s.startsWith("h"))
		choice = "heads";
	    else if (s.startsWith("t"))
		choice = "tails";
	    else if (s.startsWith("q"))
		choice = "quit";

	    if (choice != null)
		break;
	    System.out.println("Invalid choice.");

	}
	return choice;
    }

    static void printResult(String prediction, String choice) {
	System.out.println("My prediction was " + prediction + ".");
	System.out.println("Your choice was " + choice + ".");
	if (prediction.equals(choice)) {
	    System.out.println("I win!");
	} else {
	    System.out.println("You win!");
	}
	System.out.println();
    }

    static void printScores(MindReader game) {
	System.out.println("Your score: " + game.getPlayerScore());
	System.out.println(" My  score: " + game.getMindReaderScore());
	System.out.println();
    }

}
