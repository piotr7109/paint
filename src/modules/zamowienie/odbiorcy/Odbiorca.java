package modules.zamowienie.odbiorcy;

import java.util.ArrayList;

import dodatki.MySQLJDBC;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.budowy.BudowaLista;

public class Odbiorca extends ZamowienieCore
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

	public Odbiorca()
	{
		table = "t_zamowienia_odbiorcy";
	}

	@Override
	public void insert()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("INSERT INTO %s (kod, nazwa) VALUES (%d, '%s')", table, kod, nazwa);
		pgsq.queryOpertaion(query);
	}
	
	protected void deleteChildren()
	{
		BudowaLista list = new BudowaLista(id);
		ArrayList<Budowa> items = list.ConvertObjectListToBudowaLista(list.getList());
		
		for(Budowa item: items)
		{
			item.deleteRecursively();
		}
	}
}