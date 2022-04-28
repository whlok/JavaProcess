package chapter5;

/**
 * @author wenhoulai
 */
public class PrimesGenerator_v1 {
	public static void main(String[] args) {
		int[] primes = generatePrimes(100);
		for (int prime : primes) {
			System.out.println(prime);
		}
	}

	/**
	* @Description: 生产质数
	* @Param: [maxValue] 输入质数查找上限
	* @return: int[] 返回质数数组
	*/
	public static int[] generatePrimes(int maxValue) {
		if(maxValue >= 2){
			int s = maxValue + 1;
			boolean[] f = new boolean[s];
			int i;
			for(i = 0; i < s; i++){
				f[i] = true;
			}
			f[0] = f[1] = false;

			int j;
			for(i = 2; i < Math.sqrt(s); i++){
				for(j = 2 * i; j < s; j += i){
					f[j] = false;
				}
			}

			int count = 0;
			for(i = 0;i < s; i++){
				if(f[i]){
					count++;
				}
			}

			int[] primes = new int[count];

			for(i = 0, j = 0; i < s; i++){
				if(f[i]){
					primes[j++] = i;
				}
			}

			return primes;
		} else{
			return new int[0];
		}
	}
}
