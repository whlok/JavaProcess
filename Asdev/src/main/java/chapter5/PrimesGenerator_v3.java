package chapter5;

/**
 * @author wenhoulai
 *
 * 更改函数名使其更具可读性，优化初始化部分代码
 */
public class PrimesGenerator_v3 {
	private static boolean[] f;
	private static int[] result;

	public static int[] generatePrimes(int maxValue){
		if(maxValue < 2) {
			return new int[0];
		}

		initializeArrayOfIntegers(maxValue);
		crossOutMultiple();
		putUncrossedIntegerIntoResult();
		return result;
	}

	private static void putUncrossedIntegerIntoResult() {
		int count = 0;
		for (boolean b : f) {
			if (b) {
				count++;
			}
		}

		result = new int[count];

		for(int i = 0, j = 0; i < f.length; i++){
			if(f[i]){
				result[j++] = i;
			}
		}
	}

	private static void crossOutMultiple() {
		for(int i = 2; i < Math.sqrt(f.length); i++){
			if(f[i]){
				for(int j = 2*i; j < f.length; j+=i){
					f[j] = false;
				}
			}
		}
	}

	private static void initializeArrayOfIntegers(int maxValue) {
		f = new boolean[maxValue + 1];
		f[0] = f[1] = false;
		for(int i = 2; i < f.length; i++){
			f[i] = true;
		}
	}
}



