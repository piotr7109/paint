package modules.zamowienie.kody;

import java.sql.ResultSet;
import java.sql.SQLException;

import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.ZamowienieListaCore;

public class KodLista extends ZamowienieListaCore
{
	protected String table = "t_zamowienia_kody";
	public KodLista()
	{
		super(0);
		query = String.format("SELECT * FROM %s", table);
	}
	
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		ZamowienieCore zamowienie_core = new ZamowienieCore();
		
		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));
		
		return zamowienie_core;
	}
}
