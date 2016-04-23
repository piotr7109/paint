package ekrany.zamowienie.db;

import java.util.ArrayList;

import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import ekrany.Zamowienie;
import modules.zamowienie.elementy.figury.Figura;
import modules.zamowienie.elementy.figury.FiguraFactory;
import modules.zamowienie.elementy.figury.FiguraLista;
import modules.zamowienie.elementy.figury.czesci.Czesc;

public class DBConnector
{
	public static void zapiszDane()
	{
		int id_elementu = ZamowienieDane.element.getId();

		ZamowienieDane.element.update();
		
		FiguraFactory.usunWszystkie(id_elementu);
		int fig_size = ZamowienieDane.figury.size();
		for (int i = 0; i < fig_size; i++)
		{
			FiguraZamowienie fig_zam = ZamowienieDane.figury.get(i);
			modules.figury.Figura fig_temp = fig_zam.figura;

			Figura fig = new Figura();

			fig.setIdElementu(id_elementu);
			fig.setKod(fig_temp.getKod());
			fig.setPozycja(fig_zam.pozycja);
			fig.setSztuk(fig_zam.ilosc_sztuk);
			fig.setSrednica(fig_zam.srednica);
			fig.setIloscPaczek(fig_zam.ilosc_paczek);
			fig.setMaszyna(fig_zam.maszyna);
			fig.setSworzen(fig_zam.sworzen);
			fig.setUwagi(fig_zam.uwagi);
			// fig.setPoziomSkoku(...);
			// fig.setIloscSkokow(...);
			fig.insert();

			int fig_id = fig.getLastId();
			int czesc_size = fig_temp.getCzesci().size();
			for (int j = 0; j < czesc_size; j++)
			{
				modules.czesci.Czesc czesc_temp = fig_temp.getCzesci().get(j);
				Czesc czesc = new Czesc();
				czesc.setIdFigury(fig_id);
				czesc.setDlugosc(czesc_temp.getDlugosc());
				czesc.setKat(czesc_temp.getKat());
				czesc.setTyp(czesc_temp.getTyp());
				czesc.insert();

			}

		}
	}

	public static void odczytajDane()
	{
		FiguraLista figury_lista = new FiguraLista(ZamowienieDane.element.getId());

		ArrayList<Object> objects_figury = figury_lista.getList();
		int fig_size = objects_figury.size();
		for (int i = 0; i < fig_size; i++)
		{
			Figura fig_temp = (Figura) objects_figury.get(i);
			fig_temp.setCzesci();

			FiguraZamowienie fig_zam = new FiguraZamowienie();
			modules.figury.Figura fig = new modules.figury.Figura();

			fig.setKod(fig_temp.getKod());
			System.out.println("KOD:" + fig_temp.getKod());
			int czesc_size = fig_temp.getCzesci().size();

			for (int j = 0; j < czesc_size; j++)
			{
				Czesc czesc_temp = fig_temp.getCzesci().get(j);
				modules.czesci.Czesc czesc = new modules.czesci.Czesc();
				czesc.setDlugosc(czesc_temp.getDlugosc());
				czesc.setKat(czesc_temp.getKat());
				czesc.setTyp(czesc_temp.getTyp());
				fig.addCzesc(czesc);
			}

			fig_zam.figura = fig;
			fig_zam.fig = fig.getKod();
			fig_zam.pozycja = fig_temp.getPozycja();
			fig_zam.ilosc_sztuk = fig_temp.getSztuk();
			fig_zam.srednica = fig_temp.getSrednica();
			fig_zam.ilosc_paczek = fig_temp.getIloscPaczek();
			fig_zam.maszyna = fig_temp.getMaszyna();
			fig_zam.sworzen = fig_temp.getSworzen();
			fig_zam.uwagi = fig_temp.getUwagi();
			// fig_zam.poziom_skoku = fig_temp.getPoziomSkoku();
			// fig_zam.ilosc_skokow = fig_temp.getIloscSkokow();
			ZamowienieDane.figury.add(fig_zam);
		}
	}
}
