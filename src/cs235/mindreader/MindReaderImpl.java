package cs235.mindreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MindReaderImpl implements MindReader {

	private int mindReaderScore = 0;
	private int playerScore = 0;
	private List list = new LinkedList<String>();
	private Map map = new HashMap();

	final static int THREE = 3;
	final static int FOUR = 4;
	final static int FIVE = 5;

	// Constructs a new MindReaderImpl Object with a map, list, and default
	// starting scores.
	public MindReaderImpl(Map map, List list, int mindReaderScore,
			int playerScore) {
		this.map = map;
		this.list = list;
		this.mindReaderScore = 0;
		this.playerScore = 0;
	}

	// Saves the player's current list of choices in a file called "profile.txt"
	// This is formatted with a list of 4 choice sequences followed by the
	// player's choices of heads or tails
	public boolean savePlayerProfile(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException();
		}
		try {
			PrintWriter out = new PrintWriter(new FileWriter(filename));
			out.print(map.size() + "\n");
			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String line = "";
				List tempList2 = (List) it.next();
				Counter c = (Counter) map.get(tempList2);
				String counter = c.getHeads() + " " + c.getTails();
				for (int i = 0; i < tempList2.size(); i++) {
					line += tempList2.get(i) + " ";
				}
				out.println(line + counter);
			}
			out.close();
			System.out.println("File saved successfully");
			return true;

		} catch (IllegalArgumentException e) {
			System.out.println("File name cannot be null");
		} catch (IOException e) {
			System.out.println("Could not save file. Please verify path");
		}
		return false;
	}

	// Loads the player's "profile.txt" file and updates the map with it
	public boolean loadPlayerProfile(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException();
		}
		File file = new File(filename);
		int numLines = 0;
		try {
			Scanner in = new Scanner(file);
			if (in.hasNext())
				numLines = Integer.parseInt(in.nextLine());
			else
				return false;

			for (int i = 0; i < numLines; i++) {
				List tempList = new LinkedList();
				tempList.add(in.next());
				tempList.add(in.next());
				tempList.add(in.next());
				tempList.add(in.next());
				Counter c = new Counter(in.nextInt(), in.nextInt());
				map.put(tempList, c);
			}
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File was not found!");
			return false;
		} catch (NullPointerException e) {
			System.out.println("Cannot be null for file");
			return false;
		}
	}

	// Ensures only tails and heads as choices. Also compares the player's
	// choice with the mindreader's prediction and increments the appropriate
	// score
	public void makeChoice(String choice) {
		if (choice.equals("heads") || choice.equals("tails")) {
			if (choice.equals(getPrediction())) {
				mindReaderScore++;
			} else {
				playerScore++;
			}
		}

		else {
			throw new IllegalArgumentException("can be 'heads' or 'tails' only");
		}

		if (choice.equals("heads")) {
			list.add("heads");
		}
		if (choice.equals("tails")) {
			list.add("tails");
		}
		addEntry(list, map);
	}

	// If the list is 5 elements long, this calls entryDetermination and removes
	// the last element of the list
	public List addEntry(List list2, Map map) {
		if (list2.size() != FIVE) {
			return null;
		} else {
			List newList = new LinkedList(list2);
			newList.remove(FOUR);

			entryDetermination(map, list2, newList);
		}
		list2.remove(0);
		return list2;
	}

	// Determines wether to increment an existing counter for a list or create
	// a new entry in the map
	public void entryDetermination(Map map2, List list2, List newList) {

		// If the map already contains the key, increment the correct
		// counter
		if (map.containsKey(newList)) {
			Counter tempCounter = (Counter) map.get(newList);
			if (list2.get(FOUR).equals("heads")) {
				tempCounter.setHeads(tempCounter.getHeads() + 1);
			} else {
				tempCounter.setTails(tempCounter.getTails() + 1);
			}
			map.put(newList, tempCounter);
		}

		// If the map doesn't contain the key, create a new one with
		// associated value (counter)
		else {
			Counter counter = new Counter(0, 0);
			if (list2.get(FOUR).equals("heads")) {
				counter.setHeads(1);
			} else {
				counter.setTails(1);
			}
			map.put(newList, counter);
		}
	}

	// If the player's choice sequence is stored in the map and if the tails
	// counter is greater than the heads counter for that sequence, predict
	// tails
	// Otherwise predict heads
	public String getPrediction() {
		if (map.containsKey(list)) {
			Counter tempCounter = (Counter) (map.get(list));
			if (tempCounter.getTails() > tempCounter.getHeads()) {
				return "tails";
			}
		}
		return "heads";
	}

	// Returns the player's score
	public int getPlayerScore() {
		return playerScore;
	}

	// Returns the mindreader's score
	public int getMindReaderScore() {
		return mindReaderScore;
	}

}
