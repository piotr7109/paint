package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.ekrany_nowe.NowyKod;
import ekrany.formularz.ekrany_nowe.NowyMiejscowosc;
import ekrany.formularz.ekrany_nowe.NowyNazwa;
import ekrany.formularz.ekrany_nowe.NowyObiekt;
import ekrany.formularz.ekrany_nowe.NowyPlacowka;
import modules.zamowienie.kody.Kod;
import modules.zamowienie.miejscowosci.Miejscowosc;
import modules.zamowienie.nazwy.Nazwa;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.placowki.Placowka;

public class EventLoaderNowe
{
	private static JFrame createNewWindow()
	{
		JFrame frame = new JFrame("Dodaj nowy");
		frame.setResizable(false);

		frame.setVisible(true);
		return frame;
	}

	public static ActionListener kodEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = createNewWindow();
				int kod;
				try
				{
					kod = ((Kod) form.kody_combo.getItemAt(form.kody_combo.getItemCount() - 1)).getKod() + 1;
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				int id_parent = 0;
				frame.add(new NowyKod(kod, frame, id_parent, form));
				frame.pack();

			}
		};
	}

	public static ActionListener nazwaEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = createNewWindow();
				int kod;
				try
				{
					kod = ((Nazwa) form.nazwy_combo.getItemAt(form.nazwy_combo.getItemCount() - 1)).getKod() + 1;
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				int id_parent = ((Kod) form.kody_combo.getSelectedItem()).getId();
				frame.add(new NowyNazwa(kod, frame, id_parent, form));
				frame.pack();
			}
		};
	}

	public static ActionListener miejscowoscEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = createNewWindow();
				int kod;
				try
				{
					kod = ((Miejscowosc) form.miejscowosci_combo.getItemAt(form.miejscowosci_combo.getItemCount() - 1)).getKod() + 1;
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				int id_parent = ((Nazwa) form.nazwy_combo.getSelectedItem()).getId();
				frame.add(new NowyMiejscowosc(kod, frame, id_parent, form));
				frame.pack();
			}
		};
	}

	public static ActionListener placowkaEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = createNewWindow();
				int kod;
				try
				{
					kod = ((Placowka) form.placowki_combo.getItemAt(form.placowki_combo.getItemCount() - 1)).getKod() + 1;
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				int id_parent = ((Miejscowosc) form.miejscowosci_combo.getSelectedItem()).getId();
				frame.add(new NowyPlacowka(kod, frame, id_parent, form));
				frame.pack();
			}
		};
	}

	public static ActionListener obiektEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = createNewWindow();
				int kod;
				try
				{
					kod = ((Obiekt) form.obiekty_combo.getItemAt(form.obiekty_combo.getItemCount() - 1)).getKod() + 1;
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				int id_parent = ((Placowka) form.placowki_combo.getSelectedItem()).getId();
				frame.add(new NowyObiekt(kod, frame, id_parent, form));
				frame.pack();
			}
		};
	}
}
