package sakao_common;

public class Station {
	private int id;
	private String typestation;
	private boolean isbuilt;
	private double coordx;
	private double coordy;
	private int idzone;
	
	
	public Station() {}
	
	
	public Station(String type,double x, double y, boolean b) {
		this.typestation = type;
		this.isbuilt = b;
		this.coordx = x;
		this.coordy = y;
	}
	
	
	public Station(double x, double y) {
		this.coordx = x;
		this.coordy = y;
	}
	
	
	public Station(int id,String type, double x, double y,boolean b,int idzone) {
		this.id = id;
		this.typestation = type;
		this.isbuilt = b;
		this.coordx = x;
		this.coordy = y;
		this.setIdzone(idzone);
	}
	public Station(int id,String type,boolean b, double x, double y) {
		this.id = id;
		this.typestation = type;
		this.isbuilt = b;
		this.coordx = x;
		this.coordy = y;	
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

	
	public String toString() {
		return 	"{\"id\":\"" + this.id + "\"," + "\"typestation\":\"" + this.typestation + "\","
				+ "\"isbuilt\":\"" + this.getIsbuilt() +"\","+ "\"coordx\":\"" + this.getCoordx() +"\"," +  "\"coordy\":\"" + this.getCoordy() + "\"}";
	}
	
	public String getTypestation() {
		return typestation;
	}
	public void setTypestation(String typestation) {
		this.typestation = typestation;
	}
	public boolean getIsbuilt() {
		return isbuilt;
	}
	public void setIsbuilt(boolean isbuilt) {
		this.isbuilt = isbuilt;
	}
	public double getCoordx() {
		return coordx;
	}
	public void setCoordX(double coordX) {
		this.coordx = coordX;
	}
	public double getCoordy() {
		return coordy;
	}
	public void setCoordY(double coordY) {
		this.coordy = coordY;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdzone() {
		return idzone;
	}


	public void setIdzone(int idzone) {
		this.idzone = idzone;
	}
	
	
}
