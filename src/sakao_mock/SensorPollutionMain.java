package sakao_mock;

public class SensorPollutionMain {

	public static void main(String[]args) {
		SensorPollutionThread sensorPollutionThread = new SensorPollutionThread();
		sensorPollutionThread.start();
	}
	
}
