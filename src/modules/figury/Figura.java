package modules.figury;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dodatki.MySQLJDBC;
import modules.czesci.Czesc;
import modules.czesci.CzescLista;

public class Figura
{
	private int id;
	private ArrayList<Czesc> czesci = new ArrayList<Czesc>();
	private ArrayList<Czesc> czesci_atrapy = new ArrayList<Czesc>();
	private int kod;
	public int start_x, start_y;

	public Figura()
	{
		
	}
	
	public Figura(Figura fig)
	{
		this.kod = fig.kod;
		for(Czesc czesc: fig.getCzesci())
		{
			this.addCzesc(new Czesc(czesc));
		}
	}
	
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
	public ArrayList<Czesc> getCzesciAtrapy()
	{
		return this.czesci_atrapy;
	}
	public void setCzesciAtrapy()
	{
		this.czesci_atrapy.clear();
		
		CzescLista c_lista = new CzescLista();
		ArrayList<Object> lista_czesci = c_lista.getCzesciAtrapy(this.id);
		int size = lista_czesci.size();
		for(int i =0; i< size ;i++)
		{
			this.czesci_atrapy.add((Czesc)lista_czesci.get(i));
		}
	}
	public String toString()
	{
		return Integer.toString(kod);
	}
	public int insert()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("INSERT INTO t_figury(kod, start_x, start_y) VALUES(%d,%d,%d)", kod, start_x, start_y);
		pgsq.queryOpertaion(query);
		return this.getLastId();
	}
	public int getLastId()
	{
		int id = 0;
		MySQLJDBC pgsq = new MySQLJDBC();
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
