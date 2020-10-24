package sakao_connection_pool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConnectionFileReader {

	private Properties p;
	private final static int MAX_CONNECTIONS = 1;


	public ConnectionFileReader() {
		p = new Properties();
	}

	///// open the file and read it
	public void Read() {
		InputStream fis;
		try {
			fis = getClass().getClassLoader().getResourceAsStream("sakao_connection_pool/ConnectionFile.xml");
			p.loadFromXML(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	///// return the keys of the file
	///// 
	///// this function allows to browse the file
	public String getProperty(String key) {
		return p.getProperty(key);

	}

	public Properties getP() {
		return p;
	}

	public void setP(Properties p) {
		this.p = p;
	}

	public static int getMaxConnections() {
		return MAX_CONNECTIONS;
	}

}
