package inne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import dane.FiguraZamowienie;
import pdf.optymalizacja.CountableArray;

public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{	
		System.out.println("START");
		CountableArray cAr = new CountableArray();
		
		cAr.add(new FiguraZamowienie(), 10);
		cAr.add(new FiguraZamowienie(), 20);
		cAr.add(new FiguraZamowienie(), 10);

		for(int i = 0; i < cAr.array.size(); i++)
		{
			System.out.println(cAr.array.get(i).count);
		}
		
		int a = 3000;
		int b = 3000;
		
		System.out.println(a==b);
		
		System.out.println("END");

	}

	private static void optymalize(Integer[] source, int[] max)
	{

		ArrayList<ArrayList<IndexedInteger>> output = new ArrayList<ArrayList<IndexedInteger>>();

		while (isNotEmpty(source))
		{
			int number = 0;
			ArrayList<IndexedInteger> out = new ArrayList<IndexedInteger>();
			output.add(out);
			for (int i = 0; i < source.length; i++)
			{
				int item = source[i];
				if (item > 0)
				{
					number += item;
					if (number < max[0])
					{
						out.add(new IndexedInteger(source[i].intValue(), i));
						source[i] = 0;
					}
					else
					{
						number -= item;
					}
				}
			}
		}

		for (ArrayList<IndexedInteger> array : output)
		{
			System.out.println("ARRAY: ");
			for (IndexedInteger number : array)
			{
				System.out.print(" " + number.value+"("+number.index+")");
			}
			System.out.println();
			System.out.println("SUM: " + getSumofArrayElements(array));
			System.out.println();
		}

		System.out.println("");

		for (Integer item : source)
		{
			System.out.print(item + " ");
		}

	}

	private static int getSumofArrayElements(ArrayList<IndexedInteger> source)
	{
		int number = 0;

		for (IndexedInteger item : source)
		{
			number += item.value;
		}

		return number;
	}

	private static boolean isNotEmpty(Integer[] source)
	{
		boolean isEmpty = false;

		for (Integer number : source)
		{
			if (number != 0)
			{
				return true;
			}
		}

		return isEmpty;
	}

	private static Integer[] getArrayOfIntegers()
	{
		Integer[] source = { 1000, 300, 2, 35, 234, 123, 433, 234, 123, 400, 1102, 200, 1001, 5, 41, 21, 333, 654, 543, 123, 200 };
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(source));

		Collections.sort(list);
		Collections.reverse(list);

		for (int i = 0; i < source.length; i++)
		{
			source[i] = list.get(i);
		}

		return source;
	}

	private static void generateBinaryNumbers()
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

class IndexedInteger
{
	public int value, index;
	public IndexedInteger(int value, int index)
	{
		this.value = value;
		this.index = index;
	}
}
