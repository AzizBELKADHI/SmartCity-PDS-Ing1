package sakao_server;

import java.util.ArrayList;

public class WeatherService {

	private Crud_Controller controller;

	public WeatherService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<String> showAllWeatherSensor() throws ClassNotFoundException {
		return controller.showAllWeatherSensors();
	}

	public boolean addOnWeatherSensor(String target, ArrayList<String> list) {
		boolean b = false;
		try {
			controller.addOnWeatherSensor(target, list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}
}