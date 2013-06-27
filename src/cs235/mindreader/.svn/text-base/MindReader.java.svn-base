package cs235.mindreader;


public interface MindReader {

    /**
     * Saves the player's profile to a file
     * 
     * @param filename the name of the file in which to store the profile
     * @return true if the profile was successfully written to the file,
     *         or false if an error occurred
     * @throws IllegalArgumentException if filename is null
     */
    boolean savePlayerProfile(String filename);

    /**
     * Loads the player's profile from a file
     * 
     * @param filename the name of the file to be loaded
     * @return true if the profile was successfully loaded from the file,
     *         or false if an error occurred
     * @throws IllegalArgumentException if filename is null
     */
    boolean loadPlayerProfile(String filename);

    /**
     * Give the player's choice to the mind reader.
     * Compare the choice to the prediction and increment the score.
     * Learn how to make better predictions based on the given choice.
     *
     * @param choice the string "heads" if the choice is heads,
     *         or the string "tails" if the choice is tails
     *
     * @throws IllegalArgumentException if choice is not "heads" or "tails"
     */
    void makeChoice(String choice);

    /**
     * Ask the mind reader for a prediction.
     * 
     * @return the string "heads" if the prediction is heads,
     *         or the string "tails" if the prediction is tails
     */
    String getPrediction();

    /**
     * Returns the player's score
     *
     * @return the player's score
     */
    int getPlayerScore();

    /**
     * Returns the mind reader's score
     *
     * @return the mind reader's score
     */
    int getMindReaderScore();

}
