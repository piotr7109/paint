package pdf.optymalizacja;

import dane.FiguraZamowienie;

public class Pair
{
	public FiguraZamowienie fig;
	public int value;
	public int count;
	
	public Pair(FiguraZamowienie fig, int value)
	{
		this.fig = fig;
		this.value = value;
		this.count = 1;
	}
}