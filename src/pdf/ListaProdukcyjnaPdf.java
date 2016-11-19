package pdf;

import java.util.HashMap;

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

		int iloscMaszyn = getIloscMaszyn();
		HashMap<Integer, Integer> uzyteMaszyny = new HashMap<>();
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
					uzyteMaszyny.put(k, k);
					if ((index % 11 == 0 && index != 0) || (index == 0 && !cells.equals("")))
					{
						cells += "</table>";
						cells += header;
						cells += getTableHtml(uzyteMaszyny.size() != iloscMaszyn);
					}
					if (cells.equals("") && index == 0)
					{
						cells += header;
						cells += getTableHtml(uzyteMaszyny.size() != iloscMaszyn);
					}
					
					int dlugosc = (int) Obliczenia.obliczDlugoscRzeczywista(i);
					int waga = (int) (fig.ilosc_sztuk * dlugosc * FocusListeners.sred_waga.get(fig.srednica)) / 100;
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

	private int getIloscMaszyn()
	{
		HashMap<Integer, Integer> maszyny = new HashMap<>();

		for (FiguraZamowienie fig : ZamowienieDane.figury)
		{
			maszyny.put(fig.maszyna, fig.maszyna);
		}
		return maszyny.size();
	}
}
