package sakao_server;

import java.util.ArrayList;

import sakao_common.Bollard;
import sakao_common.VehicleSensor;

public class BollardService {

	private Crud_Controller controller;

	public BollardService() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	public ArrayList<Bollard> GenerateAllBollards() throws ClassNotFoundException {
		return controller.GenerateAllBollards();
	}

	public ArrayList<String> showAllBollards() throws ClassNotFoundException {
		return controller.showAllBollards();
	}

	public boolean addBollard(String target, ArrayList<String> list) {
		boolean b = false;
		try {
			/// Student p = new Student(name, age);
			controller.addOnBollard(target, list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}

	public boolean UpdateBollardIsInstalled(String target, ArrayList<String> list, ArrayList<Bollard> listBollardObj) {
		boolean b = false;
		try {
			/// Student p = new Student(name, age);
			controller.UpdateBollardIsInstalled(target, list, listBollardObj);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}
	
	
	public boolean updateBollard(int id, boolean install) {
		boolean b = false;
		try {
			controller.updateBollard(id, install);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean Updatetrue(ArrayList<Bollard> listBollard) {
		boolean b = false;
		try {

			/// Student p = new Student(name, age);
			controller.UpdateBollardTrue(listBollard);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;

	}

	public boolean Updatefalse(ArrayList<Bollard> listBollard) {
		boolean b = false;
		try {

			/// Student p = new Student(name, age);
			controller.UpdateBollardFalse(listBollard);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;

	}
	
	public boolean deleteBollardById(int ID) {
		boolean b = false;
		try {
			controller.deleteBollardById(ID);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

}
