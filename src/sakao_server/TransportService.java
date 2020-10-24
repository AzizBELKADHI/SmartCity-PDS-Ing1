package sakao_server;

import java.util.ArrayList;

public class TransportService {
	private Crud_Controller controller;

	public TransportService() throws ClassNotFoundException {

		this.controller = new Crud_Controller();

	}

	public ArrayList<String> showAllTransport() throws ClassNotFoundException {
		return controller.showAllTransport();
	}
}
