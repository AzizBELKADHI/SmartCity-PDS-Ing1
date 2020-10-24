package sakao_server;

import java.util.ArrayList;

import sakao_common.smartcity2;

public class smartCityServices {

	private Crud_Controller controller;

	public smartCityServices() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public smartcity2 GenerateCity() throws ClassNotFoundException {
		return controller.GenerateCity();
	}
	
	
	public boolean UpdateSmartCityVehicles(String target, ArrayList<String> list, smartcity2 smartCityObject) {
		boolean b = false;
		try {
			/// Student p = new Student(name, age);
			controller.UpdateSmartCityVehicles(target, list, smartCityObject);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}
	
	public void updateNumberinCirculation(int c) {
		
		try {
			controller.updateNumberinCirculation(c);
			
		}catch (Exception e) {
			e.printStackTrace();

		}
		
	}
	

	public void updateTramFrequency(int c) {
		
		try {
			controller.updateTramFrequency(c);
			
		}catch (Exception e) {
			e.printStackTrace();

		}
		
	}
	
	
}
