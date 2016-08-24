package modules.zamowienie.elementy.figury;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dodatki.MySQLJDBC;
import modules.zamowienie.elementy.figury.czesci.Czesc;
import modules.zamowienie.elementy.figury.czesci.CzescLista;

public class Figura
{
	private int id, id_elementu, kod;
	private String pozycja;
	private int sztuk, srednica, ilosc_paczek, maszyna, sworzen;
	private String uwagi;
	private int ilosc_skokow;
	private int poziom_skoku;
	private ArrayList<Czesc> czesci = new ArrayList<Czesc>();
	private ArrayList<Czesc> czesci_atrapy = new ArrayList<Czesc>();

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getKod()
	{
		return kod;
	}

	public void setKod(int kod)
	{
		this.kod = kod;
	}

	public int getIdElementu()
	{
		return id_elementu;
	}

	public void setIdElementu(int id_elementu)
	{
		this.id_elementu = id_elementu;
	}

	public String getPozycja()
	{
		return pozycja;
	}

	public void setPozycja(String pozycja)
	{
		this.pozycja = pozycja;
	}

	public int getSztuk()
	{
		return sztuk;
	}

	public void setSztuk(int sztuk)
	{
		this.sztuk = sztuk;
	}

	public int getSrednica()
	{
		return srednica;
	}

	public void setSrednica(int srednica)
	{
		this.srednica = srednica;
	}

	public int getIloscPaczek()
	{
		return ilosc_paczek;
	}

	public void setIloscPaczek(int ilosc_paczek)
	{
		this.ilosc_paczek = ilosc_paczek;
	}

	public int getMaszyna()
	{
		return maszyna;
	}

	public void setMaszyna(int maszyna)
	{
		this.maszyna = maszyna;
	}

	public int getSworzen()
	{
		return sworzen;
	}

	public void setSworzen(int sworzen)
	{
		this.sworzen = sworzen;
	}

	public String getUwagi()
	{
		return uwagi;
	}

	public void setUwagi(String uwagi)
	{
		this.uwagi = uwagi;
	}

	public int getIloscSkokow()
	{
		return ilosc_skokow;
	}

	public void setIloscSkokow(int ilosc_skokow)
	{
		this.ilosc_skokow = ilosc_skokow;
	}

	public int getPoziomSkoku()
	{
		return poziom_skoku;
	}

	public void setPoziomSkoku(int poziom_skoku)
	{
		this.poziom_skoku = poziom_skoku;
	}

	public ArrayList<Czesc> getCzesci()
	{

		return this.czesci;
	}

	public void setCzesci()
	{
		this.czesci.clear();

		CzescLista c_lista = new CzescLista();
		ArrayList<Object> lista_czesci = c_lista.getCzesci(this.id);
		int size = lista_czesci.size();
		for (int i = 0; i < size; i++)
		{
			this.czesci.add((Czesc) lista_czesci.get(i));
		}
	}

	public ArrayList<Czesc> getCzesciAtrapy()
	{
		return this.czesci_atrapy;
	}

	public void setCzesciAtrapy()
	{
		this.czesci_atrapy.clear();

		CzescLista c_lista = new CzescLista();
		ArrayList<Object> lista_czesci = c_lista.getCzesciAtrapy(this.kod);
		int size = lista_czesci.size();
		for (int i = 0; i < size; i++)
		{
			this.czesci_atrapy.add((Czesc) lista_czesci.get(i));
		}
	}

	public void addCzesc(Czesc czesc)
	{
		this.czesci.add(czesc);
	}

	public String toString()
	{
		return Integer.toString(kod);
	}

	public int insert()
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("INSERT INTO t_element_figury(" + "id_elementu, kod, pozycja, sztuk, srednica, ilosc_paczek, maszyna, sworzen, uwagi, ilosc_skokow, poziom_skoku)"
				+ " VALUES(%d, %d, '%s', %d, %d, %d, %d, %d, '%s', %d, %d)", id_elementu, kod, pozycja, sztuk, srednica, ilosc_paczek, maszyna, sworzen, uwagi, ilosc_skokow, poziom_skoku);
		pgsq.queryOpertaion(query);
		return this.getLastId();
	}
	
	public void delete() {
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("DELETE FROM t_element_figury WHERE id_elementu=%d", id_elementu);
		pgsq.queryOpertaion(query);
		query = String.format("DELETE FROM t_element_figura_czesci WHERE id_figury=%d", id);
		pgsq.queryOpertaion(query);
	}

	public void insertCzesci(modules.figury.Figura fig)
	{
		MySQLJDBC pgsq = new MySQLJDBC();
		String query ="";
		int czesc_size = fig.getCzesci().size();
		int fig_id = getLastId();
		if(czesc_size>0)
		{
			query = "INSERT INTO t_element_figura_czesci(id_figury, dlugosc, kat, typ) VALUES";
		}
		for (int j = 0; j < czesc_size; j++)
		{
			modules.czesci.Czesc czesc_temp = fig.getCzesci().get(j);
			query +=String.format("(%d, %d, %d, '%s')", fig_id, czesc_temp.getDlugosc(), czesc_temp.getKat(), czesc_temp.getTyp());
			if(j!= czesc_size-1)
			{
				query +=",";
			}
			
		}
		if(czesc_size>0)
			pgsq.queryOpertaion(query);
	}

	public int getLastId()
	{
		int id = 0;
		MySQLJDBC pgsq = new MySQLJDBC();
		String query = String.format("SELECT id FROM t_element_figury order by id desc limit 1");
		try
		{
			Statement stmt = pgsq.getC().createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				id = rs.getInt("id");

			}
			rs.close();
		}
		catch (SQLException e)
		{

		}
		return id;
	}

}
