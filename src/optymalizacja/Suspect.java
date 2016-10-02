package optymalizacja;

public class Suspect
{
	int index, value;

	public Suspect(int index, int value)
	{
		this.index = index;
		this.value = value;
	}

	public Suspect(Suspect suspect)
	{
		this.index = suspect.index;
		this.value = suspect.value;
	}

	public String toString()
	{
		return String.format("%d[%d]", value, index);
	}
}