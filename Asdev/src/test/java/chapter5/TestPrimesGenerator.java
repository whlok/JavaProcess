package chapter5;

import org.junit.jupiter.api.Test;

/**
 * @author wenhoulai
 */
public class TestPrimesGenerator {
	@Test
	public void testPrimes_V1() {
		int[] nullArray = PrimesGenerator_v1.generatePrimes(0);
		assert nullArray.length == 0;

		int[] minArray = PrimesGenerator_v1.generatePrimes(2);
		assert minArray.length == 1;
		assert minArray[0] == 2;

		int[] threeArray = PrimesGenerator_v1.generatePrimes(3);
		assert threeArray.length == 2;
		assert threeArray[0] == 2;
		assert threeArray[1] == 3;

		int[] centArray = PrimesGenerator_v1.generatePrimes(100);
		assert centArray.length == 25;
		assert centArray[24] == 97;

		System.out.println("TEST " + PrimesGenerator_v1.class.getName()+" SUCCESS");
	}

	@Test
	public void testPrimes_V2() {
		int[] nullArray = PrimesGenerator_v2.generatePrimes(0);
		assert nullArray.length == 0;

		int[] minArray = PrimesGenerator_v2.generatePrimes(2);
		assert minArray.length == 1;
		assert minArray[0] == 2;

		int[] threeArray = PrimesGenerator_v2.generatePrimes(3);
		assert threeArray.length == 2;
		assert threeArray[0] == 2;
		assert threeArray[1] == 3;

		int[] centArray = PrimesGenerator_v2.generatePrimes(100);
		assert centArray.length == 25;
		assert centArray[24] == 97;

		System.out.println("TEST " + PrimesGenerator_v2.class.getName()+" SUCCESS");
	}

	@Test
	public void testPrimes_V3() {
		int[] nullArray = PrimesGenerator_v3.generatePrimes(0);
		assert nullArray.length == 0;

		int[] minArray = PrimesGenerator_v3.generatePrimes(2);
		assert minArray.length == 1;
		assert minArray[0] == 2;

		int[] threeArray = PrimesGenerator_v3.generatePrimes(3);
		assert threeArray.length == 2;
		assert threeArray[0] == 2;
		assert threeArray[1] == 3;

		int[] centArray = PrimesGenerator_v3.generatePrimes(100);
		assert centArray.length == 25;
		assert centArray[24] == 97;

		System.out.println("TEST " + PrimesGenerator_v3.class.getName()+" SUCCESS");
	}

	@Test
	public void testPrimes_V4() {
		int[] nullArray = PrimesGenerator_v4.generatePrimes(0);
		assert nullArray.length == 0;

		int[] minArray = PrimesGenerator_v4.generatePrimes(2);
		assert minArray.length == 1;
		assert minArray[0] == 2;

		int[] threeArray = PrimesGenerator_v4.generatePrimes(3);
		assert threeArray.length == 2;
		assert threeArray[0] == 2;
		assert threeArray[1] == 3;

		int[] centArray = PrimesGenerator_v4.generatePrimes(100);
		assert centArray.length == 25;
		assert centArray[24] == 97;

		System.out.println("TEST " + PrimesGenerator_v4.class.getName()+" SUCCESS");
	}

	@Test
	public void testPrimes_V5() {
		int[] nullArray = PrimesGenerator_v5.generatePrimes(0);
		assert nullArray.length == 0;

		int[] minArray = PrimesGenerator_v5.generatePrimes(2);
		assert minArray.length == 1;
		assert minArray[0] == 2;

		int[] threeArray = PrimesGenerator_v5.generatePrimes(3);
		assert threeArray.length == 2;
		assert threeArray[0] == 2;
		assert threeArray[1] == 3;

		int[] centArray = PrimesGenerator_v5.generatePrimes(100);
		assert centArray.length == 25;
		assert centArray[24] == 97;

		System.out.println("TEST " + PrimesGenerator_v5.class.getName()+" SUCCESS");
	}

	@Test
	public void testPrimes_V6() {
		int[] nullArray = PrimesGenerator_v6.generatePrimes(0);
		assert nullArray.length == 0;

		int[] minArray = PrimesGenerator_v6.generatePrimes(2);
		assert minArray.length == 1;
		assert minArray[0] == 2;

		int[] threeArray = PrimesGenerator_v6.generatePrimes(3);
		assert threeArray.length == 2;
		assert threeArray[0] == 2;
		assert threeArray[1] == 3;

		int[] centArray = PrimesGenerator_v6.generatePrimes(100);
		assert centArray.length == 25;
		assert centArray[24] == 97;

		System.out.println("TEST " + PrimesGenerator_v6.class.getName()+" SUCCESS");
	}
}
