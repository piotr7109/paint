package inne;

import dane.FiguraZamowienie;
import dodatki.Tools;
import pdf.optymalizacja.CountableArray;

public class Test
{
	public static void main(String[] args) throws Exception
	{
		
		for (int i = 0; i < 100000; i++)
		{
			System.out.println();
			System.out.print(Tools.randInt(0, 1000) + " ");
		}
		System.out.println("END" + Tools.randInt(0, 1000));

		/*
		 * System.out.println("START"); CountableArray cAr = new
		 * CountableArray();
		 * 
		 * cAr.add(new FiguraZamowienie(), 10); cAr.add(new FiguraZamowienie(),
		 * 20); cAr.add(new FiguraZamowienie(), 10);
		 * 
		 * for (int i = 0; i < cAr.array.size(); i++) {
		 * System.out.println(cAr.array.get(i).count); }
		 * 
		 * int a = 3000; int b = 3000;
		 * 
		 * System.out.println(a == b);
		 * 
		 * System.out.println("END");
		 */
	}

	public static void generateBinaryNumbers()
	{
		String a[] = new String[1000000];
		int zera = Integer.toBinaryString(a.length - 1).length() - 1;
		for (int i = 1; i < a.length; i++)
		{
			String binary = Integer.toBinaryString(i);
			for (int j = binary.length(); j <= zera; j++)
			{
				binary = "0" + binary;
			}
			a[i] = binary;
			System.out.println(binary);

		}
	}

}
