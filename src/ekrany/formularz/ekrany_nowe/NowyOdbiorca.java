package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.DBLoader;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.odbiorcy.Odbiorca;

public class NowyOdbiorca extends AbstractDodajNowy
{

	public NowyOdbiorca(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}

	@Override
	protected void zapiszDane()
	{
		Odbiorca item = new Odbiorca();
		item.setKod(this.kod);
		item.setNazwa(this.nazwa.getText());
		item.insert();
		EventLoaderJComboBox.wczytajOdbiorcow(form);
	}

}
