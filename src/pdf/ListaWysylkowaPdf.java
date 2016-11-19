package pdf;

import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dodatki.FocusListeners;
import dodatki.Obliczenia;

public class ListaWysylkowaPdf extends PdfCreator
{
	private String kod;

	public ListaWysylkowaPdf(String kod)
	{
		this.kod = kod;
		this.FILENAME = "lista_wysylkowa/lista_w_" + kod + ".pdf";
		this.HTML_SOURCE = "bez_skrotow/";
	}

	public String getHtml()
	{
		String html = "";
		String header = "";
		String content = "";
		header += getHeaderHtml();

		String cells = "";
		String cell = getHtmlFile(HTML_SOURCE, "cell.html");
		int size = ZamowienieDane.figury.size();
		for (int i = 0; i < size; i++)
		{
			FiguraZamowienie fig = ZamowienieDane.figury.get(i);
			if (i % 22 == 0 && i != 0)
			{
				cells += "</table>";
				cells += getTableHtml(size - i > 22);
			}
			if (cells.equals("") && i == 0)
			{
				cells += getTableHtml(size - i > 22);
			}
			

			int dlugosc = Obliczenia.obliczDlugosc(fig.figura);
			int waga = fig.ilosc_sztuk * (int) (Obliczenia.obliczDlugosc(fig.figura) * FocusListeners.sred_waga.get(fig.srednica)) / 100;
			String obrazek = getImage(fig.figura);
			String cell_html = String.format(cell, fig.pozycja, fig.uwagi, dlugosc, waga, fig.ilosc_sztuk, fig.srednica, obrazek);
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
		content += cells + "</table>";
		html += String.format(getHtmlFile(HTML_SOURCE, "template.html"), header, content);

		return html;

	}

	protected String getHeaderHtml()
	{
		return String.format(getHtmlFile(HTML_SOURCE, "header.html"), "30-969 Kraków, ul. Łowińskiego 9", ZamowienieDane.element.getTerminDostawy(), ZamowienieDane.obiekt.getNazwa(),
				ZamowienieDane.odbiorca.getNazwa(), ZamowienieDane.budowa.getNazwa(), ZamowienieDane.element.getNazwa(), ZamowienieDane.element.getTerminDostawy(), kod);
	}
}
