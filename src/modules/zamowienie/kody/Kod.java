package modules.zamowienie.kody;

import dodatki.PostgreSQLJDBC;
import modules.zamowienie.ZamowienieCore;

public class Kod extends ZamowienieCore
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

	public Kod()
	{
		table = "t_zamowienia_kody";
	}

	@Override
	public void insert()
	{
		PostgreSQLJDBC pgsq = new PostgreSQLJDBC();
		String query = String.format("INSERT INTO %s (kod, nazwa) VALUES (%d, '%s')", table, kod, nazwa);
		pgsq.queryOpertaion(query);
	}
}