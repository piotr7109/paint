package dane;

import modules.figury.Figura;

public class FiguraZamowienie
{
	public Figura figura;
	public String pozycja;
	public int ilosc_sztuk;
	public int srednica;
	public int fig;
	public String kolor;
	public int material;
	// public int mat??
	public int sworzen;
	public double waga;

	public FiguraZamowienie()
	{
		figura = null;
		pozycja = "0";
		ilosc_sztuk = 0;
		srednica = 6;
		fig = 0;
		kolor = "0";
		material = 0;
		sworzen = 32;
		waga = 0.222;
	}
	public FiguraZamowienie(FiguraZamowienie f_zam)
	{
		this.figura = f_zam.figura;
		this.pozycja = f_zam.pozycja;
		this.ilosc_sztuk = f_zam.ilosc_sztuk;
		this.srednica = f_zam.srednica;
		this.fig = f_zam.fig;
		this.kolor = f_zam.kolor;
		this.material = f_zam.material;
		this.sworzen = f_zam.sworzen;
		this.waga = f_zam.waga;
	}

}
