package pdf.optymalizacja;

public class Row
{
	public String ilosc,dlugosc, mgMasz, pozycja, uwagi, szt;
	public String img;
	
	public Row(String ilosc, String dlugosc, String szt, String mgMasz, String img, String pozycja, String uwagi)
	{
		this.ilosc = ilosc;
		this.dlugosc = dlugosc;
		this.szt = szt;
		this.mgMasz = mgMasz;
		this.img = img;
		this.pozycja = pozycja;
		this.uwagi = uwagi;
	}
	
	public Row(String ilosc)
	{
		this(ilosc, "", "", "", "", "", "");
	}
}