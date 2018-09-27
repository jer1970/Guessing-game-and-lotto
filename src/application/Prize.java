package application;

public class Prize implements Comparable<Prize>{
	private String key;
	private String prize;
	
	public Prize(){
				
	}
	public Prize(String k, String p){
		this.key = k;
		this.prize = p;		
	}
	
	public String toString(){
		return "key: " + key + " prize " + prize;
	}

	public String getKey() {
		return key;
	}

	public String getPrize() {
		return prize;
	}
	public int compareTo(Prize o) {
		int val = 0;
		if(this.getKey().compareTo(o.getKey()) > 0)
			val = 1;
		else if(this.getKey().compareTo(o.getKey()) < 0)
			val = -1;
		
		return val;
	}

}
