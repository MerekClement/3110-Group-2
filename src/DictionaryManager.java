// The following class is for managing the dictionary and what is acceptable.

import Developer.Utils;
import jdk.jshell.execution.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManager {
    // Constants
    private static final String path = "resources/Words"; // <-- Do not add an extension.

    // Instance variables
    private ArrayList _words = new ArrayList<String>();
    private Utils _dev;

    /**
     * The following method is used to load the dictionary into the program.
     */
    public DictionaryManager(Utils dev) {
        // Load the dictionary
        this._loadDictonary();
        this._dev = dev;
    }

    /**
     * The following method is used to load the dictionary into the program.
     * This overloaded constructor is used to load a custom dictionary.
     * @param {ArrayList} - The array of words to load into the dictionary.
     */
    public DictionaryManager(ArrayList<String> words, Utils dev) {
        this._words = words;
        this._dev = dev;
    }

    /**
     * The following method is used to load the dictionary into the program.
     * It will use the default directory defined as path.
     */
    private void _loadDictonary() {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this._words.add(data);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * The following getter is for getting all the words in the dictonary.
     * @return {ArrayList} - Returns the array of words in the dictonary.
     */
    public ArrayList<String> getWords() {
        return this._words;
    }

    /**
     * isWord is used for checking if the word is valid.
     * @param word {String} The word to search for.
     * @return {boolean} True if the word is found, false otherwise.
     */
    public boolean isWord(String word) {
        word = word.toLowerCase();
        this._dev.printDev("Searching for word: " + word + " in list of " + this._words.size());
        return this._words.contains(word);
    }
}