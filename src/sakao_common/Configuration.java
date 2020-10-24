package sakao_common;

public class Configuration {
	private int idConfiguration;
	private int frequency;
	private int idZone;

	public Configuration() {
	}

	public Configuration(int idConfiguration, int frequency, int idZone) {
		this.idConfiguration = idConfiguration;

		this.frequency = frequency;

		this.idZone = idZone;
	}

	public int getIdConfiguration() {
		return idConfiguration;
	}

	public void setIdConfiguration(int idConfiguration) {
		this.idConfiguration = idConfiguration;
	}

	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
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
		return "{\"idConfiguration\":\"" + this.idConfiguration + "\"," + "\"frequency\":\"" + this.frequency + "\","
				+ "\"idZone\":\"" + this.idZone + "\"}";
	}

}
