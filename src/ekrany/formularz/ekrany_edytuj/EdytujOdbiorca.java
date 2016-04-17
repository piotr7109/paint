package ekrany.formularz.ekrany_edytuj;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.DBLoader;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.odbiorcy.Odbiorca;

public class EdytujOdbiorca extends AbstractEdytujNowy
{

	public EdytujOdbiorca(JFrame frame, Formularz form, ZamowienieCore item)
	{
		super(frame, form ,item);
	}

	@Override
	protected void zapiszDane()
	{
		Odbiorca item = new Odbiorca();
		item.setId(this.item.getId());
		item.setKod(this.item.getKod());
		item.setNazwa(this.nazwa.getText());
		item.update();
		EventLoaderJComboBox.wczytajOdbiorcow(form);
	}

}
