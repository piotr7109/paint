package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.ekrany_edytuj.EdytujBudowa;
import ekrany.formularz.ekrany_edytuj.EdytujElement;
import ekrany.formularz.ekrany_edytuj.EdytujObiekt;
import ekrany.formularz.ekrany_edytuj.EdytujOdbiorca;
import modules.zamowienie.ZamowienieCore;

public class EventLoaderEdytuj
{
	private static JFrame createNewWindow()
	{
		JFrame frame = new JFrame("Edycja");
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
				
				frame.add(new EdytujOdbiorca(frame, form, (ZamowienieCore)form.odbiorcy_combo.getSelectedItem()));
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
				frame.add(new EdytujBudowa(frame, form, (ZamowienieCore)form.budowy_combo.getSelectedItem()));
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
				frame.add(new EdytujObiekt(frame, form, (ZamowienieCore)form.obiekty_combo.getSelectedItem()));
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
				frame.add(new EdytujElement(frame, form, (ZamowienieCore)form.elementy_combo.getSelectedItem()));
				frame.pack();
			}
		};
	}
}
