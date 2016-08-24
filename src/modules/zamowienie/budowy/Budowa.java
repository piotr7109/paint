package modules.zamowienie.budowy;

import java.util.ArrayList;

import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.obiekty.ObiektLista;

public class Budowa extends ZamowienieCore
{
	public Budowa()
	{
		table = "t_zamowienia_budowy";
	}
	
	protected void deleteChildren()
	{
		ObiektLista list = new ObiektLista(id);
		ArrayList<Obiekt> items = list.ConvertObjectListToObiektLista(list.getList());
		
		for(Obiekt item: items)
		{
			item.deleteRecursively();
		}
	}
}
