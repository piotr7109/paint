package ekrany;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.formularz.*;
import modules.zamowienie.odbiorcy.Odbiorca;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.obiekty.Obiekt;

public class Formularz extends JPanel
{
	private static final long serialVersionUID = -7097849808157570212L;
	private static final int WIDTH = 150;
	private static final int HEIGHT = 20;
	private static final int WIDTH_LABEL = 100;

	private ArrayList<JTextField> kontrolki_label = new ArrayList<JTextField>();
	private String nazwy_label[] = { "Odbiorca", "Budowa", "Obiekt", "Element" };

	private JButton button_potwierdz;

	public JComboBox<Odbiorca> odbiorcy_combo = new JComboBox<Odbiorca>();
	public JComboBox<Budowa> budowy_combo = new JComboBox<Budowa>();
	public JComboBox<Obiekt> obiekty_combo = new JComboBox<Obiekt>();
	public JComboBox<Element> elementy_combo = new JComboBox<Element>();

	public ArrayList<Odbiorca> odbiorcy = new ArrayList<Odbiorca>();
	public ArrayList<Budowa> budowy = new ArrayList<Budowa>();
	public ArrayList<Obiekt> obiekty = new ArrayList<Obiekt>();
	public ArrayList<Element> elementy = new ArrayList<Element>();

	public JTextField odbiorcy_kod = new JTextField();
	public JTextField budowy_kod = new JTextField();
	public JTextField obiekty_kod = new JTextField();
	public JTextField elementy_kod = new JTextField();

	private JButton odbiorca_nowy = new JButton("Nowy");
	private JButton budowa_nowy = new JButton("Nowy");
	private JButton obiekt_nowy = new JButton("Nowy");
	private JButton element_nowy = new JButton("Nowy");

	private JButton odbiorca_edytuj = new JButton("Edytuj");
	private JButton budowa_edytuj = new JButton("Edytuj");
	private JButton obiekt_edytuj = new JButton("Edytuj");
	private JButton element_edytuj = new JButton("Edytuj");

	private JButton lista_produkcyjna = new JButton("Lista produkcyjna");
	private JButton lista_wysylkowa = new JButton("Lista wysyłkowa");
	private JButton metki = new JButton("Metki");
	private JButton wagi = new JButton("Wagi");
	private JButton optymalizacja = new JButton("Optymalizacja");

	private JButton rysowanie = new JButton("Dodaj Figurę");

	public Formularz()
	{
		this.setPreferredSize(new Dimension((int) (CONST.scale * CONST.WIDTH), (int) (CONST.scale * CONST.HEIGHT)));
		this.setLayout(null);

		this.konfigurujEtykiety();
		this.konfigurujKontrolkiCombo();
		this.konfigurujKontrolkiKodow();
		this.konfigurujKontrolkiNowe();
		this.konfigurujKontrolkiEdytuj();
		this.konfigurujDodatkoweButtony();
		konfigurujKontrolkiWydruki();

		EventLoaderJComboBox.wczytajOdbiorcow(this);

	}

	private void konfigurujDodatkoweButtony()
	{
		rysowanie.addActionListener(EventLoaderDodatkoweButtony.rysujButton());

		add(rysowanie);
	}

	private void konfigurujKontrolkiWydruki()
	{

		lista_produkcyjna.addActionListener(EventLoaderWydruki.drukujListeProdukcyjna());
		lista_wysylkowa.addActionListener(EventLoaderWydruki.drukujListeWysylkowa());
		metki.addActionListener(EventLoaderWydruki.drukujMetki());
		wagi.addActionListener(EventLoaderWydruki.drukujWagi());
		optymalizacja.addActionListener(EventLoaderWydruki.drukujOptymalizacje());

		add(lista_produkcyjna);
		add(lista_wysylkowa);
		add(metki);
		add(wagi);
		add(optymalizacja);
	}

	private void konfigurujKontrolkiCombo()
	{

		odbiorcy_combo.addActionListener(EventLoaderJComboBox.odbiorcyEvent(this));
		budowy_combo.addActionListener(EventLoaderJComboBox.budowyEvent(this));
		obiekty_combo.addActionListener(EventLoaderJComboBox.obiektyEvent(this));
		elementy_combo.addActionListener(EventLoaderJComboBox.elementyEvent(this));

		add(odbiorcy_combo);
		add(budowy_combo);
		add(obiekty_combo);
		add(elementy_combo);

	}

	private void konfigurujEtykiety()
	{
		int ile_kontrolek = nazwy_label.length;
		for (int i = 0; i < ile_kontrolek; i++)
		{
			JTextField k_label = new JTextField(nazwy_label[i]);
			k_label.setBorder(BorderFactory.createEmptyBorder());
			k_label.setBackground(Color.WHITE);
			k_label.setEditable(false);
			kontrolki_label.add(k_label);

			this.add(k_label);
		}

		button_potwierdz = new JButton("Potwierdź");

		button_potwierdz.addActionListener(buttonPotwierdz());

		add(button_potwierdz);
	}

	private void konfigurujKontrolkiKodow()
	{

		odbiorcy_kod.addFocusListener(EventLoaderJTextfield.odbiorcaEvent(this));
		budowy_kod.addFocusListener(EventLoaderJTextfield.budowaEvent(this));
		obiekty_kod.addFocusListener(EventLoaderJTextfield.obiektEvent(this));
		elementy_kod.addFocusListener(EventLoaderJTextfield.elementEvent(this));

		add(odbiorcy_kod);
		add(budowy_kod);
		add(obiekty_kod);
		add(elementy_kod);
	}

	private void konfigurujKontrolkiNowe()
	{

		odbiorca_nowy.addActionListener(EventLoaderNowe.odbiorcaEvent(this));
		budowa_nowy.addActionListener(EventLoaderNowe.budowaEvent(this));
		obiekt_nowy.addActionListener(EventLoaderNowe.obiektEvent(this));
		element_nowy.addActionListener(EventLoaderNowe.elementEvent(this));

		add(odbiorca_nowy);
		add(budowa_nowy);
		add(obiekt_nowy);
		add(element_nowy);
	}

	private void konfigurujKontrolkiEdytuj()
	{

		odbiorca_edytuj.addActionListener(EventLoaderEdytuj.odbiorcaEvent(this));
		budowa_edytuj.addActionListener(EventLoaderEdytuj.budowaEvent(this));
		obiekt_edytuj.addActionListener(EventLoaderEdytuj.obiektEvent(this));
		element_edytuj.addActionListener(EventLoaderEdytuj.elementEvent(this));

		add(odbiorca_edytuj);
		add(budowa_edytuj);
		add(obiekt_edytuj);
		add(element_edytuj);
	}

	protected ActionListener buttonPotwierdz()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = new JFrame("Dodaj nowy");
				frame.setResizable(false);

				frame.setVisible(true);

				if (elementy_combo.getItemCount() > 0)
				{
					ZamowienieDane.odbiorca = (Odbiorca) odbiorcy_combo.getSelectedItem();
					ZamowienieDane.budowa = (Budowa) budowy_combo.getSelectedItem();
					ZamowienieDane.obiekt = (Obiekt) obiekty_combo.getSelectedItem();
					ZamowienieDane.element = (Element) elementy_combo.getSelectedItem();
					frame.add(new Zamowienie());
				}
				else
				{
					frame.add(getKomunikatOBledzie(frame));
				}

				frame.pack();

			}
		};
	}

	protected Komunikat getKomunikatOBledzie(JFrame frame)
	{
		return new Komunikat("Brak obiektu", frame);
	}

	///////////////////////////////////////// RESIZE
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		float scale = ((float) (this.getWidth()) / 1000);
		CONST.scale = scale;
		resize();
	}

	protected void resize()
	{
		resizeCombo();
		resizeTextField();
		resizeLabel();
		resizeButton();
		resizeWydruki();
		resizeDodatkoweButtony();
		for (Component comp : this.getComponents())
		{
			((JComponent) comp).setFont(new Font("", 0, CONST.rescale(12)));
		}
		System.out.println("REPAINT END");

	}

	private void resizeDodatkoweButtony()
	{
		Dimension size = new Dimension(CONST.rescale(150), CONST.rescale(50));
		int y = this.getHeight() - size.height;
		int x = this.getWidth() - size.width;

		rysowanie.setBounds(x, y, size.width, size.height);
	}

	private void resizeWydruki()
	{
		Dimension size = new Dimension(CONST.rescale(150), CONST.rescale(50));
		int y = -size.height;
		int x = this.getWidth() - size.width;

		lista_produkcyjna.setBounds(x, y += size.height, size.width, size.height);
		lista_wysylkowa.setBounds(x, y += size.height, size.width, size.height);
		metki.setBounds(x, y += size.height, size.width, size.height);
		wagi.setBounds(x, y += size.height, size.width, size.height);
		optymalizacja.setBounds(x, y += size.height, size.width, size.height);
	}

	private void resizeButton()
	{
		int y = -20;
		int x = CONST.rescale(480);
		Dimension text_size = new Dimension(CONST.rescale(85), CONST.rescale(25));

		odbiorca_nowy.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		budowa_nowy.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		obiekt_nowy.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		element_nowy.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);

		x = CONST.rescale(580);
		y = -20;

		odbiorca_edytuj.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		budowa_edytuj.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		obiekt_edytuj.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		element_edytuj.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
	}

	private void resizeLabel()
	{
		int y = -20;
		int x = 20;

		int ile_kontrolek = kontrolki_label.size();
		for (int i = 0; i < ile_kontrolek; i++)
		{
			y += HEIGHT + 10;
			kontrolki_label.get(i).setLocation(CONST.rescale(x), CONST.rescale(y));
			kontrolki_label.get(i).setSize(new Dimension(CONST.rescale(WIDTH_LABEL), CONST.rescale(HEIGHT)));
		}

		button_potwierdz.setLocation(CONST.rescale(x + WIDTH_LABEL), CONST.rescale(y + HEIGHT * 2));
		button_potwierdz.setSize(CONST.rescale(WIDTH), (CONST.rescale(HEIGHT)));
	}

	private void resizeTextField()
	{
		int x = CONST.rescale(440);
		int y = -20;
		Dimension text_size = new Dimension(CONST.rescale(35), CONST.rescale(25));
		odbiorcy_kod.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		budowy_kod.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		obiekty_kod.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		elementy_kod.setBounds(x, CONST.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
	}

	private void resizeCombo()
	{
		int x = CONST.rescale(120);
		int y = -20;
		Dimension combo_size = new Dimension((int) (300 * CONST.scale), (int) (25 * CONST.scale));
		odbiorcy_combo.setSize(combo_size);
		budowy_combo.setSize(combo_size);
		obiekty_combo.setSize(combo_size);
		elementy_combo.setSize(combo_size);

		odbiorcy_combo.setLocation(x, CONST.rescale(y += HEIGHT + 10));
		budowy_combo.setLocation(x, CONST.rescale(y += HEIGHT + 10));
		obiekty_combo.setLocation(x, CONST.rescale(y += HEIGHT + 10));
		elementy_combo.setLocation(x, CONST.rescale(y += HEIGHT + 10));
	}
}
