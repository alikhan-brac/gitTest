package qa.streamTest.tests;

import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Comparator;
import java.util.HashSet;

import static java.util.stream.Collectors.toMap;

import qa.streamTest.models.User;

public class StreamTest {

	public List<User> userListProvider() {
		List<User> users = Stream.of(new User("Kate", 30), new User("Sam", 20), new User("Sam", 15),
				new User("Sam", 15), new User("Sam", 15), new User("Sam", 15), new User("Mike", 39),
				new User("Jenny", 23), new User("Jenny", 23), new User("Jenny", 23)).collect(Collectors.toList());
		return users;
	}

	public List<Integer> userAgesProvider() {
		List<Integer> ageOfUsers = userListProvider().stream().map(n -> n.getAge()).collect(Collectors.toList());
		return ageOfUsers;
	}

	@Test(priority = 1)
	public void userListInDescendingAge() {
		System.out.println("User list in age-descending order >>>");
		userListProvider().stream().sorted(Comparator.comparingInt(User::getAge).reversed())
				.collect(Collectors.toList()).forEach(System.out::println);
	}

	@Test(priority = 2)
	public void duplicatesOfUserAge() {
		System.out.println("Duplicate(s) of user age >>>");
		Set<Integer> items = new HashSet<>();
		userAgesProvider().stream().filter(n -> !items.add(n)).collect(Collectors.toSet()).forEach(System.out::println);
	}

	@Test(priority = 3)
	public void duplicateCountOfUserAge() {
		System.out.println("Duplicate user age values -number of times >>>");
		Map<Integer, Long> map = userAgesProvider().stream().collect(toMap(Function.identity(), x -> 1L, Long::sum));
		map.entrySet().stream().filter(n -> n.getValue() > 1).forEach(System.out::println);
		long totalCount = map.entrySet().stream().filter(n -> n.getValue() > 1).map(n -> n.getValue()).reduce(0l,
				(sum, value) -> sum + value);
		System.out.println("total of duplicate count = " + totalCount);
	}

	@Test(priority = 4)
	public void nonDuplicatesOfUserAge() {
		System.out.println("User ages without duplate(s) >>>");
		userAgesProvider().stream().distinct().sorted().forEach(System.out::println);
	}
}