package sakao_client;
/* Oumaima Code*/
public class CalculS {
	private double vehicalPollution;// = 126 ;
	private double humanPollution = 2;
	private double tramPollution;// =43 ;
	private double cyclistPollution;// = 16 ;
	private double walkerPollution=4 ;
	public CalculS() {
	
	}
	
	public CalculS(double vehicalPollution,double tramPollution, double cyclistPollution) {
		this.vehicalPollution = vehicalPollution;
		this.tramPollution = tramPollution;
		this.cyclistPollution = cyclistPollution;
		
	}
	
	
///////// perimeter calculation 
public double perimetre(double heightkm, double widthkm) {
	return 2*Math.PI*heightkm;
}
///////  average distance
public double CercleAverageDistanceV (double heightkm) {
	return 2*Math.PI*heightkm;
}
public double CercleAverageDistanceC (double heightkm) {
	return 2*Math.PI*heightkm/3;
}
public double CercleAverageDistanceT (double heightkm) {
	return  4*Math.PI*heightkm;
}
public double CercleAverageDistanceW (double heightkm) {
	return  2*Math.PI*heightkm/4;
}

///////estimated threshold ////
public double estimatedThreshold (int nbreVehicule,double heightkm, int nbreCycliste, int utilisateurTram, int pieton) {
	double V1=this.vehicalPollution*this.CercleAverageDistanceV(heightkm)*nbreVehicule;
	double V2= this.tramPollution *this.CercleAverageDistanceT(heightkm)*utilisateurTram;
	double V3 =this.cyclistPollution *this.CercleAverageDistanceC(heightkm)*nbreCycliste;
	double V4= this.walkerPollution *this.CercleAverageDistanceW(heightkm)*pieton;
	return ((V1 +V2+V4+V3)/1000);
}


}
