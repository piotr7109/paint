package ekrany;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dane.CzescKontolki;
import dane.FiguraKontrolki;
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
import modules.czesci.Czesc;
import modules.figury.Figura;
import modules.figury.FiguraFactory;
import modules.figury.FiguraLista;

public class Zamowienie extends JPanel
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

	

	

	

	

	

	
}
