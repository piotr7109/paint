package modules.zamowienie.elementy.figury.czesci;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.AbstractLista;

public class CzescLista extends AbstractLista
{
	public CzescLista()
	{
		table = "t_element_figura_czesci";
	}
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Czesc czesc = new Czesc();
		
		czesc.setId(rs.getInt("id"));
		czesc.setDlugosc(rs.getInt("dlugosc"));
		czesc.setIdFigury(rs.getInt("id_figury"));
		czesc.setKat(rs.getInt("kat"));
		czesc.setTyp(rs.getString("typ"));
		
		return czesc;
	}
	public ArrayList<Object> getCzesci(int id_figury)
	{
		query = String.format("select * FROM t_element_figura_czesci where id_figury =  %d ORDER by id", id_figury);
		ArrayList<Object> czesci = (ArrayList<Object>) getList();
		return czesci;
	}
	public ArrayList<Object> getCzesciAtrapy(int id_figury)
	{
		query = String.format("select * FROM t_czesci_atrapy where id_figury =  %d order by id", id_figury);
		ArrayList<Object> czesci = (ArrayList<Object>) getList();
		return czesci;
	}
}
