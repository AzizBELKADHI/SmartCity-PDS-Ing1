package sakao_client;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class TramStationComputer{//////////////////////////////////////// CONTAINS THE TWO ALGORITHMS
/*
 * 
 * Alain SARKISIAN 
 
 * The goal of this class is to place stations of tramway on a city taking account of four parameters which are the width, the height, the bidget of the city and also
 * the price of a station
 * 
 * This class is mainly composed of two algorithms :
 * 					The repartitor algorithm is the heart of this functionnality because the conduct line is to place as good as possible stations in order to join them
 * 					as easier as possible with the second algorithm which estimates the link between stations.
 * 
 *
 */
	private static final long serialVersionUID = 8137695111058290034L;

	private double widthKM;/// Information from user
	private double heightKM ;/// Information from user
	private double widthPX;
	private double heightPX;
	private ArrayList<Point2D.Double> graphPoints;
	private int CityBudget;/// Information from user
	private int aStationCost;/// Information from user
	private int MaxStation;
	private int rest;
	private int maxDiviser;
	private int debt;

	private ArrayList<Point2D.Double> graphNorthToSouth; /////LINE A
	private ArrayList<Point2D.Double> graphWestToEast;///// LINE B
	private ArrayList<Point2D.Double> graphNorthWestToSouthEast; /////LINE D
	private ArrayList<Point2D.Double> graphNorthEastToSouthWest;/////LINE C
	
	

/////////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Test if the point is in the circle Here in the circle also means on the
	 * circle
	 */

	public boolean isInTheCircle(double x, double y) {
		boolean b;
		double distance = (x*x) + (y*y);
		double r = this.widthKM;
		if (distance <= (r*r)) {
			b = true;
		} else {
			b = false;

		}

		return b;
	}

////////////////////////////////////////////////////////////////////////////
	/*
	 * Test if the point is in the ellipse Here in the ellipse also means on the
	 * ellipse
	 */

	public boolean isInTheEllipse(double x, double y) {
		boolean b;
		double nx = x / this.widthKM;
		double ny = y / this.heightKM;
		double r = (nx * nx) + (ny * ny);
		System.out.println(r);
		if (r <= 1) {
			b = true;
		} else {
			b = false;
		}

		return b;
	}

////////////////////////////////////////////////////////////////////////////
	/*
	 * Gives the biggest multiple of MaxStation in order to know how to organize
	 * stations on the map. It refers to the number of line
	 */

	public int StationDiviser(int MaxStation) {
		if (MaxStation == 1 || MaxStation == 2) {
			maxDiviser = 1;
		} else if (MaxStation == 3) {
			maxDiviser = 2;
			rest = 1;
		} else {
			for (int i = 1; i < MaxStation; i++) {
				if (MaxStation % i == 0 && maxDiviser <= i && maxDiviser < MaxStation) {
					maxDiviser = i;
				}

			} 

		}
		System.out.println("_____");
		System.out.println("MaxStation : " + MaxStation);
		System.out.println("MaxDiviser : " + maxDiviser);
		System.out.println("rest : " + this.rest);
		System.out.println("_____");

		return maxDiviser;
	}

	public Point2D.Double xSymetric(Point2D.Double p) {
		Point2D.Double symX;
		if (p.x == 0.0 && p.y == 0.0) {
			symX = new Point2D.Double(0.0, 0.0);
		}

		else {
			double x = p.x;
			double y = -(p.y);
			symX = new Point2D.Double(x, y);
		}
		return symX;
	}

	public Point2D.Double ySymetric(Point2D.Double p) {
		Point2D.Double symY;
		if (p.x == 0.0 && p.y == 0.0) {
			symY = new Point2D.Double(0.0, 0.0);
		} else {
			double x = -(p.x);
			double y = p.y;
			symY = new Point2D.Double(x, y);
		}
		return symY;
	}

	public Point2D.Double OSymetric(Point2D.Double p) {
		Point2D.Double symO;
		if (p.x == 0.0 && p.y == 0.0) {
			symO = new Point2D.Double(0.0, 0.0);
		} else {
			double x = -(p.x);
			double y = -(p.y);
			symO = new Point2D.Double(x, y);
		}
		return symO;
	}

	public Point2D.Double xMidSymetric(Point2D.Double p) {
		Point2D.Double symmidx;
		if (p.x == 0.0 && p.y == 0.0) {
			symmidx = new Point2D.Double(0.0, 0.0);
		}

		else {
			double x = 0;
			double y = p.y;
			symmidx = new Point2D.Double(x, y);
		}
		return symmidx;
	}

	public Point2D.Double yMidSymetric(Point2D.Double p) {
		double x = p.x;
		double y = 0;

		Point2D.Double symmidy = new Point2D.Double(x, y);

		return symmidy;
	}

	public boolean pontExist(Point2D.Double p) {
		boolean b = false;
		for (Point2D.Double point : this.graphPoints) {
			if ((point.x == p.x) && (point.y == p.y)) {
				b = true;
				break;
			}
		}

		return b;
	}

	public boolean isACircle() {
		boolean b = false;
		if (this.widthKM == this.heightKM) {
			b = true;
		}
		return b;
	}

	public boolean isAnEllipse() {
		boolean b = false;
		if (this.widthKM != this.heightKM) {
			b = true;
		}
		return b;
	}


	public void DisplayCoord() {
		for (Point2D.Double p : this.graphPoints) {
			System.out.println(p);
		}
	}

	public int HowManyNotInTheCircle() {
		int PointOutTheGraph = 0;

		if (this.isACircle()) {
			for (Point2D.Double p : graphPoints) {
				if (!this.isInTheCircle(p.x, p.y)) {
					PointOutTheGraph = PointOutTheGraph + 1;
				}
			}
		} else {
			for (Point2D.Double p : graphPoints) {
				if (!this.isInTheEllipse(p.x, p.y)) {
					PointOutTheGraph = PointOutTheGraph + 1;
				}
			}
		}

		return PointOutTheGraph;
	}
	
	
	public int HowManyInTheCircle() {
		int PointInTheGraph = 0;

		if (this.isACircle()) {
			for (Point2D.Double p : graphPoints) {
				if (this.isInTheCircle(p.x, p.y)) {
					PointInTheGraph = PointInTheGraph + 1;
				}
			}
		} else {
			for (Point2D.Double p : graphPoints) {
				if (this.isInTheEllipse(p.x, p.y)) {
					PointInTheGraph = PointInTheGraph + 1;
				}
			}
		}

		return PointInTheGraph;
	}

	public void clean() {
		System.out.println("");
		System.out.println("_______________");
		System.out.println("Cleaning process");
		Iterator<Point2D.Double> it = graphPoints.iterator();
		if (this.isACircle()) {
			while(it.hasNext()) {
				Point2D.Double p = it.next();
				if(!this.isInTheCircle(p.x, p.y)) {
					it.remove();
				}
			}
			
		} else {
			while(it.hasNext()) {
				Point2D.Double p = it.next();
				if(!this.isInTheEllipse(p.x, p.y)) {
					it.remove();
				}
			}
		}
		System.out.println("Current number of stations and position in the city : " + this.graphPoints.toString());
		System.out.println("cleaning done");
		System.out.println("________________");
		System.out.println("");
		///this.DrawPoints(gr2d);
	}

	public void PlacementOfTwoPerLine(int TotalLineToPack, int StationsToPackEachLine) {
		double stepX1 = -(0.7 * widthKM);
		double stepX2 = -(0.30 * (widthKM));
		double stepX3 = -(0.60 * widthKM);
		double stepX4 = -(0.80 * widthKM);
		int comptorP = 1;
		int comportO = 1;
		System.out.println("________________");
		System.out.println("Plaement of two stations per line calculation");
		System.out.println("");
		System.out.println("Initialization of axis : ");
		System.out.println("");
		System.out.println("Step x1 = " + stepX1);
		System.out.println("Step x2 = " + stepX2);
		System.out.println("Step x3 = " + stepX3);
		System.out.println("Step x4 = " + stepX4);
		System.out.println("");
	
		double stepY = (this.heightKM * 2) / TotalLineToPack;
		System.out.println("Step y = " + stepY);
		System.out.println("");
		System.out.println("Initialization of stations coordinates");
		
		double xp = 0.0;
		double yp = (this.heightKM * 0.95);
		
		System.out.println("x :" + xp);
		System.out.println("y : " + yp);
		
		

		for (int i = 1; i <= TotalLineToPack; i++) {
			for (int j = 1; j <= StationsToPackEachLine - 1; j++) {
				
				if (this.MaxStation >= 20) {
					if (i % 2 == 1) {
						if (comportO % 2 == 1) {
							if(this.MaxStation  >= 40) {
								xp =-(0.40 * (widthKM));
								System.out.println("new coordinates calculation");
								System.out.println("x : " +  xp);
								System.out.println("");
							}
							else {
								xp = stepX2;
								System.out.println("new coordinates calculation");
								System.out.println("x : " +  xp);
								System.out.println("");
							}
							yp = yp - stepY;
							System.out.println("new coordinates calculation");
							System.out.println("y : " +  yp);
							System.out.println("");
							comportO = comportO + 1;
						} else {
							if(this.MaxStation  >= 40) {
								xp = -(0.20 * widthKM);
								System.out.println("new coordinates calculation");
								System.out.println("x : " +  xp);
								System.out.println("");
							}
							else {
								xp = -(0.15 * widthKM);
								System.out.println("new coordinates calculation");
								System.out.println("x : " +  xp);
								System.out.println("");

							}

							yp = yp - stepY;
							System.out.println("new coordinates calculation");
							System.out.println("y : " +  yp);
							System.out.println("");
							comportO = comportO + 1;

						}
					} else {
						if (comptorP % 2 == 1) {
							xp = stepX3;
							yp = yp - stepY;
							System.out.println("new coordinates calculation");
							System.out.println("x : " +  xp);
							System.out.println("y : " + yp);
							System.out.println("");
							comptorP = comptorP + 1;

						} else {
							xp = stepX4;
							yp = yp - stepY;
							System.out.println("new coordinates calculation");
							System.out.println("x : " +  xp);
							System.out.println("y : " + yp);
							System.out.println("");
							comptorP = comptorP + 1;

						}
					}
				}

				else {
					if (i % 2 == 1) {
						if(this.MaxStation >= 10 && this.MaxStation < 20) {
							xp = stepX4;
						}
						else {
							xp = stepX2;
						}
						System.out.println("new coordinates calculation");
						System.out.println("x : " +  xp);
						System.out.println("");
						if (i == 1) {
							yp = (this.heightKM) * 0.80;
							System.out.println("new coordinates calculation");
							System.out.println("y : " + yp);
							System.out.println("");
						} else {
							yp = yp - stepY;
							System.out.println("new coordinates calculation");
							System.out.println("y : " + yp);
							System.out.println("");
						}
					}

					else if ((i % 2 == 0) && (this.isInTheCircle(stepX3, yp))
							|| (i % 2 == 0) && (this.isInTheEllipse(stepX3, yp))) {
						if(this.MaxStation <= 7) {
							xp = stepX1;
							yp = yp -stepY - 0.5;
						}
						else {
						xp = stepX3;
						yp = yp - stepY;
						}
						System.out.println("new coordinates calculation");
						System.out.println("x : " +  xp);
						System.out.println("y : " + yp);
						System.out.println("");
					} else {
						xp = 0.0;
						yp = yp - stepY;
						System.out.println("new coordinates calculation");
						System.out.println("x : " +  xp);
						System.out.println("y : " + yp);
						System.out.println("");

					}
				}

				Point2D.Double P = new Point2D.Double(xp, yp);
				System.out.println("Coordinates calculated : " + P.toString());
				System.out.println("");

				while (this.pontExist(P)) {
					System.out.println("Coordinates already exists");
					System.out.println("Calculation to fin new coordinates");
					System.out.println("");
					if (P.x < 0) {
						P.x = P.x - (widthKM / 10.0);

					} else if (P.x > 0) {
						P.x = P.x - (widthKM / 10.0);
					}
				}

				this.graphPoints.add(P);
				System.out.println("Final coordinates : " + P.toString());
				this.graphPoints.add(this.ySymetric(P));
				System.out.println("Add the symetric of this station : " + this.ySymetric(P).toString());
				System.out.println("");
				
			}
			System.out.println("Current number and position of each stations : " + this.graphPoints.toString());
			System.out.println("_______________");
			System.out.println("");
		}
		/*System.out.println("///////////////////////////////////////////////////////////////");
		System.out.println("A la sortie de place twoperline");
		System.out.println(graphPoints);
		System.out.println("SIZE GRAPHPOINTS : " + graphPoints.size());
		System.out.println("///////////////////////////////////////////////////////////////");
	*/


	}

	public void PlaceTheRestOrTheDebt() {

		if (this.rest == 1) { ///// WHEN U HAVE AN IMPAIR MAXSTATION TO PLACE THE LAST ONE IS PLACED
			System.out.println("_______________");
			System.out.println("Calculation of the rest ");
			Point2D.Double p = new Point2D.Double(0, 0);
			System.out.println(" There is a station to place : " + p.toString());
			System.out.println("");
			
			while (this.pontExist(p)) {
				System.out.println("This station is already in the city, calculation of new position : ");
				p.x = p.x - (widthKM / 10.0);
			}
			this.graphPoints.add(p);
			System.out.println("");
			System.out.println("Rest added to the city at position : " + p);
			System.out.println("Current number and position of station : " + this.graphPoints.toString());
			System.out.println("_______________");
			System.out.println("");
		}

		if (this.debt == 1) {
			System.out.println("_______________");
			System.out.println("Calculation of the debt");
			System.out.println("");
			Point2D.Double p = new Point2D.Double(0, 0);
			System.out.println(" There is a station to place : " + p.toString());
			while (this.pontExist(p)) {
				System.out.println("This station is already in the city, calculation of new position : ");
				p.x = p.x - (widthKM / 10.0);
			}
			this.graphPoints.add(p);
			System.out.println("");
			System.out.println("Debt added to the city at position : " + p);
			System.out.println("Current number and position of station : " + this.graphPoints.toString());
			System.out.println("_______________");
			System.out.println("");
			this.MaxStation = this.MaxStation + 1;
			this.debt = this.debt - 1;
			System.out.println("Number of max station update to : " + this.MaxStation);
		}
	}

	public void PlaceTheFiveFirstCase() {

		if (this.MaxStation == 2) {
			graphPoints = new ArrayList<Point2D.Double>(MaxStation);
			Point2D.Double p = new Point2D.Double((-this.widthKM) * 0.60, this.heightKM * 0.60);
			this.graphPoints.add(p);
			this.graphPoints.add(OSymetric(p));
			System.out.println("");
			System.out.println("__________");
			System.out.println("Current number of station calculated : " + this.graphPoints.size());
			System.out.println("Stations location : " + graphPoints.toString());
			System.out.println("__________");
			System.out.println("");

		}

		else if (this.MaxStation == 3) {
			graphPoints = new ArrayList<Point2D.Double>(MaxStation);
			this.graphPoints.add(new Point2D.Double(0.0, this.heightKM * 0.60));
			this.graphPoints.add(new Point2D.Double((-this.widthKM) * 0.60, (-this.heightKM) * 0.40));
			this.graphPoints.add(new Point2D.Double(this.widthKM * 0.60, (-this.heightKM) * 0.40));
			System.out.println("");
			System.out.println("__________");
			System.out.println("Current number of station calculated : " + this.graphPoints.size());
			System.out.println("Station's location : " + graphPoints.toString());
			System.out.println("__________");
			System.out.println("");
		}

		else if (this.MaxStation == 4) {
			graphPoints = new ArrayList<Point2D.Double>(MaxStation);
			Point2D.Double p = new Point2D.Double((-this.widthKM) * 0.50, this.heightKM * 0.50);
			this.graphPoints.add(p);
			this.graphPoints.add(xSymetric(p));
			this.graphPoints.add(ySymetric(p));
			this.graphPoints.add(OSymetric(p));
			System.out.println("");
			System.out.println("__________");
			System.out.println("Current number of station calculated : " + this.graphPoints.size());
			System.out.println("Stations location : " + graphPoints.toString());
			System.out.println("__________");
			System.out.println("");

		}

		else if (this.MaxStation == 5) {
			graphPoints = new ArrayList<Point2D.Double>(MaxStation);
			Point2D.Double p = new Point2D.Double((-this.widthKM) * 0.50, this.heightKM * 0.50);
			this.graphPoints.add(p);
			this.graphPoints.add(xSymetric(p));
			this.graphPoints.add(ySymetric(p));
			this.graphPoints.add(OSymetric(p));
			this.graphPoints.add(new Point2D.Double(0.0, 0.0));
			System.out.println("");
			System.out.println("__________");
			System.out.println("Current number of station calculated : " + this.graphPoints.size());
			System.out.println("Station's location : " + graphPoints.toString());
			System.out.println("__________");
			System.out.println("");
		}
		System.out.println("end of default position");
		System.out.println("_______________");
		System.out.println("");
	}

	public void ReplacePointOutTheGraph(int PointOuTheGraph) {
		System.out.println("");
		System.out.println("________________");
		System.out.println("Replacement process of stations out the city");
		System.out.println("");
		int comptor = 0;
		if (PointOuTheGraph == 2) {
			System.out.println(PointOuTheGraph + " stations to replace");
			System.out.println("");
			double y = this.heightKM * 0.5;
			double x = 0.0;
			System.out.println("new coordinates x : " + x);
			System.out.println("new coordinates y : " + y);
			System.out.println("");
			Point2D.Double P1 = new Point2D.Double(x, y);
			this.graphPoints.add(P1);
			this.graphPoints.add(xSymetric(P1));
			System.out.println("Stations replaced in the city : " + P1.toString() + " and " + xSymetric(P1).toString());
			System.out.println("");
			System.out.println("end of replacement process");
			System.out.println("_______________");
			System.out.println("");

		} else if (PointOuTheGraph == 1) {
			System.out.println(PointOuTheGraph + " station to replace");
			System.out.println("");
			Point2D.Double P1 = new Point2D.Double(0.0, 0.0);
			System.out.println("new coordinates x : " + P1.x);
			System.out.println("new coordinates y : " + P1.y);
			System.out.println("");
			while (this.pontExist(P1)) {
				System.out.println("This station is already in the city, calculation of new position : " + P1.toString());

				if (P1.x <= 0) {
					P1.x = P1.x - (widthKM / 10.0);
					System.out.println("new coordinates x : " + P1.x);

				} else if (P1.x > 0) {
					P1.x = P1.x - (widthKM / 10.0);
					System.out.println("new coordinates x : " + P1.x);
					System.out.println("");
				}
			}
			this.graphPoints.add(P1);
			System.out.println("Station added to the city at position : " + P1.toString());
			System.out.println("end of the replacement process");
			System.out.println("_______________");
			System.out.println("");

		} else {
			System.out.println(PointOuTheGraph + " stations to replace");
			System.out.println("");

			if (PointOuTheGraph % 2 == 0) {///// Number peer of station to replace in the city
				double y = this.heightKM * 0.80;
				double x = 0.0;
				System.out.println("new coordinates x : x " + x);
				System.out.println("new coordinates y : " + y);
				System.out.println("");
				double stepYPointOut = ((this.heightKM) * 2) / PointOuTheGraph;
				for (int k = 0; k < PointOuTheGraph; k++) {
					if (k == 0) {
						y = this.heightKM * 0.80;
						x = 0.0;
						System.out.println("new coordinates x : " + x);
						System.out.println("new coordinates y : " + y);
						System.out.println("");
					} else {
						x = 0.0;
						y = y - stepYPointOut;
						System.out.println("new coordinates x : " + x);
						System.out.println("new coordinates y : " + y);
						System.out.println("");
					}
					Point2D.Double P1 = new Point2D.Double(x, y);
					if(!this.isInTheCircle(P1.x,P1.y) || !this.isInTheEllipse(P1.x,P1.y)) {
						System.out.println("Check if the station is in the city : false the station is out");
						System.out.println("");
						comptor = comptor + 1;
					}
					else {
						while (this.pontExist(P1)) {
							System.out.println("a station already exists");
							System.out.println("calculation of a new position");
							if (P1.x <= 0) {
								P1.x = P1.x - (widthKM / 10.0);
								System.out.println("new coordinates x :" + x);
								System.out.println("");
	
							} else if (P1.x > 0) {
								P1.x = P1.x + (widthKM / 10.0);
								System.out.println("new coordinates x : " + x);
								System.out.println("");
							}
						}
	
						this.graphPoints.add(P1);
						
					}
				}
				double stepYComptor= ((this.heightKM) * 2) / comptor;

				for(int a = 0; a < comptor; a++) {
					Point2D.Double P1 = new Point2D.Double(x, y);
					if(a == 1) {
						P1.x = 0.0;
						P1.y = this.heightKM;
					}
					else {
						P1.x = 0.0;
						P1.y = this.heightKM - stepYComptor;
					}


					this.graphPoints.add(P1);
			
				}


			} else if (PointOuTheGraph % 2 == 1 && PointOuTheGraph >= 3) { ///// CAR IMPAIRE SUP OU EGAL A 3 A PLACER
				double stepYPointOut = (this.heightKM * 2) / PointOuTheGraph;
				double x = 0.0;
				double y = this.heightKM * 0.80;
				int PointOuTheGraphBis = PointOuTheGraph - 1;

				for (int k = 0; k < PointOuTheGraphBis; k++) {
					if (k == 0) {
						y = this.heightKM * 0.80;
						x = 0.0;
						System.out.println("new coordinates x : " + x);
						System.out.println("new coordinates y : " + y);
						System.out.println("");
					} else {
						x = 0.0;
						y = y - stepYPointOut;
						System.out.println("new coordinates x : " + x);
						System.out.println("new coordinates y : " + y);
						System.out.println("");
					}
					Point2D.Double P1 = new Point2D.Double(x, y);
					while (this.pontExist(P1)) {
						System.out.println("a station already exists at this position");
						if (P1.x <= 0) {
							P1.x = P1.x - (widthKM / 10.0);
							System.out.println("new coordinates : x " + P1.x);
							System.out.println("");

						} else if (P1.x > 0) {
							P1.x = P1.x - (widthKM / 10.0);
							System.out.println("new coordinates : x " + P1.x);
							System.out.println("");
						}
					}
					this.graphPoints.add(P1);
				}

			}

		}
		System.out.println("end of replacement process");
		System.out.println("Station's location : " + graphPoints.toString());
		System.out.println("__________");
	}

	//////////////////////////////////   ALGO OF REPARTITION OF STATIONS ///////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////



	public void StationRepartitorAlgo(double wid, double hei, int budget, int cost) {
		this.widthKM = wid;
		this.heightKM = hei;
		this.CityBudget = budget;
		this.aStationCost = cost;
		this.rest = 0;
		this.maxDiviser = 1;
		this.debt = 0;
		this.MaxStation = budget / cost;
		this.graphPoints = new ArrayList<Point2D.Double>();
		System.out.println("");
		System.out.println("//////////////////////////////////////////////////////////");
		System.out.println("BEGINING OF THE ALGORITHM");
		System.out.println("//////////////////////////////////////////////////////////");

		System.out.println("_______________");
		System.out.println("City's width in km : " + this.widthKM);
		System.out.println("City's height in km  : " + this.heightKM);
		System.out.println("City's budget : " + this.CityBudget);
		System.out.println("A station cost : " + this.aStationCost);
		System.out.println("Max station initialized to : " + MaxStation);
		System.out.println("Current number of station initialized to : " + this.graphPoints.size());
		System.out.println("_______________");
		System.out.println("");

		

		///// Establish points position and add them to the list______ heart of the
		///// algorithm <3

		if (this.MaxStation <= 5) {
			System.out.println("");
			System.out.println("_______________");
			System.out.println("MaxStation <= 5");
			System.out.println("Number of max station not enough to launch calculations : ");
			System.out.println("defaults values applicated for station's location in this case : efficient solution");
			System.out.println("");
			this.PlaceTheFiveFirstCase();
		}

		else {
			System.out.println("");
			System.out.println("MaxStation > 5");
			System.out.println("Number of max station enough to launch calculations");
			System.out.println("");

			int TotalLineToPack;
			int StationsToPackEachLine;

			if (this.widthKM == this.heightKM || this.widthKM != this.heightKM) {
				/// int step = (TotalStationsToPack - StationsToPackEachLine);
				int PointOuTheGraph = 0;
				int PointInTheGraph = 0;
				System.out.println("Point out the city before calculation initialized to : " + PointOuTheGraph );
				System.out.println("Point in the city before calculation initialized to : " + PointInTheGraph );
				System.out.println("");



				/*
				 * else if(StationsToPackEachLine == 3) { } else {
				 * this.PlacementOfTwoPerLine(TotalLineToPack, StationsToPackEachLine); }
				 */
				if (this.MaxStation % 2 == 0) {
					System.out.println("________________");
					System.out.println("The rest of the division " + this.MaxStation + "/" + "2  = "+ this.MaxStation % 2 + " the number of station to place is peer");
					System.out.println("Launch of the calculation in case of max station peer");
					System.out.println("");

					System.out.println("Calculation max diviser of max station");
					TotalLineToPack = this.StationDiviser(this.MaxStation);
					StationsToPackEachLine = MaxStation / TotalLineToPack;
					System.out.println("");
					System.out.println("Total of line to place =  "  + TotalLineToPack);
					System.out.println("Stations to pack each line = " + StationsToPackEachLine);
					System.out.println("");

					this.PlacementOfTwoPerLine(TotalLineToPack, StationsToPackEachLine);
					this.PlaceTheRestOrTheDebt();
					
					PointOuTheGraph = this.HowManyNotInTheCircle();
					PointInTheGraph = this.HowManyInTheCircle();
					System.out.println("Point out the city updated after first calculation to : " + PointOuTheGraph );
					System.out.println("Point in the city updated after first calculation  to : " + PointInTheGraph );
					System.out.println("");
					
					
					this.ReplacePointOutTheGraph(PointOuTheGraph);
					this.clean();
					PointOuTheGraph = this.HowManyNotInTheCircle();
					PointInTheGraph = this.HowManyInTheCircle();
					System.out.println("Point out the city updated after the replacement of stations and cleaning in the city : " + PointOuTheGraph );
					System.out.println("Point in the city updated after the replacement of stations and cleaning in the city   to : " + PointInTheGraph );
					System.out.println("");
					/////System.out.println("size : " + this.graphPoints.size());

				}

				else if(this.MaxStation % 2 == 1){
					System.out.println("________________");
					System.out.println(this.MaxStation + "/" + "2  = "+ this.MaxStation % 2 + " the number of station to place is not peer");
					System.out.println("Launch of the calculation in case of max station not peer");
					System.out.println("");
					int max = MaxStation - 1;
					this.MaxStation = max;
					
					System.out.println("new calculation with Maxstation - 1 : " + this.MaxStation);
					this.debt = this.debt + 1;
					System.out.println("debt initialized to : " + this.debt);
					
					TotalLineToPack = this.StationDiviser(max);
					StationsToPackEachLine = MaxStation / TotalLineToPack;
					System.out.println("");
					System.out.println("Total of line to place =  "  + TotalLineToPack);
					System.out.println("Stations to pack each line = " + StationsToPackEachLine);
					System.out.println("");

					this.PlacementOfTwoPerLine(TotalLineToPack, StationsToPackEachLine);
					this.PlaceTheRestOrTheDebt();
					
					PointOuTheGraph = this.HowManyNotInTheCircle();
					PointInTheGraph = this.HowManyInTheCircle();
					System.out.println("Point out the city updated after first calculation to : " + PointOuTheGraph );
					System.out.println("Point in the city updated after first calculation  to : " + PointInTheGraph );
					System.out.println("");

					this.ReplacePointOutTheGraph(PointOuTheGraph);
					this.clean();

					
					PointOuTheGraph = this.HowManyNotInTheCircle();
					PointInTheGraph = this.HowManyInTheCircle();
					
					System.out.println("Point out the city updated after the replacement of stations and cleaning in the city : " + PointOuTheGraph );
					System.out.println("Point in the city updated after the replacement of stations and cleaning in the city   to : " + PointInTheGraph );
					System.out.println("");
					

				}
			}
		}
		System.out.println("Final city's stations position : " + this.graphPoints.toString());
		System.out.println("Number of current stations : " + this.MaxStation);
		System.out.println("");
		System.out.println("//////////////////////////////////////////////////////////");
		System.out.println("END OF THE ALGORITHM ");
		System.out.println("//////////////////////////////////////////////////////////");
		System.out.println("");

	}
	
	public void DisplayGraph(ArrayList<Point2D.Double> graph) {
		Iterator<Point2D.Double> it = graph.iterator();
		while(it.hasNext()) {
			Point2D.Double p = it.next();
			System.out.println(p);
		}
	}
	

	////////////////////////////////////       ALGO OF LINK BETWEEN STATIONS /////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////

	
	public void StationLinkAlgo(ArrayList<Point2D.Double> graph) {
		System.out.println("");
		System.out.println("//////////////////////////////////////////////////////////");
		System.out.println("BEGINING OF THE ALGORITHM");
		System.out.println("//////////////////////////////////////////////////////////");
		System.out.println("");
		
	
		this.graphNorthToSouth = new ArrayList<Point2D.Double>();
		this.graphWestToEast = new ArrayList<Point2D.Double>();
		this.graphNorthWestToSouthEast = new ArrayList<Point2D.Double>();
		this.graphNorthEastToSouthWest= new ArrayList<Point2D.Double>();
		double North = this.getHeightKM() * 0.60;
		double South = -(this.getHeightKM() * 0.60);
		double West = -(this.getWidthKM()*0.60);
		double East = (this.getWidthKM()*0.60);
		
		System.out.println("Noth to south line initialized to " + graphNorthToSouth);
		System.out.println("West to East line initialized to ; " +graphWestToEast );
		System.out.println("NothEast to SouthWest line initialized to : " +graphNorthEastToSouthWest );
		System.out.println("NorthWest to SouthEast line initialized to : " +  graphNorthWestToSouthEast);
		System.out.println("");

		Iterator<Point2D.Double> it = graph.iterator();
		while (it.hasNext()) {
			Point2D.Double p = it.next();

			if (graph.size() == 3) {
				graphNorthToSouth.add(p);
				System.out.println("");
				System.out.println("_______________");
				System.out.println("The number of stations is too low to applicate the algortihm");
				System.out.println("Placement to : " + p);
				System.out.println("_______________");
				System.out.println("");
			}
			


			else {
				System.out.println("");
				System.out.println("_______________");
				System.out.println("The number of stations is enoughto applicate the algortihm");
				System.out.println("");

				if (p.x == 0.0 && p.y == 0.0) {
					if(graph.size() >= 3) {
						System.out.println("The station is placed to the center "  + p);
						System.out.println("Placement of this station to all line of tramway");
						System.out.println("");
						graphNorthToSouth.add((graphNorthToSouth.size())/2,p);
						graphWestToEast.add((graphWestToEast.size())/2,p);
						graphNorthWestToSouthEast.add((graphNorthWestToSouthEast.size())/2,p);
						graphNorthEastToSouthWest.add((graphNorthEastToSouthWest.size())/2,p);
						System.out.println("Added to the NorthToSouth line : " + graphNorthToSouth);
						System.out.println("Added to the WestToEast line : " + graphWestToEast);
						System.out.println("Added to the NorthWestToSouthEast line : " + graphNorthWestToSouthEast);
						System.out.println("Added to the NorthEastToSouthWest line : " + graphNorthEastToSouthWest);
						System.out.println("_______________");
						System.out.println("");
					}
					else {
					graphNorthToSouth.add(p);
					graphWestToEast.add(p);
					graphNorthWestToSouthEast.add(p);
					graphNorthEastToSouthWest.add(p);
					}
				} else {
					System.out.println("");
					System.out.println("_______________");
					if (p.y < South || p.y > North || p.x == 0.0) {
						System.out.println("Calculation of coordinates");
						System.out.println(" x : " + p.x);
						System.out.println(" y : " + p.y);
						System.out.println("CHECK ///// y < " + South + " or " +" y > " + North + " or "+ " x = " + "0" );
						System.out.println("Calculation : station added to line North to South");
						graphNorthToSouth.add(p);
						System.out.println("");
					}

					if (p.x < West || p.x > East || p.y == 0.0
							|| (p.y <= this.getHeightKM() * 0.2 && p.y >= -(this.getHeightKM() * 0.2))) {
						System.out.println("Calculation of coordinates");
						System.out.println(" x : " + p.x);
						System.out.println(" y : " + p.y);
						System.out.println(" CHECK ///// x < " + West + " or " +  " x > " + East + " or "+  "y = " + "0" );
						System.out.println("Calculation : station added to line West to East");
						graphWestToEast.add(p);
						System.out.println("");
					}

					if ((p.x >= West && p.x < 0.0) && (p.y <= North && p.y > 0.0)) {
						System.out.println("Calculation of coordinates");
						System.out.println(" x : " + p.x);
						System.out.println(" y : " + p.y);
						System.out.println(" CHECK ///// x >= " + West +" and "+ " x < " + "0" + " and " +  " y < " +  North + " and " + " y > 0"  );
						System.out.println("Calculation : station added to line NorthWest to SouthEast");
						graphNorthWestToSouthEast.add(p);
					}
					if ((p.x > 0.0 && p.x <= East) && (p.y >= South && p.y < 0.0)) {
						System.out.println("Calculation of coordinates");
						System.out.println(" x : " + p.x);
						System.out.println(" y : " + p.y);
						System.out.println(" CHECK ///// x >= 0 and "  + " x <= " + East + " and " +  " y >= " +  South + " and " + " y < 0"  );
						System.out.println("Calculation : station added to line NorthWest to SouthEast");
						graphNorthWestToSouthEast.add(p);
						System.out.println("");
					}
					if ((p.x <= East && p.x > 0.0) && (p.y <= North && p.y > 0.0)) {
						System.out.println("Calculation of coordinates");
						System.out.println(" x : " + p.x);
						System.out.println(" y : " + p.y);
						System.out.println(" CHECK ///// x <= " + East +" and "+ " x > " + "0" + " and " +  " y <= " +  North + " and " + " y > 0"  );
						System.out.println("Calculation : station added to line NorthEast to SouthWest");
						graphNorthEastToSouthWest.add(p);
						System.out.println("");
					}
					if ((p.x < 0.0 && p.x >= West) && (p.y >= South && p.y < 0.0)) {
						System.out.println("Calculation of coordinates");
						System.out.println(" x : " + p.x);
						System.out.println(" y : " + p.y);
						System.out.println(" CHECK ///// x <  0" +" and "+ " x > " + West + " and " +  " y >= " +  South + " and " + " y < 0"  );
						System.out.println("Calculation : station added to line NortEast to SouthWest");
						graphNorthEastToSouthWest.add(p);
						System.out.println("");
					}
				}
				System.out.println("_______________");
				System.out.println("");

			}
			

		}
		System.out.println("First Step finished");
		
	
	/////SORT THE GRAPH BECAUSE ALL STATIONS ARE NOT IN THE GOOD ORDER
		System.out.println("");
		System.out.println("_______________");
		System.out.println("Step that re order line North to South");
			 Collections.sort(graphNorthToSouth, new Comparator<Point2D.Double>() {
				@Override
				public int compare(Point2D.Double o1, Point2D.Double o2) {
					int result = 0;
					if(o1.y > o2.y ) {
						result = 1;
						
					}
					
					if(o1.y < o2.y) {
						result =  -1;
					}
					
					if(o1.y == o2.y) {
						result = 0;
					}
				return result;	
				}
				
			 });
			 System.out.println("End of the sort");
			 System.out.println("_______________");
			 System.out.println("");
			 
			/////SORT THE GRAPH BECAUSE ALL STATIONS ARE NOT IN THE GOOD ORDER
			 System.out.println("Step that re order line West to East");
			 Collections.sort(graphWestToEast,new Comparator<Point2D.Double>() {
				@Override
				public int compare(Point2D.Double o1, Point2D.Double o2) {
					int result = 0;
					
					if(o1.x > o2.x) {
						result = 1;
					}
					
					if(o1.x < o2.x) {
						result = -1;
					}
					
					if(o1.x == o2.x) {
						result = 0;
					}
					
					return result;
				}
				 
			 });
			 System.out.println("End of the sort");
			 System.out.println("_______________");
			 System.out.println("");


			System.out.println("_______________");
			System.out.println("North TO South");
			this.DisplayGraph(graphNorthToSouth);
			System.out.println("number of station : " + graphNorthToSouth.size());
			System.out.println("Distance of road : " + this.DistanceOfRoad(graphNorthToSouth) + "km");
			System.out.println("_______________");
			System.out.println("");
			
			System.out.println("_______________");
			System.out.println("West to East");
			this.DisplayGraph(graphWestToEast);
			System.out.println("number of station : " + graphWestToEast.size());
			System.out.println("Distance of road : " + this.DistanceOfRoad(graphWestToEast) + "km");
			System.out.println("_______________");
			
			System.out.println("");
			System.out.println("_______________");
			System.out.println("NorthEast to SouthWEST");
			this.DisplayGraph(graphNorthEastToSouthWest);
			System.out.println("number of station : " + graphNorthEastToSouthWest.size());

			System.out.println("Distance of road : " + this.DistanceOfRoad(graphNorthEastToSouthWest) + "km");
			System.out.println("_______________");
			System.out.println("");
			
			System.out.println("_______________");
			System.out.println("NorthWEST to SouthEast");
			this.DisplayGraph(graphNorthWestToSouthEast);
			System.out.println("number of station : " + graphNorthWestToSouthEast.size());
			System.out.println("Distance of road : " + this.DistanceOfRoad(graphNorthWestToSouthEast) + "km");
			System.out.println("_______________");
			System.out.println("");
			
			
			System.out.println("");
			System.out.println("//////////////////////////////////////////////////////////");
			System.out.println("END OF THE ALGORITHM ");
			System.out.println("//////////////////////////////////////////////////////////");
			System.out.println("");

			
			
			
			
			
			
			
			

	}
	
	
	
	public double DistanceOfRoad(ArrayList<Point2D.Double> graph) {
		double comptor = 0;
		System.out.println("");
		System.out.println("_______________");
		System.out.println("Begining of distance road calculation");
		System.out.println("Distance initialized to : " + comptor);

		for(int i = 0; i < graph.size() - 1; i++) {
			Point2D.Double p = graph.get(i);
			System.out.println("Distance : " + comptor + " + " + p.distance(graph.get(i+1)));
			comptor = comptor + p.distance(graph.get(i+1));
		}
		System.out.println("end of distance road calculation");
		System.out.println("_______________");
		System.out.println("");
		return comptor;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////


	public double getWidthPX() {
		return widthPX;
	}

	public void setWidthPX(double widthPX) {
		this.widthPX = widthPX;
	}

	public double getHeightPX() {
		return heightPX;
	}

	public void setHeightPX(double heightPX) {
		this.heightPX = heightPX;
	}

	public ArrayList<Point2D.Double> getGraphPoints() {
		return graphPoints;
	}

	public void setGraphPoints(ArrayList<Point2D.Double> graphPoints) {
		this.graphPoints = graphPoints;
	}

	public int getMaxStation() {
		return MaxStation;
	}

	public void setMaxStation(int maxStation) {
		MaxStation = maxStation;
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

	public int getCityBudget() {
		return CityBudget;
	}

	public void setCityBudget(int cityBudget) {
		CityBudget = cityBudget;
	}

	public int getaStationCost() {
		return aStationCost;
	}

	public void setaStationCost(int aStationCost) {
		this.aStationCost = aStationCost;
	}

	public double getWidthKM() {
		return widthKM;
	}

	public void setWidthKM(double widthKM) {
		this.widthKM = widthKM;
	}

	public double getHeightKM() {
		return heightKM;
	}

	public void setHeightKM(double heightKM) {
		this.heightKM = heightKM;
	}

	public int getMaxDiviser() {
		return maxDiviser;
	}

	public void setMaxDiviser(int maxDiviser) {
		this.maxDiviser = maxDiviser;
	}

	public int getDebt() {
		return debt;
	}

	public void setDebt(int debt) {
		this.debt = debt;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ArrayList<Point2D.Double> getGraphNorthToSouth() {
		return graphNorthToSouth;
	}


	public void setGraphNorthToSouth(ArrayList<Point2D.Double> graphNorthToSouth) {
		this.graphNorthToSouth = graphNorthToSouth;
	}


	public ArrayList<Point2D.Double> getGraphWestToEast() {
		return graphWestToEast;
	}


	public void setGraphWestToEast(ArrayList<Point2D.Double> graphWestToEast) {
		this.graphWestToEast = graphWestToEast;
	}


	public ArrayList<Point2D.Double> getGraphNorthWestToSouthEast() {
		return graphNorthWestToSouthEast;
	}


	public void setGraphNorthWestToSouthEast(ArrayList<Point2D.Double> graphNorthWestToSouthEast) {
		this.graphNorthWestToSouthEast = graphNorthWestToSouthEast;
	}


	public ArrayList<Point2D.Double> getGraphNorthEastToSouthWest() {
		return graphNorthEastToSouthWest;
	}


	public void setGraphNorthEastToSouthWest(ArrayList<Point2D.Double> graphNorthEastToSouthWest) {
		this.graphNorthEastToSouthWest = graphNorthEastToSouthWest;
	}
	
}
