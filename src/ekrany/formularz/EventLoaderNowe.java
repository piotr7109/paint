package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import dodatki.Tools;
import ekrany.Formularz;
import ekrany.formularz.ekrany_nowe.NowyOdbiorca;
import ekrany.formularz.ekrany_nowe.NowyObiekt;
import ekrany.formularz.ekrany_nowe.NowyBudowa;
import ekrany.formularz.ekrany_nowe.NowyElement;
import modules.zamowienie.ZamowienieCoreFactory;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.odbiorcy.Odbiorca;

public class EventLoaderNowe
{
	public static ActionListener odbiorcaEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = Tools.createNewWindow("Dodaj nowy");
				int kod;
				try
				{
					kod = new ZamowienieCoreFactory().getMaxKodOdbiorca();
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				int id_parent = 0;
				frame.add(new NowyOdbiorca(kod, frame, id_parent, form));
				frame.pack();

			}
		};
	}

	public static ActionListener budowaEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = Tools.createNewWindow("Dodaj nowy");
				int kod;
				int id_parent = ((Odbiorca) form.odbiorcy_combo.getSelectedItem()).getId();
				try
				{
					kod = new ZamowienieCoreFactory().getMaxKodBudowa(id_parent);
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				
				frame.add(new NowyBudowa(kod, frame, id_parent, form));
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
				JFrame frame = Tools.createNewWindow("Dodaj nowy");
				int kod;
				int id_parent = ((Budowa) form.budowy_combo.getSelectedItem()).getId();
				try
				{
					kod = new ZamowienieCoreFactory().getMaxKodObiekt(id_parent);
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				frame.add(new NowyObiekt(kod, frame, id_parent, form));
				frame.pack();
			}
		};
	}

	public static ActionListener elementEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				JFrame frame = Tools.createNewWindow("Dodaj nowy");
				int kod;
				int id_parent = ((Obiekt) form.obiekty_combo.getSelectedItem()).getId();
				try
				{
					kod = new ZamowienieCoreFactory().getMaxKodElement(id_parent);
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				frame.add(new NowyElement(kod, frame, id_parent, form));
				frame.pack();
			}
		};
	}
}
