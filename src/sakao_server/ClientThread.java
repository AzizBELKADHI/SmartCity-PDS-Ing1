package sakao_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import sakao_client_insert.TablesToBeInserted;
import sakao_common.Bollard;
import sakao_common.Configuration;
import sakao_common.Request;
import sakao_common.Response;
import sakao_common.SmartCity;
import sakao_common.VehicleSensor;
import sakao_common.smartcity2;

public class ClientThread extends Thread {
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Request request = new Request();
	private Response response = new Response();
	private Crud_Service service;
	private ConfigurationService configService;
	private SensorsService sensorService;
	private WeatherService weatherService;
	private PollutionService pollutionService;
	private ZoneService zoneService;
	private EmpreinteCarboneService empreinteCarboneService;

	private BollardService bollardService;
	private VehiclesSensorService vehiclesSensorService;
	private smartCityServices smartCityServices;

	private ArrayList<VehicleSensor> vehicleSensorObject;
	private ArrayList<Bollard> bollardObject;
	private smartcity2 smartCityObject;
	
	private TransportService transportService;

	private ObjectMapper mapper;
	private static int position = 1;
	private boolean shouldRun = true;

	public ClientThread(Socket socket) throws ClassNotFoundException {
		super("Client" + position);
		position++;
		this.clientSocket = socket;
		service = new Crud_Service();
		configService = new ConfigurationService();
		sensorService = new SensorsService();
		pollutionService = new PollutionService();
		weatherService = new WeatherService();
		zoneService = new ZoneService();
		bollardService = new BollardService();
		vehiclesSensorService = new VehiclesSensorService();
		smartCityServices = new smartCityServices();
		transportService = new TransportService();
		this.empreinteCarboneService = new EmpreinteCarboneService();
	}

	public void GenerateObject() {

		try {

			vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();
			bollardObject = bollardService.GenerateAllBollards();
			smartCityObject = smartCityServices.GenerateCity();

			/*System.out.println("Objets generés :");
			System.out.println("");
			System.out.println(vehicleSensorObject);
			System.out.println("");
			System.out.println(bollardObject);
			System.out.println("");
			System.out.println(smartCityObject);
			System.out.println("");
			System.out.println("Fin objets generés");*/

		} catch (Exception e) {

		}

	}


	// Get Data from database 
	public void CrudEmpreinteCarbone(String operation_type, String t,ArrayList<String> list) {
		if(operation_type.equals("SELECT_ALL")) {
			try {
				response.setList(empreinteCarboneService.showCityEM());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void CheckVehiclesThreshold() throws ClassNotFoundException {

		bollardObject = bollardService.GenerateAllBollards();



		int NbVehicleInCirculation = smartCityObject.VehicleInCirculation(vehicleSensorObject);
		smartCityServices.updateNumberinCirculation(NbVehicleInCirculation);
		// vehicleSensorObject.updatenull(0);
		smartCityObject = smartCityServices.GenerateCity();

		System.out.println("NbVehicleInCirculation = " + NbVehicleInCirculation);

		int Max = smartCityObject.getMaxNumberVehicles();
		int Maxminus20 = ((Max) - ((Max * 20) / 100)); // -20% of max

		if (smartCityObject.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == true) {

			bollardService.Updatetrue(bollardObject);
			bollardObject = bollardService.GenerateAllBollards();

			smartCityServices.updateTramFrequency(10);
			smartCityObject = smartCityServices.GenerateCity();



			System.out.println("Retractable bollards are raised");
			System.out.println("Tramfrequency =  10/10");

		} /*else if (smartCityObject.CheckThresholdNbMaxVehicles(NbVehicleInCirculation) == false
				&& bollardObject.get(0).getIsInstalled() == true) {*/
		else {

			if (NbVehicleInCirculation < Maxminus20) {

				bollardService.Updatefalse(bollardObject);
				bollardObject = bollardService.GenerateAllBollards();

				smartCityServices.updateTramFrequency(6);
				smartCityObject = smartCityServices.GenerateCity();

				System.out.println("Retractable bollards are lowered");
				System.out.println("Tramfrequency =  6/10");
			} else {

				if (bollardObject.get(1).getIsBollardState() == true) {

					smartCityServices.updateTramFrequency(8);
					smartCityObject = smartCityServices.GenerateCity();
					// Faire liste des bollard

					System.out.println("Number of vehicule is decreasing in town");
					System.out.println("Retractable bollards are raised");
					System.out.println("Tramfrequency =  8/10");
				} else {

					smartCityServices.updateTramFrequency(8);
					smartCityObject = smartCityServices.GenerateCity();
					// Faire liste des bollard

					System.out.println("Number of vehicule is increasing in town");
					System.out.println("Retractable bollards are lowered");
					System.out.println("Tramfrequency =  8/10");

				}

			}
		}

	}

	/*
	 * smartCityServices.updateTramFrequency(8);
	 * 
	 * bollardObject = bollardService.GenerateAllBollards(); // A ESSAYER SANS
	 * smartCityObject = smartCityServices.GenerateCity();
	 * 
	 * System.out.println("//////////////////////////////////");
	 * System.out.println("Doit etre true");
	 * System.out.println("bollardObject.get(0).getIsInstalled()");
	 * System.out.println(bollardObject.get(0).getIsInstalled());
	 * System.out.println("**********************************************");
	 * 
	 * System.out.println("Retractable bollards are raised (Nb in desc)");
	 * System.out.println("Tramfrequency =  8/10");
	 * 
	 * } else { bollardService.Updatefalse(bollardObject); bollardObject =
	 * bollardService.GenerateAllBollards();
	 * 
	 * smartCityObject = smartCityServices.GenerateCity();
	 * 
	 * System.out.println("Retractable bollards are lowered");
	 * System.out.println("Tramfrequency =  6/10");
	 * 
	 * }
	 * 
	 * // iff number circulation > max-((max *20)/100)
	 * 
	 * //// refaire
	 * 
	 * } else {
	 * 
	 * if (NbVehicleInCirculation >= Maxminus20) {
	 * 
	 * System.out.println("Maxinus20 " + Maxminus20);
	 * 
	 * smartCityServices.updateTramFrequency(8); smartCityObject =
	 * smartCityServices.GenerateCity();
	 * 
	 * bollardObject = bollardService.GenerateAllBollards(); // ???
	 * 
	 * System.out.println("Retractable bollards are raised (Nb in crois");
	 * System.out.println("Tramfrequency =  8/10");
	 * 
	 * } else {
	 * System.out.println("Retractable bollards are lowered (and bollard not true");
	 * System.out.println("Tramfrequency =  6/10");
	 * 
	 * }
	 * 
	 * }
	 */

	public void CrudBollard(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {
		case "SELECT_ALL":

			response.setList(bollardService.showAllBollards());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		case "INSERT":

			bollardService.addBollard("retractablebollard", list);//
			bollardObject = bollardService.GenerateAllBollards();

			System.out.println(bollardObject);

			String outjsonStringInsert = mapper.writeValueAsString(response);
			out.write(outjsonStringInsert + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");

			break;

		case "Update":
			// Afficher le update
			bollardService.UpdateBollardIsInstalled(target, list, bollardObject);
			bollardObject = bollardService.GenerateAllBollards();
			System.out.println("Update done on bollard");

			String outjsonStringUpdate = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdate + "\n");
			out.flush();

			System.out.println("Update done to " + this.getName());
			System.out.println("********************");
			break;

		case "UPDATEinstall":
			System.out.println(this.bollardService.updateBollard(Integer.parseInt(this.request.getList().get(0)),
					Boolean.valueOf(this.request.getList().get(1))));
			String outjsonStringUPDATEinstall = mapper.writeValueAsString(response);
			out.write(outjsonStringUPDATEinstall + "\n");
			out.flush();
			System.out.println("Update sensor done for " + this.getName());
			System.out.println("********************");
			break;

		case "DELETE":
			System.out.println(bollardService.deleteBollardById(Integer.parseInt(this.request.getList().get(0))));
			String outjsonStringDeleteABollard = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteABollard + "\n");
			out.flush();
			System.out.println("A row deleted for " + this.getName());
			System.out.println("********************");
			break;

		}

	}

	public void CrudVehiclesSensor(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {

		case "SELECT_ALL":

			response.setList(vehiclesSensorService.showAllSensorVehicles());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		//

		case "Update":

			vehiclesSensorService.UpdateSensorVehicles(target, list, vehicleSensorObject);
			vehicleSensorObject = vehiclesSensorService.GenerateAllVehicleSensors();
			System.out.println("");
//			System.out.println("List Sensor Objet Updated :");
//			System.out.println("");
//			System.out.println(vehicleSensorObject);
//			System.out.println("");
//			System.out.println("Fin object updated");
			System.out.println("");
			this.CheckVehiclesThreshold();
			String outjsonStringUpdate = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdate + "\n");
			out.flush();
			System.out.println("Update Vehicle Sensor done for " + this.getName());
			System.out.println("********************");
			break;
		}

	}

	private void CrudSmartcity(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {

		case "Update":

			smartCityServices.UpdateSmartCityVehicles(target, list, smartCityObject);
			smartCityObject = smartCityServices.GenerateCity();

			String outjsonStringUpdateSmart = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateSmart + "\n");
			out.flush();
			// System.out.println("");
			System.out.println("SmartCity object Updated :");
			System.out.println("");
			System.out.println(smartCityObject);
			System.out.println("");
			System.out.println("Fin object updated");
			System.out.println("");
			System.out.println("Update smartcity done for " + this.getName());

			System.out.println("********************");
			break;
		}

	}

	public void CrudConfiguration(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {
		case "SELECT_ALL":
			response.setList(configService.showAllConfiguration());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		case "INSERT":
			configService.addOnConfiguration("configuration", list);
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");
			break;

		}

	}

	public void CrudSensor(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException, ParseException {

		switch (operation_type) {
		case "SELECT_ALL":
			response.setList(sensorService.showAllSensors());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		case "INSERT":
			sensorService.addOnSensor(target, list);
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");
			break;

		case "DELETE":
			System.out.println(sensorService.deleteSensorById(Integer.parseInt(this.request.getList().get(0))));
			String outjsonStringDeleteAStudent = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAStudent + "\n");
			out.flush();
			System.out.println("A row deleted for " + this.getName());
			System.out.println("********************");
			break;

		case "UPDATE":
			System.out.println(this.sensorService.updateSensor(Integer.parseInt(this.request.getList().get(0)),
					Boolean.valueOf(this.request.getList().get(1))));
			String outjsonStringUpdateAge = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateAge + "\n");
			out.flush();
			System.out.println("Update sensor done for " + this.getName());
			System.out.println("********************");
			break;

		case "SELECT_POLLUTION":
			response.setList(this.sensorService.showSensorsPollution());
			String outjsonStringSelectPollution = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectPollution + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		case "SELECT_WEATHER":
			response.setList(this.sensorService.showSensorsWeather());
			String outjsonStringSelectWeather = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectWeather + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		case "SELECT_IDSENSOR":
			response.setList(sensorService.showSensorById(Integer.parseInt(list.get(0))));
			String outjsonStringSelectZone = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectZone + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;
		case "SELECT_ALERTDATE":
			response.setList(sensorService.showAlertByDate(list.get(0)));
			String outjsonStringSelectAlertDate = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAlertDate + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		}

	}

	public void CrudPollutionSensor(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {
		case "SELECT_ALL":
			response.setList(pollutionService.showAllPollutionSensor());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;
		case "INSERT":
			pollutionService.addOnPollutionSensor(target, list);
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");
			break;
		}

	}

	public void CrudWeatherSensor(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {
		case "SELECT_ALL":
			response.setList(weatherService.showAllWeatherSensor());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		case "INSERT":
			weatherService.addOnWeatherSensor(target, list);
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");
			break;
		}

	}

	private void CrudTransportation(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {
		switch (operation_type) {
			
		case "SELECT_ALL":
			response.setList(transportService.showAllTransport());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;
		}
	}

	public void CrudZone(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {
		case "SELECT_ALL":
			response.setList(zoneService.showAllZone());
			String outjsonStringSelectAll = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectAll + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;

		case "SELECT_ZONE":
			response.setList(zoneService.showZoneById(Integer.parseInt(list.get(0))));
			String outjsonStringSelectZone = mapper.writeValueAsString(response);
			out.write(outjsonStringSelectZone + "\n");
			out.flush();
			System.out.println("Display done to " + this.getName());
			System.out.println("********************");
			break;
		case "UPDATE_BETA":
			System.out.println(
					this.zoneService.updateZoneBeta(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(1))));
			String outjsonStringUpdateZoneBeta = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateZoneBeta + "\n");
			out.flush();
			System.out.println("Update zone done for " + this.getName());
			System.out.println("********************");
			break;
		case "UPDATE":
			System.out
					.println(this.zoneService.updateZone(Integer.parseInt(list.get(0)), Boolean.valueOf(list.get(1))));
			String outjsonStringUpdateZone = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateZone + "\n");
			out.flush();
			System.out.println("Update zone done for " + this.getName());
			System.out.println("********************");
			break;
		case "INSERT":
			zoneService.addOnZone(target, list);
			String outjsonStringDeleteAll = mapper.writeValueAsString(response);
			out.write(outjsonStringDeleteAll + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");
			break;
		}

	}

	public void CrudAlerteStatistics(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {
		case "INSERT":
			zoneService.addOnAlerteStatistics(target, list);
			String outjsonStringAdd = mapper.writeValueAsString(response);
			out.write(outjsonStringAdd + "\n");
			out.flush();
			System.out.println("Insert done for " + this.getName());
			System.out.println("********************");
			break;
		}

	}

	///////////////////////// CHOOSE THE GOOD REQUEST TO DO ////////////////////

	public void CrudSmartCity(String operation_type, String t, ArrayList<String> list) throws IOException {
		switch (operation_type) {
		case "SELECT_ALL":
			try {
				response.setList(service.showCity());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");
				/// response.getList().add("select not done");
				/*
				 * String outjsonStringSelectAll = mapper.writeValueAsString(response);
				 * out.write(outjsonStringSelectAll + "\n"); out.flush();
				 */
			}

			break;

		case "INSERT":
			try {
				/// String resp = "insert done";
				service.addASmartCity(list);
				String outjsonStringInsert = mapper.writeValueAsString(response);
				out.write(outjsonStringInsert + "\n");
				out.flush();
				System.out.println("Insert done for " + this.getName());
				System.out.println("********************");
				///// response.getList().add(resp);
			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * System.out.println("insert not done");
				 * response.getList().add("insert not done"); String outjsonStringInsert =
				 * mapper.writeValueAsString(response); out.write(outjsonStringInsert + "\n");
				 * out.flush();
				 */
			}

			break;

		case "DELETE_ALL":
			try {
				///// String resp = "delete done";
				service.deleteACity();
				/// response.getList().add(resp);
				String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				out.write(outjsonStringDeleteAll + "\n");
				out.flush();
				System.out.println("Delete done for " + this.getName());
				System.out.println("********************");

			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * System.out.println("delete not done");
				 * response.getList().add("delete not done");
				 */
			}
			break;

		case "SELECT_DIMENSION":
			try {
				response.setList(service.showCityDimension());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");
				/// response.getList().add("select not done");
				/*
				 * String outjsonStringSelectAll = mapper.writeValueAsString(response);
				 * out.write(outjsonStringSelectAll + "\n"); out.flush();
				 */
			}

			break;

		}
	}

	private void CrudSmartcity2(String operation_type, String target, ArrayList<String> list)
			throws ClassNotFoundException, JsonGenerationException, JsonMappingException, IOException {

		switch (operation_type) {

		case "Update":

			smartCityServices.UpdateSmartCityVehicles(target, list, smartCityObject);
			smartCityObject = smartCityServices.GenerateCity();

			String outjsonStringUpdateSmart = mapper.writeValueAsString(response);
			out.write(outjsonStringUpdateSmart + "\n");
			out.flush();
			// System.out.println("");
			System.out.println("SmartCity object Updated :");
			System.out.println("");
			System.out.println(smartCityObject);
			System.out.println("");
			System.out.println("Fin object updated");
			System.out.println("");
			System.out.println("Update smartcity done for " + this.getName());

			System.out.println("********************");
			break;
		}

	}

	public void CrudStation(String operation_type, String t, ArrayList<String> list) throws IOException {
		switch (operation_type) {
		case "SELECT_ALL":
			try {
				response.setList(service.showStations());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");
				response.getList().add("select not done");
			}

			break;

		case "INSERT":
			try {
				///// String resp = "insert done";
				service.addStation(list);
				///// response.getList().add(resp);
				String outjsonStringInsert = mapper.writeValueAsString(response);
				out.write(outjsonStringInsert + "\n");
				out.flush();
				System.out.println("Insert done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				e.printStackTrace();
				/// System.out.println("insert not done");
				/// response.getList().add("insert not done");
			}

			break;
		case "DELETE_ALL":
			try {
				///// String resp = "delete done";
				service.deleteStations();
				///// response.add(resp);
				String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				out.write(outjsonStringDeleteAll + "\n");
				out.flush();
				System.out.println("Delete done for " + this.getName());
				System.out.println("********************");
				break;
			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * System.out.println("delete not done");
				 * response.getList().add("delete not done");
				 */
			}

		case "SELECT_COORD":
			try {
				response.setList(service.showStationCoord());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");

			}

			break;

		}
	}

	public void CrudTramLineA(String operation_type, String t, ArrayList<String> list) throws IOException {
		switch (operation_type) {
		case "SELECT_ALL":
			try {
				response.setList(service.showTramLineA());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");
				response.getList().add("select not done");
			}

			break;

		case "INSERT":
			try {
				///// String resp = "insert done";
				service.addTramLineAStation(list);
				///// response.getList().add(resp);
				String outjsonStringInsert = mapper.writeValueAsString(response);
				out.write(outjsonStringInsert + "\n");
				out.flush();
				System.out.println("Insert done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				e.printStackTrace();
				/// System.out.println("insert not done");
				/// response.getList().add("insert not done");
			}

			break;
		case "DELETE_ALL":
			try {
				///// String resp = "delete done";
				service.deleteTramLineA();
				///// response.add(resp);
				String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				out.write(outjsonStringDeleteAll + "\n");
				out.flush();
				System.out.println("Delete done for " + this.getName());
				System.out.println("********************");
				break;
			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * System.out.println("delete not done");
				 * response.getList().add("delete not done");
				 */
			}

		case "SELECT_TRAMLINEA":
			try {
				response.setList(service.showTramLineACoord());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");

			}

			break;

		}
	}

	public void CrudTramLineB(String operation_type, String t, ArrayList<String> list) throws IOException {
		switch (operation_type) {
		case "SELECT_ALL":
			try {
				response.setList(service.showTramLineB());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");
				response.getList().add("select not done");
			}

			break;

		case "INSERT":
			try {
				///// String resp = "insert done";
				service.addTramLineBStation(list);
				///// response.getList().add(resp);
				String outjsonStringInsert = mapper.writeValueAsString(response);
				out.write(outjsonStringInsert + "\n");
				out.flush();
				System.out.println("Insert done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				e.printStackTrace();
				/// System.out.println("insert not done");
				/// response.getList().add("insert not done");
			}

			break;
		case "DELETE_ALL":
			try {
				///// String resp = "delete done";
				service.deleteTramLineB();
				///// response.add(resp);
				String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				out.write(outjsonStringDeleteAll + "\n");
				out.flush();
				System.out.println("Delete done for " + this.getName());
				System.out.println("********************");
				break;
			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * System.out.println("delete not done");
				 * response.getList().add("delete not done");
				 */
			}

		case "SELECT_TRAMLINEB":
			try {
				response.setList(service.showTramLineBCoord());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");

			}

			break;

		}
	}

	public void CrudTramLineC(String operation_type, String t, ArrayList<String> list) throws IOException {
		switch (operation_type) {
		case "SELECT_ALL":
			try {
				response.setList(service.showTramLineC());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");
				response.getList().add("select not done");
			}

			break;

		case "INSERT":
			try {
				///// String resp = "insert done";
				service.addTramLineCStation(list);
				///// response.getList().add(resp);
				String outjsonStringInsert = mapper.writeValueAsString(response);
				out.write(outjsonStringInsert + "\n");
				out.flush();
				System.out.println("Insert done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				e.printStackTrace();
				/// System.out.println("insert not done");
				/// response.getList().add("insert not done");
			}

			break;
		case "DELETE_ALL":
			try {
				///// String resp = "delete done";
				service.deleteTramLineC();
				///// response.add(resp);
				String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				out.write(outjsonStringDeleteAll + "\n");
				out.flush();
				System.out.println("Delete done for " + this.getName());
				System.out.println("********************");
				break;
			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * System.out.println("delete not done");
				 * response.getList().add("delete not done");
				 */
			}

		case "SELECT_TRAMLINEC":
			try {
				response.setList(service.showTramLineCCoord());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");

			}

			break;

		}
	}

	public void CrudTramLineD(String operation_type, String t, ArrayList<String> list) throws IOException {
		switch (operation_type) {
		case "SELECT_ALL":
			try {
				response.setList(service.showTramLineD());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");
				response.getList().add("select not done");
			}

			break;

		case "INSERT":
			try {
				///// String resp = "insert done";
				service.addTramLineDStation(list);
				///// response.getList().add(resp);
				String outjsonStringInsert = mapper.writeValueAsString(response);
				out.write(outjsonStringInsert + "\n");
				out.flush();
				System.out.println("Insert done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				e.printStackTrace();
				/// System.out.println("insert not done");
				/// response.getList().add("insert not done");
			}

			break;
		case "DELETE_ALL":
			try {
				///// String resp = "delete done";
				service.deleteTramLineD();
				///// response.add(resp);
				String outjsonStringDeleteAll = mapper.writeValueAsString(response);
				out.write(outjsonStringDeleteAll + "\n");
				out.flush();
				System.out.println("Delete done for " + this.getName());
				System.out.println("********************");
				break;
			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * System.out.println("delete not done");
				 * response.getList().add("delete not done");
				 */
			}

		case "SELECT_TRAMLINED":
			try {
				response.setList(service.showTramLineDCoord());
				String outjsonStringSelectAll = mapper.writeValueAsString(response);
				out.write(outjsonStringSelectAll + "\n");
				out.flush();
				System.out.println("Display done for " + this.getName());
				System.out.println("********************");
			} catch (Exception e) {
				System.out.println("select not done");

			}

			break;

		}
	}

	public void StartCrud() throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException,
			NullPointerException, JSONException {
		mapper = new ObjectMapper();
		String outjsonString = in.readLine();

		request = mapper.readValue(outjsonString, Request.class);
		System.out.println(request.getList());
		System.out.println(request.getList().size());
		System.out.println("Request received from " + this.getName());
		///// String operation_type = request.getOperation_type();
		String target = this.request.getTarget();
		switch (target) {
		case "smartcity":
			try {
				System.out.println("Let's go to see what's in smartcity");
				this.CrudSmartCity(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "station":
			try {
				System.out.println("Let's go to see what's in station");
				this.CrudStation(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "tramlinea":
			try {
				System.out.println("Let's go to see what's in tramlinea");
				this.CrudTramLineA(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "tramlineb":
			try {
				System.out.println("Let's go to see what's in tramlineb");
				this.CrudTramLineB(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "tramlinec":
			try {
				System.out.println("Let's go to see what's in tramlinec");
				this.CrudTramLineC(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "tramlined":
			try {
				System.out.println("Let's go to see what's in tramlined");
				this.CrudTramLineD(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "configuration":
			try {
				this.CrudConfiguration(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "sensor":
			try {
				this.CrudSensor(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;
		case "pollutionsensor":
			try {
				this.CrudPollutionSensor(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "weathersensor":
			try {
				this.CrudWeatherSensor(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
			}

			break;
		case "zone":
			try {
				this.CrudZone(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "alert":
			try {
				this.CrudAlerteStatistics(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "smartCity":
			try {
				this.CrudSmartcity(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "smartcity2":
			try {
				this.CrudSmartcity2(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;
		case "vehiclesSensor":
			try {
				this.CrudVehiclesSensor(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "bollard":
			try {
				this.CrudBollard(this.request.getOperation_type(), this.request.getTarget(), this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "empreinte":
			try {
				//System.out.println("Let's go to see what's in smartcity oumaima");
				this.CrudEmpreinteCarbone(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
			}

			break;

		case "transportation":
			try {
				System.out.println("Let's go to see what's in smartcity oumaima");
				this.CrudTransportation(this.request.getOperation_type(), this.request.getTarget(),
						this.request.getList());
			} catch (Exception e) {
			}

			break;

		}

	}

	public void run() {
		try {
			out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println(this.getName() + " connected");
			System.out.println("********************");
			System.out.println("");
			this.GenerateObject(); // IHM DON'T WORK
			this.CheckVehiclesThreshold();
			while (shouldRun) {
				this.StartCrud();
			}

			in.close();
			out.close();
			clientSocket.close();

		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(this.getName() + " disconnected");
			System.out.println("********************");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
