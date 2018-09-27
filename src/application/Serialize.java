package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialize {
	public void write(Object e){
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("src/prizesFolder/winners.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(e);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in src/prizesFolder/winners.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	public Object read(){
		Object obj = null;
		try {
	         FileInputStream fileIn = new FileInputStream("src/prizesFolder/winners.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         obj =  in.readObject();
	         in.close();
	         fileIn.close();
	         
	      } catch (IOException i) {
	         i.printStackTrace();
	       
	      } catch (ClassNotFoundException c) {
	         System.out.println("class not found");
	         c.printStackTrace();
	        
	      }
		return obj;
	}
}
