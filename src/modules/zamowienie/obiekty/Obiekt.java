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
		String query = String.format("INSERT INTO %s (kod, nazwa,id_parent, uwagi) VALUES (%d, '%s',%d, '%s')", table, kod, nazwa, id_parent,"");
		pgsq.queryOpertaion(query);
	}
}
