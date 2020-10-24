package sakao_connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import sakao_client.ClientSakao;
import sakao_server.ClientThread;

public class JDBCConnectionPool {

	private static ArrayList<Connection> listConnectionavailable = new ArrayList<Connection>();///// OK

	///// Create the pool of connections
	public JDBCConnectionPool() {
		this.initializeConnectionPool();
		System.out.println(ConnectionFileReader.getMaxConnections() + " connexion(s) ha(s/ve) been created");
	}

	///// Creating a new connection in order to put it in the connection pool
	public Connection createNewConnection() throws ClassNotFoundException, SQLException {
		ConnectionFileReader connectionfilereader = new ConnectionFileReader();
		connectionfilereader.Read();
		Class.forName(connectionfilereader.getProperty("driver"));
		Connection con = DriverManager.getConnection(connectionfilereader.getProperty("url"),
				connectionfilereader.getProperty("login"), connectionfilereader.getProperty("password"));
		return con;

	}

	///// Initializing the connection pool, feed the array list with 5 connections
	public void initializeConnectionPool() {
		while (!IsFull()) {
			try {
				listConnectionavailable.add(this.createNewConnection());
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

	}

///Check if there are less than 5 connection available in the pool
	public synchronized boolean IsFull() {
		final int MAX_POOL_CONNECTION = ConnectionFileReader.getMaxConnections();
		return (listConnectionavailable.size() == MAX_POOL_CONNECTION);
	}

	public synchronized boolean IsEmpty() {
		return listConnectionavailable.size() == 0;
	}

	///// Take a connection in the pool
	public synchronized Connection getConnectionFromPool() {
		Connection connection = null;
		while (this.IsEmpty()) {
			try {
				System.out.println("Please wait");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		connection = listConnectionavailable.get(0);
		listConnectionavailable.remove(0);
		this.notifyAll();
		return connection;
	}

	///// Put the connection in the pool
	public synchronized void returConnectionToPool(Connection connection) {
		listConnectionavailable.add(connection);
	}

	public void closeAllConnection() {
		for (Connection connection : listConnectionavailable) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public ArrayList<Connection> getListConnectionavailable() {
		return JDBCConnectionPool.listConnectionavailable;
	}

	public void setListConnectionavailable(ArrayList<Connection> listConnectionavailable) {
		JDBCConnectionPool.listConnectionavailable = listConnectionavailable;
	}

}
