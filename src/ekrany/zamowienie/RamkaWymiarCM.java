package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dane.CzescKontolki;
import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.Zamowienie;
import modules.czesci.Czesc;

public class RamkaWymiarCM
{

	public static JLabel max_wysokosc;
	public static JLabel max_dlugosc;
	public static JLabel calk_dlugosc;
	public static JLabel ciezar;
	public static JLabel calk_ciezar;

	public static void ramkaWymiarCM(Graphics g, Zamowienie panel)
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
		
		obliczEkstrema(panel);

	}

	public static void init(JPanel panel)
	{
		int x = 900;
		int y = 690;
		max_wysokosc = CONST.getTytul(x, y + 15, ".", Color.YELLOW);
		max_dlugosc = CONST.getTytul(x, y + 30, ".", Color.YELLOW);
		calk_dlugosc = CONST.getTytul(x, y + 45, ".", Color.YELLOW);
		ciezar = CONST.getTytul(x, y + 60, ".", Color.YELLOW);
		calk_ciezar = CONST.getTytul(x, y + 75, ".", Color.YELLOW);

		panel.add(max_wysokosc);
		panel.add(max_dlugosc);
		panel.add(calk_dlugosc);
		panel.add(ciezar);
		panel.add(calk_ciezar);

	}

	public static void ustawKontrolki(int index, Zamowienie panel)
	{
		double dlugosc = 0;
		double waga_jedn = ZamowienieDane.figury.get(index).waga;
		if (panel.tryb_rzeczywisty)
		{
			dlugosc = obliczDlugoscRzeczywista(index);
		}
		else
		{
			dlugosc = obliczDlugosc(index);
		}

		calk_dlugosc.setText(CONST.round(dlugosc, 3) + "");
		ciezar.setText(CONST.round((obliczWage(dlugosc, waga_jedn) * ZamowienieDane.figury.get(index).ilosc_sztuk), 3) + "");
		calk_ciezar.setText(CONST.round(obliczCalkCiezar(index, panel.tryb_rzeczywisty), 3) + "");

	}

	private static double obliczDlugoscRzeczywista(int index)
	{
		double dlugosc = 0;

		int size = ZamowienieDane.figury.get(index).figura.getCzesci().size();
		int sworzen = ZamowienieDane.figury.get(index).sworzen / 2;
		int srednica = ZamowienieDane.figury.get(index).srednica;

		ArrayList<Czesc> czesci = cloneCzesci(index);
		System.out.println("size:" + size);
		for (int i = 0; i < size - 1; i++)
		{
			Czesc czesc = czesci.get(i);
			Czesc czesc_n = czesci.get(i + 1);

			if (czesc.getTyp().equals("okrag") || czesc_n.getTyp().equals("okrag"))
			{
				dlugosc += czesc.getDlugosc();

			}
			else
			{
				int kat = czesc_n.getKat();
				kat = Math.abs(kat);

				System.out.println("KAT: " + kat);
				double a = czesc.getDlugosc();
				System.out.println("Wymiary: "+sworzen+" "+srednica);
				double b = 2 * Math.PI * ((sworzen + srednica)/10) * kat / 360/2;
				//int c = czesc_n.getDlugosc();
				dlugosc += (a - b);

			}
		}

		dlugosc += czesci.get(size - 1).getDlugosc();

		return dlugosc;
	}

	private static ArrayList<Czesc> cloneCzesci(int index)
	{
		ArrayList<Czesc> czesci = new ArrayList<Czesc>();
		int size = ZamowienieDane.figury.get(index).figura.getCzesci().size();

		for (int i = 0; i < size; i++)
		{
			Czesc c = new Czesc(ZamowienieDane.figury.get(index).figura.getCzesci().get(i));
			czesci.add(c);
		}

		return czesci;
	}

	private static double obliczCalkCiezar(int index, boolean rzeczywista)
	{
		double ciezar = 0;
		double dlugosc = 0;
		for (int i = 0; i <= index; i++)
		{
			if (rzeczywista)
			{
				dlugosc = obliczDlugoscRzeczywista(i);
			}
			else
			{

				dlugosc = obliczDlugosc(i);
			}
			double waga_jedn = ZamowienieDane.figury.get(i).waga;
			ciezar += obliczWage(dlugosc, waga_jedn) * ZamowienieDane.figury.get(i).ilosc_sztuk;
		}
		return ciezar;
	}

	private static double obliczWage(double dlugosc, double waga)
	{
		return (dlugosc * waga);
	}

	private static int obliczDlugosc(int index)
	{
		int dlugosc = 0;

		for (Czesc czesc : ZamowienieDane.figury.get(index).figura.getCzesci())
		{
			dlugosc += czesc.getDlugosc();
		}

		return dlugosc;
	}

	private static int last_kat;
	private static int _x;
	private static int _y;

	private static Point eks_x = new Point(); // ekstrema w poziomie - szerokoœæ
												// - x(max), y(min)
	private static Point eks_y = new Point(); // ekstrema w pionie - wysokoœæ -
												// x(max), y(min)

	private static void obliczEkstrema(Zamowienie panel)
	{
		last_kat = 0;
		_x = 520 + 25;
		_y = 120 + 150;
		eks_x.x = _x;
		eks_x.y = _x;
		eks_y.x = _y;
		eks_y.y = _y;

		if (panel.figura != null)
		{
			int index = 0;
			for (CzescKontolki czesc_text : ZamowienieDane.czesc_kontrolki)
			{
				Czesc czesc = new Czesc();
				czesc.setDlugosc(Integer.parseInt(czesc_text.bok.getText()));
				czesc.setKat(Integer.parseInt(czesc_text.kat.getText()));
				czesc.setTyp(czesc_text.typ.getText());

				switch (czesc.getTyp())
				{
					case "linia":

						rysujLinie(czesc, last_kat);

						break;

					case "okrag":
						if (index > 0)
						{
							rysujOkregi(czesc, -(90 + last_kat));
						}
						else
						{
							rysujOkregi(czesc, last_kat);
						}
						break;
				}

				index++;

			}
			max_dlugosc.setText(""+(eks_x.x - eks_x.y));
			max_wysokosc.setText(""+(eks_y.x - eks_y.y));
		}
	}

	private static void rysujOkregi(Czesc c, int poprz_kat)
	{

		int rozmiar = (int) (((c.getDlugosc() * 360) / (c.getKat() * Math.PI)));// (int)(2*(c.getDlugosc()*180)/(c.getKat()*Math.PI));

		if (_x + rozmiar > eks_x.x)
		{
			eks_x.x = _x;
		}
		if (_x + rozmiar < eks_x.y)
		{
			eks_x.y = _x;
		}
		if (_y + rozmiar > eks_y.x)
		{
			eks_y.x = _y;
		}
		if (_y + rozmiar < eks_y.y)
		{
			eks_y.y = _y;
		}
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

	private static void rysujLinie(Czesc c, int poprz_kat)
	{

		int x = (int) (Math.cos(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc());
		int y = (int) (Math.sin(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc());
		last_kat = c.getKat() + poprz_kat;
		_x += x;
		_y += y;
		if (_x > eks_x.x)
		{
			eks_x.x = _x;
		}
		if (_x < eks_x.y)
		{
			eks_x.y = _x;
		}
		if (_y > eks_y.x)
		{
			eks_y.x = _y;
		}
		if (_y < eks_y.y)
		{
			eks_y.y = _y;
		}
	}
}
