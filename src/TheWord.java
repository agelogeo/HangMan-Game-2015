import java.util.*;
public class TheWord {
	ArrayList<Character> wordLetters;
	ArrayList<Boolean> foundLetters;
	ArrayList<Character> currentFoundLetters;
	String word;
	
	public TheWord(String word){
		this.word = word;
		wordLetters  = new ArrayList<Character>();
		foundLetters = new ArrayList<Boolean>();
		currentFoundLetters = new ArrayList<Character>();

		
		// Converting from String to ArrayList Character
		for (int i = 0; i < word.length(); i++)
            wordLetters.add(word.charAt(i));
		
		// Creating True False ArrayList
		for(int i = 0; i<wordLetters.size(); i++)
			foundLetters.add(false);
		

		
	}
	
	public void showWord(){
		for(int i = 0; i<wordLetters.size(); i++)
			foundLetters.set(i, true);
	

	}
	
	//Checking if the player has won
	public boolean ifWon(Data data){
		for(boolean flag : foundLetters)
			if(flag == false)
				return false;
		return true;
	}
	
	// Searching if the letter exists inside the word
	public boolean searchLetter(String letter, Data current_data){
		char c = letter.charAt(0);
		boolean flag = false;
		int index = 0;
		for(char current: wordLetters){
			if(current == c)
			{
				foundLetters.set(index, true);
				flag = true;
			}
			index++;
		}
		
		if(flag == true)
			return true;
		
		current_data.setLives_left(current_data.getLives_left() - 1);
		return false;

		
	}
	
	//Final method to use for the GUI
	
	public String getTheCurrentWord(){
		CurrentLetters();
		return getStringRepresentation(currentFoundLetters);
	}
	
	// Converting CurrentFoundLetters to String
	public String getStringRepresentation(ArrayList<Character> list)
	{    
	    StringBuilder builder = new StringBuilder(list.size());
	    for(Character ch: list)
	    {
	        builder.append(ch);
	        builder.append(" ");
	        
	    }
	    return builder.toString();
	}

	//Creating Current Found Letters to display
	public void CurrentLetters(){
		currentFoundLetters.clear();
		for(int i = 0; i<wordLetters.size(); i++)
			if(foundLetters.get(i))
				currentFoundLetters.add(wordLetters.get(i));
			else
				currentFoundLetters.add('_');
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	

	
	
}
