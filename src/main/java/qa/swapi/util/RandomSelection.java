package qa.swapi.util;

import java.util.Random;

public class RandomSelection {
	public static String randomPeopleSelection() {
		Random rn = new Random();
		int answer = rn.nextInt(Constants.MAC_POSSIBLE_SELECTION - Constants.MIN_POSSIBLE_SELECTION + 1)
				+ Constants.MIN_POSSIBLE_SELECTION;
		String rndNum = Integer.toString(answer);
		return rndNum;
	}
}
