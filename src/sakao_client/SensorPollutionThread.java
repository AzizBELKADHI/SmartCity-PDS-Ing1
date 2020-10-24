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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.codehaus.jackson.map.ObjectMapper;

import sakao_common.Request;
import sakao_common.Zone;
import java.awt.Font;

public class SensorPollutionThread extends Thread {
	private JPanel contentPane1;
	private JPanel contentPane2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1; 
	private JLabel lblNewLabel2;
	private JLabel lblNewLabel_2; 
	protected int idSensor;
	protected int idZone;
	protected AppStructureHandler app;
	private JFrame fenetre;

	
	public SensorPollutionThread(int idSensor, int idZone, AppStructureHandler app) {
		this.idSensor = idSensor;
		this.idZone = idZone;
		this.app = app;
		
		this.fenetre = new JFrame();
		fenetre.setTitle(String.valueOf(this.idSensor));
		this.contentPane1 = new JPanel();
		this.contentPane2 = new JPanel();
		
		lblNewLabel = new JLabel("Beta calculated is");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 115, 21);
		lblNewLabel.setForeground(Color.white);
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(142, 11, 55, 21);
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel2 = new JLabel("Beta calculated is");
		lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel2.setBounds(10, 11, 115, 21);
		lblNewLabel2.setForeground(Color.white);
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(142, 11, 55, 21);
		lblNewLabel_2.setForeground(Color.white);
		
		this.contentPane1.add(lblNewLabel);
		this.contentPane1.add(lblNewLabel_1);
		
		this.contentPane2.add(lblNewLabel2);
		this.contentPane2.add(lblNewLabel_2);
	}


	
	
	public boolean algoPollution(int beta) throws IOException {

		boolean test = false;
		ArrayList<String> azone = new ArrayList<String>();
		azone.add(String.valueOf(this.idZone));
		Request request1 = new Request("SELECT_ZONE", "zone", azone);
		ArrayList<String> al1 = this.app.sendMessageToServer(request1);
		Zone req2 = new ObjectMapper().readValue(al1.get(0), Zone.class);

		if (beta >= req2.getThresholdBeta()) {

			String s1 = "{";
			String s2 = "}";
			Date actuelle = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dat = dateFormat.format(actuelle);
			ArrayList<String> a = new ArrayList<String>();
			a.add(s1);
			a.add("dateajout");
			a.add(dat);
			a.add("idsensor");
			a.add(String.valueOf(this.idSensor));
			a.add(s2);
			Request request = new Request("INSERT", "alert", a);
			app.sendMessageToServer(request);

			ArrayList<String> azon = new ArrayList<String>();
			azon.add(String.valueOf(idZone));
			azon.add("true");
			Request request2 = new Request("UPDATE", "zone", azon);

			app.sendMessageToServer(request2);

			test = true;
		}

		String s1 = "{";
		String s2 = "}";
		ArrayList<String> a = new ArrayList<String>();
		a.add(s1);
		a.add("betaaverage");
		a.add(String.valueOf(beta));
		a.add("idconfiguration");
		a.add(null);
		a.add("idsensor");
		a.add(String.valueOf(idSensor));
		a.add(s2);
		Request request = new Request("INSERT", "pollutionsensor", a);
		app.sendMessageToServer(request);

		return test;

	}

	public void run() {
		
		this.fenetre.setType(Type.UTILITY);
		this.fenetre.setResizable(false);
		this.fenetre.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		this.fenetre.setBounds(100, 100, 197, 161);

		contentPane1.setBackground(new Color(0, 128, 0));
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane1.setLayout(null);

		contentPane2.setBackground(new Color(255, 0, 0));
		contentPane2.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane2.setLayout(null);
		int i = 0;
		while (i < 10) {
			try {
				Random random = new Random();
				int randomNumber = random.nextInt(300);
				
				if (!this.algoPollution(randomNumber)) {
					lblNewLabel_1.setText(String.valueOf(randomNumber));
					this.fenetre.setContentPane(contentPane1);
					this.contentPane1.show(true);
				} else {
					lblNewLabel_2.setText(String.valueOf(randomNumber));
					this.fenetre.setContentPane(contentPane2);
					this.contentPane2.show(true);
				}
				this.fenetre.show(true);
				Thread.sleep(8000);
				i++;
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}