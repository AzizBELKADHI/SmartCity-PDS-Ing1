package sakao_common;

public class TramLineC {
	private int id;
	private int idstation;
	private double coordx;
	private double coordy;
	
	
	public TramLineC() {}
	
	
	public TramLineC(int id, int idstation,double coordx, double coordy) {
		this.id = id;
		this.idstation = idstation;
		this.coordx = coordx;
		this.coordy = coordy;
	}
	
	
	public TramLineC(int idstation,double coordx,double coordy) {
		this.idstation = idstation;
		this.coordx = coordx;
		this.coordy = coordy;
	}
	
	
	public TramLineC(double coordx,double coordy) {
		this.coordx = coordx;
		this.coordy = coordy;
	}
	
	public String toString() {
		return 	"{\"id\":\"" + this.id + "\"," + "\"idstation\":\"" + this.getIdstation() + "\"," + 
	
				"\"coordx\":\"" + this.getCoordx() + "\"," + "\"coordy\":\"" + this.getCoordy() + "\"}";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdstation() {
		return idstation;
	}


	public void setIdstation(int idstation) {
		this.idstation = idstation;
	}


	public double getCoordx() {
		return coordx;
	}


	public void setCoordx(double coordx) {
		this.coordx = coordx;
	}


	public double getCoordy() {
		return coordy;
	}


	public void setCoordy(double coordy) {
		this.coordy = coordy;
	}
	

}



