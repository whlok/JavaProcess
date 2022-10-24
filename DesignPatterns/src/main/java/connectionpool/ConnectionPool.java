package connectionpool;

import java.sql.Connection;
import java.util.Random;

/**
 * @author wenhoulai
 */
public class ConnectionPool {

	public static void main(String[] args) {
		Pool pool = new Pool(2);
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				Connection con = pool.borrow();
				try {
					Thread.sleep(new Random().nextInt(1000));
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				pool.free(con);
			}, "Thread_" + i).start();
		}
	}

}
