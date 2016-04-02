package pdf;

public class WydrukBezSkrotowPdf extends PdfCreator
{
	public WydrukBezSkrotowPdf()
	{
		this.FILENAME = "bez_skrotow.pdf";
		this.HTML_SOURCE = "bez_skrotow/";
	}

	public String getHtml()
	{
		String html = "";
		String header = "";
		String content = "";
		header += String.format(getHtmlFile(HTML_SOURCE, "header.html"), 1, 1, 1, 1, 1, 1);

		String cells = "";
		String cell = getHtmlFile(HTML_SOURCE, "cell.html");
		int size = 600;
		for (int i = 0; i < size; i++)
		{
			if (i % 22 == 0 && i != 0)
			{
				cells += "</table>";
				cells += header;
				cells += "<table style='border-collapse: collapse; width: 735px;page-break-after: always;'>";
			}

			if (i % 2 == 0)
			{
				cells += "<tr>" + String.format(cell, i);
			}
			else
			{
				cells += String.format(cell, i) + "</tr>";
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
