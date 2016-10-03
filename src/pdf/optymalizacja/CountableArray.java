package pdf.optymalizacja;

import java.util.ArrayList;

import dane.FiguraZamowienie;

public class CountableArray
{
	public ArrayList<Pair> array;
	
	public CountableArray()
	{
		array = new ArrayList<Pair>();
	}
	
	public void add(FiguraZamowienie fig, int value)
	{
		for(Pair pair : array)
		{
			if(value == 0) return;
			if(value == pair.value && pair.fig.equals(fig))
			{
				pair.count++;
				return;
			}
		}
		array.add(new Pair(fig, value));
	}
}