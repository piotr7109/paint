package ekrany.zamowienie.ramki;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dane.ZamowienieDane;
import ekrany.Zamowienie;

public class RamkaDaneKlienta
{
	private static JLabel kod, odbiorca, budowa, obiekt, element;
	private static ArrayList<JLabel> kontrolki = new ArrayList<JLabel>();
	private static double scale = Zamowienie.scale;

	public RamkaDaneKlienta(Zamowienie panel)
	{
		initKontrolki(panel);
	}

	protected void initKontrolki(Zamowienie panel)
	{
		int x = 210;
		int y = -5;
		Dimension text_size = new Dimension(200, 20);

		kod = new JLabel(getKodZamowienia());
		odbiorca = new JLabel(ZamowienieDane.odbiorca.getNazwa());
		budowa = new JLabel(ZamowienieDane.budowa.getNazwa());
		obiekt = new JLabel(ZamowienieDane.obiekt.getNazwa());
		element = new JLabel(ZamowienieDane.element.getNazwa());

		kod.setForeground(Color.WHITE);
		odbiorca.setForeground(Color.WHITE);
		budowa.setForeground(Color.WHITE);
		obiekt.setForeground(Color.WHITE);
		element.setForeground(Color.WHITE);

		kod.setBounds(x, y += 20, text_size.width, text_size.height);
		odbiorca.setBounds(x, y += 20, text_size.width, text_size.height);
		budowa.setBounds(x, y += 20, text_size.width, text_size.height);
		obiekt.setBounds(x, y += 20, text_size.width, text_size.height);

		element.setBounds(x += 500, y = 10, text_size.width, text_size.height);
		kontrolki.add(kod);
		kontrolki.add(odbiorca);
		kontrolki.add(budowa);
		kontrolki.add(obiekt);
		kontrolki.add(element);

		panel.add(kod);
		panel.add(odbiorca);
		panel.add(budowa);
		panel.add(obiekt);
		panel.add(element);
		
		rescale(scale);
	}

	protected String getKodZamowienia()
	{
		String kod = "";

		kod += transformToZeroNumber(ZamowienieDane.odbiorca.getKod());
		kod += transformToZeroNumber(ZamowienieDane.budowa.getKod());
		if (ZamowienieDane.obiekt.getKod() < 10)
		{
			kod += "0";
		}
		kod += ZamowienieDane.obiekt.getKod();

		kod += "." + transformToZeroNumber(ZamowienieDane.element.getKod());

		return kod;
	}

	private String transformToZeroNumber(int liczba)
	{
		String kod = "";

		if (liczba < 10)
		{
			kod += "00";
		}
		else if (liczba < 100)
		{
			kod += "0";
		}
		else
		{
			kod += ".";
		}
		kod += liczba;
		return kod;
	}

	public static void ramkaDaneKlienta(Graphics g, JPanel panel)
	{
		int x = 10;
		int y = 10;
		g.setColor(Color.GREEN);
		g.drawRect(x, y, ImageObserver.WIDTH - 20, 100);

		String elem[] = { "KOD", "ODBIORCA", "BUDOWA", "OBIEKT", "ELEMENT", "TERM. DOST.", "RYSUNEK NR.", "KSIÊGOWANIE" };
		int i = 0;
		for (String _elem : elem)
		{
			JLabel element = new JLabel(_elem);
			element.setSize(rescale(200), rescale(20));
			element.setLocation(rescale(x + 10), rescale(i * 20 + 15));
			element.setForeground(Color.RED);
			element.setFont(new Font("",0, rescale(12)));
			i++;
			if (i == 4)
			{
				x = 510;
				i = 0;
			}
			panel.add(element);

		}
	}

	private static int rescale(int number)
	{
		return (int) (number * scale);
	}

	// kod, odbiorca, budowa, obiekt, element;
	public static void rescale(double scale)
	{
		for (JLabel label : kontrolki)
		{
			label.setFont(new Font("",0, rescale(12)));
			label.setBounds(rescale(label.getX()), rescale(label.getY()), rescale(label.getWidth()), rescale(label.getHeight()));
		}
	}
}
