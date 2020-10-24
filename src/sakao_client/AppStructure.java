package sakao_client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_client_select.ConfigurationSelect;
import sakao_common.Request;
import sakao_common.Response;

import java.awt.Window.Type;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class AppStructure extends JFrame {

	/**
	 * 
	 */
	//private static final long serialVersionUID = -2914512782319038258L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException, JSONException {

	//	UIManager.setLookAndFeel(new NimbusLookAndFeel());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppStructure frame = new AppStructure();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ClassNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 */
	public AppStructure() throws ClassNotFoundException, IOException, JSONException {
		AppStructureHandler appStructure = new AppStructureHandler();
		//appStructure.startConnection("localhost", 3030);
		appStructure.startConnection("172.31.249.254", 3030);
		
		
		
		HomePage h = new HomePage();
		
		ManageBollard m = new ManageBollard(appStructure);
		ConfigureSensors s = new ConfigureSensors(appStructure);
		AirQuality a = new AirQuality(appStructure);
		SeuilCalcul seuil = new SeuilCalcul(appStructure);
		CreateCity c = new CreateCity();
		
	
		c.setVisible(false);
		s.setVisible(false);
		a.setVisible(false);
		seuil.setVisible(false);
		m.setVisible(false);
		
		
		JLabel image = new JLabel();
		ImageIcon im = new ImageIcon("/Logo.png");
		image.setIcon(im);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					appStructure.CloseConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		setBounds(100, 100, 917, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 901, 465);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.activeCaption);
		panel_6.setBounds(188, 55, 713, 410);
	
		panel_6.add(h);
		panel_6.add(c);
		panel_6.add(s);
		panel_6.add(a);
		panel_6.add(seuil);
		panel_6.add(m);
		h.setVisible(true);
		
		
		panel.add(panel_6);
		panel_6.setLayout(new CardLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBackground(new Color(0, 128, 0));
		panel_1.setBounds(0, 0, 188, 465);
		panel_1.add(image);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 128, 0));
		panel_5.setBounds(0, 255, 188, 43);
		panel_1.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("    Manage sensors");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.setVisible(false);
				h.setVisible(false);
				a.setVisible(false);
				seuil.setVisible(false);
				m.setVisible(false);
				s.setVisible(true);
			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgSensor = new ImageIcon(this.getClass().getResource("/sensor-icon.png")).getImage();
		Image newimgSensor = imgSensor.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel_2.setIcon(new ImageIcon(newimgSensor));

		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(6, 0, 150, 43);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel_5.add(lblNewLabel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 128, 0));
		panel_3.setBounds(0, 201, 188, 43);
		panel_1.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("    Create a city");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TramStationTracer tram;
				try {
					tram = new TramStationTracer(appStructure);
					a.setVisible(false);
					s.setVisible(false);
					h.setVisible(false);
					seuil.setVisible(false);
					m.setVisible(false);
					c.setVisible(true);
					tram.show();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		Image imgCity = new ImageIcon(this.getClass().getResource("/City-Monastery-icon.png")).getImage();
		Image newimgCity = imgCity.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel_1.setIcon(new ImageIcon(newimgCity));
		lblNewLabel_1.setBackground(SystemColor.windowBorder);

		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(10, 0, 152, 43);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 128, 0));
		panel_4.setBounds(0, 147, 188, 43);
		panel_1.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblNewLabel = new JLabel("    Home");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.setVisible(false);
				s.setVisible(false);
				a.setVisible(false);
				seuil.setVisible(false);
				m.setVisible(false);
				h.setVisible(true);

			}
		});
		lblNewLabel.setBounds(10, 0, 137, 43);
		panel_4.add(lblNewLabel);
		Image imgHome = new ImageIcon(this.getClass().getResource("/home-icon.png")).getImage();
		Image newimg = imgHome.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(newimg));

		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgSakao = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
		Image newimgSakao = imgSakao.getScaledInstance(130, 130, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel_4.setIcon(new ImageIcon(newimgSakao));
		lblNewLabel_4.setBounds(0, 0, 178, 136);
		panel_1.add(lblNewLabel_4);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(0, 128, 0));
		panel_7.setBounds(10, 316, 178, 43);
		panel_1.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("    Air quality sensor");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		Image imgAir = new ImageIcon(this.getClass().getResource("/air.png")).getImage();
		Image newimgAir = imgAir.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel_5.setIcon(new ImageIcon(newimgAir));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_5.setBounds(-11, 0, 172, 31);
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			
					c.setVisible(false);
					s.setVisible(false);
					h.setVisible(false);
					m.setVisible(false);
					seuil.setVisible(false);
					a.setVisible(true);
			
			
				

			}
		});
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 128, 0));
		panel_8.setForeground(new Color(0, 128, 0));
		panel_8.setBounds(0, 370, 188, 37);
		panel_1.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("    Manage Bollard");
		lblNewLabel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.setVisible(false);
				s.setVisible(false);
				h.setVisible(false);
				seuil.setVisible(false);
				a.setVisible(false);
				m.setVisible(true);
				
			}
		});
		lblNewLabel_6.setForeground(Color.WHITE);
		Image imgBol = new ImageIcon(this.getClass().getResource("/bollard.png")).getImage();
		Image newimgBol = imgBol.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel_6.setIcon(new ImageIcon(newimgBol));
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_6.setBounds(10, 0, 168, 37);
		panel_8.add(lblNewLabel_6);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0, 128, 0));
		panel_9.setBounds(0, 414, 188, 40);
		panel_1.add(panel_9);
		panel_9.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("    Pollution threshold");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.setVisible(false);
				s.setVisible(false);
				h.setVisible(false);
				seuil.setVisible(true);
				a.setVisible(false);
				m.setVisible(false);
				
			}
		});

		lblNewLabel_7.setForeground(Color.WHITE);
		Image imgThres = new ImageIcon(this.getClass().getResource("/threshold.png")).getImage();
		Image newimgThres = imgThres.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		lblNewLabel_7.setIcon(new ImageIcon(newimgThres));	
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_7.setBounds(10, 0, 168, 40);
		panel_9.add(lblNewLabel_7);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(25, 25, 112));
		panel_2.setBounds(188, 0, 713, 56);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Welcome to SAKAO application");
		lblNewLabel_3.setForeground(new Color(240, 248, 255));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(159, 6, 374, 34);
		panel_2.add(lblNewLabel_3);

	}
}
