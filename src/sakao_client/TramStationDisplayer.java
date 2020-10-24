package sakao_client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class TramStationDisplayer extends JComponent {///////////////////////// CONTAINS THE DRAWER 

	private static final long serialVersionUID = -5879720344667510046L;
	
	
	private TramStationComputer computer = new TramStationComputer();/////DISPLAY


	private boolean shouldRunAlgo = false;
	private boolean shouldRunDisplay = false;

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
			this.DrawCity(graphics, computer.getWidthKM(), computer.getHeightKM());
			this.DrawPoints(g2d);
			this.DrawTramLine(g2d,computer.getGraphNorthToSouth(),computer.getGraphWestToEast(),computer.getGraphNorthEastToSouthWest(),computer.getGraphNorthWestToSouthEast());
			System.out.println(computer.getGraphPoints());


	}
	
	
/////////////////////////DRAWING METHODE WHEN THEN CLIENT JUST WANT TO CHARGE AN EXISTING CITY/ THERE IS NO ALGORITHM/////////////////////////
	
	public void DrawCity(Graphics g, double w, double h) {
		computer.setHeightKM(h);
		computer.setWidthKM(w);
		computer.setWidthPX(w * 100);
		computer.setHeightPX(h * 100);
		g.setColor(Color.BLACK);
		Ellipse2D.Double oval = new Ellipse2D.Double(0.0, 0.0, computer.getWidthPX(), computer.getHeightPX());
		Graphics2D gr2d = (Graphics2D) g;

		gr2d.fill(oval);

		g.setColor(Color.WHITE);
		gr2d.setFont(new Font("default", Font.BOLD, 16));

		gr2d.drawString("CENTER", (float) (computer.getWidthPX() * 0.45), (float) (computer.getHeightPX() * 0.57));
		gr2d.drawString("WEST" , (float) (computer.getWidthPX() * 0.03), (float) (computer.getHeightPX() * 0.57));
		gr2d.drawString("EAST" , (float) (computer.getWidthPX() * 0.90), (float) (computer.getHeightPX() * 0.57));
		gr2d.drawString("SOUTH" , (float) (computer.getWidthPX() * 0.45), (float) (computer.getHeightPX() * 0.97));
		gr2d.drawString( "NORTH", (float) (computer.getWidthPX() * 0.45), (float) (computer.getHeightPX() * 0.07));

	}

	///// Logical values into physical values
	public double xToPixel(double x) {
		return ((computer.getWidthKM() * 50) + (x * 50));
	}

	public double yToPixel(double y) {
		return ((computer.getHeightKM() * 50) + (-y * 50));
	}


	public void DrawPoints(Graphics2D g) {
		g.setColor(Color.WHITE);
		int xs = 7;
		int ys = 7;
		
		if(this.getComputer().getMaxStation() > 0 && this.getComputer().getMaxStation() < 75) {
			xs = 10;
			ys = 10;
		}
		
		
		if(this.getComputer().getMaxStation() > 130) {
			xs = 5;
			ys = 5;
		}
		for (int i = 0; i < computer.getGraphPoints().size(); i++) {
			double x = computer.getGraphPoints().get(i).x;
			double y = computer.getGraphPoints().get(i).y;

			Rectangle2D.Double rectangle = new Rectangle2D.Double(xToPixel(x), yToPixel(y), xs, ys);
			g.fill(rectangle);
		}
	}



////////////////////////////////////////////////////////////////////////////
	/* Main algorithm which places the points on the map */

	

	
	
	public void DrawLines(ArrayList<Point2D.Double> graph,Graphics2D g,Color c) {
		for (int i = 0; i < graph.size(); i++) {
			g.setColor(c);

			for (int j = i + 1; j < graph.size(); j++) {
	
					double x1 = graph.get(i).x;
					double y1 = graph.get(i).y;
					double x2 = graph.get(i+1).x;
					double y2 = graph.get(i+1).y;
					g.draw(new Line2D.Double(xToPixel(x1), yToPixel(y1), xToPixel(x2), yToPixel(y2)));

				}
			
		}
	}
	

			/////this.DrawTramLine(gr2d,this.graphNorthToSouth,this.graphWestToEast,this.graphNorthEastToSouthWest,this.graphNorthWestToSouthEast);

	
	
	
	public void DrawTramLine(Graphics g,ArrayList<Point2D.Double> graphNorthToSouth,ArrayList<Point2D.Double> graphWestToEast
			,ArrayList<Point2D.Double> graphNorthEastToSouthWest,ArrayList<Point2D.Double> graphNorthWestToSouthEast) {
		Graphics2D gr2d = (Graphics2D) g;
		this.DrawLines(graphNorthToSouth,gr2d,Color.RED);
		this.DrawLines(graphWestToEast,gr2d,Color.MAGENTA);
		this.DrawLines(graphNorthEastToSouthWest,gr2d,Color.ORANGE);
		this.DrawLines(graphNorthWestToSouthEast,gr2d,Color.GREEN);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////




	
	public void repaint(){
		// repaint le component courant
		super.repaint();
		// repaint tous les components qu'il possède
		for(int i = 0; i < this.getComponentCount(); i++)
		this.getComponent(i).repaint();
	}


	public TramStationComputer getComputer() {
		return computer;
	}


	public void setComputer(TramStationComputer computer) {
		this.computer = computer;
	}


	public boolean isShouldRunAlgo() {
		return shouldRunAlgo;
	}


	public void setShouldRunAlgo(boolean shouldRunAlgo) {
		this.shouldRunAlgo = shouldRunAlgo;
	}


	public boolean isShouldRunDisplay() {
		return shouldRunDisplay;
	}


	public void setShouldRunDisplay(boolean shouldRunDisplay) {
		this.shouldRunDisplay = shouldRunDisplay;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
