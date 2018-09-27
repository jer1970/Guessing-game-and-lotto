package application;

import java.util.Comparator;

public class PrizeComparator implements Comparator<Winner>{

	@Override
	public int compare(Winner o1, Winner o2) {
		if(o1.getPrize().compareTo(o2.getPrize()) == 0)
			return 0;
		return (o1.getPrize().compareTo(o2.getPrize()));
	}

}
