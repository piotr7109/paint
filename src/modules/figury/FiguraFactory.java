package modules.figury;

import java.sql.ResultSet;
import java.sql.SQLException;

import modules.AbstractFactory;

public class FiguraFactory extends AbstractFactory
{
	public FiguraFactory()
	{
		super();
		tabela = "t_figury";
	}

	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Figura fig = new Figura();

		fig.setId(rs.getInt("id"));
		fig.setKod(rs.getInt("kod"));
		fig.start_x = rs.getInt("start_x");
		fig.start_y = rs.getInt("start_y");
		return fig;
	}

	public Figura getFiguraByKod(int kod)
	{
		query = String.format("SELECT * FROM %s where kod=%d", tabela, kod);
		Figura fig = (Figura) getObject();

		return fig;
	}

	public static boolean czyKodZajety(int kod)
	{
		FiguraFactory f_factory = new FiguraFactory();
		return f_factory.getFiguraByKod(kod) == null ? false : true;
	}
}
