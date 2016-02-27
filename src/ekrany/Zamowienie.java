package ekrany;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import modules.czesci.Czesc;
import modules.figury.Figura;
import modules.figury.FiguraFactory;
import modules.figury.FiguraLista;

public class Zamowienie extends JPanel
{
	private static final int WIDTH = CONST.WIDTH;
	private static final int HEIGHT = CONST.HEIGHT;

	private Figura figura;

	public Zamowienie()
	{
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

		ramkaDaneKlienta(g);
		ramkaWymiarMM(g);
		ramkaIlosc(g);
		ramkaFigura(g);
		ramkaCzesci(g);
		ramkaUwagi(g);
		ramkaWymiarCM(g);

	}

	private void ramkaDaneKlienta(Graphics g)
	{
		int x = 10;
		int y = 10;
		g.setColor(Color.GREEN);
		g.drawRect(x, y, WIDTH - 20, 100);

		String elem[] = { "KOD", "ODBIORCA", "BUDOWA", "OBIEKT", "ELEMENT", "TERM. DOST.", "RYSUNEK NR.", "KSIĘGOWANIE" };
		int i = 0;
		for (String _elem : elem)
		{
			JLabel element = new JLabel(_elem);
			element.setSize(200, 20);
			element.setLocation(x + 10, i * 20 + 15);
			element.setForeground(Color.RED);
			i++;
			if (i == 4)
			{
				x = 510;
				i = 0;
			}
			add(element);

		}
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
				dodajFigure();
				repaint();

			}
		});
	}

	private void dodajFigure()
	{
		int index = ZamowienieDane.f_kontrolki.size();
		int x = 20;
		int y = 160;
		int el_width = 30;

		JTextField pozycja = new JTextField("");
		JTextField ilosc_sztuk = new JTextField("");
		JTextField srednica = new JTextField("");
		JTextField fig = new JTextField("");
		JTextField kolor = new JTextField("");
		JTextField material = new JTextField("");
		// JTextField mat?? = new JTextField(fig_zam.mat??);
		JTextField sworzen = new JTextField("");

		pozycja.setBounds(x += 50, y, el_width, 20);
		ilosc_sztuk.setBounds(x += 50, y, el_width, 20);
		srednica.setBounds(x += 50, y, el_width, 20);
		fig.setBounds(x += 50, y, el_width, 20);
		kolor.setBounds(x += 50, y, el_width, 20);
		material.setBounds(x += 50, y, el_width, 20);
		// mat??.setBounds(x += 50, y, el_width, 20);
		x += 50;
		sworzen.setBounds(x += 50, y, el_width, 20);

		pozycja.setBackground(Color.BLACK);
		ilosc_sztuk.setBackground(Color.BLACK);
		srednica.setBackground(Color.BLACK);
		fig.setBackground(Color.BLACK);
		kolor.setBackground(Color.BLACK);
		material.setBackground(Color.BLACK);
		// mat??.setBackground(Color.BLACK);
		sworzen.setBackground(Color.BLACK);

		pozycja.setForeground(Color.WHITE);
		ilosc_sztuk.setForeground(Color.WHITE);
		srednica.setForeground(Color.WHITE);
		fig.setForeground(Color.WHITE);
		kolor.setForeground(Color.WHITE);
		material.setForeground(Color.WHITE);
		// mat??.setForeground(Color.WHITE);
		sworzen.setForeground(Color.WHITE);

		fig.addFocusListener(this.zmianaFigury(index));

		pozycja.addFocusListener(this.wyborFigury(index));
		ilosc_sztuk.addFocusListener(this.wyborFigury(index));
		srednica.addFocusListener(this.wyborFigury(index));
		fig.addFocusListener(this.wyborFigury(index));
		kolor.addFocusListener(this.wyborFigury(index));
		material.addFocusListener(this.wyborFigury(index));
		// mat??.addFocusListener(this.wyborFigury(index));
		sworzen.addFocusListener(this.wyborFigury(index));

		add(pozycja);
		add(ilosc_sztuk);
		add(srednica);
		add(fig);
		add(kolor);
		add(material);
		// add(mat??);
		add(sworzen);
		x = 20;
		y += 20;

		FiguraKontrolki f_kontrolki = new FiguraKontrolki();

		f_kontrolki.pozycja = pozycja;
		f_kontrolki.ilosc_sztuk = ilosc_sztuk;
		f_kontrolki.srednica = srednica;
		f_kontrolki.fig = fig;
		f_kontrolki.kolor = kolor;
		f_kontrolki.material = material;
		// f_kontrolki.ma?? = ma??;
		f_kontrolki.sworzen = sworzen;
		ZamowienieDane.f_kontrolki.add(f_kontrolki);
	}

	private FocusListener wyborFigury(final int index)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent e)
			{
			}

			@Override
			public void focusGained(FocusEvent e)
			{
				figura = ZamowienieDane.figury.get(index).figura;
				
				int x_bok = 840;
				int x_kat = 930;
				int y = 120;
				
				for(CzescKontolki c_kontrolki: ZamowienieDane.czesc_kontrolki )
				{
					remove(c_kontrolki.bok);
					remove(c_kontrolki.kat);
				}
				
				ZamowienieDane.czesc_kontrolki.clear();
				if (figura != null)
				{
					System.out.println("Czesi:"+ figura.getCzesci().size()+" "+figura.getId());
					for (Czesc czesc : figura.getCzesci())
					{
						y += 25;
						JTextField bok_text = new JTextField(czesc.getDlugosc()+"");
						JTextField kat_text = new JTextField(czesc.getKat()+"");

						bok_text.setBounds(x_bok, y, 50, 20);
						kat_text.setBounds(x_kat, y, 50, 20);
						
						add(bok_text);
						add(kat_text);
						
						CzescKontolki c_kontrolka = new CzescKontolki();
						c_kontrolka.bok = bok_text;
						c_kontrolka.kat = kat_text;
						
						ZamowienieDane.czesc_kontrolki.add(c_kontrolka);

					}

				}
				
				repaint();

			}
		};
	}

	private FocusListener zmianaFigury(final int index)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent arg0)
			{
				FiguraKontrolki f_kontrolki = ZamowienieDane.f_kontrolki.get(index);
				FiguraZamowienie f_zamowienie = ZamowienieDane.figury.get(index);

				FiguraFactory f_factory = new FiguraFactory();
				Figura figura = null;
				if(!f_kontrolki.fig.getText().equals(""))
				{
					figura = f_factory.getFiguraByKod(Integer.parseInt(f_kontrolki.fig.getText()));
				}
				
				if (figura != null)
				{
					figura.getCzesci();
					f_zamowienie.figura = figura;
				}
				else
				{
					// ERROR nie ma w bazie
				}

			}

			@Override
			public void focusGained(FocusEvent arg0)
			{
			}
		};
	}

	private void ramkaWymiarMM(Graphics g)
	{
		int x = 10;
		int y = 120;
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 500, 300);

		add(CONST.getTytul(x, y, "Wymiar(mm)", Color.PINK));

		String elem[] = { "Lp", "Pozcja", "Sztuk", "Średnica", "Figura", "Kolor", "Materiał", "Ma??", "Sworzeń" };
		int i = 0;
		for (String _elem : elem)
		{
			JLabel element = new JLabel(_elem);
			element.setSize(50, 20);
			element.setLocation(x + 5 + i * 51, y + 20);
			element.setForeground(Color.RED);
			i++;
			add(element);

		}
		x = 10;
		y = 120;


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

	private void ramkaIlosc(Graphics g)
	{
		int x = 10;
		int y = 430;
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 500, 50);
	}

	private void ramkaFigura(Graphics g)
	{
		// ramka figura
		int x = 520;
		int y = 120;
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 250, 350);

		add(CONST.getTytul(x, y, "Wymiar(cm)", Color.WHITE));
	}

	private void ramkaCzesci(Graphics g)
	{
		// ramka części
		int x = 780;
		int y = 120;
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 210, 450);

		JLabel bok = new JLabel("Bok");
		JLabel kat = new JLabel("Kąt");

		int x_bok = x + 60;
		int x_kat = x + 150;
		bok.setBounds(x_bok, y + 5, 50, 20);
		kat.setBounds(x_kat, y + 5, 50, 20);
		bok.setForeground(Color.PINK);
		kat.setForeground(Color.PINK);
		add(bok);
		add(kat);

		////
		

	}

	private void ramkaUwagi(Graphics g)
	{
		// ramka uwagi
		int x = 780;
		int y = 580;
		g.setColor(Color.PINK);
		g.drawRect(x, y, 210, 100);

		add(CONST.getTytul(x, y, "Uwagi", Color.RED));
	}

	private JLabel max_wysokosc;
	private JLabel max_dlugosc;
	private JLabel calk_dlugosc;
	private JLabel ciezar;
	private JLabel calk_ciezar;

	private void ramkaWymiarCM(Graphics g)
	{
		// ramak wymiar cm
		int x = 780;
		int y = 690;
		g.setColor(Color.BLUE);
		g.drawRect(x, y, 210, 100);

		add(CONST.getTytul(x, y, "Wymiar(cm) / Ciężar(kg)", Color.RED));

		add(CONST.getTytul(x, y + 15, "Max wysokość:", Color.PINK));
		add(CONST.getTytul(x, y + 30, "Max długość:", Color.PINK));
		add(CONST.getTytul(x, y + 45, "Całk. długość:", Color.PINK));
		add(CONST.getTytul(x, y + 60, "Ciężar:", Color.PINK));
		add(CONST.getTytul(x, y + 75, "Całk. ciężar:", Color.PINK));

		max_wysokosc = CONST.getTytul(x + 175, y + 15, ".", Color.YELLOW);
		max_dlugosc = CONST.getTytul(x + 175, y + 30, ".", Color.YELLOW);
		calk_dlugosc = CONST.getTytul(x + 175, y + 45, ".", Color.YELLOW);
		ciezar = CONST.getTytul(x + 175, y + 60, ".", Color.YELLOW);
		calk_ciezar = CONST.getTytul(x + 175, y + 75, ".", Color.YELLOW);

		add(max_wysokosc);
		add(max_dlugosc);
		add(calk_dlugosc);
		add(ciezar);
		add(calk_ciezar);
	}
}
