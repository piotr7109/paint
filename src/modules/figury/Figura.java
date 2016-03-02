package modules.figury;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dodatki.PostgreSQLJDBC;
import modules.czesci.Czesc;
import modules.czesci.CzescLista;

public class Figura
{
	private int id;
	private ArrayList<Czesc> czesci = new ArrayList<Czesc>();
	private int kod;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getKod()
	{
		return kod;
	}

	public void setKod(int kod)
	{
		this.kod = kod;
	}
	public ArrayList<Czesc> getCzesci()
	{
		
		return this.czesci;
	}
	public void setCzesci()
	{
		this.czesci.clear();
		
		CzescLista c_lista = new CzescLista();
		ArrayList<Object> lista_czesci = c_lista.getCzesci(this.id);
		int size = lista_czesci.size();
		for(int i =0; i< size ;i++)
		{
			this.czesci.add((Czesc)lista_czesci.get(i));
		}
	}
	public void addCzesc(Czesc czesc)
	{
		this.czesci.add(czesc);
	}
	public String toString()
	{
		return Integer.toString(kod);
	}
	public int insert()
	{
		PostgreSQLJDBC pgsq = new PostgreSQLJDBC();
		String query = String.format("INSERT INTO t_figury(kod) VALUES(%d)", kod);
		pgsq.queryOpertaion(query);
		return this.getLastId();
	}
	public int getLastId()
	{
		int id = 0;
		PostgreSQLJDBC pgsq = new PostgreSQLJDBC();
		String query = String.format("SELECT id FROM t_figury order by id desc limit 1");
		try
		{
			Statement stmt = pgsq.getC().createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				id = rs.getInt("id");

			}
			rs.close();
		}
		catch (SQLException e)
		{

		}
		return id;
	}

}
