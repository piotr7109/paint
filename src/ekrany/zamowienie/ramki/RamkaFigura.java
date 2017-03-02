package ekrany.zamowienie.ramki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import dane.CzescKontolki;
import dane.ZamowienieDane;
import dodatki.Tools;
import ekrany.Zamowienie;
import modules.czesci.Czesc;

public class RamkaFigura
{
	private static int width = rescale(250);
	private static int height = rescale(350);
	private static int x, y;

	public RamkaFigura(Zamowienie panel)
	{
		x = 520;
		y = 400;
		panel.add(Tools.getTytul(rescale(x), rescale(y), "Wymiar(cm)", Color.WHITE, Tools.scale));
	}

	public static void ramkaFigura(Graphics g, Zamowienie panel)
	{
		// ramka figura
		width = rescale(250);
		height = rescale(350);
		x = 520;
		y = 400;
		g.setColor(Color.BLUE);
		g.drawRect(rescale(x), rescale(y), width, height);

		_x = x + 15;
		_y = y + 75;
		last_kat = 0;

		rysujFigury(g, panel);

	}

	private static int last_kat;
	private static int _x, _y;
	private static double skala = 0.5;

	public static void skalaReset()
	{
		skala = 0.5;
	}

	private static void rysujFigury(Graphics g, Zamowienie panel)
	{
		if (panel.figura != null)
		{
			int index = 0;
			boolean error = false;
			for (CzescKontolki czesc_text : ZamowienieDane.czesc_kontrolki)
			{
				Czesc czesc = new Czesc();
				czesc.setDlugosc(Integer.parseInt(czesc_text.bok.getText()));
				czesc.setKat(Integer.parseInt(czesc_text.kat.getText()));
				czesc.setTyp(czesc_text.typ.getText());

				CzescKontolki czesc_figury = ZamowienieDane.czesc_kontrolki_figura.get(index);
				if (czesc_text.bok.hasFocus() || czesc_text.kat.hasFocus() || czesc_figury.bok.hasFocus())
				{
					g.setColor(Color.RED);
				}
				else
				{
					g.setColor(Color.YELLOW);
				}

				switch (czesc.getTyp())
				{
					case "linia":

						rysujLinie(g, czesc, last_kat);

						break;

					case "okrag":
						if (index > 0)
						{
							rysujOkregi(g, czesc, -(90 + last_kat));
						}
						else
						{
							rysujOkregi(g, czesc, last_kat);
						}
						break;
				}
				if (rescale(_x) > width + rescale(x) || rescale(_y) > height + rescale(y) || rescale(_x) < rescale(x) || rescale(_y) < rescale(y))
				{
					error = true;
					break;
				}
			}
			if (error)
			{
				skala += 0.4;
				panel.repaint();
			}
		}
	}

	private static void rysujOkregi(Graphics g, Czesc c, int poprz_kat)
	{

		int rozmiar = (int) (((c.getDlugosc() * 360) / (c.getKat() * Math.PI)) / skala);// (int)(2*(c.getDlugosc()*180)/(c.getKat()*Math.PI));

		Point p = new Point(_x, _y);

		int x2 = p.x + rozmiar / 2;
		int y2 = p.y + rozmiar / 2;
		double[] pt = { x2, p.y };
		AffineTransform.getRotateInstance(Math.toRadians((90 - poprz_kat) * 1), x2, y2).transform(pt, 0, pt, 0, 1);

		Double newX = pt[0];
		Double newY = pt[1];

		pt[0] = p.x; // pocz�tkowa pozycja X
		pt[1] = p.y; // pocz�tkowa pozycja Y

		p.x = p.x - Math.abs(newX.intValue() - p.x);
		p.y = p.y - Math.abs(newY.intValue() - p.y);

		x2 = p.x + rozmiar / 2; // �rodek �uku
		y2 = p.y + rozmiar / 2; // �rodek �uku

		// liczenie pozycji k�ta ko�cowego
		AffineTransform.getRotateInstance(Math.toRadians(-c.getKat() * 1), x2, y2).transform(pt, 0, pt, 0, 1);
		int x_koniec = (int) pt[0];
		int y_koniec = (int) pt[1];

		g.drawArc(rescale(p.x), rescale(p.y), rescale(rozmiar), rescale(rozmiar), (poprz_kat), (c.getKat()));

		if (last_kat < 0)
		{
			while (last_kat < -360)
				last_kat += 360;
		}
		else
		{
			while (last_kat > 360)
				last_kat -= 360;
		}
		last_kat = -(poprz_kat + c.getKat() + 90);

		_x = x_koniec;
		_y = y_koniec;

	}

	private static void rysujLinie(Graphics g, Czesc c, int poprz_kat)
	{
		int x = (int) (Math.cos(Tools.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		int y = (int) (Math.sin(Tools.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		last_kat = c.getKat() + poprz_kat;

		g.drawLine(rescale(_x), rescale(_y), rescale(x + _x), rescale(y + _y));
		g.fillOval(rescale(_x) - 1, rescale(y + _y) - 1, 2, 2);

		g.setFont(new Font("", 0, rescale(8)));

		double x_c_r = Math.cos(Tools.radians(c.getKat() + poprz_kat)) * c.getDlugosc();
		double y_c_r = Math.sin(Tools.radians(c.getKat() + poprz_kat)) * c.getDlugosc();
		g.drawString((int) Math.abs(x_c_r) + "", (rescale(_x) + rescale(x + _x)) / 2, rescale(y + _y));
		g.drawString((int) Math.abs(y_c_r) + "", rescale(_x), (rescale(_y) + rescale(y + _y)) / 2);

		_x += x;
		_y += y;

	}

	private static int rescale(int number)
	{
		return (int) (number * Tools.scale);
	}

	public static void rescale(double scale)
	{
		for (CzescKontolki czesc : ZamowienieDane.czesc_kontrolki_figura)
		{
			czesc.bok.setFont(new Font("", 0, rescale(12)));
		}
	}
}
