package modules.zamowienie;

import dodatki.MySQLJDBC;

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
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("INSERT INTO %s (kod, nazwa, id_parent) VALUES (%d, '%s', %d)", table, kod, nazwa, id_parent);
		
		pgsq.queryOpertaion(query);
	}
	public void update()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("UPDATE %s SET nazwa='%s' WHERE id=%d", table,nazwa, id);
		
		pgsq.queryOpertaion(query);
	}
	protected void delete()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("DELETE FROM %s WHERE kod = %d", table, kod);
		pgsq.queryOpertaion(query);
	}
	public String toString()
	{
		return this.nazwa;
	}

}
