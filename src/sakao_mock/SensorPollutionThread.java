package sakao_mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;

import sakao_common.Request;
import sakao_common.Response;
import sakao_server.ConfigurationService;
import sakao_server.Crud_Service;
import sakao_server.PollutionService;
import sakao_server.SensorsService;
import sakao_server.WeatherService;
import sakao_server.ZoneService;

public class SensorPollutionThread extends Thread {
	
	//store scenarios in a list
	public List<Map<String, String>> getScenario()  {
		ReadScenarioMock readScenarioMock = new ReadScenarioMock();
		String scenarios = readScenarioMock.readScenarioMock();

		List<Map<String, String>> scenas = new ArrayList<>();
		Map<String, String> map;
		String[] tabscenas = scenarios.split("/");
		for (int i = 0; i < tabscenas.length; i++) {
			map = new TreeMap<>(new Comparator<String>() {

				@Override
				public int compare(String h1, String h2) {
					return h1.compareTo(h2);
				}

			});
			String[] tabscen = tabscenas[i].split(";");

			for (int j = 0; j < tabscen.length; j++) {
				String[] tabVals = tabscen[j].split(":");

				for (int k = 0; k < tabVals.length - 1; k++) {
					map.put(tabVals[k], tabVals[k + 1]);

				}
			}

			scenas.add(map);
		}
		return scenas;
	}

	public void run() {
		int i = 0;

		SensorPollutionController sensorPollutionController = new SensorPollutionController();
		try {
			sensorPollutionController.startConnection("localhost", 3030);
			//sensorPollutionController.startConnection("172.31.249.254", 3030);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (i < this.getScenario().size()) {
			try {
				
					Map<String, String> scen1 = this.getScenario().get(i);
					Map<String, String> scen2 = this.getScenario().get(i + 1);
					Map<String, String> scen3 = this.getScenario().get(i + 2);
					List<Map<String, String>> scen = new ArrayList<Map<String, String>>(3);
					scen.add(scen1);
					scen.add(scen2);
					scen.add(scen3);
					
					sensorPollutionController.getSensorPollutionScenario(scen);
					i+=3;
					Thread.sleep(5000);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			sensorPollutionController.CloseConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
