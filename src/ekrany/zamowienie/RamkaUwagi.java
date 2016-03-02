package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import dodatki.CONST;

public class RamkaUwagi
{
	public static void ramkaUwagi(Graphics g, JPanel panel)
	{
		// ramka uwagi
		int x = 780;
		int y = 580;
		g.setColor(Color.PINK);
		g.drawRect(x, y, 210, 100);

		panel.add(CONST.getTytul(x, y, "Uwagi", Color.RED));
	}
}
