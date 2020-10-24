package sakao_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;
import sakao_common.SmartCity;
import sakao_common.Station;
import sakao_common.TramLineA;
import sakao_common.TramLineB;
import sakao_common.TramLineC;
import sakao_common.TramLineD;

public class TramStationTracer extends JFrame implements ActionListener {//////////////////////////// CONTAINS THE FRAME
	/*
	 * Alain SARKISIAN
	 */
	private static final long serialVersionUID = 786392524822582941L;
	private TramStationDisplayer display = new TramStationDisplayer();
	private JButton SimulateCity = new JButton("Simulate city");
	private JButton GenerateCity = new JButton("Generate city");
	private JButton Reload = new JButton("Reload");
	private JButton Save = new JButton("Save");
	private JButton RemoveCity = new JButton("RemoveCity");

	private JTextField WidthB = new JTextField("Width");
	private JTextField HeightB = new JTextField("Height");
	private JTextField Budget = new JTextField("Budget");
	private JTextField StationCost = new JTextField("StationCost");
	private JPanel contentPaneCenter;
	private JPanel contentPaneTop;

	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();
	private final static String INSERT = "INSERT";
	private ObjectMapper mapper;
	private TramStationComputer computer = new TramStationComputer();///// DISPLAY
	AppStructureHandler app;

	TramStationTracer(AppStructureHandler app) throws IOException, JSONException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		this.app = app;

		this.setTitle("Sakao City Tracer");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		contentPaneCenter = new JPanel(new BorderLayout());

		contentPaneTop = new JPanel(new GridLayout(1, 2, 10, 0));
		contentPaneTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.getContentPane().add(contentPaneCenter, BorderLayout.CENTER);
		this.getContentPane().add(contentPaneTop, BorderLayout.NORTH);
		RemoveCity.addActionListener(this);
		GenerateCity.addActionListener(this);
		Reload.addActionListener(this);
		Save.addActionListener(this);
		SimulateCity.addActionListener(this);
		this.MouseEvent();
		contentPaneTop.add(RemoveCity);
		contentPaneTop.add(GenerateCity);
		contentPaneTop.add(Reload);
		contentPaneTop.add(Save);
		contentPaneTop.add(WidthB);
		contentPaneTop.add(HeightB);
		contentPaneTop.add(Budget);
		contentPaneTop.add(StationCost);
		contentPaneTop.add(SimulateCity);

		GenerateCity.setBackground(Color.GREEN);
		RemoveCity.setBackground(Color.RED);

		Save.setBackground(Color.GREEN);
		Reload.setBackground(Color.RED);

		SimulateCity.setBackground(Color.GREEN);

		this.startConnection("localhost", 3030);
		System.out.println("jsuis dans le cosntructeur");
		ArrayList<String> array = new ArrayList<String>();
		Request requouest = new Request("SELECT_ALL", "smartcity");
		array = app.sendMessageToServer(requouest);

		if (array.size() == 0) {
			RemoveCity.setEnabled(false);
			GenerateCity.setEnabled(false);
			Reload.setEnabled(false);
			Save.setEnabled(false);
			SimulateCity.setEnabled(true);
		} else {
			RemoveCity.setEnabled(true);
			GenerateCity.setEnabled(true);
			SimulateCity.setEnabled(false);
			Reload.setEnabled(false);
			Save.setEnabled(false);
		}

		contentPaneTop.setVisible(true);

		contentPaneCenter.add(display, BorderLayout.CENTER);
		contentPaneCenter.setVisible(false);

		this.setSize(755, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		System.out.println("Array " + array);

	}

	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("waiting for connection in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("connection succeed");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(this.getSimulateCity())) {
			try {
				System.out.println("jy suis");
				double w = Double.parseDouble(this.getWidthB().getText());
				double h = Double.parseDouble(this.getHeightB().getText());
				int b = Integer.parseInt(this.getBudget().getText());
				int c = Integer.parseInt(this.getStationCost().getText());

				if (w <= 0.0 || h <= 0.0 || b < 1 || c < 1 || c >= b) {
					JOptionPane.showMessageDialog(null, "At least one of the field is not correct", "Error message",
							JOptionPane.ERROR_MESSAGE);
				}

				else {
					this.getComputer().setWidthKM(w);
					this.getComputer().setHeightKM(h);
					this.getComputer().setCityBudget(b);
					this.getComputer().setaStationCost(c);

					this.getComputer().StationRepartitorAlgo(w, h, b, c);
					this.getComputer().StationLinkAlgo(this.getComputer().getGraphPoints());
					this.getDisplay().setComputer(this.getComputer());

					this.getContentPaneCenter().setVisible(true);
					this.getReload().setEnabled(true);
					this.getSave().setEnabled(true);
					this.getSimulateCity().setEnabled(false);

				}
			} catch (NumberFormatException ex) { // handle your exception
				JOptionPane.showMessageDialog(null, "At least one of the field's type is not correct", "Error message",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		if (e.getSource().equals(this.getReload())) {
			try {
				double w = Double.parseDouble(this.getWidthB().getText());
				double h = Double.parseDouble(this.getHeightB().getText());
				int b = Integer.parseInt(this.getBudget().getText());
				int c = Integer.parseInt(this.getStationCost().getText());

				if (w <= 0.0 || h <= 0.0 || b < 1 || c < 1 || c >= b) {
					JOptionPane.showMessageDialog(null, "At least one of the field is not correct", "Error message",
							JOptionPane.ERROR_MESSAGE);

				} else {
					this.getDisplay().setShouldRunAlgo(true);
					this.getComputer().setWidthKM(w);
					this.getComputer().setHeightKM(h);
					this.getComputer().setCityBudget(b);
					this.getComputer().setaStationCost(c);
					this.getComputer().StationRepartitorAlgo(w, h, b, c);
					this.getComputer().StationLinkAlgo(this.getComputer().getGraphPoints());
					this.getDisplay().setComputer(this.getComputer());
					this.getDisplay().repaint();

				}
			} catch (NumberFormatException ex) { // handle your exception
				JOptionPane.showMessageDialog(null, "At least one of the field's type is not correct", "Error message",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource().equals(this.getSave())) {
			this.getSimulateCity().setEnabled(false);
			this.getReload().setEnabled(false);
			this.getGenerateCity().setEnabled(true);
			this.getSave().setEnabled(false);
			this.getRemoveCity().setEnabled(true);
			this.getContentPaneCenter().setVisible(false);

			String w = this.getWidthB().getText();
			String h = this.getHeightB().getText();
			String b = this.getBudget().getText();
			String c = this.getStationCost().getText();
			String s1 = "{";
			String s3 = "}";
			try {
				ArrayList<String> SmartCityList = new ArrayList<String>();///// SAVE THE CITY
				SmartCityList.add(s1);
				SmartCityList.add("heightkm");
				SmartCityList.add(h);
				SmartCityList.add("width");
				SmartCityList.add(w);
				SmartCityList.add("budget");
				SmartCityList.add(b);
				SmartCityList.add("astationcost");
				SmartCityList.add(c);
				SmartCityList.add(s3);

				Request requestCity = new Request(INSERT, "smartcity", SmartCityList);
				app.sendMessageToServer(requestCity);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				for (int i = 0; i < this.getDisplay().getComputer().getGraphPoints().size(); i++) {///// SAVE STATIONS
					ArrayList<String> StationList = new ArrayList<String>();
					Double doubleX = (this.getDisplay().getComputer().getGraphPoints().get(i).getX());
					String stringX = doubleX.toString();
					Double doubleY = (this.getDisplay().getComputer().getGraphPoints().get(i).getY());
					String stringY = doubleY.toString();
					StationList.add(s1);
					StationList.add("typestation");
					StationList.add("tramway");
					StationList.add("coordx");
					StationList.add(stringX);
					StationList.add("coordy");
					StationList.add(stringY);
					StationList.add("isbuilt");
					StationList.add("true");
					StationList.add(s3);
					Request requestStation = new Request(INSERT, "station", StationList);
					app.sendMessageToServer(requestStation);

				}

			}

			catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				for (int i = 0; i < this.getDisplay().getComputer().getGraphNorthToSouth().size(); i++) {///// SAVE TRAM
																											///// LINE A
					ArrayList<String> TramLineAList = new ArrayList<String>();
					Double doubleX = (this.getDisplay().getComputer().getGraphNorthToSouth().get(i).getX());
					String stringX = doubleX.toString();
					Double doubleY = (this.getDisplay().getComputer().getGraphNorthToSouth().get(i).getY());
					String stringY = doubleY.toString();
					TramLineAList.add(s1);
					TramLineAList.add("coordx");
					TramLineAList.add(stringX);
					TramLineAList.add("coordy");
					TramLineAList.add(stringY);
					TramLineAList.add(s3);
					Request requestTramLineA = new Request(INSERT, "tramlinea", TramLineAList);
					System.out.println(TramLineAList);
					app.sendMessageToServer(requestTramLineA);
				}

			}

			catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				for (int i = 0; i < this.getDisplay().getComputer().getGraphWestToEast().size(); i++) {///// SAVE TRAM
																										///// LINE B
					ArrayList<String> TramLineBList = new ArrayList<String>();
					Double doubleX = (this.getDisplay().getComputer().getGraphWestToEast().get(i).getX());
					String stringX = doubleX.toString();
					Double doubleY = (this.getDisplay().getComputer().getGraphWestToEast().get(i).getY());
					String stringY = doubleY.toString();
					TramLineBList.add(s1);
					TramLineBList.add("coordx");
					TramLineBList.add(stringX);
					TramLineBList.add("coordy");
					TramLineBList.add(stringY);
					TramLineBList.add(s3);
					Request requestTramLineB = new Request(INSERT, "tramlineb", TramLineBList);
					app.sendMessageToServer(requestTramLineB);
				}

			}

			catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				for (int i = 0; i < this.getDisplay().getComputer().getGraphNorthEastToSouthWest().size(); i++) {///// SAVE
																													///// THE
																													///// LINE
																													///// C
					ArrayList<String> TramLinecList = new ArrayList<String>();
					Double doubleX = (this.getDisplay().getComputer().getGraphNorthEastToSouthWest().get(i).getX());
					String stringX = doubleX.toString();
					Double doubleY = (this.getDisplay().getComputer().getGraphNorthEastToSouthWest().get(i).getY());
					String stringY = doubleY.toString();
					TramLinecList.add(s1);
					TramLinecList.add("coordx");
					TramLinecList.add(stringX);
					TramLinecList.add("coordy");
					TramLinecList.add(stringY);
					TramLinecList.add(s3);
					Request requestTramLineC = new Request(INSERT, "tramlinec", TramLinecList);
					app.sendMessageToServer(requestTramLineC);
				}

			}

			catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				for (int i = 0; i < this.getDisplay().getComputer().getGraphNorthWestToSouthEast().size(); i++) {///// SAVE
																													///// THE
																													///// LINE
																													///// D
					ArrayList<String> TramLineDList = new ArrayList<String>();
					Double doubleX = (this.getDisplay().getComputer().getGraphNorthWestToSouthEast().get(i).getX());
					String stringX = doubleX.toString();
					Double doubleY = (this.getDisplay().getComputer().getGraphNorthWestToSouthEast().get(i).getY());
					String stringY = doubleY.toString();
					TramLineDList.add(s1);
					TramLineDList.add("coordx");
					TramLineDList.add(stringX);
					TramLineDList.add("coordy");
					TramLineDList.add(stringY);
					TramLineDList.add(s3);
					Request requestTramLineD = new Request(INSERT, "tramlined", TramLineDList);
					app.sendMessageToServer(requestTramLineD);
				}

			}

			catch (IOException e1) {
				e1.printStackTrace();
			}

			JOptionPane.showMessageDialog(null, "You saved the city and stations", "City and stations saved !",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getSource().equals(this.getRemoveCity())) {
			this.getRemoveCity().setEnabled(false);
			this.getSave().setEnabled(false);
			this.getReload().setEnabled(false);

			this.getSimulateCity().setEnabled(true);
			this.getGenerateCity().setEnabled(false);

			try {
				Request requestDeleteCity = new Request("DELETE_ALL", "smartcity");
				app.sendMessageToServer(requestDeleteCity);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				Request requestDeleteStation = new Request("DELETE_ALL", "station");
				app.sendMessageToServer(requestDeleteStation);
			}

			catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				Request requestDeleteTramLineA = new Request("DELETE_ALL", "tramlinea");
				app.sendMessageToServer(requestDeleteTramLineA);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				Request requestDeleteTramLineB = new Request("DELETE_ALL", "tramlineb");
				app.sendMessageToServer(requestDeleteTramLineB);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				Request requestDeleteTramLineC = new Request("DELETE_ALL", "tramlinec");
				app.sendMessageToServer(requestDeleteTramLineC);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				Request requestDeleteTramLineD = new Request("DELETE_ALL", "tramlined");
				app.sendMessageToServer(requestDeleteTramLineD);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			this.getContentPaneCenter().setVisible(false);

			JOptionPane.showMessageDialog(null, "You remove the city and stations", "City and stations removed!",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getSource().equals(this.getGenerateCity())) { ///// LIRE LA BDD ET AFFICHER
			this.getDisplay().setShouldRunAlgo(false);
			this.getRemoveCity().setEnabled(true);
			this.getSave().setEnabled(false);
			this.getReload().setEnabled(false);
			this.getSimulateCity().setEnabled(false);
			this.getGenerateCity().setEnabled(false);
			this.getDisplay().setShouldRunDisplay(true);

			try {///// SELECT DEJA LES DIMENSIONS DE LA VILLE
				Request request = new Request("SELECT_DIMENSION", "smartcity");
				ArrayList<String> list = app.sendMessageToServer(request);
				ArrayList<SmartCity> listSmartCity = new ArrayList<SmartCity>();
				ArrayList<Double> listDimension = new ArrayList<Double>();

				String s = list.get(0) + "\n";
				SmartCity req = new ObjectMapper().readValue(s, SmartCity.class);
				double x = req.getHeightkm();
				double y = req.getWidthkm();
				System.out.println(" ///////////////" + x + " " + y);
				listSmartCity.add(req);
				listDimension.add(x);
				listDimension.add(y);
				this.getDisplay().getComputer().setHeightKM(x);
				this.getDisplay().getComputer().setWidthKM(y);
			} catch (Exception e1) {
				System.out.println("SmartCity is empty");
				JOptionPane.showMessageDialog(null, "Empty Smartcity", "Error message", JOptionPane.ERROR_MESSAGE);
				double x = 0.0;
				double y = 0.0;
				System.out.println(" ///////////////" + x + " " + y);

				this.getDisplay().getComputer().setHeightKM(x);
				this.getDisplay().getComputer().setWidthKM(y);

			}

			try {///// SELECT LES COORDONNEES DES STATIONS
				Request request = new Request("SELECT_COORD", "station");
				ArrayList<String> list = app.sendMessageToServer(request);
				ArrayList<Station> listStation = new ArrayList<Station>();
				ArrayList<Point2D.Double> listCoord = new ArrayList<Point2D.Double>();
				for (int i = 0; i < list.size(); i++) {
					String s = list.get(i) + "\n";
					Station req1 = new ObjectMapper().readValue(s, Station.class);
					double x = req1.getCoordx();
					double y = req1.getCoordy();
					Point2D.Double p = new Point2D.Double(x, y);
					listStation.add(req1);
					listCoord.add(p);
				}
				this.getDisplay().getComputer().setGraphPoints(listCoord);

			} catch (IOException e2) {
				System.out.println("Station is empty");
				JOptionPane.showMessageDialog(null, "Stations are empty", "Error message", JOptionPane.ERROR_MESSAGE);
			}

			try {///// SELECT DEJA LES COORDONNES DE CHAQUE LIGNE DE TRAMWAY
				Request request = new Request("SELECT_TRAMLINEA", "tramlinea");
				ArrayList<String> list = app.sendMessageToServer(request);
				ArrayList<TramLineA> listTramLineA = new ArrayList<TramLineA>();
				ArrayList<Point2D.Double> listTramAStationCoord = new ArrayList<Point2D.Double>();
				for (int i = 0; i < list.size(); i++) {
					String s = list.get(i) + "\n";
					TramLineA req1 = new ObjectMapper().readValue(s, TramLineA.class);
					double x = req1.getCoordx();
					double y = req1.getCoordy();
					Point2D.Double p = new Point2D.Double(x, y);
					listTramLineA.add(req1);
					listTramAStationCoord.add(p);
				}
				this.getDisplay().getComputer().setGraphNorthToSouth(listTramAStationCoord);
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("TramLineA is empty");
				JOptionPane.showMessageDialog(null, "TramLineA empty", "Error message", JOptionPane.ERROR_MESSAGE);
			}

			try {///// SELECT DEJA LES COORDONNES DE CHAQUE LIGNE DE TRAMWAY
				Request request = new Request("SELECT_TRAMLINEB", "tramlineb");
				ArrayList<String> list = app.sendMessageToServer(request);
				ArrayList<TramLineB> listTramLineB = new ArrayList<TramLineB>();
				ArrayList<Point2D.Double> listTramBStationCoord = new ArrayList<Point2D.Double>();
				for (int i = 0; i < list.size(); i++) {
					String s = list.get(i) + "\n";
					/// System.out.println("S: "+s);
					TramLineB req1 = new ObjectMapper().readValue(s, TramLineB.class);
					double x = req1.getCoordx();
					double y = req1.getCoordy();
					Point2D.Double p = new Point2D.Double(x, y);
					listTramLineB.add(req1);
					listTramBStationCoord.add(p);
				}
				this.getDisplay().getComputer().setGraphWestToEast(listTramBStationCoord);
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("TramLineA is empty");
				JOptionPane.showMessageDialog(null, "TramLineA empty", "Error message", JOptionPane.ERROR_MESSAGE);
			}

			try {///// SELECT DEJA LES COORDONNES DE CHAQUE LIGNE DE TRAMWAY
				Request request = new Request("SELECT_TRAMLINEC", "tramlinec");
				ArrayList<String> list = app.sendMessageToServer(request);
				ArrayList<TramLineC> listTramLineC = new ArrayList<TramLineC>();
				ArrayList<Point2D.Double> listTramCStationCoord = new ArrayList<Point2D.Double>();
				for (int i = 0; i < list.size(); i++) {
					String s = list.get(i) + "\n";
					/// System.out.println("S: "+s);
					TramLineC req1 = new ObjectMapper().readValue(s, TramLineC.class);
					double x = req1.getCoordx();
					double y = req1.getCoordy();
					Point2D.Double p = new Point2D.Double(x, y);
					listTramLineC.add(req1);
					listTramCStationCoord.add(p);
				}
				this.getDisplay().getComputer().setGraphNorthEastToSouthWest(listTramCStationCoord);
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("TramLineA is empty");
				JOptionPane.showMessageDialog(null, "TramLineA empty", "Error message", JOptionPane.ERROR_MESSAGE);
			}

			try {///// SELECT DEJA LES COORDONNES DE CHAQUE LIGNE DE TRAMWAY
				Request request = new Request("SELECT_TRAMLINED", "tramlined");
				ArrayList<String> list = app.sendMessageToServer(request);
				ArrayList<TramLineD> listTramLineD = new ArrayList<TramLineD>();
				ArrayList<Point2D.Double> listTramDStationCoord = new ArrayList<Point2D.Double>();
				for (int i = 0; i < list.size(); i++) {
					String s = list.get(i) + "\n";
					/// System.out.println("S: "+s);
					TramLineD req1 = new ObjectMapper().readValue(s, TramLineD.class);
					double x = req1.getCoordx();
					double y = req1.getCoordy();
					Point2D.Double p = new Point2D.Double(x, y);
					listTramLineD.add(req1);
					listTramDStationCoord.add(p);
				}
				this.getDisplay().getComputer().setGraphNorthWestToSouthEast(listTramDStationCoord);
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("TramLineA is empty");
				JOptionPane.showMessageDialog(null, "TramLineA empty", "Error message", JOptionPane.ERROR_MESSAGE);
			}

			this.getContentPaneCenter().setVisible(true);

			JOptionPane.showMessageDialog(null, "You have generated the existing city", "Existing city generated !",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void MouseEvent() {
		WidthB.getFont().deriveFont(Font.ITALIC);
		WidthB.setForeground(Color.gray);
		HeightB.getFont().deriveFont(Font.ITALIC);
		HeightB.setForeground(Color.gray);
		Budget.getFont().deriveFont(Font.ITALIC);
		Budget.setForeground(Color.gray);
		StationCost.getFont().deriveFont(Font.ITALIC);
		StationCost.setForeground(Color.gray);
		WidthB.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				WidthB = ((JTextField) e.getSource());
				WidthB.setText("");
				WidthB.getFont().deriveFont(Font.PLAIN);
				WidthB.setForeground(Color.black);
				WidthB.removeMouseListener(this);
			}

		});

		HeightB.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				HeightB = ((JTextField) e.getSource());
				HeightB.setText("");
				HeightB.getFont().deriveFont(Font.PLAIN);
				HeightB.setForeground(Color.black);
				HeightB.removeMouseListener(this);
			}

		});

		Budget.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Budget = ((JTextField) e.getSource());
				Budget.setText("");
				Budget.getFont().deriveFont(Font.PLAIN);
				Budget.setForeground(Color.black);
				Budget.removeMouseListener(this);
			}

		});

		StationCost.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				StationCost = ((JTextField) e.getSource());
				StationCost.setText("");
				StationCost.getFont().deriveFont(Font.PLAIN);
				StationCost.setForeground(Color.black);
				StationCost.removeMouseListener(this);
			}

		});

	}

	public void mouseWheelMoved(MouseWheelEvent arg0) {
		if (arg0.getPreciseWheelRotation() < 0) {
			this.getDisplay().getComputer().setWidthKM(this.getDisplay().getComputer().getWidthKM()
					- (this.getDisplay().getComputer().getWidthKM() * 0.20));
			this.getDisplay().getComputer().setHeightKM(this.getDisplay().getComputer().getHeightKM()
					- (this.getDisplay().getComputer().getHeightKM() * 0.20));
			this.contentPaneCenter.setSize(3, 3);

		}
	}

	public JButton getSimulateCity() {
		return SimulateCity;
	}

	public void setSimulateCity(JButton simulateCity) {
		SimulateCity = simulateCity;
	}

	public JButton getGenerateCity() {
		return GenerateCity;
	}

	public void setGenerateCity(JButton generateCity) {
		GenerateCity = generateCity;
	}

	public JButton getReload() {
		return Reload;
	}

	public void setReload(JButton reload) {
		Reload = reload;
	}

	public JButton getSave() {
		return Save;
	}

	public void setSave(JButton save) {
		Save = save;
	}

	public JButton getRemoveCity() {
		return RemoveCity;
	}

	public void setRemoveCity(JButton removeCity) {
		RemoveCity = removeCity;
	}

	public JTextField getWidthB() {
		return WidthB;
	}

	public void setWidthB(JTextField widthB) {
		WidthB = widthB;
	}

	public JTextField getHeightB() {
		return HeightB;
	}

	public void setHeightB(JTextField heightB) {
		HeightB = heightB;
	}

	public JTextField getBudget() {
		return Budget;
	}

	public void setBudget(JTextField budget) {
		Budget = budget;
	}

	public JTextField getStationCost() {
		return StationCost;
	}

	public void setStationCost(JTextField stationCost) {
		StationCost = stationCost;
	}

	public JPanel getContentPaneCenter() {
		return contentPaneCenter;
	}

	public void setContentPaneCenter(JPanel contentPaneCenter) {
		this.contentPaneCenter = contentPaneCenter;
	}

	public JPanel getContentPaneTop() {
		return contentPaneTop;
	}

	public void setContentPaneTop(JPanel contentPaneTop) {
		this.contentPaneTop = contentPaneTop;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TramStationDisplayer getDisplay() {
		return display;
	}

	public void setDisplay(TramStationDisplayer display) {
		this.display = display;
	}

	public TramStationComputer getComputer() {
		return computer;
	}

	public void setComputer(TramStationComputer computer) {
		this.computer = computer;
	}
}

/*
 * public String sendMessagToServerSmartCity(String operationtype, String
 * target,SmartCity o ) throws IOException { Request request = new
 * Request(operationtype,target,o); mapper = new ObjectMapper(); String
 * outjsonString = mapper.writeValueAsString(request);
 * System.out.println("REQUEST SENT"); System.out.println(outjsonString);
 * System.out.println(" _____"); System.out.println(""); out.write(outjsonString
 * + "\n"); out.flush(); String injsonString = in.readLine(); response =
 * mapper.readValue(injsonString, Response.class); return response.toString(); }
 */

/*
 * public String sendMessagToServerStation(String operationtype, String
 * target,Station o ) throws IOException { Request request = new
 * Request(operationtype,target,o); mapper = new ObjectMapper(); String
 * outjsonString = mapper.writeValueAsString(request);
 * System.out.println("REQUEST SENT"); System.out.println(outjsonString);
 * System.out.println(" _____"); System.out.println(""); out.write(outjsonString
 * + "\n"); out.flush(); String injsonString = in.readLine(); response =
 * mapper.readValue(injsonString, Response.class); return response.toString(); }
 * 
 * 
 * public String sendMessagToServerDeleteStation(String operationtype, String
 * target) throws IOException { Request request = new
 * Request(operationtype,target); mapper = new ObjectMapper(); String
 * outjsonString = mapper.writeValueAsString(request);
 * System.out.println("REQUEST SENT"); System.out.println(outjsonString);
 * System.out.println(" _____"); System.out.println(""); out.write(outjsonString
 * + "\n"); out.flush(); String injsonString = in.readLine(); response =
 * mapper.readValue(injsonString, Response.class); return response.toString(); }
 * 
 * 
 * public boolean sendMessagToServerCityExists(String operationtype, String
 * target,Station t) throws IOException { Request request = new
 * Request(operationtype,target,t); mapper = new ObjectMapper(); String
 * outjsonString = mapper.writeValueAsString(request);
 * System.out.println("REQUEST SENT"); System.out.println(outjsonString);
 * System.out.println(" _____"); System.out.println(""); out.write(outjsonString
 * + "\n"); out.flush(); String injsonString = in.readLine(); response =
 * mapper.readValue(injsonString, Response.class); return response.isB(); }
 */
