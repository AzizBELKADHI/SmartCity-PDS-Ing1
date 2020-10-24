package sakao_client_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;



import sakao_common.Request;
import sakao_common.Response;

// TEST 
public class UpdateVehicleSensor3 {
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();

	private ObjectMapper mapper;
	

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


	public static void main(String[] args) throws IOException, JSONException {
		UpdateVehicleSensor3 client1 = new UpdateVehicleSensor3();
		//client1.startConnection("localhost", 3030);
		client1.startConnection("172.31.249.254", 3030);
		readFile table = new readFile();
		System.out.println("********************************");
		System.out.println("INITIALIZATION");
		System.out.println("Vehicles in Circulation in the city: 200");
		System.out.println("Vehicles MaxNumberThreshold : 500");
		System.out.println("SensorVehiculeInput1 = 0");
		System.out.println("SensorVehiculeOutput2 = 0");
		System.out.println("SensorVehiculeInput3 = 0");
		System.out.println("SensorVehiculeOutput4 = 0");
		System.out.println("********************************");
		
		Request req1 = new ObjectMapper().readValue(table.readFileInitilization(), Request.class);
		Request req2 = new ObjectMapper().readValue(table.readFileInitilizationSmartcity(), Request.class);
		
		client1.sendMessageToServer(req2);	
		System.out.println("Update done");
		System.out.println("********************");
		client1.sendMessageToServer(req1);
		System.out.println("Update done");
		System.out.println("********************");
		
		System.out.println("TEST 3");
		System.out.println("Updated SensorVehiculeInput1 to 200 ");
		System.out.println("SensorVehiculeOutput2 = 200");
		System.out.println("SensorVehiculeInput3 = 200");
		System.out.println("SensorVehiculeOutput4 = 0");
		
		
		
		System.out.println("Expected results : retractablebollard are lower");
		System.out.println("Number of vehicule in circulation = 200+400-200 = 400");
		System.out.println("TramFrequency : 8/10");
		
		
		
		
		Request req3 = new ObjectMapper().readValue(table.readFileVehiclesTEST3(), Request.class);
		
	
		//System.out.println(req1);
		

		
		client1.sendMessageToServer(req3);
		System.out.println("Update done");
		System.out.println("********************");
		client1.CloseConnection();
		
		
	}

}
