package pdf;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
import modules.figury.FiguraFactory;

public class DrawFigura
{
	public static final String RESULT = System.getProperty("user.home") + "/Desktop/java_pdf/png/";
	private static int width = 300;
	private static int height = 100;

	public static String rysuj(Figura figura)
	{
		String filename = "rbs" + (int) (Math.random() * 100000);
		FiguraFactory f_factory = new FiguraFactory();
		Figura figura_atrapa = f_factory.getFiguraByKod(figura.getKod());
		figura_atrapa.setCzesciAtrapy();
		try
		{

			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D ig2 = bi.createGraphics();
			ig2.setStroke(new BasicStroke(3f));
			ig2.setFont(new Font("", 0, 20));
			ig2.setColor(Color.WHITE);
			ig2.fillRect(0, 0, width, height);
			ig2.setColor(Color.BLACK);
			_x = (int) (figura_atrapa.start_x/skala);
			_y = (int) (figura_atrapa.start_y/skala);
			last_kat = 0;

			rysujFigury(ig2, figura,figura_atrapa);

			ImageIO.write(bi, "PNG", new File("temp_img/" + filename + ".PNG"));
		}
		catch (IOException ie)
		{
			ie.printStackTrace();
		}
		return "temp_img/" + filename + ".PNG";
	}

	private static int last_kat;
	private static int _x, _y;
	private static double skala = 0.5;

	private static int rescale(int number)
	{
		return (int) (number * CONST.scale);
	}

	private static void rysujFigury(Graphics g, Figura fig, Figura figura_atrapa)
	{

		int index = 0;
		
		
		int fig_atr_size = figura_atrapa.getCzesciAtrapy().size();
		for (int i =0; i< fig_atr_size; i++)
		{
			Czesc czesc = fig.getCzesci().get(i);
			Czesc czesc_atrapa = figura_atrapa.getCzesciAtrapy().get(i);
			switch (czesc.getTyp())
			{
				case "linia":

					rysujLinie(g, czesc_atrapa, last_kat, czesc);

					break;

				case "okrag":
					if (index > 0)
					{
						rysujOkregi(g, czesc_atrapa, -(90 + last_kat), czesc);
					}
					else
					{
						rysujOkregi(g, czesc_atrapa, last_kat, czesc);
					}
					break;
			}

		}
	}

	private static void rysujOkregi(Graphics g, Czesc c, int poprz_kat, Czesc c_opis)
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

	private static void rysujLinie(Graphics g, Czesc c, int poprz_kat, Czesc c_opis)
	{
		int x = (int) (Math.cos(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		int y = (int) (Math.sin(CONST.radians(c.getKat() + poprz_kat)) * c.getDlugosc() / skala);
		last_kat = c.getKat() + poprz_kat;

		g.drawLine(_x, _y, x + _x, y + _y);
		g.setColor(Color.WHITE);
		g.fillRect((x + 2 * _x) / 2, (y + 2 * _y) / 2 - 12, 30, 13);
		g.setColor(Color.BLACK);
		g.drawString(c_opis.getDlugosc() + "", (x + 2 * _x) / 2, (y + 2 * _y) / 2);

		_x += x;
		_y += y;

	}
}
