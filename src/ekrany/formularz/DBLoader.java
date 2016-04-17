package ekrany.formularz;

import java.util.ArrayList;

import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.budowy.BudowaLista;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.elementy.ElementLista;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.obiekty.ObiektLista;
import modules.zamowienie.odbiorcy.Odbiorca;
import modules.zamowienie.odbiorcy.OdbiorcaLista;

public class DBLoader
{	
	public static ArrayList<Odbiorca> getOdbiorcy()
	{
		OdbiorcaLista lista = new OdbiorcaLista();
		return lista.ConvertObjectListToOdbiorcaLista(lista.getList());
	}

	public static ArrayList<Budowa> getBudowy(int parent_id)
	{
		BudowaLista lista = new BudowaLista(parent_id);
		return lista.ConvertObjectListToBudowaLista(lista.getList());
	}

	public static ArrayList<Obiekt> getObiekty(int parent_id)
	{
		ObiektLista lista = new ObiektLista(parent_id);
		return lista.ConvertObjectListToObiektLista(lista.getList());
	}

	public static ArrayList<Element> getElementy(int parent_id)
	{
		ElementLista lista = new ElementLista(parent_id);
		return lista.ConvertObjectListToElementLista(lista.getList());
	}
}
