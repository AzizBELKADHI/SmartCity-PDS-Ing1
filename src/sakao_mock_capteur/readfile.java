package sakao_mock_capteur;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class readfile {

	public String readFileInitilization() throws IOException {
		//InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\InitilizationTESTVehicles.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("table-to-be-inserted/InitilizationTESTVehicles.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileInitilizationSmartcity() throws IOException {
	//	InputStream fis = new FileInputStream("resources\\table-to-be-inserted\\InitilizationTESTSmartCity.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("table-to-be-inserted/InitilizationTESTSmartCity.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	
	public String readFileVehiclesSensor1T1() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor1\\capteur1.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor1/Capteur1.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor2T1() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor1\\capteur2.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor1/Capteur2.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor3T1() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor1\\capteur3.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor1/Capteur3.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor4T1() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor1\\capteur4.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor1/Capteur4.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor1T2() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor2\\capteur1.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor2/Capteur1.json"));
		
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	public String readFileVehiclesSensor2T2() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor2\\capteur2.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor2/Capteur2.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor3T2() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor2\\capteur3.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor2/Capteur3.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor4T2() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor2\\capteur4.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor2/Capteur4.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor1T3() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor3\\capteur1.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor3/Capteur1.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor2T3() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor3\\capteur2.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor3/Capteur2.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor3T3() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor3\\capteur3.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor3/Capteur3.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor4T3() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor3\\capteur4.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor3/Capteur4.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	
	public String readFileVehiclesSensor1T4() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor4\\capteur1.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor4/Capteur1.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor2T4() throws IOException {
	//	InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor4\\capteur2.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor4/Capteur2.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor3T4() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor4\\capteur3.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor4/Capteur3.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor4T4() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor4\\capteur4.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor4/Capteur4.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor1T5() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor5\\capteur1.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor5/Capteur1.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor2T5() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor5\\capteur2.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor5/Capteur2.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	
	public String readFileVehiclesSensor3T5() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor5\\capteur3.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor5/Capteur3.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
	public String readFileVehiclesSensor4T5() throws IOException {
		//InputStream fis = new FileInputStream("resources\\scenario\\scenarioVehiclesSensor5\\capteur4.json");
		InputStreamReader ipsr = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("scenarioVehiclesSensor5/Capteur4.json"));
		BufferedReader br = new BufferedReader(ipsr);
		String outjsonString = "";
		String chaine = "";

		while ((outjsonString = br.readLine()) != null) {
			chaine = chaine + outjsonString;
		}
		//System.out.println("Request sent : " + chaine);
		return chaine;
	}
}
