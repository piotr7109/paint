package inne;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import dodatki.MySQLJDBC;


public class Zaa {

	private int aap;
	private String nazwa;

	public int getAap() {
		return aap;
	}

	public void setAap(int aap) {
		this.aap = aap;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	
	public void setObject()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		Statement stmt;
		Connection c = pgsq.getC(); 
		try
		{
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM zaa where aap="+aap );
			while ( rs.next() ) 
			{
	            this.nazwa = rs.getString("nazwa");
	            
			}
			rs.close();
			stmt.close();
			c.close();

		}
		catch ( Exception e ) 
		{
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
	    }
	}
	
	
	
	

}
