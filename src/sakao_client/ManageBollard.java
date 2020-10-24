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
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import sakao_common.Bollard;
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

///////////////////////////

class ButtonRenderer2 extends JButton implements TableCellRenderer {

	public ButtonRenderer2() {
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

class ButtonEditor2 extends DefaultCellEditor {

	protected JButton button;
	private String label;
	private boolean isPushed;
	private int id;
	private AppStructureHandler app;
	private JButton btn = null;
	public ButtonEditor2(JCheckBox checkBox, AppStructureHandler app,JButton btn) {
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
		this.btn = btn;
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
			int dialogUpdate = JOptionPane.showConfirmDialog(null, "Are you sure you want to install this Bollard?",
					"INSTALL BOLLARD", JOptionPane.YES_NO_OPTION);
			if (dialogUpdate == JOptionPane.YES_OPTION) {
				ArrayList<String> a = new ArrayList<String>();
				a.add(String.valueOf(id));
				a.add("true");
				Request request = new Request("UPDATEinstall", "bollard", a);
				try {
					ArrayList<String> al = app.sendMessageToServer(request);
					
					btn.doClick();
					
				
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


//////////////////////
public class ManageBollard extends JPanel {

	private JTextField textFieldBollard_1;
	private JTextField textFieldBollard_2;
	private JTable table_2;
	private JTable table2;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public ManageBollard(AppStructureHandler app) throws IOException {
		
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
		
		
		JPanel panelBollards = new JPanel();
		tabbedPane.addTab("List of Bollard", null, panelBollards, null);
		panelBollards.setLayout(null);
		
		JPanel panelAddBollard = new JPanel();
		tabbedPane.addTab("Add Bollard", null, panelAddBollard, null);
		panelAddBollard.setLayout(null);

		JLabel lblAddBollard = new JLabel("Add a new bollard");
		lblAddBollard.setForeground(SystemColor.textHighlight);
		lblAddBollard.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddBollard.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblAddBollard.setBounds(210, 25, 277, 32);
		panelAddBollard.add(lblAddBollard);
		
		JLabel lblNewLabelBollard = new JLabel("Bollard state");
		lblNewLabelBollard.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelBollard.setBounds(44, 96, 99, 21);
		panelAddBollard.add(lblNewLabelBollard);
		

		JLabel lblNewLabelBollard_2 = new JLabel("IP Address");
		lblNewLabelBollard_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelBollard_2.setBounds(44, 128, 99, 32);
		panelAddBollard.add(lblNewLabelBollard_2);
		

		JLabel lblNewLabelBollard_3 = new JLabel("MAC Address");
		lblNewLabelBollard_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabelBollard_3.setBounds(44, 171, 99, 21);
		panelAddBollard.add(lblNewLabelBollard_3);
		
		
		String Bollardstate[] = { "lower", "lifted" };
		JComboBox comboBoxBollard = new JComboBox(Bollardstate);
		comboBoxBollard.setBounds(177, 96, 107, 22);
		panelAddBollard.add(comboBoxBollard);
		
		textFieldBollard_1 = new JTextField();
		textFieldBollard_1.setBounds(177, 134, 107, 22);
		panelAddBollard.add(textFieldBollard_1);
		textFieldBollard_1.setColumns(10);
		

		textFieldBollard_2 = new JTextField();
		textFieldBollard_2.setBounds(177, 172, 107, 22);
		;
		panelAddBollard.add(textFieldBollard_2);
		textFieldBollard_2.setColumns(10);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JButton btnNewBollard_1 = new JButton("Submit");
		JButton btLoadConfig2 = new JButton("Load");
		btnNewBollard_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int row = table_2.getSelectedRow();
					int modelRow = table_2.convertRowIndexToModel(row);
					int idZone = (int) table_2.getValueAt(modelRow, 0);
					String s1 = "{";
					String s2 = "}";
					String Bstate = (String) comboBoxBollard.getItemAt(comboBoxBollard.getSelectedIndex());

					String ipaddress = textFieldBollard_1.getText().toString();
					String macaddress = textFieldBollard_2.getText().toString();

					if ((ipaddress.length() != 0) && (macaddress.length() != 0)) {
						ArrayList<String> b = new ArrayList<String>();
						b.add(s1);
						b.add("bollardstate");
						if (Bstate == "lifted") {
							b.add("true");
						} else {
							b.add("false");
						}

						b.add("idzone");
						b.add(String.valueOf(idZone));
						b.add("isinstalled");
						b.add("false");
						b.add("ipaddress");
						b.add(ipaddress);
						b.add("macaddress");
						b.add(macaddress);
						b.add(s2);
						Request request = new Request("INSERT", "bollard", b);
						app.sendMessageToServer(request);
						btLoadConfig2.doClick();
						JOptionPane.showMessageDialog(null, "A new bollard is successfully added");
					} else {
						JOptionPane.showMessageDialog(null, "Please enter the ip address and Mac address", "",
								JOptionPane.WARNING_MESSAGE);

					}
				} catch (IOException | IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Please choose the area", "Choose area",
							JOptionPane.WARNING_MESSAGE);

				}

			}
		});

		btnNewBollard_1.setForeground(new Color(255, 255, 255));
		btnNewBollard_1.setBackground(new Color(34, 139, 34));
		btnNewBollard_1.setBounds(371, 286, 89, 23);
		panelAddBollard.add(btnNewBollard_1);

		JButton btnNewBollard_2 = new JButton("Cancel");
		btnNewBollard_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textFieldBollard_1.setText("");
				textFieldBollard_2.setText("");

			}
		});
		btnNewBollard_2.setBounds(255, 286, 89, 23);
		panelAddBollard.add(btnNewBollard_2);
		
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

		JScrollPane scrollPaneBollard_1 = new JScrollPane();
		scrollPaneBollard_1.setBounds(346, 96, 333, 144);
		panelAddBollard.add(scrollPaneBollard_1);
		
		JSeparator separatorBollard_1 = new JSeparator();
		separatorBollard_1.setBounds(346, 83, 149, 2);
		panelAddBollard.add(separatorBollard_1);
		
		JSeparator separatorBollard = new JSeparator();
		separatorBollard.setBounds(260, 54, 177, 2);
		panelAddBollard.add(separatorBollard);
		
		table_2 = new JTable();
		scrollPaneBollard_1.setViewportView(table_2);
		
		table_2.setModel(dtm1);
		
		JLabel lblNewLabelBollard_4 = new JLabel("Please select bolard's zone");
		lblNewLabelBollard_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabelBollard_4.setBounds(354, 68, 252, 14);
		panelAddBollard.add(lblNewLabelBollard_4);
		

		
		JScrollPane scrollBollard = new JScrollPane();
		scrollBollard.setBounds(52, 76, 589, 261);
		panelBollards.add(scrollBollard);
		
		table2 = new JTable();

		String headerBollard[] = new String[] { "Id", "BollardState", "IP Address", "MAC Address",
				"isInstalled"};

		scrollBollard.setViewportView(table2);
		
		btLoadConfig2.setBackground(new Color(0, 153, 0));
		btLoadConfig2.setForeground(SystemColor.window);

		btLoadConfig2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					try {
						
						Request request2 = new Request("SELECT_ALL", "bollard");
						DefaultTableModel dtm2 = new DefaultTableModel(headerBollard, 0);
						ArrayList<String> al2 = app.sendMessageToServer(request2);
						System.out.println(al2);
						Object[] temp = new Object[5];

						for (int i = 0; i < al2.size(); i++) {
							String s = al2.get(i) + "\n";
							
							System.out.println("oooooooook");
							Bollard req2 = new ObjectMapper().readValue(s, Bollard.class);
							temp[0] = req2.getIdBollard();
							temp[1] = req2.getIsBollardState();
							temp[2] = req2.getIpaddress();
							temp[3] = req2.getMacaddress();
							temp[4] = req2.getIsInstalled();
							System.out.println(req2);
							

							dtm2.addRow(temp);

						}

						table2.setModel(dtm2);
						
						

						table2.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer2());

						// SET CUSTOM EDITOR TO TEAMS COLUMN
						table2.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor2(new JCheckBox(), app,btLoadConfig2));
						//btLoadConfig2.doClick();

			
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btLoadConfig2.setBounds(453, 42, 89, 23);
		panelBollards.add(btLoadConfig2);

		JButton btnNewButton2 = new JButton("Delete");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = table2.getSelectedRow();
					int modelRow = table2.convertRowIndexToModel(row);
					int i = (int) table2.getValueAt(modelRow, 0);
					int dialogDelete2 = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to delete this bollard?", "DELETE bollard", JOptionPane.YES_NO_OPTION);
					if (dialogDelete2 == JOptionPane.YES_OPTION) {
						ArrayList<String> a = new ArrayList<String>();
						a.add(String.valueOf(i));
						Request request = new Request("DELETE", "bollard", a);
						
						try {
							ArrayList<String> al = app.sendMessageToServer(request);
							//////////////////////////////////////////////////////////////////////////////////////DO CLICK
							
							btLoadConfig2.doClick();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "Please choose the bollard to delete", "Choose bollard",
							JOptionPane.WARNING_MESSAGE);

				}
			}
		});

		btnNewButton2.setBackground(new Color(204, 0, 0));
		btnNewButton2.setForeground(SystemColor.window);
		btnNewButton2.setBounds(552, 42, 89, 23);
		panelBollards.add(btnNewButton2);
		
	}

}
