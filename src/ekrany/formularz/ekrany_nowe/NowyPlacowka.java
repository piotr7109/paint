package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.placowki.Placowka;

public class NowyPlacowka extends AbstractDodajNowy
{

	public NowyPlacowka(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}

	@Override
	protected void zapiszDane()
	{
		Placowka item = new Placowka();
		item.setIdParent(id_parent);
		item.setKod(kod);
		item.setNazwa(nazwa.getText());
		item.insert();
		EventLoaderJComboBox.wczytajPlacowki(id_parent, form);
	}

}
