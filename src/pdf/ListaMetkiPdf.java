package pdf;

import dane.FiguraZamowienie;
import dane.ZamowienieDane;
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
		int size = ZamowienieDane.figury.size();
		cells += "<table style='border-collapse: collapse;page-break-after: always;'>";
		for (int i = 0; i < size; i++)
		{
			FiguraZamowienie fig = ZamowienieDane.figury.get(i);
			if (i % 10 == 0 && i != 0)
			{
				cells += "</table>";
				cells += "<table style='border-collapse: collapse; page-break-after: always;'>";
			}
			int dlugosc = Obliczenia.obliczDlugosc(fig.figura);
			int waga = fig.ilosc_sztuk * (int) (Obliczenia.obliczDlugosc(fig.figura) * FocusListeners.sred_waga.get(fig.srednica));
			String obrazek = DrawFigura.rysuj(fig.figura);
			String cell_html = String.format(cell);
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
}
