package ekrany.formularz;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import ekrany.Formularz;
import modules.zamowienie.kody.Kod;
import modules.zamowienie.miejscowosci.Miejscowosc;
import modules.zamowienie.nazwy.Nazwa;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.placowki.Placowka;

public class EventLoaderJTextfield
{
	public static FocusListener kodEvent(final Formularz form)
	{
		return new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				int size = form.kody_combo.getItemCount();
				for(int i =0; i< size; i++)
				{
					if(((Kod)form.kody_combo.getItemAt(i)).getKod() == Integer.parseInt(form.kody_kod.getText()))
					{
						form.kody_combo.setSelectedItem(form.kody_combo.getItemAt(i));
						break;
					}
				}
				
			}
			@Override
			public void focusGained(FocusEvent e){}
		};
	}
	public static FocusListener nazwaEvent(final Formularz form)
	{
		return new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				int size = form.nazwy_combo.getItemCount();
				for(int i =0; i< size; i++)
				{
					if(((Nazwa)form.nazwy_combo.getItemAt(i)).getKod() == Integer.parseInt(form.nazwy_kod.getText()))
					{
						form.nazwy_combo.setSelectedItem(form.nazwy_combo.getItemAt(i));
						break;
					}
				}
				
			}
			@Override
			public void focusGained(FocusEvent e){}
		};
	}
	public static FocusListener miejscowoscEvent(final Formularz form)
	{
		return new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				int size = form.miejscowosci_combo.getItemCount();
				for(int i =0; i< size; i++)
				{
					if(((Miejscowosc)form.miejscowosci_combo.getItemAt(i)).getKod() == Integer.parseInt(form.miejscowosci_kod.getText()))
					{
						form.miejscowosci_combo.setSelectedItem(form.miejscowosci_combo.getItemAt(i));
						break;
					}
				}
				
			}
			@Override
			public void focusGained(FocusEvent e){}
		};
	}
	
	public static FocusListener placowkaEvent(final Formularz form)
	{
		return new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				int size = form.placowki_combo.getItemCount();
				for(int i =0; i< size; i++)
				{
					if(((Placowka)form.placowki_combo.getItemAt(i)).getKod() == Integer.parseInt(form.placowki_kod.getText()))
					{
						form.placowki_combo.setSelectedItem(form.placowki_combo.getItemAt(i));
						break;
					}
				}
				
			}
			@Override
			public void focusGained(FocusEvent e){}
		};
	}
	public static FocusListener obiektEvent(final Formularz form)
	{
		return new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				int size = form.obiekty_combo.getItemCount();
				for(int i =0; i< size; i++)
				{
					if(((Obiekt)form.obiekty_combo.getItemAt(i)).getKod() == Integer.parseInt(form.obiekty_kod.getText()))
					{
						form.obiekty_combo.setSelectedItem(form.obiekty_combo.getItemAt(i));
						break;
					}
				}
				
			}
			@Override
			public void focusGained(FocusEvent e){}
		};
	}
}
