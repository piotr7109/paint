package pdf;

import java.util.ArrayList;
import java.util.Date;

import dodatki.Tools;
import javafx.util.Pair;
import optymalizacja.Suspect;

public class OptymalizacjaPdf extends PdfCreator
{
	private int srednica;
	private ArrayList<Pair<Integer, Suspect[]>> sol;

	public OptymalizacjaPdf(String time, int sr, ArrayList<Pair<Integer, Suspect[]>> sol)
	{
		this.FILENAME = String.format("optymalizacja/optymalizacja_%s_%d.pdf", time, sr);
		this.HTML_SOURCE = "optymalizacja/";
		this.srednica = sr;
		this.sol = sol;
	}

	@Override
	public String getHtml()
	{
		String content = "";

		content += getHeaderHtml(srednica, "SuperFlex", "BST500");

		String subContent = "";

		subContent += "<table class='content'>";
		subContent += getTableHeader();
		subContent += "</table>";

		content += subContent;
		return String.format(getHtmlFile(HTML_SOURCE, "template.html"), content);
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
