package CarbonTest;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

import sakao_client.CalculS;
import sakao_client_insert.TablesToBeInserted;
import sakao_common.SmartCity;


public class CarbonTest2 {

	//	double perimetre=carbonTest.getPerimetreValueOfSmartCity(sc);
	
		public SmartCity getSmartCity() {
			try {				
				TablesToBeInserted table = new TablesToBeInserted();
				String s = table.readFileCarbonEstimateConf();///// STACK THE FILE'S VALUES INTO s			
				SmartCity sc=new ObjectMapper().readValue(s, SmartCity.class);		
				return sc;
			}
			catch(Exception e) {
				System.out.println("erreur"+e);
				e.printStackTrace();
			}
			
			return null;
		}
		 public static int somme(int a, int b, int c,int d)
		    {
		        return a+b+c+d;
		    }
		public double getPerimetreValueOfSmartCity(SmartCity sc) {
			return sc.getHeightkm();
			
		}
		
    public static void main(String[] args)  {

	int nbreVehiculeD=4000;
	int nbreTramD=2000;
	int nbrePietonD=3000;
	int nbreCyclisteD=1000;
	int nbrePopulation= 10000;
	

	int nbreVehiculeE=2000;
	int nbreTramE=1000;
	int nbrePietonE=5000;
	int nbreCyclisteE=500;
	
	CarbonTest2 carbonTest = new CarbonTest2();
	System.out.println("**********************************");
	System.out.println("  THE BEGINNING OF THE TEST 2 ");
	System.out.println("**********************************");
	System.out.println("IN THIS TEST WE WILL PROPOSE A GOOD CURRENT TRAVEL POLICY");
	System.out.println("EXPECTED RESULT : the proposed travel policy is better than the current travel policy ");
	CalculS calcul = new CalculS();
	SmartCity sc = carbonTest.getSmartCity();

	double perimetre=carbonTest.getPerimetreValueOfSmartCity(sc);
	
	if (somme(nbreVehiculeE, nbreCyclisteE, nbreTramE, nbrePietonE ) <  nbrePopulation) {
		 System.out.println("verified computational condition");
	}
 else {
		System.out.println("unverified computational condition");
		////System.out.println(" condition :" + somme(nbreVehiculeE, nbreCyclisteE, nbreTramE, nbrePietonE ));
	 
 }
 double calculD =calcul.estimatedThreshold (nbreVehiculeD,perimetre , nbreCyclisteD, nbreTramD, nbrePietonD);
double calculE =calcul.estimatedThreshold (nbreVehiculeE,perimetre , nbreCyclisteE, nbreTramE, nbrePietonE);
//double calculE =calcul.estimatedThreshold (nbreVehiculeE,perimetre , nbreCyclisteE, nbreTramE, nbrePietonE);
System.out.println("the proposed travel policy" );
System.out.println("perimetre : "+perimetre );
System.out.println("The number of vehicles :" + nbreVehiculeE);
 System.out.println("The number of vehicles :" + nbreVehiculeE);
 System.out.println("The number of cyclists :" + nbreCyclisteE);
 System.out.println("the number of pedestrians :" + nbrePietonE);
 System.out.println("the number of tram users:" + nbreTramE);
 System.out.println("pollution threshold : "+ calculE);
 System.out.println("**********************************");
 System.out.println("**********************************");
 System.out.println("the current travel policy" );
 System.out.println("The number of vehicles :" + nbreVehiculeD);
 System.out.println("The number of cyclists :" + nbreCyclisteD);
 System.out.println("the number of pedestrians :" + nbrePietonD);
 System.out.println("the number of tram users:" + nbreTramD); 
 System.out.println("the current pollution threshold :" + calculD);

	if (calculE< calculD) {
		System.out.println("Conclusion :the proposed travel policy is better than the current travel policy");
 }
 else {
		System.out.println("Conclusion :the current travel policy is better than the proposed travel policy");
	 
 }
	System.out.println(" FINAL ");
	System.out.println(" ********************************************* ");



} }
