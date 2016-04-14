package modules.zamowienie.obiekty.figury;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dodatki.PostgreSQLJDBC;
import modules.czesci.Czesc;
import modules.czesci.CzescLista;

public class Figura
{
	private int id, id_obiektu, kod;
	private String pozycja;
	private int sztuk, srednica, ilosc_paczek, material, sworzen;
	private String uwagi;
	private int ilosc_skokow;
	private double poziom_skoku;
	private ArrayList<Czesc> czesci = new ArrayList<Czesc>();

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

	public int getIdObiektu()
	{
		return id_obiektu;
	}

	public void setIdObiektu(int id_obiektu)
	{
		this.id_obiektu = id_obiektu;
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

	public int getMaterial()
	{
		return material;
	}

	public void setMaterial(int material)
	{
		this.material = material;
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

	public double getPoziomSkoku()
	{
		return poziom_skoku;
	}

	public void setPoziomSkoku(double poziom_skoku)
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
		PostgreSQLJDBC pgsq = new PostgreSQLJDBC();
		String query = String.format("INSERT INTO t_obiekt_figury(" 
		+ "id_obiektu, kod, pozycja, sztuk, srednica, ilosc_paczek, material, sworzen, uwagi, ilosc_skokow, poziom_skoku)"
		+ " VALUES(%d, %d, '%s', %d, %d, %d, %d, %d, '%s', %d, %d)", 
		id_obiektu, kod, pozycja, sztuk, srednica, ilosc_paczek, material, sworzen, uwagi, ilosc_skokow, poziom_skoku);
		pgsq.queryOpertaion(query);
		return this.getLastId();
	}

	public int getLastId()
	{
		int id = 0;
		PostgreSQLJDBC pgsq = new PostgreSQLJDBC();
		String query = String.format("SELECT id FROM t_obiekt_figury order by id desc limit 1");
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
