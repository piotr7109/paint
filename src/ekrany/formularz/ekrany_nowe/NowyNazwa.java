package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.nazwy.Nazwa;

public class NowyNazwa extends AbstractDodajNowy
{

	public NowyNazwa(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}

	@Override
	protected void zapiszDane()
	{
		Nazwa item = new Nazwa();
		item.setIdParent(id_parent);
		item.setKod(kod);
		item.setNazwa(nazwa.getText());
		item.insert();
		EventLoaderJComboBox.wczytajNazwy(id_parent, form);
	}

}
