package Tram_UC_Test;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

import sakao_client.TramStationComputer;
import sakao_client_insert.TablesToBeInserted;
import sakao_common.Request;

public class TramStationRepartitorAlgoTest {
	
/*
 * Alain SARKISIAN 
 * Aims to show calculations done during the algorithm.
 * 
 * A prepared list is the points that we expect to have at the and of the algorithm if everything is correctly calculate
 * 
 * Then we compare these lists in order to see if coordinates are the same and provue that calculation are 
 * write.
 */
	
 
	TramStationComputer computer = new TramStationComputer();

	public TramStationRepartitorAlgoTest() throws IOException {

	}

	public void computeTramStation() throws IOException {
		TablesToBeInserted table = new TablesToBeInserted();
		String s = table.readFileSmartCityRepartitorAlgo();///// STACK THE FILE'S VALUES INTO s
		Request reqSmartCity = new ObjectMapper().readValue(s, Request.class);///// JSON TO OBJEC
		ArrayList<String> listCityInfo = reqSmartCity.getList();///// STACK THE REQUEST LIST INTO list IN ORDER TO READ
																///// VARIABLES VALUES
		int length = 0;
		


		///////////////////////////////////////////////////////////////////// EXPECTED
		///////////////////////////////////////////////////////////////////// VALUES
		Point2D.Double p1 = new Point2D.Double(-3.0, 2.571428571428571);
		Point2D.Double p2 = new Point2D.Double(3.0, 2.571428571428571);
		Point2D.Double p3 = new Point2D.Double(-4.0, 1.1428571428571426);
		Point2D.Double p4 = new Point2D.Double(4.0, 1.1428571428571426);
		Point2D.Double p5 = new Point2D.Double(-3.0, -0.28571428571428603);
		Point2D.Double p6 = new Point2D.Double(3.0, -0.28571428571428603);
		Point2D.Double p7 = new Point2D.Double(-4.0, -1.7142857142857146);
		Point2D.Double p8 = new Point2D.Double(4.0, -1.7142857142857146);
		Point2D.Double p9 = new Point2D.Double(-3.0, -3.1428571428571432);
		Point2D.Double p10 = new Point2D.Double(3.0, -3.1428571428571432);
		Point2D.Double p11 = new Point2D.Double(0.0, 0.0);
		Point2D.Double p12 = new Point2D.Double(0.0, 4.0);
		Point2D.Double p13 = new Point2D.Double(0.0, 1.5);
		Point2D.Double p14 = new Point2D.Double(0.0, -1.0);
		Point2D.Double p15 = new Point2D.Double(0.0, -3.5);

		ArrayList<Point2D.Double> listPrepared = new ArrayList<Point2D.Double>();
		listPrepared.add(p1);
		listPrepared.add(p2);
		listPrepared.add(p3);
		listPrepared.add(p4);
		listPrepared.add(p5);
		listPrepared.add(p6);
		listPrepared.add(p7);
		listPrepared.add(p8);
		listPrepared.add(p9);
		listPrepared.add(p10);
		listPrepared.add(p11);
		listPrepared.add(p12);
		listPrepared.add(p13);
		listPrepared.add(p14);
		listPrepared.add(p15);
///////////////////////////////////////////////////////////////

		///// USE THE FILE'S VALUE TO LAUNCH ALGORITHMS
		double w = Double.parseDouble(listCityInfo.get(2));
		double h = Double.parseDouble(listCityInfo.get(4));
		int b = Integer.parseInt(listCityInfo.get(6));
		int c = Integer.parseInt(listCityInfo.get(8));

		/////////////////////////////////////////////////


		///// LAUNCH THE REPARTITOR ALGORITHM
		computer.StationRepartitorAlgo(w, h, b, c);

		///// STACK THE RESULTS OF THE ALGORITHM IN THIS LIST IN ORDER TO COMPARE
		ArrayList<Point2D.Double> listResult = new ArrayList<Point2D.Double>();
		listResult = computer.getGraphPoints();
		System.out.println("//////////////////////////////////////////////////////////");
		System.out.println("THE TEST BEGIN LET'S CHECK IF THE ALGORITHM GIVES EXPECTED VALUES");
		System.out.println("//////////////////////////////////////////////////////////");
		System.out.println("");
		System.out.println("List of prepared points that we expect to have after the algorithm launch");
		System.out.println("Prepared list : " + listPrepared.toString());
		
		if (listResult.size() == listPrepared.size()) {///// FIRST STEP COMPARE THE SIZE
			length = listPrepared.size();
			System.out.println("_______________");
			System.out.println("First step : The number of points expected is correct !");
			System.out.println("Each one have " + length + " points !");
			System.out.println("Expected results : 15");
			System.out.println("_______________");
			System.out.println("");

			///// NEXT STEP COMPARE THE VALUES IN EACH LIST
			for (int i = 0; i < length; i++) {
				if (listPrepared.get(i).equals(listResult.get(i))) {
					System.out.println("_______________");
					System.out.println("The algorithm generates expected points : n° " + (i + 1));
					System.out.println("Prepared list expected results : " + listPrepared.get(i));
					System.out.println("Results of the algorithm : " + listResult.get(i));
					System.out.println("_______________");
					System.out.println("");
				} else {
					System.out.println("Fail : There is a problem, the algorithm did not generate expected points");

				}
			}
		} else {
			System.out.println("Fail : The number of points expected is not correct !");
		}
		
		System.out.println("");
		System.out.println("//////////////////////////////////////////////////////////");
		System.out.println("THE TEST ENDS");
		System.out.println("//////////////////////////////////////////////////////////");

	}

	public static void main(String[] args) throws IOException {
		TramStationRepartitorAlgoTest tramstation = new TramStationRepartitorAlgoTest();
		tramstation.computeTramStation();

	}

}
