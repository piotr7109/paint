package modules.zamowienie.nazwy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.zamowienie.ZamowienieListaCore;

public class NazwaLista extends ZamowienieListaCore
{
	protected static String table = "t_zamowienia_nazwy";
	public NazwaLista(int id_parent)
	{ 
		super(id_parent,table);
	}
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Nazwa zamowienie_core = new Nazwa();
		
		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));
		zamowienie_core.setIdParent(rs.getInt("id_parent"));
		
		return zamowienie_core;
	}
	public ArrayList<Nazwa> ConvertObjectListToNazwaLista(ArrayList<Object> objects)
	{
		ArrayList<Nazwa> zamowienie_core_lista = new ArrayList<Nazwa>();

		for (Object object : objects)
		{
			zamowienie_core_lista.add((Nazwa) object);
		}
		return zamowienie_core_lista;
	}

}
