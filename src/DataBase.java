import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;

public class DataBase {
	private ArrayList<String> words = new ArrayList<String>();
	public DataBase(){
		
		// Reading the txt
		try{
			String line = " ";
			FileReader freader = new FileReader("wikipedia.txt");
			BufferedReader reader = new BufferedReader(freader);
			while(line != null)
			{
				String[] parts = null;
				line = reader.readLine();
				if(line == null)
					break;
				parts = line.split(" ");
				for (String word : parts) 
				{
					// Removing
					word = Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");  //  accents
					word = word.toUpperCase(); // Upper Casing the chars
					word = word.replaceAll("[:»«,.;!??(){}\\[\\]<>%]",""); // punctuations
					if(word.length() > 3 && !word.matches(".*\\d+.*"))
					{
						if(!words.contains(word)) //checking if the word already exists
							words.add(word); // adding the word
					}
				}
			}
			reader.close();
		}
			catch(FileNotFoundException e)
			{
				System.out.println("File not found!");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}

	// Selecting a random word from the database
	public String randomWord(){
		Random r = new Random();
		int shit = (r.nextInt(words.size()));
		System.out.println(shit);
		return words.get(shit);
	}
}
