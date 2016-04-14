package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.DBLoader;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.kody.Kod;

public class NowyKod extends AbstractDodajNowy
{

	public NowyKod(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}

	@Override
	protected void zapiszDane()
	{
		Kod kod = new Kod();
		kod.setKod(this.kod);
		kod.setNazwa(this.nazwa.getText());
		kod.insert();
		EventLoaderJComboBox.wczytajKody(form);
	}

}
