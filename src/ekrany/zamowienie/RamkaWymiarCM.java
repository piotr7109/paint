package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dodatki.CONST;

public class RamkaWymiarCM
{

	private static JLabel max_wysokosc;
	private static JLabel max_dlugosc;
	private static JLabel calk_dlugosc;
	private static JLabel ciezar;
	private static JLabel calk_ciezar;

	public static void ramkaWymiarCM(Graphics g, JPanel panel)
	{
		// ramak wymiar cm
		int x = 780;
		int y = 690;
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 210, 100);

		panel.add(CONST.getTytul(x, y, "Wymiar(cm) / Ciê¿ar(kg)", Color.RED));

		panel.add(CONST.getTytul(x, y + 15, "Max wysokoœæ:", Color.PINK));
		panel.add(CONST.getTytul(x, y + 30, "Max d³ugoœæ:", Color.PINK));
		panel.add(CONST.getTytul(x, y + 45, "Ca³k. d³ugoœæ:", Color.PINK));
		panel.add(CONST.getTytul(x, y + 60, "Ciê¿ar:", Color.PINK));
		panel.add(CONST.getTytul(x, y + 75, "Ca³k. ciê¿ar:", Color.PINK));

		max_wysokosc = CONST.getTytul(x + 175, y + 15, ".", Color.YELLOW);
		max_dlugosc = CONST.getTytul(x + 175, y + 30, ".", Color.YELLOW);
		calk_dlugosc = CONST.getTytul(x + 175, y + 45, ".", Color.YELLOW);
		ciezar = CONST.getTytul(x + 175, y + 60, ".", Color.YELLOW);
		calk_ciezar = CONST.getTytul(x + 175, y + 75, ".", Color.YELLOW);

		panel.add(max_wysokosc);
		panel.add(max_dlugosc);
		panel.add(calk_dlugosc);
		panel.add(ciezar);
		panel.add(calk_ciezar);
	}
}
