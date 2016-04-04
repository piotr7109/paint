package modules.zamowienie.placowki;

import modules.zamowienie.ZamowienieListaCore;

public class PlacowkaLista extends ZamowienieListaCore
{
	protected String table = "t_zamowienia_placowki";
	public PlacowkaLista(int id_parent)
	{
		super(id_parent);
	}
}
