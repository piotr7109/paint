package modules.zamowienie.odbiorcy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.zamowienie.ZamowienieListaCore;

public class OdbiorcaLista extends ZamowienieListaCore
{
	protected String table = "t_zamowienia_odbiorcy";
	public OdbiorcaLista()
	{
		super(0, "");
		query = String.format("SELECT * FROM %s ORDER BY nazwa", table);
	}
	
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Odbiorca zamowienie_core = new Odbiorca();
		
		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));
		
		return zamowienie_core;
	}
	public ArrayList<Odbiorca> ConvertObjectListToOdbiorcaLista(ArrayList<Object> objects)
	{
		ArrayList<Odbiorca> zamowienie_core_lista = new ArrayList<Odbiorca>();

		for (Object object : objects)
		{
			zamowienie_core_lista.add((Odbiorca) object);
		}
		return zamowienie_core_lista;
	}
}
