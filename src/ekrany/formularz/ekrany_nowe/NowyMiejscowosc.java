package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.miejscowosci.Miejscowosc;

public class NowyMiejscowosc extends AbstractDodajNowy
{

	public NowyMiejscowosc(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}
	@Override
	protected void zapiszDane()
	{
		Miejscowosc item = new Miejscowosc();
		item.setIdParent(id_parent);
		item.setKod(kod);
		item.setNazwa(nazwa.getText());
		item.insert();
		EventLoaderJComboBox.wczytajMiejscowosci(id_parent, form);
	}

}
