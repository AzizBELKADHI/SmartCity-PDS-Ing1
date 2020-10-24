package sakao_common;

public class Sensor {
	/*
	 * public enum State { Work, Fail };
	 * 
	 * public enum Type { Pollution, Weather, Vehicle };
	 */
	private int idSensor;
	private String sensorState;
	private String sensorType;
	private int idZone;
	private String ipAddress;
	private String macAddress;
	private boolean isInstalled;

	public Sensor() {
	}

	public Sensor(int idSensor, String sensorState, String sensorType, int idZone, String ipAddress, String macAddress,
			boolean isInstalled) {
		this.idSensor = idSensor;
		this.sensorState = sensorState;
		this.sensorType = sensorType;
		this.idZone = idZone;
		this.ipAddress = ipAddress;
		this.macAddress = macAddress;
		this.isInstalled = isInstalled;
	}

	public int getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public String getSensorState() {
		return sensorState;
	}

	public void setSensorState(String sensorState) {
		this.sensorState = sensorState;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public boolean getIsInstalled() {
		return isInstalled;
	}

	public void setIsInstalled(boolean isInstalled) {
		this.isInstalled = isInstalled;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{\"idSensor\":\"" + this.idSensor + "\"," + "\"idZone\":\"" + this.idZone + "\"," + "\"sensorState\":\""
				+ this.sensorState + "\"," + "\"sensorType\":\"" + this.sensorType + "\"," + "\"ipAddress\":\""
				+ this.ipAddress + "\"," + "\"macAddress\":\"" + this.macAddress + "\"," + "\"isInstalled\":\""
				+ this.isInstalled + "\"}";
	}

}
