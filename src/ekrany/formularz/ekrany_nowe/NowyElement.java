package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import dodatki.Tools;
import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.elementy.Element;

public class NowyElement extends AbstractDodajNowy
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5401613140693358806L;
	public NowyElement(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}

	@Override
	protected void zapiszDane()
	{
		Element item = new Element();
		item.setIdParent(id_parent);
		item.setKod(Integer.parseInt(kod_label.getText()));
		item.setNazwa(nazwa.getText());
		item.insert();
		makeDir(item);
		EventLoaderJComboBox.wczytajElementy(id_parent, form);
	}
	protected void makeDir(ZamowienieCore item)
	{
		String odbiorca_nazwa = ((ZamowienieCore)form.odbiorcy_combo.getSelectedItem()).getNazwa();
		String budowa_nazwa = ((ZamowienieCore)form.budowy_combo.getSelectedItem()).getNazwa();
		String obiekt_nazwa = ((ZamowienieCore)form.obiekty_combo.getSelectedItem()).getNazwa();
		Tools.makeDir("wydruki/"+odbiorca_nazwa+"/"+budowa_nazwa+"/"+obiekt_nazwa+"/"+item.getNazwa());
		
		Tools.makeDir("wydruki/"+odbiorca_nazwa+"/"+budowa_nazwa+"/"+obiekt_nazwa+"/"+item.getNazwa()+"/metki");
		Tools.makeDir("wydruki/"+odbiorca_nazwa+"/"+budowa_nazwa+"/"+obiekt_nazwa+"/"+item.getNazwa()+"/lista_produkcyjna");
		Tools.makeDir("wydruki/"+odbiorca_nazwa+"/"+budowa_nazwa+"/"+obiekt_nazwa+"/"+item.getNazwa()+"/lista_wagi");
		Tools.makeDir("wydruki/"+odbiorca_nazwa+"/"+budowa_nazwa+"/"+obiekt_nazwa+"/"+item.getNazwa()+"/lista_wysylkowa");
		Tools.makeDir("wydruki/"+odbiorca_nazwa+"/"+budowa_nazwa+"/"+obiekt_nazwa+"/"+item.getNazwa()+"/optymalizacja");
	}

}
