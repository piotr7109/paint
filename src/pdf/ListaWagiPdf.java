package pdf;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import dodatki.FocusListeners;
import dodatki.Obliczenia;
import pdf.wagi.MaszynaHelper;
import pdf.wagi.MaterialHelper;

public class ListaWagiPdf extends PdfCreator
{
	private String kod;

	public ListaWagiPdf(String kod)
	{
		this.kod = kod;
		this.FILENAME = "lista_wagi/wagi_" + kod + ".pdf";
		this.HTML_SOURCE = "wagi/";
	}

	@Override
	public String getHtml()
	{
		String html = "";
		String header = "";
		String maszyna = "";
		String material = "";

		header = getHeaderHtml();

		maszyna = String.format(getHtmlFile(HTML_SOURCE, "maszyna.html"), kod, "OGÓŁEM", getMaszynaHtml());

		material = String.format(getHtmlFile(HTML_SOURCE, "material.html"), "OGÓŁEM", getMaterialHtml());

		int proste = 0;
		int giete = 0;
		for (FiguraZamowienie fig_zam : ZamowienieDane.figury)
		{
			if (fig_zam.figura.getKod() == 1)
			{
				proste++;
			}
			else
			{
				giete++;
			}
		}

		html += String.format(getHtmlFile(HTML_SOURCE, "template.html"), header, maszyna, material, "Proste", proste, "Gięte", giete);
		return html;
	}

	protected String getHeaderHtml()
	{
		String html = "";

		html += String.format(getHtmlFile(HTML_SOURCE, "header.html"), ZamowienieDane.odbiorca.getNazwa(), ZamowienieDane.budowa.getNazwa(), ZamowienieDane.obiekt.getNazwa(),
				ZamowienieDane.element.getNazwa(), ZamowienieDane.element.getTerminDostawy());

		return html;
	}

	protected String getMaterialHtml()
	{
		String material_cells = "";

		Set<Integer> srednice = new TreeSet<Integer>();
		for (FiguraZamowienie fig_zam : ZamowienieDane.figury)
		{
			srednice.add(fig_zam.srednica);
		}
		ArrayList<MaterialHelper> wagi_h = new ArrayList<MaterialHelper>();
		MaterialHelper wagi_suma = new MaterialHelper();
		for (int sr : srednice)
		{
			MaterialHelper wh = new MaterialHelper();
			for (FiguraZamowienie fig : ZamowienieDane.figury)
			{
				if (fig.srednica == sr)
				{
					int waga = fig.ilosc_sztuk * (int) (Obliczenia.obliczDlugosc(fig.figura) * FocusListeners.sred_waga.get(sr));
					wh.dodajWage(waga, fig.maszyna);
					wagi_suma.dodajWage(waga, fig.maszyna);
				}
			}
			wagi_h.add(wh);
			material_cells += String.format(getHtmlFile(HTML_SOURCE, "cell_material.html"), sr, 0, 0, 0, 0, wh.bst500, wh.st500b, wh.rb500w, wh.ogolem);
		}
		material_cells += String.format(getHtmlFile(HTML_SOURCE, "cell_material.html"), "OGÓŁEM", 0, 0, 0, 0, wagi_suma.bst500, wagi_suma.st500b, wagi_suma.rb500w, wagi_suma.ogolem);

		return material_cells;
	}

	protected String getMaszynaHtml()
	{
		String maszyna_cells = "";

		Set<Integer> srednice = new TreeSet<Integer>();
		for (FiguraZamowienie fig_zam : ZamowienieDane.figury)
		{
			srednice.add(fig_zam.srednica);
		}
		ArrayList<MaszynaHelper> wagi_h = new ArrayList<MaszynaHelper>();
		MaszynaHelper wagi_suma = new MaszynaHelper();
		for (int sr : srednice)
		{
			MaszynaHelper wh = new MaszynaHelper();
			for (FiguraZamowienie fig : ZamowienieDane.figury)
			{
				if (fig.srednica == sr)
				{
					int dlugosc = Obliczenia.obliczDlugosc(fig.figura);
					int waga = fig.ilosc_sztuk * (int) (dlugosc * FocusListeners.sred_waga.get(sr)) / 100;
					wh.dodajWage(fig, waga, dlugosc);
					wagi_suma.dodajWage(fig, waga, dlugosc);
				}
			}
			wagi_h.add(wh);
			maszyna_cells += String.format(getHtmlFile(HTML_SOURCE, "cell_maszyna.html"), sr, wh.form12, wh.form16, wh.msyn, wh.sflex, wh.cs40, wh.mag, wh.nrec, wh.grec, wh.ogolem);
		}
		maszyna_cells += String.format(getHtmlFile(HTML_SOURCE, "cell_maszyna.html"), "OGÓŁEM", wagi_suma.form12, wagi_suma.form16, wagi_suma.msyn, wagi_suma.sflex, wagi_suma.cs40, wagi_suma.mag,
				wagi_suma.nrec, wagi_suma.grec, wagi_suma.ogolem);
		
		if(wagi_suma.ogolem == 0) {
			throw new NullPointerException();
		}
		return maszyna_cells;
	}

}
