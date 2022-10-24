package thread;

import java.util.LinkedList;

/**
 * 异步模式之生产者/消费者：
 * <p>
 * 消费队列可以用来平衡生产和消费的线程资源，不需要产生结果和消费结果的线程一一对应
 * 生产者仅负责产生结果数据，不关心数据该如何处理，而消费者专心处理结果数据
 * 消息队列是有容量限制的，满时不会再加入数据，空时不会再消耗数据
 * JDK 中各种阻塞队列，采用的就是这种模式
 *
 * @author wenhoulai
 */
public class ProducerConsumer {

	public static void main(String[] args) {
		MessageQueue queue = new MessageQueue(2);
		for (int i = 0; i < 3; i++) {
			int id = i;
			new Thread(() -> {
				queue.put(new Message(id, "值" + id));
			}, "生产者" + i).start();
		}

		new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000);
					queue.take();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "消费者").start();
	}
}

//消息队列类，Java间线程之间通信
class MessageQueue {
	private final LinkedList<Message> list = new LinkedList<>();
	private final int capacity;

	public MessageQueue(int capacity) {
		this.capacity = capacity;
	}

	public Message take() {
		synchronized (list) {
			while (list.isEmpty()) {
				System.out.println(Thread.currentThread().getName() + ": 队列为空，消费者线程等待");
				try {
					list.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Message message = list.removeFirst();
			System.out.println(Thread.currentThread().getName() + ": 已消费消息--" + message);
			list.notifyAll();
			return message;
		}
	}

	public void put(Message message) {
		synchronized (list) {
			while (list.size() == capacity) {
				System.out.println(Thread.currentThread().getName() + ": 队列已满，生产者线程等待");
				try {
					list.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			list.addLast(message);
			System.out.println(Thread.currentThread().getName() + ": 已生产消息--" + message);
			list.notifyAll();
		}
	}
}

class Message {
	private int id;
	private Object value;

	public Message(int id, Object value) {
		this.id = id;
		this.value = value;
	}
}