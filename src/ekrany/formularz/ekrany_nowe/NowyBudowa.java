package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import dodatki.Tools;
import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.budowy.Budowa;

public class NowyBudowa extends AbstractDodajNowy
{

	public NowyBudowa(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}

	@Override
	protected void zapiszDane()
	{
		Budowa item = new Budowa();
		item.setIdParent(id_parent);
		item.setKod(Integer.parseInt(kod_label.getText()));
		item.setNazwa(nazwa.getText());
		item.insert();
		makeDir(item);
		EventLoaderJComboBox.wczytajBudowy(id_parent, form);
	}
	protected void makeDir(ZamowienieCore item)
	{
		String odbiorca_nazwa = ((ZamowienieCore)form.odbiorcy_combo.getSelectedItem()).getNazwa();
		Tools.makeDir("wydruki/"+odbiorca_nazwa+"/"+item.getNazwa());
	}

}
