import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {
	private Data data;
	boolean flag;
	public Load(){
		
		// Game Load
		try {
			FileInputStream fileIn = new FileInputStream("gameData.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			data = (Data) in.readObject();
			in.close();
			fileIn.close();		
			
			System.out.println("Deserialized Object...");
			flag = true;
		}
		catch(IOException i) {
			System.out.println("No Save file Found");
			flag = false;
		} catch (ClassNotFoundException i) {
			i.printStackTrace();
		}
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	

}
