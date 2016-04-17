package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ekrany.Formularz;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.odbiorcy.Odbiorca;

public class EventLoaderJComboBox
{
	public static void wczytajOdbiorcow(final Formularz form)
	{
		form.odbiorcy = DBLoader.getOdbiorcy();
		form.odbiorcy_combo.removeAllItems();
		int odbiorcy_size = form.odbiorcy.size();
		for (int i = 0; i < odbiorcy_size; i++)
		{
			Odbiorca kod = (Odbiorca) form.odbiorcy.get(i);
			form.odbiorcy_combo.addItem(kod);
		}

	}

	public static ActionListener odbiorcyEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (form.odbiorcy_combo.getSelectedItem() != null)
				{
					Odbiorca item = (Odbiorca) form.odbiorcy_combo.getSelectedItem();
					form.odbiorcy_kod.setText(item.getKod() + "");
					wczytajBudowy(item.getId(), form);
				}

			}
		};
	}

	public static void wczytajBudowy(int id_parent, final Formularz form)
	{
		form.budowy = DBLoader.getBudowy(id_parent);
		int size = form.budowy.size();
		form.budowy_combo.removeAllItems();
		for (int i = 0; i < size; i++)
		{
			Budowa item = (Budowa) form.budowy.get(i);
			form.budowy_combo.addItem(item);
		}

	}

	public static ActionListener budowyEvent(final Formularz form)
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (form.budowy_combo.getSelectedItem() != null)
				{
					Budowa item = (Budowa) form.budowy_combo.getSelectedItem();
					form.budowy_kod.setText(item.getKod() + "");
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
					wczytajElementy(item.getId(), form);
				}
			}
		};
	}


	public static void wczytajElementy(int id_parent, Formularz form)
	{
		form.elementy = DBLoader.getElementy(id_parent);
		form.elementy_combo.removeAllItems();
		int size = form.elementy.size();
		for (int i = 0; i < size; i++)
		{
			Element item = (Element) form.elementy.get(i);
			form.elementy_combo.addItem(item);
		}

	}

	public static ActionListener elementyEvent(final Formularz form)
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (form.elementy_combo.getSelectedItem() != null)
				{
					Element item = (Element) form.elementy_combo.getSelectedItem();
					form.elementy_kod.setText(item.getKod() + "");
				}
			}
		};
	}
}
