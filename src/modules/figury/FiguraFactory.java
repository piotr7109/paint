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
		
		return fig;
	}
}
