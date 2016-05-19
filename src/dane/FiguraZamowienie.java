package dane;

import modules.figury.Figura;

public class FiguraZamowienie
{
	public Figura figura;
	public String pozycja;
	public int ilosc_sztuk;
	public int srednica;
	public int fig;
	public int ilosc_paczek;
	public int maszyna;
	// public int mat??
	public int sworzen;
	public double waga;

	public String uwagi;

	public FiguraZamowienie()
	{
		figura = null;
		pozycja = "0";
		ilosc_sztuk = 1;
		srednica = 6;
		fig = 1;
		ilosc_paczek = 1;
		maszyna = 4;
		sworzen = 32;
		waga = 0.222;
		uwagi = "";
	}

	public FiguraZamowienie(FiguraZamowienie f_zam)
	{
		this.figura = new Figura(f_zam.figura);
		this.pozycja = (Integer.parseInt(f_zam.pozycja)+1)+"";
		this.ilosc_sztuk = f_zam.ilosc_sztuk;
		this.srednica = f_zam.srednica;
		this.fig = f_zam.fig;
		this.ilosc_paczek = f_zam.ilosc_paczek;
		this.maszyna = f_zam.maszyna;
		this.sworzen = f_zam.sworzen;
		this.waga = f_zam.waga;
		this.uwagi = f_zam.uwagi;
	}

}
