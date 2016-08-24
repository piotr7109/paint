package modules.zamowienie.elementy;

import java.util.Date;

import dodatki.MySQLJDBC;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.elementy.figury.FiguraFactory;

public class Element extends ZamowienieCore
{
	private String uwagi;
	private String termin_dostawy, rysunek;
	private Date data_utworzenia;

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

	public Date getDataUtworzenia()
	{
		return data_utworzenia;
	}

	public void setDataUtworzenia(Date data_utworzenia)
	{
		this.data_utworzenia = data_utworzenia;
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
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("INSERT INTO %s (kod, nazwa,id_parent, uwagi, termin_dostawy, rysunek, data_utworzenia) VALUES (%d, '%s',%d, '%s', '%s', '%s', NOW())", table, kod, nazwa, id_parent, "",
				termin_dostawy, rysunek);
		pgsq.queryOpertaion(query);
	}

	public void update()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("UPDATE %s SET nazwa = '%s',termin_dostawy = '%s', rysunek='%s' WHERE id = %d", table, nazwa, termin_dostawy, rysunek, id);
		pgsq.queryOpertaion(query);
	}
	
	@Override
	protected void deleteChildren()
	{
		FiguraFactory.usunWszystkie(id);
	}
}
