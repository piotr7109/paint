package ekrany.zamowienie.ramki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.AffineTransform;

import javax.swing.JTextField;

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
		y = 120;
		panel.add(Tools.getTytul(rescale(x), rescale(y), "Wymiar(cm)", Color.WHITE, Tools.scale));
	}

	public static void ramkaFigura(Graphics g, Zamowienie panel)
	{
		// ramka figura
		width = rescale(250);
		height = rescale(350);
		x = 520;
		y = 120;
		g.setColor(Color.BLUE);
		g.drawRect(rescale(x), rescale(y), width, height);

		_x = x + 50;
		_y = y + 150;
		last_kat = 0;

		rysujFigury(g, panel);

	}

	public static void rysujKontrolki(Zamowienie panel, int index)
	{
		int x_bok = 450;
		int y = 520;

		for (CzescKontolki c_kontrolki : ZamowienieDane.czesc_kontrolki_figura)
		{
			panel.remove(c_kontrolki.bok);
		}

		ZamowienieDane.czesc_kontrolki_figura.clear();
		panel.f_x = 0;
		if (panel.figura != null)
		{
			int index_czesc = 0;
			for (Czesc czesc : panel.figura.getCzesci())
			{
				y += 25;
				JTextField bok_text = new JTextField(czesc.getDlugosc() + "");

				bok_text.setBounds(x_bok, y, 50, 20);

				bok_text.addFocusListener(zapiszBok(index, index_czesc, panel, bok_text));

				bok_text.addKeyListener(panel);

				Tools.setKoloryNieaktywny2(bok_text);

				panel.add(bok_text);

				CzescKontolki c_kontrolka = new CzescKontolki();
				c_kontrolka.bok = bok_text;
				c_kontrolka.set();

				ZamowienieDane.czesc_kontrolki_figura.add(c_kontrolka);
				index_czesc++;
			}

		}
		rescale(Tools.scale);
		panel.repaint();
	}

	private static FocusListener zapiszBok(final int index, final int index_czesc, final Zamowienie panel, final JTextField text_field)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent arg0)
			{
				if (text_field.getText().equals(""))
				{
					text_field.setText("0");
				}
				Tools.setKoloryNieaktywny2(text_field);
				Czesc czesc = ZamowienieDane.figury.get(index).figura.getCzesci().get(index_czesc);
				czesc.setDlugosc(Integer.parseInt(ZamowienieDane.czesc_kontrolki_figura.get(index_czesc).bok.getText()));
				panel.repaint();
			}

			@Override
			public void focusGained(FocusEvent arg0)
			{
				Tools.setKoloryAktywny2(text_field);
				panel.repaint();

			}
		};
	}

	private static int last_kat;
	private static int _x, _y;
	private static double skala = 1;

	public static void skalaReset()
	{
		skala = 1;
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
				int start_x = _x;
				int start_y = _y;

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
				else
				{
					ZamowienieDane.czesc_kontrolki_figura.get(index).bok.setLocation(rescale((start_x + _x) / 2), rescale((start_y + _y) / 2));
					index++;
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
		g.drawString((int)Math.abs(x*skala) + "", (rescale(_x) + rescale(x + _x)) / 2, rescale(y + _y));
		g.drawString((int)Math.abs(y*skala) + "", rescale(_x), (rescale(_y) + rescale(y + _y)) / 2);
		
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
