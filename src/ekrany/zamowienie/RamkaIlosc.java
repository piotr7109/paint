package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;

import ekrany.Zamowienie;

public class RamkaIlosc
{
	public static void ramkaIlosc(Graphics g, Zamowienie panel)
	{
		int x = 10;
		int y = 430;
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 500, 50);
	}
}
