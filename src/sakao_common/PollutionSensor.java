package sakao_common;

public class PollutionSensor {

	private int idPollutionSensor, idSensor, idConfiguration;
	private double betaAverage;

	public PollutionSensor() {
	}

	public PollutionSensor(int idPollutionSensor, int idSensor, int idConfiguration, double betaAverage) {
		super();
		this.idPollutionSensor = idPollutionSensor;
		this.idSensor = idSensor;
		this.idConfiguration = idConfiguration;
		this.betaAverage = betaAverage;
	}

	public int getIdPollutionSensor() {
		return idPollutionSensor;
	}

	public void setIdPollutionSensor(int idPollutionSensor) {
		this.idPollutionSensor = idPollutionSensor;
	}

	public int getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(int idSensor) {
		this.idSensor = idSensor;
	}

	public int getIdConfiguration() {
		return idConfiguration;
	}

	public void setIdConfiguration(int idConfiguration) {
		this.idConfiguration = idConfiguration;
	}

	public double getBetaAverage() {
		return betaAverage;
	}

	public void setBetaAverage(double betaAverage) {
		this.betaAverage = betaAverage;
	}

	@Override
	public String toString() {
		return "{\"idPollutionSensor\":\"" + this.idPollutionSensor + "\"," + "\"idSensor\":\"" + this.idSensor + "\","
				+ "\"idConfiguration\":\"" + this.idConfiguration + "\"," + "\"betaAverage\":\"" + this.betaAverage
				+ "\"}";
	}

}
