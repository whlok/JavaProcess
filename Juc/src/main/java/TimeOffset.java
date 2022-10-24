import java.util.Calendar;

/**
 * 计算时间戳距离UTC的时间偏移量。
 *
 * @author wenhoulai
 */
public class TimeOffset {
	public static void main(String[] args) {
		int offset = Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis());
		System.out.println(offset);
	}
}
