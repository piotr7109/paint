package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.elementy.Element;

public class NowyElement extends AbstractDodajNowy
{

	public NowyElement(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}

	@Override
	protected void zapiszDane()
	{
		Element item = new Element();
		item.setIdParent(id_parent);
		item.setKod(kod);
		item.setNazwa(nazwa.getText());
		item.insert();
		EventLoaderJComboBox.wczytajElementy(id_parent, form);
	}

}
