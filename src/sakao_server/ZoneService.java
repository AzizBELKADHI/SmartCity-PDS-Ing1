package sakao_server;

import java.util.ArrayList;

public class ZoneService {
	private Crud_Controller controller;

	public ZoneService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<String> showAllZone() throws ClassNotFoundException {
		return controller.showAllZone();
	}

	public ArrayList<String> showZoneById(int id) throws ClassNotFoundException {
		return controller.showZoneById(id);
	}

	public boolean addOnAlerteStatistics(String target, ArrayList<String> list) {
		boolean b = false;
		try {
			controller.addOnAlerteStatistics(target, list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}
	
	public boolean updateZoneBeta(int id, int thresholdbeta) {
		boolean b = false;
		try {
			controller.updateZoneBeta(id, thresholdbeta);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean updateZone(int id, boolean alertstate) {
		boolean b = false;
		try {
			controller.updateZone(id, alertstate);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	

	public boolean addOnZone(String target, ArrayList<String> list) {
		boolean b = false;
		try {
			controller.addOnZone(target, list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}
	

}
