package pdf;

import java.awt.event.FocusListener;

import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dodatki.FocusListeners;
import dodatki.Obliczenia;
import modules.figury.Figura;

public class ListaWysylkowaPdf extends PdfCreator
{
	private String kod;
	public ListaWysylkowaPdf(String kod)
	{
		this.kod = kod;
		this.FILENAME = "lista_wysylkowa/lista_w_"+kod+".pdf";
		this.HTML_SOURCE = "bez_skrotow/";
	}

	public String getHtml()
	{
		String html = "";
		String header = "";
		String content = "";
		header += String.format(getHtmlFile(HTML_SOURCE, "header.html"),
				ZamowienieDane.element.getTerminDostawy(), 
				ZamowienieDane.obiekt.getNazwa(),
				ZamowienieDane.odbiorca.getNazwa(),
				ZamowienieDane.budowa.getNazwa(),
				ZamowienieDane.element.getNazwa(),
				ZamowienieDane.element.getTerminDostawy(),
				kod);

		String cells = "";
		String cell = getHtmlFile(HTML_SOURCE, "cell.html");
		int size =ZamowienieDane.figury.size();
		for (int i = 0; i < size; i++)
		{
			FiguraZamowienie fig = ZamowienieDane.figury.get(i);
			if (i % 22 == 0 && i != 0)
			{
				cells += "</table>";
				cells += header;
				cells += "<table style='border-collapse: collapse; width: 735px;page-break-after: always;'>";
			}
			int dlugosc = Obliczenia.obliczDlugosc(fig.figura);
			int waga = fig.ilosc_sztuk*(int) (Obliczenia.obliczDlugosc(fig.figura)* FocusListeners.sred_waga.get(fig.srednica));
			String obrazek = DrawFigura.rysuj(fig.figura);
			String cell_html = String.format(cell,
					fig.pozycja,
					fig.uwagi,
					dlugosc,
					waga,
					fig.ilosc_sztuk,
					fig.srednica,
					obrazek);
			System.out.println(obrazek);
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
		content += String.format(getHtmlFile(HTML_SOURCE, "content.html"), cells);
		html += String.format(getHtmlFile(HTML_SOURCE, "template.html"), header, content);

		return html;

	}
}
