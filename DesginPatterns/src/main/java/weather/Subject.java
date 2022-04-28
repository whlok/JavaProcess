package weather;

/**
 * @author wenhoulai
 */
public interface Subject {
	// 这俩个方法都需要一个观察者作为变量，该观察者是用来注册或被删除的
	public void registerObserver(Observer observer);

	public void removeObserver(Observer observer);

	// 当主题状态改变时，这个方法会被调用，以通知所有的观察者
	public void notifyObservers();
}
