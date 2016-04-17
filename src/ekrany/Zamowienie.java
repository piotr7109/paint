package ekrany;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dane.CzescKontolki;
import dane.FiguraKontrolki;
import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.zamowienie.DodawanieFigur;
import ekrany.zamowienie.db.DBConnector;
import ekrany.zamowienie.ramki.RamkaCzesci;
import ekrany.zamowienie.ramki.RamkaDaneKlienta;
import ekrany.zamowienie.ramki.RamkaFigura;
import ekrany.zamowienie.ramki.RamkaIlosc;
import ekrany.zamowienie.ramki.RamkaUwagi;
import ekrany.zamowienie.ramki.RamkaWymiarCM;
import ekrany.zamowienie.ramki.RamkaWymiarMM;
import modules.figury.Figura;

public class Zamowienie extends JPanel implements KeyListener
{
	/**
	 * 
	 */
	public static double scale = 1.3;
	private static final long serialVersionUID = 1651641468298004106L;
	private final int WIDTH = (int)(CONST.WIDTH*scale);
	private final int HEIGHT = (int)(CONST.HEIGHT*scale);
	

	public Figura figura;
	public JButton zapisz_dane = new JButton("Zapisz dane");

	public Zamowienie()
	{
		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		this.addKeyListener(this);
		init();

	}

	private void init()
	{
		resetDefaults();
		initZapiszButton();
		DodawanieFigur.setDefaultFigura();
		wczytajDane();

		RamkaWymiarCM.init(this);

		RamkaUwagi ramka_uwagi = new RamkaUwagi(this);
		RamkaDaneKlienta ramka_dane_klienta = new RamkaDaneKlienta(this);
	}

	protected void initZapiszButton()
	{
		zapisz_dane.setBounds(0, this.HEIGHT - 25, 150, 25);
		zapisz_dane.addActionListener(zapiszDane());
		add(zapisz_dane);
	}

	private ActionListener zapiszDane()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DBConnector.zapiszDane();

			}
		};
	}

	private void wczytajDane()
	{
		DBConnector.odczytajDane();
		int size = ZamowienieDane.figury.size();
		for (int i = 0; i < size; i++)
		{
			DodawanieFigur.dodajFigure(this, false);
		}
		if (ZamowienieDane.figury.size() == 0)
		{
			DodawanieFigur.dodajFigure(this, true);
		}
	}

	protected void resetDefaults()
	{
		ZamowienieDane.czesc_kontrolki = new ArrayList<CzescKontolki>();
		ZamowienieDane.czesc_kontrolki_figura = new ArrayList<CzescKontolki>();
		ZamowienieDane.f_kontrolki = new ArrayList<FiguraKontrolki>();
		ZamowienieDane.figury = new ArrayList<FiguraZamowienie>();

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
		RamkaCzesci.ramkaCzesci(g, this);
		RamkaFigura.ramkaFigura(g, this);

		RamkaUwagi.ramkaUwagi(g, this);
		RamkaWymiarCM.ramkaWymiarCM(g, this);

		if (tryb_rzeczywisty)
		{
			g.setColor(Color.GREEN);
		}
		else
		{
			g.setColor(Color.RED);
		}
		g.fillOval(WIDTH - 50, 0, 50, 50);
		
		RamkaWymiarMM.rescale(scale);
		

	}

	public int z_x = -1; // RamkaWymiarMM - pozycje
	public int z_y = 1;
	public int c_x = -1; // RamkaCzesci - części
	public int c_y = 0;
	public int f_x = 0; // RamkaFigura - części
	public String tryb = "figury"; // figury/czesci/czesci_figury/uwagi
	public boolean tryb_rzeczywisty = false; // programowa/rzeczywista
	public JTextField poprzedni_text_field = null;
	public FiguraZamowienie f_zamowienie_temp = null;

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (tryb.equals("figury"))
		{
			if (z_x >= 0)
			{
				switch (e.getKeyCode())
				{
					case 40: // DÓŁ
						if (z_x + 1 < ZamowienieDane.f_kontrolki.size())
						{
							z_x++;

						}
						else
						{
							DodawanieFigur.dodajFigure(this, true);
						}
						break;
					case 38: // GÓRA
						if (z_x > 0)
						{
							z_x--;
						}
						break;
					case 39: // PRAWO
					case 10: // ENTER
						if (z_y + 1 < 8)
						{
							z_y++;
						}
						else
						{
							if (z_x + 1 < ZamowienieDane.f_kontrolki.size())
							{
								z_x++;
							}
							else
							{
								DodawanieFigur.dodajFigure(this, true);
							}
							z_y = 1;
						}
						break;

					case 37: // LEWO
						if (z_y > 1)
						{
							z_y--;
						}
						else
						{
							if (z_x > 0)
							{
								z_x--;
								z_y = 7;
							}
						}
						break;
				}
				RamkaWymiarMM.przewinElementy(this, ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y]);
				ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
				if (e.getKeyCode() == 112) // F1
				{
					tryb = "czesci_figury";
					ZamowienieDane.czesc_kontrolki_figura.get(f_x).bok.grabFocus();

				}

			}
		}
		else if (tryb.equals("czesci"))
		{
			switch (e.getKeyCode())
			{
				case 40:
					if (c_x + 1 < ZamowienieDane.figury.get(z_x).figura.getCzesci().size())
					{
						c_x++;
					}
					else
					{
						RamkaCzesci.dodajCzesc(this, z_x);

					}

					break;
				case 38:
					if (c_x > 0)
					{
						c_x--;
					}
					break;
				case 39:
					if (c_y + 1 < 2)
					{
						c_y++;
					}
					break;

				case 37:
					if (c_y > 0)
					{
						c_y--;
					}
					break;
			}
			ZamowienieDane.czesc_kontrolki.get(c_x).kontrolki[c_y].grabFocus();
			if (e.getKeyCode() == 112) // F1
			{
				tryb = "figury";
				ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
			}
		}
		else if (tryb.equals("czesci_figury"))
		{
			switch (e.getKeyCode())
			{
				case 10:
				case 40:
					if (f_x + 1 < ZamowienieDane.figury.get(z_x).figura.getCzesci().size())
					{
						f_x++;
					}
					else
					{
						tryb = "figury";
						ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
					}
					break;
				case 38:
					if (f_x > 0)
					{
						f_x--;
					}

					break;
			}
			ZamowienieDane.czesc_kontrolki_figura.get(f_x).bok.grabFocus();
			if (e.getKeyCode() == 112) // F1
			{
				tryb = "czesci";
				ZamowienieDane.czesc_kontrolki.get(c_x).kontrolki[c_y].grabFocus();

			}
		}
		if (tryb.equals("uwagi"))
		{
			if (e.getKeyCode() == 120) // F9
			{
				tryb = "figury";
				ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
			}
		}
		else
		{

			switch (e.getKeyCode())
			{
				case 113: // F2
					if (tryb_rzeczywisty)
					{
						tryb_rzeczywisty = false;
						RamkaWymiarCM.ustawKontrolki(z_x, this);
						repaint();
					}
					else
					{
						tryb_rzeczywisty = true;
						RamkaWymiarCM.ustawKontrolki(z_x, this);
						repaint();
					}
					break;

				case 82: // r
					DodawanieFigur.usunFigure(z_x, this);
					break;

				case 117: // F6 - zapisz w pamięci
					f_zamowienie_temp = ZamowienieDane.figury.get(z_x);
					break;

				case 118: // F7 - skopiuj
					ZamowienieDane.figury.set(z_x, new FiguraZamowienie(f_zamowienie_temp));
					ZamowienieDane.f_kontrolki.get(z_x).updateFromFiguraZamowienie();

					break;
				case 120: // F9
					tryb = "uwagi";
					RamkaUwagi.uwagi.grabFocus();
					break;
			}

			// czyszczenie pól jak Excelu - wchodząc w pole i wpisując kasujesz
			// poprzednią zawartość
			if (jestKodemCyfry(e.getKeyCode()))
			{
				JTextField current_text_field = (JTextField) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
				if (poprzedni_text_field != current_text_field)
				{
					poprzedni_text_field = current_text_field;
					current_text_field.setText("");
				}

			}
		}
		System.out.println("KEY: " + e.getKeyCode());

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	private boolean jestKodemCyfry(int kod)
	{
		if ((kod >= 96 && kod <= 105) || (kod >= 48 && kod <= 57))
		{
			return true;
		}
		return false;
	}
	
	public static int rescale(double number)
	{
		return (int)(scale*number);
	}

}
