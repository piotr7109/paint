package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import dane.ZamowienieDane;
import dodatki.FocusListeners;
import dodatki.Obliczenia;
import dodatki.Tools;
import ekrany.Formularz;
import ekrany.zamowienie.ramki.RamkaWymiarCM;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.elementy.figury.Figura;
import modules.zamowienie.elementy.figury.FiguraLista;
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
		form.odbiorcy_combo.setSelectedItem(getItemByMaximumId(form.odbiorcy));

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
					form.budowy_combo.removeAllItems();
					form.elementy_combo.removeAllItems();
					form.obiekty_combo.removeAllItems();
					wczytajBudowy(item.getId(), form);
					ZamowienieDane.odbiorca = (Odbiorca) form.odbiorcy_combo.getSelectedItem();
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
		form.budowy_combo.setSelectedItem(getItemByMaximumId(form.budowy));
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
					form.elementy_combo.removeAllItems();
					form.obiekty_combo.removeAllItems();
					wczytajObiekty(item.getId(), form);
					ZamowienieDane.budowa = (Budowa) form.budowy_combo.getSelectedItem();
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
		form.obiekty_combo.setSelectedItem(getItemByMaximumId(form.obiekty));
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
					form.elementy_combo.removeAllItems();
					wczytajElementy(item.getId(), form);
					ZamowienieDane.obiekt = (Obiekt) form.obiekty_combo.getSelectedItem();
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
		form.elementy_combo.setSelectedItem(getItemByMaximumId(form.elementy));
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
					ZamowienieDane.element = (Element) form.elementy_combo.getSelectedItem();
					wczytajDodatkoweDane(form);
				}
			}
		};
	}

	private static void wczytajDodatkoweDane(final Formularz form)
	{
		wczytajDateUtworzenia(form);
		wczytajWage(form);
	}

	private static void wczytajWage(final Formularz form)
	{
		FocusListeners.setSredSworzen();
		double waga = 0;

		Element el = (Element) form.elementy_combo.getSelectedItem();
		ArrayList<Object> figury = new FiguraLista(el.getId()).getList();

		int size = figury.size();
		Figura fig = null;
		for (int i = 0; i < size; i++)
		{
			fig = (Figura) figury.get(i);
			fig.setCzesci();
			try
			{
				waga += Tools.round((RamkaWymiarCM.obliczWage(Obliczenia.obliczDlugosc(fig), FocusListeners.sred_waga.get(fig.getSrednica())) * fig.getSztuk()), 0);
			}
			catch (NullPointerException e)
			{
				System.err.println(e.getMessage());
			}
		}

		if (waga > 0)
		{
			form.waga.setText(waga + "");
		}
		else
		{
			form.waga.setText("n/a");
		}
	}

	private static void wczytajDateUtworzenia(final Formularz form)
	{
		Date date = ((Element) form.elementy_combo.getSelectedItem()).getDataUtworzenia();
		if (date != null)
			form.data_utworzenia.setText(date.toString());
		else
			form.data_utworzenia.setText("n/a");
	}

	private static ZamowienieCore getItemByMaximumId(ArrayList<?> items)
	{
		if (items.size() == 0)
			return null;
		ZamowienieCore zc = (ZamowienieCore) items.get(items.size() - 1);

		for (Object item : items)
		{
			if (((ZamowienieCore) item).getId() > zc.getId())
			{
				zc = (ZamowienieCore) item;
			}
		}

		return zc;
	}
}
