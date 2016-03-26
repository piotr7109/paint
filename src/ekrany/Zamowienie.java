package ekrany;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

public class Zamowienie extends JPanel implements KeyListener
{
	private static final int WIDTH = CONST.WIDTH;
	private static final int HEIGHT = CONST.HEIGHT;

	public Figura figura;

	public Zamowienie()
	{
		this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		this.addKeyListener(this);

		init();

	}

	private void init()
	{
		DodawanieFigur.setDefaultFigura();
		DodawanieFigur.dodajFigure(this, true);

		RamkaWymiarCM.init(this);
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
		
		if(tryb_rzeczywisty)
		{
			g.setColor(Color.GREEN);
		}
		else
		{
			g.setColor(Color.RED);
		}
		g.fillOval(WIDTH-50, 0, 50, 50);

	}

	public int z_x = -1;
	public int z_y = 1;
	public int c_x = -1;
	public int c_y = 0;
	public String tryb = "figury"; // figury/czesci
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
					case 40:
						if (z_x + 1 < ZamowienieDane.f_kontrolki.size())
						{
							z_x++;

						}
						else
						{
							DodawanieFigur.dodajFigure(this, true);
						}
						break;
					case 38:
						if (z_x > 0)
						{
							z_x--;
						}
						break;
					case 39:
						if (z_y + 1 < 7)
						{
							z_y++;
						}
						break;

					case 37:
						if (z_y > 1)
						{
							z_y--;
						}
						break;
				}
				RamkaWymiarMM.przewinElementy(this, ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y]);
				ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].grabFocus();
				if (e.getKeyCode() == 112) // F1
				{
					tryb = "czesci";
					ZamowienieDane.czesc_kontrolki.get(c_x).kontrolki[c_y].grabFocus();
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
		}

		if (jestKodemCyfry(e.getKeyCode()))
		{
			if (poprzedni_text_field != ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y])
			{
				poprzedni_text_field = ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y];
				ZamowienieDane.f_kontrolki.get(z_x).kontrolki[z_y].setText("");
			}
		}
		System.out.println(e.getKeyCode());

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

}
