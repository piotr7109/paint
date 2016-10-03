package pdf;

import java.util.ArrayList;

import dane.FiguraZamowienie;
import dodatki.Tools;
import javafx.util.Pair;
import optymalizacja.Suspect;
import pdf.optymalizacja.CountableArray;
import pdf.optymalizacja.Row;

public class OptymalizacjaPdf extends PdfCreator
{
	private int srednica;
	private ArrayList<Pair<Integer, Suspect[]>> sol;
	private String klient = "";
	private ArrayList<FiguraZamowienie> figZam;

	public OptymalizacjaPdf(String time, int sr, ArrayList<Pair<Integer, Suspect[]>> sol, ArrayList<FiguraZamowienie> figZam)
	{
		this.FILENAME = String.format("optymalizacja/optymalizacja_%s_%d.pdf", time, sr);
		this.HTML_SOURCE = "optymalizacja/";
		this.srednica = sr;
		this.sol = sol;
		this.figZam = figZam;
	}

	@Override
	public String getHtml()
	{
		String content = "";

		content += getHeaderHtml(srednica, "SuperFlex", "BST500");
		content += "<table class='content'>";
		content += getTableHeader();

		for (Pair<Integer, Suspect[]> pair : sol)
		{
			content += getRows(pair);
		}

		content += "</table>";

		return String.format(getHtmlFile(HTML_SOURCE, "template.html"), content);
	}

	protected String getRows(Pair<Integer, Suspect[]> pair)
	{
		String html = "";
		Row row;
		html += getRowHeader(pair.getKey().toString());
		CountableArray cArray = new CountableArray();
		for (Suspect sus : pair.getValue())
		{
			cArray.add(figZam.get(sus.index), sus.value);
		}
		
		for(pdf.optymalizacja.Pair figs : cArray.array)
		{
			row = new Row("1", String.valueOf(figs.value), String.valueOf(figs.count), "2D", DrawFigura.rysuj(figs.fig.figura), figs.fig.pozycja, figs.fig.uwagi);
			html += getCell(row, "row");
		}
		return html;
	}

	protected String getRowHeader(String dlugosc)
	{
		return String.format("%s", getCell(new Row(dlugosc), "row-start"));
	}

	protected String getCell(Row row, String cssClass)
	{
		return String.format(getHtmlFile(HTML_SOURCE, "cell.html"), cssClass, row.ilosc, row.dlugosc, row.szt, row.mgMasz, row.img, row.pozycja, row.uwagi, klient);
	}

	protected String getTableHeader()
	{
		return getHtmlFile(HTML_SOURCE, "table_header.html");
	}

	protected String getHeaderHtml(int srednica, String maszyna, String material)
	{
		return String.format(getHtmlFile(HTML_SOURCE, "header.html"), Tools.getFormattedDate(), maszyna, srednica, material);
	}
}
