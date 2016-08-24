package modules.zamowienie.obiekty;

import java.util.ArrayList;

import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.elementy.Element;
import modules.zamowienie.elementy.ElementLista;

public class Obiekt extends ZamowienieCore
{
	public Obiekt()
	{
		table = "t_zamowienia_obiekty";
	}
	
	protected void deleteChildren()
	{
		ElementLista list = new ElementLista(id);
		ArrayList<Element> items = list.ConvertObjectListToElementLista(list.getList());
		
		for(Element item: items)
		{
			item.deleteRecursively();
		}
	}
}
