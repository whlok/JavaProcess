package os;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

/**
 * LRU算法。
 * 基于双向链表+哈希表实现。
 *
 * @author wenhoulai
 */
public class LRUCache {
	/** 容量大小 */
	private int size;
	/** 限制大小 */
	private int capacity;
	/** 数据和链表中节点的映射 */
	Int2ObjectOpenHashMap<DoubleQueueNode> map;
	/** 头节点 避免null检查 */
	private DoubleQueueNode head;
	/** 尾节点 避免null检查 */
	private DoubleQueueNode tail;

	public LRUCache(int capacity) {

	}
}

// 双向链表节点
class DoubleQueueNode {
	int key;
	int val;
	DoubleQueueNode pre;
	DoubleQueueNode next;

	public DoubleQueueNode(int key, int val) {
		this.key = key;
		this.val = val;
	}
}