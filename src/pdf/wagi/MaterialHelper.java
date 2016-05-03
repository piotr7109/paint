package pdf.wagi;

public class MaterialHelper
{
	public int bst500, st500b, rb500w, ogolem;
	public int inne;

	public MaterialHelper()
	{
		bst500 = 0;
		st500b = 0;
		rb500w = 0;
	}

	public void dodajWage(int waga, int maszyna)
	{
		ogolem += waga;
		switch (maszyna)
		{
			case 1:
			case 2:
			case 3:
			case 4:
				bst500 += waga;
				break;
			case 5:
				st500b += waga;
				break;
			case 6:
			case 7:
				rb500w += waga;
				break;
		}
	}
}
