package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.ekrany_nowe.NowyOdbiorca;
import ekrany.formularz.ekrany_nowe.NowyObiekt;
import ekrany.formularz.ekrany_nowe.NowyBudowa;
import ekrany.formularz.ekrany_nowe.NowyElement;

import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.odbiorcy.Odbiorca;

public class EventLoaderNowe
{
	private static JFrame createNewWindow()
	{
		JFrame frame = new JFrame("Dodaj nowy");
		frame.setResizable(false);

		frame.setVisible(true);
		return frame;
	}

	public static ActionListener odbiorcaEvent(final Formularz form)
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
					kod = ((Odbiorca) form.odbiorcy_combo.getItemAt(form.odbiorcy_combo.getItemCount() - 1)).getKod() + 1;
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
				JFrame frame = createNewWindow();
				int kod;
				try
				{
					kod = ((Budowa) form.budowy_combo.getItemAt(form.budowy_combo.getItemCount() - 1)).getKod() + 1;
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				int id_parent = ((Odbiorca) form.odbiorcy_combo.getSelectedItem()).getId();
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
				int id_parent = ((Budowa) form.budowy_combo.getSelectedItem()).getId();
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
				JFrame frame = createNewWindow();
				int kod;
				try
				{
					kod = ((Element) form.elementy_combo.getItemAt(form.elementy_combo.getItemCount() - 1)).getKod() + 1;
				}
				catch (NullPointerException e)
				{
					kod = 1;
				}
				int id_parent = ((Obiekt) form.obiekty_combo.getSelectedItem()).getId();
				frame.add(new NowyElement(kod, frame, id_parent, form));
				frame.pack();
			}
		};
	}
}
