package sakao_common;

import java.util.Date;

public class AlerteStatistics {
	private int idAlerteStatistics;
	private Date dateAjout;
	private int idSensor;

	public AlerteStatistics() {
	}

	public AlerteStatistics(int idAlerteStatistics, Date dateAjout, int idSensor) {
		this.idAlerteStatistics = idAlerteStatistics;
		this.dateAjout = dateAjout;
		this.idSensor = idSensor;
	}

	public int getIdAlerteStatistics() {
		return idAlerteStatistics;
	}

	public void setIdAlerteStatistics(int idAlerteStatistics) {
		this.idAlerteStatistics = idAlerteStatistics;
	}

	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	public int getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	@Override
	public String toString() {
		return "{\"idAlerteStatistics\":\"" + this.idAlerteStatistics + "\"," + "\"dateAjout\":\"" + this.dateAjout
				+ "\"," + "\"idSensor\":\"" + this.idSensor + "\"}";
	}

}
