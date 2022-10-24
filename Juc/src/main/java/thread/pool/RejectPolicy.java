package thread.pool;

/**
 * 拒绝策略。
 *
 * @author wenhoulai
 */
public interface RejectPolicy<T> {
	void reject(BlockingQueue<T> queue, T task);
}
