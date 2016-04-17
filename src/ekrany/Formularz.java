package ekrany;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dane.ZamowienieDane;
import ekrany.formularz.*;
import modules.zamowienie.odbiorcy.Odbiorca;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.obiekty.Obiekt;

public class Formularz extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7097849808157570212L;
	private static final int WIDTH = 150;
	private static final int HEIGHT = 20;
	private static final int WIDTH_LABEL = 100;

	private ArrayList<JLabel> kontrolki_label = new ArrayList<JLabel>();
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

	public Formularz()
	{

		this.setLayout(null);

		this.stworzEtykiety();
		this.stworzKontrolki();
		this.ustawKontrolkiKodow();
		this.konfigurujKontrolki();
		this.konfigurujKontrolkiNowe();
		this.konfigurujKontrolkiEdytuj();

		EventLoaderJComboBox.wczytajOdbiorcow(this);

	}

	private void stworzKontrolki()
	{
		int x = 120;
		int y = -20;
		Dimension combo_size = new Dimension(300, 25);

		odbiorcy_combo.setSize(combo_size);
		budowy_combo.setSize(combo_size);
		obiekty_combo.setSize(combo_size);
		elementy_combo.setSize(combo_size);

		odbiorcy_combo.setLocation(x, y += HEIGHT + 10);
		budowy_combo.setLocation(x, y += HEIGHT + 10);
		obiekty_combo.setLocation(x, y += HEIGHT + 10);
		elementy_combo.setLocation(x, y += HEIGHT + 10);

		odbiorcy_combo.addActionListener(EventLoaderJComboBox.odbiorcyEvent(this));
		budowy_combo.addActionListener(EventLoaderJComboBox.budowyEvent(this));
		obiekty_combo.addActionListener(EventLoaderJComboBox.obiektyEvent(this));
		elementy_combo.addActionListener(EventLoaderJComboBox.elementyEvent(this));

		add(odbiorcy_combo);
		add(budowy_combo);
		add(obiekty_combo);
		add(elementy_combo);

	}

	private void ustawKontrolkiKodow()
	{
		int x = 440;
		int y = -20;
		Dimension text_size = new Dimension(35, 25);
		odbiorcy_kod.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		budowy_kod.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		obiekty_kod.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		elementy_kod.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);

		odbiorcy_kod.addFocusListener(EventLoaderJTextfield.odbiorcaEvent(this));
		budowy_kod.addFocusListener(EventLoaderJTextfield.budowaEvent(this));
		obiekty_kod.addFocusListener(EventLoaderJTextfield.obiektEvent(this));
		elementy_kod.addFocusListener(EventLoaderJTextfield.elementEvent(this));

		add(odbiorcy_kod);
		add(budowy_kod);
		add(obiekty_kod);
		add(elementy_kod);
	}

	private void stworzEtykiety()
	{
		int ile_kontrolek = nazwy_label.length;
		for (int i = 0; i < ile_kontrolek; i++)
		{
			JLabel k_label = new JLabel(nazwy_label[i]);
			k_label.setSize(new Dimension(WIDTH_LABEL, HEIGHT));

			kontrolki_label.add(k_label);

			this.add(k_label);
		}

		button_potwierdz = new JButton("PotwierdŸ");
		button_potwierdz.setSize(WIDTH, HEIGHT);
		button_potwierdz.addActionListener(buttonPotwierdz());
		this.add(button_potwierdz);
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
					frame.add(getNewZamowienie());
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

	protected Zamowienie getNewZamowienie()
	{
		return new Zamowienie();
	}

	private void konfigurujKontrolkiNowe()
	{
		int y = -20;
		int x = 480;
		Dimension text_size = new Dimension(75, 25);

		odbiorca_nowy.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		budowa_nowy.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		obiekt_nowy.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		element_nowy.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);

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
		int y = -20;
		int x = 580;
		Dimension text_size = new Dimension(75, 25);

		odbiorca_edytuj.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		budowa_edytuj.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		obiekt_edytuj.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		element_edytuj.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);

		odbiorca_edytuj.addActionListener(EventLoaderEdytuj.odbiorcaEvent(this));
		budowa_edytuj.addActionListener(EventLoaderEdytuj.budowaEvent(this));
		obiekt_edytuj.addActionListener(EventLoaderEdytuj.obiektEvent(this));
		element_edytuj.addActionListener(EventLoaderEdytuj.elementEvent(this));

		add(odbiorca_edytuj);
		add(budowa_edytuj);
		add(obiekt_edytuj);
		add(element_edytuj);
	}

	private void konfigurujKontrolki()
	{
		int y = -20;
		int x = 20;

		int ile_kontrolek = kontrolki_label.size();
		for (int i = 0; i < ile_kontrolek; i++)
		{
			y += HEIGHT + 10;
			kontrolki_label.get(i).setLocation(x, y);

		}

		button_potwierdz.setLocation(x + WIDTH_LABEL, y + HEIGHT * 2);
	}
}
