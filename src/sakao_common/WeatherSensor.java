package sakao_common;

public class WeatherSensor {

	private int idWeatherSensor, idSensor, idConfiguration;
	private int temperature;
	private String stateOfTheSky;

	public WeatherSensor() {
	}

	public WeatherSensor(int idWeatherSensor, int idSensor, int idConfiguration, int temperature,
			String stateOfTheSky) {
		this.idWeatherSensor = idWeatherSensor;
		this.idSensor = idSensor;
		this.idConfiguration = idConfiguration;
		this.temperature = temperature;
		this.stateOfTheSky = stateOfTheSky;
	}

	public int getIdWeatherSensor() {
		return idWeatherSensor;
	}

	public void setIdWeatherSensor(int idWeatherSensor) {
		this.idWeatherSensor = idWeatherSensor;
	}

	public int getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public int getIdConfiguration() {
		return idConfiguration;
	}

	public void setIdConfiguration(int idConfiguration) {
		this.idConfiguration = idConfiguration;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getStateOfTheSky() {
		return stateOfTheSky;
	}

	public void setStateOfTheSky(String stateOfTheSky) {
		this.stateOfTheSky = stateOfTheSky;
	}

	@Override
	public String toString() {
		return "{\"idWeatherSensor\":\"" + this.idWeatherSensor + "\"," + "\"idSensor\":\"" + this.idSensor + "\","
				+ "\"idConfiguration\":\"" + this.idConfiguration + "\"," + "\"temperature\":\"" + this.temperature
				+ "\"," + "\"stateOfTheSky\":\"" + this.stateOfTheSky + "\"}";
	}

}
