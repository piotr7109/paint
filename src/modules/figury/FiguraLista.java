package modules.figury;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.AbstractLista;

public class FiguraLista extends AbstractLista
{
	public FiguraLista()
	{
		table = "t_figury";
	}
	protected Object fetchObject(ResultSet rs) throws SQLException
	{
		Figura fig = new Figura();
		
		fig.setId(rs.getInt("id"));
		fig.setKod(rs.getInt("kod"));
		return fig;
	}
}
