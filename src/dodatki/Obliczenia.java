package dodatki;
import java.awt.Point;

import dane.ZamowienieDane;
import modules.czesci.Czesc;
import modules.figury.Figura;


public class Obliczenia 
{
	
	public int getDlugosc(Point a, Point b)
    {
    	double dl = 0.0;
    	dl = Math.sqrt(Math.pow(a.getX()-b.getX(),2) + Math.pow(a.getY()-b.getY(),2) );
    	return (int)dl;
    }
	/**
	 * Funnkcja liczy k�t pomi�dzy punktami a i c wyko�ystuj�c punkt �rodkowy b
	 * @param a - punkt start
	 * @param b - punkt wsp�lny
	 * @param c - punkt koniec
	 * @return k�t
	 */
	public int getKat(Point a, Point b, Point c)
    {
		double kat1 = Math.atan2(c.getY()- b.getY(), c.getX()- b.getX());
    	double kat2 = Math.atan2(a.getY()- b.getY(), a.getX()- b.getX());
		
		double kat;
		kat = Math.toDegrees(kat1-kat2);
		if(kat<0)
    		kat = kat+360;
		return (int)(kat);
    }
	public static int obliczDlugosc(Figura fig)
	{
		int dlugosc = 0;

		for (Czesc czesc : fig.getCzesci())
		{
			dlugosc += czesc.getDlugosc();
		}

		return dlugosc;
	}
}
