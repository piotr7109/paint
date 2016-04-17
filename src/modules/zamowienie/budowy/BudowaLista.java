package modules.zamowienie.budowy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.zamowienie.ZamowienieListaCore;

public class BudowaLista extends ZamowienieListaCore
{
	public BudowaLista(int id_parent)
	{

		super(id_parent, "t_zamowienia_budowy");
		table = "t_zamowienia_budowy";
	}

	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Budowa zamowienie_core = new Budowa();

		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));
		zamowienie_core.setIdParent(rs.getInt("id_parent"));

		return zamowienie_core;
	}

	public ArrayList<Budowa> ConvertObjectListToBudowaLista(ArrayList<Object> objects)
	{
		ArrayList<Budowa> zamowienie_core_lista = new ArrayList<Budowa>();

		for (Object object : objects)
		{
			zamowienie_core_lista.add((Budowa) object);
		}
		return zamowienie_core_lista;
	}

}
