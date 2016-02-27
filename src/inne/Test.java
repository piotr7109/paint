package inne;
import java.sql.ResultSet;

import modules.figury.Figura;
import modules.figury.FiguraFactory;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		FiguraFactory f_factory = new FiguraFactory();
		Figura f = f_factory.getFiguraByKod(9);
		System.out.println(f.getId());
		

	}

}
