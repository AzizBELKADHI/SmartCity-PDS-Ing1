package sakao_server;

import java.sql.SQLException;
import java.util.ArrayList;



public class Crud_Service {

	private Crud_Controller controller;

	public Crud_Service() throws ClassNotFoundException {
		this.controller = new Crud_Controller();
	}

	/*public ArrayList<Student> showStudent() throws ClassNotFoundException {
		return controller.showStudent();
	}*/

/*	public boolean addOnTable(String target,ArrayList<String> list) {
		boolean b = false;
		try {
			///Student p = new Student(name, age);
			controller.addOnTable(target,list);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}*/

	/*
	 * public boolean deleteStudentByName(String name) { boolean b = false; try {
	 * controller.deleteStudentByName(name);
	 * 
	 * b = true; } catch (Exception e) { e.printStackTrace();
	 * 
	 * } return b; }
	 */


/*
	public boolean updateStudentAge(int id, int age) {
		boolean b = false;
		try {
			if (controller.existStudent(id) == null)
				System.out.println("Id inexistant");
			controller.updateStudentAge(id, age);

			b = true;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return b;
	}

	public boolean deleteAllStudent() {
		boolean b = false;
		try {
			controller.deleteAllStudent();

			b = true;
		} catch (Exception e) {
			System.out.println("all rows deleted");
		}
		return b;
	}

	public boolean updateStudentName(int id, String name) {
		boolean b = false;
		try {
			if (controller.existStudent(id) == null)
				System.out.println("Id inexistant");
			controller.updateStudentName(id, name);

			b = true;
		}

		catch (Exception e) {
			e.printStackTrace();

		}
		return b;
	}*/
	
	
	
	

	
	/////////////////////////////// SELECT ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
	
	public ArrayList<String> showCity() {
		return controller.showCity();
	}
	
	
	public ArrayList<String> showStations(){
		return controller.showStations();
	}
	
	
	public ArrayList<String> showTramLineA(){
		return controller.showTramLineA();
	}
	
	
	public ArrayList<String> showTramLineB(){
		return controller.showTramLineB();
	}
	
	
	public ArrayList<String> showTramLineC(){
		return controller.showTramLineC();
	}
	
	
	public ArrayList<String> showTramLineD(){
		return controller.showTramLineD();
	}
	
	
	
	public ArrayList<String> showCityDimension() {
		return controller.showCityDimension();
	}
	
	
	public ArrayList<String> showStationCoord(){
		return controller.showStationCoord();
	}
	
	
	public ArrayList<String> showTramLineACoord() {
		return controller.showTramLineACoord();
	}
	
	
	public ArrayList<String> showTramLineBCoord() {
		return controller.showTramLineBCoord();
	}
	
	
	public ArrayList<String> showTramLineCCoord() {
		return controller.showTramLineCCoord();
	}
	
	
	public ArrayList<String> showTramLineDCoord() {
		return controller.showTramLineDCoord();
	}
	
	
	
	
	
	
	/////////////////////////////// ADD ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
	public boolean addASmartCity(ArrayList<String> smartlist) {
		boolean b = false;
		try {
			controller.addASmartCity(smartlist);
			b = true;
		}
		catch(Exception e) {}
		return b;
	}
	
	
	public boolean addStation(ArrayList<String> stationlist) {
		boolean b = false;
		try {
			controller.addStation(stationlist);
			b = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	
	public boolean addTramLineAStation(ArrayList<String> smartlist) {
		boolean b = false;
		try {
			controller.addTramLineAStation(smartlist);
			b = true;
		}
		catch(Exception e) {}
		return b;
	}
	
	
	public boolean addTramLineBStation(ArrayList<String> smartlist) {
		boolean b = false;
		try {
			controller.addTramLineBStation(smartlist);
			b = true;
		}
		catch(Exception e) {}
		return b;
	}
	
	
	public boolean addTramLineCStation(ArrayList<String> smartlist) {
		boolean b = false;
		try {
			controller.addTramLineCStation(smartlist);
			b = true;
		}
		catch(Exception e) {}
		return b;
	}
	
	
	public boolean addTramLineDStation(ArrayList<String> smartlist) {
		boolean b = false;
		try {
			controller.addTramLineDStation(smartlist);
			b = true;
		}
		catch(Exception e) {}
		return b;
	}
	
	
	
	
	
	
	
	
	/////////////////////////////// DELETE ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
	
	public boolean deleteACity() throws SQLException, ClassNotFoundException {
		boolean b = false;
		try {
			controller.deleteACity();

			b = true;
		} catch (Exception e) {
		}
		return b;
	}
	
	
	public boolean deleteStations() throws SQLException, ClassNotFoundException {
		boolean b = false;
		try {
			controller.deleteStations();

			b = true;
		} catch (Exception e) {
		}
		return b;
	}
	

	
	
	public boolean deleteTramLineA() throws SQLException, ClassNotFoundException {
		boolean b = false;
		try {
			controller.deleteTramLineA();

			b = true;
		} catch (Exception e) {
		}
		return b;
	}
	
	
	public boolean deleteTramLineB() throws SQLException, ClassNotFoundException {
		boolean b = false;
		try {
			controller.deleteTramLineB();

			b = true;
		} catch (Exception e) {
		}
		return b;
	}
		public boolean deleteTramLineC() throws SQLException, ClassNotFoundException {
		boolean b = false;
		try {
			controller.deleteTramLineC();

			b = true;
		} catch (Exception e) {
		}
		return b;
	}
		public boolean deleteTramLineD() throws SQLException, ClassNotFoundException {
		boolean b = false;
		try {
			controller.deleteTramLineD();

			b = true;
		} catch (Exception e) {
		}
		return b;
	}
	
	
	
	
	
	
	
	

	public Crud_Controller getController() {
		return controller;
	}

	public void setController(Crud_Controller controller) {
		this.controller = controller;
	}

}
