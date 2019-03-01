
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.Scanner;

public class PassengerSeat {
	public static void main(String[] args) {
		// String str = "1A 3B 2C 2A 50J 50A 50B 50C 50D 50E 50F 50G 50H 50K 1B
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the reserved seats: ");
		String str = input.nextLine();
		System.out.println(checkSeats(str));
	}

	public static int checkSeats(String string) {

		// Declaration based on assumptions mentioned in problem
		int numberRows = 50;
		int maxThree = numberRows * 3;
		int remainingMaxThree = maxThree;
		int threeSeat = 0;

		String upperCase = string.toUpperCase();

		/*
		 * Associating rows A through K with different number of zeros to
		 * identify them after sorting
		 */
		upperCase = upperCase.replace("A", "00");
		upperCase = upperCase.replace("B", "00");
		upperCase = upperCase.replace("C", "00");
		upperCase = upperCase.replace("D", "0000");
		upperCase = upperCase.replace("E", "0000");
		upperCase = upperCase.replace("F", "0000");
		upperCase = upperCase.replace("G", "0000");
		upperCase = upperCase.replace("H", "000000");
		upperCase = upperCase.replace("J", "000000");
		upperCase = upperCase.replace("K", "000000");

		upperCase = upperCase.replace(" ", ",");

		Map<Integer, Integer> repeated = new HashMap<>();

		// Converting the string to a list of integers
		List<Integer> seats = Stream.of(upperCase.split(",")).map(Integer::parseInt).collect(Collectors.toList());

		Collections.sort(seats);

		System.out.println(seats);
		// System.out.println(seats.size());

		// Enhanced for loop to populate HashMap with repeated values
		for (int number : seats) {
			int counter = repeated.containsKey(number) ? repeated.get(number) : 0;
			counter += 1;
			repeated.put(number, counter);
		}
		System.out.println(repeated);

		// Enhanced for loop to track number of zeros
		for (int tracker : repeated.keySet()) {
			int store = tracker;
			int counter = 0;
			while (store % 10 == 0) {
				counter++;
				store /= 10;
				// System.out.println(counter);
			}
			System.out.println(counter);
			if ((counter == 4 || counter == 5) && repeated.get(tracker) >= 2) {
				remainingMaxThree -= 1;
			}
			// System.out.println(counter);

			if ((counter == 2 || counter == 3) && repeated.get(tracker) >= 1) {
				remainingMaxThree -= 1;
			}
			if ((counter == 6 || counter == 7) && repeated.get(tracker) >= 1) {
				remainingMaxThree -= 1;
			}

		}

		// Loop to find number of triplicates
		for (int getCtr : repeated.values()) {
			if (getCtr > 2) {

				threeSeat += getCtr / 3;

			}

		}

		/*
		 * ABC [||] DEFG [||] HJK ABC [||] DEFG [||] HJK
		 */

		System.out.println("Maximum 3 Person family that can be seated: " + maxThree);
		System.out.println();
		System.out.println(
				"Identifying 3 person family that may have taken seats based on reservation list: " + threeSeat);
		System.out.println();
		System.out.println("Remaining seats that can fit 3 Person family: ");
		return remainingMaxThree;

	}

}
