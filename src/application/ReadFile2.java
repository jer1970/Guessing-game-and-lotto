/**
 * @author jeremy staunton
 * studentId r00158317
 * 
 */
package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import myTree.Tree;



public class ReadFile2 {
	private Prize pr;
	private File file = new File("src/prizesFolder/prizes.txt");
	private FileReader fr = null;
	private BufferedReader br = null;

	private Tree<Prize> myTree;
	

	public void read() {

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
		} catch (IOException e) {
			System.out.println("IOException failed to get file ");
		}
		myTree = new Tree<Prize>();


		boolean eof = false;
		while (eof == false) {
			String[] array = null;
			String line = null;
			int length = 0;
			do {
				try {
					line = br.readLine();
					if (line != null) {
						line = line.trim();
						length = line.length() + 1;
					}
				} catch (IOException e) {
					System.out.println("IOException");
				}
				try {
					array = line.split(",");
					for (int i = 0; i < array.length; i++)
						System.out.print(array[i]);
					System.out.println();
				} catch (NullPointerException e) {
					System.out.println("NullPointerException1");
				}
			} while (line != null && line.length() > length);

			if (line == null)
				eof = true;
			try {
				//int star = Integer.parseInt(array[1].trim());
				
				pr = new Prize(array[2].trim(), array[0]);
				myTree.insertNode(pr);

			} catch (NullPointerException e) {
				System.out.println("NullPointerException2");
			}
		}

	}
	
	public Tree<Prize> getTree(){
		return myTree;
	}
}
