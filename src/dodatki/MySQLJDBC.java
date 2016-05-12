package dodatki;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLJDBC
{
	private String host = "localhost:3306";
	private String database = "rbs_stal";
	private String user = "user";
	private String password = "123";

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