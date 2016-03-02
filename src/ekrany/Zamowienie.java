package ekrany;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
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

	private void getFigury()
	{
		DodawanieFigur.setDefaultFigura();
		DodawanieFigur.dodajFigure(this);

	}

	public int z_x = -1;
	public int z_y = 1;
	public int c_x = -1;
	public int c_y = 0;
	public String tryb = "figury";

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
							DodawanieFigur.dodajFigure(this);
						}
						break;
					case 38:
						if (z_x > 0)
						{
							z_x--;
						}
						break;
					case 39:
						if (z_y + 1 < 8)
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

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

}
