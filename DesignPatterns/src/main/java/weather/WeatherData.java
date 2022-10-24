package weather;

import java.util.ArrayList;

/**
 * @author wenhoulai
 */
public class WeatherData implements Subject {

	private final ArrayList<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;

	public WeatherData() {
		observers = new ArrayList<Observer>();
	}

	/**
	 * 当注册观察者时，我们只需要把它加到ArrayList后面即可
	 */

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		int i = observers.indexOf(observer);
		if (i >= 0) {
			observers.remove(observer);
		}
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(temperature, humidity, pressure);
		}
	}

	/**
	 * 当气象站得到更新观测值时，我们通知观察者
	 */
	public void measurementsChanged() {
		notifyObservers();
	}

	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}

	// WeatherData的其他方法
}
