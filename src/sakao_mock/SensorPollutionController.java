package sakao_mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;
import sakao_common.Zone;

public class SensorPollutionController {

	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();
	private ObjectMapper mapper;

	private List<Map<String, String>> map;


	public void getSensorPollutionScenario(List<Map<String, String>> map)
			throws JsonParseException, JsonMappingException, IOException {
		int schoolVal = 161;
		int pietVal = 190;
		int denseVal = 218;
		if (map.get(0).get("nexttothezone").equals("School")) {
			System.out.println("The sensor is located in the area "+map.get(0).get("location")+" next to a school");
			if (Integer.parseInt(map.get(1).get("temperature")) > 30) {
				double sVal = schoolVal * 0.85;
				System.out.println("***************************************");
				System.out.println("Beta average detected is : " + map.get(2).get("betaaverage"));
				System.out.println("Temperature is : " + map.get(1).get("temperature"));
				System.out.println("Alert threshold is : " + sVal);
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("***************************************");
					System.out.println("******An alert triggered*******");
					System.out.println("***************************************");
				} else {
					System.out.println("***************************************");
					System.out.println("******No alert******");
					System.out.println("***************************************");
				}
			} else if (Integer.parseInt(map.get(1).get("temperature")) < 15) {
				double sVal = schoolVal * 1.15;
				System.out.println("***************************************");
				System.out.println("Beta average detected is : " + map.get(2).get("betaaverage"));
				System.out.println("Temperature is : " + map.get(1).get("temperature"));
				System.out.println("Alert threshold is : " + sVal);
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("***************************************");
					System.out.println("******An alert triggered*******");
					System.out.println("***************************************");
				} else {
					System.out.println("***************************************");
					System.out.println("******No alert******");
					System.out.println("***************************************");
				}
			} else {
				System.out.println("***************************************");
				System.out.println("Beta average detected is : " + map.get(2).get("betaaverage"));
				System.out.println("Temperature is : " + map.get(1).get("temperature"));
				System.out.println("Alert threshold is : " + schoolVal);
				if (Integer.parseInt((map.get(2).get("betaaverage"))) > schoolVal) {
					System.out.println("***************************************");
					System.out.println("******An alert triggered*******");
					System.out.println("***************************************");
				} else {
					System.out.println("***************************************");
					System.out.println("******No alert******");
					System.out.println("***************************************");
				}
			}
		}
		if (map.get(0).get("nexttothezone").equals("Pedestrian")) {
			System.out.println("The sensor is located in the area "+map.get(0).get("location")+" next to a pedestrian areas");
			if (Integer.parseInt(map.get(1).get("temperature")) > 30) {
				double sVal = pietVal * 0.85;
				System.out.println("***************************************");
				System.out.println("Beta average detected is : " + map.get(2).get("betaaverage"));
				System.out.println("Temperature is : " + map.get(1).get("temperature"));
				System.out.println("Alert threshold is : " + sVal);
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("***************************************");
					System.out.println("******An alert triggered*******");
					System.out.println("***************************************");

				} else {
					System.out.println("***************************************");
					System.out.println("******No alert******");
					System.out.println("***************************************");
				}
			} else if (Integer.parseInt(map.get(1).get("temperature")) < 15) {
				double sVal = pietVal * 1.15;
				System.out.println("***************************************");
				System.out.println("Beta average detected is : " + map.get(2).get("betaaverage"));
				System.out.println("Temperature is : " + map.get(1).get("temperature"));
				System.out.println("Alert threshold is : " + sVal);
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("***************************************");
					System.out.println("******An alert triggered*******");
					System.out.println("***************************************");

				} else {
					System.out.println("***************************************");
					System.out.println("******No alert******");
					System.out.println("***************************************");
				}
			} else {
				System.out.println("***************************************");
				System.out.println("Beta average detected is : " + map.get(2).get("betaaverage"));
				System.out.println("Temperature is : " + map.get(1).get("temperature"));
				System.out.println("Alert threshold is : " + pietVal);
				if (Integer.parseInt((map.get(2).get("betaaverage"))) > pietVal) {
					System.out.println("***************************************");
					System.out.println("******An alert triggered*******");
					System.out.println("***************************************");

				} else {
					System.out.println("***************************************");
					System.out.println("******No alert******");
					System.out.println("***************************************");
				}
			}
		}
		if (map.get(0).get("nexttothezone").equals("Dense")) {
			System.out.println("The sensor is located in the area "+map.get(0).get("location")+" next to a dense traffic");
			if (Integer.parseInt(map.get(1).get("temperature")) > 30) {
				double sVal = denseVal * 0.85;
				System.out.println("***************************************");
				System.out.println("Beta average detected is : " + map.get(2).get("betaaverage"));
				System.out.println("Temperature is : " + map.get(1).get("temperature"));
				System.out.println("Alert threshold is : " + sVal);
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("***************************************");
					System.out.println("******An alert triggered*******");
					System.out.println("***************************************");

				} else {
					System.out.println("***************************************");
					System.out.println("******No alert******");
					System.out.println("***************************************");
				}
			} else if (Integer.parseInt(map.get(1).get("temperature")) < 15) {
				double sVal = denseVal * 1.15;
				System.out.println("***************************************");
				System.out.println("Beta average detected is : " + map.get(2).get("betaaverage"));
				System.out.println("Temperature is : " + map.get(1).get("temperature"));
				System.out.println("Alert threshold is : " + sVal);
				if (Double.parseDouble((map.get(2).get("betaaverage"))) > sVal) {
					System.out.println("***************************************");
					System.out.println("******An alert triggered*******");
					System.out.println("***************************************");

				} else {
					System.out.println("***************************************");
					System.out.println("******No alert******");
					System.out.println("***************************************");
				}
			} else {
				System.out.println("***************************************");
				System.out.println("Beta average detected is : " + map.get(2).get("betaaverage"));
				System.out.println("Temperature is : " + map.get(1).get("temperature"));
				System.out.println("Alert threshold is : " + denseVal);
				if (Integer.parseInt((map.get(2).get("betaaverage"))) > denseVal) {
					System.out.println("***************************************");
					System.out.println("******An alert triggered*******");
					System.out.println("***************************************");
				} else {
					System.out.println("***************************************");
					System.out.println("******No alert******");
					System.out.println("***************************************");
				}
			}
		}
	}

	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("waiting for connection in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("connection succeed");
	}

	public String sendMessageToServer(Request request) throws IOException {
		mapper = new ObjectMapper();
		String outjsonString = mapper.writeValueAsString(request);
		System.out.println("REQUEST SENT");
		System.out.println(outjsonString);
		System.out.println(" _____");
		System.out.println("");
		out.write(outjsonString + "\n");
		out.flush();
		String injsonString = in.readLine();
		System.out.println(injsonString);
		response = mapper.readValue(injsonString, Response.class);
		return response.toString();
	}

	public void CloseConnection() throws IOException {
		System.out.println("waiting for disconnection");
		out.close();
		in.close();
		clientSocket.close();
		System.out.println("disconnected");
	}
	
	
}
