package dodatki;

public class Luk 
{
	public int x, y, rozmiar, kat, kat2, x_koniec, y_koniec, kierunek;
	
	
	public void setLuk(int x, int y, int rozmiar, int kat, int kat2)
	{
		this.x = x;
    	this.y= y;
    	this.rozmiar = rozmiar;
    	this.kat = kat;
    	this.kat2 = kat2; 
	}
	
	public String toString()
	{
		return x+" "+y+" "+rozmiar+" "+kat+" "+kat2; 
	}
}
