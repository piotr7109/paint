package ekrany;

import dodatki.*;
import modules.czesci.Czesc;
import modules.figury.Figura;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

public class Rysowanie extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = CONST.WIDTH;
	private static final int HEIGHT = CONST.HEIGHT;

	private boolean is_pressed = false;
	private Point start, koniec;
	private ArrayList<Point> linie_start = new ArrayList<Point>();
	private ArrayList<Point> linie_koniec = new ArrayList<Point>();

	private Luk luk;
	private ArrayList<Luk> okregi = new ArrayList<Luk>();
	private JComboBox<Integer> kierunek = new JComboBox<>(new Integer[] { 1, -1 });

	private ArrayList<Integer> dlugosci = new ArrayList<Integer>();
	private ArrayList<Integer> katy = new ArrayList<Integer>();

	private ArrayList<JLabel> dlugosc_label = new ArrayList<JLabel>();
	private ArrayList<JLabel> katy_label = new ArrayList<JLabel>();
	private ArrayList<String> figury_kolejnosc = new ArrayList<String>();

	private Point aktualna_pozycja = new Point(0, 0);
	private JLabel aktualna_dlugosc = new JLabel();
	private JLabel aktualny_kat = new JLabel();
	private int last_kat = 0;

	private String figura = "odcinek";
	private String last_figura = "";

	public JButton reset;
	public JButton zapisz;
	public JTextField kod;

	private int skala = 5;

	private ArrayList<JTextField> dlugosci_text = new ArrayList<JTextField>();
	private ArrayList<JTextField> katy_text = new ArrayList<JTextField>();

	public Rysowanie(JFrame frame)
	{

		this.setLayout(null);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.dodajKontrolki(frame);
		this.aktualna_dlugosc.setSize(25, 25);
		this.aktualny_kat.setSize(70, 25);
		// this.katy.add(0);
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	public ArrayList<Integer> _dlugosci = new ArrayList<Integer>();
	public ArrayList<Integer> _katy = new ArrayList<Integer>();
	public ArrayList<String> _typy_figur = new ArrayList<String>();

	public void dodajKontrolki(JFrame frame)
	{
		JButton odcinek = new JButton("Odcinek");
		JButton okrag = new JButton("Łuk");
		reset = new JButton("Resetuj");
		zapisz = new JButton("Zapisz");
		kod = new JTextField();

		odcinek.setSize(CONST.btn_size);
		okrag.setSize(CONST.btn_size);
		reset.setSize(CONST.btn_size);
		zapisz.setSize(CONST.btn_size);
		kod.setSize(50, 25);

		odcinek.setLocation(0, 0);
		okrag.setLocation(110, 0);
		reset.setLocation(220, 0);
		zapisz.setLocation(WIDTH - 100, HEIGHT - 25);
		kod.setLocation(WIDTH - 150, HEIGHT - 25);

		reset.addActionListener(resetuj(frame));

		this.add(odcinek);
		this.add(okrag);
		this.add(reset);
		this.add(zapisz);
		this.add(kod);

		odcinek.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				figura = "odcinek";
				remove(kierunek);
				repaint();
			}
		});
		okrag.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				figura = "okrag";
				kierunek.setLocation(210, 30);
				kierunek.setSize(50, 25);
				add(kierunek);
				repaint();
			}
		});
		zapisz.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				_dlugosci.clear();
				_katy.clear();
				_typy_figur.clear();
				int index_linia = 0;
				int index_okrag = 0;
				for (String s : figury_kolejnosc)
				{
					switch (s)
					{
						case "linia":
							_dlugosci.add(dlugosci.get(index_linia));
							_katy.add(katy.get(index_linia));
							_typy_figur.add("linia");
							index_linia++;
							break;
						case "okrag":
							double rozmiar = (okregi.get(index_okrag).rozmiar * Math.PI * okregi.get(index_okrag).kat2 / 360);
							_dlugosci.add((int) rozmiar);
							_katy.add(okregi.get(index_okrag).kat2);
							_typy_figur.add("okrag");
							index_okrag++;
							break;

					}

				}
				zapiszElement();

			}
		});

	}

	private int start_x = 0, start_y = 0;

	private void zapiszElement()
	{
		Figura fig = new Figura();
		fig.setKod(Integer.parseInt(kod.getText()));
		fig.start_x = start_x / skala;
		fig.start_y = (start_y) / skala - 20;
		int id_figury = fig.insert();
		int size = dlugosci_text.size();
		for (int i = 0; i < size; i++)
		{
			Czesc czesc = new Czesc();
			czesc.setDlugosc(Integer.parseInt(dlugosci_text.get(i).getText()));
			czesc.setKat(Integer.parseInt(katy_text.get(i).getText()));
			czesc.setIdFigury(id_figury);
			czesc.setTyp(_typy_figur.get(i));
			czesc.insert();
			czesc.setDlugosc(_dlugosci.get(i) / skala);
			czesc.setKat(_katy.get(i));
			czesc.insertAtrapa();
		}
	}

	private ActionListener resetuj(final JFrame frame)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				frame.dispose();
				JFrame new_frame = new JFrame("Rysowanie");
				new_frame.setResizable(false);

				new_frame.setVisible(true);
				new_frame.add(new Rysowanie(new_frame));
				new_frame.pack();

			}
		};
	}

	private void resizeCzesci()
	{
		Dimension size = new Dimension(CONST.rescale(50), CONST.rescale(20));
		int x = (int) (this.getWidth() - size.getWidth());
		int x2 = (int) (this.getWidth() - size.getWidth() * 2 - 10);
		int y = 0;
		int y2 = 0;

		int text_size = dlugosci_text.size();

		for (int i = 0; i < text_size; i++)
		{
			dlugosci_text.get(i).setBounds(x2, CONST.rescale(y += size.getHeight()), size.width, size.height);
			katy_text.get(i).setBounds(x, CONST.rescale(y2 += size.getHeight()), size.width, size.height);
		}
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
		if (!is_pressed)
		{
			if (start == null)
			{
				start = new Point();
				start.x = e.getX();
				start.y = e.getY();
				start_x = start.x;
				start_y = start.y;
				this.add(aktualna_dlugosc);
				this.add(aktualny_kat);
			}
			else
			{
				start = koniec;
			}
			if (figura == "okrag")
			{
				this.luk = new Luk();
			}
			is_pressed = true;
		}
		else
		{
			koniec = new Point();
			koniec.x = e.getX();
			koniec.y = e.getY();
			is_pressed = false;

			switch (this.figura)
			{
				case "odcinek":
					this.addLinie();
					this.obliczLinie();
					figury_kolejnosc.add("linia");
					break;
				case "okrag":
					this.addOkrag();
					figury_kolejnosc.add("okrag");
					break;
			}

			this.aktualny_kat.setText("");
			this.aktualny_kat.setForeground(Color.GREEN);

		}
		repaint();
	}

	private void addOkrag()
	{
		Luk luk = this.luk;
		this.okregi.add(luk);
		this.koniec = new Point(luk.x_koniec, luk.y_koniec);

		// this.luk = null;
		int rad_x = (int) (Math.cos(-1 * Math.toRadians(180 + this.luk.kat + this.luk.kat2 + (this.luk.kierunek * 90))) * 100);
		int rad_y = (int) (Math.sin(-1 * Math.toRadians(180 + this.luk.kat + this.luk.kat2 + (this.luk.kierunek * 90))) * 100);
		po = new Point(luk.x_koniec + rad_x, luk.y_koniec + rad_y);

		if (this.luk.kierunek == 1)
			this.last_kat = (luk.kat2 + luk.kat);
		else
			this.last_kat = (luk.kat2 + luk.kat + 180);
		this.last_figura = "okrag";

		this.dodajCzesc();
	}

	private void addLinie()
	{
		linie_start.add(start);
		linie_koniec.add(koniec);
		int size = linie_start.size() - 1;
		this.last_kat = Obliczenia.getKat(linie_start.get(size), linie_koniec.get(size), new Point(linie_koniec.get(size).x, linie_koniec.get(size).y + 90));
		this.last_figura = "linia";
	}

	private void obliczLinie()
	{

		int dlugosc = Obliczenia.getDlugosc(start, koniec);

		JLabel dlugosc_label = new JLabel(dlugosc / skala + "");
		dlugosc_label.setForeground(Color.GREEN);

		dlugosc_label.setLocation((start.x + koniec.x) / 2, (start.y + koniec.y) / 2);
		dlugosc_label.setSize(25, 25);
		this.dlugosc_label.add(dlugosc_label);
		this.add(dlugosc_label);
		this.dlugosci.add(dlugosc);

		if (linie_start.size() > 1)
		{
			JLabel kat_label = new JLabel(kat_teraz / skala + "");
			kat_label.setForeground(Color.GREEN);
			kat_label.setLocation(start.x, start.y);
			kat_label.setSize(25, 25);
			this.katy_label.add(kat_label);
			this.add(kat_label);
			this.katy.add(kat_teraz);
		}
		else
		{
			this.katy.add(kat_teraz);
		}

		this.dodajCzesc();

	}

	public void mouseDragged(MouseEvent e)
	{
	}

	Point po = new Point(0, 0);
	private int kat_teraz = 0;

	////////////////////////////////////////////////////////////////////////////////////////////////////
	public void mouseMoved(MouseEvent e)
	{
		if (is_pressed)
		{
			aktualna_pozycja.x = e.getX();
			aktualna_pozycja.y = e.getY();

			int dlugosc_teraz = 0;

			if (this.figura == "odcinek")
			{
				if (linie_start.size() > 0)
				{
					switch (this.last_figura)
					{
						case "linia":
							kat_teraz = Obliczenia.getKat(linie_start.get(linie_start.size() - 1), start, e.getPoint()) - 180;
							break;
						case "okrag":

							kat_teraz = Obliczenia.getKat(po, start, e.getPoint()) - 180;
							break;
					}

				}
				else
				{
					kat_teraz = Obliczenia.getKat(start, start, e.getPoint());
					if (kat_teraz > 180)
					{
						kat_teraz -= 360;
					}

				}
				dlugosc_teraz = Obliczenia.getDlugosc(aktualna_pozycja, start);
			}
			else if (figura == "okrag")
			{
				switch (this.last_figura)
				{
					case "linia":

						break;
					case "okrag":

						break;
				}
				kat_teraz = Math.abs((int) aktualna_pozycja.getY() - start.y) * 2;
				if (kat_teraz > 360)
					kat_teraz = 360;
				dlugosc_teraz = (int) (Math.abs((int) aktualna_pozycja.getX() - start.x) * kat_teraz / 360 * Math.PI);
			}

			this.aktualna_dlugosc.setText(dlugosc_teraz / skala + "");
			this.aktualna_dlugosc.setLocation((start.x + e.getX()) / 2, (start.y + e.getY()) / 2);
			this.aktualna_dlugosc.setForeground(Color.GREEN);

			this.aktualny_kat.setText("kąt: " + kat_teraz + "");
			this.aktualny_kat.setLocation((start.x + e.getX()) / 2 + 50, (start.y + e.getY()) / 2 + 50);

			repaint();
		}

	}

	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke(4));
		rysujLinie(g2d);
		rysujOkregi(g2d);
		rysujAktualne(g2d);
		resizeCzesci();

		g2d.setColor(Color.BLUE);
		g2d.drawRect(0, 100, 150 * skala, 50 * skala);

		g2d.setStroke(new BasicStroke(1));
	}

	private void rysujAktualne(Graphics g2d)
	{
		if (is_pressed)
		{
			switch (this.figura)
			{
				case "odcinek":
					g2d.drawLine((int) start.getX(), (int) start.getY(), (int) aktualna_pozycja.getX(), (int) aktualna_pozycja.getY());
					break;
				case "okrag":
					rysujLuk(g2d);
					break;
			}

		}

	}

	private void rysujLuk(Graphics g2d)
	{
		int kat;
		int kierunek = (int) this.kierunek.getSelectedItem();
		int rozmiar = Math.abs((int) aktualna_pozycja.getX() - start.x);
		int kat2 = Math.abs((int) aktualna_pozycja.getY() - start.y) * 2;

		kat = last_kat * kierunek;
		Point p;
		if (koniec == null)
		{
			p = new Point(start.x, start.y);
		}
		else
		{
			p = new Point(koniec.x, koniec.y);
		}
		int x2 = p.x + rozmiar / 2;
		int y2 = p.y + rozmiar / 2;
		double[] pt = { x2, p.y };
		AffineTransform.getRotateInstance(Math.toRadians((90 - kat) * kierunek), x2, y2).transform(pt, 0, pt, 0, 1);

		Double newX = pt[0];
		Double newY = pt[1];

		pt[0] = p.x; // pocz�tkowa pozycja X
		pt[1] = p.y; // pocz�tkowa pozycja Y

		p.x = p.x - Math.abs(newX.intValue() - p.x);
		p.y = p.y - Math.abs(newY.intValue() - p.y);

		x2 = p.x + rozmiar / 2; // �rodek �uku
		y2 = p.y + rozmiar / 2; // �rodek �uku

		// liczenie pozycji k�ta ko�cowego
		AffineTransform.getRotateInstance(Math.toRadians(-kat2 * kierunek), x2, y2).transform(pt, 0, pt, 0, 1);
		int x_koniec = (int) pt[0];
		int y_koniec = (int) pt[1];
		if (kierunek == -1)
		{
			g2d.drawArc(p.x, p.y, rozmiar, rozmiar, kat * kierunek - 180, kat2 * kierunek);
			this.luk.setLuk(p.x, p.y, rozmiar, kat * kierunek - 180, kat2 * kierunek);
		}
		else
		{
			g2d.drawArc(p.x, p.y, rozmiar, rozmiar, kat * kierunek, kat2 * kierunek);
			this.luk.setLuk(p.x, p.y, rozmiar, kat * kierunek, kat2 * kierunek);

		}
		this.luk.kierunek = kierunek;
		this.luk.x_koniec = x_koniec;
		this.luk.y_koniec = y_koniec;

	}

	private void rysujOkregi(Graphics g2d)
	{
		int ile_okregow = okregi.size();
		for (int i = 0; i < ile_okregow; i++)
		{
			Luk ok = okregi.get(i);
			g2d.drawArc(ok.x, ok.y, ok.rozmiar, ok.rozmiar, ok.kat, ok.kat2);
		}

	}

	private void rysujLinie(Graphics g2d)
	{
		Point s = new Point();
		Point k = new Point();
		int ile_linii = linie_start.size();
		for (int i = 0; i < ile_linii; i++)
		{
			s = linie_start.get(i);
			k = linie_koniec.get(i);
			g2d.drawLine((int) s.getX(), (int) s.getY(), (int) k.getX(), (int) k.getY());
		}
	}

	private int z_x = 0, z_y = -1;
	private JTextField poprzedni_text_field = null;

	private void dodajCzesc()
	{
		JTextField dlugosc = new JTextField("0");
		JTextField kat = new JTextField("0");

		CONST.setKoloryNieaktywny(dlugosc);
		CONST.setKoloryNieaktywny(kat);

		dlugosc.addKeyListener(this);
		kat.addKeyListener(this);
		dlugosc.addFocusListener(kontrolkiFocus());
		kat.addFocusListener(kontrolkiFocus());

		dlugosci_text.add(dlugosc);
		katy_text.add(kat);

		dlugosc.grabFocus();
		z_y++;
		add(dlugosc);
		add(kat);
	}

	private FocusListener kontrolkiFocus()
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent e)
			{
				CONST.setKoloryNieaktywny((JTextComponent) e.getSource());

			}

			@Override
			public void focusGained(FocusEvent e)
			{
				CONST.setKoloryAktywny((JTextComponent) e.getSource());

			}
		};
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int size = dlugosci_text.size();
		switch (e.getKeyCode())
		{
			case 40: // DÓŁ
				if (z_y + 1 < size)
				{
					z_y++;
				}
				break;
			case 38: // GÓRA
				if (z_y > 0)
				{
					z_y--;
				}
				break;
			case 39: // PRAWO
				if (z_x + 1 < 2)
				{
					z_x++;
				}
				break;
			case 37: // ENTER
				if (z_x > 0)
				{
					z_x--;
				}
				break;
		}
		if (z_x == 0)
		{
			dlugosci_text.get(z_y).grabFocus();
		}
		else
		{
			katy_text.get(z_y).grabFocus();
		}
		if (CONST.jestKodemCyfry(e.getKeyCode()))
		{
			JTextField current_text_field = (JTextField) KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
			if (poprzedni_text_field != current_text_field)
			{
				poprzedni_text_field = current_text_field;
				current_text_field.setText("");
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
	}

}