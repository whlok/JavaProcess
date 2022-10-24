package weather.board;

import weather.DisplayElement;
import weather.Observer;
import weather.Subject;
import weather.WeatherData;

/**
 * @author wenhoulai
 */
public class StatisticsDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private Subject weatherData;

	public StatisticsDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Current conditions : " + temperature + "F degres and " + humidity + "% humidity");

	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temperature = temp;
		this.humidity = humidity;
		display();
	}
}
