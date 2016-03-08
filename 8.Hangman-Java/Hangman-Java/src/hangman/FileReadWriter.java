package hangman;

import java.io.*;
import java.util.ArrayList;

public class FileReadWriter {

	private ObjectOutputStream output;
	private ObjectInputStream input;
	ArrayList<Players> myArr = new ArrayList<Players>();

	public void openFileToWrite() {
		try { // Open file
			output = new ObjectOutputStream(new FileOutputStream("players.ser", true)); //ObjectOutputStream cant append to an existing file, bad choice!!!!!!
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		}
	}

	// add records to file
	public void addRecords(int scores, String name) {
		Players players = new Players(name, scores); // object to be written to file

		try { // output values to file
			output.writeObject(players); // output players
		} catch (IOException ioException) {
			System.err.println("Error writing to file.");
		}
	}

	public void closeFileFromWriting() {
		try{ // close file
			if (output != null){
			  output.close();
			}
		} catch (IOException ioException) {
			System.err.println("Error closing file."); //show error
            System.exit(1); //exit
		}
	}

    //Removed unnecessary if-statement that would always execute
    //Removed excessive parentheses
	public void openFiletoRead() {
		try {
            input = new ObjectInputStream(new FileInputStream("players.ser"));
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		}
	}

    //Removed while loop
	public void readRecords() {
		Players records;

		try { // input the values from the file
			Object obj = null;

            while((obj = input.readObject()) != null) {
                records = (Players) obj;
                myArr.add(records);
                System.out.printf("DEBUG: %-10d%-12s\n",
                            records.getScores(), records.getName());
            } // end while
        } // end try
		 catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Unable to create object.");
		} catch (IOException ioException) {
			System.err.println("Error during reading from file.");
		}
	}

    //Removed closefilefromreading method

    //Removed unnecessary if statement that repeats the sorting and printing of scoreboard
	public void printAndSortScoreBoard() {
		Players temp;
		int n = myArr.size();
		for (int pass = 1; pass < n; pass++) { //Sort arraylist
			for (int i = 0; i < n - pass; i++) {
				if (myArr.get(i).getScores() > myArr.get(i + 1).getScores()) {
					temp = myArr.get(i);
                    myArr.set(i, myArr.get(i + 1));
                    myArr.set(i + 1, temp);
				}
			}
		}

		System.out.println("Scoreboard:"); //Print scoreboard
		for (int i = 0; i < myArr.size(); i++) {
			System.out.printf("%d. %s ----> %d\n", i+1, myArr.get(i).getName(),
					myArr.get(i).getScores());
		}
	}

	//Removed exit statement that would always execute
    //Changed from private to public after removing method calling this method
	public void tryCloseFileFromReading() {
		try {
			if (input != null){
				input.close();
			}
		} catch (IOException ioException) {
			System.err.println("Error closing file.");
			System.exit(1);
		}
	}
}
