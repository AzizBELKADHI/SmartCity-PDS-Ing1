package sakao_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_client_insert.TablesToBeInserted;
import sakao_common.Configuration;
import sakao_common.Request;
import sakao_common.Response;

public class ClientSakao {
	private Socket clientSocket;
	private OutputStreamWriter out;
	private BufferedReader in;
	private Response response = new Response();

	private ObjectMapper mapper;
	private final static String SELECT_ALL = "SELECT_ALL";
	private final static String DELETE_ALL = "DELETE_ALL";
	private final static String INSERT = "INSERT";
	private final static String DELETE = "DELETE";
	private final static String UPDATE_NAME = "UPDATE_NAME";
	private final static String UPDATE_AGE = "UPDATE_AGE";
	// private final static String STUDENT = "Student";

	public void startConnection(String ip, int port) throws IOException, JSONException {
		System.out.println("waiting for connection in to the server");
		clientSocket = new Socket(ip, port);
		out = new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
		System.out.println("connection succeed");
		this.StartIHM();
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
		if (request.getOperation_type().equals(SELECT_ALL)) {
			System.out.println("Response");
			System.out.println(injsonString);
		}

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

	///// The following function will be use in the futur to decide first in which
	///// table you want to send request
	public void StartIHM() throws IOException {
		Scanner sco = new Scanner(System.in);
		System.out.println("LIST OF TABLES, PLEASE CHOOSE A TABLE");
		int choice = 0;

		while (choice < 6 && choice >= 0) {
			System.out.println("1.Configuration");
			System.out.println("2.Sensor");
			System.out.println("6.Log out");

			int choix = sco.nextInt();
			choice = choix;

			switch (choix) {
			case 1:
				this.StartIHMConfiguration();
			case 2:
				this.StartIHMSensor();
			case 6:
				try {
					this.CloseConnection();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public void StartIHMSensor() throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("CRUD MENU");
		int choice = 0;

		while (choice < 7 && choice >= 0) {
			System.out.println("1.Add a sensor");
			System.out.println("2.View all sensors");
			System.out.println("6.Log out");
			System.out.println("********************");
			System.out.println("");

			int choix = sc.nextInt();

			choice = choix;

			switch (choix) {

			/*
			 * case 1: TablesToBeInserted table = new TablesToBeInserted(); Request req1 =
			 * new ObjectMapper().readValue(table.readFileConfiguration(), Request.class);
			 * this.sendMessageToServer(req1); System.out.println("Insert done");
			 * System.out.println("********************");
			 * 
			 * break;
			 */

			case 2:///// OK
				try {
					Request request = new Request(SELECT_ALL, "sensor");
					this.sendMessageToServer(request);
					System.out.println("Display done");
					System.out.println("********************");
				} catch (IOException e) {
					e.printStackTrace();
				}

				break;

			/*
			 * case 3: System.out.println("Please enter the id"); int idtodelete =
			 * sc.nextInt(); /*Request request = new Request(DELETE, STUDENT, idtodelete);
			 * this.sendMessageToServer(request);
			 */
			/*
			 * System.out.println("Delete done");
			 * System.out.println("********************");
			 * 
			 * break;
			 * 
			 * case 4: System.out.println("Please enter the id"); int idupdateage =
			 * sc.nextInt(); System.out.println("Please enter the new age"); int
			 * ageupdateage = sc.nextInt(); /*Request request = new Request(UPDATE_AGE,
			 * STUDENT, idupdateage, ageupdateage); this.sendMessageToServer(request);
			 */
			/*
			 * System.out.println("Update age done");
			 * System.out.println("********************");
			 * 
			 * break;
			 * 
			 * case 5: try { Request request = new Request(DELETE_ALL, STUDENT);
			 * this.sendMessageToServer(request); System.out.println("Delete all done");
			 * System.out.println("********************"); } catch (IOException e1) {
			 * e1.printStackTrace(); }
			 * 
			 * break;
			 */
			case 6: ///// OK
				try {
					System.out.println("********************");
					System.out.println("You left the menu, see you soon thank you !");
					sc.close();
					this.CloseConnection();

				} catch (Exception e) {
					;
				}

			}

		}

	}

	public void StartIHMConfiguration() throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("CRUD MENU");
		int choice = 0;

		while (choice < 7 && choice >= 0) {
			System.out.println("1.Add a configuration");
			System.out.println("2.View all configurations");
		/*	System.out.println("3.Delete a configuration");
			System.out.println("4.Update a configuration");
			System.out.println("5.Delete all configurations");*/
			System.out.println("6.Log out");
			System.out.println("********************");
			System.out.println("");

			int choix = sc.nextInt();

			choice = choix;

			switch (choix) {

			case 1:
				TablesToBeInserted table = new TablesToBeInserted();
				Request req1 = new ObjectMapper().readValue(table.readFileConfiguration(), Request.class);
				this.sendMessageToServer(req1);
				System.out.println("Insert done");
				System.out.println("********************");

				break;

			case 2:///// OK
				try {
					Request request = new Request(SELECT_ALL, "configuration");
					this.sendMessageToServer(request);
					System.out.println("Display done");
					System.out.println("********************");
				} catch (IOException e) {
					e.printStackTrace();
				}

				break;

			/*
			 * case 3: System.out.println("Please enter the id"); int idtodelete =
			 * sc.nextInt(); /*Request request = new Request(DELETE, STUDENT, idtodelete);
			 * this.sendMessageToServer(request);
			 */
			/*
			 * System.out.println("Delete done");
			 * System.out.println("********************");
			 * 
			 * break;
			 * 
			 * case 4: System.out.println("Please enter the id"); int idupdateage =
			 * sc.nextInt(); System.out.println("Please enter the new age"); int
			 * ageupdateage = sc.nextInt(); /*Request request = new Request(UPDATE_AGE,
			 * STUDENT, idupdateage, ageupdateage); this.sendMessageToServer(request);
			 */
			/*
			 * System.out.println("Update age done");
			 * System.out.println("********************");
			 * 
			 * break;
			 * 
			 * case 5: try { Request request = new Request(DELETE_ALL, STUDENT);
			 * this.sendMessageToServer(request); System.out.println("Delete all done");
			 * System.out.println("********************"); } catch (IOException e1) {
			 * e1.printStackTrace(); }
			 * 
			 * break;
			 */
			case 6: ///// OK
				try {
					System.out.println("********************");
					System.out.println("You left the menu, see you soon thank you !");
					sc.close();
					this.CloseConnection();

				} catch (Exception e) {
					;
				}

			}

		}

	}

	public static void main(String[] args) throws IOException, JSONException {
		ClientSakao client1 = new ClientSakao();
		client1.startConnection("localhost", 3030);

	}

}
