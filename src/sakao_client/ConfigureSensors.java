package sakao_client;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import sakao_server.ConfigurationService;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import sakao_common.Configuration;
import sakao_common.Request;
import sakao_common.Response;
import sakao_common.Sensor;
import sakao_common.Zone;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;

import java.awt.SystemColor;
import javax.swing.JComboBox;

class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}

class ButtonEditor extends DefaultCellEditor {

	protected JButton button;
	private String label;
	private boolean isPushed;
	private int id;
	private AppStructureHandler app;

	public ButtonEditor(JCheckBox checkBox, AppStructureHandler app) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
		this.app = app;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());

		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		int modelRow = table.convertRowIndexToModel(row);
		id = (int) table.getValueAt(modelRow, 0);
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	@Override
	public Object getCellEditorValue() {
		if (isPushed && label.equals("false")) {
			int dialogUpdate = JOptionPane.showConfirmDialog(null, "Are you sure you want to install this Sensor?",
					"INSTALL SENSOR", JOptionPane.YES_NO_OPTION);
			if (dialogUpdate == JOptionPane.YES_OPTION) {
				ArrayList<String> a = new ArrayList<String>();
				a.add(String.valueOf(id));
				a.add("true");
				Request request = new Request("UPDATE", "sensor", a);
				try {
					ArrayList<String> al = app.sendMessageToServer(request);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		isPushed = false;
		return label;
	}

	@Override
	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}
}

public class ConfigureSensors<E> extends JPanel {
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_1;

	/**
	 * Create the panel.
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public ConfigureSensors(AppStructureHandler app) throws ClassNotFoundException, IOException {

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 713, 410);
		add(panel);
		panel.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(191, 205, 219));
		tabbedPane.setFont(new Font("Arial", Font.ITALIC, 11));
		tabbedPane.setBounds(0, 0, 713, 410);
		panel.add(tabbedPane);

		JPanel panelSensors = new JPanel();
		tabbedPane.addTab("List of Sensors", null, panelSensors, null);
		panelSensors.setLayout(null);

		JPanel panelAddSensor = new JPanel();
		tabbedPane.addTab("Add Sensor", null, panelAddSensor, null);
		panelAddSensor.setLayout(null);

		JLabel lblAddSensor = new JLabel("Add a new sensor");
		lblAddSensor.setForeground(SystemColor.textHighlight);
		lblAddSensor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSensor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblAddSensor.setBounds(210, 25, 277, 32);
		panelAddSensor.add(lblAddSensor);

		JLabel lblNewLabel = new JLabel("Sensor state");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(44, 96, 99, 21);
		panelAddSensor.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sensor type\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(44, 128, 99, 32);
		panelAddSensor.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("IP Address");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(44, 171, 99, 21);
		panelAddSensor.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("MAC Address");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(44, 217, 99, 21);
		panelAddSensor.add(lblNewLabel_3);

		String state[] = { "Work" };
		JComboBox comboBox = new JComboBox(state);
		comboBox.setBounds(177, 96, 131, 22);
		panelAddSensor.add(comboBox);

		String type[] = { "Pollution", "Weather", "Vehicle" };
		JComboBox comboBox_1 = new JComboBox(type);
		comboBox_1.setBounds(177, 134, 131, 22);
		panelAddSensor.add(comboBox_1);

		textField = new JTextField();
		textField.setBounds(177, 172, 131, 30);
		panelAddSensor.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(177, 213, 131, 31);
		panelAddSensor.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_2 = new JButton("Submit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					textField.setForeground(Color.BLACK);
					textField_1.setForeground(Color.BLACK);
					int row = table_1.getSelectedRow();
					int modelRow = table_1.convertRowIndexToModel(row);
					int idZone = (int) table_1.getValueAt(modelRow, 0);
					String s1 = "{";
					String s2 = "}";
					String state = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
					String type = (String) comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
					String ipaddress = "";
					String macaddress = "";

					final String IP_ADDRESS_PATTERN = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}";
					final String MAC_ADDRESS_PATTERN = "\\w\\w:\\w\\w:\\w\\w:\\w\\w:\\w\\w:\\w\\w";

					Matcher matcherip = Pattern.compile(IP_ADDRESS_PATTERN).matcher(textField.getText());
					Matcher matchermac = Pattern.compile(MAC_ADDRESS_PATTERN).matcher(textField_1.getText());

					if (!matcherip.find() && !matchermac.find()) {
						textField.setForeground(Color.RED);
						textField.setText("Incorrect IP address");
						textField_1.setForeground(Color.RED);
						textField_1.setText("Incorrect MAC address");
					}
					Matcher matcherip2 = Pattern.compile(IP_ADDRESS_PATTERN).matcher(textField.getText());
					Matcher matchermac2 = Pattern.compile(MAC_ADDRESS_PATTERN).matcher(textField_1.getText());
					if (matcherip2.find() && !matchermac2.find()) {
						textField_1.setForeground(Color.RED);
						textField_1.setText("Incorrect MAC address");

					}
					Matcher matcherip3 = Pattern.compile(IP_ADDRESS_PATTERN).matcher(textField.getText());
					Matcher matchermac3 = Pattern.compile(MAC_ADDRESS_PATTERN).matcher(textField_1.getText());
					if (matchermac3.find() && !matcherip3.find()) {
						textField.setForeground(Color.RED);
						textField.setText("Incorrect IP address");

					}
					Matcher matcherip4 = Pattern.compile(IP_ADDRESS_PATTERN).matcher(textField.getText());
					Matcher matchermac4 = Pattern.compile(MAC_ADDRESS_PATTERN).matcher(textField_1.getText());
					if (matcherip4.find() && matchermac4.find()) {
						macaddress = textField_1.getText();
						ipaddress = textField.getText();
						ArrayList<String> a = new ArrayList<String>();
						a.add(s1);
						a.add("sensorstate");
						a.add(state);
						a.add("sensortype");
						a.add(type);
						a.add("idzone");
						a.add(String.valueOf(idZone));
						a.add("ipaddress");
						a.add(ipaddress);
						a.add("macaddress");
						a.add(macaddress);
						a.add("isinstalled");
						a.add("false");
						a.add(s2);
						Request request = new Request("INSERT", "sensor", a);
						app.sendMessageToServer(request);

						JOptionPane.showMessageDialog(null, "A new sensor is successfully added");
					}
				} catch (IOException | IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Please choose the area", "Choose area",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(34, 139, 34));
		btnNewButton_2.setBounds(371, 286, 89, 23);
		panelAddSensor.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Cancel");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textField.setText("");
				textField_1.setText("");

			}
		});
		btnNewButton_3.setBounds(255, 286, 89, 23);
		panelAddSensor.add(btnNewButton_3);

		String headerZone[] = new String[] { "Id", "Next To The Zone", "Location" };

		Request request = new Request("SELECT_ALL", "zone");
		DefaultTableModel dtm1 = new DefaultTableModel(headerZone, 0);
		ArrayList<String> al = app.sendMessageToServer(request);

		Object[] temp = new Object[3];

		for (int i = 0; i < al.size(); i++) {
			String s = al.get(i) + "\n";
			Zone req1 = new ObjectMapper().readValue(s, Zone.class);
			temp[0] = req1.getIdZone();
			temp[1] = req1.getNextToTheZone();
			temp[2] = req1.getLocation();

			dtm1.addRow(temp);

		}

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(346, 96, 333, 144);
		panelAddSensor.add(scrollPane_1);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(346, 83, 149, 2);
		panelAddSensor.add(separator_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(260, 54, 177, 2);
		panelAddSensor.add(separator);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		table_1.setModel(dtm1);

		JLabel lblNewLabel_4 = new JLabel("Please select sensor zone");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(354, 68, 252, 14);
		panelAddSensor.add(lblNewLabel_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 76, 589, 261);
		panelSensors.add(scrollPane);

		table = new JTable();

		String header[] = new String[] { "Id", "State", "Type", "IP Address", "MAC Address", "isInstalled" };

		scrollPane.setViewportView(table);
		JButton btLoadConfig = new JButton("Load");
		btLoadConfig.setBackground(new Color(0, 153, 0));
		btLoadConfig.setForeground(SystemColor.window);

		btLoadConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					try {
						Request request = new Request("SELECT_ALL", "sensor");
						DefaultTableModel dtm = new DefaultTableModel(header, 0);
						ArrayList<String> al = app.sendMessageToServer(request);

						Object[] temp = new Object[6];

						for (int i = 0; i < al.size(); i++) {
							String s = al.get(i) + "\n";
							Sensor req1 = new ObjectMapper().readValue(s, Sensor.class);
							temp[0] = req1.getIdSensor();
							temp[1] = req1.getSensorState();
							temp[2] = req1.getSensorType();
							temp[3] = req1.getIpAddress();
							temp[4] = req1.getMacAddress();
							temp[5] = req1.getIsInstalled();

							dtm.addRow(temp);

						}

						table.setModel(dtm);

						table.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());

						// SET CUSTOM EDITOR TO TEAMS COLUMN
						table.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox(), app));

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btLoadConfig.setBounds(453, 42, 89, 23);
		panelSensors.add(btLoadConfig);

		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table.getSelectedRow();
					int modelRow = table.convertRowIndexToModel(row);
					int i = (int) table.getValueAt(modelRow, 0);
					int dialogDelete = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete this Sensor?", "DELETE SENSOR", JOptionPane.YES_NO_OPTION);
					if (dialogDelete == JOptionPane.YES_OPTION) {
						ArrayList<String> a = new ArrayList<String>();
						a.add(String.valueOf(i));
						Request request = new Request("DELETE", "sensor", a);
						try {
							ArrayList<String> al = app.sendMessageToServer(request);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Please select the sensor to delete", "Choose sensor",
							JOptionPane.WARNING_MESSAGE);

				}
			}
		});

		btnNewButton.setBackground(new Color(204, 0, 0));
		btnNewButton.setForeground(SystemColor.window);
		btnNewButton.setBounds(552, 42, 89, 23);
		panelSensors.add(btnNewButton);

	}
}
