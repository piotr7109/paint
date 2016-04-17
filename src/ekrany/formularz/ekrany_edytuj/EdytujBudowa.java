package ekrany.formularz.ekrany_edytuj;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.budowy.Budowa;

public class EdytujBudowa extends AbstractEdytujNowy
{

	public EdytujBudowa(JFrame frame, Formularz form, ZamowienieCore item)
	{
		super(frame, form, item);
	}

	@Override
	protected void zapiszDane()
	{
		Budowa item = new Budowa();
		item.setId(this.item.getId());
		item.setIdParent(this.item.getIdParent());
		item.setKod(this.item.getKod());
		item.setNazwa(this.nazwa.getText());
		item.update();
		EventLoaderJComboBox.wczytajBudowy(item.getIdParent(), form);
	}

}
