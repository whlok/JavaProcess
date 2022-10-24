import org.junit.Test;
import weather.WeatherData;
import weather.board.CurrentConditionsDisplay;
import weather.board.ForecastDisplay;
import weather.board.StatisticsDisplay;

/**
 * @author wenhoulai
 */
public class WeatherStation {
	@Test
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		// 建立三个布告板
		CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
