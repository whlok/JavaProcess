package Cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wenhoulai
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	private final int CACHE_SIZE;

	/**
	 * 传进来的是最大能缓存的数据量。
	 *
	 * @param cacheSize
	 */
	public LRUCache(int cacheSize) {
		super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
		this.CACHE_SIZE = cacheSize;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > CACHE_SIZE;
	}
}
