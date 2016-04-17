package ekrany.formularz;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import ekrany.Formularz;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.odbiorcy.Odbiorca;

public class EventLoaderJTextfield
{
	public static FocusListener odbiorcaEvent(final Formularz form)
	{
		return new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				int size = form.odbiorcy_combo.getItemCount();
				for(int i =0; i< size; i++)
				{
					if(((Odbiorca)form.odbiorcy_combo.getItemAt(i)).getKod() == Integer.parseInt(form.odbiorcy_kod.getText()))
					{
						form.odbiorcy_combo.setSelectedItem(form.odbiorcy_combo.getItemAt(i));
						break;
					}
				}
				
			}
			@Override
			public void focusGained(FocusEvent e){}
		};
	}
	public static FocusListener budowaEvent(final Formularz form)
	{
		return new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				int size = form.budowy_combo.getItemCount();
				for(int i =0; i< size; i++)
				{
					if(((Budowa)form.budowy_combo.getItemAt(i)).getKod() == Integer.parseInt(form.budowy_kod.getText()))
					{
						form.budowy_combo.setSelectedItem(form.budowy_combo.getItemAt(i));
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
	

	public static FocusListener elementEvent(final Formularz form)
	{
		return new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				int size = form.elementy_combo.getItemCount();
				for(int i =0; i< size; i++)
				{
					if(((Element)form.elementy_combo.getItemAt(i)).getKod() == Integer.parseInt(form.elementy_kod.getText()))
					{
						form.elementy_combo.setSelectedItem(form.elementy_combo.getItemAt(i));
						break;
					}
				}
				
			}
			@Override
			public void focusGained(FocusEvent e){}
		};
	}
}
