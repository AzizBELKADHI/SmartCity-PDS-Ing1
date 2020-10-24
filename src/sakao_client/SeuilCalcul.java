package sakao_client;
/* Oumaima Code*/

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import sakao_common.Request;
import sakao_common.SmartCity;
import sakao_common.Transportation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;

public class SeuilCalcul extends JPanel {
	private JTextField textField;
	private JTextField nbUsesTram90;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_4;
	private JTextField textField_1;
	private int NB_VEHICLES;
	private int NB_TRAM;
	private int NB_BIKE;
	private int NB_Ped;
	private double vehicalPollution;
	private double tramPollution;
	private double cyclistPollution;
	public static final int NB_POPULATION = 10000;

	public SeuilCalcul(AppStructureHandler appStructure) throws IOException, JSONException {

		setLayout(null);
		Request request = new Request("SELECT_ALL", "empreinte");
		ArrayList<String> al;

		try {
			al = appStructure.sendMessageToServer(request);

			String s = al.get(0) + "\n";

			SmartCity req1 = new ObjectMapper().readValue(s, SmartCity.class);

		} catch (IOException e1) {
			e1.printStackTrace();

		}

		Request requestT = new Request("SELECT_ALL", "transportation");
		ArrayList<String> listTransportation;

		try {

			listTransportation = appStructure.sendMessageToServer(requestT);



			for (int i = 0; i < listTransportation.size(); i++) {
				
				String s = listTransportation.get(i) + "\n";

				Transportation reqT = new ObjectMapper().readValue(s, Transportation.class);

				String TypeOfTransport = reqT.getTypeoftransport();
				System.out.println(TypeOfTransport);
				if (TypeOfTransport.equals("Tram")) {

					NB_TRAM = reqT.getDailytransportusercount();
					tramPollution =reqT.getAverageofco2releasedbytransport();
					

				}
				if (TypeOfTransport.equals("Vehicle")) {

					NB_VEHICLES = reqT.getDailytransportusercount();
					vehicalPollution =reqT.getAverageofco2releasedbytransport();

				}
				if (TypeOfTransport.equals("Bike")) {

					NB_BIKE = reqT.getDailytransportusercount();
					cyclistPollution = reqT.getAverageofco2releasedbytransport();
					

				}

				
				NB_Ped = NB_POPULATION - (NB_TRAM +NB_VEHICLES+ NB_BIKE);
				
				

			}
			/*
			 * System.out.println("////////////////////////////////////////////////");
			 * System.out.println(NB_TRAM);
			 * System.out.println("////////////////////////////////////////////////");
			 */
			System.out.println("cyclistPollution");
			System.out.println(cyclistPollution);
			System.out.println("vehicalPollution");
			System.out.println(vehicalPollution);
			System.out.println();

		} catch (Exception e) {
			// TODO: handle exception
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 713, 410);
		add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("the current travel policy ", null, panel_1, null);
		panel_1.setLayout(null);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Estimation", null, panel, null);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(286, 187, 96, 19);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(286, 297, 96, 19);
		textField_1.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(286, 235, 96, 19);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setText(Integer.toString(NB_VEHICLES));
		textField_4.setBounds(259, 91, 96, 19);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setText(Integer.toString(NB_TRAM));
		textField_5.setBounds(259, 133, 96, 19);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setText(Integer.toString(NB_BIKE));
		textField_6.setBounds(259, 192, 96, 19);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setText(Integer.toString(NB_Ped));
		textField_7.setBounds(259, 257, 96, 19);
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(259, 304, 96, 19);
		textField_8.setColumns(10);

		nbUsesTram90 = new JTextField();
		nbUsesTram90.setBounds(286, 143, 96, 19);
		nbUsesTram90.setColumns(10);

		JLabel lblPiton = new JLabel("\r\n" + "the number of pedestrians");
		lblPiton.setBounds(53, 260, 133, 13);

		JLabel lblBetaaverage = new JLabel("BetaAverage");
		lblBetaaverage.setBounds(53, 304, 133, 19);

		JLabel lblTheNumberOf = new JLabel("the number of vehicles present in the city");
		lblTheNumberOf.setBounds(53, 94, 196, 13);

		JLabel msgToDisplay = new JLabel("");
		msgToDisplay.setBounds(180, 300, 45, 13);

		JLabel lblUtilisateurDeTram = new JLabel("the number of tramway users");
		lblUtilisateurDeTram.setBounds(53, 149, 196, 13);

		JLabel lblUtilisateurDeVelo = new JLabel("the number of bike users");
		lblUtilisateurDeVelo.setBounds(53, 195, 172, 13);

		JLabel lblPedestrians = new JLabel("\r\n the number of pedestrians");
		lblPedestrians.setBounds(32, 190, 193, 13);

		JLabel label_1 = new JLabel(" the number of bike users\r\n");
		label_1.setBounds(32, 238, 193, 13);

		JLabel label_2 = new JLabel("\r\n the number of vehicle");
		label_2.setBounds(32, 98, 133, 13);

		JLabel label_3 = new JLabel("\r\n the number of tramway users");
		label_3.setBounds(32, 146, 162, 13);

		JButton btnConclusion = new JButton("conclusion");
		btnConclusion.setBounds(536, 341, 112, 21);
		btnConclusion.setVisible(false);

		JButton btnNewButton = new JButton("Load\r\n");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.setBounds(42, 39, 85, 21);

		JButton btnReleod = new JButton("estimation");
		btnReleod.setBounds(536, 296, 112, 21);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(286, 95, 96, 20);
		spinner.setModel(new SpinnerNumberModel(0, 0, 4000, 1));

		JLabel lblThresholdCalculate = new JLabel("threshold calculate");
		lblThresholdCalculate.setBounds(29, 300, 125, 13);
		panel.add(lblThresholdCalculate);

		JSeparator separator = new JSeparator();
		separator.setBounds(138, 276, 510, 2);

		panel_1.add(textField_4);
		panel_1.add(textField_5);
		panel_1.add(textField_6);
		panel_1.add(textField_7);
		panel_1.add(textField_8);
		panel_1.add(lblPiton);
		panel_1.add(lblBetaaverage);
		panel_1.add(lblUtilisateurDeVelo);
		panel_1.add(lblUtilisateurDeTram);
		panel_1.add(lblTheNumberOf);
		panel_1.add(btnNewButton);

		panel.add(lblPedestrians);
		panel.add(nbUsesTram90);
		panel.add(label_1);
		panel.add(label_2);
		panel.add(label_3);
		panel.add(spinner);
		panel.add(textField);
		panel.add(textField_3);
		panel.add(textField_1);
		panel.add(separator);
		panel.add(msgToDisplay);
		panel.add(btnConclusion);
		panel.add(btnReleod);

		// Action to calculate/load BetAverage
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculS calculS = new CalculS(vehicalPollution,tramPollution,cyclistPollution);
				Request request = new Request("SELECT_ALL", "empreinte");
				ArrayList<String> al;
				try {
					al = appStructure.sendMessageToServer(request);
					String s = al.get(0) + "\n";
					SmartCity req1 = new ObjectMapper().readValue(s, SmartCity.class);
					double calcul1 = calculS.estimatedThreshold(NB_VEHICLES, req1.getHeightkm(), NB_BIKE, NB_TRAM,
							NB_Ped);
					String calcul = String.valueOf(calcul1);
					DecimalFormat numberFormat = new DecimalFormat("#.00");
					String limitCalcul = numberFormat.format(calcul1);

					if (limitCalcul.contains(",")) {
						limitCalcul = limitCalcul.replace(",", ".");
					}
					textField_8.setText(limitCalcul);

					if (!textField_1.getText().equals("") && !textField_8.getText().equals("")) {
						btnConclusion.setVisible(true);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		// Action to estimate pollution threshold
		btnReleod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculS calculS = new CalculS();

				Request request = new Request("SELECT_ALL", "empreinte");
				ArrayList<String> al;
				try {
					al = appStructure.sendMessageToServer(request);

					if ((textField.getText().equals("") || Integer.parseInt(textField.getText()) <= 0)
							|| (nbUsesTram90.getText().equals("") || Integer.parseInt(nbUsesTram90.getText()) <= 0)
							|| (textField_3.getText().equals("") || Integer.parseInt(textField_3.getText()) <= 0)
							|| (spinner.toString().isEmpty() || ((Integer) spinner.getValue() <= 0))) {
						JOptionPane.showMessageDialog(null,
								"The inputs are incorrects, all the inputs must be greater then 0");
					} else {
						String s = al.get(0) + "\n";
						SmartCity req1 = new ObjectMapper().readValue(s, SmartCity.class);
						double calcul1 = calculS.estimatedThreshold((Integer) spinner.getValue(), req1.getHeightkm(),
								Integer.parseInt(textField_3.getText()), Integer.parseInt(nbUsesTram90.getText()),
								Integer.parseInt(textField.getText()));
						double sumFields = Integer.parseInt(textField_3.getText())
								+ Integer.parseInt(nbUsesTram90.getText()) + Integer.parseInt(textField.getText())
								+ (Integer) spinner.getValue();
						if (sumFields > NB_POPULATION) {
							JOptionPane.showMessageDialog(null,
									"The sums of the users of transport must be less than the number of population");
						} else {

							String calcul = String.valueOf(calcul1);
							DecimalFormat numberFormat = new DecimalFormat("#.00");
							String limitCalcul = numberFormat.format(calcul1);

							if (limitCalcul.contains(",")) {
								limitCalcul = limitCalcul.replace(",", ".");
							}
							textField_1.setText(limitCalcul);

							if (!textField_1.getText().equals("") && !textField_8.getText().equals("")) {
								btnConclusion.setVisible(true);

							}
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		// Action to display conclusion
		btnConclusion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Double param1 = Double.parseDouble(textField_1.getText());
					Double param2 = Double.parseDouble(textField_8.getText());

					if (param1 < param2) {
						JOptionPane.showMessageDialog(null,
								"the proposed travel policy is better than the current travel policy");
					} else {
						JOptionPane.showMessageDialog(null,
								"the current travel policy is better than the proposed travel policy");

					}

				} catch (Exception expt) {
					expt.printStackTrace();

				}
			}
		});

	}
}
