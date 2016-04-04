package modules.zamowienie.obiekty;

import dodatki.PostgreSQLJDBC;
import modules.zamowienie.ZamowienieCore;

public class Obiekt extends ZamowienieCore
{
	private String uwagi;
	public Obiekt()
	{
		table = "t_zamowienia_obiekty";
	}
	public String getUwagi()
	{
		return uwagi;
	}
	public void setUwagi(String uwagi)
	{
		this.uwagi = uwagi;
	}
	public void insert()
	{
		PostgreSQLJDBC pgsq = new PostgreSQLJDBC();
		String query = String.format("INSERT INTO %s (kod, nazwa, uwagi) VALUES (%d, '%s', '%s')", table, kod, nazwa, uwagi);
		pgsq.queryOpertaion(query);
	}
}
