package ekrany.formularz;

import java.util.ArrayList;

import modules.zamowienie.kody.Kod;
import modules.zamowienie.kody.KodLista;
import modules.zamowienie.miejscowosci.Miejscowosc;
import modules.zamowienie.miejscowosci.MiejscowoscLista;
import modules.zamowienie.nazwy.Nazwa;
import modules.zamowienie.nazwy.NazwaLista;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.obiekty.ObiektLista;
import modules.zamowienie.placowki.Placowka;
import modules.zamowienie.placowki.PlacowkaLista;

public class DBLoader
{	
	public static ArrayList<Kod> getKody()
	{
		KodLista lista = new KodLista();
		return lista.ConvertObjectListToKodLista(lista.getList());
	}

	public static ArrayList<Nazwa> getNazwy(int parent_id)
	{
		NazwaLista lista = new NazwaLista(parent_id);
		return lista.ConvertObjectListToNazwaLista(lista.getList());
	}

	public static ArrayList<Miejscowosc> getMiejscowosci(int parent_id)
	{
		MiejscowoscLista lista = new MiejscowoscLista(parent_id);
		return lista.ConvertObjectListToMiejscowoscLista(lista.getList());
	}

	public static ArrayList<Placowka> getPlacowki(int parent_id)
	{
		PlacowkaLista lista = new PlacowkaLista(parent_id);
		return lista.ConvertObjectListToPlacowkaLista(lista.getList());
	}

	public static ArrayList<Obiekt> getObiekty(int parent_id)
	{
		ObiektLista lista = new ObiektLista(parent_id);
		return lista.ConvertObjectListToObiektLista(lista.getList());
	}
}
