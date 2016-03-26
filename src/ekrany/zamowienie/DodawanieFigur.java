package ekrany.zamowienie;

import java.awt.Color;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dane.FiguraKontrolki;
import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dodatki.CONST;
import dodatki.FocusListeners;
import ekrany.Zamowienie;

import modules.figury.Figura;
import modules.figury.FiguraFactory;

public class DodawanieFigur
{
	private static Figura default_figura;

	public static void setDefaultFigura()
	{

		FiguraFactory fig_factory = new FiguraFactory();
		default_figura = (Figura) fig_factory.getFiguraByKod(12);
		default_figura.setCzesci();
	}

	private static FiguraZamowienie getDefaultFiguraZamowienie()
	{
		FiguraZamowienie fig_zam = new FiguraZamowienie();
		fig_zam.figura = default_figura;
		fig_zam.fig = default_figura.getKod();
		return fig_zam;

	}

	public static void dodajFigure(Zamowienie panel, boolean default_figura)
	{
		FiguraKontrolki f_kontrolki = new FiguraKontrolki();
		f_kontrolki.index = ZamowienieDane.f_kontrolki.size();

		if (default_figura == true)
		{
			ZamowienieDane.figury.add(getDefaultFiguraZamowienie());
		}
		int index = f_kontrolki.index;
		int x = 20;
		int y;
		JTextField liczba = new JTextField(index + 1 + "");
		JTextField pozycja = new JTextField("0");
		JTextField ilosc_sztuk = new JTextField("1");
		JTextField srednica = new JTextField("6");
		JTextField fig = new JTextField(1 + "");
		JTextField kolor = new JTextField("0");
		JTextField material = new JTextField("6");
		// JTextField mat?? = new JTextField(fig_zam.mat??);
		JTextField sworzen = new JTextField(32 + "");
		if (ZamowienieDane.figury.get(index) != null)
		{
			FiguraZamowienie f_zamowienie = ZamowienieDane.figury.get(index);
			pozycja.setText("" + f_zamowienie.pozycja);
			ilosc_sztuk.setText("" + f_zamowienie.ilosc_sztuk);
			srednica.setText("" + f_zamowienie.srednica);
			fig.setText("" + f_zamowienie.fig);
			kolor.setText("" + f_zamowienie.kolor);
			material.setText("" + f_zamowienie.material);
			// ma??.setText(""+f_zamowienie.ma??);
			sworzen.setText("" + f_zamowienie.sworzen);

		}

		if (index > 0)
		{
			y = ZamowienieDane.f_kontrolki.get(index - 1).pozycja.getY() + 25;

		}
		else
		{
			y = 160 + index * 25;
		}

		int el_width = 30;

		liczba.setBounds(x, y, el_width, 20);
		pozycja.setBounds(x += 50, y, el_width, 20);
		ilosc_sztuk.setBounds(x += 50, y, el_width, 20);
		srednica.setBounds(x += 50, y, el_width, 20);
		fig.setBounds(x += 50, y, el_width, 20);
		kolor.setBounds(x += 50, y, el_width, 20);
		material.setBounds(x += 50, y, el_width, 20);
		// mat??.setBounds(x += 50, y, el_width, 20);
		x += 50;
		sworzen.setBounds(x += 50, y, el_width, 20);

		fig.addFocusListener(zmianaFigury(index, panel));

		pozycja.addFocusListener(wyborFigury(index, panel, pozycja));
		ilosc_sztuk.addFocusListener(wyborFigury(index, panel, ilosc_sztuk));
		srednica.addFocusListener(wyborFigury(index, panel, srednica));
		fig.addFocusListener(wyborFigury(index, panel, fig));
		kolor.addFocusListener(wyborFigury(index, panel, kolor));
		material.addFocusListener(wyborFigury(index, panel, material));
		// mat??.addFocusListener(wyborFigury(index, panel, mat??));
		sworzen.addFocusListener(wyborFigury(index, panel, sworzen));

		pozycja.addKeyListener(panel);
		ilosc_sztuk.addKeyListener(panel);
		srednica.addKeyListener(panel);
		fig.addKeyListener(panel);
		kolor.addKeyListener(panel);
		material.addKeyListener(panel);
		// mat??.addKeyListener(panel);
		sworzen.addKeyListener(panel);

		CONST.setKoloryNieaktywny(pozycja);
		CONST.setKoloryNieaktywny(ilosc_sztuk);
		CONST.setKoloryNieaktywny(srednica);
		CONST.setKoloryNieaktywny(fig);
		CONST.setKoloryNieaktywny(kolor);
		CONST.setKoloryNieaktywny(material);
		// CONST.setKolory(mat??);
		CONST.setKoloryNieaktywny(sworzen);
		liczba.setBackground(Color.BLACK);
		liczba.setForeground(Color.GREEN);
		liczba.setBorder(BorderFactory.createEmptyBorder());

		// nieedytowalne
		liczba.setEditable(false);
		sworzen.setEditable(false);

		srednica.addFocusListener(FocusListeners.srednicaFocusListener(index, srednica, sworzen));
		ilosc_sztuk.addFocusListener(iloscSztukFocusListener(index, panel, ilosc_sztuk));

		panel.add(pozycja);
		panel.add(ilosc_sztuk);
		panel.add(srednica);
		panel.add(fig);
		panel.add(kolor);
		panel.add(material);
		// RamkaWymiarMM.panel.add(mat??);
		panel.add(sworzen);
		panel.add(liczba);

		x = 20;
		y += 20;

		f_kontrolki.pozycja = pozycja;
		f_kontrolki.ilosc_sztuk = ilosc_sztuk;
		f_kontrolki.srednica = srednica;
		f_kontrolki.fig = fig;
		f_kontrolki.kolor = kolor;
		f_kontrolki.material = material;
		// f_kontrolki.ma?? = ma??;
		f_kontrolki.sworzen = sworzen;
		f_kontrolki.liczba = liczba;

		panel.z_x++;

		ZamowienieDane.f_kontrolki.add(f_kontrolki);
		ZamowienieDane.f_kontrolki.get(index).setKontrolki();

	}

	private static void aktualizujDane(final int index)
	{
		FiguraKontrolki f_kontrolki = ZamowienieDane.f_kontrolki.get(index);

		FiguraZamowienie f_zam = ZamowienieDane.figury.get(index);

		f_zam.fig = Integer.parseInt(f_kontrolki.fig.getText());
		f_zam.ilosc_sztuk = Integer.parseInt(f_kontrolki.ilosc_sztuk.getText());
		f_zam.kolor = f_kontrolki.kolor.getText();
		f_zam.material = Integer.parseInt(f_kontrolki.material.getText());
		f_zam.pozycja = f_kontrolki.pozycja.getText();
		f_zam.srednica = Integer.parseInt(f_kontrolki.srednica.getText());
		f_zam.sworzen = Integer.parseInt(f_kontrolki.sworzen.getText());

	}

	private static FocusListener iloscSztukFocusListener(final int index, final Zamowienie panel, final JTextField text_field)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent e)
			{
				if (text_field.getText().equals("") || text_field.getText().equals("0"))
				{
					// usunFigure(index, panel);
				}

			}

			@Override
			public void focusGained(FocusEvent e)
			{
				// TODO Auto-generated method stub

			}
		};
	}

	public static void dodajFigury(Zamowienie panel)
	{
		int ile_figur = ZamowienieDane.figury.size();
		boolean czy_focus_przejety = false;
		for (int i = 0; i < ile_figur; i++)
		{
			dodajFigure(panel, false);
			panel.c_x = panel.c_y = 0;
			panel.z_x--;
			ArrayList<FiguraKontrolki> f_kontrolki = ZamowienieDane.f_kontrolki;
			if (f_kontrolki.get(f_kontrolki.size() - 1).index == panel.z_x)
			{
				f_kontrolki.get(f_kontrolki.size() - 1).kontrolki[panel.z_y].grabFocus();
				czy_focus_przejety = true;
			}
		}
		if(!czy_focus_przejety)
		{
			panel.z_x--;
			ArrayList<FiguraKontrolki> f_kontrolki = ZamowienieDane.f_kontrolki;
			f_kontrolki.get(f_kontrolki.size() - 1).kontrolki[panel.z_y].grabFocus();
		}
	}

	public static void usunFigure(int index, Zamowienie panel)
	{

		for (FiguraKontrolki fig : ZamowienieDane.f_kontrolki)
		{
			for (JTextField text_field : fig.kontrolki)
			{
				for (FocusListener focus_listener : text_field.getFocusListeners())
				{
					text_field.removeFocusListener(focus_listener);
				}
				panel.remove(text_field);
			}
		}
		ZamowienieDane.f_kontrolki.clear();
		ZamowienieDane.figury.remove(index);
		panel.repaint();

		dodajFigury(panel);

		// ZamowienieDane.czesc_kontrolki.remove(index);

	}

	private static FocusListener wyborFigury(final int index, final Zamowienie panel, final JTextField text_field)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent e)
			{
				panel.poprzedni_text_field = text_field;
				if (text_field.getText().equals(""))
				{
					text_field.setText(0 + "");
				}

				aktualizujDane(index);
				CONST.setKoloryNieaktywny(text_field);
			}

			@Override
			public void focusGained(FocusEvent e)
			{
				CONST.setKoloryAktywny(text_field);
				panel.figura = ZamowienieDane.figury.get(index).figura;

				RamkaCzesci.rysujKontrolki(panel, index);
				RamkaWymiarCM.ustawKontrolki(index, panel);

			}
		};
	}

	private static FocusListener zmianaFigury(final int index, Zamowienie panel)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent arg0)
			{
				FiguraKontrolki f_kontrolki = ZamowienieDane.f_kontrolki.get(index);
				FiguraZamowienie f_zamowienie = ZamowienieDane.figury.get(index);
				if (Integer.parseInt(f_kontrolki.fig.getText()) != f_zamowienie.figura.getKod())
				{

					FiguraFactory f_factory = new FiguraFactory();
					Figura figura = null;
					if (!f_kontrolki.fig.getText().equals(""))
					{
						figura = f_factory.getFiguraByKod(Integer.parseInt(f_kontrolki.fig.getText()));
					}

					if (figura != null)
					{
						figura.setCzesci();
						figura.getCzesci();
						f_zamowienie.figura = figura;
					}
					else
					{
						// ERROR nie ma w bazie
					}
				}

			}

			@Override
			public void focusGained(FocusEvent arg0)
			{
			}
		};
	}
}
