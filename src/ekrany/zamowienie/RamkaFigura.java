package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import dane.CzescKontolki;
import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.Zamowienie;
import modules.czesci.Czesc;

public class RamkaFigura
{

	public static void ramkaFigura(Graphics g, Zamowienie panel)
	{
		// ramka figura
		int x = 520;
		int y = 120;
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 250, 350);

		panel.add(CONST.getTytul(x, y, "Wymiar(cm)", Color.WHITE));

		_x = x + 25;
		_y = y + 150;
		last_kat = 0;
		
		rysujFigury(g, panel);

	}

	private static int last_kat;
	private static int _x;
	private static int _y;
	private static int skala = 4;

	private static void rysujFigury(Graphics g, Zamowienie panel)
	{

		if (panel.figura != null)
		{
			int index = 0;
			for (CzescKontolki czesc_text : ZamowienieDane.czesc_kontrolki)
			{
				Czesc czesc = new Czesc();
				czesc.setDlugosc(Integer.parseInt(czesc_text.bok.getText()));
				czesc.setKat(Integer.parseInt(czesc_text.kat.getText()));
				czesc.setTyp(czesc_text.typ.getText());

				if (czesc_text.bok.hasFocus() || czesc_text.kat.hasFocus())
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
						if (index > 0)
						{
							rysujLinie(g, czesc, last_kat);
						}
						else
						{
							rysujLinie(g, czesc, last_kat);
						}

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
				index++;

			}
		}
	}

	private static void rysujOkregi(Graphics g, Czesc c, int poprz_kat)
	{

		int rozmiar = (int) ((c.getDlugosc() * 360) / (c.getKat() * Math.PI)) / skala;// (int)(2*(c.getDlugosc()*180)/(c.getKat()*Math.PI));
		g.drawOval(_x, _y, 5, 5);

		Point p = new Point(_x, _y);

		int x2 = p.x + rozmiar / 2;
		int y2 = p.y + rozmiar / 2;
		double[] pt = { x2, p.y };
		AffineTransform.getRotateInstance(Math.toRadians((90 - poprz_kat) * 1), x2, y2).transform(pt, 0, pt, 0, 1);

		Double newX = pt[0];
		Double newY = pt[1];

		pt[0] = p.x; // pocz¹tkowa pozycja X
		pt[1] = p.y; // pocz¹tkowa pozycja Y

		p.x = p.x - Math.abs(newX.intValue() - p.x);
		p.y = p.y - Math.abs(newY.intValue() - p.y);

		x2 = p.x + rozmiar / 2; // œrodek ³uku
		y2 = p.y + rozmiar / 2; // œrodek ³uku

		// liczenie pozycji k¹ta koñcowego
		AffineTransform.getRotateInstance(Math.toRadians(-c.getKat() * 1), x2, y2).transform(pt, 0, pt, 0, 1);
		int x_koniec = (int) pt[0];
		int y_koniec = (int) pt[1];

		g.drawArc(p.x, p.y, rozmiar, rozmiar, (poprz_kat), (c.getKat()));

		g.fillOval(x_koniec, y_koniec, 10, 10);

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

		int x = (int) (Math.cos(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		int y = (int) (Math.sin(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		last_kat = c.getKat() + poprz_kat;

		g.drawLine(_x, _y, x + _x, y + _y);
		g.fillOval(x + _x - 3, y + _y - 3, 6, 6);
		_x += x;
		_y += y;
	}
}
