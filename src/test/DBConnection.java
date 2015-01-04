package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.mysql.jdbc.Driver;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add its
 * .jar file to your build path. You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for
 * jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path: 1. Right click on your project 2. Go to Build
 * Path -> Add External Archives... 3. Select the file
 * mysql-connector-java-5.1.24-bin.jar NOTE: If you have a different version of
 * the .jar file, the name may be a little different.
 * 
 * The user name and password are both "root", which should be correct if you
 * followed the advice in the MySQL tutorial. If you want to use different
 * credentials, you can change them below.
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using
 * password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isn't
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class DBConnection {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/**
	 * The name of the database we are testing with (this default is installed
	 * with MySQL)
	 */
	private final String dbName = "miniEbay";

	/** The name of the table we are testing with */
	private final String tableName = "JDBC_TEST";
	private String driver = "com.mysql.jdbc.Driver";

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName
				+ ":" + this.portNumber + "/" + this.dbName, connectionProps);

		return conn;
	}

	public List<Item> getCurrentItems() {
		// Connect to MySQL
		Connection conn = null;
		List<Item> result = new ArrayList<Item>();
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return null;
		}
		// Create a table
		try {
			String query = "SELECT * FROM storage";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				Item a = new Item(name, price, id);
				result.add(a);
			}
			st.close();
			return result;

		} catch (SQLException e) {
			System.out.println("ERROR: Could not get user information");
			e.printStackTrace();
			return null;
		}
	}

	public User getUser(String userName, String password) {
		// Connect to MySQL
		Connection conn = null;
		List<Item> result = new ArrayList<Item>();
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return null;
		}
		// Create a table
		try {
			String query = "SELECT * FROM user where name= \'" + userName
					+ "\'";
			// create the java statement
			Statement st = conn.createStatement();
			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				// iterate through the java resultset
				String name = rs.getString("name");
				String pass = rs.getString("password");
				if (pass.equals(password)) {
					double balance = rs.getDouble("balance");
					User user = new User(name, password, balance);
					return user;
				}
			}
		} catch (SQLException e) {
			System.out.println("ERROR: Could not get user information");
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {
		DBConnection connection = new DBConnection();
		System.out.println(connection.getCurrentItems().get(0).getName());
	}

}