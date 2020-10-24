package sakao_common;

public class SmartCity {
	private int id;
	private double heightkm;
	private double widthkm;
	private int budget;
	private int astationcost;
	private String name;
	
	
	
	
	public SmartCity() {}
	
	
	public SmartCity(double h, double w, int budget, int c, String name) {
		this.heightkm = h;
		this.widthkm = w;
		this.budget = budget;
		this.astationcost = c;
		this.name = name;
	}
	
	
	public SmartCity(int id,double h, double w, int budget, int c,String name) {
		this.id = id;
		this.heightkm = h;
		this.widthkm = w;
		this.budget = budget;
		this.astationcost = c;
		this.name = name;
	}

	public SmartCity(int id,double h, double w) {
		this.id = id;
		this.heightkm = h;
		this.widthkm = w;
	}
	
	
	public SmartCity(double h, double w) {
		this.heightkm = h;
		this.widthkm = w;
	}



	
	
	public String toString() {
		return 	"{\"id\":\"" + this.id + "\"," + "\"heightkm\":\"" + this.getHeightkm() + "\","
				+ "\"widthkm\":\"" + + this.getWidthkm() +"\","+ "\"budget\":\"" + this.getBudget()  +"\"," +  "\"astationcost\":\"" + this.astationcost  +
				"\"," + "\"name\":\"" + this.name +"\"}";
	}
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getHeightkm() {
		return this.heightkm;
	}
	public void setHeightkm(double height) {
		this.heightkm = height;
	}
	public double getWidthkm() {
		return this.widthkm;
	}
	public void setWidthkm(double width) {
		this.widthkm = width;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public int getAstationcost() {
		return astationcost;
	}
	public void setAstationcost(int astationcost) {
		this.astationcost = astationcost;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
