package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JTextField;

import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.Zamowienie;

public class RamkaWymiarMM
{
	public static void ramkaWymiarMM(Graphics g, Zamowienie panel)
	{
		int x = 10;
		int y = 120;
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 500, 300);

		panel.add(CONST.getTytul(x, y, "Wymiar(mm)", Color.PINK));

		String elem[] = { "Lp", "Pozcja", "Sztuk", "Œrednica", "Figura", "Kolor", "Materia³", "Ma??", "Sworzeñ" };
		int i = 0;
		for (String _elem : elem)
		{
			JLabel element = new JLabel(_elem);
			element.setSize(50, 20);
			element.setLocation(x + 5 + i * 51, y + 20);
			element.setForeground(Color.RED);
			i++;
			panel.add(element);

		}
		x = 10;
		y = 120;

	}

	public static void przewinElementy(Zamowienie panel, JTextField txt)
	{
		ZamowienieDane.f_kontrolki.size();
		int size_y = ZamowienieDane.f_kontrolki.get(0).kontrolki.length;
		int size_x = ZamowienieDane.f_kontrolki.size();
		if (txt.getY() > 385)
		{
			for (int i = 0; i < size_x; i++)
			{
				for (int j = 0; j < size_y; j++)
				{
					JTextField text = ZamowienieDane.f_kontrolki.get(i).kontrolki[j];
					text.setLocation(text.getX(), text.getY() - 25);
					if ( text.getY() > 385 || text.getY() < 160)
					{
						text.setVisible(false);
					}
					else
					{
						text.setVisible(true);
					}

					
				}
			}
		}
		else if (txt.getY() < 160)
		{
			for (int i = 0; i < size_x; i++)
			{
				for (int j = 0; j < size_y; j++)
				{
					JTextField text = ZamowienieDane.f_kontrolki.get(i).kontrolki[j];
					text.setLocation(text.getX(), text.getY() + 25);
					if ( text.getY() > 385 || text.getY() < 160)
					{
						text.setVisible(false);
					}
					else
					{
						text.setVisible(true);
					}
				}
			}
		}
	}
}
