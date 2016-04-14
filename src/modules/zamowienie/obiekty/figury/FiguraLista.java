package modules.zamowienie.obiekty.figury;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.AbstractLista;

public class FiguraLista extends AbstractLista
{
	public FiguraLista()
	{
		table = "t_obiekt_figury";
	}
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Figura fig = new Figura();

		fig.setId(rs.getInt("id"));
		fig.setIdObiektu(rs.getInt("id_obiektu"));
		fig.setKod(rs.getInt("kod"));
		fig.setPozycja(rs.getString("pozycja"));
		fig.setSztuk(rs.getInt("sztuk"));
		fig.setSrednica(rs.getInt("srednica"));
		fig.setIloscPaczek(rs.getInt("ilosc_paczek"));
		fig.setMaterial(rs.getInt("material"));
		fig.setSworzen(rs.getInt("sworzen"));
		fig.setUwagi(rs.getString("uwagi"));
		fig.setIloscSkokow(rs.getInt("ilosc_skokow"));
		fig.setPoziomSkoku(rs.getInt("poziom_skoku"));
		
		return fig;
	}
}
