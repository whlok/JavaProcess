package os;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wenhoulai
 */
public class LRU extends LinkedHashMap {
	private int capacity;

	public LRU(int capacity) {
		super(16, 0.75f, true);
		this.capacity = capacity;
	}

	@Override
	public boolean removeEldestEntry(Map.Entry eldest) {
		return super.size() >= capacity;
	}
}
