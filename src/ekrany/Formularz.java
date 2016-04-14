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


import dodatki.CONST;
import ekrany.formularz.*;
import modules.zamowienie.kody.Kod;
import modules.zamowienie.miejscowosci.Miejscowosc;
import modules.zamowienie.nazwy.Nazwa;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.placowki.Placowka;

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
	private String nazwy_label[] = { "Kod", "Nazwa", "Miejscowoœæ", "Placówka", "Obiekt wysy³any", "Uwagi" };

	private JButton button_potwierdz;

	public JComboBox<Kod> kody_combo = new JComboBox<Kod>();
	public JComboBox<Nazwa> nazwy_combo = new JComboBox<Nazwa>();
	public JComboBox<Miejscowosc> miejscowosci_combo = new JComboBox<Miejscowosc>();
	public JComboBox<Placowka> placowki_combo = new JComboBox<Placowka>();
	public JComboBox<Obiekt> obiekty_combo = new JComboBox<Obiekt>();

	public ArrayList<Kod> kody = new ArrayList<Kod>();
	public ArrayList<Nazwa> nazwy = new ArrayList<Nazwa>();
	public ArrayList<Miejscowosc> miejscowosci = new ArrayList<Miejscowosc>();
	public ArrayList<Placowka> placowki = new ArrayList<Placowka>();
	public ArrayList<Obiekt> obiekty = new ArrayList<Obiekt>();

	public JTextField kody_kod = new JTextField();
	public JTextField nazwy_kod = new JTextField();
	public JTextField miejscowosci_kod = new JTextField();
	public JTextField placowki_kod = new JTextField();
	public JTextField obiekty_kod = new JTextField();
	
	private JButton kod_nowy = new JButton("Nowy");
	private JButton nazwa_nowy = new JButton("Nowy");
	private JButton miejscowosc_nowy = new JButton("Nowy");
	private JButton placowka_nowy = new JButton("Nowy");
	private JButton obiekt_nowy = new JButton("Nowy");

	public Formularz()
	{
		
		this.setLayout(null);
		
		this.stworzEtykiety();
		this.stworzKontrolki();
		this.ustawKontrolkiKodow();
		this.konfigurujKontrolki();
		this.konfigurujKontrolkiNowe();

		EventLoaderJComboBox.wczytajKody(this);

	}


	private void stworzKontrolki()
	{
		int x = 120;
		int y = -20;
		Dimension combo_size = new Dimension(300, 25);

		kody_combo.setSize(combo_size);
		nazwy_combo.setSize(combo_size);
		miejscowosci_combo.setSize(combo_size);
		placowki_combo.setSize(combo_size);
		obiekty_combo.setSize(combo_size);

		kody_combo.setLocation(x, y += HEIGHT + 10);
		nazwy_combo.setLocation(x, y += HEIGHT + 10);
		miejscowosci_combo.setLocation(x, y += HEIGHT + 10);
		placowki_combo.setLocation(x, y += HEIGHT + 10);
		obiekty_combo.setLocation(x, y += HEIGHT + 10);

		kody_combo.addActionListener(EventLoaderJComboBox.kodyEvent(this));
		nazwy_combo.addActionListener(EventLoaderJComboBox.nazwyEvent(this));
		miejscowosci_combo.addActionListener(EventLoaderJComboBox.miejscowosciEvent(this));
		placowki_combo.addActionListener(EventLoaderJComboBox.placowkiEvent(this));
		obiekty_combo.addActionListener(EventLoaderJComboBox.obiektyEvent(this));

		add(kody_combo);
		add(nazwy_combo);
		add(miejscowosci_combo);
		add(placowki_combo);
		add(obiekty_combo);

	}

	private void ustawKontrolkiKodow()
	{
		int x = 440;
		int y = -20;
		Dimension text_size = new Dimension(35, 25);
		kody_kod.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		nazwy_kod.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		miejscowosci_kod.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		placowki_kod.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		obiekty_kod.setBounds(x, y += HEIGHT + 10, text_size.width, text_size.height);
		
		kody_kod.addFocusListener(EventLoaderJTextfield.kodEvent(this));
		nazwy_kod.addFocusListener(EventLoaderJTextfield.nazwaEvent(this));
		miejscowosci_kod.addFocusListener(EventLoaderJTextfield.miejscowoscEvent(this));
		placowki_kod.addFocusListener(EventLoaderJTextfield.placowkaEvent(this));
		obiekty_kod.addFocusListener(EventLoaderJTextfield.obiektEvent(this));
		add(kody_kod);
		add(nazwy_kod);
		add(miejscowosci_kod);
		add(placowki_kod);
		add(obiekty_kod);
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
				
				if(obiekty_combo.getItemCount() > 0)
				{
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
		
		kod_nowy.setBounds(x,y += HEIGHT + 10, text_size.width, text_size.height);
		nazwa_nowy.setBounds(x,y += HEIGHT + 10, text_size.width, text_size.height);
		miejscowosc_nowy.setBounds(x,y += HEIGHT + 10, text_size.width, text_size.height);
		placowka_nowy.setBounds(x,y += HEIGHT + 10, text_size.width, text_size.height);
		obiekt_nowy.setBounds(x,y += HEIGHT + 10, text_size.width, text_size.height);
		
		kod_nowy.addActionListener(EventLoaderNowe.kodEvent(this));
		nazwa_nowy.addActionListener(EventLoaderNowe.nazwaEvent(this));
		miejscowosc_nowy.addActionListener(EventLoaderNowe.miejscowoscEvent(this));
		placowka_nowy.addActionListener(EventLoaderNowe.placowkaEvent(this));
		obiekt_nowy.addActionListener(EventLoaderNowe.obiektEvent(this));
		
		add(kod_nowy);
		add(nazwa_nowy);
		add(miejscowosc_nowy);
		add(placowka_nowy);
		add(obiekt_nowy);
	}

	private void konfigurujKontrolki()
	{
		int y = -20;
		int x = 20;

		int ile_kontrolek = kontrolki_label.size();
		for (int i = 0; i < ile_kontrolek; i++)
		{
			y +=  HEIGHT + 10;
			kontrolki_label.get(i).setLocation(x, y);

		}

		button_potwierdz.setLocation(x + WIDTH_LABEL, y + HEIGHT * 2);
	}
}
