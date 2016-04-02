package pdf;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import com.lowagie.text.pdf.BaseFont;

abstract public class PdfCreator implements PdfCreatorInterface
{
	protected String RESULT_DIRECTORY = System.getProperty("user.home") + "/Desktop/java_pdf/";
	protected String FILENAME;
	protected String HTML_SOURCE;

	protected void drukuj()
	{
		String yourXhtmlContentAsString = getHtml();
		Document document = XMLResource.load(new ByteArrayInputStream(yourXhtmlContentAsString.getBytes())).getDocument();
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(document, null);
		try
		{
			renderer.getFontResolver().addFont("font/arialuni.ttf", "UTF-8", BaseFont.NOT_EMBEDDED);
			renderer.layout();
			FileOutputStream fos = new FileOutputStream(RESULT_DIRECTORY + FILENAME);
			renderer.createPDF(fos);
			fos.close();
		}
		catch (Exception e)
		{
			System.err.println(e.getStackTrace());
		}
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
