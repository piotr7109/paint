package ekrany;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import dodatki.CONST;
import modules.czesci.Czesc;
import modules.czesci.CzescLista;
import modules.figury.Figura;
import modules.figury.FiguraFactory;

public class WyswietlFigure extends JPanel
{
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 800;

	private Figura figura;
	private int _x;
	private int _y;
	private int last_kat;

	public JButton odswiez = new JButton("OdŸwie¿");

	public WyswietlFigure()
	{
		figura = this.getFigura();

		kontrolki();
	}

	private void kontrolki()
	{
		odswiez.setSize(CONST.btn_size);

		odswiez.setLocation(0, 0);

		add(odswiez);
	}

	private Figura getFigura()
	{
		Figura fig = new Figura();
		FiguraFactory f_factory = new FiguraFactory();
		f_factory.setId(fig.getLastId());
		fig = (Figura) f_factory.getObject();

		CzescLista cz_lista = new CzescLista();
		ArrayList<Object> czesci = (ArrayList<Object>) cz_lista.getCzesci(fig.getId());
		int czesc_size = czesci.size();
		for (int i = 0; i < czesc_size; i++)
		{
			Czesc c = (Czesc) czesci.get(i);
			fig.addCzesc(c);
		}

		return fig;
	}

	protected void paintComponent(Graphics g)
	{
		_x = 200;
		_y = 400;
		last_kat = 0;
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.BLACK);
		rysujFigury(g);

	}

	private void rysujFigury(Graphics g)
	{
		int index = 0;
		for (Czesc czesc : figura.getCzesci())
		{
			switch (czesc.getTyp())
			{
				case "linia":
					if (index > 0)
					{
						rysujLinie(g, czesc, last_kat + 180);
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

	private void rysujOkregi(Graphics g, Czesc c, int poprz_kat)
	{

		int rozmiar = c.getDlugosc();// (int)(2*(c.getDlugosc()*180)/(c.getKat()*Math.PI));
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

		if(last_kat<0)
		{
			while(last_kat < -360)
				last_kat+=360;
		}
		else
		{
			while(last_kat > 360)
				last_kat-=360;
		}
		System.out.println(last_kat);
		last_kat = -(poprz_kat + c.getKat()+90);
		
		_x = x_koniec;
		_y = y_koniec;
	}

	private void rysujLinie(Graphics g, Czesc c, int poprz_kat)
	{
		
		int x = (int) (Math.cos(radians(c.getKat() + poprz_kat)) * c.getDlugosc());
		int y = (int) (Math.sin(radians(c.getKat() + poprz_kat)) * c.getDlugosc());
		last_kat = c.getKat() + poprz_kat;

		g.drawLine(_x, _y, x + _x, y + _y);
		g.fillOval(x + _x - 3, y + _y - 3, 6, 6);
		_x += x;
		_y += y;
	}

	private double radians(int kat)
	{

		return (kat * Math.PI / 180);
	}

}
