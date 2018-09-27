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
import java.util.TreeMap;

public class ReadFile {
	private File file = new File("src/prizesFolder/prizes.txt");
	private FileReader fr = null;
	private BufferedReader br = null;

	private TreeMap<String, TreeMap<String, String>> hashStar;
	private TreeMap<String, String> fourStarMap;
	private TreeMap<String, String> fiveStarMap;
	private TreeMap<String, String> sixStarMap;

	public void read() {

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
		} catch (IOException e) {
			System.out.println("IOException failed to get file ");
		}

		hashStar = new TreeMap<String, TreeMap<String, String>>();
		fourStarMap = new TreeMap<String, String>();
		fiveStarMap = new TreeMap<String, String>();
		sixStarMap = new TreeMap<String, String>();

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
				int star = Integer.parseInt(array[1].trim());
				switch (star) {
				case 4:
					fourStarMap.put(array[2].trim(), array[0].trim());
					System.out.println(fourStarMap.get(array[2].trim()));
					hashStar.put(array[1].trim(), fourStarMap);
					break;
				case 5:
					fiveStarMap.put(array[2].trim(), array[0].trim());
					hashStar.put(array[1].trim(), fiveStarMap);
					break;
				case 6:
					sixStarMap.put(array[2].trim(), array[0].trim());
					hashStar.put(array[1].trim(), sixStarMap);
					break;
				}
			} catch (NullPointerException e) {
				System.out.println("NullPointerException2");
			}
		}

		// print the results
		// for (String key : hashStar.keySet()) {
		// System.out.print(key + ": ");
		// TreeMap<String, String> hashPrize = hashStar.get(key);
		// for (String aKey : hashPrize.keySet()) {
		// System.out.print(aKey + ": ");
		// String value = hashPrize.get(aKey);
		// System.out.println(value);
		// }
		// System.out.println("");
		// }
	}

	public TreeMap<String, TreeMap<String, String>> getHashStar() {
		return hashStar;
	}
}
