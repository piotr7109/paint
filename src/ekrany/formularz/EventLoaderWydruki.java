package ekrany.formularz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import dane.ZamowienieDane;
import dodatki.Tools;
import dodatki.FocusListeners;
import ekrany.Formularz;
import ekrany.optymalizacja.OpymalizacjaFrame;
import ekrany.zamowienie.db.DBConnector;
import modules.zamowienie.elementy.Element;
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

	public static ActionListener drukujOptymalizacje(final Formularz form)
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFrame frame = Tools.createNewWindow("Optymalizacja");
				Element element = (Element)form.elementy_combo.getSelectedItem();
				frame.add(new OpymalizacjaFrame(element));
				frame.pack();
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
