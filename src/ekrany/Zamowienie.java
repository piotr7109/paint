package ekrany;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.zamowienie.DodawanieFigur;
import ekrany.zamowienie.RamkaCzesci;
import ekrany.zamowienie.RamkaDaneKlienta;
import ekrany.zamowienie.RamkaFigura;
import ekrany.zamowienie.RamkaIlosc;
import ekrany.zamowienie.RamkaUwagi;
import ekrany.zamowienie.RamkaWymiarCM;
import ekrany.zamowienie.RamkaWymiarMM;
import modules.figury.Figura;
import modules.figury.FiguraFactory;
import modules.figury.FiguraLista;

public class Zamowienie extends JPanel implements KeyListener
{
	private static final int WIDTH = CONST.WIDTH;
	private static final int HEIGHT = CONST.HEIGHT;

	public Figura figura;
	private Zamowienie zamowienie;

	public Zamowienie()
	{
		zamowienie = this;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		getFigury();
		this.addKeyListener(this);
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.YELLOW);
		rysujRamki(g);
	}

	private void rysujRamki(Graphics g)
	{

		RamkaDaneKlienta.ramkaDaneKlienta(g, this);
		RamkaWymiarMM.ramkaWymiarMM(g, this);
		RamkaIlosc.ramkaIlosc(g, this);
		RamkaFigura.ramkaFigura(g, this);
		RamkaCzesci.ramkaCzesci(g, this);
		RamkaUwagi.ramkaUwagi(g, this);
		RamkaWymiarCM.ramkaWymiarCM(g, this);

	}

	private JComboBox<Figura> figury_combo = new JComboBox<Figura>();

	private void getFigury()
	{
		figury_combo.removeAllItems();
		ArrayList<Object> ob;

		FiguraLista f_lista = new FiguraLista();
		ob = f_lista.getList();
		int size = ob.size();

		for (int i = 0; i < size; i++)
		{
			Figura fig = (Figura) ob.get(i);
			figury_combo.addItem(fig);
		}

		figury_combo.setBounds(20, 120 + 265, 75, 25);
		add(figury_combo);
		JButton dodaj_figure = new JButton("Dodaj");

		dodaj_figure.setBounds(100, 120 + 265, 75, 25);
		add(dodaj_figure);

		dodaj_figure.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				FiguraZamowienie fig_zam = new FiguraZamowienie();
				fig_zam.figura = (Figura) figury_combo.getSelectedItem();
				ZamowienieDane.figury.add(fig_zam);
				DodawanieFigur.dodajFigure(zamowienie);
				repaint();

			}
		});
	}

	private ArrayList<FiguraZamowienie> getFigZam()
	{
		ArrayList<FiguraZamowienie> figs_zam = new ArrayList<FiguraZamowienie>();

		FiguraFactory f_factory;
		FiguraZamowienie fig_zam;

		fig_zam = new FiguraZamowienie();
		f_factory = new FiguraFactory();
		f_factory.setId(11);
		fig_zam.figura = (Figura) f_factory.getObject();
		figs_zam.add(fig_zam);

		fig_zam = new FiguraZamowienie();
		f_factory = new FiguraFactory();
		f_factory.setId(16);
		fig_zam.figura = (Figura) f_factory.getObject();
		figs_zam.add(fig_zam);

		return figs_zam;
	}

	public int z_x = -1;
	public int z_y = 0;

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (z_x >= 0)
		{
			switch (e.getKeyCode())
			{
				case 40:
					if (z_x + 1 < ZamowienieDane.f_kontrolki.size())
					{
						z_x++;
						ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
					}
					break;
				case 38:
					if (z_x > 0)
					{
						z_x--;
						ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
					}
					break;
				case 39:
					if (z_y + 1 < 8)
					{
						z_y++;
						ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
					}
					break;

				case 37:
					if (z_y > 0)
					{
						z_y--;
						ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
					}
					break;
			}
		}
		System.out.println(z_x + "  " + ZamowienieDane.f_kontrolki.size());
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

}
