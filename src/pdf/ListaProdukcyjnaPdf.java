package pdf;

import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dodatki.FocusListeners;
import dodatki.Obliczenia;

public class ListaProdukcyjnaPdf extends PdfCreator
{
	private String kod;

	public ListaProdukcyjnaPdf(String kod)
	{
		this.kod = kod;
		this.FILENAME = "lista_produkcyjna/lista_prod_" + kod + ".pdf";
		this.HTML_SOURCE = "skroty/";
	}

	public String getHtml()
	{
		String html = "";
		String header = "";
		String content = "";

		int maszyny_size = FocusListeners.maszyny.size();
		String cells = "";
		String cell = getHtmlFile(HTML_SOURCE, "cell.html");
		for (int k = 1; k < maszyny_size; k++)
		{
			header = String.format(getHtmlFile(HTML_SOURCE, "header.html"), kod, ZamowienieDane.odbiorca.getNazwa(), FocusListeners.maszyny.get(k), ZamowienieDane.budowa.getNazwa(),
					ZamowienieDane.element.getNazwa());
			int size = ZamowienieDane.figury.size();
			int index = 0;
			for (int i = 0; i < size; i++)
			{
				FiguraZamowienie fig = ZamowienieDane.figury.get(i);
				if (fig.maszyna == k)
				{
					if ((index % 11 == 0 && index != 0) || (index == 0 && !cells.equals("")))
					{
						cells += "</table>";
						cells += header;
						cells += "<table style='border-collapse: collapse; width: 735px;page-break-after: always;'>";
					}
					if (cells.equals("") && index == 0)
					{
						cells += header;
						cells += "<table style='border-collapse: collapse; width: 735px;page-break-after: always;'>";
					}
					int dlugosc = (int) Obliczenia.obliczDlugoscRzeczywista(i);
					int waga = (int) (fig.ilosc_sztuk * dlugosc * FocusListeners.sred_waga.get(fig.srednica));
					String obrazek = getImage(fig.figura);
					String cell_html = String.format(cell, fig.pozycja, fig.uwagi, dlugosc, waga, fig.ilosc_sztuk, fig.srednica, obrazek);
					cells += "<tr>" + cell_html + "</tr>";
					index++;
				}

			}

		}
		content += cells + "</table>";
		html += String.format(getHtmlFile(HTML_SOURCE, "template.html"), content);
		return html;

	}
}
