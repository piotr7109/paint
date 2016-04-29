package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import dane.ZamowienieDane;
import dodatki.CONST;
import dodatki.FocusListeners;
import ekrany.zamowienie.db.DBConnector;
import pdf.ListaProdukcyjnaPdf;
import pdf.ListaWysylkowaPdf;

public class EventLoaderWydruki
{

	private static void initDane()
	{
		DBConnector.odczytajDane();
		FocusListeners.setSredSworzen();
	}

	public static ActionListener drukujListeProdukcyjna()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (ZamowienieDane.element != null)
				{
					initDane();
					String kod = CONST.getKodZamowienia();
					ListaProdukcyjnaPdf pdf = new ListaProdukcyjnaPdf(kod);
					pdf.drukuj();
				}
			}
		};
	}

	public static ActionListener drukujListeWysylkowa()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (ZamowienieDane.element != null)
				{
					initDane();
					String kod = CONST.getKodZamowienia();
					ListaWysylkowaPdf pdf = new ListaWysylkowaPdf(kod);
					pdf.drukuj();
				}
			}
		};
	}

	public static ActionListener drukujMetki()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

			}
		};
	}

	public static ActionListener drukujOptymalizacje()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

			}
		};
	}

	public static ActionListener drukujWagi()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

			}
		};
	}
}
