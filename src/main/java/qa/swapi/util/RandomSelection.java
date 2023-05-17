package qa.swapi.util;

import java.util.Random;

public class RandomSelection {
	public static String randomPeopleSelection() {
		Random rn = new Random();
		int answer = rn.nextInt(Constants.maxPossibleSelection - Constants.minPossibleSelection + 1)
				+ Constants.minPossibleSelection;
		String rndNum = Integer.toString(answer);
		return rndNum;
	}
}
