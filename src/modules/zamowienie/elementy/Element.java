package modules.zamowienie.elementy;

import dodatki.PostgreSQLJDBC;
import modules.zamowienie.ZamowienieCore;

public class Element extends ZamowienieCore
{
	private String uwagi;
	private String termin_dostawy, rysunek;

	public String getTerminDostawy()
	{
		return termin_dostawy;
	}

	public void setTerminDostawy(String termin_dostawy)
	{
		this.termin_dostawy = termin_dostawy;
	}

	public String getRysunek()
	{
		return rysunek;
	}

	public void setRysunek(String rysunek)
	{
		this.rysunek = rysunek;
	}

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
		String query = String.format("INSERT INTO %s (kod, nazwa,id_parent, uwagi, termin_dostawy, rysunek) VALUES (%d, '%s',%d, '%s', '%s', '%s')", table, kod, nazwa, id_parent, "", termin_dostawy,
				rysunek);
		pgsq.queryOpertaion(query);
	}

	public void update()
	{
		PostgreSQLJDBC pgsq = new PostgreSQLJDBC();
		String query = String.format("UPDATE %s SET nazwa = '%s',termin_dostawy = '%s', rysunek='%s' WHERE id = %d", table, nazwa, termin_dostawy, rysunek, id);
		pgsq.queryOpertaion(query);
	}
}
