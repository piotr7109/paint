package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RamkaDaneKlienta
{
	public static void ramkaDaneKlienta(Graphics g, JPanel panel)
	{
		int x = 10;
		int y = 10;
		g.setColor(Color.GREEN);
		g.drawRect(x, y, panel.WIDTH - 20, 100);

		String elem[] = { "KOD", "ODBIORCA", "BUDOWA", "OBIEKT", "ELEMENT", "TERM. DOST.", "RYSUNEK NR.", "KSIÊGOWANIE" };
		int i = 0;
		for (String _elem : elem)
		{
			JLabel element = new JLabel(_elem);
			element.setSize(200, 20);
			element.setLocation(x + 10, i * 20 + 15);
			element.setForeground(Color.RED);
			i++;
			if (i == 4)
			{
				x = 510;
				i = 0;
			}
			panel.add(element);

		}
	}
}
