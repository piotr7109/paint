package modules.zamowienie.elementy.figury;

import java.sql.ResultSet;
import java.sql.SQLException;

import modules.AbstractLista;

public class FiguraLista extends AbstractLista
{

	public FiguraLista(int id_elementu)
	{
		table = "t_element_figury";
		query = String.format("SELECT * from %s WHERE id_elementu = %d", table, id_elementu );
		System.out.println(query);
	}
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Figura fig = new Figura();

		fig.setId(rs.getInt("id"));
		fig.setIdElementu(rs.getInt("id_elementu"));
		fig.setKod(rs.getInt("kod"));
		fig.setPozycja(rs.getString("pozycja"));
		fig.setSztuk(rs.getInt("sztuk"));
		fig.setSrednica(rs.getInt("srednica"));
		fig.setIloscPaczek(rs.getInt("ilosc_paczek"));
		fig.setMaszyna(rs.getInt("maszyna"));
		fig.setSworzen(rs.getInt("sworzen"));
		fig.setUwagi(rs.getString("uwagi"));
		fig.setIloscSkokow(rs.getInt("ilosc_skokow"));
		fig.setPoziomSkoku(rs.getInt("poziom_skoku"));
		
		return fig;
	}
}
