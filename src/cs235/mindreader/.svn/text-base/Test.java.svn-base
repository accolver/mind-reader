package cs235.mindreader;

import java.io.FileNotFoundException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {

		MindReader mindReader = Factory.createMindReader();
		try {
			mindReader.loadPlayerProfile(null);
		} catch (IllegalArgumentException e) {
			System.out.println("File cannot be null");
		}
		mindReader.loadPlayerProfile("NonExistentFileName");

		makeChoices(mindReader);
		try {
			mindReader.makeChoice("nothing");
		} catch (IllegalArgumentException e) {
			System.out.println("Can only be heads or tails");
		}
		mindReader.getPrediction();
		mindReader.getMindReaderScore();
		mindReader.getPlayerScore();
		try {
			mindReader.savePlayerProfile(null);
		} catch (IllegalArgumentException e) {
			System.out.println("cannot save a file as 'null'");
		}
		mindReader.savePlayerProfile("profile2.txt");
		Counter c = new Counter(0, 0);
		try {
			c.setHeads(-1);
		} catch (IllegalArgumentException e) {
			System.out.println("Heads must be set as a non-negative number");
		}
		c.setHeads(1);
		c.getHeads();
		try {
			c.setTails(-1);
		} catch (IllegalArgumentException e) {
			System.out.println("Tails must be set as a non-negative number");
		}
		c.setTails(1);
		c.getTails();
		c.toString();

	}

	private static void makeChoices(MindReader mindReader) {

		mindReader.makeChoice("heads");
		mindReader.makeChoice("tails");
		mindReader.makeChoice("heads");
		mindReader.makeChoice("tails");
		mindReader.makeChoice("heads");
		mindReader.makeChoice("tails");
		
	}
}
