package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ekrany.Formularz;
import modules.zamowienie.kody.Kod;
import modules.zamowienie.miejscowosci.Miejscowosc;
import modules.zamowienie.nazwy.Nazwa;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.placowki.Placowka;

public class EventLoaderJComboBox
{
	public static void wczytajKody(final Formularz form)
	{
		form.kody = DBLoader.getKody();
		form.kody_combo.removeAllItems();
		int kody_size = form.kody.size();
		for (int i = 0; i < kody_size; i++)
		{
			Kod kod = (Kod) form.kody.get(i);
			form.kody_combo.addItem(kod);
		}

	}

	public static ActionListener kodyEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (form.kody_combo.getSelectedItem() != null)
				{
					Kod kod = (Kod) form.kody_combo.getSelectedItem();
					form.kody_kod.setText(kod.getKod() + "");
					wczytajNazwy(kod.getId(), form);
				}

			}
		};
	}

	public static void wczytajNazwy(int id_parent, final Formularz form)
	{
		form.nazwy = DBLoader.getNazwy(id_parent);
		int size = form.nazwy.size();
		form.nazwy_combo.removeAllItems();
		for (int i = 0; i < size; i++)
		{
			Nazwa item = (Nazwa) form.nazwy.get(i);
			form.nazwy_combo.addItem(item);
		}

	}

	public static ActionListener nazwyEvent(final Formularz form)
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (form.nazwy_combo.getSelectedItem() != null)
				{
					Nazwa item = (Nazwa) form.nazwy_combo.getSelectedItem();
					form.nazwy_kod.setText(item.getKod() + "");
					wczytajMiejscowosci(item.getId(), form);
				}
			}
		};
	}

	public static void wczytajMiejscowosci(int id_parent, Formularz form)
	{
		form.miejscowosci = DBLoader.getMiejscowosci(id_parent);
		form.miejscowosci_combo.removeAllItems();
		int size = form.miejscowosci.size();
		for (int i = 0; i < size; i++)
		{
			Miejscowosc item = (Miejscowosc) form.miejscowosci.get(i);
			form.miejscowosci_combo.addItem(item);
		}

	}

	public static ActionListener miejscowosciEvent(final Formularz form)
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (form.miejscowosci_combo.getSelectedItem() != null)
				{
					Miejscowosc item = (Miejscowosc) form.miejscowosci_combo.getSelectedItem();
					form.miejscowosci_kod.setText(item.getKod() + "");
					wczytajPlacowki(item.getId(), form);
				}
			}
		};
	}

	public static void wczytajPlacowki(int id_parent, Formularz form)
	{
		form.placowki = DBLoader.getPlacowki(id_parent);
		form.placowki_combo.removeAllItems();
		int size = form.placowki.size();
		for (int i = 0; i < size; i++)
		{
			Placowka item = (Placowka) form.placowki.get(i);
			form.placowki_combo.addItem(item);
		}

	}

	public static ActionListener placowkiEvent(final Formularz form)
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (form.placowki_combo.getSelectedItem() != null)
				{
					Placowka item = (Placowka) form.placowki_combo.getSelectedItem();
					form.placowki_kod.setText(item.getKod() + "");
					wczytajObiekty(item.getId(), form);
				}
			}
		};
	}

	public static void wczytajObiekty(int id_parent, Formularz form)
	{
		form.obiekty = DBLoader.getObiekty(id_parent);
		form.obiekty_combo.removeAllItems();
		int size = form.obiekty.size();
		for (int i = 0; i < size; i++)
		{
			Obiekt item = (Obiekt) form.obiekty.get(i);
			form.obiekty_combo.addItem(item);
		}

	}

	public static ActionListener obiektyEvent(final Formularz form)
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (form.obiekty_combo.getSelectedItem() != null)
				{
					Obiekt item = (Obiekt) form.obiekty_combo.getSelectedItem();
					form.obiekty_kod.setText(item.getKod() + "");
				}
			}
		};
	}
}
