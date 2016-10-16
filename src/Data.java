import java.io.Serializable;

public class Data implements Serializable{
	/**
	 * 
	 */
	//Data keeping the Score,Lives and Difficulty
	private static final long serialVersionUID = 1L;
	private int player_score, computer_score;
	private int difficulty;
	private int lives_left;

	public Data(int difficulty){
		if(difficulty == 1)
			lives_left = 8;
		else if  (difficulty == 2)
			lives_left = 6;
		else
			lives_left = 4;
		this.difficulty = difficulty;
		player_score = 0;
		computer_score = 0;
	}
	
	public void modifyData(int difficulty){
		if(difficulty == 1)
			lives_left = 8;
		else if  (difficulty == 2)
			lives_left = 6;
		else
			lives_left = 4;
		this.difficulty = difficulty;
		player_score = 0;
		computer_score = 0;
	}
	
	public void wonData(int difficulty){
		if(difficulty == 1)
			lives_left = 8;
		else if  (difficulty == 2)
			lives_left = 6;
		else
			lives_left = 4;
		player_score++;
		
	}
	public void lostData(int difficulty){
		if(difficulty == 1)
			lives_left = 8;
		else if  (difficulty == 2)
			lives_left = 6;
		else
			lives_left = 4;
		computer_score++;
		
	}
	
	public void newGameData(int difficulty)
	{
		if(difficulty == 1)
			lives_left = 8;
		else if  (difficulty == 2)
			lives_left = 6;
		else
			lives_left = 4;
		
		computer_score = 0;
		player_score = 0;
	}
	
	public boolean ifLost(){
		if (lives_left==0)
			return true;
		return false;
	}


	public int getDifficulty() {
		return difficulty;
	}

	public int getPlayer_score() {
		return player_score;
	}

	public int getComputer_score() {
		return computer_score;
	}

	public int getLives_left() {
		return lives_left;
	}

	public void setLives_left(int lives_left) {
		this.lives_left= lives_left;
	}

	public void setPlayer_score(int player_score) {
		this.player_score = player_score;
	}

	public void setComputer_score(int computer_score) {
		this.computer_score = computer_score;
	}
	
	
}
