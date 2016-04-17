package ekrany.zamowienie.ramki;

import java.awt.Color;
import java.awt.Graphics;

import ekrany.Zamowienie;

public class RamkaIlosc
{
	public static double scale = Zamowienie.scale;
	public static void ramkaIlosc(Graphics g, Zamowienie panel)
	{
		int x = 10;
		int y = 430;
		g.setColor(Color.WHITE);
		g.drawRect(rescale(x),rescale(y),rescale(500),rescale(50));
	}
	
	private static int rescale(int number)
	{
		return (int)(number*scale);
	}
	public static void rescale(double scale)
	{
		
	}
}
