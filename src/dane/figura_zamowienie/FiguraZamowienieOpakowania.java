package dane.figura_zamowienie;

import dane.FiguraZamowienie;

public class FiguraZamowienieOpakowania extends FiguraZamowienie
{
	public int nr_opakowania;
	public FiguraZamowienieOpakowania()
	{
		super();
		this.nr_opakowania = 1;
	}
	public FiguraZamowienieOpakowania(FiguraZamowienie f_zam)
	{
		this.figura = f_zam.figura;
		this.pozycja = f_zam.pozycja;
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
