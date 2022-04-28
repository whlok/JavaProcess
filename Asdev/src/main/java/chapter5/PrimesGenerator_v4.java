package chapter5;


/**
 * @author wenhoulai
 */
public class PrimesGenerator_v4 {

	private static boolean[] isCrossed;
	private static int[] result;

	public static int[] generatePrimes(int maxValue) {
		if(maxValue < 2){
			return new int[0];
		}
		initializeArrayOfIntegers(maxValue);
		crossOutMultiples();
		putUncrossedIntegerIntoResult();
		return result;
	}

	private static void putUncrossedIntegerIntoResult() {
		int count = 0;
		for(int i = 2; i < isCrossed.length; i++){
			if(notCrossed(i)){
				count++;
			}
		}

		result = new int[count];

		for(int i = 2, j = 0; i < isCrossed.length; i++){
			if(notCrossed(i)){
				result[j++] = i;
			}
		}
	}

	private static void crossOutMultiples() {
		int maxPrimeFactor = calcMaxPrimeFactor();
		for(int i = 2; i <= maxPrimeFactor; i++){
			if(notCrossed(i)){
				crossOutMultiplesOf(i);
			}
		}
	}

	private static boolean notCrossed(int i) {
		return !isCrossed[i];
	}

	private static void crossOutMultiplesOf(int i) {
		for(int multiple = 2*i; multiple < isCrossed.length; multiple += i){
			isCrossed[multiple] = true;
		}
	}

	private static int calcMaxPrimeFactor() {
		double maxPrimeFactor = Math.sqrt(isCrossed.length) + 1;
		return (int) maxPrimeFactor;
	}

	private static void initializeArrayOfIntegers(int maxValue) {
		isCrossed = new boolean[maxValue + 1];
		for(int i = 2; i < isCrossed.length; i++){
			isCrossed[i] = false;
		}
	}
}
