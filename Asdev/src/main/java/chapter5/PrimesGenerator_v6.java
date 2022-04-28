package chapter5;

/**
 * @author wenhoulai
 */
public class PrimesGenerator_v6 {

	private static boolean[] crossedOut;
	private static int[] result;

	public static int[] generatePrimes(int maxValue) {
		if(maxValue < 2){
			return new int[0];
		}
		uncrossIntegersUpTo(maxValue);
		crossOutMultiples();
		putUncrossedIntegerIntoResult();
		return result;
	}

	private static void putUncrossedIntegerIntoResult() {
		result = new int[numberOfUncrossedIntegers()];
		for(int j = 0, i = 2; i < crossedOut.length; i++){
			if(notCrossed(i)){
				result[j++] = i;
			}
		}
	}

	private static int numberOfUncrossedIntegers() {
		int count = 0;
		for(int i = 2; i < crossedOut.length; i++){
			if(notCrossed(i)){
				count++;
			}
		}
		return count;
	}

	private static void crossOutMultiples() {
		int limit = determineIterationLimit();
		for(int i = 2; i <= limit; i++){
			if(notCrossed(i)){
				crossOutMultiplesOf(i);
			}
		}
	}

	private static boolean notCrossed(int i) {
		return !crossedOut[i];
	}

	private static void crossOutMultiplesOf(int i) {
		for(int multiple = 2*i; multiple < crossedOut.length; multiple += i){
			crossedOut[multiple] = true;
		}
	}

	private static int determineIterationLimit() {
		double maxPrimeFactor = Math.sqrt(crossedOut.length);
		return (int) maxPrimeFactor;
	}

	private static void uncrossIntegersUpTo(int maxValue) {
		crossedOut = new boolean[maxValue + 1];
		for(int i = 2; i < crossedOut.length; i++){
			crossedOut[i] = false;
		}
	}
}
