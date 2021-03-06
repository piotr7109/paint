package ekrany;

import java.awt.AWTException;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dane.ZamowienieDane;
import dodatki.Tools;
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
	
	private JButton odbiorca_usun = new JButton("Usuń");
	private JButton budowa_usun = new JButton("Usuń");
	private JButton obiekt_usun = new JButton("Usuń");
	private JButton element_usun = new JButton("Usuń");

	private JButton lista_produkcyjna = new JButton("Lista produkcyjna");
	private JButton lista_wysylkowa = new JButton("Lista wysyłkowa");
	private JButton metki = new JButton("Metki");
	private JButton wagi = new JButton("Wagi");
	private JButton optymalizacja = new JButton("Optymalizacja");

	private JButton rysowanie = new JButton("Dodaj Figurę");

	private JTextField data_utworzenia_label = new JTextField("Data utworzenia:");
	private JTextField waga_label = new JTextField("Waga zaprogramowana:");
	private JTextField waga_rzeczywista_label = new JTextField("Waga rzeczywista:");

	public JTextField data_utworzenia = new JTextField("n/a");
	public JTextField waga = new JTextField("n/a");
	public JTextField waga_rzeczywista = new JTextField("n/a");

	public Formularz()
	{
		this.setPreferredSize(Tools.getDimension((int) (Tools.MAX_WIDTH / 20 * 13), (int) (Tools.MAX_HEIGHT / 20 * 13)));
		this.setLayout(null);

		konfigurujEtykiety();
		konfigurujKontrolkiCombo();
		konfigurujKontrolkiKodow();
		konfigurujKontrolkiNowe();
		konfigurujKontrolkiEdytuj();
		konfigurujKontrolkiUsun();
		konfigurujDodatkoweButtony();
		konfigurujKontrolkiWydruki();
		konfigurujDodatkoweDane();

		EventLoaderJComboBox.wczytajOdbiorcow(this);

	}

	private void konfigurujDodatkoweDane()
	{
		Tools.converToDisabledJTextField(data_utworzenia_label);
		Tools.converToDisabledJTextField(waga_label);
		Tools.converToDisabledJTextField(waga_rzeczywista_label);
		Tools.converToDisabledJTextField(data_utworzenia);
		Tools.converToDisabledJTextField(waga);
		Tools.converToDisabledJTextField(waga_rzeczywista);

		add(data_utworzenia_label);
		add(waga_label);
		//add(waga_rzeczywista_label);

		add(data_utworzenia);
		add(waga);
		//add(waga_rzeczywista);

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
		optymalizacja.addActionListener(EventLoaderWydruki.drukujOptymalizacje(this));

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
	
	private void konfigurujKontrolkiUsun()
	{

		odbiorca_usun.addActionListener(EventLoaderUsun.odbiorcaEvent(this));
		budowa_usun.addActionListener(EventLoaderUsun.budowaEvent(this));
		obiekt_usun.addActionListener(EventLoaderUsun.obiektEvent(this));
		element_usun.addActionListener(EventLoaderUsun.elementEvent(this));

		add(odbiorca_usun);
		add(budowa_usun);
		add(obiekt_usun);
		add(element_usun);
	}

	protected ActionListener buttonPotwierdz()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{

				if (elementy_combo.getItemCount() > 0)
				{
					JFrame frame = new JFrame("Zamówienie");
					frame.setResizable(false);
					frame.setVisible(true);

					ZamowienieDane.odbiorca = (Odbiorca) odbiorcy_combo.getSelectedItem();
					ZamowienieDane.budowa = (Budowa) budowy_combo.getSelectedItem();
					ZamowienieDane.obiekt = (Obiekt) obiekty_combo.getSelectedItem();
					ZamowienieDane.element = (Element) elementy_combo.getSelectedItem();
					try
					{
						frame.add(new Zamowienie(frame));
					}
					catch (AWTException e)
					{
						e.printStackTrace();
					}

					frame.pack();
				}
				else
				{
					Tools.showSimpleMessage("Brak obiektu!", JOptionPane.ERROR_MESSAGE);
				}

			}
		};
	}

	///////////////////////////////////////// RESIZE
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		float scale = ((float) (this.getWidth()) / 1000);
		Tools.scale = scale;
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
			((JComponent) comp).setFont(new Font("", 0, Tools.rescale(12)));
		}
		System.out.println("REPAINT END");

	}

	private void resizeDodatkoweButtony()
	{
		Dimension size = new Dimension(Tools.rescale(150), Tools.rescale(50));
		int y = this.getHeight() - size.height;
		int x = this.getWidth() - size.width;

		rysowanie.setBounds(x, y, size.width, size.height);
	}

	private void resizeWydruki()
	{
		Dimension size = new Dimension(Tools.rescale(150), Tools.rescale(50));
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
		int x = Tools.rescale(480);
		Dimension text_size = new Dimension(Tools.rescale(85), Tools.rescale(25));

		odbiorca_nowy.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		budowa_nowy.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		obiekt_nowy.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		element_nowy.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);

		x = Tools.rescale(580);
		y = -20;

		odbiorca_edytuj.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		budowa_edytuj.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		obiekt_edytuj.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		element_edytuj.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		
		x = Tools.rescale(680);
		y = -20;
		
		odbiorca_usun.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		budowa_usun.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		obiekt_usun.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		element_usun.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
	}

	private void resizeLabel()
	{
		int y = -20;
		int x = 20;

		int ile_kontrolek = kontrolki_label.size();
		for (int i = 0; i < ile_kontrolek; i++)
		{
			y += HEIGHT + 10;
			kontrolki_label.get(i).setLocation(Tools.rescale(x), Tools.rescale(y));
			kontrolki_label.get(i).setSize(new Dimension(Tools.rescale(WIDTH_LABEL), Tools.rescale(HEIGHT)));
		}

		button_potwierdz.setLocation(Tools.rescale(x + WIDTH_LABEL), Tools.rescale(y + HEIGHT * 2));
		button_potwierdz.setSize(Tools.rescale(WIDTH), (Tools.rescale(HEIGHT)));

		x = 20;
		y = 300;
		data_utworzenia_label.setSize(Tools.rescale(WIDTH), (Tools.rescale(HEIGHT)));
		data_utworzenia_label.setLocation(Tools.rescale(x), Tools.rescale(y += HEIGHT + 10));
		waga_label.setSize(Tools.rescale(WIDTH), (Tools.rescale(HEIGHT)));
		waga_label.setLocation(Tools.rescale(x), Tools.rescale(y += HEIGHT + 10));
		waga_rzeczywista_label.setSize(Tools.rescale(WIDTH), (Tools.rescale(HEIGHT)));
		waga_rzeczywista_label.setLocation(Tools.rescale(x), Tools.rescale(y += HEIGHT + 10));

		x += 150;
		y = 300;

		data_utworzenia.setSize(Tools.rescale(WIDTH*2), (Tools.rescale(HEIGHT)));
		data_utworzenia.setLocation(Tools.rescale(x), Tools.rescale(y += HEIGHT + 10));
		waga.setSize(Tools.rescale(WIDTH*2), (Tools.rescale(HEIGHT)));
		waga.setLocation(Tools.rescale(x), Tools.rescale(y += HEIGHT + 10));
		//waga_rzeczywista.setSize(Tools.rescale(WIDTH*2), (Tools.rescale(HEIGHT)));
		//waga_rzeczywista.setLocation(Tools.rescale(x), Tools.rescale(y += HEIGHT + 10));
	}

	private void resizeTextField()
	{
		int x = Tools.rescale(440);
		int y = -20;
		Dimension text_size = new Dimension(Tools.rescale(35), Tools.rescale(25));
		odbiorcy_kod.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		budowy_kod.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		obiekty_kod.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
		elementy_kod.setBounds(x, Tools.rescale(y += HEIGHT + 10), text_size.width, text_size.height);
	}

	private void resizeCombo()
	{
		int x = Tools.rescale(120);
		int y = -20;
		Dimension combo_size = new Dimension((int) (300 * Tools.scale), (int) (25 * Tools.scale));
		odbiorcy_combo.setSize(combo_size);
		budowy_combo.setSize(combo_size);
		obiekty_combo.setSize(combo_size);
		elementy_combo.setSize(combo_size);

		odbiorcy_combo.setLocation(x, Tools.rescale(y += HEIGHT + 10));
		budowy_combo.setLocation(x, Tools.rescale(y += HEIGHT + 10));
		obiekty_combo.setLocation(x, Tools.rescale(y += HEIGHT + 10));
		elementy_combo.setLocation(x, Tools.rescale(y += HEIGHT + 10));
	}
}
