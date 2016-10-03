package pdf;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import com.lowagie.text.pdf.BaseFont;

import dane.ZamowienieDane;

abstract public class PdfCreator implements PdfCreatorInterface
{
	protected String RESULT_DIRECTORY = "wydruki/"+ZamowienieDane.odbiorca.getNazwa()+"/"+ZamowienieDane.budowa.getNazwa()+"/"+ZamowienieDane.obiekt.getNazwa()+"/"+ZamowienieDane.element.getNazwa()+"/";
	protected String FILENAME;
	protected String HTML_SOURCE;

	public void drukuj()
	{
		String contentHtml = getHtml();
		byte[] byteContentHtml = null;
		try
		{
			byteContentHtml = contentHtml.getBytes("UTF-8");
		}
		catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		Document document = XMLResource.load(new ByteArrayInputStream(byteContentHtml)).getDocument();
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocument(document, null);
		try
		{
			renderer.getFontResolver().addFont("files/font/arialuni.ttf", "UTF-8", BaseFont.NOT_EMBEDDED);
			renderer.layout();
			FileOutputStream fos = new FileOutputStream(RESULT_DIRECTORY + FILENAME);
			renderer.createPDF(fos);
			fos.close();
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		finally
		{
			try
			{
				FileUtils.cleanDirectory(new File("temp_img/"));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}; 
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
