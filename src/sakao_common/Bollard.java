package sakao_common;

public class Bollard {

	private int idBollard, idZone;
	private boolean bollardState;
	private boolean isInstalled;
	private String ipaddress;
	private String macaddress;
	// private Zone zone;

	// private int idZone = zone.getIdZone();

	public Bollard() {

	}

	public Bollard(int idBollard, boolean bollardState, int idZone, boolean isInstalled, String ipaddress,
			String macaddress) {
		this.idBollard = idBollard;
		this.bollardState = bollardState;
		this.idZone = idZone;
		this.isInstalled = isInstalled;
		this.ipaddress = ipaddress;
		this.macaddress = macaddress;

	}

	public boolean getIsInstalled() {
		return isInstalled;
	}

	public void setInstalled(boolean isInstalled) {
		this.isInstalled = isInstalled;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public int getIdBollard() {
		return idBollard;
	}

	public void setIdBollard(int idBollard) {
		this.idBollard = idBollard;
	}

	public boolean getIsBollardState() {
		return bollardState;
	}

	public void setBollardState(boolean bollardState) {
		this.bollardState = bollardState;
	}

	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public String toString() {
		return "{\"idBollard\":\"" + this.getIdBollard() + "\"," 
				+ "\"bollardState\":\"" + this.getIsBollardState() + "\"," 
				+ "\"idZone\":\"" + this.getIdZone() + "\","
				+ "\"isInstalled\":\"" + this.getIsInstalled()
				+ "\"," + "\"ipaddress\":\"" + this.getIpaddress()
				+ "\"," + "\"macaddress\":\"" + this.getMacaddress()
				+ "\"}";
	}
	

	// public Zone getZone() {
//		return zone;
//	}
//
//	public void setZone(Zone zone) {
//		this.zone = zone;
//	}
//	@Override
//	public String toString() {
//		return "Bollard [idBollard=" + idBollard + ", BollardState=" + BollardState + ", idZone=" + idZone + "]";
//	}

	/*
	 * @Override public String toString() { return "Bollard [idBollard=" + idBollard
	 * + ", BollardState=" + BollardState + ", zone=" + zone + ", idZone=" + idZone
	 * + "]"; }
	 */
	/*
	 * @Override public String toString() { return "Bollard [idBollard=" + idBollard
	 * + ", idZone=" + idZone + ", BollardState=" + BollardState + "]"; }
	 */

	/*
	 * public String toString() { return "{" + "\"idBollard\":\"" + this.idBollard +
	 * "\"," + "\"zone\":\"" + this.zone + "\"," + "\"idZone\":\"" + this.idZone +
	 * "\"," + "\"BollardState\":\"" + this.BollardState + "\"" + "}"; }
	 * 
	 */

}
