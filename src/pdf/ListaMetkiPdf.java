package pdf;

import java.util.ArrayList;

import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dane.figura_zamowienie.FiguraZamowienieOpakowania;
import dodatki.FocusListeners;
import dodatki.Obliczenia;

public class ListaMetkiPdf extends PdfCreator
{
	private String kod;

	public ListaMetkiPdf(String kod)
	{
		this.kod = kod;
		this.FILENAME = "metki/metki_" + kod + ".pdf";
		this.HTML_SOURCE = "metki/";
	}

	public String getHtml()
	{
		String html = "";
		String cells = "";

		String cell = getHtmlFile(HTML_SOURCE, "cell.html");
		
		ArrayList<FiguraZamowienieOpakowania> fig_zam = new ArrayList<FiguraZamowienieOpakowania>();
		for(FiguraZamowienie fig_temp: ZamowienieDane.figury)
		{
			
			for(int j=0; j<fig_temp.ilosc_paczek;j++)
			{
				FiguraZamowienieOpakowania fig_temp_op = new FiguraZamowienieOpakowania(fig_temp);
				fig_temp_op.nr_opakowania =j+1;
				fig_zam.add(fig_temp_op);
			}
		}
		int size = fig_zam.size();
		cells += "<table style='border-collapse: collapse;page-break-after: always;'>";
		for (int i = 0; i < size; i++)
		{
			FiguraZamowienieOpakowania fig = fig_zam.get(i);
			if (i % 10 == 0 && i != 0)
			{
				cells += "</table>";
				cells += "<table style='border-collapse: collapse; page-break-after: always;'>";
			}
			
			String cell_html = getCellHtml(cell, fig);
			if (i % 2 == 0)
			{
				cells += "<tr>" + cell_html;
			}
			else
			{
				cells += cell_html + "</tr>";
			}

		}

		if (size % 2 == 1)
		{
			cells += "</tr>";
		}
		cells += "</table>";

		html += String.format(getHtmlFile(HTML_SOURCE, "template.html"), cells);
		System.out.println(html);
		return html;

	}
	protected String getCellHtml(String cell, FiguraZamowienieOpakowania fig)
	{
		String html = "";
		
		int dlugosc = Obliczenia.obliczDlugosc(fig.figura);
		int waga = fig.ilosc_sztuk/fig.ilosc_paczek * (int) (Obliczenia.obliczDlugosc(fig.figura) * FocusListeners.sred_waga.get(fig.srednica));
		String obrazek = DrawFigura.rysuj(fig.figura);
		html = String.format(cell,
				ZamowienieDane.odbiorca.getNazwa(),
				ZamowienieDane.budowa.getNazwa(),
				ZamowienieDane.obiekt.getNazwa(),
				ZamowienieDane.element.getNazwa(),
				fig.ilosc_sztuk/fig.ilosc_paczek,
				fig.srednica,
				dlugosc,
				FocusListeners.maszyny.get(fig.maszyna),
				fig.pozycja,
				fig.uwagi,
				obrazek,
				waga,
				fig.nr_opakowania,
				fig.ilosc_paczek);
		
		return html;
	}
	
}
