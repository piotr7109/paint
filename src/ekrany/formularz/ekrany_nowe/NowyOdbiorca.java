package ekrany.formularz.ekrany_nowe;

import javax.swing.JFrame;

import dodatki.Tools;
import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.odbiorcy.Odbiorca;

public class NowyOdbiorca extends AbstractDodajNowy
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1100943528070043938L;

	public NowyOdbiorca(int kod, JFrame frame, int id_parent, Formularz form)
	{
		super(kod, frame, id_parent, form);
	}

	@Override
	protected void zapiszDane()
	{
		Odbiorca item = new Odbiorca();
		item.setKod(Integer.parseInt(kod_label.getText()));
		item.setNazwa(this.nazwa.getText());
		item.insert();
		Tools.makeDir("wydruki/"+item.getNazwa());
		EventLoaderJComboBox.wczytajOdbiorcow(form);
	}

}
