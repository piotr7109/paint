package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dane.ZamowienieDane;
import dodatki.Tools;
import dodatki.FocusListeners;
import ekrany.zamowienie.db.DBConnector;
import pdf.ListaMetkiPdf;
import pdf.ListaProdukcyjnaPdf;
import pdf.ListaWagiPdf;
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
					String kod = Tools.getKodZamowienia();
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
					String kod = Tools.getKodZamowienia();
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
				initDane();
				String kod = Tools.getKodZamowienia();
				ListaMetkiPdf pdf = new ListaMetkiPdf(kod);
				pdf.drukuj();
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
				initDane();
				String kod = Tools.getKodZamowienia();
				ListaWagiPdf pdf = new ListaWagiPdf(kod);
				pdf.drukuj();
			}
		};
	}
}
