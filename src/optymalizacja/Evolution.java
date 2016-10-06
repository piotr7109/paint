package optymalizacja;

import java.util.ArrayList;

import ekrany.optymalizacja.ProgressFrame;
import javafx.util.Pair;

public class Evolution implements Runnable
{
	private int[] data;
	private Integer[] max; // should be sorted in descending order
	public ArrayList<Pair<Integer, Suspect[]>> sol;
	public int waste;
	private ProgressFrame pFrame;

	public Evolution(int[] source, Integer[] max)
	{
		this.data = source;
		this.max = EvoHelper.sortArray(max);
	}

	private int progress;

	private int grandIterations, iterations, numOfSuspects;

	public void setParameters(int grandIterations, int iterations, int numOfSuspects, ProgressFrame pFrame)
	{
		this.grandIterations = grandIterations;
		this.iterations = iterations;
		this.numOfSuspects = numOfSuspects;
		this.pFrame = pFrame;
	}

	public void run()
	{
		int waste = 0;
		boolean initialized = false;

		for (int i = 0; i < grandIterations; i++)
		{
			int result = evolve(iterations, numOfSuspects, waste);
			if (!initialized)
			{
				initialized = true;
			}
			else
			{
				if (waste > result)
				{
					waste = result;
				}
			}

			progress = (int) (((double) i / (double) grandIterations) * 100);
			pFrame.updateProgress(progress);
			System.out.println(waste);
			
			if(waste == 0)
			{
				break;
			}

		}
		for (Pair<Integer, Suspect[]> item : sol)
		{
			item = EvoHelper.clearSuspect(item);
		}

		this.waste = waste;

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
