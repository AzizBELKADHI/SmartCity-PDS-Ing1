package sakao_connection_pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataSource {

	private static JDBCConnectionPool instance;

	public DataSource() throws ClassNotFoundException {
		instance = new JDBCConnectionPool();
	}

	public static ArrayList<Connection> getListConnectionavailable() {
		return instance.getListConnectionavailable();
	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = instance.getConnectionFromPool();
		return connection;
	}

	public static void returnConnection(Connection connection) {
		instance.returConnectionToPool(connection);
	}


	public static void closeInstance() {
		instance.closeAllConnection();
	}

	public static JDBCConnectionPool getInstance() {
		return instance;
	}

	public static void setInstance(JDBCConnectionPool instance) {
		DataSource.instance = instance;
	}

}
