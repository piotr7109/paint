package modules.zamowienie.elementy;

import dodatki.PostgreSQLJDBC;
import modules.zamowienie.ZamowienieCore;

public class Element extends ZamowienieCore
{
	private String uwagi;
	public Element()
	{
		table = "t_zamowienia_elementy";
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
