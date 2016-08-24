package ekrany.formularz.ekrany_edytuj;

import javax.swing.JFrame;

import ekrany.Formularz;
import ekrany.formularz.EventLoaderJComboBox;
import modules.zamowienie.ZamowienieCore;
import modules.zamowienie.obiekty.Obiekt;

public class EdytujObiekt extends AbstractEdytujNowy
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5913614541240958879L;

	public EdytujObiekt(JFrame frame, Formularz form, ZamowienieCore item)
	{
		super(frame, form, item);
	}

	@Override
	protected void zapiszDane()
	{
		Obiekt item = new Obiekt();
		item.setId(this.item.getId());
		item.setIdParent(this.item.getIdParent());
		item.setKod(this.item.getKod());
		item.setNazwa(this.nazwa.getText());
		item.update();
		EventLoaderJComboBox.wczytajObiekty(item.getIdParent(), form);
	}

}
