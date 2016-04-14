package modules.zamowienie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.AbstractLista;

public class ZamowienieListaCore extends AbstractLista
{

	public ZamowienieListaCore(int id_parent, String table)
	{
		if (id_parent != 0)
		{
			query = String.format("SELECT * FROM %s WHERE id_parent=%d", table, id_parent);
		}
	}

	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		ZamowienieCore zamowienie_core = new ZamowienieCore();

		zamowienie_core.setId(rs.getInt("id"));
		zamowienie_core.setIdParent(rs.getInt("id_parent"));
		zamowienie_core.setKod(rs.getInt("kod"));
		zamowienie_core.setNazwa(rs.getString("nazwa"));

		return zamowienie_core;
	}

	public ArrayList<ZamowienieCore> ConvertObjectListToZamowienieCoreList(ArrayList<Object> objects)
	{
		ArrayList<ZamowienieCore> zamowienie_core_lista = new ArrayList<ZamowienieCore>();

		for (Object object : objects)
		{
			zamowienie_core_lista.add((ZamowienieCore) object);
		}
		return zamowienie_core_lista;
	}
}
