package sakao_common;

import java.sql.Date;;

public class VehicleSensor extends Sensor {

	private int idSensor = this.getIdSensor() ;
	private String sensorState = this.getSensorState() ;
	private String sensorType = this.getSensorType() ;
	private String sensorTypeIO;
	private int numberOfVehicle;
	private int idZone = this.getIdZone();
	private String ipAddress =this.getIpAddress() ;
	private String macAddress = this.getMacAddress();
	private boolean isInstalled =this.getIsInstalled() ;

	
	public VehicleSensor() {
		
	};
	public VehicleSensor(int idSensor, String SensorState, String SensorType, String sensorTypeIO, int numberOfVehicle,
			/* int IDBollard, Date DateOfConfiguration, */ int idZone, String ipAddress, String macAddress,
			boolean isInstalled) {
		super(idSensor, SensorState, SensorType, idZone, ipAddress, macAddress, isInstalled);
		this.sensorTypeIO = sensorTypeIO;
		this.numberOfVehicle = numberOfVehicle;

	}



	public String getSensorTypeIO() {
		return sensorTypeIO;
	}
	public void setSensorTypeIO(String sensorTypeIO) {
		this.sensorTypeIO = sensorTypeIO;
	}


	public int getNumberOfVehicle() {
		return numberOfVehicle;
	}
	public void setNumberOfVehicle(int numberOfVehicle) {
		this.numberOfVehicle = numberOfVehicle;
	}
	



	

	public String toString() {
		return "{\"idSensor\":\"" + this.idSensor + "\"," + "\"sensorState\":\"" + this.sensorState + "\","
				+ "\"sensorType\":\"" + this.sensorType + "\"," + "\"sensorTypeIO\":\"" + this.sensorTypeIO + "\","
				+ "\"numberOfVehicle\":\"" + this.numberOfVehicle + "\"," + "\"idZone\":\"" + this.idZone + "\","
				+ "\"ipAddress\":\"" + this.ipAddress + "\"," + "\"macAddress\":\"" + this.macAddress + "\","
				+ "\"isInstalled\":\"" + this.isInstalled + "\"}";
	}


	/*
	 * public Bollard getBollard() { return Bollard; }
	 * 
	 * public void setBollard(Bollard bollard) { Bollard = bollard; }
	 * 
	 * public int getIdBollard() { return idBollard; }
	 * 
	 * public void setIdBollard(int idBollard) { this.idBollard = idBollard; }
	 */

	/*
	 * public Date getDateOfConfiguration() { return DateOfConfiguration; }
	 * 
	 * public void setDateOfConfiguration(Date dateOfConfiguration) {
	 * DateOfConfiguration = dateOfConfiguration; }
	 */

}
