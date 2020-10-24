package sakao_common;

import java.util.ArrayList;
import java.util.Iterator;

public class smartcity2 {
	private int id;
	private double heightkm;
	private double widthkm;
	private int budget;
	private int astationcost;
	private String name;

	private int numberVehicles; // In circulation
	private int maxNumberVehicles;
	private int tramFrequency;
	
	public smartcity2() {
	}

	public smartcity2(double h, double w, int budget, int c, String name) {
		this.heightkm = h;
		this.widthkm = w;
		this.budget = budget;
		this.astationcost = c;
		this.name = name;
	}

	public smartcity2(int id, double h, double w, int budget, int c, String name) {
		this.id = id;
		this.heightkm = h;
		this.widthkm = w;
		this.budget = budget;
		this.astationcost = c;
		this.name = name;
	}

	public smartcity2(double h, double w) {
		this.heightkm = h;
		this.widthkm = w;
	}

	public smartcity2(int id, double HeightKM, double WidthKM, int Budget, int AStationCost, int numberVehicles,
			int maxNumberVehicles, int tramFrequency, String name) {
///MIS A JOUR
		this.id = id;
		this.heightkm = HeightKM;
		this.widthkm = WidthKM;
		this.budget = Budget;
		this.astationcost = AStationCost;
		this.maxNumberVehicles = maxNumberVehicles;
		this.tramFrequency = tramFrequency;
		this.numberVehicles = numberVehicles;
		this.name = name;

	}

	public String toString() {
		return "{\"id\":\"" + this.id + "\"," + "\"heightkm\":\"" + this.getHeightkm() + "\"," + "\"widthkm\":\""
				+ this.getWidthkm() + "\"," + "\"budget\":\"" + this.getBudget() + "\"," + "\"astationcost\":\""
				+ this.astationcost + "\"," + "\"maxnumberofvehicles\":\"" + this.getMaxNumberVehicles() + "\","
				+ "\"tramfrequency\":\"" + this.getTramFrequency() + "\"," + "\"numbervehicles\":\""
				+ this.getNumberVehicles() + "\"," + "\"name\":\"" + this.name + "\"}";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getHeightkm() {
		return this.heightkm;
	}

	public void setHeightkm(double height) {
		this.heightkm = height;
	}

	public double getWidthkm() {
		return this.widthkm;
	}

	public void setWidthkm(double width) {
		this.widthkm = width;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getAstationcost() {
		return astationcost;
	}

	public void setAstationcost(int astationcost) {
		this.astationcost = astationcost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberVehicles() {
		return numberVehicles;
	}

	public void setNumberVehicles(int numberVehicles) {
		numberVehicles = numberVehicles;
	}

	public int getMaxNumberVehicles() {
		return maxNumberVehicles;
	}

	public void setMaxNumberVehicles(int maxNumberVehicles) {
		maxNumberVehicles = maxNumberVehicles;
	}

	public int getTramFrequency() {
		return tramFrequency;
	}

	public void setTramFrequency(int tramFrequency) {
		tramFrequency = tramFrequency;
	}

	public int CityThresholdPolution(ArrayList<Zone> listzone) {

		int cityThresholdPolution = 0;

		for (Zone zone : listzone) {

			cityThresholdPolution += zone.getThresholdBeta();

		}

		return cityThresholdPolution;

	}

	public int VehicleInCirculation(ArrayList<VehicleSensor> Sensorlist) {

		int inV = 0;
		int outV = 0;
	
		Iterator<VehicleSensor> it = Sensorlist.iterator();
		
		while(it.hasNext()) {
			VehicleSensor v = it.next();
			

			if (v.getSensorTypeIO().equals("Input")) {

				inV += v.getNumberOfVehicle();

			} else if (v.getSensorTypeIO().equals("Output")) {

				outV += v.getNumberOfVehicle();

			}
			
		}
		int NbVehicleInCirculation = (inV + this.numberVehicles) - outV;
		return NbVehicleInCirculation;
	}
//		for (VehicleSensor sensor : Sensorlist) {
//			
//			
//			System.out.println(sensor.getSensorTypeIO());
//			
//			
//			
//			if (sensor.getSensorTypeIO() =="Input") {
//
//				inV += sensor.getNumberOfVehicle();
//
//			} else if (sensor.getSensorTypeIO() == "Output") {
//
//				outV += sensor.getNumberOfVehicle();
//
//			}
//
//		}



	public boolean CheckThresholdNbMaxVehicles(int NbVehicleInCirculation) {

		if (NbVehicleInCirculation >= this.maxNumberVehicles) {
			return true;
		} else {
			return false;
		}

	}

	public boolean CheckThresholdBeta(int cityThresholdPolution, int CityPolution) {

		if (CityPolution >= cityThresholdPolution) {
			return true;
		} else {
			return false;
		}

	}
}
