package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ekrany.Formularz;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.odbiorcy.Odbiorca;

public class EventLoaderUsun
{
	public static ActionListener odbiorcaEvent(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (form.odbiorcy_combo.getItemCount() > 0 && confirmRemoval())
				{
					((Odbiorca) form.odbiorcy_combo.getSelectedItem()).deleteRecursively();
					form.odbiorcy_combo.removeItemAt(form.odbiorcy_combo.getSelectedIndex());
					form.budowy_combo.removeAllItems();
					form.obiekty_combo.removeAllItems();
					form.elementy_combo.removeAllItems();
				}
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
				if (form.budowy_combo.getItemCount() > 0 && confirmRemoval())
				{
					((Budowa) form.budowy_combo.getSelectedItem()).deleteRecursively();
					form.budowy_combo.removeItemAt(form.budowy_combo.getSelectedIndex());
					form.obiekty_combo.removeAllItems();
					form.elementy_combo.removeAllItems();
				}
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
				if (form.obiekty_combo.getItemCount() > 0 && confirmRemoval())
				{
					((Obiekt) form.obiekty_combo.getSelectedItem()).deleteRecursively();
					form.obiekty_combo.removeItemAt(form.obiekty_combo.getSelectedIndex());
					form.elementy_combo.removeAllItems();
				}
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
				if (form.elementy_combo.getItemCount() > 0 && confirmRemoval())
				{
					((Element) form.elementy_combo.getSelectedItem()).deleteRecursively();
					form.elementy_combo.removeItemAt(form.elementy_combo.getSelectedIndex());
				}
			}
		};
	}
	
	private static boolean confirmRemoval()
	{
		if (JOptionPane.showConfirmDialog(null, "Na pewno chcesz usunąć?", "UWAGA", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
		{
			return true;
		}
		return false;
	}
}
