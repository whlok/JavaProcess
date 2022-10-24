package match;

/**
 * 字符串匹配算法。
 *
 * @author wenhoulai
 * @link <a href="https://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html">字符串匹配算法链接</a>
 */
public class KMP {

	public int indexOf(String source, String pattern) {
		int i = 0, j = 0;
		char[] src = source.toCharArray();
		char[] ptn = pattern.toCharArray();

		int sLen = src.length;
		int pLen = ptn.length;
		int[] next = getNext(ptn);
		while (i < sLen && j < pLen) {
			if (j == -1 || src[i] == ptn[j]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}
		if (j == pLen) {
			return i - j;
		}
		return -1;
	}

	// 已知next[j] = k,利用递归的思想求出next[j+1]的值
	// 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
	// 1. 如果p[j] = p[k], 则next[j+1] = next[k] + 1;
	// 2. 如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,
	// 如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止

	protected int[] getNext(char[] p) {

		int pLen = p.length;
		int[] next = new int[pLen];

		int k = -1;
		int j = 0;
		// next数组中next[0]为-1
		next[0] = -1;

		while (j < pLen - 1) {
			if (k == -1 || p[j] == p[k]) {
				k++;
				j++;
				next[j] = k;
			} else {
				k = next[k];
			}
		}

		return next;
	}

	public static void main(String[] args) {
		KMP kmp = new KMP();
		String a = "QWERQWR";
		String b = "WWE QWERQW QWERQWERQWRT";
		int[] next = kmp.getNext(a.toCharArray());
		for (int i = 0; i < next.length; i++) {
			System.out.println(a.charAt(i) + "    " + next[i]);
		}
		int res = kmp.indexOf(b, a);
		System.out.println(res);
	}
}
