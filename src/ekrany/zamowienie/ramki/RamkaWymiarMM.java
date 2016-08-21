package ekrany.zamowienie.ramki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JTextField;

import dane.FiguraKontrolki;
import dane.ZamowienieDane;
import dodatki.Tools;
import ekrany.Zamowienie;

public class RamkaWymiarMM
{

	public RamkaWymiarMM(Zamowienie panel)
	{
		initKontrolki(panel);
	}
	
	protected void initKontrolki(Zamowienie panel)
	{
		int x = 10;
		int y = 120;
		
		panel.add(Tools.getTytul(rescale(x), rescale(y), "Wymiar(mm)", Color.PINK, Tools.scale));

		String elem[] = { "Lp", "Pozcja", "Sztuk", "Średnica", "Figura", "Il. pac.", "Maszyna", "Ma??", "Sworzeń" };
		int i = 0;
		for (String _elem : elem)
		{
			JLabel element = new JLabel(_elem);
			element.setSize(rescale(50), rescale(20));
			element.setLocation(rescale(x + 5 + i * 51), rescale(y + 20));
			element.setForeground(Color.RED);
			element.setFont(new Font("", 0, rescale(12)));
			i++;
			panel.add(element);

		}
	}
	
	public static void ramkaWymiarMM(Graphics g)
	{
		int x = 10;
		int y = 120;
		g.setColor(Color.BLUE);
		g.drawRect(rescale(x), rescale(y), rescale(500), rescale(300));
	}

	public static void przewinElementy(Zamowienie panel, JTextField txt)
	{
		ZamowienieDane.f_kontrolki.size();
		int size_y = ZamowienieDane.f_kontrolki.get(0).kontrolki.length;
		int size_x = ZamowienieDane.f_kontrolki.size();
		if (txt.getY() > rescale(385))
		{
			for (int i = 0; i < size_x; i++)
			{
				for (int j = 0; j < size_y; j++)
				{
					JTextField text = ZamowienieDane.f_kontrolki.get(i).kontrolki[j];
					text.setLocation(text.getX(), text.getY() - rescale(25));
					if (text.getY() > rescale(385) || text.getY() < rescale(160))
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
		else if (txt.getY() < rescale(160))
		{
			for (int i = 0; i < size_x; i++)
			{
				for (int j = 0; j < size_y; j++)
				{
					JTextField text = ZamowienieDane.f_kontrolki.get(i).kontrolki[j];
					text.setLocation(text.getX(), text.getY() + rescale(25));
					if (text.getY() > rescale(385) || text.getY() < rescale(160))
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

	private static int rescale(int number)
	{
		return (int) (number * Tools.scale);
	}

	public static void rescale()
	{
		for (FiguraKontrolki fig_k : ZamowienieDane.f_kontrolki)
		{
			for (JTextField text : fig_k.kontrolki)
			{
				text.setFont(new Font("", 0, rescale(12)));
			}
		}
	}
}
