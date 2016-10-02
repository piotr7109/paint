package dodatki;

import java.util.ArrayList;

import dane.FiguraZamowienie;
import dane.ZamowienieDane;
import modules.czesci.Czesc;

public class MathHelper
{
	public static double obliczDlugoscRzeczywista(FiguraZamowienie fig_zam)
	{
		double dlugosc = 0;

		int size = fig_zam.figura.getCzesci().size();
		int sworzen = fig_zam.sworzen / 2;
		int srednica = fig_zam.srednica;

		ArrayList<Czesc> czesci = fig_zam.figura.getCzesci();
		for (int i = 0; i < size - 1; i++)
		{
			Czesc czesc = czesci.get(i);
			Czesc czesc_n = czesci.get(i + 1);

			if (czesc.getTyp().equals("okrag") || czesc_n.getTyp().equals("okrag"))
			{
				dlugosc += czesc.getDlugosc();

			}
			else
			{
				int kat = czesc_n.getKat();
				kat = Math.abs(kat);

				double a = czesc.getDlugosc();
				double b = 2 * Math.PI * ((sworzen + srednica) / 10) * kat / 360 / 2;
				// int c = czesc_n.getDlugosc();
				dlugosc += (a - b);

			}
		}

		dlugosc += czesci.get(size - 1).getDlugosc();

		return dlugosc;
	}
	
	private static ArrayList<Czesc> cloneCzesci(int index)
	{
		ArrayList<Czesc> czesci = new ArrayList<Czesc>();
		int size = ZamowienieDane.figury.get(index).figura.getCzesci().size();

		for (int i = 0; i < size; i++)
		{
			Czesc c = new Czesc(ZamowienieDane.figury.get(index).figura.getCzesci().get(i));
			czesci.add(c);
		}

		return czesci;
	}
}
