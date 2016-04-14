package modules.zamowienie.placowki;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.zamowienie.ZamowienieListaCore;

public class PlacowkaLista extends ZamowienieListaCore
{
	protected static String table = "t_zamowienia_placowki";

	public PlacowkaLista(int id_parent)
	{
		super(id_parent, table);
	}

	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Placowka zamowienie_core = new Placowka();

		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));
		zamowienie_core.setIdParent(rs.getInt("id_parent"));

		return zamowienie_core;
	}

	public ArrayList<Placowka> ConvertObjectListToPlacowkaLista(ArrayList<Object> objects)
	{
		ArrayList<Placowka> zamowienie_core_lista = new ArrayList<Placowka>();

		for (Object object : objects)
		{
			zamowienie_core_lista.add((Placowka) object);
		}
		return zamowienie_core_lista;
	}
}
