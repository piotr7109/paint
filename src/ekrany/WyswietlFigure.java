package ekrany;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dodatki.Tools;
import modules.czesci.Czesc;
import modules.czesci.CzescLista;
import modules.figury.Figura;
import modules.figury.FiguraFactory;
import modules.figury.FiguraLista;

public class WyswietlFigure extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = Tools.WIDTH;
	private static final int HEIGHT = Tools.HEIGHT;

	private Figura figura;
	private int _x;
	private int _y;
	private int last_kat;
	private ArrayList<Figura> lista_figur;

	private JComboBox<Figura> figury_combo = new JComboBox<Figura>();
	private JButton wczytaj = new JButton("Wczytaj");

	private ArrayList<JLabel> dlugosci_label = new ArrayList<JLabel>();
	private ArrayList<JLabel> katy_label = new ArrayList<JLabel>();

	public JButton odswiez = new JButton("Od�wie�");

	public WyswietlFigure()
	{
		this.setLayout(null);

		this.getFigury();
		kontrolki();

	}

	private void getFigury()
	{
		lista_figur = new ArrayList<Figura>();
		figury_combo.removeAllItems();
		ArrayList<Object> ob;

		FiguraLista f_lista = new FiguraLista();
		ob = f_lista.getList();
		int size = ob.size();

		for (int i = 0; i < size; i++)
		{
			Figura fig = (Figura) ob.get(i);
			lista_figur.add(fig);
			figury_combo.addItem(fig);
		}

	}

	private void kontrolki()
	{

		odswiez.setSize(Tools.btn_size);
		wczytaj.setSize(Tools.btn_size);
		figury_combo.setSize(Tools.btn_size);

		odswiez.setLocation(WIDTH - 100, 0);
		wczytaj.setLocation(100, 0);
		figury_combo.setLocation(0, 0);

		add(odswiez);
		add(wczytaj);
		add(figury_combo);

		wczytaj.addActionListener(this.getWczytajEvent());
		odswiez.addActionListener(getOdswiezEvent());

	}

	private ActionListener getWczytajEvent()
	{
		ActionListener a_listener = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Figura fig = (Figura) figury_combo.getSelectedItem();
				setFigura(fig.getId());
				repaint();

			}
		};
		return a_listener;
	}

	private ActionListener getOdswiezEvent()
	{
		ActionListener a_listener = new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				getFigury();
				repaint();

			}
		};
		return a_listener;
	}

	private void setFigura(int id_figury)
	{
		Figura fig = new Figura();
		FiguraFactory f_factory = new FiguraFactory();
		f_factory.setId(id_figury);
		if (fig.getLastId() > 0)
		{
			fig = (Figura) f_factory.getObject();

			CzescLista cz_lista = new CzescLista();
			ArrayList<Object> czesci = (ArrayList<Object>) cz_lista.getCzesci(fig.getId());
			int czesc_size = czesci.size();
			for (int i = 0; i < czesc_size; i++)
			{
				Czesc c = (Czesc) czesci.get(i);
				fig.addCzesc(c);
			}
		}

		this.figura = fig;
		this.listaCzesciRysuj();
	}

	private void listaCzesciRysuj()
	{
		wyczyscListeCzesci();
		int i = 0;
		for (Czesc czesc : figura.getCzesci())
		{
			JLabel dl = new JLabel(czesc.getDlugosc() + "");
			JLabel kat = new JLabel(czesc.getKat() + "");

			dl.setForeground(Color.GREEN);
			kat.setForeground(Color.GREEN);

			dl.setSize(25, 25);
			kat.setSize(25, 25);

			dl.setLocation(WIDTH - 50, i * 25 + 50);
			kat.setLocation(WIDTH - 25, i * 25 + 50);

			dlugosci_label.add(dl);
			katy_label.add(kat);

			add(dl);
			add(kat);
			i++;
		}
	}

	private void wyczyscListeCzesci()
	{
		int size = dlugosci_label.size();
		for (int i = 0; i < size; i++)
		{
			this.remove(dlugosci_label.get(i));
			this.remove(katy_label.get(i));
		}
		dlugosci_label.clear();
		katy_label.clear();
	}

	protected void paintComponent(Graphics g)
	{
		_x = 200;
		_y = 400;
		last_kat = 0;
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.YELLOW);
		rysujFigury(g);

	}

	private void rysujFigury(Graphics g)
	{
		if (figura != null)
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
	}

	private void rysujOkregi(Graphics g, Czesc c, int poprz_kat)
	{

		int rozmiar = (int) ((c.getDlugosc() * 360) / (c.getKat() * Math.PI));
		g.drawOval(_x, _y, 5, 5);

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

	private void rysujLinie(Graphics g, Czesc c, int poprz_kat)
	{

		int x = (int) (Math.cos(Tools.radians(c.getKat() + poprz_kat)) * c.getDlugosc());
		int y = (int) (Math.sin(Tools.radians(c.getKat() + poprz_kat)) * c.getDlugosc());
		last_kat = c.getKat() + poprz_kat;

		g.drawLine(_x, _y, x + _x, y + _y);
		g.fillOval(x + _x - 3, y + _y - 3, 6, 6);
		_x += x;
		_y += y;
	}

}
