package pdf.wagi;

import dane.FiguraZamowienie;

public class MaszynaHelper
{
	public int form12, form16, msyn, sflex, cs40, mag, nrec, grec, ogolem;

	public MaszynaHelper()
	{
		form12 = 0;
		form16 = 0;
		msyn = 0;
		sflex = 0;
		cs40 = 0;
		mag = 0;
		nrec = 0;
		grec = 0;
		ogolem = 0;
	}

	public void dodajWage(FiguraZamowienie fig, int waga, int dlugosc)
	{
		ogolem += waga;
		switch (fig.maszyna)
		{
			case 1:
				mag += waga;
				break;
			case 2:
				nrec += waga;
				break;
			case 3:
				grec += waga;
				break;
			case 4:
				if(fig.figura.getKod() != 1 && dlugosc > 1200)
				{
					grec +=waga;
				}
				else if(fig.figura.getKod() != 1 && dlugosc < 80)
				{
					nrec+=waga;
					grec+=waga;
				}
				else if (fig.figura.getKod() != 1)
				{
					cs40 += waga;
				}
				else if (dlugosc > 1200 && fig.figura.getKod() == 1)
				{
					mag += waga;
				}
				else if (dlugosc < 80)
				{
					nrec+= waga;
				}
				sflex += waga;
				break;
			case 5:
				form12 += waga;
				break;
			case 6:
				msyn += waga;
				break;
			case 7:
				form16 += waga;
				break;
		}
	}
}
