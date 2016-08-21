package ekrany;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import dane.*;
import dodatki.Keys;
import dodatki.Tools;
import ekrany.zamowienie.DodawanieFigur;
import ekrany.zamowienie.db.DBConnector;
import ekrany.zamowienie.ramki.*;
import modules.figury.Figura;

public class Zamowienie extends JPanel implements KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1651641468298004106L;
	private int WIDTH = (int) (Tools.WIDTH * Tools.scale);
	private int HEIGHT = (int) (Tools.HEIGHT * Tools.scale);

	public Figura figura;
	public JButton zapisz_dane = new JButton("Zapisz dane");

	private JFrame frame;

	public Zamowienie(JFrame frame)
	{

		this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		WIDTH = (int) (Tools.WIDTH * Tools.scale);
		HEIGHT = (int) (Tools.HEIGHT * Tools.scale);
		this.frame = frame;
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
		RamkaCzesci ramka_czesci = new RamkaCzesci(this);
		RamkaWymiarMM ramka_wymiar_mm = new RamkaWymiarMM(this);
		RamkaWymiarCM ramka_wymiar_cm = new RamkaWymiarCM(this);
		RamkaFigura ramka_figura = new RamkaFigura(this);
		RamkaIlosc ramka_ilosc = new RamkaIlosc(this);
		RamkaFiguraAtrapa ramka_figura_atrapa = new RamkaFiguraAtrapa(this);

		addMouseListeners();

		frame.addWindowListener(this.actionOnClose());
	}

	protected void initZapiszButton()
	{
		zapisz_dane.setBounds(0, this.HEIGHT - 25, 150, 25);
		zapisz_dane.addActionListener(zapiszDaneListener());
		add(zapisz_dane);
	}

	private ActionListener zapiszDaneListener()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				zapiszDane();
			}
		};
	}

	private void zapiszDane()
	{
		DBConnector.zapiszDane();
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

	int i;

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.YELLOW);
		// policzIloscKomponentow();
		rysujRamki(g);

	}

	private MouseListener ml = Tools.getFocusOut(this);

	private void addMouseListeners()
	{
		for (Component c : this.getComponents())
		{
			if (c instanceof JTextField)
			{
				c.addMouseListener(ml);
			}
		}
	}

	private void policzIloscKomponentow()
	{
		int label = 0;
		int button = 0;
		int text_field = 0;

		for (Component c : this.getComponents())
		{
			if (c instanceof JButton)
				;
			// button++;
			if (c instanceof JLabel)
				;
			// label++;
			if (c instanceof JTextField)
			{
			}
			// text_field++;
		}
		// System.out.println("Label:" + label);
		// System.out.println("Buttin:" + button);
		// System.out.println("TextField:" + text_field);
	}

	private void rysujRamki(Graphics g)
	{

		RamkaDaneKlienta.ramkaDaneKlienta(g, this);
		RamkaWymiarMM.ramkaWymiarMM(g);
		RamkaIlosc.ramkaIlosc(g, this);
		RamkaCzesci.ramkaCzesci(g);
		RamkaFigura.ramkaFigura(g, this);
		RamkaFiguraAtrapa.ramkaFiguraAtrapa(g, this);

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

		if (czy_aktywna_ilosc_paczek_sworzen)
		{
			g.setColor(Color.BLUE);
		}
		else
		{
			g.setColor(Color.BLACK);
		}
		g.fillRect(WIDTH - 25, 50, 25, 25);

		RamkaWymiarMM.rescale();

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
	public boolean czy_aktywna_ilosc_paczek_sworzen = false;

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (tryb.equals("figury"))
		{
			if (z_x >= 0)
			{
				switch (e.getKeyCode())
				{
					case Keys.ARROW_DOWN:
						if (z_x + 1 < ZamowienieDane.f_kontrolki.size())
						{
							z_x++;
						}
						else
						{
							DodawanieFigur.dodajFigure(this, true);
						}
						RamkaFigura.skalaReset();
						break;
					case Keys.ARROW_UP: // GÓRA
						if (z_x > 0)
						{
							z_x--;
						}
						RamkaFigura.skalaReset();
						break;
					case Keys.ARROW_RIGHT: // PRAWO
					case Keys.ENTER: // ENTER
						if (!czy_aktywna_ilosc_paczek_sworzen && (z_y == 4 || z_y == 6))
						{
							z_y++;
						}

						if (z_y + 1 < 8)
						{
							z_y++;
						}
						else
						{
							tryb = "czesci_figury";
							ZamowienieDane.czesc_kontrolki_figura.get(f_x).bok.grabFocus();
							z_y = 1;
						}
						break;

					case Keys.ARROW_LEFT: // LEWO
						if (!czy_aktywna_ilosc_paczek_sworzen && (z_y == 6))
						{
							z_y--;
						}
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
				if (tryb.equals("figury"))
				{
					RamkaWymiarMM.przewinElementy(this, ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y]);
					ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
					if (e.getKeyCode() == Keys.F1) // F1
					{
						tryb = "czesci_figury";
						ZamowienieDane.czesc_kontrolki_figura.get(f_x).bok.grabFocus();
					}
				}

			}
		}
		else if (tryb.equals("czesci"))
		{
			switch (e.getKeyCode())
			{
				case Keys.ARROW_DOWN:
					if (c_x + 1 < ZamowienieDane.figury.get(z_x).figura.getCzesci().size())
					{
						c_x++;
					}
					else
					{
						RamkaCzesci.dodajCzesc(this, z_x);
					}
					break;
				case Keys.ARROW_UP:
					if (c_x > 0)
					{
						c_x--;
					}
					break;
				case Keys.ARROW_RIGHT:
					if (c_y + 1 < 2)
					{
						c_y++;
					}
					break;

				case Keys.ARROW_LEFT:
					if (c_y > 0)
					{
						c_y--;
					}
					break;
			}
			ZamowienieDane.czesc_kontrolki.get(c_x).kontrolki[c_y].grabFocus();
			if (e.getKeyCode() == Keys.F1) // F1
			{
				tryb = "figury";
				ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
			}
		}
		else if (tryb.equals("czesci_figury"))
		{
			switch (e.getKeyCode())
			{
				case Keys.ENTER:
				case Keys.ARROW_DOWN:
					if (f_x + 1 < ZamowienieDane.figury.get(z_x).figura.getCzesci().size())
					{
						f_x++;
					}
					else
					{

						if (z_x + 1 >= ZamowienieDane.f_kontrolki.size())
						{
							DodawanieFigur.dodajFigure(this, true);
						}
						else
						{
							z_x++;
						}
						tryb = "figury";
						ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
					}
					break;
				case Keys.ARROW_UP:
					if (f_x > 0)
					{
						f_x--;
					}

					break;
			}
			ZamowienieDane.czesc_kontrolki_figura.get(f_x).bok.grabFocus();
			if (e.getKeyCode() == Keys.F1) // F1
			{
				tryb = "czesci";
				ZamowienieDane.czesc_kontrolki.get(c_x).kontrolki[c_y].grabFocus();

			}
		}
		if (tryb.equals("uwagi"))
		{
			if (e.getKeyCode() == Keys.F9) // F9
			{
				tryb = "figury";
				ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
			}
		}
		else
		{
			switch (e.getKeyCode())
			{
				case Keys.F2: // F2
					if (tryb_rzeczywisty)
					{
						tryb_rzeczywisty = false;
					}
					else
					{
						tryb_rzeczywisty = true;
					}
					RamkaWymiarCM.ustawKontrolki(z_x, this);
					repaint();
					break;

				case Keys.R: // r
					DodawanieFigur.usunFigure(z_x, this);
					break;

				case Keys.F6: // F6 - zapisz w pamięci
					f_zamowienie_temp = ZamowienieDane.figury.get(z_x);
					break;

				case Keys.F7: // F7 - skopiuj
					ZamowienieDane.figury.set(z_x, new FiguraZamowienie(f_zamowienie_temp));
					ZamowienieDane.f_kontrolki.get(z_x).updateFromFiguraZamowienie();

					break;
				case Keys.F9: // F9
					tryb = "uwagi";
					RamkaUwagi.uwagi.grabFocus();
					break;
				case Keys.F12: // F12
					czy_aktywna_ilosc_paczek_sworzen = !czy_aktywna_ilosc_paczek_sworzen;
					repaint();
					break;
			}

			// czyszczenie pól jak Excelu - wchodząc w pole i wpisując kasujesz
			// poprzednią zawartość
			if (Tools.jestKodemCyfry(e.getKeyCode()))
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

	public static int rescale(double number)
	{
		return (int) (Tools.scale * number);
	}

	public WindowAdapter actionOnClose()
	{
		return new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent windowEvent)
			{
				if (JOptionPane.showConfirmDialog(frame, "Czy chcesz zapisać?", "UWAGA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				{
					System.out.println("ZAPISAĆ!!!!!!!!11");
					zapiszDane();
				}
			}
		};
	}

}
