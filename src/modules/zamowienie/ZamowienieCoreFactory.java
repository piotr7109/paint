package modules.zamowienie;

import java.sql.ResultSet;
import java.sql.SQLException;

import modules.AbstractFactory;

public class ZamowienieCoreFactory extends AbstractFactory {
	public ZamowienieCoreFactory() {
		super();
		tabela = "t_zamowienia_odbiorcy";
	}

	protected Object fetchObject(ResultSet rs) throws SQLException {
		ZamowienieCore zc = new ZamowienieCore();
		zc.nazwa = rs.getString("nazwa");
		zc.kod = rs.getInt("kod");

		return zc;
	}

	private int getMaxKod(String tabela, int id_parent) {
		query = String.format("SELECT * FROM %s WHERE id_parent=%d ORDER BY kod DESC LIMIT 1", tabela, id_parent);

		ZamowienieCore zc = (ZamowienieCore) getObject();
		return zc.kod + 1;
	}

	public int getMaxKodOdbiorca() {
		query = String.format("SELECT * FROM %s ORDER BY kod DESC LIMIT 1", "t_zamowienia_odbiorcy");

		ZamowienieCore zc = (ZamowienieCore) getObject();
		return zc.kod + 1;
	}

	public int getMaxKodBudowa(int id_parent) {
		return getMaxKod("t_zamowienia_budowy", id_parent);
	}

	public int getMaxKodElement(int id_parent) {
		return getMaxKod("t_zamowienia_elementy", id_parent);
	}

	public int getMaxKodObiekt(int id_parent) {
		return getMaxKod("t_zamowienia_obiekty", id_parent);
	}

	private boolean czyNazwaIstnieje(String nazwa, int kod, String tabela, int id_parent) {
		query = String.format("SELECT * FROM %s where (nazwa='%s' OR kod=%d) AND id_parent=%d", tabela, nazwa, kod, id_parent);
		return (getObject() == null) ? false : true;
	}

	public boolean czyIstniejeOdbiorca(String nazwa, int kod) {
		query = String.format("SELECT * FROM %s where nazwa='%s' OR kod=%d", "t_zamowienia_odbiorcy", nazwa, kod);
		return getObject() != null;
	}

	public boolean czyIstniejeBudowa(String nazwa, int kod, int id_parent) {
		return czyNazwaIstnieje(nazwa, kod, "t_zamowienia_budowy", id_parent);
	}

	public boolean czyIstniejeObiekt(String nazwa, int kod, int id_parent) {
		return czyNazwaIstnieje(nazwa, kod, "t_zamowienia_obiekty", id_parent);
	}

	public boolean czyIstniejeElement(String nazwa, int kod, int id_parent) {
		return czyNazwaIstnieje(nazwa, kod, "t_zamowienia_elementy", id_parent);
	}

}
