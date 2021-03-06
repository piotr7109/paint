/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

package inne;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import com.lowagie.text.pdf.BaseFont;

/**
 * First iText example: Hello World.
 */
public class Pdf
{

	/** Path to the resulting PDF file. */
	public static final String RESULT = System.getProperty("user.home") + "/Desktop/java_pdf/skroty.pdf";

	/**
	 * Creates a PDF file: hello.pdf
	 * 
	 * @param args
	 *            no arguments needed
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		Pdf pdf = new Pdf();
		String yourXhtmlContentAsString = pdf.getHtmlSkroty();
		System.out.println(yourXhtmlContentAsString);
		Document document = XMLResource.load(new ByteArrayInputStream(yourXhtmlContentAsString.getBytes())).getDocument();
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(document, null);
		renderer.getFontResolver().addFont("files/font/arialuni.ttf", "UTF-8", BaseFont.NOT_EMBEDDED);
		renderer.layout();
		FileOutputStream fos = new FileOutputStream(RESULT);
		renderer.createPDF(fos);
		fos.close();
		// System.out.println("File 1: '" + RESULT + "' created.");

	}

	private String getHtmlSkroty()
	{
		String katalog = "bez_skrotow/";

		String html = "";
		String header = "";
		String content = "";
		header += String.format(getHtmlFile(katalog, "header.html"), 1, 1, 1, 1, 1, 1);

		String cells = "";
		String cell = getHtmlFile(katalog, "cell.html");
		int size = 50;
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
				cells += "<tr>" + String.format(cell, i, Integer.toString(i), "1", "2", "3", "4");
			}
			else
			{
				cells += String.format(cell, i, Integer.toString(i), "1", "2", "3", "4") + "</tr>";
			}

		}
		if (size % 2 == 1)
		{
			cells += "</tr>";
		}
		content += String.format(getHtmlFile(katalog, "content.html"), cells);
		html += String.format(getHtmlFile(katalog, "template.html"), header, content);

		return html;

	}

	protected String getHtmlFile(String katalog, String url)
	{

		StringBuilder contentBuilder = new StringBuilder();
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + katalog + url)));
			String str;
			while ((str = in.readLine()) != null)
			{
				contentBuilder.append(str);
			}
			in.close();
		}
		catch (IOException e)
		{
		}
		String content = contentBuilder.toString();
		return content;
	}

}