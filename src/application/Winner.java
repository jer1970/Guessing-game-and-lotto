package application;


public class Winner implements java.io.Serializable, Comparable<Winner>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 93778538559079658L;
	
	private String fName;
	private String lName;
	private String prize;
	
	public Winner(String fn, String ln, String p){
		this.fName = fn;
		this.lName = ln;
		this.prize = p;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public String getPrize() {
		return prize;
	}

	@Override
	public int compareTo(Winner w) {
		if(this.lName.compareTo(w.getlName()) == 0 ){
			if(this.fName.compareTo(w.getfName()) == 0)
				return 0;
			else if(this.fName.compareTo(w.getfName()) < 0)
				return -1;
			else
				return 1;
		}
		else if(this.lName.compareTo(w.lName) < 0)
			return -1;
		else
			return 1;
	}
	public String toString(){
		return fName + " " + lName;
	}
}
