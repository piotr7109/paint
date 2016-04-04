package modules.zamowienie.kody;

import modules.zamowienie.ZamowienieCore;

public class Kod extends ZamowienieCore
{
	@Override
	public void setIdParent(int id_parent)
	{
		
	}
	@Override
	public int getIdParent()
	{
		return 0;
	}
	
	public Kod()
	{
		table = "t_zamowienia_kody";
	}
}