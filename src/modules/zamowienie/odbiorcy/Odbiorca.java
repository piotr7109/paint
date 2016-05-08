package modules.zamowienie.odbiorcy;

import dodatki.MySQLJDBC;
import modules.zamowienie.ZamowienieCore;

public class Odbiorca extends ZamowienieCore
{
	@Override
	public void setIdParent(int id_parent)
	{

	}

	@Override
	public int getIdParent()
	{
		return 0;
	}

	public Odbiorca()
	{
		table = "t_zamowienia_odbiorcy";
	}

	@Override
	public void insert()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("INSERT INTO %s (kod, nazwa) VALUES (%d, '%s')", table, kod, nazwa);
		pgsq.queryOpertaion(query);
	}
}