/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

package inne;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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

		String yourXhtmlContentAsString = getHtmlSkroty();
		Document document = XMLResource.load(new ByteArrayInputStream(yourXhtmlContentAsString.getBytes())).getDocument();

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(document, null);
		renderer.getFontResolver().addFont("arialuni.ttf", "UTF-8", BaseFont.NOT_EMBEDDED);
		renderer.layout();
		FileOutputStream fos = new FileOutputStream(RESULT);
		renderer.createPDF(fos);
		fos.close();
		// System.out.println("File 1: '" + RESULT + "' created.");

	}

	private static String getHtmlSkroty()
	{
		String katalog = "skroty/";

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
		content += String.format(getHtmlFile(katalog, "content.html"), cells);
		html += String.format(getHtmlFile(katalog, "template.html"), header, content);

		return html;

	}

	private static String getHtmlBezSkrotow()
	{
		String katalog = "bez_skrotow/";

		String html = "";
		String header = "";
		String content = "";
		header += String.format(getHtmlFile(katalog, "header.html"), 1, 1, 1, 1, 1, 1);

		String cells = "";
		String cell = getHtmlFile(katalog, "cell.html");
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
		content += String.format(getHtmlFile(katalog, "content.html"), cells);
		html += String.format(getHtmlFile(katalog, "template.html"), header, content);

		return html;

	}

	public static String getHtmlFile(String katalog, String url)
	{

		StringBuilder contentBuilder = new StringBuilder();
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("pdf_html/" + katalog + url), "UTF-8"));
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