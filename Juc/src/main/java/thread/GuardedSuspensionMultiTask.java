//package thread;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// * @author wenhoulai
// */
//public class GuardedSuspensionMultiTask {
//	public static void main(String[] args) {
//
//	}
//}
//
//class People extends Thread {
//	private int id;
//
//	private String mail;
//
//	@Override
//	public void run() {
//		GuardedObjectV guardedObject = MailBoxes.getGuardedObject(id);
//		System.out.println("开始送信 ID：" + guardedObject.getId() + " content: " + mail);
//		guardedObject.complete(mail);
//	}
//}
//
//
//class MailBoxes {
//	private static Map<Integer, GuardedObjectV> boxes = new HashMap<>();
//	private static int id = 1;
//
//	private static synchronized int generateId() {
//		return id++;
//	}
//
//	public static GuardedObjectV getGuardedObject(int id) {
//		return boxes.remove(id);
//	}
//
//	public static GuardedObjectV createGuardedObject() {
//		GuardedObjectV go = new GuardedObjectV(generateId());
//		boxes.put(go.getId(), go);
//		return go;
//	}
//
//	public static Set<Integer> getIds() {
//		return boxes.keySet();
//	}
//}
//
//class GuardedObjectV {
//	//标识，Guarded Object
//	private int id;
//
//	public GuardedObjectV(int generateId) {
//		id = generateId;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//}