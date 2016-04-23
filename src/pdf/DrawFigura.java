package pdf;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dodatki.CONST;
import modules.czesci.Czesc;
import modules.figury.Figura;

public class DrawFigura
{
	public static final String RESULT = System.getProperty("user.home") + "/Desktop/java_pdf/png/";
	private static int width = 300;
	private static int height = 150;
	public static String rysuj(Figura figura)
	{
		String filename = "rbs" + (int) (Math.random() * 100000);
		try
		{

			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D ig2 = bi.createGraphics();
			ig2.setStroke(new BasicStroke(2.5f));
			skala = 0.5;
			_x = 100;
			_y = 50;
			last_kat = 0;

			while (rysujFigury(ig2, figura))
			{
				last_kat = 0;
				_x = 100;
				_y = 70;
				ig2.setColor(Color.white);
				ig2.fillRect(0, 0, width, height);
				ig2.setColor(Color.black);
				skala += 0.01;
			}
			
			ImageIO.write(bi, "PNG", new File("temp_img/"+filename + ".PNG"));
		}
		catch (IOException ie)
		{
			ie.printStackTrace();
		}
		return "temp_img/"+filename + ".PNG";
	}

	private static int last_kat;
	private static int _x, _y;
	private static double skala = 0.5;
	

	private static int rescale(int number)
	{
		return (int) (number * CONST.scale);
	}

	private static boolean rysujFigury(Graphics g, Figura fig)
	{

		boolean error = false;
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
			if (_x >= width || _y >= height || _x <= 0 || _y <= 0)
			{
				error = true;
				break;
			}

		}

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
		int x = (int) (Math.cos(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		int y = (int) (Math.sin(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		last_kat = c.getKat() + poprz_kat;

		g.drawLine(_x, _y, x + _x, y + _y);
		_x += x;
		_y += y;

	}
}
