package modules.zamowienie.miejscowosci;

import modules.zamowienie.ZamowienieListaCore;

public class MiejscowoscLista extends ZamowienieListaCore
{
	protected String table = "t_zamowienia_miejscowosci";
	public MiejscowoscLista(int id_parent)
	{ 
		super(id_parent);
	}
}
