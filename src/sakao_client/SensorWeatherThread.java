package sakao_client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.map.ObjectMapper;

import sakao_common.Request;
import sakao_common.Zone;
import javax.swing.JLabel;

public class SensorWeatherThread extends Thread {
	private JPanel contentPane1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1; 
	protected int idSensor;
	protected int idZone;
	protected AppStructureHandler app;
	private JFrame fenetre;

	public SensorWeatherThread(int idSensor, int idZone, AppStructureHandler app) {
		this.idSensor = idSensor;
		this.idZone = idZone;
		this.app = app;

		this.fenetre = new JFrame();
		fenetre.setTitle(String.valueOf(this.idSensor));
	
		lblNewLabel = new JLabel("The temperature is");
		lblNewLabel.setBounds(10, 11, 115, 21);
		
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(142, 11, 55, 21);
		
		
		this.contentPane1 = new JPanel();
		this.contentPane1.setLayout(null);
		this.contentPane1.add(lblNewLabel);
		this.contentPane1.add(lblNewLabel_1);
	}

	public void algoWeather(int temperature) throws IOException {
		ArrayList<String> azone = new ArrayList<String>();
		azone.add(String.valueOf(this.idZone));
		Request request1 = new Request("SELECT_ZONE", "zone", azone);
		ArrayList<String> al1 = this.app.sendMessageToServer(request1);
		Zone req2 = new ObjectMapper().readValue(al1.get(0), Zone.class);

		double d = req2.getThresholdBeta();
		
		if (temperature > 30) {
			d = req2.getThresholdBeta() * 0.85;
		} else if (temperature < 15) {
			d = req2.getThresholdBeta() * 1.15;
		}

		int beta = (int) d;
		ArrayList<String> azon = new ArrayList<String>();
		azon.add(String.valueOf(idZone));
		azon.add(String.valueOf(beta));
		Request request = new Request("UPDATE_BETA", "zone", azon);
		app.sendMessageToServer(request);

		String s1 = "{";
		String s2 = "}";
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add(s1);
		a2.add("temperature");
		a2.add(String.valueOf(temperature));
		a2.add("stateofthesky");
		if (temperature < 10) {
			a2.add("cloudy");
		} else {
			a2.add("nice");
		}
		a2.add("idsensor");
		a2.add(String.valueOf(idSensor));
		a2.add("idconfiguration");
		a2.add(null);
		a2.add(s2);
		Request request2 = new Request("INSERT", "weathersensor", a2);
		app.sendMessageToServer(request2);

	}

	public void run() {

		this.fenetre.setType(Type.UTILITY);
		this.fenetre.setResizable(false);
		this.fenetre.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		this.fenetre.setBounds(100, 100, 197, 120);

		contentPane1.setBackground(new Color(255, 215, 0));
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane1.setLayout(new BorderLayout(0, 0));

		
		
		
		
		int i = 0;
		while (i < 3) {
			try {
				Random random = new Random();
				int randomNumber = random.nextInt(50);
				lblNewLabel_1.setText(String.valueOf(randomNumber));
				this.algoWeather(randomNumber);
				
				this.fenetre.setContentPane(contentPane1);
				this.contentPane1.show(true);
				this.fenetre.show(true);
				Thread.sleep(10000);
				i++;
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}