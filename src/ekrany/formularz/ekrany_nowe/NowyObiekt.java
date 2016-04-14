package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.obiekty.Obiekt;
import modules.zamowienie.placowki.Placowka;


public class NowyObiekt extends AbstractDodajNowy
{

	public NowyObiekt(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}
	@Override
	protected void zapiszDane()
	{
		Obiekt item = new Obiekt();
		item.setIdParent(id_parent);
		item.setKod(kod);
		item.setNazwa(nazwa.getText());
		item.insert();
		EventLoaderJComboBox.wczytajObiekty(id_parent, form);
	}

}
