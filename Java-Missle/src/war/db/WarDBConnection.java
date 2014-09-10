package war.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class WarDBConnection {
	private static Connection   connection;
	private static String       dbUrl;
	private static ResultSet    rs;
	private static PreparedStatement statement;
	
	static {
		dbUrl = "jdbc:mysql://localhost/war";
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(dbUrl, "root", "");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println(e.getMessage());
				e = e.getNextException();
			}
		}
	}

	public static int addNewMissile(String id,String destination,int damage,int flyTime,String status) {
		int res = 0;
		try {
		//	connection.createStatement();
			statement = (PreparedStatement) connection.prepareStatement("INSERT INTO War.missile (id, date,destination,damage,flyTime,status) VALUES (?, now(), ?, ?, ?, ?)");
			statement.setString(1, id);
			// statement.setString(1, "now()");
			statement.setString(2, destination);
			statement.setInt(3, damage);
			statement.setInt(4, flyTime);
			statement.setString(5, status);
			
			res = statement.executeUpdate();
		} catch (SQLException e) {
			while (e != null) {
				System.out.println(e.getMessage());
				e = e.getNextException();
			}
		}
		return res;
	}
	
	public static List<String> getAllTablesNames()  {
		List<String> allTables = new ArrayList<String>();
		try {
			DatabaseMetaData md = connection.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			
			while (rs.next()) {
				allTables.add(rs.getString(3));
			}
		} catch (SQLException e) {
			while (e != null) {
				e.printStackTrace();
				e = e.getNextException();
			}
		}
		return allTables;
	}
	
	public static Vector<String[]> getQueryData(String tableName, Vector<String> headers) {
		Vector<String[]> rowsData = new Vector<String[]>();
		try {
			statement = (PreparedStatement) connection.prepareStatement("SELECT * FROM " + tableName);
			rs = statement.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int numOfCols = meta.getColumnCount();

			// rebuild the headers array with the new column names
			headers.clear();
			for (int h = 1; h <= numOfCols; h++) {
				headers.add(meta.getColumnName(h));
			}
			
			while (rs.next()) {
				String[] record = new String[numOfCols];
				for (int i = 0; i < numOfCols; i++) {
					record[i] = rs.getString(i + 1);
				}
				rowsData.addElement(record);
			}
		} catch (SQLException e) {
			while (e != null) {
				e.printStackTrace();
				e = e.getNextException();
			}
		}
		return rowsData;
	}
	
	public static void closeDB() {
		try {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			System.out.println("Could not close the current connection.");
			e.printStackTrace();
		}
	}

}
