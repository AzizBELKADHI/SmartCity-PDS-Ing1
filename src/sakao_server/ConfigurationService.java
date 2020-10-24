package sakao_server;

import java.util.ArrayList;

import sakao_common.Configuration;

public class ConfigurationService {

	private Crud_Controller controller;

	public ConfigurationService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<String> showAllConfiguration() throws ClassNotFoundException {
		return controller.showAllConfiguration();
	}

	public boolean addOnConfiguration(String target, ArrayList<String> list) {
		boolean b = false;
		try {
			controller.addOnConfiguration(target, list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

}
