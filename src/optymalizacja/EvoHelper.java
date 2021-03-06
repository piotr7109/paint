package optymalizacja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import dodatki.Tools;
import javafx.util.Pair;

public class EvoHelper
{
	public static Suspect[] arrayToSuspects(int[] source)
	{
		Suspect[] spcts = new Suspect[source.length];

		for (int i = 0; i < source.length; i++)
		{
			spcts[i] = new Suspect(i, source[i]);
		}

		return spcts;
	}

	public static Pair<Integer, Suspect[]> clearSuspect(Pair<Integer, Suspect[]> items)
	{
		ArrayList<Suspect> spcts = new ArrayList<>();
		for (Suspect item : items.getValue())
		{
			if (item.value != 0)
			{
				spcts.add(item);
			}
		}
		return new Pair<Integer, Suspect[]>(items.getKey(), spcts.toArray(new Suspect[spcts.size()]));
	}

	public static boolean isNear(int value, int target, int range)
	{
		if (value >= target - range && value <= target)
		{
			return true;
		}
		return false;
	}

	public static Suspect[] removeSuspect(Suspect[] source, Suspect[] resolution)
	{
		ArrayList<Suspect> list = new ArrayList<Suspect>(Arrays.asList(source));

		for (Suspect item : resolution)
		{

			if (item.value != 0)
			{
				for (Suspect suspect : list)
				{
					if (suspect.index == item.index)
					{
						list.remove(suspect);
						break;
					}
				}
			}
		}

		return list.toArray(new Suspect[list.size()]);
	}

	public static Suspect[][] generateSuspects(Suspect[] source, int max, int numOfSuspects)
	{
		Suspect[][] suspects = new Suspect[numOfSuspects][source.length];

		for (int i = 0; i < numOfSuspects; i++)
		{
			suspects[i] = createSuspect(source, max);
			while (arrayValue(suspects[i]) > max)
			{
				suspects[i] = createSuspect(source, max);
			}
		}
		return suspects;
	}

	public static Suspect[] createSuspect(Suspect[] source, int max)
	{
		int size = source.length;
		int random;
		Suspect[] out = new Suspect[size];
		int numOfSynapses = (int) (1.5 * Math.random() * (double) max / averageValue(source));
		for (int i = 0; i < size; i++)
		{
			out[i] = new Suspect(0, 0);
		}

		for (int i = 0; i < numOfSynapses; i++)
		{
			random = Tools.randInt(0, size - 1);
			out[random] = new Suspect(source[random]);
		}

		return out;
	}

	private static double averageValue(Suspect[] array)
	{
		return (double) arrayValue(array) / (double) array.length;
	}

	public static int arraySize(Suspect[] array)
	{
		int value = 0;

		for (Suspect item : array)
		{
			if (item.value > 0)
				value++;
		}

		return value;
	}

	public static int arrayValue(Suspect[] array)
	{
		int value = 0;

		for (Suspect item : array)
		{
			value += item.value;
		}

		return value;
	}

	public static Integer[] sortArray(Integer[] array)
	{
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(array));

		Collections.sort(list);
		Collections.reverse(list);

		return list.toArray(new Integer[list.size()]);
	}

	public static void displayArray(Suspect[] array)
	{
		for (Suspect item : array)
		{
			System.out.print(item);
		}
		System.out.println();
	}
}
