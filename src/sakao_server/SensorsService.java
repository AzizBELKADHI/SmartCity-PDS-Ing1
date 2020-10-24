package sakao_server;

import java.util.ArrayList;
import java.util.Date;

public class SensorsService {

	private Crud_Controller controller;

	public SensorsService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<String> showAllSensors() throws ClassNotFoundException {
		return controller.showAllSensors();
	}

	public ArrayList<String> showSensorsPollution() throws ClassNotFoundException {
		return controller.showSensorsPollution();
	}

	public ArrayList<String> showSensorsWeather() throws ClassNotFoundException {
		return controller.showSensorsWeather();
	}

	public ArrayList<String> showSensorById(int id) throws ClassNotFoundException {
		return controller.showSensorById(id);
	}
	
	public boolean addOnSensor(String target, ArrayList<String> list) {
		boolean b = false;
		try {
			controller.addOnSensor(target, list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public boolean deleteSensorById(int ID) {
		boolean b = false;
		try {
			controller.deleteSensorById(ID);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean updateSensor(int id, boolean install) {
		boolean b = false;
		try {
			controller.updateSensor(id, install);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public ArrayList<String> showAlertByDate(String date) throws ClassNotFoundException {
		return controller.showAlertByDate(date);
	}
	
	
	
}
