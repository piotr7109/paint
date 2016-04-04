package modules.zamowienie;

import dodatki.PostgreSQLJDBC;

public class ZamowienieCore
{
	protected int id, id_parent;
	protected int kod;
	protected String nazwa;
	protected String table;

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

	public String getNazwa()
	{
		return nazwa;
	}

	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
	}

	public int getIdParent()
	{
		return id_parent;
	}

	public void setIdParent(int id_parent)
	{
		this.id_parent = id_parent;
	}

	public void insert()
	{
		PostgreSQLJDBC pgsq = new PostgreSQLJDBC();
		String query = String.format("INSERT INTO %s (kod, nazwa) VALUES (%d, '%s')", table, kod, nazwa);
		pgsq.queryOpertaion(query);
	}

	protected void delete()
	{
		PostgreSQLJDBC pgsq = new PostgreSQLJDBC();
		String query = String.format("DELETE FROM %s WHERE kod = %d", table, kod);
		pgsq.queryOpertaion(query);
	}

}
