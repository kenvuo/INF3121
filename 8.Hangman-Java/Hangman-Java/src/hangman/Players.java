package hangman;

import java.io.Serializable;

public class Players implements Serializable{
	
	private String name;
	private int scores;

	public Players(String name, int scores) {
		this.name = name;
		this.scores = scores;
	}

	//return player name
	public String getName() {
		return name;
	}

	//return players score:
	public int getScores() {
		return scores;
	}
}
