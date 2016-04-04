package modules.zamowienie.nazwy;

import modules.zamowienie.ZamowienieListaCore;

public class NazwaLista extends ZamowienieListaCore
{
	protected String table = "t_zamowienia_nazwy";
	public NazwaLista(int id_parent)
	{ 
		super(id_parent);
	}

}
