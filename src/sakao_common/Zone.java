package sakao_common;

public class Zone {
	private int idZone;
	private String nextToTheZone;
	private String location;
	private int thresholdBeta;
	private boolean alerteState; // a revoir
	private int idCity;

	public Zone() {
	}

	public Zone(int idZone, String nextToTheZone, String location, int thresholdBeta) {
		this.idZone = idZone;
		this.nextToTheZone = nextToTheZone;
		this.location = location;
		this.thresholdBeta = thresholdBeta;
	}

	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public String getNextToTheZone() {
		return nextToTheZone;
	}

	public void setNextToTheZone(String nextToTheZone) {
		this.nextToTheZone = nextToTheZone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getThresholdBeta() {
		return thresholdBeta;
	}

	public void setThresholdBeta(int thresholdBeta) {
		this.thresholdBeta = thresholdBeta;
	}

	public boolean isAlerteState() {
		return alerteState;
	}

	public void setAlerteState(boolean alerteState) {
		this.alerteState = alerteState;
	}

	public int getIdCity() {
		return idCity;
	}

	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}

	@Override
	public String toString() {
		return "{\"idZone\":\"" + this.idZone + "\"," + "\"nextToTheZone\":\"" + this.nextToTheZone + "\","
				+ "\"location\":\"" + this.location + "\"," + "\"thresholdBeta\":\"" + this.thresholdBeta + "\","
				+ "\"alerteState\":\"" + this.alerteState + "\"," + "\"idCity\":\"" + this.idCity + "\"}";
	}

}
