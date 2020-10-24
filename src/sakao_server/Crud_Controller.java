package sakao_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import org.codehaus.jackson.map.ObjectMapper;

import sakao_common.AlerteStatistics;
import sakao_common.Bollard;
import sakao_common.Configuration;
import sakao_common.PollutionSensor;
import sakao_common.Sensor;
import sakao_common.TramLineD;
import sakao_common.Transportation;
import sakao_common.VehicleSensor;
import sakao_common.WeatherSensor;
import sakao_common.Zone;
import sakao_connection_pool.DataSource;
import sakao_common.SmartCity;
import sakao_common.Station;
import sakao_common.TramLineA;
import sakao_common.TramLineB;
import sakao_common.TramLineC;
import sakao_common.TramLineD;
import sakao_common.smartcity2;


public class Crud_Controller {

	public Crud_Controller() throws ClassNotFoundException {
	}

	//// Transport

	public ArrayList<String> showAllTransport() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from transportation");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Transportation(rs.getInt("idtransportation"), rs.getString("typeoftransport"),
						rs.getInt("dailytransportusercount"),
						rs.getDouble("averageofco2releasedbytransport")).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

/////// db 

	public ArrayList<String> showVehiculNumb() {
		ArrayList<String> don = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select maxnumbervehicles from smartcity;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("/////////////////////////////////////////////////////");
		//		SmartCity s = new SmartCity(rs.getInt("maxnumbervehicles"));
				
				//don.add(new ObjectMapper().writeValueAsString(s));
			}

			DataSource.returnConnection(con);
			System.out.println("fini");
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return don;
	}

	public ArrayList<String> showBeta() {
		ArrayList<String> don = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select avg(beteaverage) as betaaverage from pollutionsensor");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// don.add(new PollutionSensor(rs.getDouble(1)).toString());
			}
			DataSource.returnConnection(con);
			System.out.println("fini");
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return don;
	}

////oumaima
	public ArrayList<String> showCityEM() {
		ArrayList<String> don = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from smartcity");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				SmartCity s = new SmartCity(rs.getInt("idcity"), rs.getDouble("heightkm"), rs.getDouble("widthkm"));

				don.add(new ObjectMapper().writeValueAsString(s));
			}

			DataSource.returnConnection(con);
			System.out.println("fini");
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return don;
	}

	public ArrayList<String> showAllConfiguration() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from configuration");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Configuration(rs.getInt("idconfiguration"), rs.getInt("frequency"), rs.getInt("idzone"))
						.toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<String> showAllSensors() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from sensor");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Sensor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getBoolean(7)).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<String> showAlertByDate(String date) throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con
					.prepareStatement("select * from alertestatistics where dateajout = '" + date + "\'");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new AlerteStatistics(rs.getInt(1), rs.getDate(2), rs.getInt(3)).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<String> showSensorsPollution() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement(
					"select * from sensor where sensortype like 'Pollution' and isinstalled = 'true' ");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Sensor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getBoolean(7)).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<String> showSensorsWeather() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con
					.prepareStatement("select * from sensor where sensortype like 'Weather' and isinstalled = 'true' ");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Sensor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getBoolean(7)).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<String> showAllPollutionSensors() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from pollutionsensor");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new PollutionSensor(rs.getInt(1), rs.getInt(4), rs.getInt(3), rs.getDouble(2)).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<String> showAllWeatherSensors() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from weathersensor");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new WeatherSensor(rs.getInt(1), rs.getInt(4), rs.getInt(5), rs.getInt(2), rs.getString(3))
						.toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<String> showAllZone() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from zone");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Zone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	

	public ArrayList<String> showAllBollards() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from retractablebollard");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Bollard(rs.getInt(1), rs.getBoolean(2), rs.getInt(3), rs.getBoolean(4), rs.getString(5),
						rs.getString(6)).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<String> showAllSensorVehicles() throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		ArrayList<VehicleSensor> retour1 = new ArrayList<VehicleSensor>();
		ArrayList<Sensor> sensor = new ArrayList<Sensor>();

		try {
			Connection con1 = DataSource.getConnection();
			PreparedStatement pt1 = con1.prepareStatement("select * from sensor");
			ResultSet rs1 = pt1.executeQuery();

			while (rs1.next()) {

				sensor.add(new Sensor(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getInt(4),
						rs1.getString(5), rs1.getString(6), rs1.getBoolean(7)));

				
			}
			DataSource.returnConnection(con1);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

		try {
			Connection con2 = DataSource.getConnection();
			PreparedStatement pt2 = con2.prepareStatement("select * from vehiclesensor");
			ResultSet rs = pt2.executeQuery();
			while (rs.next()) {

				for (Sensor s : sensor) {
					// System.out.println("SENSOR =" +s.getIdSensor());
					// System.out.println("Vehicule sensor id =" + rs.getInt(4) );
					if (s.getIdSensor() == rs.getInt(4)) {

						System.out.println("rs.getint(4) =" + rs.getInt(4));
						// System.out.println("s.getSensorState() ="+s.getSensorState());
						// System.out.println("s.getSensorType() ="+s.getSensorType());
						System.out.println("rs.getString(2) =" + rs.getString(2));
						System.out.println(" rs.getInt(3) =" + rs.getInt(3));
						System.out.println(" rs.getInt(3) =" + rs.getInt(3));
						// System.out.println("s.getIdZone() ="+s.getIdZone());
						// System.out.println("s.getIpAddress() ="+s.getIpAddress());
						// System.out.println("s.getMacAddress() ="+s.getMacAddress());
						// System.out.println("s.getIsInstalled()="+s.getIsInstalled());

						// VehicleSensor c = new VehicleSensor(1,"12","23","12",12,12,"12","12",true);

						VehicleSensor c = new VehicleSensor(rs.getInt(4), s.getSensorState(), s.getSensorType(),
								rs.getString(2), rs.getInt(3), s.getIdZone(), s.getIpAddress(), s.getMacAddress(),
								s.getIsInstalled());
						String c1 = c.toString();
						// System.out.println("c.getIdSensor(); =" + c.getIdSensor());
						// System.out.println("le c = "+c);

						retour1.add(c);

						retour.add(c1);

						// System.out.println("retour1 =" + retour1);

						
					}
				}

			}
			DataSource.returnConnection(con2);
			System.out.println("retour laaa   =" + retour);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}
	

	public void addOnBollard(String target, ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into " + target + " (BollardState, IDZone, IsInstalled,Ipaddress,Macaddress) VALUES ";
			int i = 2;

			System.out.println("taille " + list.size());
			System.out.println("case " + list.get(2));
			while (i < list.size()) {
				req += "(" + list.get(i) + "," + list.get(i + 2) + "," + list.get(i + 4) + "," + list.get(i + 6) + ","
						+ list.get(i + 8) + "),";
				i += 12;

			}

			// System.out.println("");

			System.out.println("");
			req = req.substring(0, req.length() - 1);
			// System.out.println(req);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			System.out.println("");
			// System.out.println(req);
			System.out.println("");
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	///////////////////////////////////////////////////
	public void addOnZone(String target, ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into zone ( nexttothezone, location, thresholdbeta, alertstate, idcity) VALUES ";

			int i = 2;
			while (i < list.size()) {
				req += "('" + list.get(i) + "\'" + "," + "\'" + list.get(i + 2) + "\'" + "," + list.get(i + 4) + ","
						+ list.get(i + 6) + "," + list.get(i + 8) + "),";
				i += 12;
			}
			req = req.substring(0, req.length() - 1);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	///////////////////////////////////////////////////
	public void addOnWeatherSensor(String target, ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into weathersensor (temperature, stateofthesky, idsensor, idconfiguration) VALUES ";

			int i = 2;
			while (i < list.size()) {
				req += "(" + list.get(i) + "," + "\'" + list.get(i + 2) + "\'" + "," + list.get(i + 4) + ","
						+ list.get(i + 6) + "),";
				i += 10;
			}
			req = req.substring(0, req.length() - 1);
			System.out.println(req);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			System.out.println(req);
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void addOnPollutionSensor(String target, ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into " + target + " (betaaverage, idconfiguration, idsensor) VALUES ";

			int i = 2;
			while (i < list.size()) {
				req += "(" + list.get(i) + "," + list.get(i + 2) + "," + list.get(i + 4) + "),";
				i += 8;
			}
			req = req.substring(0, req.length() - 1);
			System.out.println(req);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			System.out.println(req);
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void addOnSensor(String target, ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into " + target
					+ " (sensorstate, sensortype, idzone, ipaddress, macaddress, isinstalled) VALUES ";

			int i = 2;
			while (i < list.size()) {
				req += "(" + "\'" + list.get(i) + "\'" + "," + "\'" + list.get(i + 2) + "\'" + "," + list.get(i + 4)
						+ "," + "\'" + list.get(i + 6) + "\'" + "," + "\'" + list.get(i + 8) + "\'" + ","
						+ list.get(i + 10) + "),";
				i += 14;
			}
			req = req.substring(0, req.length() - 1);
			System.out.println(req);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			System.out.println(req);
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void addOnConfiguration(String target, ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into " + target + " (frequency, idzone) VALUES ";
			int i = 2;
			while (i < list.size()) {
				req += "(" + list.get(i) + "," + list.get(i + 2) + "),";
				i += 6;
			}
			req = req.substring(0, req.length() - 1);
			System.out.println(req);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			System.out.println(req);
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteSensorById(int id) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("delete from sensor where idsensor = " + id);
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void updateSensor(int id, boolean install) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pstm = con.prepareStatement(" UPDATE sensor SET isinstalled = ?  WHERE idsensor = ?");
			pstm.setBoolean(1, install);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void updateZoneBeta(int id, int thresholdbeta) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pstm = con.prepareStatement(" UPDATE zone SET thresholdbeta = ?  WHERE idzone = ?");
			pstm.setInt(1, thresholdbeta);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void updateZone(int id, boolean alertestate) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pstm = con.prepareStatement(" UPDATE zone SET alertstate = ?  WHERE idzone = ?");
			pstm.setBoolean(1, alertestate);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public ArrayList<String> showZoneById(int id) throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from zone where idzone =" + id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Zone(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;

	}

	public ArrayList<String> showSensorById(int id) throws ClassNotFoundException {
		ArrayList<String> retour = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from sensor where idsensor =" + id);
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				retour.add(new Sensor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getBoolean(7)).toString());

			}
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;

	}

	public void addOnAlerteStatistics(String target, ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "insert into alertestatistics (dateajout, idsensor) VALUES ";
			int i = 2;
			while (i < list.size()) {
				req += "(" + "\'" + list.get(i) + "\'" + "," + list.get(i + 2) + "),";
				i += 6;
			}
			req = req.substring(0, req.length() - 1);
			System.out.println(req);
			PreparedStatement pstm = con.prepareStatement(req);
			pstm.executeUpdate();
			System.out.println(req);
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * public Student existStudent(int id) throws ClassNotFoundException { Student
	 * retour = null; try { Connection con = DataSource.getConnection();
	 * 
	 * PreparedStatement pt =
	 * con.prepareStatement("select * from personne where id =" + id); ResultSet rs
	 * = pt.executeQuery(); while (rs.next()) { int idS = rs.getInt(1); String name
	 * = rs.getString(2); int age = rs.getInt(3); retour = new Student(idS, name,
	 * age); DataSource.returnConnection(con); }
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * } return retour;
	 * 
	 * }
	 * 
	 * // Request SELECT
	 * 
	 * public ArrayList<Object> showStudent(String target) throws
	 * ClassNotFoundException { ArrayList<Object> retour = new ArrayList<Object>();
	 * try { Connection con = DataSource.getConnection();
	 * 
	 * PreparedStatement pt = con.prepareStatement("select * from" + target);
	 * ResultSet rs = pt.executeQuery(); while (rs.next()) { int id = rs.getInt(1);
	 * String name = rs.getString(2); int age = rs.getInt(3); retour.add(new
	 * Student(id, name, age)); DataSource.returnConnection(con); }
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * } return retour;
	 * 
	 * }
	 */

	/*
	 * public void deleteStudentByName(int ID) throws ClassNotFoundException { try {
	 * Connection con = DataSource.getConnection(); Statement stmt =
	 * con.createStatement(); ResultSet rslt =
	 * stmt.executeQuery("select id from Student where name = " + name);
	 * while(rslt.next()) { if(rslt.equals(obj)) }
	 * 
	 * PreparedStatement pt = con
	 * .prepareStatement("delete from Student where name like ?"); pt.setString(1,
	 * name); pt.execute(); DataSource.returnConnection(con);
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * }
	 * 
	 * }
	 */

	/*
	 * public void deleteStudentById(int ID) throws ClassNotFoundException { try {
	 * Connection con = DataSource.getConnection(); PreparedStatement pt =
	 * con.prepareStatement("delete from personne where id = " + ID); pt.execute();
	 * DataSource.returnConnection(con);
	 * 
	 * }
	 * 
	 * catch (SQLException ex) { System.out.println("erreur " + ex.getMessage()); }
	 * }
	 * 
	 * // Request INSERT
	 * 
	 * public void addOnTable(String target, ArrayList<String> list) throws
	 * ClassNotFoundException { try {
	 * 
	 * Connection con = DataSource.getConnection(); System.out.println(con);
	 * 
	 * String req = "insert into " + target + "(";
	 * 
	 * int i = 1; while(i < list.size() - 2 ) { if(i == list.size() - 3) { req = req
	 * + list.get(i) + ") "; break; } else { req = req + list.get(i) + "," ; i =
	 * i+2; } } req = req + "values ("; int j = 2; while(j < list.size() - 1) { if(j
	 * == list.size() - 2) { req = req + list.get(j) + ")"; break; } else { req =
	 * req + list.get(j) + ","; j = j + 2; } } System.out.println(req);
	 * PreparedStatement pstm = con.prepareStatement(req); /*pstm.setString(1,
	 * p.getName()); pstm.setInt(2, p.getAge());
	 */
	/*
	 * pstm.executeUpdate(); System.out.println(req);
	 * DataSource.returnConnection(con);
	 * 
	 * } catch (SQLException ex) { ex.printStackTrace(); } }
	 * 
	 * // Request UPDATE
	 * 
	 * public void updateStudentAge(int id, int age) throws ClassNotFoundException {
	 * try { Connection con = DataSource.getConnection();
	 * 
	 * PreparedStatement pstm =
	 * con.prepareStatement(" UPDATE personne SET age = ?  WHERE id = ?");
	 * pstm.setInt(1, age); pstm.setInt(2, id); pstm.executeUpdate();
	 * DataSource.returnConnection(con);
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * } }
	 * 
	 * public void updateStudentName(int id, String name) throws
	 * ClassNotFoundException { try { Connection con = DataSource.getConnection();
	 * 
	 * PreparedStatement pstm =
	 * con.prepareStatement(" UPDATE personne SET name = ?  WHERE id = ?");
	 * pstm.setString(1, name); pstm.setInt(2, id); pstm.executeUpdate();
	 * DataSource.returnConnection(con);
	 * 
	 * } catch (SQLException ex) { System.out.println("erreur " + ex.getMessage());
	 * }
	 * 
	 * }
	 * 
	 * public void deleteAllStudent() throws SQLException, ClassNotFoundException {
	 * Connection con = DataSource.getConnection();
	 * 
	 * Statement query = con.createStatement(); int result =
	 * query.executeUpdate("TRUNCATE TABLE personne");
	 * DataSource.returnConnection(con);
	 * 
	 * }
	 */

	/////////////////////////////// SELECT /////////////////////////
	/////////////////////////////// /////////////////////////
	/////////////////////////////// /////////////////////////
	/////////////////////////////// /////////////////////////
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
	public ArrayList<String> showCity() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from smartcity");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new SmartCity(rs.getInt("idcity"), rs.getDouble("heightkm"), rs.getDouble("widthkm"),
						rs.getInt("budget"), rs.getInt("astationcost"), rs.getString("name")).toString());
			}

			DataSource.returnConnection(con);
			System.out.println("fini");
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showStations() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from station");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new Station(rs.getInt("idstation"), rs.getString("typestation"), rs.getDouble("coordx"),
						rs.getDouble("coordy"), rs.getBoolean("isbuilt"), rs.getInt("idzone")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showTramLineA() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from tramlinea");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getInt("idtramlinea"), rs.getInt("idstation"), rs.getDouble("coordx"),
						rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showTramLineB() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from tramlineb");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getInt("idtramlineb"), rs.getInt("idstation"), rs.getDouble("coordx"),
						rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showTramLineC() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from tramlinec");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getInt("idtramlinec"), rs.getInt("idstation"), rs.getDouble("coordx"),
						rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showTramLineD() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from tramlined");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getInt("idtramlined"), rs.getInt("idstation"), rs.getDouble("coordx"),
						rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showCityDimension() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select heightkm,widthkm from smartcity");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new SmartCity(rs.getDouble("heightkm"), rs.getDouble("widthkm")).toString());
			}

			DataSource.returnConnection(con);
			System.out.println("fini");
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showStationCoord() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from station");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new Station(rs.getDouble("coordx"), rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showTramLineACoord() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from tramlinea");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineA(rs.getDouble("coordx"), rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showTramLineBCoord() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from tramlineb");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineB(rs.getDouble("coordx"), rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showTramLineCCoord() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from tramlinec");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineC(rs.getDouble("coordx"), rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	public ArrayList<String> showTramLineDCoord() {
		ArrayList<String> displayer = new ArrayList<String>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select coordx,coordy from tramlined");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				displayer.add(new TramLineD(rs.getDouble("coordx"), rs.getDouble("coordy")).toString());
			}

			DataSource.returnConnection(con);
		} catch (Exception e) {
			System.out.println("erreur " + e.getMessage());
		}

		return displayer;
	}

	/////////////////////////////////////// ADD /////////////////////////
	/////////////////////////////////////// /////////////////////////
	/////////////////////////////////////// /////////////////////////
	/////////////////////////////////////// /////////////////////////
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 

	public void addASmartCity(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String sqlreq = "insert into smartcity(heightkm,widthkm,budget,astationcost,name) VALUES";
			String s = "sakao";
			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + list.get(i) + "," + list.get(i + 2) + "," + list.get(i + 4) + "," + list.get(i + 6)
						+ "," + "'" + s + "'" + "),";
				i += list.size();
			}

			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);

			PreparedStatement pstmt = con.prepareStatement(sqlreq);

			pstmt.executeUpdate();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void addStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			String sqlReqFkCity = "select idcity from smartcity";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCity);
			Object idfk = null;
			while (rs.next()) {
				idfk = rs.getInt(1);
			}

			DataSource.returnConnection(con);

			con = DataSource.getConnection();
			String sqlreq = "insert into station(typestation,coordx,coordy,isbuilt,idcity,idtransportation) VALUES";

			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + "'" + list.get(i) + "'" + "," + list.get(i + 2) + "," + list.get(i + 4) + ","
						+ list.get(i + 6) + "," + idfk + "," + 1 + "),";
				i += 8;
			}

			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);

			PreparedStatement pstmt = con.prepareStatement(sqlreq);

			pstmt.executeUpdate();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void addTramLineAStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			String sqlReqFkCoord = "select station.idstation from station " + "inner join tramlinea on "
					+ "station.coordx =  + " + list.get(2) + "and station.coordy = " + list.get(4);
			String sqlreq = "insert into tramlinea(idstation,coordx,coordy) VALUES";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCoord);
			Object idfk = null;
			while (rs.next()) {
				idfk = rs.getInt(1);
			}

			DataSource.returnConnection(con);

			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + idfk + "," + list.get(i) + "," + list.get(i + 2) + "),";
				i += 6;
			}
			con = DataSource.getConnection();
			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);
			PreparedStatement pstmt = con.prepareStatement(sqlreq);
			pstmt.executeUpdate();
			DataSource.returnConnection(con);
			rs.close();

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void addTramLineBStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			String sqlReqFkCoord = "select station.idstation from station " + "inner join tramlineb on "
					+ "station.coordx =  + " + list.get(2) + "and station.coordy = " + list.get(4);
			String sqlreq = "insert into tramlineb(idstation,coordx,coordy) VALUES";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCoord);
			Object idfk = null;
			while (rs.next()) {
				idfk = rs.getInt(1);
			}

			DataSource.returnConnection(con);

			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + idfk + "," + list.get(i) + "," + list.get(i + 2) + "),";
				i += 6;
			}
			con = DataSource.getConnection();
			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);
			PreparedStatement pstmt = con.prepareStatement(sqlreq);
			pstmt.executeUpdate();
			DataSource.returnConnection(con);
			rs.close();

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void addTramLineCStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			String sqlReqFkCoord = "select station.idstation from station " + "inner join tramlinec on "
					+ "station.coordx =  + " + list.get(2) + "and station.coordy = " + list.get(4);
			String sqlreq = "insert into tramlinec(idstation,coordx,coordy) VALUES";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCoord);
			Object idfk = null;
			while (rs.next()) {
				idfk = rs.getInt(1);
			}

			DataSource.returnConnection(con);

			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + idfk + "," + list.get(i) + "," + list.get(i + 2) + "),";
				i += 6;
			}
			con = DataSource.getConnection();
			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);
			PreparedStatement pstmt = con.prepareStatement(sqlreq);
			pstmt.executeUpdate();
			rs.close();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void addTramLineDStation(ArrayList<String> list) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String sqlReqFkCoord = "select station.idstation from station " + "inner join tramlined on "
					+ "station.coordx =  + " + list.get(2) + "and station.coordy = " + list.get(4);
			String sqlreq = "insert into tramlined(idstation,coordx,coordy) VALUES";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sqlReqFkCoord);
			Object idfk = null;
			while (rs.next()) {
				idfk = rs.getInt(1);
			}

			DataSource.returnConnection(con);

			System.out.println("list size : " + list.size());

			int i = 2;
			while (i < list.size()) {
				sqlreq += "(" + idfk + "," + list.get(i) + "," + list.get(i + 2) + "),";
				i += 6;
			}
			con = DataSource.getConnection();
			sqlreq = sqlreq.substring(0, sqlreq.length() - 1);
			System.out.println(sqlreq);
			PreparedStatement pstmt = con.prepareStatement(sqlreq);
			pstmt.executeUpdate();
			rs.close();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	///////////////////////// DELETE /////////////////////////
	///////////////////////// ///////////////////////// /////////////////////////
	///////////////////////// /////////////////////////
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// 
///////////////////////// ///////////////////////// ///////////////////////// ///////////////////////// /////////////////////////

	public void deleteACity() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();

		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE smartcity cascade");
		/// System.out.println(result + "rows deleted");
		DataSource.returnConnection(con);
	}

	public void deleteStations() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE station cascade");
		///// System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}

	public void deleteTramLineA() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE tramlinea cascade");
		///// System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}

	public void deleteTramLineB() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE tramlineb cascade");
		///// System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}

	public void deleteTramLineC() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE tramlinec cascade");
		///// System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}

	public void deleteTramLineD() throws SQLException, ClassNotFoundException {
		Connection con = DataSource.getConnection();
		Statement query = con.createStatement();
		query.executeUpdate("TRUNCATE TABLE tramlined cascade");
		///// System.out.println(result + "row(s) deleted");
		DataSource.returnConnection(con);
	}

	public ArrayList<VehicleSensor> GenerateAllVehicleSensors() throws ClassNotFoundException {
		ArrayList<VehicleSensor> retour = new ArrayList<VehicleSensor>();
		ArrayList<Sensor> sensor = new ArrayList<Sensor>();

		try {
			Connection con1 = DataSource.getConnection();
			PreparedStatement pt1 = con1.prepareStatement("select * from sensor");
			ResultSet rs1 = pt1.executeQuery();

			while (rs1.next()) {

				/*
				 * sensor.add(new Sensor(rs1.getInt(1), rs1.getString(2), rs1.getString(3),
				 * rs1.getInt(4), rs1.getString(5), rs1.getString(6), rs1.getBoolean(7)));
				 */

				sensor.add(new Sensor(rs1.getInt("idsensor"), rs1.getString("sensorstate"), rs1.getString("sensortype"),
						rs1.getInt("idzone"), rs1.getString("ipaddress"), rs1.getString("macaddress"),
						rs1.getBoolean("isinstalled")));

				DataSource.returnConnection(con1);
			}

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

		try {
			Connection con2 = DataSource.getConnection();
			PreparedStatement pt2 = con2.prepareStatement("select * from vehiclesensor");
			ResultSet rs = pt2.executeQuery();
			while (rs.next()) {

				for (Sensor s : sensor) {
					if (s.getIdSensor() == rs.getInt(4)) {

						retour.add(new VehicleSensor(rs.getInt(1), s.getSensorState(), s.getSensorType(),
								rs.getString(2), rs.getInt(3), s.getIdZone(), s.getIpAddress(), s.getMacAddress(),
								s.getIsInstalled()));
						DataSource.returnConnection(con2);
					}

				}

			}

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public ArrayList<Bollard> GenerateAllBollards() throws ClassNotFoundException {
		ArrayList<Bollard> retour = new ArrayList<Bollard>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from retractablebollard");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				/*
				 * retour.add(new Bollard(rs.getInt(1), rs.getBoolean(2), rs.getInt(3),
				 * rs.getBoolean(4), rs.getString(5), rs.getString(6)));
				 */

				retour.add(new Bollard(rs.getInt("idbollard"), rs.getBoolean("bollardstate"), rs.getInt("idzone"),
						rs.getBoolean("isinstalled"), rs.getString("ipaddress"), rs.getString("macaddress")));

				DataSource.returnConnection(con);
			}

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return retour;
	}

	public smartcity2 GenerateCity() throws ClassNotFoundException {
		smartcity2 smartcity = null;
		// ArrayList<smartcity> retour = new ArrayList<smartcity>();
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("select * from smartcity");
			ResultSet rs = pt.executeQuery();
			while (rs.next()) {
				/*
				 * smartcity = new smartcity2(rs.getInt(1), rs.getDouble(3), rs.getDouble(4),
				 * rs.getInt(5), rs.getInt(6), rs.getInt(8), rs.getInt(7), rs.getInt(9),
				 * rs.getString(2));
				 */
				smartcity = new smartcity2(rs.getInt("idcity"), rs.getDouble("heightkm"), rs.getDouble("widthkm"),
						rs.getInt("budget"), rs.getInt("astationcost"), rs.getInt("numberofvehicules"),
						rs.getInt("maxnumbervehicles"), rs.getInt("tramfrequency"), rs.getString("name"));
				DataSource.returnConnection(con);
			}

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
		return smartcity;
	}

	public void UpdateSensorVehicles(String target, ArrayList<String> list,
			ArrayList<VehicleSensor> listVehicleSensorObj) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			String req = "UPDATE vehiclesensor SET  numberofvehicle=? WHERE idvehiclesensor =? and sensortype= ?;";

			PreparedStatement pstm = con.prepareStatement(req);
			int i = 2;

			// System.out.println("taille " + list.size());
			// System.out.println("case " + list.get(2));
			while (i < list.size()) {
				pstm.setInt(2, Integer.parseInt((list.get(i)))); // IDVehicule
				pstm.setString(3, list.get(i + 2)); // Sensortypeio
				pstm.setInt(1, Integer.parseInt(list.get(i + 4)));// NUMBERVEHICULE
				pstm.executeUpdate();

				// Don't work if generateobject is commented in clientThread
				for (VehicleSensor sensor : listVehicleSensorObj) {
					if ((sensor.getIdSensor() == Integer.parseInt((list.get(i))))) {

						sensor.setNumberOfVehicle(Integer.parseInt(list.get(i + 4)));

					}

				}

				i += 8;

			}
			req = req.substring(0, req.length() - 1);
			// System.out.println(req);
			// PreparedStatement pstm = con.prepareStatement(req);
			// pstm.executeUpdate();
			// System.out.println("");
			// System.out.println(req);
			// System.out.println("");
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void UpdateBollardIsInstalled(String target, ArrayList<String> list, ArrayList<Bollard> listBollardObj)
			throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			String req = "UPDATE retractablebollard SET  IsInstalled=? WHERE idbollard = ? and idzone= ?;";
			PreparedStatement pstm = con.prepareStatement(req);
			int i = 2;

			System.out.println("taille " + list.size());
			System.out.println("case " + list.get(2));
			while (i < list.size()) {
				pstm.setInt(2, Integer.parseInt((list.get(i)))); // Idbollard
				pstm.setBoolean(1, Boolean.valueOf(list.get(i + 2)).booleanValue()); // IsInstalled
				pstm.setInt(3, Integer.parseInt(list.get(i + 4)));// Idzone
				pstm.executeUpdate();

				for (Bollard bollard : listBollardObj) {
					if ((bollard.getIdBollard() == Integer.parseInt((list.get(i))))) {

						bollard.setInstalled(Boolean.valueOf(list.get(i + 2)).booleanValue());

					}

				}

				i += 8;

			}
			req = req.substring(0, req.length() - 1);
			// System.out.println(req);
			// PreparedStatement pstm = con.prepareStatement(req);
			// pstm.executeUpdate();
			System.out.println("");
			// System.out.println(req);
			System.out.println("");
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public void UpdateBollardTrue(ArrayList<Bollard> listBollard) throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("UPDATE retractablebollard SET bollardstate= true ;");
			pt.executeUpdate();

			for (Bollard bollard : listBollard) {

				bollard.setBollardState(true);

			}

			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void UpdateBollardFalse(ArrayList<Bollard> listBollard) throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("UPDATE retractablebollard SET bollardstate= false ;");
			pt.executeUpdate();

			for (Bollard bollard : listBollard) {

				bollard.setBollardState(false);

			}

			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void deleteBollardById(int id) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con.prepareStatement("delete from retractablebollard where idbollard = " + id);
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void updateBollard(int id, boolean install) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();

			PreparedStatement pstm = con
					.prepareStatement(" UPDATE retractablebollard SET isinstalled = ?  WHERE idbollard = ?");
			pstm.setBoolean(1, install);
			pstm.setInt(2, id);
			pstm.executeUpdate();
			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}
	}

	public void UpdateSmartCityVehicles(String target, ArrayList<String> list, smartcity2 smartCityObject)
			throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			String req = "UPDATE smartcity SET maxnumbervehicles=?, numberofvehicules=?, tramfrequency=?\r\n"
					+ "	WHERE idcity = 1;";

			PreparedStatement pstm = con.prepareStatement(req);
			int i = 2;

			while (i < list.size()) {
				pstm.setInt(1, Integer.parseInt((list.get(i)))); // MaxnumberVeh
				pstm.setInt(2, Integer.parseInt(list.get(i + 2))); // numberofvehicules
				pstm.setInt(3, Integer.parseInt(list.get(i + 4)));// tramfrequecy
				pstm.executeUpdate();

				smartCityObject.setMaxNumberVehicles(Integer.parseInt((list.get(i))));
				smartCityObject.setNumberVehicles(Integer.parseInt(list.get(i + 2)));
				smartCityObject.setTramFrequency(Integer.parseInt(list.get(i + 4)));

				i += 8;

			}
			req = req.substring(0, req.length() - 1);

			DataSource.returnConnection(con);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void updateNumberinCirculation(int c) throws ClassNotFoundException {

		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con
					.prepareStatement("UPDATE smartcity SET numberofvehicules=" + c + "  WHERE idcity = 1;");
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

	}

	public void updateTramFrequency(int c) throws ClassNotFoundException {
		try {
			Connection con = DataSource.getConnection();
			PreparedStatement pt = con
					.prepareStatement("UPDATE smartcity SET tramfrequency=" + c + "  WHERE idcity = 1;");
			pt.execute();
			DataSource.returnConnection(con);
		} catch (SQLException ex) {
			System.out.println("erreur " + ex.getMessage());
		}

	}

}
