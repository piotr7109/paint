package inne;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dane.CzescKontolki;
import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.Zamowienie;
import modules.czesci.Czesc;
import modules.figury.Figura;

public class WriteImageType
{
	public static final String RESULT = System.getProperty("user.home") + "/Desktop/java_pdf/png/";

	static public void main(String args[]) throws Exception
	{
		try
		{
			int width = 100, height = 50;
			long start = System.currentTimeMillis();
			// TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
			// into integer pixels
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D ig2 = bi.createGraphics();
			
			Figura figura = new Figura();

			Czesc c1 = new Czesc();
			c1.setDlugosc(90);
			c1.setKat(0);
			c1.setTyp("linia");
			figura.addCzesc(c1);

			Czesc c2 = new Czesc();
			c2.setDlugosc(90);
			c2.setKat(90);
			c2.setTyp("linia");
			figura.addCzesc(c2);

			Czesc c3 = new Czesc();
			c3.setDlugosc(180);
			c3.setKat(-45);
			c3.setTyp("linia");
			figura.addCzesc(c3);
			
			for (int i = 0; i < 500; i++)
			{
				skala = 1;
				_x = 30;
				_y = 30;
				last_kat = 0;
				

				while (rysujFigury(ig2, figura))
				{
					last_kat = 0;
					_x = 30;
					_y = 5;
					ig2.setColor(Color.white);
					ig2.fillRect(0, 0, width, height);
					ig2.setColor(Color.black);
					skala += 0.01;
				}

				ImageIO.write(bi, "PNG", new File(RESULT + "rbs" + Math.random() + ".PNG"));
			}

			long stop = System.currentTimeMillis();
			System.out.println("Czas wykonania:" + (stop - start));
			// http://www.postgresql.org/docs/7.4/static/jdbc-binary-data.html
		}
		catch (IOException ie)
		{
			ie.printStackTrace();
		}

	}

	private static int last_kat;
	private static int _x, _y;
	private static double skala = 1;
	private static int width = 100;
	private static int height = 50;

	private static int rescale(int number)
	{
		return (int) (number * CONST.scale);
	}

	private static boolean rysujFigury(Graphics g, Figura fig)
	{

		boolean error = false;
		// if (panel.figura != null)
		// {
		int index = 0;

		

		for (Czesc czesc : fig.getCzesci())
		{

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
			if (_x > width || _y > height || _x < 0 || _y < 0)
			{
				error = true;
				break;
			}

		}

		// }
		return error;
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
		int x = (int) (Math.cos(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		int y = (int) (Math.sin(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		last_kat = c.getKat() + poprz_kat;

		g.drawLine(_x, _y, x + _x, y + _y);
		_x += x;
		_y += y;

	}
}
