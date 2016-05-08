package modules.zamowienie.elementy.figury;

import java.sql.ResultSet;
import java.sql.SQLException;

import dodatki.MySQLJDBC;
import modules.AbstractFactory;

public class FiguraFactory extends AbstractFactory
{
	public FiguraFactory()
	{
		super();
		tabela = "t_element_figury";
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

	public Figura getFiguraByKod(int kod)
	{
		query = String.format("SELECT * FROM %s where kod=%d", tabela, kod);
		Figura fig = (Figura) getObject();

		return fig;
	}
	public static void usunWszystkie(int id_elementu)
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query_czesci = String.format("DELETE FROM t_element_figura_czesci WHERE id_figury in (SELECT id from t_element_figury  where id_elementu = %d)", id_elementu);
		pgsq.queryOpertaion(query_czesci);
		
		pgsq = new MySQLJDBC();
		String query_figura = String.format("DELETE FROM t_element_figury where id_elementu=%d", id_elementu);
		pgsq.queryOpertaion(query_figura);
	}
}
