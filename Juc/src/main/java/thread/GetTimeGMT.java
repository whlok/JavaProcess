package thread;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author wenhoulai
 */
public class GetTimeGMT {
	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss zzz");

		Date date = new Date();

		System.out.println("Local Time: " + sdf.format(date));

		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		System.out.println("GMT Time  : " + sdf.format(date));

		ZonedDateTime currentDate = ZonedDateTime.now(ZoneOffset.UTC);
		System.out.println(currentDate);
		System.out.println("时间1 " + System.currentTimeMillis());
		LocalDateTime localDateTime = LocalDateTime.now();
		ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
		long t = localDateTime.toEpochSecond(zoneOffset);
		System.out.println("时间2 " + t);

	}
}
