package modules.zamowienie.elementy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.zamowienie.ZamowienieListaCore;

public class ElementLista extends ZamowienieListaCore
{ 

	public ElementLista(int id_parent)
	{
		super(id_parent, "t_zamowienia_elementy");
		table = "t_zamowienia_elementy";
	}

	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Element zamowienie_core = new Element();

		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setIdParent(rs.getInt("id_parent"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));
		zamowienie_core.setUwagi(rs.getString("uwagi"));

		return zamowienie_core;
	}
	public ArrayList<Element> ConvertObjectListToElementLista(ArrayList<Object> objects)
	{
		ArrayList<Element> zamowienie_core_lista = new ArrayList<Element>();

		for (Object object : objects)
		{
			zamowienie_core_lista.add((Element) object);
		}
		return zamowienie_core_lista;
	}
}
