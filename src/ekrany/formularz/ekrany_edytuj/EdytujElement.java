package ekrany.formularz.ekrany_edytuj;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.budowy.Budowa;
import modules.zamowienie.elementy.Element;

public class EdytujElement extends AbstractEdytujNowy
{

	public EdytujElement(JFrame frame, Formularz form, ZamowienieCore item)
	{
		super(frame, form, item);
	}

	@Override
	protected void zapiszDane()
	{
		Element item = new Element();
		item.setId(this.item.getId());
		item.setIdParent(this.item.getIdParent());
		item.setKod(this.item.getKod());
		item.setNazwa(this.nazwa.getText());
		item.update();
		EventLoaderJComboBox.wczytajElementy(item.getIdParent(), form);
	}

}
