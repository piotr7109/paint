package dodatki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import system.Config;

public class MySQLJDBC
{
	private String host = Config.host;
	private String database = Config.database;
	private String user = Config.user;
	private String password = Config.password;

	private Connection c = null;

	public Connection getC()
	{
		return c;
	}

	public MySQLJDBC()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database+"?characterEncoding=utf8", user, password);
			// System.out.println("Opened database successfully");

		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		// System.out.println("Operation done successfully");

	}

	public void queryOpertaion(String query)
	{
		Statement stmt;
		System.out.println(query);
		try
		{
			stmt = c.createStatement();
			
			stmt.executeUpdate(query);
			stmt.close();
			c.close();

		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

}