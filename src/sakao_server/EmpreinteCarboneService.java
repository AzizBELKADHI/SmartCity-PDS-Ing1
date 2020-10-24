package sakao_server;

import java.util.ArrayList;

public class EmpreinteCarboneService {

	private Crud_Controller controller;

	public EmpreinteCarboneService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<String> showCityEM() {
		return controller.showCityEM();
	}
	
	public ArrayList<String> showBetaAverage() {
		return controller.showBeta();
	}

	public ArrayList<String> showMaxNumberVehicles() {
		return controller.showVehiculNumb();
	}
}