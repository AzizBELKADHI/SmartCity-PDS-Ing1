package sakao_mock_capteur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;

public class mockSensor1_4 {
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
		mockSensor1_4 client1 = new mockSensor1_4();
		client1.startConnection("172.31.249.254", 3030);
		readfile table = new readfile();

		System.out.println("*******************************************************************");
		System.out.println("For Start the simulation, press a number");
		System.out.println("*******************************************************************");

		Scanner sco = new Scanner(System.in);
		int choix = sco.nextInt();

		System.out.println("");
		System.out.println("instant T1 :");
		System.out.println("");

		Request req3 = new ObjectMapper().readValue(table.readFileVehiclesSensor4T1(), Request.class);
		client1.sendMessageToServer(req3);

		System.out.println("*******************************************************************");
		System.out.println("Press a number, to get instant T2");
		System.out.println("*******************************************************************");

		choix = sco.nextInt();
		System.out.println("");
		System.out.println("instant T2 :");
		System.out.println("");
		Request req4 = new ObjectMapper().readValue(table.readFileVehiclesSensor4T2(), Request.class);
		client1.sendMessageToServer(req4);

		System.out.println("*******************************************************************");
		System.out.println("Press a number, to get instant T3");
		System.out.println("*******************************************************************");

		choix = sco.nextInt();
		System.out.println("");
		System.out.println("instant T3 :");
		System.out.println("");
		Request req5 = new ObjectMapper().readValue(table.readFileVehiclesSensor4T3(), Request.class);
		client1.sendMessageToServer(req5);

		System.out.println("*******************************************************************");
		System.out.println("Press a number, to get instant T4");
		System.out.println("*******************************************************************");

		choix = sco.nextInt();

		System.out.println("");
		System.out.println("instant T4 :");
		System.out.println("");
		Request req6 = new ObjectMapper().readValue(table.readFileVehiclesSensor4T4(), Request.class);
		client1.sendMessageToServer(req6);

		System.out.println("*******************************************************************");
		System.out.println("Press a number, to get instant T5");
		System.out.println("*******************************************************************");

		choix = sco.nextInt();

		System.out.println("");
		System.out.println("instant T5 :");
		System.out.println("");
		Request req7 = new ObjectMapper().readValue(table.readFileVehiclesSensor4T5(), Request.class);
		client1.sendMessageToServer(req7);

		System.out.println("*******************************************************************");
		System.out.println("Press a number, to Close connection");
		System.out.println("*******************************************************************");

		choix = sco.nextInt();

		System.out.println("Update done");
		System.out.println("********************");
		client1.CloseConnection();

	}

}
