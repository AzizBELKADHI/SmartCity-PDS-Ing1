package sakao_client;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.codehaus.jackson.map.ObjectMapper;

import sakao_common.AlerteStatistics;
import sakao_common.Request;
import sakao_common.Sensor;
import sakao_common.Zone;

import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.SliderUI;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;

public class AirQuality extends JPanel {
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private ArrayList<Integer> listSensorPollution = new ArrayList<Integer>();
	private ArrayList<Integer> listSensorWeather = new ArrayList<Integer>();

	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */

	public AirQuality(AppStructureHandler app) throws IOException {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 713, 410);
		add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 713, 410);
		panel.add(tabbedPane);

		// Panel Air pollution
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Air pollution", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 54, 498, 124);
		panel_1.add(scrollPane);

		String headerZone[] = new String[] { "Id", "Next To The Zone", "Location", "Threshold Beta" };

		Request request = new Request("SELECT_ALL", "zone");
		DefaultTableModel dtm1 = new DefaultTableModel(headerZone, 0);
		ArrayList<String> al = app.sendMessageToServer(request);

		Object[] temp = new Object[4];

		for (int i = 0; i < al.size(); i++) {
			String s = al.get(i) + "\n";
			Zone req1 = new ObjectMapper().readValue(s, Zone.class);
			temp[0] = req1.getIdZone();
			temp[1] = req1.getNextToTheZone();
			temp[2] = req1.getLocation();
			temp[3] = req1.getThresholdBeta();

			dtm1.addRow(temp);

		}

		table = new JTable();
		scrollPane.setViewportView(table);

		table.setModel(dtm1);

		JLabel lblNewLabel = new JLabel("Select the area to be edit");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(34, 22, 254, 14);
		panel_1.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(30, 41, 161, 2);
		panel_1.add(separator);

		JLabel lblNewLabel_1 = new JLabel("Nitrogen dioxide ( Hourly average : \u03B1 < 240 \u03BCg/m3 )\r\n");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(25, 211, 241, 23);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Ozone ( Daily average : \u03B1 < 120 \u03BCg/m3 )");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(301, 215, 193, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PM ( Daily average : \u03B1 < 50 \u03BCg/m3 )");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(25, 275, 168, 14);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Sulfur dioxide ( Hourly average : \u03B1 < 350 \u03BCg/m3 ) ");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(301, 271, 278, 23);
		panel_1.add(lblNewLabel_4);

		JSlider slider_1 = new JSlider();
		slider_1.setValue(0);
		slider_1.setMaximum(50);
		slider_1.setBounds(35, 300, 200, 26);
		Hashtable<Integer, JLabel> labels1 = new Hashtable<>();
		labels1.put(0, new JLabel("0"));
		labels1.put(25, new JLabel("25"));
		labels1.put(50, new JLabel("50"));
		slider_1.setLabelTable(labels1);
		slider_1.setPaintLabels(true);
		panel_1.add(slider_1);

		JSlider slider_2 = new JSlider();
		slider_2.setValue(0);
		slider_2.setMaximum(120);
		slider_2.setBounds(289, 238, 200, 26);
		Hashtable<Integer, JLabel> labels2 = new Hashtable<>();
		labels2.put(0, new JLabel("0"));
		labels2.put(40, new JLabel("40"));
		labels2.put(80, new JLabel("80"));
		labels2.put(120, new JLabel("120"));
		slider_2.setLabelTable(labels2);
		slider_2.setPaintLabels(true);
		panel_1.add(slider_2);

		JSlider slider_3 = new JSlider();
		slider_3.setValue(0);
		slider_3.setMaximum(350);
		slider_3.setBounds(289, 300, 200, 26);
		Hashtable<Integer, JLabel> labels3 = new Hashtable<>();
		labels3.put(0, new JLabel("0"));
		labels3.put(50, new JLabel("50"));
		labels3.put(125, new JLabel("125"));
		labels3.put(200, new JLabel("200"));
		labels3.put(275, new JLabel("275"));
		labels3.put(350, new JLabel("350"));
		slider_3.setLabelTable(labels3);
		slider_3.setPaintLabels(true);
		panel_1.add(slider_3);

		JLabel lblNewLabel_7 = new JLabel("Temperature");
		lblNewLabel_7.setFont(new Font("Times New Rom2an", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(579, 215, 84, 14);
		panel_1.add(lblNewLabel_7);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(20, -20, 49, 1));
		spinner.setBounds(580, 238, 57, 26);
		panel_1.add(spinner);

		JSlider slider = new JSlider();

		slider.setValue(0);
		slider.setMaximum(240);
		slider.setBounds(30, 238, 200, 26);
		Hashtable<Integer, JLabel> labels = new Hashtable<>();
		labels.put(0, new JLabel("0"));
		labels.put(80, new JLabel("80"));
		labels.put(160, new JLabel("160"));
		labels.put(240, new JLabel("240"));
		slider.setLabelTable(labels);
		slider.setPaintLabels(true);
		panel_1.add(slider);

		JButton btnNewButton = new JButton("Edit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table.getSelectedRow();
					int modelRow = table.convertRowIndexToModel(row);
					int idZone = (int) table.getValueAt(modelRow, 0);

					int nd = slider.getValue();
					int ozone = slider_2.getValue();
					int pm = slider_1.getValue();
					int sulfur = slider_3.getValue();

					int temp = (Integer) spinner.getValue();

					int s = 0;
					s += nd;
					s += ozone;
					s += pm;
					s += sulfur;
					double average = s / 4;

					if (temp > 30) {
						average = average * 0.85;
					} else if (temp < 15) {
						average = average * 1.15;
					}

					int beta = (int) average;

					ArrayList<String> azon = new ArrayList<String>();
					azon.add(String.valueOf(idZone));
					azon.add(String.valueOf(beta));
					Request request2 = new Request("UPDATE_BETA", "zone", azon);
					try {
						app.sendMessageToServer(request2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					DefaultTableModel dtm2 = new DefaultTableModel(headerZone, 0);
					ArrayList<String> al = app.sendMessageToServer(request);

					Object[] temp2 = new Object[4];

					for (int i = 0; i < al.size(); i++) {
						String s2 = al.get(i) + "\n";
						Zone req1 = new ObjectMapper().readValue(s2, Zone.class);
						temp2[0] = req1.getIdZone();
						temp2[1] = req1.getNextToTheZone();
						temp2[2] = req1.getLocation();
						temp2[3] = req1.getThresholdBeta();

						dtm2.addRow(temp2);

					}

					table = new JTable();
					scrollPane.setViewportView(table);

					table.setModel(dtm2);

					JOptionPane.showMessageDialog(null, "The zone alert threshold has been modified", "Alert threshold",
							JOptionPane.WARNING_MESSAGE);

				} catch (IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Please choose the area", "Choose area",
							JOptionPane.WARNING_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(34, 139, 34));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton.setBounds(583, 300, 99, 23);
		panel_1.add(btnNewButton);

		// Panel Launch of sensors

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Launch of sensors", null, panel_3, null);
		panel_3.setLayout(null);

		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(10, 59, 317, 166);
		panel_3.add(scrollPane3);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(381, 57, 317, 166);
		panel_3.add(scrollPane_1);

		table_1 = new JTable();

		JLabel lblNewLabel3 = new JLabel("Choose the pollution sensor");
		lblNewLabel3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel3.setBounds(34, 22, 254, 14);
		panel_3.add(lblNewLabel3);

		JButton btnNewButton_3 = new JButton("Start");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TO DO
				int row = table_1.getSelectedRow();
				int modelRow = table_1.convertRowIndexToModel(row);
				int idZone = (int) table_1.getValueAt(modelRow, 2);
				int idSensor = (int) table_1.getValueAt(modelRow, 0);

				SensorPollutionThread sensorPollutionThread = new SensorPollutionThread(idSensor, idZone, app);
				if (listSensorPollution.contains(idSensor)) {
					JOptionPane.showMessageDialog(null, "The sensor " + idSensor + " is already launched",
							"Start sensor", JOptionPane.INFORMATION_MESSAGE);
				} else {
					listSensorPollution.add(idSensor);
					sensorPollutionThread.start();
				}

			}
		});
		btnNewButton_3.setBackground(new Color(0, 0, 128));
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBounds(113, 254, 89, 23);
		panel_3.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Start");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_3.getSelectedRow();
				int modelRow = table_3.convertRowIndexToModel(row);
				int idZone = (int) table_3.getValueAt(modelRow, 2);
				int idSensor = (int) table_3.getValueAt(modelRow, 0);

				SensorWeatherThread sensorPollutionThread = new SensorWeatherThread(idSensor, idZone, app);
				if (listSensorWeather.contains(idSensor)) {
					JOptionPane.showMessageDialog(null, "The sensor " + idSensor + " is already launched",
							"Start sensor", JOptionPane.INFORMATION_MESSAGE);
				} else {
					listSensorWeather.add(idSensor);
					sensorPollutionThread.start();
				}

			}
		});
		btnNewButton_4.setBackground(new Color(0, 0, 128));
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBounds(490, 254, 89, 23);
		panel_3.add(btnNewButton_4);

		JLabel lblNewLabel_5 = new JLabel("Choose the weather sensor");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(432, 23, 204, 14);
		panel_3.add(lblNewLabel_5);

		JSeparator separator3 = new JSeparator();
		separator3.setBounds(30, 41, 161, 2);
		panel_3.add(separator3);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(353, 57, 27, 124);
		panel_3.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(429, 41, 161, 2);
		panel_3.add(separator_2);

		table_3 = new JTable();

		panel_1.add(btnNewButton);

		JButton btnNewButton_2 = new JButton("Load");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String headerZone[] = new String[] { "ID", "Type", "ID Zone", "Zone", "Beta" };
				DefaultTableModel dtm1 = new DefaultTableModel(headerZone, 0);

				Request request = new Request("SELECT_POLLUTION", "sensor");
				ArrayList<String> al;
				try {
					al = app.sendMessageToServer(request);

					Object[] temp = new Object[5];

					for (int i = 0; i < al.size(); i++) {
						String s = al.get(i) + "\n";
						Sensor req1 = new ObjectMapper().readValue(s, Sensor.class);
						temp[0] = req1.getIdSensor();
						temp[1] = req1.getSensorType();
						ArrayList<String> azone = new ArrayList<String>();
						azone.add(String.valueOf(req1.getIdZone()));
						Request request1 = new Request("SELECT_ZONE", "zone", azone);
						ArrayList<String> al1 = app.sendMessageToServer(request1);
						Zone req2 = new ObjectMapper().readValue(al1.get(0), Zone.class);
						temp[2] = req2.getIdZone();
						temp[3] = req2.getNextToTheZone();
						temp[4] = req2.getThresholdBeta();

						dtm1.addRow(temp);

					}

					table_1.setModel(dtm1);
					scrollPane3.setViewportView(table_1);

					String header[] = new String[] { "ID", "Type", "ID Zone", "Zone", "Beta" };
					DefaultTableModel dtm2 = new DefaultTableModel(header, 0);

					Request request1 = new Request("SELECT_WEATHER", "sensor");
					ArrayList<String> al1 = app.sendMessageToServer(request1);
					Object[] temp1 = new Object[5];

					for (int i = 0; i < al1.size(); i++) {
						String s = al1.get(i) + "\n";
						Sensor req1 = new ObjectMapper().readValue(s, Sensor.class);
						temp1[0] = req1.getIdSensor();
						temp1[1] = req1.getSensorType();
						ArrayList<String> azone = new ArrayList<String>();
						azone.add(String.valueOf(req1.getIdZone()));
						Request request2 = new Request("SELECT_ZONE", "zone", azone);
						ArrayList<String> al2 = app.sendMessageToServer(request2);
						Zone req2 = new ObjectMapper().readValue(al2.get(0), Zone.class);
						temp1[2] = req2.getIdZone();
						temp1[3] = req2.getNextToTheZone();
						temp1[4] = req2.getThresholdBeta();
						dtm2.addRow(temp1);

					}

					table_3.setModel(dtm2);

					scrollPane_1.setViewportView(table_3);

				} catch (IOException e1) { // TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_2.setBackground(new Color(0, 128, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(291, 19, 89, 23);
		panel_3.add(btnNewButton_2);

		// Panel Historic
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Historic", null, panel_2, null);
		panel_2.setLayout(null);

		JCalendar calendar = new JCalendar();
		calendar.setBounds(40, 67, 229, 189);
		panel_2.add(calendar);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(337, 67, 319, 189);
		panel_2.add(scrollPane_2);

		table_2 = new JTable();
		String headerAlert[] = new String[] { "ID", "Date Added", "Sensor Type" };

		scrollPane_2.setViewportView(table_2);

		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm3 = new DefaultTableModel(headerAlert, 0);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = "";
				ArrayList<String> alDate = new ArrayList<String>();

				String sdate = String.valueOf(calendar.getYearChooser().getYear()) + "-"
						+ String.valueOf(calendar.getMonthChooser().getMonth() + 1) + "-"
						+ String.valueOf(calendar.getDayChooser().getDay());
				alDate.add(sdate);
				Request request = new Request("SELECT_ALERTDATE", "sensor", alDate);

				try {
					ArrayList<String> al1 = app.sendMessageToServer(request);

					Object[] temp2 = new Object[3];

					for (int i = 0; i < al1.size(); i++) {
						String s = al1.get(i) + "\n";
						AlerteStatistics req1;

						req1 = new ObjectMapper().readValue(s, AlerteStatistics.class);

						strDate = dateFormat.format(req1.getDateAjout());

						temp2[0] = req1.getIdAlerteStatistics();
						temp2[1] = strDate;
						ArrayList<String> asensor = new ArrayList<String>();
						asensor.add(String.valueOf(req1.getIdSensor()));

						Request request2 = new Request("SELECT_IDSENSOR", "sensor", asensor);
						ArrayList<String> al2 = app.sendMessageToServer(request2);
						Sensor req2;

						req2 = new ObjectMapper().readValue(al2.get(0), Sensor.class);

						temp2[2] = req2.getSensorType();

						dtm3.addRow(temp2);

					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				table_2.setModel(dtm3);

			}
		});

		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 100, 0));
		btnNewButton_1.setBounds(336, 29, 89, 23);
		panel_2.add(btnNewButton_1);

		JLabel lblNewLabel_6 = new JLabel("Choose the date to display the alerts triggered");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(27, 26, 257, 27);
		panel_2.add(lblNewLabel_6);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(27, 49, 257, 7);
		panel_2.add(separator_3);

	}

}
