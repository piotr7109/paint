package ekrany.zamowienie;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import org.apache.commons.lang.NumberUtils;

import com.mysql.jdbc.StringUtils;

import dane.FiguraKontrolki;
import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dodatki.Tools;
import dodatki.FocusListeners;
import ekrany.Zamowienie;
import ekrany.zamowienie.ramki.RamkaCzesci;
import ekrany.zamowienie.ramki.RamkaFigura;
import ekrany.zamowienie.ramki.RamkaFiguraAtrapa;
import ekrany.zamowienie.ramki.RamkaIlosc;
import ekrany.zamowienie.ramki.RamkaUwagi;
import ekrany.zamowienie.ramki.RamkaWymiarCM;
import ekrany.zamowienie.ramki.RamkaWymiarMM;
import modules.czesci.Czesc;
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
		default_figura.setCzesciAtrapy();
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
			if (f_kontrolki.index > 0)
			{
				ZamowienieDane.figury.get(f_kontrolki.index).uwagi = ZamowienieDane.figury.get(f_kontrolki.index - 1).uwagi;
			}
		}

		int index = f_kontrolki.index;
		int x = 20;
		int y;
		JTextField liczba = new JTextField(index + 1 + "");
		JTextField pozycja = new JTextField();
		JTextField ilosc_sztuk = new JTextField();
		JTextField srednica = new JTextField();
		JTextField fig = new JTextField();
		JTextField ilosc_paczek = new JTextField();
		JTextField maszyna = new JTextField();
		// JTextField mat?? = new JTextField(fig_zam.mat??);
		JTextField sworzen = new JTextField();

		if (ZamowienieDane.figury.get(index) != null)
		{
			FiguraZamowienie f_zamowienie = ZamowienieDane.figury.get(index);
			pozycja.setText("" + f_zamowienie.pozycja);
			ilosc_sztuk.setText("" + f_zamowienie.ilosc_sztuk);
			srednica.setText("" + f_zamowienie.srednica);
			fig.setText("" + f_zamowienie.fig);
			ilosc_paczek.setText("" + f_zamowienie.ilosc_paczek);
			maszyna.setText("" + f_zamowienie.maszyna);
			// ma??.setText(""+f_zamowienie.ma??);
			sworzen.setText("" + f_zamowienie.sworzen);

		}

		if (index > 0)
		{
			y = ZamowienieDane.f_kontrolki.get(index - 1).pozycja.getY() + rescale(25);

		}
		else
		{
			y = rescale(160 + index * 25);
		}
		int el_width = 30;

		liczba.setBounds(rescale(x), y, rescale(el_width), rescale(20));
		pozycja.setBounds(rescale(x += 50), y, rescale(el_width), rescale(20));
		ilosc_sztuk.setBounds(rescale(x += 50), y, rescale(el_width), rescale(20));
		srednica.setBounds(rescale(x += 50), y, rescale(el_width), rescale(20));
		fig.setBounds(rescale(x += 50), y, rescale(el_width), rescale(20));
		ilosc_paczek.setBounds(rescale(x += 50), y, rescale(el_width), rescale(20));
		maszyna.setBounds(rescale(x += 50), y, rescale(el_width), rescale(20));
		// mat??.setBounds(x += 50, y, el_width, 20);
		rescale(x += 50);
		sworzen.setBounds(rescale(x += 50), y, rescale(el_width), rescale(20));

		fig.getDocument().addDocumentListener(zmianaFigury(index, panel));

		pozycja.addFocusListener(wyborFigury(index, panel, pozycja));
		ilosc_sztuk.addFocusListener(wyborFigury(index, panel, ilosc_sztuk));
		srednica.addFocusListener(wyborFigury(index, panel, srednica));
		fig.addFocusListener(wyborFigury(index, panel, fig));
		ilosc_paczek.addFocusListener(wyborFigury(index, panel, ilosc_paczek));
		maszyna.addFocusListener(wyborFigury(index, panel, maszyna));
		// mat??.addFocusListener(wyborFigury(index, panel, mat??));
		sworzen.addFocusListener(wyborFigury(index, panel, sworzen));

		pozycja.addKeyListener(panel);
		ilosc_sztuk.addKeyListener(panel);
		srednica.addKeyListener(panel);
		fig.addKeyListener(panel);
		ilosc_paczek.addKeyListener(panel);
		maszyna.addKeyListener(panel);
		// mat??.addKeyListener(panel);
		sworzen.addKeyListener(panel);

		Tools.setKoloryNieaktywny(pozycja);
		Tools.setKoloryNieaktywny(ilosc_sztuk);
		Tools.setKoloryNieaktywny(srednica);
		Tools.setKoloryNieaktywny(fig);
		Tools.setKoloryNieaktywny(ilosc_paczek);
		Tools.setKoloryNieaktywny(maszyna);
		// CONST.setKolory(mat??);
		Tools.setKoloryNieaktywny(sworzen);
		liczba.setBackground(Color.BLACK);
		liczba.setForeground(Color.GREEN);
		liczba.setBorder(BorderFactory.createEmptyBorder());

		// nieedytowalne
		liczba.setEditable(false);
		// sworzen.setEditable(false);

		srednica.addFocusListener(FocusListeners.srednicaFocusListener(index, srednica, sworzen));
		ilosc_sztuk.addFocusListener(iloscSztukFocusListener(index, panel, ilosc_sztuk));

		panel.add(pozycja);
		panel.add(ilosc_sztuk);
		panel.add(srednica);
		panel.add(fig);
		panel.add(ilosc_paczek);
		panel.add(maszyna);
		// RamkaWymiarMM.panel.add(mat??);
		panel.add(sworzen);
		panel.add(liczba);

		x = 20;
		y += 20;

		f_kontrolki.pozycja = pozycja;
		f_kontrolki.ilosc_sztuk = ilosc_sztuk;
		f_kontrolki.srednica = srednica;
		f_kontrolki.fig = fig;
		f_kontrolki.ilosc_paczek = ilosc_paczek;
		f_kontrolki.maszyna = maszyna;
		// f_kontrolki.ma?? = ma??;
		f_kontrolki.sworzen = sworzen;
		f_kontrolki.liczba = liczba;

		panel.z_x++;

		ZamowienieDane.f_kontrolki.add(f_kontrolki);
		ZamowienieDane.f_kontrolki.get(index).setKontrolki();
		RamkaWymiarMM.przewinElementy(panel, liczba);

	}

	public static void aktualizujDane(final int index, final Zamowienie panel)
	{
		FiguraKontrolki f_kontrolki = ZamowienieDane.f_kontrolki.get(index);

		FiguraZamowienie f_zam = ZamowienieDane.figury.get(index);

		f_zam.fig = Integer.parseInt(f_kontrolki.fig.getText());
		f_zam.ilosc_sztuk = Integer.parseInt(f_kontrolki.ilosc_sztuk.getText());
		f_zam.ilosc_paczek = Integer.parseInt(f_kontrolki.ilosc_paczek.getText());
		f_zam.maszyna = Integer.parseInt(f_kontrolki.maszyna.getText());
		f_zam.pozycja = f_kontrolki.pozycja.getText();
		f_zam.srednica = Integer.parseInt(f_kontrolki.srednica.getText());
		f_zam.sworzen = Integer.parseInt(f_kontrolki.sworzen.getText());
		RamkaUwagi.ramkaUwagiUpdate(panel);
		RamkaIlosc.update(panel);

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
					usunFigure(index, panel);
				}

			}

			@Override
			public void focusGained(FocusEvent e)
			{
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
		if (!czy_focus_przejety)
		{
			panel.z_x--;
			ArrayList<FiguraKontrolki> f_kontrolki = ZamowienieDane.f_kontrolki;
			f_kontrolki.get(f_kontrolki.size() - 1).kontrolki[panel.z_y].grabFocus();
		}
	}

	public static void usunFigure(int index, Zamowienie panel)
	{
		if (ZamowienieDane.figury.size() > 1)
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
		}
		// ZamowienieDane.czesc_kontrolki.remove(index);

	}

	private static FocusListener wyborFigury(final int index, final Zamowienie panel, final JTextField text_field)
	{
		return new FocusListener()
		{

			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e)
			{
				panel.poprzedni_text_field = text_field;
				if (!NumberUtils.isNumber(text_field.getText()))
				{
					text_field.setText(0 + "");
				}

				aktualizujDane(index, panel);
				Tools.setKoloryNieaktywny(text_field);
			}

			@Override
			public void focusGained(FocusEvent e)
			{
				Tools.setKoloryAktywny(text_field);
				panel.figura = ZamowienieDane.figury.get(index).figura;
				// panel.figura_atrapa = new
				// Figura(ZamowienieDane.figury.get(index).figura);

				RamkaCzesci.rysujKontrolki(panel, index);
				RamkaFiguraAtrapa.rysujKontrolki(panel, index);
				RamkaWymiarCM.ustawKontrolki(index, panel);

			}
		};
	}

	private static DocumentListener zmianaFigury(final int index, final Zamowienie panel)
	{
		return new DocumentListener()
		{

			@Override
			public void removeUpdate(DocumentEvent e)
			{
			}

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				String kod = "";
				try
				{
					kod = e.getDocument().getText(0, e.getDocument().getLength());
				}
				catch (BadLocationException e1)
				{
					e1.printStackTrace();
				}
				FiguraZamowienie f_zamowienie = ZamowienieDane.figury.get(index);
				if (Integer.parseInt(kod) != f_zamowienie.figura.getKod())
				{

					FiguraFactory f_factory = new FiguraFactory();
					Figura figura = null;
					if (!kod.equals(""))
					{
						RamkaFigura.skalaReset();
						figura = f_factory.getFiguraByKod(Integer.parseInt(kod));

					}

					if (figura != null)
					{
						figura.setCzesci();
						figura.setCzesciAtrapy();

						figura.wyczyscDlugoscCzesci();
						f_zamowienie.figura = figura;
						panel.figura = f_zamowienie.figura;

					}
					else
					{
						// ERROR nie ma w bazie
					}
				}
				RamkaFiguraAtrapa.rysujKontrolki(panel, index);
				RamkaCzesci.rysujKontrolki(panel, index);
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
			}
		};
	}

	private static int rescale(int number)
	{
		return (int) (number * Tools.scale);
	}

	public static void rescale()
	{
		for (FiguraKontrolki f_kontrolki : ZamowienieDane.f_kontrolki)
		{
			for (JTextField text : f_kontrolki.kontrolki)
			{
				text.setFont(new Font("", 0, rescale(12)));
			}
		}
	}
}
