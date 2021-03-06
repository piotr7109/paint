package modules.zamowienie.obiekty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.zamowienie.ZamowienieListaCore;

public class ObiektLista extends ZamowienieListaCore
{
	public ObiektLista(int id_parent)
	{ 
		super(id_parent, "t_zamowienia_obiekty");
		table = "t_zamowienia_obiekty";
	}
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Obiekt zamowienie_core = new Obiekt();
		
		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));
		zamowienie_core.setIdParent(rs.getInt("id_parent"));
		
		return zamowienie_core;
	}
	public ArrayList<Obiekt> ConvertObjectListToObiektLista(ArrayList<Object> objects)
	{
		ArrayList<Obiekt> zamowienie_core_lista = new ArrayList<Obiekt>();

		for (Object object : objects)
		{
			zamowienie_core_lista.add((Obiekt) object);
		}
		return zamowienie_core_lista;
	}
}
