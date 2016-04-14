package modules.zamowienie.miejscowosci;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.zamowienie.ZamowienieListaCore;

public class MiejscowoscLista extends ZamowienieListaCore
{
	protected static String table = "t_zamowienia_miejscowosci";
	public MiejscowoscLista(int id_parent)
	{ 
		super(id_parent, table);
	}
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Miejscowosc zamowienie_core = new Miejscowosc();
		
		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));
		zamowienie_core.setIdParent(rs.getInt("id_parent"));
		
		return zamowienie_core;
	}
	public ArrayList<Miejscowosc> ConvertObjectListToMiejscowoscLista(ArrayList<Object> objects)
	{
		ArrayList<Miejscowosc> zamowienie_core_lista = new ArrayList<Miejscowosc>();

		for (Object object : objects)
		{
			zamowienie_core_lista.add((Miejscowosc) object);
		}
		return zamowienie_core_lista;
	}
}
