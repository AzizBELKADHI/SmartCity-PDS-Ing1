package sakao_server;

import java.util.ArrayList;

public class PollutionService {

	private Crud_Controller controller;

	public PollutionService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<String> showAllPollutionSensor() throws ClassNotFoundException {
		return controller.showAllPollutionSensors();
	}

	public boolean addOnPollutionSensor(String target, ArrayList<String> list) {
		boolean b = false;
		try {
			controller.addOnPollutionSensor(target, list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

}
