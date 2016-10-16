import java.io.*;
public class Save {
	public Save(Data gameData){
		// Game Save
		
		try {
			FileOutputStream fileOut = new FileOutputStream("GameData.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(gameData);
			out.close();
			fileOut.close();		
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		System.out.println("Serialized Object...");
	}
	

}
