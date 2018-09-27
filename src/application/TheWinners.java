package application;

import java.util.ArrayList;


public class TheWinners implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7931507063337184907L;
	private ArrayList<Winner> winnerList;
	private int total;
	
	public TheWinners(){
		this.winnerList = new ArrayList<Winner>();
		this.total = 0;
	}

	public ArrayList<Winner> getWinnerList() {
		return winnerList;
	}

	public int getTotal() {
		return total;
	}
	
	public void add(Winner winr){
		this.winnerList.add(winr);
		this.total++;
		System.out.println("winner successfully added.");
	}
	
	public void remove(String fName, String lName){
		int size = winnerList.size();
		Winner w;
		for (int i = size - 1; i >= 0; i--){
			w = winnerList.get(i);
			if(w.getfName().equals(fName) && w.getlName().equals(lName)){
				winnerList.remove(w);
				this.total--;
			}
		}
	}
}
