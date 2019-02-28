import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassengerSeat {
	public static void main(String[] args) {
		String str = "1A 3B 2C 2A 3B 50J 45A 50A 50B 50C 9A 50D 50E 50F 50G 50H 50K 1B 1C 1E 1F 1G";
		/*Scanner input = new Scanner(System.in);
		System.out.println("Enter the reserved seats: ");
		String str = input.nextLine();*/
		System.out.println(checkSeats(str));
	}

	public static int checkSeats(String string) {

		// Declaration based on assumptions mentioned in problem
		int numberRows = 50;
		int perRow = 10;
		int maxThree = numberRows * 3;
		int remainingMaxThree = 0;

		String upperCase = string.toUpperCase();
		
		/* Associating rows A through K with different number of zeros to
		 identify them after sorting*/
		upperCase = upperCase.replace("A", "00");
		upperCase = upperCase.replace("B", "00");
		upperCase = upperCase.replace("C", "00");
		upperCase = upperCase.replace("D", "000");
		upperCase = upperCase.replace("E", "000");
		upperCase = upperCase.replace("F", "000");
		upperCase = upperCase.replace("G", "000");
		upperCase = upperCase.replace("H", "00000");
		upperCase = upperCase.replace("J", "00000");
		upperCase = upperCase.replace("K", "00000");

		upperCase = upperCase.replace(" ", ",");

		Map<Integer, Integer> repeated = new HashMap<>();
		
		//Converting the string to a list of integers
		List<Integer> seats = Stream.of(upperCase.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		Collections.sort(seats);

		// System.out.println(seats);
		
		//Enhanced for loop to populate HashMap with repeated values
		for (int number : seats) {
			int counter = repeated.containsKey(number) ? repeated.get(number) : 0;
			counter += 1;
			repeated.put(number, counter);
		}
		
		//Enhanced for loop to calculate number of triplicates 
		int threeSeat = 0;
		for (int getCtr : repeated.values()) {
			if (getCtr > 2) {

				threeSeat += getCtr / 3;

			}
		}

		/*
		 * ABC [||] DEFG [||] HJK 
		 * ABC [||] DEFG [||] HJK
		 */
		
		
		remainingMaxThree = maxThree - threeSeat;

		System.out.println("Maximum 3 Person family that can be seated: " + maxThree);
		System.out.println();
		System.out.println(
				"Identifying 3 person family that may have taken seats based on reservation list: " + threeSeat);
		System.out.println();
		System.out.println("Remaining seats that can fit 3 Person family: ");
		return remainingMaxThree;

	}

}
