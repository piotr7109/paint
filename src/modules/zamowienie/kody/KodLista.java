package modules.zamowienie.kody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.zamowienie.ZamowienieListaCore;

public class KodLista extends ZamowienieListaCore
{
	protected String table = "t_zamowienia_kody";
	public KodLista()
	{
		super(0, "");
		query = String.format("SELECT * FROM %s", table);
	}
	
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Kod zamowienie_core = new Kod();
		
		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));
		
		return zamowienie_core;
	}
	public ArrayList<Kod> ConvertObjectListToKodLista(ArrayList<Object> objects)
	{
		ArrayList<Kod> zamowienie_core_lista = new ArrayList<Kod>();

		for (Object object : objects)
		{
			zamowienie_core_lista.add((Kod) object);
		}
		return zamowienie_core_lista;
	}
}
