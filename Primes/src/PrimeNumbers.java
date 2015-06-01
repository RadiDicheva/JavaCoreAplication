import java.util.ArrayList;
import java.util.Scanner;

public class PrimeNumbers {

	public static ArrayList<Integer> primesInAnInterval(int from, int to) {

		if (from > to)
			throw new IllegalArgumentException(
					"Invalid input: from is bigger than to");
		if (from < 0 || to < 0)
			throw new IllegalArgumentException(
					"Invalid input: from or to is less than 0");

		boolean isPrime[] = new boolean[to + 1];

		for (int i = 1; i <= to; i++)
			isPrime[i] = true;

		int squareRoot = (int) Math.sqrt(to);

		for (int i = 2; i <= squareRoot; i++) {
			if (isPrime[i])
				for (int j = i * i; j <= to; j += i)
					isPrime[j] = false;
		}

		ArrayList<Integer> output = new ArrayList<Integer>();

		for (int i = from; i <= to; i++) {
			if (isPrime[i])
				output.add(i);
		}

		return output;
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.printf("From: ");
		int from = scan.nextInt();
		System.out.printf("To: ");
		int to = scan.nextInt();

		System.out.println("Prime numbers in this interval are: "
				+ primesInAnInterval(from, to));
		
		scan.close();
	}
}
