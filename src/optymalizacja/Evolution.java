package optymalizacja;

import java.io.IOException;
import java.util.ArrayList;

import javafx.util.Pair;

public class Evolution
{
	private int[] data;
	private Integer[] max; // should be sorted in descending order
	private ArrayList<Pair<Integer, Suspect[]>> sol;

	public static void main(String[] args)
	{
		int[] source = { 1000, 300, 2, 35, 234, 123, 433, 234, 123, 400, 1102, 200, 1001, 5, 41, 21, 333, 654, 543, 123, 200 };
		Integer[] max = { 1400, 1200, 1000 };

		Evolution evo = new Evolution(source, max);
		evo.runEvolve(50, 20, 100);
	}

	public Evolution(int[] source, Integer[] max)
	{
		this.data = source;
		this.max = EvoHelper.sortArray(max);
	}

	public void runEvolve(int grandIterations, int iterations, int numOfSuspects)
	{
		int waste = 0;

		for (int i = 0; i < grandIterations; i++)
		{
			if (i % 30 == 0)
			{
				System.out.println();
			}

			int result = evolve(iterations, numOfSuspects, waste);
			if (waste == 0)
			{
				waste = result;
			}
			else
			{
				if (waste > result)
				{
					waste = result;
				}
			}
			int progress = (int) (((double) i / (double) grandIterations) * 100);
			System.out.print(progress + "% ");

		}
		System.out.println();
		for (Pair<Integer, Suspect[]> item : sol)
		{
			item = EvoHelper.clearSuspect(item);
			EvoHelper.displayArray(item.getValue());
			System.out.println(EvoHelper.arrayValue(item.getValue()));
		}

		System.out.println("WASTE: " + waste);

	}

	private int evolve(int iterations, int numOfSuspects, int actualWaste)
	{
		int[] _data = new int[data.length];
		System.arraycopy(data, 0, _data, 0, data.length);
		Suspect[] source = EvoHelper.arrayToSuspects(_data);

		int errorPoint = 0;
		ArrayList<Pair<Integer, Suspect[]>> solutions = new ArrayList<Pair<Integer, Suspect[]>>();
		int waste = 0;
		while (source.length > 0)
		{
			for (int i = 0; i < iterations; i++)
			{
				Suspect[][] spcts = EvoHelper.generateSuspects(source, max[0], numOfSuspects);
				suspectsLoop: for (int j = 0; j < numOfSuspects; j++)
				{
					int sum = EvoHelper.arrayValue(spcts[j]);
					for (int weight : max)
					{
						if (EvoHelper.isNear(sum, weight, errorPoint) && sum > 0)
						{
							solutions.add(new Pair<Integer, Suspect[]>(weight, spcts[j]));
							waste += weight - EvoHelper.arrayValue(spcts[j]);
							if (actualWaste != 0 && actualWaste <= waste)
							{
								return actualWaste;
							}
							source = EvoHelper.removeSuspect(source, spcts[j]);
							break suspectsLoop;
						}
					}

				}
			}
			errorPoint++;
		}

		sol = solutions;

		return waste;
	}

}
