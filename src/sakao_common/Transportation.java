package sakao_common;

public class Transportation {
	private int idtransportation;
	private String typeoftransport;
	private int dailytransportusercount;
	private double averageofco2releasedbytransport;

	public Transportation() {
	}

	public Transportation(int idtransportation, String typeoftransport, int dailytransportusercount,
			double averageofco2releasedbytransport) {
		this.idtransportation = idtransportation;
		this.typeoftransport = typeoftransport;
		this.dailytransportusercount = dailytransportusercount;
		this.averageofco2releasedbytransport = averageofco2releasedbytransport;
	}

	public int getIdtransportation() {
		return idtransportation;
	}

	public void setIdtransportation(int idtransportation) {
		this.idtransportation = idtransportation;
	}

	public String getTypeoftransport() {
		return typeoftransport;
	}

	public void setTypeoftransport(String typeoftransport) {
		this.typeoftransport = typeoftransport;
	}

	public int getDailytransportusercount() {
		return dailytransportusercount;
	}

	public void setDailytransportusercount(int dailytransportusercount) {
		this.dailytransportusercount = dailytransportusercount;
	}

	public double getAverageofco2releasedbytransport() {
		return averageofco2releasedbytransport;
	}

	public void setAverageofco2releasedbytransport(double averageofco2releasedbytransport) {
		this.averageofco2releasedbytransport = averageofco2releasedbytransport;
	}


		
		public String toString() {
			return 	"{\"idtransportation\":\"" + this.getIdtransportation() + "\"," + "\"typeoftransport\":\"" + this.getTypeoftransport() + "\","
					+ "\"dailytransportusercount\":\"" + + this.getDailytransportusercount() +"\","+ "\"averageofco2releasedbytransport\":\"" + this.getAverageofco2releasedbytransport() +
					
					"\"}";
		}
	}


