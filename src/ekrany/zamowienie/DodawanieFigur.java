package ekrany.zamowienie;

import java.awt.Color;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import dane.FiguraKontrolki;
import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dodatki.CONST;
import ekrany.Zamowienie;

import modules.figury.Figura;
import modules.figury.FiguraFactory;

public class DodawanieFigur
{
	private static Figura default_figura;

	public static void setDefaultFigura()
	{

		FiguraFactory fig_factory = new FiguraFactory();
		default_figura = (Figura) fig_factory.getFiguraByKod(1);
		default_figura.setCzesci();
	}

	private static FiguraZamowienie getDefaultFiguraZamowienie()
	{
		FiguraZamowienie fig_zam = new FiguraZamowienie();
		fig_zam.figura = default_figura;
		fig_zam.fig = default_figura.getKod();
		return fig_zam;

	}

	public static void dodajFigure(Zamowienie panel)
	{
		ZamowienieDane.figury.add(getDefaultFiguraZamowienie());

		int index = ZamowienieDane.f_kontrolki.size();
		int x = 20;
		int y;
		if (index > 0)
		{
			y = ZamowienieDane.f_kontrolki.get(index - 1).pozycja.getY() + 25;
		}
		else
		{
			y = 160 + index * 25;
		}

		int el_width = 30;

		JTextField liczba = new JTextField(index + 1 + "");
		JTextField pozycja = new JTextField("");
		JTextField ilosc_sztuk = new JTextField("");
		JTextField srednica = new JTextField("");
		JTextField fig = new JTextField(1 + "");
		JTextField kolor = new JTextField("");
		JTextField material = new JTextField("");
		// JTextField mat?? = new JTextField(fig_zam.mat??);
		JTextField sworzen = new JTextField("");

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

		FiguraKontrolki f_kontrolki = new FiguraKontrolki();

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

	private static FocusListener wyborFigury(final int index, final Zamowienie panel, final JTextField text_field)
	{
		return new FocusListener()
		{

			@Override
			public void focusLost(FocusEvent e)
			{
				CONST.setKoloryNieaktywny(text_field);
			}

			@Override
			public void focusGained(FocusEvent e)
			{
				CONST.setKoloryAktywny(text_field);
				panel.figura = ZamowienieDane.figury.get(index).figura;

				RamkaCzesci.rysujKontrolki(panel, index);

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
