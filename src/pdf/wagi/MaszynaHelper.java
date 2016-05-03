package pdf.wagi;

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
	
	public void dodajWage(int waga, int maszyna)
	{
		ogolem += waga;
		switch (maszyna)
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
