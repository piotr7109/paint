package modules.czesci;

import dodatki.MySQLJDBC;

public class Czesc
{
	private int id, dlugosc, kat;
	private int id_figury;
	private String typ;

	public Czesc()
	{

	}

	public Czesc(Czesc c)
	{
		id = c.getId();
		dlugosc = c.getDlugosc();
		kat = c.getKat();
		id_figury = c.getIdFigury();
		typ = c.getTyp();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getDlugosc()
	{
		return dlugosc;
	}

	public void setDlugosc(int dlugosc)
	{
		this.dlugosc = dlugosc;
	}

	public int getKat()
	{
		return kat;
	}

	public void setKat(int kat)
	{
		this.kat = kat;
	}

	public String getTyp()
	{
		return typ;
	}

	public void setTyp(String typ)
	{
		this.typ = typ;
	}

	public int getIdFigury()
	{
		return id_figury;
	}

	public void setIdFigury(int id_figury)
	{
		this.id_figury = id_figury;
	}

	public void insert()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("INSERT INTO t_czesci(id_figury, dlugosc, kat, typ) VALUES(%d, %d, %d, '%s')", id_figury, dlugosc, kat, typ);
		pgsq.queryOpertaion(query);
	}
	public void insertAtrapa()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("INSERT INTO t_czesci_atrapy(id_figury, dlugosc, kat, typ) VALUES(%d, %d, %d, '%s')", id_figury, dlugosc, kat, typ);
		pgsq.queryOpertaion(query);
	}

}
