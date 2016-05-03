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

		header = String.format(getHtmlFile(HTML_SOURCE, "header.html"));

		maszyna = String.format(getHtmlFile(HTML_SOURCE, "maszyna.html"), kod, getMaszynaHtml());

		material = String.format(getHtmlFile(HTML_SOURCE, "material.html"), kod, getMaterialHtml());

		html += String.format(getHtmlFile(HTML_SOURCE, "template.html"), header, maszyna, material);
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
					int waga = fig.ilosc_sztuk * (int) (Obliczenia.obliczDlugosc(fig.figura) * FocusListeners.sred_waga.get(sr));
					wh.dodajWage(waga, fig.maszyna);
					wagi_suma.dodajWage(waga, fig.maszyna);
				}
			}
			wagi_h.add(wh);
			maszyna_cells += String.format(getHtmlFile(HTML_SOURCE, "cell_maszyna.html"), sr, wh.form12, wh.form16, wh.msyn, wh.sflex, wh.cs40, wh.mag, wh.nrec, wh.grec, wh.ogolem);
		}
		maszyna_cells += String.format(getHtmlFile(HTML_SOURCE, "cell_maszyna.html"), "OGÓŁEM", wagi_suma.form12, wagi_suma.form16, wagi_suma.msyn, wagi_suma.sflex, wagi_suma.cs40, wagi_suma.mag,
				wagi_suma.nrec, wagi_suma.grec, wagi_suma.ogolem);
		return maszyna_cells;
	}

}